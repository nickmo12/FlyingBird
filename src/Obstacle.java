import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Obstacle {
    double x;
    double y;

//    double w;
//    double h;


    BufferedImage bottom;
    BufferedImage top;

    Rectangle rectBottom;
    Rectangle rectTop;

    int bottomOffset = 800;

    public Obstacle(int x, int y){
        this.x = x;
        this.y = y;

        try {
            bottom = ImageIO.read(new File("./images/bottom_column.png"));
            top = ImageIO.read(new File("./images/top_column.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        rectTop = new Rectangle(x, y, top.getWidth() - 46, top.getHeight() - 2, Color.RED);
        rectBottom = new Rectangle(x, y + bottomOffset, bottom.getWidth() - 44, bottom.getHeight() - 1, Color.RED);
    }

    public void move(){
        rectTop.px = x - Camera.x;
        rectBottom.px = x - Camera.x;

    }

    public void draw(Graphics pen){
        pen.drawImage(top, (int) x - Camera.x, (int)y, top.getWidth() - 40, top.getHeight(), null);
        pen.drawImage(bottom, (int)x - Camera.x, (int)y + bottomOffset, bottom.getWidth() - 40, bottom.getHeight(), null); //FIXME - check distance between top and bottom
        //pen.setColor(Color.RED);
        //pen.drawRect((int)rectTop.px - Camera.x, (int)rectTop.py, rectTop.w, rectTop.h);
        //pen.drawRect((int)rectBottom.px - Camera.x, (int)rectBottom.py, rectBottom.w, rectBottom.h);
    }

}
