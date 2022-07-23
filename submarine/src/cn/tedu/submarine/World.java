package cn.tedu.submarine;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Arrays;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*整个游戏世界*/
public class World extends JPanel{
    public static final int WIDTH=641;
    public static final int HEIGHT=479;

    public static final int RUNNING=0;//运行
    public static final int PAUSE=1;  //暂停
    public static final int GAME_OVER=2;//游戏结束
    private int state=RUNNING;

    private Battleship ship=new Battleship();
    private SeaObject[] submarines={};
    private Mine[] mines={};
    private Bomb[] bombs={};



    /**生成潜艇对象*/
    private SeaObject nextsubmarine(){
        Random rand=new Random();
        int type=rand.nextInt(20);
        if (type<10){
            return new ObserveSubmarine();
        }else if (type<16){
            return new TorpedoSubmarine();
        }else{
            return new MineSubmarine();
        }
    }
    //潜艇入场计数
    private int subEnterIndex=0;
    private void submarineEnterAction(){
        subEnterIndex++;
        if (subEnterIndex%10==0){
            SeaObject obj=nextsubmarine();
            submarines=Arrays.copyOf(submarines,submarines.length+1);
            submarines[submarines.length-1]=obj;
        }
    }
    private int minEnterIndex=0;
    //水雷入场
    private void mineEnterAction(){
        minEnterIndex++;
        if (minEnterIndex%40==0){
            for (int i=0;i<submarines.length;i++){
                if (submarines[i] instanceof MineSubmarine){
                    MineSubmarine ms=(MineSubmarine) submarines[i];
                    Mine obj = ms.shootMine();
                    mines=Arrays.copyOf(mines,mines.length+1);
                    mines[mines.length-1]=obj;
                }
            }
        }
    }

    private void moveAction(){
        for (int i=0;i<submarines.length;i++){
            submarines[i].move();
        }
        for (int i=0;i<mines.length;i++){
            mines[i].move();
        }
        for (int i=0;i<bombs.length;i++){
            bombs[i].move();
        }
    }

    private void outOfBoundsAction(){
        for (int i=0;i<submarines.length;i++){
            if (submarines[i].isOutOfBounds() || submarines[i].isDead()){
                submarines[i]=submarines[submarines.length-1];
                submarines=Arrays.copyOf(submarines,submarines.length-1);
            }
        }
        for (int i=0;i<mines.length;i++){
            if (mines[i].isOutOfBounds() || mines[i].isDead()){
                mines[i]=mines[mines.length-1];
                mines=Arrays.copyOf(mines,mines.length-1);
            }
        }
        for (int i=0;i<bombs.length;i++){
            if (bombs[i].isOutOfBounds() || bombs[i].isDead()){
                bombs[i]=bombs[bombs.length-1];
                bombs=Arrays.copyOf(bombs,bombs.length-1);
            }
        }
    }

    private int score=0;
    /** 炸弹与潜艇的碰撞*/
    private void bombBangAction(){
        for (int i=0;i<bombs.length;i++){
            Bomb b=bombs[i];
            for (int j=0;j<submarines.length;j++){
                SeaObject s=submarines[j];
                if (b.isLive()&&s.isLive()&&s.isHit(b)){
                    s.goDead();
                    b.goDead();
                    //的东西
                    if (s instanceof EnemyScore){
                        EnemyScore es=(EnemyScore) s;
                        score+= es.getScore();
                    }
                    if (s instanceof EnemyLife){
                        EnemyLife el=(EnemyLife) s;
                        int num=el.getLife();
                        ship.addLife(num);
                    }
                }
            }
        }
    }
    public void mineBangAction(){
        for (int i=0;i<mines.length;i++){
            Mine m=mines[i];
            if (m.isLive()&& ship.isLive()&&m.isHit(ship)){
                m.goDead();
                ship.subtractLife();
            }
        }
    }

    /**检测游戏结束*/
    private void checkGameOverAction(){
        if (ship.getLife()<=0){
            state=GAME_OVER;
        }
    }

    /**启动程序执行*/
    private void action(){
        KeyAdapter k=new KeyAdapter() {
            @Override
            /** 重写keyReleased按键抬起事件*/
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_P){
                    if (state==RUNNING){
                        state=PAUSE;
                    }else if (state==PAUSE){
                        state=RUNNING;
                    }
                }
                if (state==RUNNING){
                    if (e.getKeyCode()==KeyEvent.VK_SPACE){
                        Bomb obj= ship.shootBomb();
                        bombs=Arrays.copyOf(bombs,bombs.length+1);
                        bombs[bombs.length-1]=obj;
                    }
                    if (e.getKeyCode()==KeyEvent.VK_LEFT){
                        ship.moveLeft();
                    }
                    if (e.getKeyCode()==KeyEvent.VK_RIGHT){
                        ship.moveRight();
                    }
                }
            }
        };                 //键盘侦听器
        this.addKeyListener(k);       //添加侦听


        Timer timer=new Timer();//定时器对象
        int interval=10;//间隔10毫秒
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (state==RUNNING){
                    submarineEnterAction();
                    mineEnterAction();
                    moveAction();
                    bombBangAction();
                    mineBangAction();
                    outOfBoundsAction();
                    checkGameOverAction();
                    repaint();//重画
                }
            }
        }, interval, interval);//定时日程表
    }

    /** 重写paint()画 g:系统自带画笔*/
    public void paint(Graphics g){
        Images.sea.paintIcon(null,g,0,0);
        ship.paintImage(g);
        for (int i=0;i< submarines.length;i++){
            submarines[i].paintImage(g);
        }
        for (int i=0;i< mines.length;i++){
            mines[i].paintImage(g);
        }
        for (int i=0;i<bombs.length;i++){
            bombs[i].paintImage(g);
        }
        g.drawString("SCORE"+score,200,50);
        g.drawString("LIFE"+ship.getLife(),400,50);

        if (state==GAME_OVER){
            Images.gameover.paintIcon(null,g,0,0);
        }
    }


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        World world=new World();
        world.setFocusable(true);
        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH+16,HEIGHT+39);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        world.action();//启动程序执行
    }
}
