package Piece;

import Judge.Judge;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChessPiece implements Piece {
    /**
     * 棋子类
     */

    public final static BufferedImage[] pieces_images = new BufferedImage[15];    //所有棋子图片
    public final static BufferedImage[] pieces_select_images = new BufferedImage[15]; //所有棋子被选中时的图片
    public final static BufferedImage[] pieces_pre_images = new BufferedImage[15];    //所有棋子”允许位置“图片

    static {//加载所有棋子图片
        LoadImages();
    }

    //各棋子名字，不同种类棋子名字唯一，所以可通过名字区分棋子
    public final static String P_JIANG_CHU = "将";
    public final static String P_SHI_CHU = "士";
    public final static String P_XIANG_CHU = "象";
    public final static String P_MA_CHU = "馬";
    public final static String P_JU_CHU = "車";
    public final static String P_PAO_CHU = "砲";
    public final static String P_ZU_CHU = "卒";

    public final static String P_SHUAI_HAN = "帅";
    public final static String P_SHI_HAN = "仕";
    public final static String P_XIANG_HAN = "相";
    public final static String P_MA_HAN = "傌";
    public final static String P_CHE_HAN = "车";
    public final static String P_PAO_HAN = "炮";
    public final static String P_BING_HAN = "兵";

    private final List<Point> can_go = new ArrayList<>();
    private final Point position;   //该棋子位置
    private final String name;      //该棋子名称
    private final byte group;       //该棋子阵营
    public final byte ID;           //该棋子id
    private final BufferedImage[] image = new BufferedImage[3];   //该棋子的3种状态的图片
    private boolean alive = true;     //该棋子存活状态

    /**
     * 加载所有棋子图片
     */
    public static void LoadImages() {
        try {
            pieces_images[0] = ImageIO.read(new File("image/p_bing_han.png"));
            pieces_images[1] = ImageIO.read(new File("image/p_che_han.png"));
            pieces_images[2] = ImageIO.read(new File("image/p_jiang_chu.png"));
            pieces_images[3] = ImageIO.read(new File("image/p_ju_chu.png"));
            pieces_images[4] = ImageIO.read(new File("image/p_ma_chu.png"));
            pieces_images[5] = ImageIO.read(new File("image/p_ma_han.png"));
            pieces_images[6] = ImageIO.read(new File("image/p_pao_chu.png"));
            pieces_images[7] = ImageIO.read(new File("image/p_pao_han.png"));
            pieces_images[8] = ImageIO.read(new File("image/p_shi_chu.png"));
            pieces_images[9] = ImageIO.read(new File("image/p_shi_han.png"));
            pieces_images[10] = ImageIO.read(new File("image/p_shuai_han.png"));
            pieces_images[11] = ImageIO.read(new File("image/p_xiang_chu.png"));
            pieces_images[12] = ImageIO.read(new File("image/p_xiang_han.png"));
            pieces_images[13] = ImageIO.read(new File("image/p_zu_chu.png"));

            pieces_select_images[0] = ImageIO.read(new File("image/p_bing_han_1.png"));
            pieces_select_images[1] = ImageIO.read(new File("image/p_che_han_1.png"));
            pieces_select_images[2] = ImageIO.read(new File("image/p_jiang_chu_1.png"));
            pieces_select_images[3] = ImageIO.read(new File("image/p_ju_chu_1.png"));
            pieces_select_images[4] = ImageIO.read(new File("image/p_ma_chu_1.png"));
            pieces_select_images[5] = ImageIO.read(new File("image/p_ma_han_1.png"));
            pieces_select_images[6] = ImageIO.read(new File("image/p_pao_chu_1.png"));
            pieces_select_images[7] = ImageIO.read(new File("image/p_pao_han_1.png"));
            pieces_select_images[8] = ImageIO.read(new File("image/p_shi_chu_1.png"));
            pieces_select_images[9] = ImageIO.read(new File("image/p_shi_han_1.png"));
            pieces_select_images[10] = ImageIO.read(new File("image/p_shuai_han_1.png"));
            pieces_select_images[11] = ImageIO.read(new File("image/p_xiang_chu_1.png"));
            pieces_select_images[12] = ImageIO.read(new File("image/p_xiang_han_1.png"));
            pieces_select_images[13] = ImageIO.read(new File("image/p_zu_chu_1.png"));

            pieces_pre_images[0] = ImageIO.read(new File("image/p_bing_han_2.png"));
            pieces_pre_images[1] = ImageIO.read(new File("image/p_che_han_2.png"));
            pieces_pre_images[2] = ImageIO.read(new File("image/p_jiang_chu_2.png"));
            pieces_pre_images[3] = ImageIO.read(new File("image/p_ju_chu_2.png"));
            pieces_pre_images[4] = ImageIO.read(new File("image/p_ma_chu_2.png"));
            pieces_pre_images[5] = ImageIO.read(new File("image/p_ma_han_2.png"));
            pieces_pre_images[6] = ImageIO.read(new File("image/p_pao_chu_2.png"));
            pieces_pre_images[7] = ImageIO.read(new File("image/p_pao_han_2.png"));
            pieces_pre_images[8] = ImageIO.read(new File("image/p_shi_chu_2.png"));
            pieces_pre_images[9] = ImageIO.read(new File("image/p_shi_han_2.png"));
            pieces_pre_images[10] = ImageIO.read(new File("image/p_shuai_han_2.png"));
            pieces_pre_images[11] = ImageIO.read(new File("image/p_xiang_chu_2.png"));
            pieces_pre_images[12] = ImageIO.read(new File("image/p_xiang_han_2.png"));
            pieces_pre_images[13] = ImageIO.read(new File("image/p_zu_chu_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("读取图片异常！");
            System.exit(0);
        }
    }

    /**
     * 构造方法，构造棋子对象时，必须指定名字，id，位置
     *
     * @param name 名字
     * @param ID   id
     * @param pos  位置
     */
    public ChessPiece(String name, byte ID, Point pos) {
        this.name = name;
        this.ID = ID;
        this.position = pos;

        int subscript;
        switch (name) {
            case P_BING_HAN -> {
                subscript = 0;
                this.group = Judge.G_HAN;
            }
            case P_CHE_HAN -> {
                subscript = 1;
                this.group = Judge.G_HAN;
            }
            case P_JIANG_CHU -> {
                subscript = 2;
                this.group = Judge.G_CHU;
            }
            case P_JU_CHU -> {
                subscript = 3;
                this.group = Judge.G_CHU;
            }
            case P_MA_CHU -> {
                subscript = 4;
                this.group = Judge.G_CHU;
            }
            case P_MA_HAN -> {
                subscript = 5;
                this.group = Judge.G_HAN;
            }
            case P_PAO_CHU -> {
                subscript = 6;
                this.group = Judge.G_CHU;
            }
            case P_PAO_HAN -> {
                subscript = 7;
                this.group = Judge.G_HAN;
            }
            case P_SHI_CHU -> {
                subscript = 8;
                this.group = Judge.G_CHU;
            }
            case P_SHI_HAN -> {
                subscript = 9;
                this.group = Judge.G_HAN;
            }
            case P_SHUAI_HAN -> {
                subscript = 10;
                this.group = Judge.G_HAN;
            }
            case P_XIANG_CHU -> {
                subscript = 11;
                this.group = Judge.G_CHU;
            }
            case P_XIANG_HAN -> {
                subscript = 12;
                this.group = Judge.G_HAN;
            }
            case P_ZU_CHU -> {
                subscript = 13;
                this.group = Judge.G_CHU;
            }
            default -> {
                subscript = 14;
                this.group = Judge.G_NULL;
            }
        }
        RegisterImages(subscript);
    }

    /**
     * 获取该棋子图片
     *
     * @param n 该棋子id
     */
    private void RegisterImages(int n) {
        this.image[0] = pieces_images[n];
        this.image[1] = pieces_select_images[n];
        this.image[2] = pieces_pre_images[n];
    }

    @Override
    public void Move(int x, int y) {
        position.x = x;
        position.y = y;
    }

    @Override
    public BufferedImage[] GetImage() {
        return image;
    }

    @Override
    public String GetName() {
        return name;
    }

    @Override
    public byte GetID() {
        return ID;
    }

    @Override
    public byte GetGroup() {
        return group;
    }

    @Override
    public Point GetPosition() {
        return (Point) position.clone();
    }

    @Override
    public boolean IsAlive() {
        return alive;
    }

    @Override
    public void SetAlive(boolean b) {
        alive = b;
    }

    @Override
    public List<Point> GetCanGo() {
        return can_go;
    }

    /**
     * 计算该棋子当前可能可以去的位置
     */

    public void CountCanGo(Piece[][] map) {
        int poX = position.x;
        int poY = position.y;
        can_go.clear();
        switch (name) {
            case P_JIANG_CHU:
            case P_SHUAI_HAN: {
                if (group == Judge.GetHome()) {
                    if (poY + 1 <= 10 && map[poX][poY + 1].GetGroup() != group)
                        can_go.add(new Point(poX, poY + 1));
                    if (poY - 1 >= 8 && map[poX][poY - 1].GetGroup() != group)
                        can_go.add(new Point(poX, poY - 1));
                } else {
                    if (poY + 1 <= 3 && map[poX][poY + 1].GetGroup() != group)
                        can_go.add(new Point(poX, poY + 1));
                    if (poY - 1 >= 1 && map[poX][poY - 1].GetGroup() != group)
                        can_go.add(new Point(poX, poY - 1));
                }
                if (poX - 1 >= 4 && map[poX - 1][poY].GetGroup() != group)
                    can_go.add(new Point(poX - 1, poY));
                if (poX + 1 <= 6 && map[poX + 1][poY].GetGroup() != group)
                    can_go.add(new Point(poX + 1, poY));
            }
            break;
            case P_MA_CHU:
            case P_MA_HAN: {
                if (poX - 1 > 0 && poX - 1 < 10 && poY - 2 > 0 && poY - 2 < 11 && map[poX - 1][poY - 2].GetGroup() != group && map[poX][poY - 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX - 1, poY - 2));
                if (poX - 1 > 0 && poX - 1 < 10 && poY + 2 > 0 && poY + 2 < 11 && map[poX - 1][poY + 2].GetGroup() != group && map[poX][poY + 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX - 1, poY + 2));
                if (poX + 1 > 0 && poX + 1 < 10 && poY - 2 > 0 && poY - 2 < 11 && map[poX + 1][poY - 2].GetGroup() != group && map[poX][poY - 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX + 1, poY - 2));
                if (poX + 1 > 0 && poX + 1 < 10 && poY + 2 > 0 && poY + 2 < 11 && map[poX + 1][poY + 2].GetGroup() != group && map[poX][poY + 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX + 1, poY + 2));
                if (poX - 2 > 0 && poX - 2 < 10 && poY + 1 > 0 && poY + 1 < 11 && map[poX - 2][poY + 1].GetGroup() != group && map[poX - 1][poY].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX - 2, poY + 1));
                if (poX - 2 > 0 && poX - 2 < 10 && poY - 1 > 0 && poY - 1 < 11 && map[poX - 2][poY - 1].GetGroup() != group && map[poX - 1][poY].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX - 2, poY - 1));
                if (poX + 2 > 0 && poX + 2 < 10 && poY + 1 > 0 && poY + 1 < 11 && map[poX + 2][poY + 1].GetGroup() != group && map[poX + 1][poY].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX + 2, poY + 1));
                if (poX + 2 > 0 && poX + 2 < 10 && poY - 1 > 0 && poY - 1 < 11 && map[poX + 2][poY - 1].GetGroup() != group && map[poX + 1][poY].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX + 2, poY - 1));
            }
            break;
            case P_XIANG_CHU:
            case P_XIANG_HAN: {
                int up, down;
                if (group == Judge.GetHome()) {
                    up = 5;
                    down = 11;
                } else {
                    up = 0;
                    down = 6;
                }
                if (poX + 2 > 0 && poX + 2 < 10 && poY + 2 > up && poY + 2 < down && map[poX + 2][poY + 2].GetGroup() != group && map[poX + 1][poY + 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX + 2, poY + 2));
                if (poX + 2 > 0 && poX + 2 < 10 && poY - 2 > up && poY - 2 < down && map[poX + 2][poY - 2].GetGroup() != group && map[poX + 1][poY - 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX + 2, poY - 2));
                if (poX - 2 > 0 && poX - 2 < 10 && poY + 2 > up && poY + 2 < down && map[poX - 2][poY + 2].GetGroup() != group && map[poX - 1][poY + 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX - 2, poY + 2));
                if (poX - 2 > 0 && poX - 2 < 10 && poY - 2 > up && poY - 2 < down && map[poX - 2][poY - 2].GetGroup() != group && map[poX - 1][poY - 1].GetID() == NullPiece.ID)
                    can_go.add(new Point(poX - 2, poY - 2));
            }
            break;
            case P_SHI_CHU:
            case P_SHI_HAN: {
                int up, down;
                if (group == Judge.GetHome()) {
                    up = 7;
                    down = 11;
                } else {
                    up = 0;
                    down = 4;
                }
                if (poX + 1 > 3 && poX + 1 < 7 && poY + 1 > up && poY + 1 < down && map[poX + 1][poY + 1].GetGroup() != group)
                    can_go.add(new Point(poX + 1, poY + 1));
                if (poX + 1 > 3 && poX + 1 < 7 && poY - 1 > up && poY - 1 < down && map[poX + 1][poY - 1].GetGroup() != group)
                    can_go.add(new Point(poX + 1, poY - 1));
                if (poX - 1 > 3 && poX - 1 < 7 && poY + 1 > up && poY + 1 < down && map[poX - 1][poY + 1].GetGroup() != group)
                    can_go.add(new Point(poX - 1, poY + 1));
                if (poX - 1 > 3 && poX - 1 < 7 && poY - 1 > up && poY - 1 < down && map[poX - 1][poY - 1].GetGroup() != group)
                    can_go.add(new Point(poX - 1, poY - 1));
            }
            break;
            case P_BING_HAN:
            case P_ZU_CHU: {
                int up, down;
                if (group == Judge.GetHome()) {
                    if (poY - 1 > 0 && map[poX][poY - 1].GetGroup() != group)
                        can_go.add(new Point(poX, poY - 1));
                    up = 1;
                    down = 5;
                } else {
                    if (poY + 1 < 11 && map[poX][poY + 1].GetGroup() != group)
                        can_go.add(new Point(poX, poY + 1));
                    up = 6;
                    down = 10;
                }
                if (poY >= up && poY <= down && poX - 1 >= 1 && map[poX - 1][poY].GetGroup() != group)
                    can_go.add(new Point(poX - 1, poY));
                if (poY >= up && poY <= down && poX + 1 <= 9 && map[poX + 1][poY].GetGroup() != group)
                    can_go.add(new Point(poX + 1, poY));
            }
            break;
            case P_PAO_CHU:
            case P_PAO_HAN: {
                boolean obstacle = false;
                for (int y = 1; poY + y < 11; y++) {  //向下
                    if (!obstacle && map[poX][poY + y].GetID() != NullPiece.ID) {
                        obstacle = true;
                        continue;
                    }
                    if (!obstacle && map[poX][poY + y].GetID() == NullPiece.ID)
                        can_go.add(new Point(poX, poY + y));
                    if (obstacle && map[poX][poY + y].GetID() != NullPiece.ID && group != map[poX][poY + y].GetGroup()) {
                        can_go.add(new Point(poX, poY + y));
                        break;
                    }
                }

                obstacle = false;
                for (int y = -1; poY + y > 0; y--) {  //向上
                    if (!obstacle && map[poX][poY + y].GetID() != NullPiece.ID) {
                        obstacle = true;
                        continue;
                    }
                    if (!obstacle && map[poX][poY + y].GetID() == NullPiece.ID)
                        can_go.add(new Point(poX, poY + y));
                    if (obstacle && map[poX][poY + y].GetID() != NullPiece.ID && group != map[poX][poY + y].GetGroup()) {
                        can_go.add(new Point(poX, poY + y));
                        break;
                    }
                }

                obstacle = false;
                for (int x = -1; poX + x > 0; x--) {  //向左
                    if (!obstacle && map[poX + x][poY].GetID() != NullPiece.ID) {
                        obstacle = true;
                        continue;
                    }
                    if (!obstacle && map[poX + x][poY].GetID() == NullPiece.ID)
                        can_go.add(new Point(poX + x, poY));
                    if (obstacle && map[poX + x][poY].GetID() != NullPiece.ID && group != map[poX + x][poY].GetGroup()) {
                        can_go.add(new Point(poX + x, poY));
                        break;
                    }
                }

                obstacle = false;
                for (int x = 1; poX + x < 10; x++) {  //向右
                    if (!obstacle && map[poX + x][poY].GetID() != NullPiece.ID) {
                        obstacle = true;
                        continue;
                    }
                    if (!obstacle && map[poX + x][poY].GetID() == NullPiece.ID)
                        can_go.add(new Point(poX + x, poY));
                    if (obstacle && map[poX + x][poY].GetID() != NullPiece.ID && group != map[poX + x][poY].GetGroup()) {
                        can_go.add(new Point(poX + x, poY));
                        break;
                    }
                }
            }
            break;
            case P_CHE_HAN:
            case P_JU_CHU: {
                for (int y = 1; poY + y < 11; y++) {  //向下
                    if (map[poX][poY + y].GetID() != NullPiece.ID) {
                        if (map[poX][poY + y].GetGroup() != group)
                            can_go.add(new Point(poX, poY + y));
                        break;
                    } else
                        can_go.add(new Point(poX, poY + y));
                }

                for (int y = -1; poY + y > 0; y--) {  //向上
                    if (map[poX][poY + y].GetID() != NullPiece.ID) {
                        if (map[poX][poY + y].GetGroup() != group)
                            can_go.add(new Point(poX, poY + y));
                        break;
                    } else
                        can_go.add(new Point(poX, poY + y));
                }

                for (int x = -1; poX + x > 0; x--) {  //向左
                    if (map[poX + x][poY].GetID() != NullPiece.ID) {
                        if (map[poX + x][poY].GetGroup() != group)
                            can_go.add(new Point(poX + x, poY));
                        break;
                    } else
                        can_go.add(new Point(poX + x, poY));
                }

                for (int x = 1; poX + x < 10; x++) {  //向右
                    if (map[poX + x][poY].GetID() != NullPiece.ID) {
                        if (map[poX + x][poY].GetGroup() != group)
                            can_go.add(new Point(poX + x, poY));
                        break;
                    } else
                        can_go.add(new Point(poX + x, poY));
                }
            }
            break;
            default:
                break;
        }
    }
}
