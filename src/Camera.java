public class Camera {
    static int x = 0;
    static int y = 0;
    static int z = 1;

    static int angle = 0;

    static double cosA = 1;
    static double sinA = 0;

    public static void initialize(int x, int y, int z, int angle)
    {
        Camera.x = x;
        Camera.y = y;
        Camera.z = z;

        Camera.angle = angle;

        cosA = Lookup.cos[angle];
        sinA = Lookup.sin[angle];
    }

    public static void initialize(int x, int y, int angle)
    {
        Camera.x = x;
        Camera.y = y;
        Camera.z = 0;

        Camera.angle = angle;

        cosA = Lookup.cos[angle];
        sinA = Lookup.sin[angle];
    }

    public static void turnLeft(int dangle)
    {
        angle -= dangle;

        if(angle < 0)  angle += 360;
    }

    public static void turnRight(int dangle)
    {
        angle += dangle;

        if(angle >= 360)  angle -= 360;
    }



    public static void moveRight(int dx)
    {
        x += dx;
    }

    public static void moveLeft(int dx)
    {
        x -= dx;
    }

    public static void moveUp(int dy)
    {
        y -= dy;
    }

    public static void moveDown(int dy)
    {
        y += dy;
    }

    public static void moveIn(int dz)
    {
        z += dz;
    }

    public static void moveOut(int dz)
    {
        z -= dz;
    }
}
