import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MyGame extends GamePanel {

    Image background;
    static BufferedImage[] bottomRunner = new BufferedImage[100];
    Sprite bird;
    BufferedImage bottomColumn;
    Obstacle[] obstacles = new Obstacle[100];
    int obstacleStart = 1500;

    static boolean ready = false;
    int tick = 0;
    Rectangle ground;
    int randomNum;
    MainMenu mainMenu = new MainMenu();
    int birdDistanceTraveled = 0;

    static int score = 0; //used for score and for index of column past
    static int bestScore = 0;

    GameOver gameOver;
    boolean gameIsOver = false;

    HUDScore hud;

    public void initialize() {
        background = Toolkit.getDefaultToolkit().getImage("./images/background.png");
        bird = new Sprite(100, 400, 35, 35, "brd", Sprite.pose, 4, "png");
        try {
            for (int i = 0; i < bottomRunner.length; i++) {
                bottomRunner[i] = ImageIO.read(new File("./images/bottom_runner.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOver = new GameOver((int)bird.px, 0);
        ground = new Rectangle(Camera.x, 1020 - bottomRunner[0].getHeight() + 30, 1000000000, 1000, Color.BLACK);
        hud = new HUDScore((int)bird.px);

    }

    public void respond_To_User_Input() {
        if(!ready){
            bird.accelerationY = 0;
            if(pressing[S]){
                obstacleStart = (int)bird.px + 1500;
                ready = true;
                bird.accelerationY = 0.75;
                for (int i = 0; i < obstacles.length; i++) { //initialize obstacle locations
                    randomNum = ThreadLocalRandom.current().nextInt(-500, -100 + 1);
                    obstacles[i] = new Obstacle(obstacleStart + (i * 400), randomNum); //Camera.x +
                }
                pressing[S] = false;
            }
        } else { //we are ready to play, user has pressed S
            if(!gameIsOver){
                if(pressing[UP]){
                    bird.jump(16);
                    pressing[UP] = false;
                }
            } else {
                if(pressing[R]){
                    playAgain();
                }
            }
        }
    }

    public void move_Computer_Controlled_Entities() {
        if(ready){
            if(bird.isMoving){
                Camera.moveRight(5);
                bird.moveRight(5);
                hud.x = (int) bird.px;

                if(bird.px > (obstacleStart + 50 - 400)) { // Once the bird passes the first obstacle minus 400, the distance between each obstacle. (90 is the width of the obstacle image(130 - 40), which is how it's drawn in Obstacle class)
                    birdDistanceTraveled += 5;
                    if(birdDistanceTraveled % 400 == 0) {
                        score += 1;
                    }
                }
            }
            if(score > bestScore){
                bestScore = score;
            }
        }
    }

    public void resolve_Collisions() {
        if(ready) {
            if (bird.overlaps(ground)) {
                bird.py = 1015 - bottomRunner[0].getHeight();
                bird.isMoving = false;
                bird.isOverlapping = true;
                gameOver.x = (int)bird.px;
                gameIsOver = true;
            }
            for (int i = 0; i < obstacles.length; i++) {
                if ((bird.overlaps(obstacles[i].rectTop)) || (bird.overlaps(obstacles[i].rectBottom))) { //
                    bird.isOverlapping = true;
                    gameOver.x = (int)bird.px;
                    gameIsOver = true;
                }
            }
            if (bird.isOverlapping){
                bird.moveDown();
                if(bird.py > 1015 - bottomRunner[0].getHeight()){
                    bird.py = 1015 - bottomRunner[0].getHeight();
                }
            }
        }
    }

    //-------------------------------------------------------------------------
    // paint the screen when the O.S. calls according to the code below
    //-------------------------------------------------------------------------

    public void paint(Graphics pen) {
        tick++;
        if(tick > 60) {
            pen.drawImage(background, (int) bird.px - 100 - Camera.x, 0, background.getWidth(null) + 50, 1020, null);
            if(!ready) {
                mainMenu.draw(pen);
            }
            else {

                for (int i = 0; i < obstacles.length; i++) {
                    obstacles[i].draw(pen);
                }
                for (int i = 0; i < bottomRunner.length; i++) {
                    pen.drawImage(bottomRunner[i], i * bottomRunner[i].getWidth() - Camera.x, 1020 - bottomRunner[i].getHeight() + 30, bottomRunner[i].getWidth(), bottomRunner[i].getHeight(), null);
                }

                if(bird.isOverlapping) {
                    gameOver.draw(pen);
                }
                else{
                    hud.draw(pen);
                }
                //ground.draw(pen);
            }
        bird.draw(pen);
        }
    }
    int mx = 0;
    int my = 0;

    int nx = 0;
    int ny = 0;


    public void mousePressed(MouseEvent e)
    {
        // Get the mouse location
        mx = e.getX();
        my = e.getY();
    }

    //-------------------------------------------------------------------------

    public void mouseDragged(MouseEvent e)
    {
        // Get the mouse location
        nx = e.getX();
        ny = e.getY();

    }

    public void mouseMoved(MouseEvent e)
    {
        // Get the mouse location
        mx = e.getX();
        my = e.getY();
    }

    public void mouseReleased(MouseEvent e)
    {
        mousePressed = false;
    }

    public void playAgain(){
        ready = false;
        gameIsOver = false;
        score = 0;
        bird.isOverlapping = false;
        bird.px = 100;
        bird.py = 400;
        Camera.x = 0;
        bird.isMoving = false;
        birdDistanceTraveled = 0;
        hud.x = (int) bird.px;
    }

}
