package Game;

import Judge.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChoicePanel {

    public static byte RESTART=0;
    public static byte RETRACT=1;
    public static byte MENU=2;

    JPanel pa_choice=new JPanel();
    JLabel la1=new JLabel("吉");
    JLabel la2=new JLabel("吉");

    //“吉”
    JPanel La1=new JPanel();//“吉”
    JPanel La2=new JPanel();//“吉”

    JButton b_menu=new JButton("主菜单");
    JButton b_huiqi=new JButton("悔棋");
    JButton b_restart=new JButton("重新开始");

    public JPanel GetPanel(){
        return pa_choice;
    }

    public ChoicePanel(int height)
    {
        pa_choice.setLayout(null);
        pa_choice.setBackground(new Color(238,187,85));
        pa_choice.setPreferredSize(new Dimension(200,height));

        la1.setFont(new Font("楷体",Font.BOLD,50));

        la2.setFont(new Font("楷体",Font.BOLD,50));

        La1.setLayout(new FlowLayout(FlowLayout.CENTER));

        La1.add(la1);
        La1.setBackground(new Color(238,187,85));
        La1.setBounds(50,700,100,100);
        La1.setVisible(true);

        La2.setLayout(new FlowLayout(FlowLayout.CENTER));
        La2.add(la2);
        La2.setBackground(new Color(238,187,85));
        La2.setBounds(50,50,100,100);
        La2.setVisible(true);
        pa_choice.add(La1);
        pa_choice.add(La2);

        if(Judge.GetHome()==Judge.GetNowPlayer().GetGroup()){
            la1.setText("吉");
            la2.setText("  ");
        }
        else{
            la2.setText("吉");
            la1.setText("");
        }

        b_huiqi.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseMoved(MouseEvent e) {
                b_huiqi.setBackground(new Color(251,238,168));
            }
        });
        b_huiqi.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                b_huiqi.setBackground(new Color(238,187,85));
            }
        });

        Color color=new Color(238,187,85);
        Font font=new Font("楷体",Font.BOLD,20);
        b_restart.setBackground(color);
        b_restart.setFont(font);
        b_restart.setBorderPainted(false);

        b_huiqi.setBackground(color);
        b_huiqi.setFont(font);
        b_huiqi.setBorderPainted(false);

        b_menu.setBackground(color);
        b_menu.setFont(font);
        b_menu.setBorderPainted(false);

        b_restart.setBounds(20,360,150,30);
        b_huiqi.setBounds(20,390,150,30);
        b_menu.setBounds(20,420,150,30);

        AddMouseListener(b_menu);
        AddMouseListener(b_restart);
        AddMouseListener(b_huiqi);
        AddMouseMotionListener(b_menu);
        AddMouseMotionListener(b_restart);
        AddMouseMotionListener(b_huiqi);

        pa_choice.add(b_restart);
        pa_choice.add(b_huiqi);
        pa_choice.add(b_menu);
    }

    private void AddMouseMotionListener(JButton button){
        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                button.setBackground(new Color(251, 238, 168));
            }
        });
    }
    private void AddMouseListener(JButton button){
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(238,187,85));
            }
        });
    }

    public void AddListener(boolean[] choose){
        b_restart.addActionListener(e -> choose[RESTART]=true);
        b_huiqi.addActionListener(e -> choose[RETRACT]=true);
        b_menu.addActionListener(e -> choose[MENU]=true);
    }

    public void ChangeNowPoint(){
        if(Judge.GetHome()==Judge.GetNowPlayer().GetGroup()){
            la1.setText("吉");
            la2.setText("  ");
        }
        else{
            la2.setText("吉");
            la1.setText("");
        }
    }
}
