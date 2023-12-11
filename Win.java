import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the condition where the player has successfully finished the game (winning)
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class Win extends World
{
    private GreenfootImage winImage;
    private Player player;
    private GreenfootSound backsound; // Tambahkan variabel untuk backsound
    private int volume = 80; // Atur volume awal sesuai preferensi Anda (0-100)

    public Win(Player player){    
        super(401, 461, 1);
        winImage = new GreenfootImage("Congratulation.png"); // Gantilah "win_image.png" dengan nama file gambar yang sesuai
        setBackground(winImage);
        this.player = player;
        
        // Inisialisasi backsound dengan file audio Congrats.mp3
        backsound = new GreenfootSound("Congrats.mp3");
        backsound.setVolume(volume); // Atur volume awal
    }
    
    public void act(){
        if (Greenfoot.isKeyDown("enter")) {
            player.stopped();
            // Kembali ke Home saat Enter ditekan
            Greenfoot.setWorld(new Home());
            Greenfoot.stop();
        }
        // Putar backsound jika belum dimulai
        if (!backsound.isPlaying()) {
            backsound.play();
        }
    }
}
