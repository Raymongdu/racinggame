public class Dash
{
    public static final double WIDTH = 5;
    public static final double HEIGHT = 15;
    private double xPos, yPos;
    
    public Dash(double x, double y)
    {
        xPos = x;
        yPos = y;
    }
    
    public double getWidth() {return WIDTH;}
    public double getHeight() {return HEIGHT;}
    public double getXPos() {return xPos;}
    public double getYPos() {return yPos;}
    
    public void decrementY(double y) {yPos -= y;}
    public void decrementX(double x) {xPos -= x;}
    public void incrementY(double y) {yPos += y;}
    public void incrementX(double x) {xPos += x;}
}