package Board;

public class StepStack {
    /**
     * 纪录栈类，用于保存棋步
     */
    private final int max_step; //最大容量，不出意外为2
    private int top=0;  //栈顶指针
    private int size=0; //当前元素个数

    private final ChessBoard.Step[] steps;  //栈容器

    /**
     * 构造方法，初始化栈
     * @param max 栈的最大容量
     */
    public StepStack(int max){
        max_step=max;
        steps=new ChessBoard.Step[max_step];
    }

    /**
     * 入栈，若栈满，会退出最先进栈的元素
     * @param step 要入栈的元素
     */
    public void push(ChessBoard.Step step){
        steps[top]=step;
        top++;
        top%=max_step;
        size++;
        if(size>=max_step)
            size=max_step;
    }

    /**
     * 出栈，并返回出栈元素
     * @return  返回出栈元素
     */
    public ChessBoard.Step pop(){
        if(size==0)
            return null;
        top+=max_step-1;
        top%=max_step;
        size--;
        return steps[top];
    }

    /**
     * 获取栈顶元素但不出栈
     * @return 返回栈顶元素
     */
    public ChessBoard.Step peek(){
        if(size==0)
            return null;
        int tmp=top;
        tmp+=max_step-1;
        tmp%=max_step;
        return steps[tmp];
    }

    /**
     * 返回是否栈空
     * @return 返回是否栈空，空返回真
     */
    public boolean empty(){
        return size == 0;
    }

    /**
     * 返回栈中元素个数
     * @return 返回元素个数
     */
    public int size(){
        return size;
    }
}
