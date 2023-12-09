import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the game home screen 2
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnterKey extends Actor
{
    /**
     * Act - do whatever the EnterKey wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
        GreenfootImage image = getImage();
        image.scale(401, 461);
        setImage(image);
    }
}
