package cn.tedu.submarine;

import javax.swing.ImageIcon;

/**战舰*/
public class Battleship extends SeaObject{
    private int life;
    /**构造方法*/
    public Battleship(){
        super(66,26,270,124,30);
        life = 5;
    }
    /** 重写move()移动*/
    public void move(){
    }
    /**重写getImage获取图片*/
    public ImageIcon getImage(){
        return Images.battleship;
    }
    /**发射炸弹*/
    public Bomb shootBomb(){
        return new Bomb(this.x,this.y);
    }

    public void moveLeft(){
        x-=speed;
    }
    public void moveRight(){
        x+=speed;
    }
    /** 战舰増命*/
    public void addLife(int num){
        life+=num;
    }
    /**获取命数*/
    public int getLife(){
        return life;
    }

    /**减命*/
    public void subtractLife(){
        life--;
    }

}
