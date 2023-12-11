import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the game over
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class GameOver extends Actor
{
    private GreenfootSound bgm3;
    private GreenfootSound bgm4;
    public GameOver(){
        // Set gambar untuk GameOver sesuai kebutuhan Anda
        GreenfootImage image = new GreenfootImage("GameOver.png"); // Ganti "game_over.png" dengan nama gambar yang sesuai
        setImage(image);
        
        // Inisialisasi objek GreenfootSound untuk dua lagu
        bgm3 = new GreenfootSound("GameOver3.mp3"); // Ganti dengan nama file mp3 pertama
        bgm4 = new GreenfootSound("LittleGirl.mp3"); // Ganti dengan nama file mp3 kedua
        
        // Set volume awal untuk kedua musik
        bgm3.setVolume(50); // Atur volume musik pertama (0-100)
        bgm4.setVolume(95); // Atur volume musik kedua (0-100)

        // Memulai kedua musik secara bersamaan
        bgm3.playLoop();
        bgm4.play();
    }
    
    public void act(){
        // Tambahkan logika untuk menanggapi input dari pemain
        if (Greenfoot.isKeyDown("y") || Greenfoot.isKeyDown("Y")) {
            // Hentikan musik dan mulai permainan baru
            stopMusic();
            
            // Jika pemain menekan 'y' atau 'Y', maka mulai permainan baru
            Greenfoot.playSound("ScaryOpening.mp3");
            Greenfoot.setWorld(new Maze());
        } else if (Greenfoot.isKeyDown("n") || Greenfoot.isKeyDown("N")) {
            // Hentikan musik dan mulai permainan baru
            stopMusic();
            
            // Jika pemain menekan 'n' atau 'N', maka keluar dari permainan dan kembali ke Home
            // Ganti dunia ke HomeScreen dan mematikan Greenfoot
            Greenfoot.setWorld(new Home());
            Greenfoot.stop();
        }
    }
    
    private void stopMusic() {
        // Hentikan kedua musik
        bgm3.stop();
        bgm4.stop();
    }
}
