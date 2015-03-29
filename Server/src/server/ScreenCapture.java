
package server;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ScreenCapture {
    public ScreenCapture() throws AWTException
    {
        try{
            Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
            Robot robot = new Robot();
            BufferedImage img = robot.createScreenCapture(new Rectangle(size));
            File save_path=new File("C://Users//chauhan//Documents//NetBeansProjects//Server//capture.jpg");
            ImageIO.write(img, "JPG", save_path);
            Resize R=new Resize();
            }
            catch(Exception e)
            {
                System.out.println("Problem");
            }
    }
    
}
