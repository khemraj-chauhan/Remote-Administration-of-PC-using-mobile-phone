
package server;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyPress {
    public KeyPress(String s)throws AWTException{
        Robot r=new Robot();
       int i,j,k;
       s=s.toUpperCase();
       s=s.replace(" HTTP/1.0","");
       System.out.println("here "+s);
       for(i=0;i<s.length();i++){
           char res=s.charAt(i);
           System.out.println(res);
           r.keyPress(res);
           r.keyRelease(res);
       }
       r.keyPress(KeyEvent.VK_ENTER);
       r.keyRelease(KeyEvent.VK_ENTER);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(KeyPress.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScreenCapture s1=new ScreenCapture();
       
    }
}
