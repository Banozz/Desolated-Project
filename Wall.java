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
        String imgName = "Brick_11-128x128.png";
        GreenfootImage img = new GreenfootImage(imgName);
        img.scale(Maze.Unit, Maze.Unit);
        //img.setColor(Color.BLACK);
        //img.fill();
        setImage(img);
    }
}