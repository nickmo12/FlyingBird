import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HUDScore {
    int x;

    BufferedImage[] numbers = new BufferedImage[10];

    public HUDScore(int x) {
        this.x = x;

        try {
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = ImageIO.read(new File("./images/numbers/big_" + i + ".png"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics pen) {

        if (MyGame.score <= 9) { //single digit
            pen.drawImage(numbers[MyGame.score], x - Camera.x + 200, 30, numbers[MyGame.score].getWidth(), numbers[MyGame.score].getHeight(), null);
        } else {
            pen.drawImage(numbers[MyGame.score / 10], x - Camera.x - 30 + 200, 30, numbers[MyGame.score / 10].getWidth(), numbers[MyGame.score / 10].getHeight(), null);
            pen.drawImage(numbers[MyGame.score % 10], x - Camera.x + 200, 30, numbers[MyGame.score % 10].getWidth(), numbers[MyGame.score % 10].getHeight(), null);
        }
    }
}
