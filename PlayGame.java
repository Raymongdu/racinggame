import java.awt.event.*;
import java.util.Date;
import javax.swing.*;
import java.awt.*;
/*
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
*/

public class PlayGame extends JPanel implements ActionListener
{
    private static boolean wPressed = false;
    private static boolean aPressed = false;
    private static boolean sPressed = false;
    private static boolean dPressed = false;
    private static boolean leftPressed = false;
    private static boolean rightPressed = false;
    private static boolean upPressed = false;
    private static boolean downPressed = false;
    private JLabel colorLabel, nameLabel;
    private JLabel description;
    private static Color[] colors = {Color.LIGHT_GRAY, Color.LIGHT_GRAY};
    private int carSelection = 1;
    private JTextField nameBox;
    private static String[] names = {"Car1", "Car2"};
    private static JFrame carScreen = new JFrame("Selecting Cars");
    private static JFrame levelScreen = null; //new JFrame("Choose Difficulty!");
    private static JFrame gameScreen = null; //new JFrame("Play Game!");
    private static boolean initializationComplete;
    private static Game game = null;
    public static long count = 0;
    private static int difficultyLevel = 0;
    
    public static void play()
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher()
        {
        @Override
            public boolean dispatchKeyEvent(KeyEvent ke)
            {
                synchronized (Game.class)
                {
                    switch (ke.getID())
                    {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_W)
                            wPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_A)
                            aPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_S)
                            sPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_D)
                            dPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_UP)
                            upPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_DOWN)
                            downPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
                            rightPressed = true;
                        if (ke.getKeyCode() == KeyEvent.VK_LEFT)
                            leftPressed = true;
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_W)
                            wPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_A)
                            aPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_S)
                            sPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_D)
                            dPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_UP)
                            upPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_DOWN)
                            downPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_RIGHT)
                            rightPressed = false;
                        if (ke.getKeyCode() == KeyEvent.VK_LEFT)
                            leftPressed = false;
                        break;
                    }
                }
                return false;
            }
        });
        initializationComplete = false;
        playGame();
    }
    
    private static void playGame()
    {
        /*
        try
        {
            System.out.println("HI");
            URL url = new URL("http://pscode.org/media/leftright.wav");
            Clip clip = AudioSystem.getClip();
            // getAudioInputStream() also accepts a File or InputStream
            AudioInputStream ais = AudioSystem.getAudioInputStream( url );
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            System.out.println("BYE");
        }
        catch(Exception e) {}
        */
        
        
        
        
        
        
        SwingUtilities.invokeLater(new Runnable() {
            public void run()
            {
                new PlayGame().play();
            }
        });
        
    }
    
    
    
    public PlayGame()
    {
        //if (isUpPressed())
        //    System.out.println("HI");
        if (carScreen != null)
        {
            carScreen.setLayout(new FlowLayout());
            carScreen.setSize(400, 150);
            carScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            description = new JLabel("                                     Car " + (carSelection) + "                                             ");
            carScreen.add(description);
            
            nameBox = new JTextField(20);
            nameBox.setActionCommand("nameBox");
            nameBox.addActionListener(this);
            carScreen.add(nameBox);
    
            nameLabel = new JLabel("Enter the car's name");
            carScreen.add(nameLabel);
           
            
            JButton greenBtn = new JButton("Green");
            JButton blueBtn = new JButton("Blue");
            JButton redBtn = new JButton("Red");
            JButton yellowBtn = new JButton("Yellow");
            greenBtn.addActionListener(this);
            blueBtn.addActionListener(this);
            redBtn.addActionListener(this);
            yellowBtn.addActionListener(this);
            carScreen.add(greenBtn);
            carScreen.add(blueBtn);
            carScreen.add(redBtn);
            carScreen.add(yellowBtn);
            colorLabel = new JLabel("Enter color!");
            carScreen.add(colorLabel);
            
            JButton okBtn = new JButton("               DONE               ");
            okBtn.addActionListener(this);
            carScreen.add(okBtn);
            
            carScreen.setVisible(true);
        }
        else if (levelScreen != null)
        {
            levelScreen.setLayout(new FlowLayout());
            levelScreen.setSize(500, 300);
            levelScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JLabel levelDirection = new JLabel("        Choose difficulty           ");
            levelScreen.add(levelDirection);
            
            JButton easyBtn = new JButton("Easy");
            JButton normalBtn = new JButton("Normal");
            JButton hardBtn = new JButton("Hard");
            JButton insaneBtn = new JButton("Insane");
            JButton opBtn = new JButton("Just OP");
            
            easyBtn.addActionListener(this);
            normalBtn.addActionListener(this);
            hardBtn.addActionListener(this);
            insaneBtn.addActionListener(this);
            opBtn.addActionListener(this);
            
            levelScreen.add(easyBtn);
            levelScreen.add(normalBtn);
            levelScreen.add(hardBtn);
            levelScreen.add(insaneBtn);
            levelScreen.add(opBtn);
        }
        else if (gameScreen != null)
        {
            //THIS IS THE ACTUAL GAME!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if (isWPressed())
                game.getCar1().accelerate();
            else if (isSPressed())
                game.getCar1().decelerate();
            else if (isAPressed())
                game.getCar1().moveLeft();
            else if (isDPressed())
                game.getCar1().moveRight();
            
            if (isUpPressed())
                game.getCar2().accelerate();
            else if (isDownPressed())
                game.getCar2().decelerate();
            else if (isLeftPressed())
                game.getCar2().moveLeft();
            else if (isRightPressed())
                game.getCar2().moveRight();
            
           
            gameScreen.repaint();
                

            
            int winner = game.turn();
            /*
            if (count % 1000 == 0)
            {
                System.out.println(game.getCar1());
                System.out.println(game.getCar2());
                game.printObstacles();
                System.out.println();
            }
            */
            
            if (winner != 0)
            {
                gameScreen = null;
                Object[] options = {"OK"};
                String name = (winner == -1) ? game.getCar1().getName() : game.getCar2().getName();
                String message = name + " won!";
                JOptionPane.showOptionDialog(null, message, "WINNER!!!!",
                         JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                         null, options, options[0]);
            }
            count++;
        }
    }
    
    
    
    @Override
    public void paintComponent(Graphics g)
    {
        for (Object obj: game.getObjects())
        {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.BLACK);
            g2d.drawLine(175,0,0,700);
            g2d.drawLine(275,0,450,700);
            g2d.drawLine(625,0,450,700);
            g2d.drawLine(725,0,890,700);
            g2d.drawLine(450,0,450,700);
            if(obj.getClass() == Car.class)
            {
                Car newcar = (Car)obj;
                g2d.setColor(newcar.getColor());
                g2d.fillRect((int) newcar.getXPos(), (int) newcar.getYPos(), (int)newcar.getCarWidth(), (int)newcar.getCarHeight());
                g2d.fillRect((int)(newcar.getXPos() + Car.getCarWidth()/6), (int)(newcar.getYPos() - 2*Car.getCarHeight()/3),
                    (int)(2*Car.getCarWidth()/3), (int)(2*Car.getCarHeight()/3));
                
                g2d.setColor(Color.BLACK);
                g2d.fillRect((int) newcar.getXPos(), (int) newcar.getYPos()+(int)newcar.getCarHeight(), (int)Car.TIRE_WIDTH, (int)Car.TIRE_HEIGHT);
                g2d.fillRect((int) newcar.getXPos()-10 + (int)newcar.getCarWidth(), (int) newcar.getYPos() + (int)newcar.getCarHeight(), (int)Car.TIRE_WIDTH, (int)Car.TIRE_HEIGHT);
                if (newcar.getColor() == Color.BLACK || newcar.getColor() == Color.BLUE || newcar.getColor() == Color.RED)
                    g2d.setColor(Color.WHITE);
                g2d.drawString("Sos-mobile", (int)(newcar.getXPos() + newcar.getCarWidth()/15),
                            (int)(newcar.getYPos() + 2*newcar.getCarHeight()/3));
                g2d.setColor(Color.BLACK);
                
                if(newcar.getXPos() > 440)
                {
                    g2d.drawString("Health: " + (int)(newcar.getHealth()), 460, 20);
                    g2d.drawString("Position: "+newcar.getPrintPosition() + " ft", 460, 35);
                    g2d.drawString("Speed: " + newcar.getPrintSpeed() + " mph", 460, 50);
                }
                else
                {
                    g2d.drawString("Health: " + (int)(newcar.getHealth()), 10, 20);
                    g2d.drawString("Position: "+newcar.getPrintPosition() + " ft", 10, 35);
                    g2d.drawString("Speed: " + newcar.getPrintSpeed() + " mph", 10, 50);
                }
            }
            else if(obj.getClass() == Rock.class)
            {
                
                Rock newrock = (Rock) obj;
                
                g2d.setColor(Color.LIGHT_GRAY);
                int[] xPoints = new int[5];
                int[] yPoints = new int[5];
                xPoints[0] = (int)newrock.getX();
                xPoints[1] = (int)newrock.getX() + (int)(newrock.getWidth()/6.0);
                xPoints[2] = (int)newrock.getX() + (int)(newrock.getWidth()/2.0);
                xPoints[3] = (int)newrock.getX() + (int)(newrock.getWidth()*5.0/6.0);
                xPoints[4] = (int)newrock.getX() + (int)newrock.getWidth();
                yPoints[0] = (int)newrock.getY();
                yPoints[1] = (int)newrock.getY() - (int)(newrock.getHeight()/3.0 );
                yPoints[2] = (int)newrock.getY() - (int)(newrock.getHeight()*2.0/3.0);
                yPoints[3] = (int)newrock.getY() - (int)(newrock.getHeight()/3.0);
                yPoints[4] = (int)newrock.getY();
                g2d.fillPolygon(xPoints, yPoints, 5);
            }
            else if(obj.getClass() == Sos.class)
            {
                g2d.setColor(Color.BLACK);
                Sos sos = (Sos) obj;
                g2d.fillOval((int)(sos.getX()-sos.adjustSize(Sos.HEAD_SIZE/2)), (int)(sos.getY()-sos.adjustSize(Sos.HEAD_SIZE)), 
                                (int)sos.adjustSize(Sos.HEAD_SIZE), (int)sos.adjustSize(Sos.HEAD_SIZE));
                g2d.drawLine((int)(sos.getX()), (int)(sos.getY()), (int)(sos.getX()), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE)));
                g2d.drawLine((int)(sos.getX()-sos.adjustSize(Sos.ARMS_SIZE/2)), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE/3)),
                                (int)(sos.getX() + sos.adjustSize(Sos.ARMS_SIZE/2)), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE/3)));
                g2d.drawLine((int)(sos.getX()), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE)),
                                (int)(sos.getX() - sos.adjustSize(Sos.ARMS_SIZE/2)), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE) + sos.adjustSize(Sos.LEG_SIZEY)));
                g2d.drawLine((int)(sos.getX()), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE)),
                                (int)(sos.getX() + sos.adjustSize(Sos.ARMS_SIZE/2)), (int)(sos.getY() + sos.adjustSize(Sos.BODY_SIZE) + sos.adjustSize(Sos.LEG_SIZEY)));
                g2d.setColor(Color.RED);
                g2d.drawString("SOS", (int)(sos.getX()-Sos.HEAD_SIZE/2), (int)(sos.getY() - sos.HEAD_SIZE));
            }
            else if(obj.getClass() == Log.class)
            {
                Log newlog = (Log) obj;
                g2d.setColor(new Color(139, 69, 19));
                g2d.fillRect((int) newlog.getX()+(int)(newlog.getWidth()/10.0), (int)newlog.getY(), (int) (newlog.getWidth()*9.0/10.0), (int) newlog.getHeight());
                g2d.fillRect((int) newlog.getX() + (int)(newlog.getWidth()/3.0), (int) (newlog.getY() - newlog.getHeight()/2.0), (int) (newlog.getWidth()/16.0), (int) (newlog.getHeight()/2.0));
                g2d.setColor(new Color(244, 164, 96));
                g2d.fillOval((int) newlog.getX(), (int) newlog.getY(), (int) (newlog.getWidth()/5.0), (int) newlog.getHeight());
            }
            else if (obj.getClass() == Dash.class)
            {
                Dash newDash = (Dash)obj;
                g2d.setColor(Color.YELLOW);
                g2d.fillRect((int)(newDash.getXPos() - Dash.WIDTH/2), (int)(newDash.getYPos() - Dash.HEIGHT/2), 
                                (int)Dash.WIDTH,(int)Dash.HEIGHT);
            }
            
        }
        
        
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getActionCommand().equals("Green"))
        {
            colorLabel.setText("Car " + carSelection + " is green.");
            colors[carSelection-1] = Color.GREEN;
        }
        else if (ae.getActionCommand().equals("Blue"))
        {
            colorLabel.setText("Car " + carSelection + " is blue.");
            colors[carSelection-1] = Color.BLUE;
        }
        else if (ae.getActionCommand().equals("Red"))
        {
            colorLabel.setText("Car " + carSelection + " is red.");
            colors[carSelection - 1] = Color.RED;
        }
        else if (ae.getActionCommand().equals("Yellow"))
        {
            colorLabel.setText("Car " + carSelection + " is yellow.");
            colors[carSelection - 1] = Color.YELLOW;
        }
        else if (ae.getActionCommand().equals("               DONE               "))
        {
            names[carSelection-1] = nameBox.getText();
            if (nameBox.getText().equals(""))
            {
                names[carSelection-1] = "CAR #" + ((int)(Math.random() * 10000) + 1);
            }
            Object[] options = {"OK"};
            String message = "This is the car you chose: \n" + 
                             "Name: " + names[carSelection - 1] + "\n" + 
                             "Color: " + printColor(colors[carSelection - 1]);
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
                     
            carSelection++;
            if (carSelection == 3)
            {
                carScreen.dispose();
                carScreen = null;
                levelScreen = new JFrame("Choose Difficulty");
                levelScreen.setSize(500, 100);
                levelScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                levelScreen.setVisible(true);  
            }
            else
            {
                description.setText("                                     Car " + (carSelection) + "                                             ");
                nameBox.setText("");
                colorLabel.setText("Enter color!");
            }
        }
        else if (ae.getActionCommand().equals("Easy"))
        {
            difficultyLevel = 0;
            levelScreen.dispose();
            levelScreen = null;
            initializationComplete = true;
            
            Object[] options = {"OK"};
            String message = "How to Play\n" + names[0] + " uses WASD to move.\n" + names[1] + " uses arrow keys to move.\n"
                                + "Don't hit the obstacles!\nGoal of game: Reach the end (2000 ft) \n   OR live longer than your opponent!\nEnjoy!";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
            
            gameScreen = new JFrame("Play Game!");
            gameScreen.setSize(900, 700);
            gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameScreen.setVisible(true);
            game = new Game(gameScreen, names[0], colors[0], names[1], colors[1], difficultyLevel);
            gameScreen.add(new PlayGame());
        }
        else if (ae.getActionCommand().equals("Normal"))
        {
            difficultyLevel = 1;
            levelScreen.dispose();
            levelScreen = null;
            initializationComplete = true;
            
            Object[] options = {"OK"};
            String message = "How to Play\n" + names[0] + " uses WASD to move.\n" + names[1] + " uses arrow keys to move.\n"
                                + "Don't hit the obstacles!\nGoal of game: Reach the end (2000 ft) \n   OR live longer than your opponent!\nEnjoy!";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
            
            gameScreen = new JFrame("Play Game!");
            gameScreen.setSize(900, 700);
            gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameScreen.setVisible(true);
            game = new Game(gameScreen, names[0], colors[0], names[1], colors[1], difficultyLevel);
            gameScreen.add(new PlayGame());
        }
        else if (ae.getActionCommand().equals("Hard"))
        {
            difficultyLevel = 2;
            levelScreen.dispose();
            levelScreen = null;
            initializationComplete = true;
            
            Object[] options = {"OK"};
            String message = "How to Play\n" + names[0] + " uses WASD to move.\n" + names[1] + " uses arrow keys to move.\n"
                                + "Don't hit the obstacles!\nGoal of game: Reach the end (2000 ft) \n   OR live longer than your opponent!\nEnjoy!";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
            
            gameScreen = new JFrame("Play Game!");
            gameScreen.setSize(900, 700);
            gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameScreen.setVisible(true);
            game = new Game(gameScreen, names[0], colors[0], names[1], colors[1], difficultyLevel);
            gameScreen.add(new PlayGame());
        }
        else if (ae.getActionCommand().equals("Insane"))
        {
            difficultyLevel = 3;
            levelScreen.dispose();
            levelScreen = null;
            initializationComplete = true;
            
            Object[] options = {"OK"};
            String message = "Warning! There will be lag.\nYou have been warned...";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
            
            message = "How to Play\n" + names[0] + " uses WASD to move.\n" + names[1] + " uses arrow keys to move.\n"
                                + "Don't hit the obstacles!\nGoal of game: Reach the end (2000 ft) \n   OR live longer than your opponent!\nEnjoy!";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
             
            gameScreen = new JFrame("Play Game!");
            gameScreen.setSize(900, 700);
            gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameScreen.setVisible(true);
            game = new Game(gameScreen, names[0], colors[0], names[1], colors[1], difficultyLevel);
            gameScreen.add(new PlayGame());
        }
        else if (ae.getActionCommand().equals("Just OP"))
        {
            difficultyLevel = 4;
            levelScreen.dispose();
            levelScreen = null;
            initializationComplete = true;
            
            Object[] options = {"OK"};
            String message = "Warning! There will be lag.\nYou have been warned...";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
            
            message = "How to Play\n" + names[0] + " uses WASD to move.\n" + names[1] + " uses arrow keys to move.\n"
                                + "Don't hit the obstacles!\nGoal of game: Reach the end (2000 ft) \n   OR live longer than your opponent!\nEnjoy!";
            JOptionPane.showOptionDialog(null, message, "To Clarify:",
                     JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
                     null, options, options[0]);
            
            gameScreen = new JFrame("Play Game!");
            gameScreen.setSize(900, 700);
            gameScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            gameScreen.setVisible(true);
            game = new Game(gameScreen, names[0], colors[0], names[1], colors[1], difficultyLevel);
            gameScreen.add(new PlayGame());
        }
    }
    
    private String printColor(Color c)
    {
        if (c == Color.GREEN)
            return "Green";
        else if (c == Color.BLUE)
            return "Blue";
        else if (c == Color.RED)
            return "Red";
        else if (c == Color.YELLOW)
            return "Yellow";
        else if (c == Color.BLACK)
            return "Black";
        else if (c == Color.WHITE)
            return "White";
        else if (c == Color.LIGHT_GRAY)
            return "Light Gray";
        else
            return "WHAT HAVE YOU DONE!?!?";
    }
    
    private static boolean isUpPressed()
    {
        synchronized (PlayGame.class)
        {
            return upPressed;
        }
    }

    private static boolean isDownPressed()
    {
        synchronized (PlayGame.class)
        {
            return downPressed;
        }
    }

    private static boolean isLeftPressed()
    {
        synchronized (PlayGame.class)
        {
            return leftPressed;
        }
    }

    private static boolean isRightPressed()
    {
        synchronized (PlayGame.class)
        {
            return rightPressed;
        }
    }

    private static boolean isWPressed()
    {
        synchronized (PlayGame.class)
        {
            return wPressed;
        }
    }

    private static boolean isAPressed()
    {
        synchronized (PlayGame.class)
        {
            return aPressed;
        }
    }

    private static boolean isSPressed()
    {
        synchronized (PlayGame.class)
        {
            return sPressed;
        }
    }

    private static boolean isDPressed()
    {
        synchronized (PlayGame.class)
        {
            return dPressed;
        }
    }
}