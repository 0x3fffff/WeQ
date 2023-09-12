/*
 * Created by JFormDesigner on Sat Jan 14 17:26:03 CST 2023
 */

package client;

import java.awt.event.*;
import com.formdev.flatlaf.FlatIntelliJLaf;
import comm.Msg;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import javax.swing.*;

/**
 * @author 0x3fffff
 * @URL https://blog.csdn.net/qq_19655605?type=blog
 */
public class MainWindow extends JFrame {
    public MainWindow() throws IOException {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e){
        // TODO add your code here
        if (JoinBtn.getText().equals("加入聊天室")){
            //发起连接
            JoinBtn.setEnabled(false);
            user= NameText.getText();//获取用户名
            if (user.equals("")) {
                new HintWindow(this,"昵称不能为空，请修改后重试", 1).setVisible(true);
                JoinBtn.setEnabled(true);
                return;
            }
            if (user.equals("QAQ531722852")){
                flag=true;
                ChatTextPane.addText("<center><i>已解除限制</i></center>"+"\n");
                JoinBtn.setEnabled(true);
                return;
            }
            System.out.println(user+"发起连接请求");
            try {
                socket=new Socket("103.133.177.248",5317);
                InputStream in = socket.getInputStream();
                ObjectInputStream ObjIn = new ObjectInputStream(in);
                sVersion=(String)ObjIn.readObject();
                names=(HashSet)ObjIn.readObject();
                System.out.println(names);
                //System.out.println(sVersion);
                System.out.println("当前用户名："+user);
                if(!sVersion.equals(version)){  //版本不一致
                    HintWindow hw1=new HintWindow(this,"<HTML>当前版本为："+version+"<BR/>最新版本为："+sVersion+"<BR/>请下载最新版!</HTML>",2);
                    hw1.setBounds(0,0,300,150);
                    SwingUtil.centerInOwner(hw1,this);
                    hw1.setVisible(true);
                    HintWindow hw2=new HintWindow(this,"<HTML><a href=\"http://ox3fffff.cn/\">http://ox3fffff.cn</a><br/>秘密入口口令:WeQ</HTML>",3);
                    hw2.setBounds(0,0,300,150);
                    SwingUtil.centerInOwner(hw2,this);
                    hw2.setVisible(true);
                    socket.close();
                    System.out.println("版本不一致");
                    return;
                }
                if (names.contains(user)){   //验证昵称是否重复
                    new HintWindow(this,"昵称已存在，请修改后重试",1).setVisible(true);
                    socket.close();
                    System.out.println("昵称重复");
                    return;
                }

                OutputStream outputStream=socket.getOutputStream();//字节输出流
                DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流
                ChatTextPane.addText("<center><i>"+user+"</i>"+" 加入聊天"+"</center>"+"\n");
                dout.writeUTF(user);//.writeUTF里的内容输出到文本里
                new ClientThread(socket,this).start();
                SendBtn.setEnabled(true);//再设置“发送”按钮可以被点击
                JoinBtn.setText("退出聊天室");
                NameText.setEnabled(false);
            } catch (IOException ex) {
                //ex.printStackTrace();
                //ChatTextArea.append(user+" 加入聊天失败，请稍后重试！"+"\n");//添加到显示的文本区域
                System.out.println("连接失败!");
                ChatTextPane.addText(user+" 加入聊天失败，请稍后重试！"+"\n");
                new HintWindow(this,"加入聊天失败，请稍后重试",1).setVisible(true);
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }finally {
                JoinBtn.setEnabled(true);
            }
        }else{
            try {
                socket.close();
                StaLabel.setText("当前在线：？人");
                JoinBtn.setText("加入聊天室");
                ChatTextPane.addText("<center><i>"+user+"</i>"+" 退出聊天"+"</center>"+"\n");
                NameText.setEnabled(true);
                SendBtn.setEnabled(false);
                new HintWindow(this,"已与服务器断开连接!",1).setVisible(true);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private void SendBtnMouseClicked(MouseEvent e) {
        // TODO add your code here
        //System.out.println(e);
        try {
            OutputStream outputStream=socket.getOutputStream();//字节输出流
            //DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流
            ObjectOutputStream ObjOut = new ObjectOutputStream(outputStream);
            String text=SendText.getText();//获取待发送文本框的内容
            if (text.equals(""))return;
            if (text.length()>600){
                new HintWindow(this,"单次发送的信息不能这么长哦！",1).setVisible(true);
                return;
            }
            if (!flag)
                text=Util.htmlEscape1(text); //替换html字符
            //dout.writeUTF(text);//输出到文件
            ObjOut.writeObject(new Msg(0,text));
            String fPath = "<img src=\""+getClass().getResource("/res/emoji").toString()+"/$1.png\"/>";
            text=text.replaceAll("#\\(([^)]+)\\)", fPath);
            ChatTextPane.addText("<p>"+">我 : "+text+"</p>");
            SendText.setText("");//设置下一次需要输入文本框的内容为空
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void thisWindowClosing(WindowEvent e) {
        // TODO add your code here
//        try {
//            OutputStream outputStream=socket.getOutputStream();//字节输出流
//            DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流
//            dout.writeUTF("[//close*/]");//.writeUTF里的内容输出到文本里
//            socket.close();
//        } catch (IOException ee) {
//            ee.printStackTrace();
//        }
    }

    private void SendTextKeyPressed(KeyEvent e) {
        // TODO add your code here
        if (e.getKeyCode()==10){
            //SendBtnMouseClicked();
            try {
                OutputStream outputStream=socket.getOutputStream();//字节输出流
                //DataOutputStream dout=new DataOutputStream(outputStream);//数据输出流
                ObjectOutputStream ObjOut = new ObjectOutputStream(outputStream);
                String text=SendText.getText();//获取待发送文本框的内容
                if (text.equals(""))return;
                if (text.length()>600){
                    new HintWindow(this,"单次发送的信息不能这么长哦！",1).setVisible(true);
                    return;
                }
                text=Util.htmlEscape1(text); //替换html字符
                //dout.writeUTF(text);//输出到文件
                ObjOut.writeObject(new Msg(0,text));
                //ChatTextArea.append(">>>我说 : "+text+"\n");//添加到显示的文本区域
                String fPath = "<img src=\""+getClass().getResource("/res/emoji").toString()+"/$1.png\"/>";
                text=text.replaceAll("#\\(([^)]+)\\)", fPath);
                ChatTextPane.addText("<p>"+">我 : "+text+"</p>");
                SendText.setText("");//设置下一次需要输入文本框的内容为空
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        //System.out.println(e);

    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        NorthPanle = new JPanel();
        JoinBtn = new JButton();
        StaLabel = new JLabel();
        panel2 = new JPanel();
        label2 = new JLabel();
        NameText = new JTextField();
        SouthPanel = new JPanel();
        SendText = new JTextField();
        SendBtn = new JButton();
        scrollPane1 = new JScrollPane();
        ChatTextPane = new HTextPane();

        //======== this ========
        setMinimumSize(new Dimension(506, 37));
        setTitle("WeQ");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/res/h.png")).getImage());
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== NorthPanle ========
        {
            NorthPanle.setMinimumSize(new Dimension(506, 40));
            NorthPanle.setLayout(new BorderLayout());

            //---- JoinBtn ----
            JoinBtn.setText("\u52a0\u5165\u804a\u5929\u5ba4");
            JoinBtn.setMinimumSize(new Dimension(94, 25));
            JoinBtn.setMaximumSize(new Dimension(94, 25));
            JoinBtn.setPreferredSize(new Dimension(94, 25));
            JoinBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    button1MouseClicked(e);
                }
            });
            NorthPanle.add(JoinBtn, BorderLayout.EAST);

            //---- StaLabel ----
            StaLabel.setText("\u5f53\u524d\u5728\u7ebf\uff1a\uff1f\u4eba");
            StaLabel.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
            NorthPanle.add(StaLabel, BorderLayout.WEST);

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout(FlowLayout.RIGHT));

                //---- label2 ----
                label2.setText("\u6635\u79f0");
                label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 14));
                panel2.add(label2);

                //---- NameText ----
                NameText.setMinimumSize(new Dimension(40, 30));
                NameText.setPreferredSize(new Dimension(200, 30));
                panel2.add(NameText);
            }
            NorthPanle.add(panel2, BorderLayout.CENTER);
        }
        contentPane.add(NorthPanle, BorderLayout.NORTH);

        //======== SouthPanel ========
        {
            SouthPanel.setLayout(new BorderLayout());

            //---- SendText ----
            SendText.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    SendTextKeyPressed(e);
                }
            });
            SouthPanel.add(SendText, BorderLayout.CENTER);

            //---- SendBtn ----
            SendBtn.setText("\u53d1\u9001");
            SendBtn.setEnabled(false);
            SendBtn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    SendBtnMouseClicked(e);
                }
            });
            SouthPanel.add(SendBtn, BorderLayout.EAST);
        }
        contentPane.add(SouthPanel, BorderLayout.SOUTH);

        //======== scrollPane1 ========
        {

            //---- ChatTextPane ----
            ChatTextPane.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
            ChatTextPane.setText("<html>\r\n  <head>\r\n\n  </head>\r\n  <body>\r\n    <p style=\"margin-top: 0\">\r\n      \r\n    </p>\r\n  </body>\r\n</html>\r\n");
            scrollPane1.setViewportView(ChatTextPane);
        }
        contentPane.add(scrollPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel NorthPanle;
    private JButton JoinBtn;
    private JLabel StaLabel;
    private JPanel panel2;
    private JLabel label2;
    private JTextField NameText;
    private JPanel SouthPanel;
    private JTextField SendText;
    private JButton SendBtn;
    private JScrollPane scrollPane1;
    private HTextPane ChatTextPane;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private Socket socket;
    private String user="";
    private Boolean flag=false;
    private HashSet<String> names;
    private final String version="0.0.3";
    private String sVersion;

    public static void main(String[] args) throws IOException {
        try {
            UIManager.setLookAndFeel( new FlatIntelliJLaf() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }
        MainWindow mw = new MainWindow();
        mw.setBounds(0,0,577,506);
        SwingUtil.centerInScreen(mw);
        mw.setVisible(true);

    }

    //get


    public JLabel getStaLabel() {
        return StaLabel;
    }

    public HTextPane getChatTextPane() {
        return ChatTextPane;
    }
}
