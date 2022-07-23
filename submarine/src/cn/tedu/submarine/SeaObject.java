package cn.tedu.submarine;

import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Graphics;

public abstract class SeaObject {
    public static final int LIVE=0;
    public static final int DEAD=1;
    protected int state=LIVE;

    protected int width;       //宽
    protected int height;      //高
    protected int x;           //x坐标
    protected int y;           //y坐标
    protected int speed;       //速度
    /** 给侦查、鱼类、水雷潜艇*/
    public SeaObject(int width,int height){
        this.width=width;
        this.height=height;
        x=-width;
        Random rand = new Random();
        y=rand.nextInt(World.HEIGHT-height-150+1)+150;
        speed=rand.nextInt(3)+1;
    }
    /** 炸弹水雷战舰*/
    public SeaObject(int width,int height,int x,int y, int speed){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public abstract void move();
    /** 获取对象的图片*/
    public abstract ImageIcon getImage();
    /** 判断对象是活着的*/
    public boolean isLive(){
        return state==LIVE;
    }
    /** 判断对象是死了的*/
    public boolean isDead(){
        return state==DEAD;
    }

    /** 画对象*/
    public void paintImage(Graphics g){
        if(this.isLive()){
            this.getImage().paintIcon(null,g,this.x,this.y);
        }
    }
    /** 检测潜艇是否越界*/
    public boolean isOutOfBounds(){
        return this.x>=World.WIDTH;
    }
    /**检测碰撞*/
    public boolean isHit(SeaObject other){
        //假设this表示潜艇，other表示炸弹
        int x1=this.x-other.width;  //x1:潜艇的x-炸弹的宽
        int x2=this.x+this.width;   //x2:潜艇的x-潜艇的宽
        int y1=this.y-other.height; //y1:潜艇的y-炸弹的高
        int y2=this.y+this.height;  //y2:潜艇的y-潜艇的高
        int x= other.x;   //x：炸弹的x
        int y= other.y;   //y：炸弹的y

        return x>=x1 && x<=x2 && y>=y1 && y<=y2;//x在x1与x2之间，y在y1与y2之间即为撞上
    }
    /**海洋对象去死*/
    public void goDead(){
        state=DEAD;
    }

}
