import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class for the obstacles in the game
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Obstacle extends Actor
{
    private int speed = 2;
    private String direction;
    private String imageFile;
    
    public Obstacle(String direction){
        this.direction = direction;
        setImageForDirection(direction);
    }
    
    private void setImageForDirection(String direction){
        if (direction.equals("UP")) {
            imageFile = "monsterUP.png";
        } else if (direction.equals("DOWN")) {
            imageFile = "monsterDOWN.png";
        } else if (direction.equals("LEFT")) {
            imageFile = "monsterLEFT.png";
        } else if (direction.equals("RIGHT")) {
            imageFile = "monsterRIGHT.png";
        }
        setImage(imageFile);
    }
    
    public void act(){
        // Move the obstacle based on its direction
        int x = getX();
        int y = getY();
        
        if (direction.equals("UP")) {
            setLocation(x, y - speed);
        } else if (direction.equals("DOWN")) {
            setLocation(x, y + speed);
        } else if (direction.equals("LEFT")) {
            setLocation(x - speed, y);
        } else if (direction.equals("RIGHT")) {
            setLocation(x + speed, y);
        }
        
        // Check if the obstacle has collided with the player
        if (isTouching(Player.class)) {
            // Game Over logic (you can customize this part)
            Greenfoot.setWorld(new Lose());
        }
        
        // Check if the obstacle is out of the world and should be removed
        if (isTouching(Wall.class)) {
            // Obstacle has hit a wall, reverse its direction
            if (direction.equals("UP")) {
                direction = "DOWN";
                setImageForDirection(direction);
            } else if (direction.equals("DOWN")) {
                direction = "UP";
                setImageForDirection(direction);
            } else if (direction.equals("LEFT")) {
                direction = "RIGHT";
                setImageForDirection(direction);
            } else if (direction.equals("RIGHT")) {
                direction = "LEFT";
                setImageForDirection(direction);
            }
        }
    }   
}
