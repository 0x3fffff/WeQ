package server;

import comm.Msg;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;

public class ServerThread extends Thread{
    Socket socket;
    UserSocket us;
    ArrayList<UserSocket> sockets;
    HashSet<String> names;
    String user;
    public ServerThread(UserSocket us, ArrayList<UserSocket> sockets,String user,HashSet<String> names) {
        this.socket=us.getSocket();
        this.sockets=sockets;
        this.user=user;
        this.us=us;
        this.names=names;
        sendMsg(0,"<center><i>"+user+"</i>"+" 加入聊天"+"</center>");
        sendMsg2(1,""+sockets.size());
    }

    public void run(){
        while (true){
            try {
                InputStream inputStream = socket.getInputStream();
                DataInputStream din=new DataInputStream(inputStream);//数据输入流
                ObjectInputStream ObjIn = new ObjectInputStream(inputStream);
                Msg msg = (Msg) ObjIn.readObject();
                //String text=din.readUTF();//获取输入的信息
                if (msg.getType()==0){
                    String text=(String) msg.getObj();
                    System.out.println(user+":"+text);
                    sendMsg(0,"<p><i>"+user+"</i> : "+text+"</p>");
                }
            } catch (IOException | ClassNotFoundException e) {
                //e.printStackTrace();
                sockets.remove(us);
                names.remove(user);
                System.out.println(user+" 退出聊天室");
                System.out.println(sockets);
                System.out.println(names);
                sendMsg(0,"<center><i>"+user+"</i>"+" 退出聊天"+"</center>");
                sendMsg2(1,""+sockets.size());
                break;
            }
        }
    }

    public void sendMsg(int type,String msgText){ //给除了自己发信息
        try{
            for (UserSocket userSocket : sockets) {
                Socket sc = userSocket.getSocket();//获取每个用户的socket
                if (sc != socket) {//判断 不需要自己发信息给自己
                    OutputStream outputStream = sc.getOutputStream();//字节输出流
                    //DataOutputStream dout = new DataOutputStream(outputStream);//数据输出流
                    ObjectOutputStream ObjOut = new ObjectOutputStream(outputStream);
                    ObjOut.writeObject(new Msg(type,msgText));
                    //dout.writeUTF(msgText);//把当前客户端的名字和信息发送给除自己以外的其他客户端
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void sendMsg2(int type,String msgText){ //给所有人发信息
        try{
            for (UserSocket userSocket : sockets) {
                Socket sc = userSocket.getSocket();//获取每个用户的socket
                OutputStream outputStream = sc.getOutputStream();//字节输出流
                ObjectOutputStream ObjOut = new ObjectOutputStream(outputStream);
                //DataOutputStream dout = new DataOutputStream(outputStream);//数据输出流
                ObjOut.writeObject(new Msg(type,msgText));
                ///dout.writeUTF(msg);//把当前客户端的名字和信息发送给所有客户端
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
