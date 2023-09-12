/*
 * Created by JFormDesigner on Wed Jan 18 15:36:14 CST 2023
 */

package client;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.*;

/**
 * @author 0x3fffff
 * @URL https://blog.csdn.net/qq_19655605?type=blog
 */
public class HintWindow extends JDialog {
    public HintWindow(JFrame frame,String text,int flag) {
        super(frame,"WeQ",true);
        this.text=text;
        this.flag=flag;
        initComponents();
        init();
        SwingUtil.centerInOwner(frame,this);
    }

    private void okButtonMouseClicked(MouseEvent e) {
        // TODO add your code here
        if (flag==1){
            this.dispose();
        }else if (flag==2){
            try {
                String url = "http://ox3fffff.cn/";
                java.net.URI uri = java.net.URI.create(url);
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                    // 获取系统默认浏览器打开链接
                }
            } catch (NullPointerException | IOException e1) {
                // 此为uri为空时抛出异常
                e1.printStackTrace();
            }finally {
                this.dispose();
            }

        }else if (flag==3){
            try {
                String url = "http://ox3fffff.cn/";
                java.net.URI uri = java.net.URI.create(url);
                // 获取当前系统桌面扩展
                java.awt.Desktop dp = java.awt.Desktop.getDesktop();
                // 判断系统桌面是否支持要执行的功能
                if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                    dp.browse(uri);
                    // 获取系统默认浏览器打开链接
                }
            } catch (NullPointerException | IOException e1) {
                // 此为uri为空时抛出异常
                e1.printStackTrace();
            }finally {
                this.dispose();
                System.exit(1);
            }

        }
    }

    private void thisWindowClosed(WindowEvent e) {
        // TODO add your code here
        if (flag==3){
            System.exit(1);
        }
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        textLabel = new JLabel();
        buttonBar = new JPanel();
        leftButton = new JButton();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        setTitle("WeQ");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                thisWindowClosed(e);
            }
        });
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new BorderLayout());

                //---- textLabel ----
                textLabel.setText("-");
                contentPanel.add(textLabel, BorderLayout.CENTER);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- leftButton ----
                leftButton.setText("text");
                buttonBar.add(leftButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- okButton ----
                okButton.setText("\u786e\u5b9a");
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("\u53d6\u6d88");
                buttonBar.add(cancelButton, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables  @formatter:off
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel textLabel;
    private JPanel buttonBar;
    private JButton leftButton;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables  @formatter:on
    private int flag;
    private String text;
    public void init(){
        if (flag==1){//无取消按钮
            cancelButton.setVisible(false);
            leftButton.setVisible(false);
            textLabel.setText(text);
        }else if (flag==2){//确定按钮打开连接
            cancelButton.setVisible(false);
            leftButton.setVisible(false);
            textLabel.setText(text);
        }else if (flag==3){//确定按钮打开连接
            cancelButton.setVisible(false);
            leftButton.setVisible(false);
            textLabel.setText(text);
        }
    }

    public static void main(String[] args) {
        //new HintWindow(this,"单次发送的文字不能大于500哦!",2).setVisible(true);
    }
}
