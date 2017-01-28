import java.util.ArrayList;
import java.awt.Color;
import java.util.Date;
import javax.swing.*;

public class Game
{
    private Car[] cars;
    private ArrayList<Obstacle> obstacles1, obstacles2;
    private ArrayList<Dash> dashes1;
    private ArrayList<Dash> dashes2;
    private final double DISTANCE = 200000; //should take about 1 minute
    private JFrame field;
    private final double SPEED_REDUCTION = 50;
    private double lastPosition1 = 0, lastPosition2 = 0;
    private final double OBSTACLE_DISTANCE;
    private final int DIFFICULTY;
    
    
    public Game()
    {
        cars = new Car[]{new Car(), new Car()};
        obstacles1 = new ArrayList<Obstacle>();
        obstacles2 = new ArrayList<Obstacle>();
        dashes1 = new ArrayList<Dash>();
        dashes2 = new ArrayList<Dash>();
        DIFFICULTY = 0;
        OBSTACLE_DISTANCE = 1000;
    }
    
    public Game(JFrame field, String n1, Color c1, String n2, Color c2, int difficulty)
    {
        this.field = field;
        cars = new Car[]{new Car(field, n1, c1, field.getWidth() / 4 - Car.getCarWidth()/2, 0, field.getWidth() / 2),
                new Car(field, n2, c2, field.getWidth() * .75 - Car.getCarWidth()/2, field.getWidth() / 2, field.getWidth())};
        obstacles1 = new ArrayList<Obstacle>();
        obstacles2 = new ArrayList<Obstacle>();
        dashes1 = new ArrayList<Dash>();
        dashes2 = new ArrayList<Dash>();
        initializeDashes();
        DIFFICULTY = difficulty;
        switch(difficulty)
        {
            case 0: OBSTACLE_DISTANCE = 5000;
            break;
            case 1: OBSTACLE_DISTANCE = 3500;
            break;
            case 2: OBSTACLE_DISTANCE = 2000;
            break;
            case 3: OBSTACLE_DISTANCE = 1000;
            break;
            case 4: OBSTACLE_DISTANCE = 10;
            break;
            default: OBSTACLE_DISTANCE = 1;
        }
    }
    
    public Car getCar1() {return cars[0];}
    public Car getCar2() {return cars[1];}
    public ArrayList getObstacles1() {return obstacles1;}
    public ArrayList getObstacles2() {return obstacles2;}
    public ArrayList getObjects()
    {
        ArrayList<Object> objects = new ArrayList<Object>();
       
        for (Object obj : dashes1)
            objects.add(obj);
        for (Object obj : dashes2)
            objects.add(obj);
            
        for (Object obj : obstacles1)
            objects.add(obj);
        for (Object obj : obstacles2)
            objects.add(obj);
            
        objects.add(cars[0]);
        objects.add(cars[1]);
        return objects;
    }
    
    private void initializeDashes()
    {
        for (int i = 23; i < cars[0].getMaxY(); i += 45)
        {
            dashes1.add(new Dash((cars[0].getMaxX() - cars[0].getMinX()) / 2 + cars[0].getMinX(), i));
            dashes2.add(new Dash((cars[1].getMaxX() - cars[1].getMinX()) / 2 + cars[1].getMinX(), i));
        }
    }
    
    
    /**
     * @return -1 if car1 wins
     *         0 if no one has won yet
     *         1 if car2 wins
     */
    public int turn()
    {
        cars[0].moveForward();
        cars[1].moveForward();
        for (int i = 0; i < obstacles1.size(); i++)
        {
            obstacles1.get(i).incrementY(cars[0].getSpeed()/SPEED_REDUCTION);
            if (obstacles1.get(i).getY() > cars[0].getMaxY())
            {
                obstacles1.remove(i);
                i--;
            }
            else if (obstacles1.get(i).collide(cars[0]))
            {
                cars[0].collide(obstacles1.get(i));
                obstacles1.remove(i);
                i--;
            }
        }
        for (int i = 0; i < obstacles2.size(); i++)
        {
            obstacles2.get(i).incrementY(cars[1].getSpeed()/SPEED_REDUCTION);
            if (obstacles2.get(i).getY() > cars[1].getMaxY())
            {
                obstacles2.remove(i);
                i--;
            }
            else if (obstacles2.get(i).collide(cars[1]))
            {
                cars[1].collide(obstacles2.get(i));
                obstacles2.remove(i);
                i--;
            }
        }
        
        for (int i = 0; i < dashes1.size(); i++)
        {
            dashes1.get(i).incrementY(cars[0].getSpeed()/SPEED_REDUCTION);
            if (dashes1.get(i).getYPos() > cars[0].getMaxY())
            {
                dashes1.remove(i);
                dashes1.add(dashes1.size(), new Dash((cars[0].getMaxX() - cars[0].getMinX()) / 2 + cars[0].getMinX(), 0));
                i--;
            }
        }
        for (int i = 0; i < dashes2.size(); i++)
        {
            dashes2.get(i).incrementY(cars[1].getSpeed()/SPEED_REDUCTION);
            if (dashes2.get(i).getYPos() > cars[1].getMaxY())
            {
                dashes2.remove(i);
                dashes2.add(dashes2.size(), new Dash((cars[1].getMaxX() - cars[1].getMinX()) / 2 + cars[1].getMinX(), 0));
                i--;
            }
        }
        
        //add Obstacles
        if (cars[0].getSpeed() != 0 && cars[0].getPosition() - lastPosition1 > OBSTACLE_DISTANCE && Math.random() > .3)
        {
            obstacles1.add(randomObstacle(1));
            lastPosition1 = cars[0].getPosition();
            //System.out.println("HI");
        }
        if (cars[1].getSpeed() != 0 && cars[1].getPosition() - lastPosition2 > OBSTACLE_DISTANCE && Math.random() > .3)
        {
            obstacles2.add(randomObstacle(2));
            lastPosition2 = cars[1].getPosition();
        }
            
            
        
        if (cars[0].getPosition() >= DISTANCE || cars[1].getHealth() <= 0)
            return -1;
        else if (cars[1].getPosition() >= DISTANCE || cars[0].getHealth() <= 0)
            return 1;
        else
            return 0;
        
    }
    
    public Obstacle randomObstacle(int num)
    {
        double d = Math.random();
        double x = Math.random() * (cars[num-1].getMaxX() - cars[num-1].getMinX())
                    + cars[num-1].getMinX();
        double y = cars[num-1].getMinY();
        if (d < .475)
            return new Rock((x+Rock.WIDTH > cars[num-1].getMaxX() ? cars[num-1].getMaxX() - Rock.WIDTH : x), y, cars[num-1].getMaxY());
        else if (d < .95)
            return new Log((x+Log.WIDTH > cars[num-1].getMaxX() ? cars[num-1].getMaxX() - Log.WIDTH : x), y, cars[num-1].getMaxY());
        else
            return new Sos((x+Sos.WIDTH > cars[num-1].getMaxX() ? cars[num-1].getMaxX() - Sos.WIDTH : x), y, cars[num-1].getMaxY());
    }
    
    public void printObstacles()
    {
        /*
        for (Obstacle obs : obstacles1)
            System.out.println(obs);
        System.out.println();
        for (Obstacle obs : obstacles2)
            System.out.println(obs);
            */
        System.out.println(obstacles1.size() + " " + obstacles2.size());
    }
}