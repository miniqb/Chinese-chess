package Player;

public class ChessPlayer implements Player{
    /**
     * 玩家类
     */

    private byte group; //玩家阵营
    private byte choice;    //玩家的选择

    public ChessPlayer(){ }
    public byte GetGroup() {
        return group;
    }

    public byte GetChoice() {
        return choice;
    }

    /**
     * 设置玩家选择
     * @param choice 选择
     */
    public void MakeChoice(byte choice){
        this.choice=choice;
    }

    /**
     * 设置玩家阵营
     * @param group 阵营
     */
    public void SetGroup(byte group) {
        this.group = group;
    }

}
