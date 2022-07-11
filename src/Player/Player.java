package Player;

public interface Player {
    /**
     * 获取该玩家阵营
     * @return 返回玩家阵营
     */
    byte GetGroup();

    /**
     * 获取该玩家选择
     * @return 返回玩家选择
     */
    byte GetChoice();
}
