import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameOver {
    int x;
    int y;

    BufferedImage banner;
    BufferedImage scoreboard;
    BufferedImage background;

    BufferedImage[] smallNumbers = new BufferedImage[10];
    BufferedImage[] bigNumbers = new BufferedImage[10];

    public GameOver(int x, int y) {
        this.x = x;
        this.y = y;

        try {
            background = ImageIO.read(new File("./images/background.png"));
            banner = ImageIO.read(new File("./images/game_over.png"));
            scoreboard = ImageIO.read(new File("./images/scoreboard.png"));
            for (int i = 0; i < smallNumbers.length; i++) {
                smallNumbers[i] = ImageIO.read(new File("./images/numbers/small_" + i + ".png"));
                bigNumbers[i] = ImageIO.read(new File("./images/numbers/big_" + i + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics pen){
        pen.drawImage(banner, x - Camera.x + 20, background.getHeight()/5, banner.getWidth(), banner.getHeight(), null); // + background.getWidth()/2 - banner.getWidth()/2     background.getHeight()/5, banner.getWidth(), banner.getHeight()
        pen.drawImage(scoreboard, x - Camera.x, background.getHeight()/3, scoreboard.getWidth(), scoreboard.getHeight(), null);

        //SCORE
        if(MyGame.score <= 9){ //single digit
            pen.drawImage(bigNumbers[MyGame.score], x - Camera.x + scoreboard.getWidth()/2 + ((scoreboard.getWidth()/3)) + 8, background.getHeight()/3 + scoreboard.getHeight()/3 - 10, bigNumbers[MyGame.score].getWidth(), scoreboard.getHeight()/6, null);
        }
        else {
            pen.drawImage(bigNumbers[MyGame.score / 10], x - Camera.x + scoreboard.getWidth()/2 + ((scoreboard.getWidth()/3)) - 28, background.getHeight()/3 + scoreboard.getHeight()/3 - 10, bigNumbers[MyGame.score / 10].getWidth(), scoreboard.getHeight()/6, null);
            pen.drawImage(bigNumbers[MyGame.score % 10], x - Camera.x + scoreboard.getWidth()/2 + ((scoreboard.getWidth()/3)) + 8, background.getHeight()/3 + scoreboard.getHeight()/3 - 10, bigNumbers[MyGame.score % 10].getWidth(), scoreboard.getHeight()/6, null);

        }
        //BEST
        if(MyGame.bestScore <= 9){ //single digit
            pen.drawImage(bigNumbers[MyGame.bestScore], x - Camera.x + scoreboard.getWidth()/2 + ((scoreboard.getWidth()/3)) + 8, background.getHeight()/3 + scoreboard.getHeight()/3 + 90, bigNumbers[MyGame.bestScore].getWidth(), scoreboard.getHeight()/6, null);
        }
        else {
            pen.drawImage(bigNumbers[MyGame.bestScore / 10], x - Camera.x + scoreboard.getWidth()/2 + ((scoreboard.getWidth()/3)) - 28, background.getHeight()/3 + scoreboard.getHeight()/3 + 90, bigNumbers[MyGame.bestScore / 10].getWidth(), scoreboard.getHeight()/6, null);
            pen.drawImage(bigNumbers[MyGame.bestScore % 10], x - Camera.x + scoreboard.getWidth()/2 + ((scoreboard.getWidth()/3)) + 8, background.getHeight()/3 + scoreboard.getHeight()/3 + 90, bigNumbers[MyGame.bestScore % 10].getWidth(), scoreboard.getHeight()/6, null);

        }
        pen.setFont(new Font("Arial", Font.BOLD, 36));
        pen.drawString("Press R to Restart", x - Camera.x + 70, ((background.getHeight()/3) * 2) - 70);
    }

}
