import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the game home screen
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class HomeScreen extends Actor
{
    /**
     * Act - do whatever the HomeScreen wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        GreenfootImage image = getImage();
        image.scale(401, 461);
        setImage(image);
    }
}
