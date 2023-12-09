import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to get the "Key" item that will be used in the game
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Key extends Items
{
    public Key(){
        int width = getImage().getWidth()*9/10;
        int height = getImage().getHeight()*9/10;
        getImage().scale(width, height);
    }
}