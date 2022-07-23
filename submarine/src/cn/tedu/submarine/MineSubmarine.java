package cn.tedu.submarine;


import javax.swing.ImageIcon;

//水雷潜艇
public class MineSubmarine extends SeaObject implements EnemyLife{
    public MineSubmarine(){
        super(63,19);
    }
    /** 重写move()移动*/
    public void move(){
        x+=speed;
    }
    /**重写getImage获取图片*/
    public ImageIcon getImage(){
        return Images.minesubm;
    }

    //发射水雷
    public Mine shootMine(){
        return new Mine(this.x+this.width,this.y-11);
    }
    public int getLife(){
        return 1;
    }
}
