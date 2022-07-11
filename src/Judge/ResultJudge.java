package Judge;

import Board.ChessBoard;
import Piece.*;

import java.awt.*;
import java.util.List;

public class ResultJudge extends Judge{

    /**
     * 单例类，判断游戏结果
     */

    private static ResultJudge me;//单例对象
    public static ResultJudge Init() {
        if(me == null)
            me = new ResultJudge();
        return me;
    }

    private ResultJudge(){}

    /**
     * 判断游戏是否结束，同时设置赢家和输家
     * @return “真”为结束
     */
    @Override
    public boolean DoJudge() {
        if(winner!=null)
            return true;
        byte win_group=WhoWin();
        if(win_group!=G_NULL){
            winner=player_1.GetGroup()==win_group?player_1:player_2;
            loser=winner==player_1?player_2:player_1;
            return true;
        }
        return false;
    }


    private byte WhoWin(){
        ChessBoard board=ChessBoard.Init();
        if(!board.GetJiang().IsAlive())
            return Judge.G_HAN;
        if(!board.GetShuai().IsAlive())
            return Judge.G_CHU;
        Piece[] pieces=board.GetAllPieces();
        boolean can_go_chu=false;
        for (Piece piece:pieces) {
            if(piece.IsAlive()&&piece.GetGroup()==G_CHU) {
                List<Point> points=piece.GetCanGo();
                for (Point point:points) {
                    if(!WillHeadMeeting(piece.GetPosition(),point)&&!WillHeadEaten(board.GetJiang(),piece.GetPosition(),point)) {
                        can_go_chu = true;
                        break;
                    }
                }
            }
        }
        if(!can_go_chu)
            return G_HAN;
        for (Piece piece:pieces) {
            if(piece.IsAlive()&&piece.GetGroup()==G_HAN) {
                List<Point> points=piece.GetCanGo();
                for (Point point:points) {
                    if(!WillHeadMeeting(piece.GetPosition(),point)&&!WillHeadEaten(board.GetShuai(),piece.GetPosition(),point)) {
                        return G_NULL;
                    }
                }
            }
        }
        return G_CHU;

    }
}
