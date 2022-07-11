package Game;

import javax.swing.*;
import java.awt.*;

public class StartFrame {
    JFrame frame=new JFrame("中国象棋");
    JPanel panel=new JPanel();
    JButton one=new JButton("双人游戏");
    JButton two=new JButton("本地联机");
    JLabel point=new JLabel("中国象棋");
    StartOrJoinFrame dialog=new StartOrJoinFrame(frame);
    public StartFrame(){
        panel.setPreferredSize(new Dimension(300,400));
        panel.setLayout(new FlowLayout(FlowLayout.CENTER,10,60));
        point.setFont(new Font("楷体",Font.BOLD,50));
        Dimension size=new Dimension(150,30);
        one.setPreferredSize(size);
        two.setPreferredSize(size);
        panel.setBackground(new Color(238,187,85));
        panel.add(point);
        panel.add(one);
        panel.add(two);
        frame.setBounds(600,200,0,0);
        frame.add(panel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void Init(final byte[] choose){
        frame.setVisible(true);
        one.addActionListener(e -> choose[0]=1);
        two.addActionListener(e -> dialog.Init(choose));
    }
    public void Close(){
        frame.dispose();
    }

    static class StartOrJoinFrame {
        JDialog dialog;
        JPanel panel=new JPanel();
        JButton create=new JButton("创建棋局");
        JButton join=new JButton("加入棋局");
        JLabel point=new JLabel("您可以创建或加入一个棋局!");
        public StartOrJoinFrame(JFrame father){
            dialog=new JDialog(father,"选择游戏",true);
            point.setFont(new Font("黑体",Font.PLAIN,15));
            panel.add(point);
            panel.add(create);
            panel.add(join);
            panel.setLayout(new FlowLayout(FlowLayout.CENTER,20,30));
            panel.setBackground(new Color(238,187,85));
            panel.setPreferredSize(new Dimension(280,130));
            dialog.add(panel);
            dialog.setBounds(610,370,0,0);
            dialog.pack();
            dialog.setResizable(false);
        }

        public void Init(byte[] choose){
            create.addActionListener(e -> choose[0]=ChessGame.THERE_F);
            join.addActionListener(e -> choose[0]=ChessGame.THERE_S);
            dialog.setVisible(true);
        }


    }
}
