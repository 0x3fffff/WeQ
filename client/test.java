/*
 * Created by JFormDesigner on Sun Jan 15 16:52:41 CST 2023
 */

package client;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;

/**
 * @author 0x3fffff
 * @URL https://blog.csdn.net/qq_19655605?type=blog
 */
public class test extends JFrame {
    public test() {
        initComponents();
    }

    private void button1MouseClicked(MouseEvent e) {
        // TODO add your code here
//        textPane1.setText(textPane1.getText()+"<html><img src=\"https://img-home.csdnimg.cn/images/20201124032511.png\">1231</html>");
//        System.out.println(textPane1.getText());
//        System.out.println("----------------");
//        HTMLDocument doc = (HTMLDocument) textPane1.getStyledDocument();
//
//        //SimpleAttributeSet keyWord = new SimpleAttributeSet();
//        //AttributeSet kw = textPane1.getCharacterAttributes();
//        //StyleConstants.setBold(keyWord, true);
//        try {
//            //doc.insertString(doc.getLength(), "&gt;<img src=\"file:\\E:\\项目\\聊天室\\res\\h.png\">123", null );
//            doc.insertAfterEnd(null,"<img src=\\\"file:\\\\E:\\\\项目\\\\聊天室\\\\res\\\\h.png\\\">");
//        } catch (BadLocationException | IOException ex) {
//            ex.printStackTrace();
//        }
        //textPane1.insertIcon(new ImageIcon("https://img-home.csdnimg.cn/images/20201124032511.png"));

        //test02 t2 = new test02();
        //String str="换行换行#(13)啊啊";
        //textPane1.addText("<img src=\"file:\\E:\\项目\\聊天室\\res\\h.png\">12331");
//        String str2 = getClass().getResource("/res/emoji").toString();
//        str=str.replaceAll("#\\(([^)]+)\\)", "<img src=\""+str2+"/$1.png\">");
//        textPane1.addText(str);
        //System.out.println(textPane1.getText());
        for (int i=0;i<3;i++){
            titlev.add(titles[i]);
        }
        for (int i=0;i<2;i++){
            Vector<String> T = new Vector<>();
            for (int j=0;j<3;j++){
                T.add(datas[i][j]);
            }datav.add(T);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        button1 = new JButton();
        scrollPane2 = new JScrollPane();
        table1 = new JTable(datav,titlev);

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //---- button1 ----
        button1.setText("text");
        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                button1MouseClicked(e);
            }
        });
        contentPane.add(button1, BorderLayout.SOUTH);

        //======== scrollPane2 ========
        {

            //---- table1 ----
            table1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null},
                    {null, null, null},
                },
                new String[] {
                    null, null, null
                }
            ));
            scrollPane2.setViewportView(table1);
        }
        contentPane.add(scrollPane2, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JButton button1;
    private JScrollPane scrollPane2;
    private JTable table1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    String[] titles = {"1","2","3"};
    String[][] datas = {{"a","b","c"},{"d","e","f"}};
    Vector<String> titlev=new Vector<>();
    Vector<Vector> datav = new Vector<>();
    DefaultTableModel DF = new DefaultTableModel(datav,titlev);

    public static void main(String[] args) throws BadLocationException {
        test ab = new test();
        ab.setVisible(true);
        //ab.textPane1.setText("<img src=\"file:\\E:\\项目\\聊天室\\res\\h.png\">");
        //ab.editorPane1.setText("<html><img src=\"https://img-home.csdnimg.cn/images/20201124032511.png\">1231</html>");
        ab.setBounds(0, 0, 600, 600);

    }
}

