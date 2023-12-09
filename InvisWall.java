import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to create a wall in the second stage of the game
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class InvisWall extends Wall
{
    public InvisWall(){
        GreenfootImage img = new GreenfootImage(Maze.Unit, Maze.Unit);
        img.setColor(Color.WHITE);
        img.fill();
        setImage(img);
    }
}
