package Piece;

import Board.ChessBoard;
import Judge.Judge;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class NullPiece implements Piece{
    /**
     * 空子类，棋盘上没有子的地方就要放上空子，空子id只能为0，非空子id不能为1，空子名字固定为”Null“，空子状态固定为死亡，空子无阵营，空子保存了位置信息
     */

    private final static List<Point> can_go=new ArrayList<>();
    private final static NullPiece[][] null_map = new NullPiece[ChessBoard.WIDTH+1][ChessBoard.HIGH+1]; //空子缓存，以后需要获取空子时直接在这里面获取
    static {//初始化空子缓存
        for (int i = 0; i < null_map.length; i++) {
            for (int j = 0; j < null_map[i].length; j++) {
                null_map[i][j]=new NullPiece(i,j);
            }
        }
        can_go.add(new Point(0,0));
    }

    private final Point position;
    public final static String NAME = "Null";
    public final static byte ID = 0;

    private NullPiece() {
        position=new Point(0,0);
    }
    private NullPiece(int x,int y) {
        position=new Point(x,y);
    }

    /**
     * 从缓存中获取空子
     * @param x 要获取空子的x坐标
     * @param y 要获取空子的y坐标
     * @return  返回位置为（x，y）的空子
     */
    public static Piece GetNull(int x,int y){
        return null_map[x][y];
    }
    @Override
    public void Move(int x,int y) {//严格上不允许改变空子位置信息，要想让某个位置为空子，请使用GetNull方法
    }

    @Override
    public BufferedImage[] GetImage() {
        return new BufferedImage[3];
    }

    @Override
    public String GetName() {
        return NullPiece.NAME;
    }

    @Override
    public byte GetID() {
        return NullPiece.ID;
    }

    @Override
    public byte GetGroup() {
        return Judge.G_NULL;
    }

    @Override
    public Point GetPosition() {
        return (Point) position.clone();
    }

    @Override
    public boolean IsAlive() {
        return false;
    }

    @Override
    public void SetAlive(boolean b) {}

    @Override
    public List<Point> GetCanGo() {
        return can_go;
    }

    @Override
    public void CountCanGo(Piece[][] map){}
}
