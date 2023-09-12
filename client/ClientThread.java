package client;

import comm.Msg;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread{
    private Socket socket;
    private HTextPane ChatTextPane;
    private MainWindow MW;
    public ClientThread(Socket socket,MainWindow MW){
        this.socket=socket;
        this.MW = MW;
        this.ChatTextPane=MW.getChatTextPane();
    }
    @Override
    public void run() {
        try {
            while (true){
                InputStream inputStream = socket.getInputStream();
                //DataInputStream din=new DataInputStream(inputStream);//数据输入流
                ObjectInputStream ObjIn = new ObjectInputStream(inputStream);
                Msg msg = (Msg) ObjIn.readObject();
                if (msg.getType()==0){
                    String msgText=(String) msg.getObj();
                    String fPath = "<img src=\""+getClass().getResource("/res/emoji").toString()+"/$1.png\"/>";
                    msgText=msgText.replaceAll("#\\(([^)]+)\\)", fPath);
                    ChatTextPane.addText(msgText);
                }else if (msg.getType()==1){
                    MW.getStaLabel().setText("当前在线："+(String) msg.getObj()+"人");
                    continue;
                }
//                String text=din.readUTF();//获取输入的数据
//                ChatTextArea.append(text);//添加到文本区域
//                String newText=Util.checkUpdNum(text);
//                if (!text.equals(newText)){
//                    MW.getStaLabel().setText("当前在线："+newText+"人");
//                    continue;
//                }

                //System.out.println(ChatTextPane.getText());
            }
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("断开连接");
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            System.out.println("断开连接");
        }

    }


}
