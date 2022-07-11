package Internet;

import Board.ChessBoard;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements Internet{
    private Socket client;
    public Client(String add,int port) {
        try {
            System.out.println("客户器端正在尝试连接服务器端。。。");
            client = new Socket(add,port);
            System.out.println(InetAddress.getLocalHost().getHostName());
            System.out.println("客户端已成功连接到服务器端");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("未知的主机名");
        }
    }

    public void Send(Point now,Point aim){
        byte[] send={(byte) now.x, (byte) (ChessBoard.HIGH+1-now.y),(byte) aim.x,(byte) (ChessBoard.HIGH+1-aim.y)};
        try {
            OutputStream os;
            os = client.getOutputStream();
            System.out.println("客户端正在发送数据。。。");
            os.write(send);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Point[] Receive(){
        Point[] points=new Point[2];
        try {
            System.out.println("客户端正在接收数据。。。");
            InputStream is=client.getInputStream();
            byte[] receive=new byte[1024];
            System.out.println("客户端正在接收数据。。。");
            is.read(receive);
            System.out.println("已接受数据");
            points[0]=new Point(receive[0],receive[1]);
            points[1]=new Point(receive[2],receive[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}