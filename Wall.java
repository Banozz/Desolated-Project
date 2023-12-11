import greenfoot.*;
/**
 * Class to create a wall for the entire game
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Wall extends Actor
{
    /** builds simple image for wall objects */
    public Wall(){
        GreenfootImage img = new GreenfootImage(Maze.Unit, Maze.Unit);
        img.setColor(Color.BLACK);
        img.fill();
        setImage(img);
    }
}