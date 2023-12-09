import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Information here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Information extends Actor
{
    /**
     * Act - do whatever the Information wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Information(String text){
        GreenfootImage img = new GreenfootImage(text.length()*20,25);
        img.drawString(text,2,20);
        setImage(img);
    }
    public void act(){
        // Add your action code here.
    }
}
