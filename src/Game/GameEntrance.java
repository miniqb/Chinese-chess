package Game;

public class GameEntrance {
    //游戏入口
    public static void main(String[] args) {
        Start();
    }
    static public void Start(){
        byte[] choose={0};
        StartFrame start=new StartFrame();
        start.Init(choose);
        while (choose[0]==0){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        start.Close();
        new ChessGame(choose[0]).StartGame();
    }
}
