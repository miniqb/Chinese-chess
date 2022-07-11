package Judge;

import Board.ChessBoard;
import Piece.*;

import java.awt.*;
import java.util.List;

public class ThinkingJudge extends Judge{
    /**
     * 该类用于判断选择目标位置时，鼠标移动到的位置是否合法
     */
    private static ThinkingJudge me;
    public static ThinkingJudge Init() {
        if(me == null)
            me = new ThinkingJudge();
        return me;
    }

    private ThinkingJudge(){}
    private final Point move_last=new Point(-1,-1); //上一次moving的位置
    private boolean thinking_result =false;   //判断结果
    /**
     * 判断选择的目标位置是否合法
     * @return 真表示合法
     */
    @Override
    public boolean DoJudge() {
        Point move_now=ChessBoard.Init().moving;
        //如果这次moving和上次moving所指向棋盘的位置相同，就直接返回上次判断的结果
        if(move_now.equals(move_last))
            return thinking_result;
        Piece now=ChessBoard.Init().GetNowSelect();
        Piece aim=ChessBoard.Init().GetPiece(move_now.x,move_now.y);
        Piece head=Judge.GetNowPlayer().GetGroup()==Judge.G_HAN?ChessBoard.Init().GetShuai():ChessBoard.Init().GetJiang();
        thinking_result =(!now.GetPosition().equals(aim.GetPosition())&&!WillHeadMeeting(now.GetPosition(),move_now)&&CanGo(now,aim.GetPosition())&&!WillHeadEaten(head,now.GetPosition(),move_now));
        move_last.setLocation(move_now);
        is_right_position= thinking_result;
        return thinking_result;
    }

    /**
     * 获取两个位置间存活棋子个数
     * @param now 位置1
     * @param aim 位置2
     * @return 返回存活棋子个数
     */
    private int HaveTunnel(Point now,Point aim,Point add,Point ignore){
        if(add==null)
            add=new Point(-1,-1);
        if(ignore==null)
            ignore=new Point(-1,-1);
        ChessBoard board=ChessBoard.Init();
        int sum=0;
        switch (board.GetPiece(now.x,now.y).GetName()){
            case ChessPiece.P_PAO_CHU:
            case ChessPiece.P_PAO_HAN:
            case ChessPiece.P_JU_CHU:
            case ChessPiece.P_CHE_HAN:
                int max = Math.max(now.x, aim.x);
                int min = Math.min(now.x, aim.x);
                for (int x = min+1; x < max; x++) {
                    Piece piece=board.GetPiece(x,now.y);
                    if((piece.GetID()!=NullPiece.ID&&!piece.GetPosition().equals(ignore))||
                            (piece.GetID()==NullPiece.ID&&piece.GetPosition().equals(add))) {
                        System.out.println(piece.GetName());
                       // System.out.println(piece.GetPosition().y);
                       // System.out.println(piece.GetPosition().x);
                        System.out.println(ignore.x);
                        System.out.println(ignore.y);
                        sum++;
                    }
                }
                max = Math.max(now.y, aim.y);
                min = Math.min(now.y, aim.y);
                for (int y = min+1; y < max; y++) {
                    Piece piece=board.GetPiece(now.x,y);
                    if ((piece.GetID()!=NullPiece.ID&&!piece.GetPosition().equals(ignore))||
                            (piece.GetID()==NullPiece.ID&&piece.GetPosition().equals(add))) {
                        System.out.println(piece.GetName()+"PAO");
                        sum++;
                    }
                }
                break;
            case ChessPiece.P_XIANG_CHU:
            case ChessPiece.P_XIANG_HAN: {
                Piece piece=board.GetPiece((now.x + aim.x) / 2, (now.y + aim.y) / 2);
                if ((piece.GetID()!=NullPiece.ID&&!piece.GetPosition().equals(ignore))||
                        (piece.GetID()==NullPiece.ID&&piece.GetPosition().equals(add))) {
                    System.out.println(piece.GetName()+"XIANG");
                    sum++;
                }
            }
                break;
            case ChessPiece.P_MA_CHU:
            case ChessPiece.P_MA_HAN: {
                Piece piece=board.GetPiece((now.x + aim.x) / 2, now.y);
                if (Math.abs(now.x - aim.x) == 2 && (piece.GetID() != NullPiece.ID&&!piece.GetPosition().equals(ignore))||
                        (piece.GetID()==NullPiece.ID&&piece.GetPosition().equals(add))){
                    System.out.println(piece.GetName()+"MA");
                    sum++;
                }

                else if (Math.abs(now.y - aim.y) == 2 && board.GetPiece(now.x, (now.y + aim.y) / 2).GetID() != NullPiece.ID) {
                    sum++;
                    System.out.println(piece.GetName()+"MA");
                }
            }
                break;
            case ChessPiece.P_JIANG_CHU:
            case ChessPiece.P_SHUAI_HAN:
                Point head=
                        board.GetPiece(now.x,now.y).GetName().equals(ChessPiece.P_SHUAI_HAN)?
                                board.GetJiang().GetPosition():
                                board.GetShuai().GetPosition();
                if(head.x==aim.x){
                    max=Math.max(aim.y,head.y);
                    min=Math.min(aim.y,head.y);
                    for (int y = min+1; y < max; y++) {
                        if(board.GetPiece(aim.x,y).GetID()!=NullPiece.ID) {
                            sum++;
                            break;
                        }
                    }
                }
                else
                    sum++;
                break;
            default:
                break;
        }
        return sum;
    }



    private boolean CanGo(Piece now,Point aim) {
        List<Point> can_go = now.GetCanGo();
        for (Point p : can_go) {
            if (p.equals(aim))
                return true;
        }
        return false;
    }

    public static void Resetting(){
        ThinkingJudge.Init().move_last.setLocation(0,0);
        ThinkingJudge.Init().thinking_result =false;
    }
}


