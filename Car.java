import java.awt.Color;
import javax.swing.*;

public class Car
{
    private double position;
    private double health;
    private double speed;
    private Color color;
    private double xPos;
    private double yPos;
    private JFrame field;
    private static final double MIN_HEALTH = 0;
    private static final double MAX_HEALTH = 100;
    private static final double MAX_SPEED = 20;
    private static final double MIN_SPEED = 0;
    private static final double ACCEL = .002;
    private static final double DECEL = .05;
    private static final double COLLISION_SPEED_REDUCTION = 2;
    private static final double WIDTH = 75;
    private static final double HEIGHT = 30;
    private final double INITIAL_YPOS;
    private static final double moveSideways = .5;
    private final double minX, maxX;
    private final double minY = 0;
    private final double maxY;
    public static final double TIRE_WIDTH = 10;
    public static final double TIRE_HEIGHT = 20;
    private String name;
    
    public Car()
    {
        field = null;
        health = speed = xPos = yPos = position = maxY = minX = maxX = 0;
        color = new Color(0, 0, 0);
        name = "";
        INITIAL_YPOS = 450;
    }
    
    public Car(JFrame field, String name, double health, double speed, Color color, double xPos, double yPos, double minX, double maxX)
    {
        this.field = field;
        position = 0;
        this.health = health;
        this.speed = speed;
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.minX = minX;
        this.maxX = maxX;
        maxY = field.getHeight();
        this.name = name;
        INITIAL_YPOS = 450;
    }
    
    public Car(JFrame field, String name, double position, double health, double speed, Color color, double xPos, double yPos, double minX, double maxX)
    {
        this.field = field;
        this.position = position;
        this.health = health;
        this.speed = speed;
        this.color = color;
        this.xPos = xPos;
        this.yPos = yPos;
        this.minX = minX;
        this.maxX = maxX;
        maxY = field.getHeight();
        this.name = name;
        INITIAL_YPOS = 450;
    }
    
    public Car(JFrame field, String name, Color color, double xPos, double minX, double maxX)
    {
        this.field = field;
        position = 0;
        this.health = MAX_HEALTH;
        this.speed = MIN_SPEED;
        this.color = color;
        this.xPos = xPos;
        this.yPos = INITIAL_YPOS = field.getHeight() - 100 - HEIGHT;
        this.minX = minX;
        this.maxX = maxX;
        maxY = field.getHeight();
        this.name = name;
    }
    
    public Car(JFrame field, String name, double position, Color color, double xPos, double minX, double maxX)
    {
        this.field = field;
        this.position = position;
        this.health = MAX_HEALTH;
        this.speed = MIN_SPEED;
        this.color = color;
        this.xPos = xPos;
        this.yPos = INITIAL_YPOS = field.getHeight() - 100 - HEIGHT;
        this.minX = minX;
        this.maxX = maxX;
        maxY = field.getHeight();
        this.name = name;
    }
    
    public double getHealth() {return health;}
    public double getPosition() {return position;}
    public int getPrintPosition() {return (int)(position / 10);}
    public double getSpeed() {return speed;}
    public int getPrintSpeed() {return (int)(speed * 10);}
    public Color getColor() {return color;}
    public static double getMaxHealth() {return MAX_HEALTH;}
    public static double getMaxSpeed() {return MAX_SPEED;}
    public static double getAccel() {return ACCEL;}
    public static double getCarWidth() {return WIDTH;}
    public static double getCarHeight() {return HEIGHT;}
    public double getXPos() {return xPos;}
    public double getYPos() {return yPos;}
    public double getMinX() {return minX;}
    public double getMaxX() {return maxX;}
    public double getMinY() {return minY;}
    public double getMaxY() {return maxY;}
    public String getName() {return name;}
    
    public void moveForward()
    {
        position += speed;
    }
    
    public void accelerate()
    {
        speed += ACCEL;
        if (speed > MAX_SPEED)
            speed = MAX_SPEED;
    }
    
    public void decelerate()
    {
        speed -= DECEL;
        if (speed < MIN_SPEED)
            speed = MIN_SPEED;
    }
    
    public void moveLeft()
    {
        xPos -= moveSideways;
        if (xPos < minX)
            xPos = minX;
    }
    
    public void moveRight()
    {
        xPos += moveSideways;
        if (xPos > (maxX - WIDTH))
            xPos = maxX - WIDTH;
    }
    
    public void moveUp(double d)
    {
        if (d < 0)
        {
            moveDown(-d);
            return;
        }
        accelerate();
    }
    
    public void moveDown(double d)
    {
        if (d < 0)
        {
            moveUp(-d);
            return;
        }
        decelerate();
    }
    
    public void collide(Obstacle obs)
    {
        health -= obs.getDamage();
        speed /= COLLISION_SPEED_REDUCTION;
    }
    
    public String toString()
    {
        return name + " " + position + " " + xPos + " " + yPos + " " + health;
    }
    
}
