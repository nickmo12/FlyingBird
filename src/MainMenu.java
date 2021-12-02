import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainMenu {
    int x;
    int y;
    Image background = Toolkit.getDefaultToolkit().getImage("./images/background.png");
    Image getReady = Toolkit.getDefaultToolkit().getImage("./images/get_ready.png");
    BufferedImage[] bottomRunner = new BufferedImage[100];

    public MainMenu(){
        x = 0;
        y = 0;
        try {
            for (int i = 0; i < bottomRunner.length; i++) {
                bottomRunner[i] = ImageIO.read(new File("./images/bottom_runner.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public void draw(Graphics pen) {
        moveRunner();
         for (int i = 0; i < bottomRunner.length; i++) {
             pen.drawImage(bottomRunner[i], i * bottomRunner[i].getWidth() - x, 1020 - bottomRunner[i].getHeight() + 30, bottomRunner[i].getWidth(), bottomRunner[i].getHeight(), null);
         }
         pen.drawImage(getReady, 100, background.getHeight(null)/5, getReady.getWidth(null), getReady.getHeight(null), null);
         pen.setFont(new Font("Arial", Font.BOLD, 36));
         pen.drawString("Press S to Start", background.getWidth(null)/2 - 100, background.getHeight(null)/3);
         pen.drawString("Press UP to Jump", background.getWidth(null)/2 - 100, background.getHeight(null)/3 + 40);
     }

     public void moveRunner(){
        x += 5;
     }
}
