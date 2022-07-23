package cn.tedu.submarine;

import javax.swing.ImageIcon;

//炸弹
public class Bomb extends SeaObject{
    public Bomb(int x,int y){
        super(9,12,x,y,3);
    }
    /** 重写move()移动*/
    public void move(){
        y+=speed;
    }
    /**重写getImage获取图片*/
    public ImageIcon getImage(){
        return Images.bomb;
    }
    /** 重写检查炸弹是否越界*/
    public boolean isOutOfBounds(){
        return this.y>=World.HEIGHT;
    }
}
