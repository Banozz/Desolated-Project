import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to get the Arrow Key that will be displayed in the 'Maze' world
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class ArrowKey extends Actor
{
    private GreenfootImage arrowKey1 = new GreenfootImage("ArrowKey1.png");
    private GreenfootImage arrowKey2 = new GreenfootImage("ArrowKey2.png");
    private boolean isArrowKey1 = true;
    private int animationDelay = 45;
    
    public ArrowKey(){
        // Enlarge the image when initializing
        arrowKey1.scale(arrowKey1.getWidth() * 2, arrowKey1.getHeight() * 2);
        arrowKey2.scale(arrowKey2.getWidth() * 2, arrowKey2.getHeight() * 2);
        setImage(arrowKey1);
    }
    
    public void act(){
        // Ganti antara dua gambar sebagai animasi setiap kali penundaan mencapai 0
        if (animationDelay == 0) {
            if (isArrowKey1) {
                setImage(arrowKey2);
            } else {
                setImage(arrowKey1);
            }

            isArrowKey1 = !isArrowKey1;
            animationDelay = 45; // Atur ulang penundaan
        } else {
            animationDelay--;
        }
    }
}
