package Judge;

import Game.ChessGame;

public class ChoiceJudge extends Judge{
    /**
     * 单例类，对选择做出合法性判断同时执行一些操作
     */

    private static ChoiceJudge me;//单例对象

    /**
     * 获取单例对象
     * @return 返回单例对象
     */
    public static ChoiceJudge Init() {
        if(me == null)
            me = new ChoiceJudge();
        return me;
    }

    private ChoiceJudge(){}
    /**
     * 对选择做判断
     */
    @Override
    public boolean DoJudge() {
        boolean result=true;    //判断结果
        switch (player_now.GetChoice()){
            case C_GO:
                if(!other_end||!is_right_position)  //如果选中棋子和目标位置棋子相同或者目标位置棋子不合法
                    result=false;
                else if(ChessGame.mod==ChessGame.HERE){  //若合法，轮到下名玩家
                    player_now=player_now==player_1?player_2:player_1;
                }
                break;
            case C_RETRACT:
                break;
        }
        if (result)
            is_right_position=false;
        return result;//返回结果
    }

    public void OtherEnd(boolean oe){
        other_end=oe;
    }
}
