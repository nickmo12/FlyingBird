import java.awt.*;

public class Sprite extends Rectangle {
    Animation[] animation;  // an array of Animation objects to select from

    final int RIGHT = 0;    // Convenience constants
    final int DOWN  = 1;    // indexing the array of
    final int LEFT  = 2;    // Animations according to
    final int UP    = 3;    // the type of motion


    boolean isMoving = false; // the Sprite can be moving or still
    boolean isJumping = false;

    int motion = RIGHT;        // index indicating the type of motion

    boolean alive = true;
    static String[] pose = {"fly"};

    boolean isOverlapping = false;


    //-------------------------------------------------------------------------
    // Construct the Sprite
    //-------------------------------------------------------------------------

    public Sprite(int x, int y, int w, int h, String name, String[] pose, int count, String filetype)
    {
        super(x, y, w, h, Color.RED);

        // Set length of array to match the number of Animations
        animation = new Animation[pose.length];

        // Load the Animations
        for(int i = 0; i < pose.length; i++)
        {
            animation[i] = new Animation(name + "_" + pose[i] + "_", count, filetype);
        }
        //this.accelerationY = 0.75;
        //this.velocityX = 2;
    }

    //-------------------------------------------------------------------------
    // Construct the Sprite
    //-------------------------------------------------------------------------

    public Sprite(int x, int y, int w, int h, String name, String[] pose, int[] count, String filetype)
    {
        super(x, y, w, h, Color.RED);

        // Set length of array to match the number of Animations
        animation = new Animation[pose.length];

        // Load the Animations
        for(int i = 0; i < pose.length; i++)
        {
            animation[i] = new Animation(name + "_" + pose[i] + "_", count[i], filetype);
        }
    }

    public void setVelocityY(int velocity){
        this.velocityY = velocity;
    }

    //-------------------------------------------------------------------------

    public void jump(int dy)
    {
        velocityY -= dy;         // moving up on the screen corresponds to y going down

        isMoving = true;   // Cause paint method to use Animation's current image

        isJumping = true;
    }

    //-------------------------------------------------------------------------

    public void moveUp(int dy)
    {
        py -= dy;         // moving up on the screen corresponds to y going down

        isMoving = true;   // Cause paint method to use Animation's current image

        motion = UP;     // Set Animation array index according to this action
    }

    //-------------------------------------------------------------------------

    public void moveDown()
    {
        //py += dy;
        py += 20;
        //velocityY -= accelerationY;

        isMoving = false;

        //motion = DOWN;
    }

    //-------------------------------------------------------------------------

    public void moveLeft(int dx)
    {
        //if(!jumping)
        {
            px -= dx;

            isMoving = true;

            motion = LEFT;
        }
    }

    //-------------------------------------------------------------------------

    public void moveRight(int dx)
    {
        px += dx;
        py += velocityY;
        velocityY += accelerationY;

        isMoving = true;

        motion = RIGHT;

    }
    public void move(){
        isMoving = true;
        px += velocityX;
        py += velocityY;

        velocityX += accelerationX;
        velocityY += accelerationY;

    }

    public void fall(){
        if(py < 1015 - MyGame.bottomRunner[0].getHeight()){
            py += 10;
        }
        else {
            py = 1015 - MyGame.bottomRunner[0].getHeight();
        }
    }

    //-------------------------------------------------------------------------

    public void dies()
    {
        alive = false;

        py = -100000;
    }

    //-------------------------------------------------------------------------
    // Draws the Sprite on the screen according to its state:
    //   moving indicated whether to use the Animation's current or still image
    //   motion is an index into the array of Animations to pick the right pose
    //-------------------------------------------------------------------------

    public void draw(Graphics pen)
    {
        if(alive) {
            if(isMoving) {
                pen.drawImage(animation[motion].getCurrentImage(), (int)px - Camera.x, (int)py, w, h, null);
            }

            else {
                pen.drawImage(animation[motion].getStillImage(), (int)px - Camera.x, (int)py, w, h, null);
            }

            //moving = false;  // Set back to default

//            if(!jumping) {
//                velocityX = 0;
//            }

            //super.draw(pen);
        }
    }
}
