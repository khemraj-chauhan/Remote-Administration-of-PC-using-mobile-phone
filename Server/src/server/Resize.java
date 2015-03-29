
package server;


import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class Resize {
private static final int IMG_WIDTH = 342;
private static final int IMG_HEIGHT = 192;
    
    public Resize() {
        try {
            // TODO code application logic here
            BufferedImage originalImage = ImageIO.read(new File("C://Users//chauhan//Documents//NetBeansProjects//Server//capture.jpg"));
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizeImageJpg = resizeImage(originalImage, type);
            ImageIO.write(resizeImageJpg, "jpg", new File("C://Users//chauhan//Documents//NetBeansProjects//Server//capture_copy.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(Resize.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
       private static BufferedImage resizeImage(BufferedImage originalImage, int type){
	BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	Graphics2D g = resizedImage.createGraphics();
	g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	g.dispose();
 
	return resizedImage;
    }
}
