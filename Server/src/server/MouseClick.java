
package server;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MouseClick {
    private Robot robot = new Robot();
    public MouseClick(int x,int y) throws AWTException, IOException{
        robot.mouseMove(x, y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK); 
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseClick.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ScreenCapture s1=new ScreenCapture();
        } catch (AWTException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
