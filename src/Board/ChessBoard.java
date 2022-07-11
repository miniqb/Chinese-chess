package Board;

import Game.ChessGame;
import Judge.*;
import Piece.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChessBoard {
    /**
     * 棋盘类，封装了棋盘上的各种信息，是单例类
     */
    //单例对象
    private static ChessBoard me;

    //棋盘上所有棋子的初始位置
    private static final int[][] positions ={
          {0,0}, {1,10}, {9,10}, {2,10}, {8,10}, {3,10}, {7,10},
          {4,10}, {6,10}, {5,10}, {2,8}, {8,8}, {1,7}, {3,7}, {5,7},
          {7,7}, {9,7}, {1,1}, {9,1}, {2,1}, {8,1}, {3,1}, {7,1}, {4,1},
          {6,1}, {5,1}, {2,3}, {8,3}, {1,4}, {3,4}, {5,4}, {7,4}, {9,4},
    };
    //棋盘宽度
    public final static byte WIDTH = 9;
    //棋盘高度
    public final static byte HIGH = 10;

    //一个空子常量
    public final static Piece Null = NullPiece.GetNull(0,0);

    //棋盘数组
    private final Piece[][] pieces_map;
    public static final int ALL_SUM=33;
    //棋子容器
    private final Piece[] pieces_all;

    //记录之前走的棋步
    private final StepStack record= new StepStack(2);
    //
    public final Point moving=new Point(0,0);

    //当前选中棋子
    private Piece now_select;
    //当前选中棋子的目标位置
    private Piece aim_select;

    //棋盘图片
    private BufferedImage board_image;

    private boolean information_change = false;

    //构造方法，对一些数据进行初始化
    private ChessBoard(){
        //读入棋盘图片
        try {
            board_image= ImageIO.read(new File("image/b_chessboard.png"));
        } catch (IOException e) {
            System.out.println("图片加载错误！");
            e.printStackTrace();
        }

        //构造棋盘数组和棋子容器
        pieces_map = new Piece[WIDTH+1][];
        for (int i = 0; i <= WIDTH; i++) {
            pieces_map[i] = new Piece[HIGH+1];
        }
        pieces_all = new Piece[ALL_SUM];

        //创造棋子
        CreatePieces();
        //按初始位置摆好棋子
        InitializePieces();

        UpdatePiecesCanGo();

        now_select=Null;
        aim_select=Null;
    }

    /**
     * 入口方法，ChessBoard类的对象只通过该方法获取，每次获取的对象都是同一个
     * @return 返回一个ChessBoard对象
     */
    public static ChessBoard Init(){
        if(me==null) {
            if (Judge.GetHome() == Judge.G_NULL) {
                System.out.println("玩家尚未分配阵营！");
                System.exit(0);
            }
            me = new ChessBoard();
        }
        return me;
    }

    /**
     * 构造所有非空棋子，存放在pieces_all中，每个棋子的id即为它在pieces——all中的索引值
     */
    private void CreatePieces(){//创建所有棋子对象
        pieces_all[0]=Null; //将一个空子放在0索引处
        pieces_all[1]=new ChessPiece(ChessPiece.P_CHE_HAN,(byte) 1,new Point(1,10));
        pieces_all[2]=new ChessPiece(ChessPiece.P_CHE_HAN,(byte) 2,new Point(9,10));
        pieces_all[3]=new ChessPiece(ChessPiece.P_MA_HAN,(byte) 3,new Point(2,10));
        pieces_all[4]=new ChessPiece(ChessPiece.P_MA_HAN,(byte) 4,new Point(8,10));
        pieces_all[5]=new ChessPiece(ChessPiece.P_XIANG_HAN,(byte) 5,new Point(3,10));
        pieces_all[6]=new ChessPiece(ChessPiece.P_XIANG_HAN,(byte) 6,new Point(7,10));
        pieces_all[7]=new ChessPiece(ChessPiece.P_SHI_HAN,(byte) 7,new Point(4,10));
        pieces_all[8]=new ChessPiece(ChessPiece.P_SHI_HAN,(byte) 8,new Point(6,10));
        pieces_all[9]=new ChessPiece(ChessPiece.P_SHUAI_HAN,(byte) 9,new Point(5,10));
        pieces_all[10]=new ChessPiece(ChessPiece.P_PAO_HAN,(byte) 10,new Point(2,8));
        pieces_all[11]=new ChessPiece(ChessPiece.P_PAO_HAN,(byte) 11,new Point(8,8));
        pieces_all[12]=new ChessPiece(ChessPiece.P_BING_HAN,(byte) 12,new Point(1,7));
        pieces_all[13]=new ChessPiece(ChessPiece.P_BING_HAN,(byte) 13,new Point(3,7));
        pieces_all[14]=new ChessPiece(ChessPiece.P_BING_HAN,(byte) 14,new Point(5,7));
        pieces_all[15]=new ChessPiece(ChessPiece.P_BING_HAN,(byte) 15,new Point(7,7));
        pieces_all[16]=new ChessPiece(ChessPiece.P_BING_HAN,(byte) 16,new Point(9,7));

        pieces_all[17]=new ChessPiece(ChessPiece.P_JU_CHU,(byte) 17,new Point(1,1));
        pieces_all[18]=new ChessPiece(ChessPiece.P_JU_CHU,(byte) 18,new Point(9,1));
        pieces_all[19]=new ChessPiece(ChessPiece.P_MA_CHU,(byte) 19,new Point(2,1));
        pieces_all[20]=new ChessPiece(ChessPiece.P_MA_CHU,(byte) 20,new Point(8,1));
        pieces_all[21]=new ChessPiece(ChessPiece.P_XIANG_CHU,(byte) 21,new Point(3,1));
        pieces_all[22]=new ChessPiece(ChessPiece.P_XIANG_CHU,(byte) 22,new Point(7,1));
        pieces_all[23]=new ChessPiece(ChessPiece.P_SHI_CHU,(byte) 23,new Point(4,1));
        pieces_all[24]=new ChessPiece(ChessPiece.P_SHI_CHU,(byte) 24,new Point(6,1));
        pieces_all[25]=new ChessPiece(ChessPiece.P_JIANG_CHU,(byte) 25,new Point(5,1));
        pieces_all[26]=new ChessPiece(ChessPiece.P_PAO_CHU,(byte) 26,new Point(2,3));
        pieces_all[27]=new ChessPiece(ChessPiece.P_PAO_CHU,(byte) 27,new Point(8,3));
        pieces_all[28]=new ChessPiece(ChessPiece.P_ZU_CHU,(byte) 28,new Point(1,4));
        pieces_all[29]=new ChessPiece(ChessPiece.P_ZU_CHU,(byte) 29,new Point(3,4));
        pieces_all[30]=new ChessPiece(ChessPiece.P_ZU_CHU,(byte) 30,new Point(5,4));
        pieces_all[31]=new ChessPiece(ChessPiece.P_ZU_CHU,(byte) 31,new Point(7,4));
        pieces_all[32]=new ChessPiece(ChessPiece.P_ZU_CHU,(byte) 32,new Point(9,4));
    }

    /**
     * 按照主场玩家所属阵营将所有棋子放到棋盘上合适的位置，这些位置是棋子的初始位置，棋盘上本没有子的地方会被摆上空子
     */
    public void InitializePieces(){
            int constantX = Judge.GetHome() == Judge.G_CHU ? WIDTH + 1 : 0;
            int constantY = Judge.GetHome() == Judge.G_CHU ? HIGH + 1 : 0;
            for (int i = 0; i < positions.length; i++) {
                pieces_all[i].Move(Math.abs(constantX - positions[i][0]), Math.abs(constantY - positions[i][1]));
            }
            for (int i = 0; i <= WIDTH; i++) { //在棋盘上摆上”空子“
                for (int j = 0; j <= HIGH; j++) {
                    pieces_map[i][j] = NullPiece.GetNull(i,j);
                }
            }
            for (int i = 1; i < pieces_all.length; i++) { //在棋盘上摆子
                Point temp = pieces_all[i].GetPosition();
                pieces_map[temp.x][temp.y] = pieces_all[i];
                pieces_all[i].SetAlive(true);
            }
            information_change=true;
        }

    /**
     * 通过棋盘坐标获取棋盘上相应位置的棋子
     * @param x 棋盘上的x坐标
     * @param y 棋盘上的y坐标
     * @return 返回棋盘上相应位置的棋子
     */
    public Piece GetPiece(int x,int y){
        return pieces_map[x][y];
    }

    /**
     * 获取棋盘图片
     * @return 返回一个保存着棋盘图片的BufferedImage对象
     */
    public BufferedImage GetBoardImage(){
        return board_image;
    }

    /**
     * 获取所有棋子
     * @return 返回棋子容器
     */
    public Piece[] GetAllPieces(){
        return pieces_all;
    }

    /**
     * 获取当前选中棋子
     * @return 返回当前选中棋子
     */
    public Piece GetNowSelect(){
        return now_select;
    }

    /**
     * 获取目标位置棋子
     * @return 返回目标位置棋子
     */
    public Piece GetAimSelect(){
        return aim_select;
    }

    /**
     * 设置当前选中棋子
     * @param x 要设置棋子的x坐标
     * @param y 要设置棋子的y坐标
     */
    public void SetNowSelect(int x,int y){
        now_select=pieces_map[x][y];
    }
    public void SetNowSelect(Piece piece){
        now_select=piece;
    }

    /**
     * 设置目标位置棋子
     * @param x 要设置棋子的x坐标
     * @param y 要设置棋子的y坐标
     */
    public void SetAimSelect(int x,int y){
        aim_select=pieces_map[x][y];
    }
    public void SetAimSelect(Piece piece){
        aim_select=piece;
    }

    /**
     * 根据当前选中棋子(now_pieces)和目标位置棋子(aim_pieces)来移动棋子，将当前选中棋子移动到目标位置处，同时会记录该步
     */
    public void MovePiece(){
        //记录该步
        if(ChessGame.mod==ChessGame.HERE)
            record.push(new Step(now_select.GetID(), aim_select.GetID(), now_select.GetPosition(), aim_select.GetPosition()));

        //移动棋子
        aim_select.SetAlive(false); //令目标位置棋子死亡
        Point pos_aim=aim_select.GetPosition();
        Point pos_now=now_select.GetPosition();
        pieces_map[pos_aim.x][pos_aim.y]=now_select; //在棋盘上的目标位置放置选中棋子
        pieces_map[pos_now.x][pos_now.y]=NullPiece.GetNull(pos_now.x,pos_now.y); //将原来选中棋子的位置放置空子
        now_select.Move(pos_aim.x,pos_aim.y);   //将选中棋子的位置改为目标位置
        information_change=true;
    }

    public void MovePiece(Point now,Point aim){
        Piece now_select=pieces_map[now.x][now.y];
        Piece aim_select=pieces_map[aim.x][aim.y];
        //记录该步
        record.push(new Step(now_select.GetID(), aim_select.GetID(), now_select.GetPosition(), aim_select.GetPosition()));

        //移动棋子
        aim_select.SetAlive(false); //令目标位置棋子死亡
        Point pos_aim=aim_select.GetPosition();
        Point pos_now=now_select.GetPosition();
        pieces_map[pos_aim.x][pos_aim.y]=now_select; //在棋盘上的目标位置放置选中棋子
        pieces_map[pos_now.x][pos_now.y]=NullPiece.GetNull(pos_now.x,pos_now.y); //将原来选中棋子的位置放置空子
        now_select.Move(pos_aim.x,pos_aim.y);   //将选中棋子的位置改为目标位置
        information_change=true;
    }

    /**
     * 执行悔棋操作，退回上一步（双方同时退回一步）
     */
    public void Retract(){
        if(record.size()>=2&&!record.empty()){
            for (int i = 0; i < 2; i++) {
                Step step = record.pop();   //将上一步弹出记录栈
                pieces_all[step.eaten].SetAlive(true);  //将被吃的子复活

                //还原上一步棋盘中目标位置的棋子和选中位置的棋子
                pieces_map[step.end.x][step.end.y] = step.eaten == Null.GetID() ? NullPiece.GetNull(step.end.x, step.end.y) : pieces_all[step.eaten];
                pieces_map[step.start.x][step.start.y] = pieces_all[step.piece];

                //将上一步选中棋子位置还原
                pieces_all[step.piece].Move(step.start.x, step.start.y);
                information_change=true;
            }

        }
    }

    /**
     * 重置当前选中棋子和目标位置棋子为空子
     */
    public void ResetSelect(){
        now_select=Null;
        aim_select=Null;
    }

    /**
     * 获取“将”
     * @return 返回“将”子
     */
    public Piece GetJiang(){
        return pieces_all[25];
    }

    /**
     * 获取“帅”
     * @return 返回“帅”子
     */
    public Piece GetShuai(){
        return pieces_all[9];
    }

    public void UpdatePiecesCanGo(){
        if(information_change) {
            for (Piece piece : pieces_all) {
                if (piece.IsAlive())
                    piece.CountCanGo(pieces_map);
            }
            information_change=false;
        }
    }

    public void ClearStack(){
        while (!record.empty())
            record.pop();
    }

    public static class Step{
        /**
         * 用于包装棋步信息的类
         */
        byte piece; //该步选中棋子id
        byte eaten; //该步目标位置棋子id
        Point start; //该步选中棋子位置
        Point end;  //该步目标位置
        Step(byte piece,byte eaten,Point start,Point end){
            this.piece=piece;
            this.eaten=eaten;
            this.start=start;
            this.end=end;
        }
    }
}
