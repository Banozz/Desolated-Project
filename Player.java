import greenfoot.*;
import java.util.List;
/** an actor whose immediate surrounding area is lit only
  * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 */

public class Player extends Actor
{
    public static int score;
    public static int keys;
    public static int battLevel;
    public static int doorKey;
    public static boolean hasHeart = false;
    private Light light; // see Light class below in this class
    private Actor xDisplay, yDisplay;
    private int lastDx, lastDy; // to retain movement of last act step (for fluid turning)
    private int animationFrame = 0; // Untuk mengontrol animasi
    private int itemsCollected;
    private GreenfootSound heartbeatSound = new GreenfootSound("HeartbeatUp.mp3");
    int speed = 1;

    private Arrowkeys arrowkeys;
    
    // Gambar animasi untuk masing-masing arah gerakan
    private GreenfootImage[] upImages = new GreenfootImage[7];
    private GreenfootImage[] rightImages = new GreenfootImage[7];
    private GreenfootImage[] downImages = new GreenfootImage[7];
    private GreenfootImage[] leftImages = new GreenfootImage[7];
    
    public Player(){
        itemsCollected = 0;
        // Load gambar animasi untuk masing-masing arah gerakan
        for (int i = 0; i < 7; i++) {
            upImages[i] = new GreenfootImage("moveUP" + (i + 1) + ".png");
            rightImages[i] = new GreenfootImage("moveRIGHT" + (i + 1) + ".png");
            downImages[i] = new GreenfootImage("moveDOWN" + (i + 1) + ".png");
            leftImages[i] = new GreenfootImage("moveLEFT" + (i + 1) + ".png");
        }
        setImage(upImages[0]); // Atur gambar awal
        score = 0;
        keys = 0;
        battLevel = 0;
    }
    
    /** adds light around this player and adds and initializes coordinate displaying actors */
    protected void addedToWorld(World world){
        setLightRange(500);
        xDisplay = new Element();
        world.addObject(xDisplay, 45, 28);
        yDisplay = new Element();
        world.addObject(yDisplay, 51, 53);
        updateLocDisplay();
    }
    
    // Metode untuk mengubah gambar berdasarkan arah gerakan
    private void setAnimationImage(int dx, int dy) {
        if (dx > 0) {
            setImage(rightImages[animationFrame]);
        } else if (dx < 0) {
            setImage(leftImages[animationFrame]);
        } else if (dy > 0) {
            setImage(downImages[animationFrame]);
        } else if (dy < 0) {
            setImage(upImages[animationFrame]);
        }
    }
    
    private void updateLocDisplay(){
        GreenfootImage img = new GreenfootImage("X:  "+(getX()+((Maze)getWorld()).scroller.getScrolledX()), 24, Color.WHITE, Color.BLACK);
        xDisplay.setImage(img);
        img = new GreenfootImage("Y:  "+(getY()+((Maze)getWorld()).scroller.getScrolledY()), 24, Color.WHITE, Color.BLACK);
        yDisplay.setImage(img);
    }
    
    // Metode untuk memainkan animasi
    private void playAnimation() {
        animationFrame = (animationFrame + 1) % 7; // Change 3 to 7 for 7 frames
    }
    
    public void HitDoor(){
        Door door = (Door)getOneIntersectingObject(Door.class);
        Door2 door2 = (Door2)getOneIntersectingObject(Door2.class);
        Door3 door3 = (Door3)getOneIntersectingObject(Door3.class);
        if(keys == 1 && door != null){
            score = 1;
            getWorld().removeObject(door);
        } else if (keys == 2 && door2 != null){
            score = 3;
            getWorld().removeObject(door2);
            stopped(); // Stop the heartbeat sound
        } else if (keys == 3 && door3 != null){
            score = 4;
            getWorld().removeObject(door3);
        }
    }
    
    public void HitItems(){
        // Check hit items
        Items heart = (Heart)getOneIntersectingObject(Heart.class);
        Items battery = (Battery)getOneIntersectingObject(Battery.class);
        Items key = (Key)getOneIntersectingObject(Key.class);
        Items key2 = (Key2)getOneIntersectingObject(Key2.class);
        Items key3 = (Key3)getOneIntersectingObject(Key3.class);
        if (heart != null && !heart.isTaken()) {
            heart.setTaken(true);
            getWorld().removeObject(heart);
            removeMonsters();
            // Set the volume and loop the sound
            heartbeatSound.setVolume(57); // Adjust the volume (0-100)
            heartbeatSound.playLoop(); // Loop the sound
            score = 2;
        } else if (battery != null && !battery.isTaken()) {
            battery.setTaken(true);
            getWorld().removeObject(battery);
            setLightRange(100);
            battery.setTaken(true);
            battLevel = 1;
        } else if(key != null && !key.isTaken()){
            key.setTaken(true);
            getWorld().removeObject(key);
            keys = 1;
        } else if(key2 != null && !key2.isTaken()){ 
            key2.setTaken(true);
            getWorld().removeObject(key2);
            keys = 2;
        } else if(key3 != null && !key3.isTaken()){
            key3.setTaken(true);
            getWorld().removeObject(key3);
            keys = 3;
        }
        // To remove arrow keys after tutorial
        if(battLevel == 1 || score >= 1 && score <= 3){
            getWorld().removeObject(arrowkeys);
        }
    }
    
    // Method to remove all Monsters from the world
    private void removeMonsters() {
        Maze maze = (Maze) getWorld();
        List<Monster> monsters = maze.getObjects(Monster.class);
        for (Monster monster : monsters) {
            maze.removeObject(monster);
        }
    }
    
    public int getItemsCollected(){
        return itemsCollected;
    }
    
    public void act(){
        int dx = 0, dy = 0;
        if (Greenfoot.isKeyDown("right")){
            setLocation(getX()+speed, getY());
            dx++;
            if (getOneObjectAtOffset(dx*(Maze.Unit/2+1+speed), 0, Wall.class) != null){
                dx = 0;
                setLocation(getX()-speed, getY());
            }
        }
        if (Greenfoot.isKeyDown("left")){ 
            setLocation(getX()-speed,getY());
            dx--;
            if (getOneObjectAtOffset(dx*(Maze.Unit/2+1+speed), 0, Wall.class) != null){
                dx = 0;
                setLocation(getX()+speed, getY());
            }

        }
        // Check vertical movement keys
        if (Greenfoot.isKeyDown("down")){
            setLocation(getX(),getY()+speed);
            dy++;
            if (getOneObjectAtOffset(0, dy*(Maze.Unit/2+1+speed), Wall.class) != null){
                dy = 0;
                setLocation(getX(), getY()-speed);
            }
        }
        if (Greenfoot.isKeyDown("up")){
            setLocation(getX(),getY()-speed);
            dy--;
            if (getOneObjectAtOffset(0, dy*(Maze.Unit/2+1+speed), Wall.class) != null){
                dy = 0;
                setLocation(getX(), getY()+speed);
            }

        }
        // Set gambar animasi berdasarkan arah gerakan
        setAnimationImage(dx, dy);
        // Mainkan animasi
        playAnimation();
        
        if (arrowkeys != null) {
            getWorld().removeObject(arrowkeys);
        }

        // move with Arrow Keys
        arrowkeys = new Arrowkeys();
        getWorld().addObject(arrowkeys, getX(), getY()-40);
        
        // move with light
        setLocation(getX()+dx, getY()+dy);
        light.setLocation(getX(), getY());
        // retain movement for determining turns on next act step
        lastDx = dx;
        lastDy = dy;
        // update location display
        updateLocDisplay();
        
        // get an items
        HitItems();
        HitDoor();
    }
    
    // Stopping the Heartbeat Sound (BELUM KEPAKE) -> BISA DI WORLD
    public void stopped(){
        heartbeatSound.stop();
    }
    
    /**
     * has actor illuminate a given distance
     * 
     * @param range radius of illumination around this actor
     */
    public void setLightRange(int range){
        if (light != null) getWorld().removeObject(light);
        light = new Light(range);
        getWorld().addObject(light, getX(), getY());
    }
    
    /**
     * the light source (belongs to Player object)
     * NOTE: the full size of image is set to 600 for this particular scenario
     * (a future update may allow the full size to be given via a constructor parameter)
     */
    public class Light extends Actor{
        /** 
         * constructs image of illumination around source
         * 
         * @param radius the radius of the illuminated area
         */
        public Light(int radius)
        {
            setRange(radius);
        }
        
        /**
         * sets the range from source of illumination (strength of light)
         * 
         * @param radius the radius of the illuminated area
         */
        public void setRange(int radius)
        {
            // build top half of image and totally fill with black (darkness)
            GreenfootImage half = new GreenfootImage(2*radius, radius);
            half.fill();
            // add transparencies that become more opaque as the distance from a soon-to-be "center" point increases;
            // center point in top half image is at center of bottom edge of the image
            for (int y=0; y<radius;y++) 
            {
                for (int x=0; x<2*radius; x++)
                {
                    int dist = (int)Math.hypot(x-radius, y-radius);
                    if (dist < radius)
                    {
                        Color color = new Color(0, 0, 0, 256*dist/radius);
                        half.setColorAt(x, y, color);
                    }
                }
            }
            // build actual light source image
            int fullW = 800, fullH = 800; // full range of darkness required for this scenario
            GreenfootImage img = new GreenfootImage(fullW, fullH);
            // add darkness along the edges of image
            img.fillRect(0, 0, fullW, fullH/2-radius);
            img.fillRect(0, 0, fullW/2-radius, fullH);
            img.fillRect(0, fullH/2+radius, fullW, fullH/2-radius);
            img.fillRect(fullW/2+radius, 0, fullW/2-radius, fullH);
            // draw the half image onto actual image (twice -- once mirrored)
            img.drawImage(half, fullW/2-radius, fullH/2-radius);
            half.mirrorVertically();
            img.drawImage(half, fullW/2-radius, fullH/2);
            // set image to actor
            setImage(img);
        }
    }
    public class Arrowkeys extends Actor{
        private GreenfootImage arrowKey1 = new GreenfootImage("ArrowKey1.png");

        public Arrowkeys(){
            arrowKey1.scale(arrowKey1.getWidth() * 1, arrowKey1.getHeight() * 1);
            setImage(arrowKey1);
            act();
        }

        public void act(){

        }
    }
}