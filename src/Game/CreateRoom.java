package Game;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CreateRoom {
    JFrame frame=new JFrame("创建棋局");
    JPanel panel=new JPanel();
    JLabel points=new JLabel("请要加入的玩家输入该主机名:");
    JLabel IP=new JLabel();
    String str_ip;
    void Init(){
        try {
            str_ip=InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        points.setFont(new Font("", Font.PLAIN,15));
        IP.setText(str_ip);
        IP.setFont(new Font("微软雅黑",Font.BOLD,30));
        panel.add(points);
        panel.add(IP);
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
