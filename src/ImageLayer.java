import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLayer {
    int x;
    int y;
    int z;

    BufferedImage image;


    public ImageLayer(int x, int y, int z, String filename)
    {
        this.x = x;
        this.y = y;
        this.z = z;

        try {
            this.image = ImageIO.read(new File("./image/" + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.image = Toolkit.getDefaultToolkit().getImage("./image/" + filename);
    }

    public void draw(Graphics pen)
    {
        int w = image.getWidth(null);

        for(int i = 0; i < 20; i++)

            pen.drawImage(image, x + i * w - Camera.x / z, y, null);
    }
}
