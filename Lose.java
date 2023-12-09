import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the condition, where the player loses the game (game over)
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Lose extends World
{
    public Lose(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(401, 461, 1); 
        
        // Tambahkan objek GameOver ke dunia Lose
        addObject(new GameOver(), getWidth() / 2, getHeight() / 2);
    }
}
