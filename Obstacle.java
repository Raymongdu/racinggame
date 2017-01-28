import javax.swing.*;

public abstract class Obstacle
{
    private double damage;
    private double xPos, yPos;
    private final double WIDTH, HEIGHT;
    private final double maxY;
    private final double minY;
    
    /*
    public Obstacle()
    {
        damage = 0;
        xPos = yPos = 0;
        WIDTH = HEIGHT = 0;
        maxY = 0;
    }
    */
    
    public Obstacle(double damage, double xPos, double yPos, double maxY, double width, double height)
    {
        this.damage = damage;
        this.xPos = xPos;
        this.yPos = yPos;
        this.WIDTH = width;
        this.HEIGHT = height;
        minY = yPos;
        this.maxY = maxY;
    }
    
    public double getDamage() {return damage;}
    public double getX() {return xPos;}
    public double getY() {return yPos;}
    
    public void decrementY(double y) {yPos -= y;}
    public void decrementX(double x) {xPos -= x;}
    public void incrementY(double y) {yPos += y;}
    public void incrementX(double x) {xPos += x;}
    
    public double getMaxWidth() {return WIDTH;}
    public double getMaxHeight() {return HEIGHT;}
    public double getWidth() {return WIDTH * (maxY - yPos) / (minY - maxY) + WIDTH;}
    public double getHeight() {return HEIGHT * (maxY-yPos) / (minY - maxY) + HEIGHT;}
    public double adjustSize(double d) {return d/2 * (maxY-yPos) / (minY-maxY) + d;} // sos only
    
    public String toString()
    {
        return damage + " " + xPos + " " + yPos;
    }
    
    public boolean collide(Car car)
    {
        
        if (getY() + HEIGHT >= car.getYPos() - 2/3 * car.getCarHeight() && getY() + HEIGHT <= car.getYPos() + car.getCarHeight() + car.TIRE_HEIGHT ||
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