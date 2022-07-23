package cn.tedu.submarine;


import javax.swing.ImageIcon;

//侦察潜艇
public class ObserveSubmarine extends SeaObject implements EnemyScore{
    public ObserveSubmarine(){
        super(63,19);
    }
    /** 重写move()移动*/
    public void move(){
        x+=speed;
    }
    /**重写getImage获取图片*/
    public ImageIcon getImage(){
        return Images.obsersubm;
    }

    public int getScore(){
        return 10;//打掉侦查潜艇的10分
    }
}
