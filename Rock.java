import javax.swing.*;

public class Rock extends Obstacle
{
    public static final double DAMAGE = 5;
    public static final double WIDTH = 30;
    public static final double HEIGHT = 60;
    
    public Rock(double xPos, double yPos, double maxY)
    {
        super(DAMAGE, xPos, yPos, maxY, WIDTH, HEIGHT);
    }
    
    public boolean collide(Car car)
    {

        if (getY() - HEIGHT >= car.getYPos() - 2/3 * car.getCarHeight() && getY() - HEIGHT <= car.getYPos() + car.getCarHeight() + car.TIRE_HEIGHT ||
            getY() >= car.getYPos() - 2/3 * car.getCarHeight() && getY() <= car.getYPos() + car.getCarHeight() + car.TIRE_HEIGHT)
        {
            if (getX() + WIDTH >= car.getXPos() && getX() + WIDTH <= car.getXPos() + car.getCarWidth() ||
            getX() >= car.getXPos() && getX() <= car.getXPos() + car.getCarWidth())
            {
                return true;
            }
        }
        return false;
    }
    
}