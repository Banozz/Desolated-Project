import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to get an image of the item 'Heart'
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Heart extends Items
{
    public Heart(){
        setImage("hearty1.png"); // Set gambar sesuai item 'hearty1'   
        getImage().scale(getImage().getWidth()*2/3, getImage().getHeight()*2/3);
    }
    
    public void act(){ 
    }
}

