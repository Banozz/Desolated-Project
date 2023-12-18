import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to make doors in the game (stage markers)
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Door extends Wall
{
    World world = getWorld();
    public void act(){
    }
    public Door(){
        String imgName = "ironbars.png";
        GreenfootImage img = new GreenfootImage(imgName);
        img.scale(Maze.Unit, Maze.Unit);
        //img.setColor(Color.BLACK);
        //img.fill();
        //setImage(img);
        //GreenfootImage img = new GreenfootImage(Maze.Unit*3, Maze.Unit-1);
        //img.setColor(Color.GRAY);
        //img.fill();
        setImage(img);
    }
}