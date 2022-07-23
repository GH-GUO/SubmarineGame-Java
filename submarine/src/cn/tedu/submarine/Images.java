package cn.tedu.submarine;
import javax.swing.ImageIcon;
public class Images {
    public static ImageIcon sea;             //海洋
    public static ImageIcon gameover;        //游戏结束
    public static ImageIcon battleship;      //战舰图片
    public static ImageIcon obsersubm;       //侦察潜艇图片
    public static ImageIcon torpesubm;       //鱼雷潜艇图片
    public static ImageIcon minesubm;       //水雷潜艇图片
    public static ImageIcon mine;            //水雷图片
    public static ImageIcon bomb;            //炸弹图片


    static { //初始化静态图片
        sea=new ImageIcon("img/sea.png");
        gameover=new ImageIcon("img/gameover.png");
        battleship=new ImageIcon("img/battleship.png");
        obsersubm=new ImageIcon("img/obsersubm.png");
        torpesubm=new ImageIcon("img/torpesubm.png");
        minesubm=new ImageIcon("img/minesubm.png");
        mine=new ImageIcon("img/mine.png");
        bomb=new ImageIcon("img/bomb.png");
    }

    public static void main(String[] args) {   //测试图片是否读取成功，8正确，其余数字都是读取失败。
        System.out.println(sea.getImageLoadStatus());
        System.out.println(gameover.getImageLoadStatus());
        System.out.println(battleship.getImageLoadStatus());
        System.out.println(obsersubm.getImageLoadStatus());
        System.out.println(torpesubm.getImageLoadStatus());
        System.out.println(minesubm.getImageLoadStatus());
        System.out.println(mine.getImageLoadStatus());
        System.out.println(bomb.getImageLoadStatus());
    }
}
