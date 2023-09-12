package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class Server {
    static ArrayList<UserSocket> sockets=new ArrayList<UserSocket>();
    static HashSet<String> names=new HashSet<>();
    private static final String version ="0.0.3";

    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(5317);
        System.out.println("服务器准备就绪");
        while(true){
            try {
                Socket socket=ss.accept();
                System.out.println("响应请求");
                OutputStream out = socket.getOutputStream(); //返回所有用户名
                ObjectOutputStream ObjOut = new ObjectOutputStream(out);
                ObjOut.writeObject(version); //版本号
                ObjOut.writeObject(names); //所有用户名

                InputStream inputStream=socket.getInputStream();//字节输入流
                DataInputStream din=new DataInputStream(inputStream);//数据输入流
                String user=din.readUTF();//获取客户端的名称
                //user=user.substring(0,user.length()-5);//把客户端的名字获取，删除“加入聊天”
                UserSocket us=new UserSocket(socket,user);
                sockets.add(us);
                names.add(user);
                new ServerThread(us,sockets,user,names).start();
            } catch (IOException e) {
                System.out.println("昵称存在，断开连接");
            }
        }


    }
}
