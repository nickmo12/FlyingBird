import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animation {
    BufferedImage[] image;

    // Index into the array of images
    int current = 0;

    // The first image is a still pose
    final int still = 0;

    // count down from 10
    int delay = 3;

    //-------------------------------------------------------------------------
    // Construct the Animation object:
    //-------------------------------------------------------------------------
    // name     : leading substring shared by all image files in the Animation
    // count    : number of images in the Animation
    // filetype : filetype shared by all image files in the Animation
    //-------------------------------------------------------------------------
    // filepath = "./image/[shared name][index#].[filetype]"
    //-------------------------------------------------------------------------

    public Animation(String name, int count, String filetype)
    {
        // Create array to store images
        image = new BufferedImage[count];

        // Load all the images from files into the array
        for(int i = 0; i < count; i++)
        {
            try {
                image[i] = ImageIO.read(new File("./images/" + name + i + "." + filetype));
            } catch (IOException e) {
                e.printStackTrace();
            }
            //image[i] = Toolkit.getDefaultToolkit().getImage("./image/" + name + i + "." + filetype);
        }
    }

    //-------------------------------------------------------------------------
    // draw the current image as reported by the getCurrentImage method
    //-------------------------------------------------------------------------

    public void draw(Graphics pen)
    {
        pen.drawImage(getCurrentImage(), 200, 200, null);
    }

    //-------------------------------------------------------------------------
    // return one image from the array as indicated by the index current
    //-------------------------------------------------------------------------
    // The index current is updated when the delay count down reaches zero
    // at which point the delay is reset to its maximum value
    //-------------------------------------------------------------------------

    public Image getCurrentImage()
    {
        // when count down complete
        if(delay == 0)
        {
            // index next image
            current++;

            // cycle back to first image after last image
            if (current == image.length)  current = 0;

            // reset count down
            delay = 3;
        }

        // count down
        delay--;

        return image[current];
    }

    //-------------------------------------------------------------------------
    // return the still image associated with the Animation
    //-------------------------------------------------------------------------

    public Image getStillImage()
    {
        return image[still];
    }

    //-------------------------------------------------------------------------

}
