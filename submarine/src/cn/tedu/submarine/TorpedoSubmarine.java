package cn.tedu.submarine;

import javax.swing.ImageIcon;
import java.util.Random;

//鱼雷潜艇
public class TorpedoSubmarine extends SeaObject implements EnemyScore{
    public TorpedoSubmarine(){
        super(64,20);
    }
    /** 重写move()移动*/
    public void move(){
        x+=speed;
    }
    /**重写getImage获取图片*/
    public ImageIcon getImage(){
        return Images.torpesubm;
    }
    public int getScore(){
        return 40;
    }
}
