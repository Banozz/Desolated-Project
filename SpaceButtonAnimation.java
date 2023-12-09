import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the Space Button Animation in 'Home Screen' world
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SpaceButtonAnimation extends Actor
{
    private GreenfootImage spaceButton1 = new GreenfootImage("SpaceButton1.png");
    private GreenfootImage spaceButton2 = new GreenfootImage("SpaceButton2.png");
    private boolean isSpaceButton1 = true;
    private int animationDelay = 45; // Tambahkan penundaan agar animasi lebih lambat

    public SpaceButtonAnimation(){
        // Enlarge the image when initializing
        spaceButton1.scale(spaceButton1.getWidth() * 2, spaceButton1.getHeight() * 2);
        spaceButton2.scale(spaceButton2.getWidth() * 2, spaceButton2.getHeight() * 2);
        setImage(spaceButton1);
    }
    
    public void act(){
        // Ganti antara dua gambar sebagai animasi setiap kali penundaan mencapai 0
        if (animationDelay == 0) {
            if (isSpaceButton1) {
                setImage(spaceButton2);
            } else {
                setImage(spaceButton1);
            }

            isSpaceButton1 = !isSpaceButton1;
            animationDelay = 45; // Atur ulang penundaan
        } else {
            animationDelay--;
        }
    }
}
