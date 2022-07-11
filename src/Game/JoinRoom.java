package Game;

import javax.swing.*;
import java.awt.*;

public class JoinRoom {
    JFrame frame=new JFrame("加入棋局");
    JPanel panel=new JPanel();
    JTextField text=new JTextField();
    JLabel points=new JLabel("请输入要加入的主机名：");
    JButton confirm=new JButton("确认");
    void Init(StringBuffer add){
        confirm.addActionListener(e -> add.append(text.getText()));
        text.setPreferredSize(new Dimension(300,30));
        text.setFont(new Font("微软雅黑",Font.BOLD,15));
        points.setFont(new Font("",Font.PLAIN,15));
        panel.add(points);
        panel.add(text);
        panel.add(confirm);
        panel.setPreferredSize(new Dimension(400,100));
        frame.add(panel);
        frame.setBounds(300,300,10,10);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }

    public void Close(){
        frame.dispose();
    }
}
