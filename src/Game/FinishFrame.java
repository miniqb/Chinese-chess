package Game;

import Judge.Judge;

import javax.swing.*;
import java.awt.*;

public class FinishFrame {
    public static final byte FC_AGAIN=1;
    public static final byte FC_EXIT=2;
    private JFrame finish_frame=new JFrame();
    private JButton b_again=new JButton("再来一局");
    private JButton b_exit=new JButton("  退出  ");
    private JLabel l_han=new JLabel("汉方胜利");
    private JLabel l_chu=new JLabel("楚方胜利");
    private Box bo=  Box.createHorizontalBox();
    private JPanel above_frame=new JPanel();
    private JPanel below_frame=new JPanel();
    public void Init(final byte[] choose)
    {
        finish_frame.setLayout(new BorderLayout());
        above_frame.setLayout(new FlowLayout());
        below_frame.setLayout(new FlowLayout());
        bo.add(Box.createHorizontalGlue());
        finish_frame.setBounds(600,400,200,200);
        finish_frame.setBackground(new Color(238,187,85));
        l_han.setBounds(0,0,200,100);
        l_chu.setBounds(0,0,200,100);
        l_chu.setFont(new Font("楷体",Font.BOLD,20));
        l_han.setFont(new Font("楷体",Font.BOLD,20));
        b_again.addActionListener(e -> choose[0]=FC_AGAIN);
        b_exit.addActionListener(e -> choose[0]=FC_EXIT);
        bo.add(b_again);
        bo.add(b_exit);
        below_frame.add(bo);
        finish_frame.add(above_frame,BorderLayout.NORTH);
        finish_frame.add(below_frame,BorderLayout.SOUTH);
        if(Judge.getWinner().GetGroup()==Judge.G_HAN)
        {
            above_frame.add(l_han);
        }
        else
        {
            above_frame.add(l_chu);
        }
        finish_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        finish_frame.setVisible(true);
    }

    public void Close(){
        finish_frame.dispose();
    }
}
