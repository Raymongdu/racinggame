import javax.swing.*;

public class Sos extends Obstacle
{
    public static final double DAMAGE = 25;
    public static final double WIDTH = 20;
    public static final double HEIGHT = 50;
    public static final double HEAD_SIZE = 10;
    public static final double BODY_SIZE = 30;
    public static final double ARMS_SIZE = 20;
    public static final double LEG_SIZEY = 7; //xPos = same as arms
    
    public Sos(double xPos, double yPos, double maxY)
    {
        super(DAMAGE, xPos, yPos, maxY, WIDTH, HEIGHT);
    }

    /*
    public boolean collide(Car car)
    {
        
        if (getY() + HEIGHT >= car.getY() - 2/3 * car.getCarHeight() && getY() && getY() + HEIGHT <= car.getY() + car.getCarHeight() ||
            getY() >= car.getY() && getY() <= car.getY() + car.getCarHeight())
        {
            if (getX() + WIDTH >= car.getX() && getX() + WIDTH <= car.getX() + car.getCarWidth() ||
            getX() >= car.getX() && getX() <= car.getX() + car.getCarWidth())
            {
                return true;
            }
        }
        return false;
    }
    */
    
}