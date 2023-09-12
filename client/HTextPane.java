package client;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import java.io.IOException;

public class HTextPane extends JTextPane {

    /** Creates a new instance of JTextPanel_N */
    StringBuffer temp=new StringBuffer();
    javax.swing.text.html.HTMLDocument hd;
    int tempnum=0;
    public HTextPane() {
        super.setContentType("text/html");
        super.setEditable(false);
        hd=new HTMLDocument();
        hd=(HTMLDocument) super.getDocument();


    }
    /***/
    public void addText(String str) {

        if(tempnum==0) {
            try {
                hd.setInnerHTML(hd.getDefaultRootElement(),str);
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            tempnum++;
        } else {
            try {
                System.out.println("super.getDocument().getEndPosition().getOffset() is: "+super.getDocument().getEndPosition().getOffset());
                hd.insertBeforeEnd(hd.getDefaultRootElement(),str);
                super.setCaretPosition(super.getDocument().getLength());
            } catch (BadLocationException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        //temp.append(str);
        // super.setText(temp.toString());
    }

}


