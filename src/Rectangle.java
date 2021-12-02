import java.awt.*;

public class Rectangle {
    double px;
    double py;

    double velocityX = 0;
    double velocityY = 0;

    double accelerationX = 0;
    double accelerationY = 0;

    int w;
    int h;

    Color c;

    public Rectangle(int x, int y, int w, int h, Color c)
    {
        this.px = x;
        this.py = y;

        this.w = w;
        this.h = h;

        this.c = c;
    }

    public void draw(Graphics pen)
    {
        pen.setColor(c);

        pen.drawRect((int)px, (int)py, w, h);
    }

    public void setColor(Color c)
    {
        this.c = c;
    }


    public void setVelocity(double vx, double vy)
    {
        this.velocityX = vx;
        this.velocityY = vy;
    }

    public void setAcceleration(double ax, double ay)
    {
        this.accelerationX = ax;
        this.accelerationY = ay;
    }

    public void setSize(int w, int h)
    {
        this.w = w;
        this.h = h;
    }

    public void resizeBy(int dw, int dh)
    {
        w += dw;
        h += dh;
    }

    public void setLocation(int x, int y)
    {
        this.px = x;
        this.py = y;
    }

    public void move()
    {
        px += velocityX;
        py += velocityY;

        velocityX += accelerationX;
        velocityY += accelerationY;
    }

    public void moveBy(int dx, int dy)
    {
        px += dx;
        py += dy;
    }

    public void moveUp(int dy)
    {
        py -= dy;
    }

    public void moveDown(int dy)
    {
        py += dy;
    }

    public void moveLeft(int dx)
    {
        px -= dx;
    }

    public void moveRight(int dx)
    {
        px += dx;
    }

    //-------------------------------------------------------------------------
    // Is point (mx, my) contained within this Axis Aligned Rect (x, y, w, h)?
    //-------------------------------------------------------------------------

    public boolean contains(int mx, int my)
    {
        return (mx >= px) && (mx <= px+w) && (my >= py) && (my <= py+h);
    }



    public boolean overlaps(Rectangle r)
    {
        return (r.px + r.w >=   px)  &&
                (  px +   w >= r.px)  &&
                (  py +   h >= r.py)  &&
                (r.py + r.h >=   py);
    }

}
