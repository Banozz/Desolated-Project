import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class for items that the 'Player' will interact with
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Items extends Actor
{
    private boolean[] isTaken; // Menyimpan status apakah item sudah diambil
    private boolean taken = false;
    
    public Items(){
        isTaken = new boolean[5];
    }
    public void act(){
        // Add your action code here.
    }
    public boolean isTaken(){
        return taken;
    }
    public void setTaken(boolean taken){
        this.taken = taken;
    }
}
