package Piece;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public interface Piece {//棋子接口类

    /**
     * 移动自身位置
     * @param x 目标位置的x坐标
     * @param y 目标位置的x坐标
     */
    void Move(int x,int y);

    /**
     * 获取棋子的所有图片
     * @return 返回该棋子的所有图片
     */
    BufferedImage[] GetImage();

    /**
     * 获取该棋子名称
     * @return 返回该棋子名称
     */
    String GetName();

    /**
     * 获取该棋子id
     * @return 返回该棋子id
     */
    byte GetID();

    /**
     * 获取该棋子阵营
     * @return 返回该棋子阵营
     */
    byte GetGroup();

    /**
     * 获取该棋子位置
     * @return 返回该棋子位置
     */
    Point GetPosition();

    /**
     * 获取该棋子存活状态
     * @return 返回该棋子是否存活
     */
    boolean IsAlive();

    /**
     * 设置该棋子存活状态
     * @param b 真为存活
     */
    void SetAlive(boolean b);

    /**
     * 获取该棋子可能能去的位置（无视障碍和棋盘边界）
     * @return 返回该棋子可能能去的位置
     */
    List<Point> GetCanGo();

    void CountCanGo(Piece[][] map);
}
