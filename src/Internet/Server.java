package Internet;

import Board.ChessBoard;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Internet{
    private ServerSocket server;
    private Socket client;
    private final int PORT=8886;
    public Server(){
        try {
            server=new ServerSocket(PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("服务器端正在尝试连接客户端。。。");
            client=server.accept();
            System.out.println("服务器端已连接到客户端！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Connect(){
        try {
            client=server.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Send(Point now,Point aim){
        byte[] sends={(byte) now.x, (byte) (ChessBoard.HIGH+1-now.y),(byte) aim.x,(byte) (ChessBoard.HIGH+1-aim.y)};
        try {
            OutputStream os=client.getOutputStream();
            System.out.println("服务器端正在发送数据。。。");
            os.write(sends);
            System.out.println("服务器已发送数据");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Point[] Receive(){
        Point[] points=new Point[2];
        try {
            InputStream is=client.getInputStream();
            byte[] receive=new byte[4];
            System.out.println("服务器端正在接收数据。。。");
            is.read(receive);
            points[0]=new Point(receive[0],receive[1]);
            points[1]=new Point(receive[2],receive[3]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return points;
    }
}
