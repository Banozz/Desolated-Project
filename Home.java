import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the intro of the game to the screen
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Home extends World
{
    GreenfootSound bgm1 = new GreenfootSound("Welcoming.mp3");
    //GreenfootSound bgm2 = new GreenfootSound("InMaze.mp3"); // Ganti dengan nama berkas musik berikutnya
    boolean isBGMPlaying = false; // Menandai apakah latar belakang musik sedang dimainkan
    // Animasi SpaceButton
    private SpaceButtonAnimation spaceButtonAnimation;
    
    public Home(){    
        // Create a new world with 401 x 461 cells with a cell size of 1x1 pixels.
        super(401, 461, 1);
        addObject(new HomeScreen(),201,215);
        addObject(new EnterKey(), 198,355);
        // Tambahkan animasi SpaceButton
        spaceButtonAnimation = new SpaceButtonAnimation();
        addObject(spaceButtonAnimation, 198, 355);
    }
    
    public void act(){
        if (Greenfoot.isKeyDown("space"))
        {
            // Hentikan musik latar belakang yang sedang dimainkan
            if (isBGMPlaying) {
                bgm1.stop();
                //bgm2.stop();
            }
            Greenfoot.setWorld(new StoryLine());
        }
        // Periksa apakah latar belakang musik sedang dimainkan, jika tidak, putar musik pertama
        if (!isBGMPlaying) {
            bgm1.play();
            bgm1.setVolume(35);
            isBGMPlaying = true;
        }
        // Update animasi SpaceButton
        spaceButtonAnimation.act();
    }
}
