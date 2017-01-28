import javax.swing.*;

public class Log extends Obstacle
{
    public static final double DAMAGE = 10;
    public static final double WIDTH = 80;
    public static final double HEIGHT = 20;
    
    public Log(double xPos, double yPos, double maxY)
    {
        super(DAMAGE, xPos, yPos, maxY, WIDTH, HEIGHT);
    }
    /*
    public boolean collide(Car car)
    {
        if(car.getYPos() + car.getCarHeight() + car.getCarHeight()/3.0 >= super.getY() || car.getYPos() <= super.getY() + super.getHeight())
        {
            if(car.getXPos() + car.getCarWidth() >= super.getX() || car.getXPos() <= super.getX() + super.getWidth())
                return true;
        }
        return false;
    }
    */
}