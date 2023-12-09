import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Door2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door2 extends Wall
{
    public Door2(){
        GreenfootImage img = new GreenfootImage(Maze.Unit*3, Maze.Unit-1);
        img.setColor(Color.GRAY);
        img.fill();
        setImage(img);
    }
    
    public void act()
    {
        // Add your action code here.
    }
}
