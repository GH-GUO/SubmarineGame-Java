package cn.tedu.submarine;

import javax.swing.ImageIcon;

//水雷
public class Mine extends SeaObject{
    public Mine(int x,int y){
        super(11,11,x,y,1);
    }
    /** 重写move()移动*/
    public void move(){
        y-=speed;
    }
    /**重写getImage获取图片*/
    public ImageIcon getImage(){
        return Images.mine;
    }
    public boolean isOutOfBounds() {
        return this.y<=150-this.height;
    }
}
