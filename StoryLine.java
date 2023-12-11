import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Class to display the beginning of the story from the game to the screen
 * 
 * AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 * @version (a version number or a date)
 */
public class StoryLine extends World
{
    private String story1 = 
        "            Desolated,\n" +
        "\n"+
        "You have no recollections\n"+
        "of any memory,\n" +
        "all you know is,\n" +
        "to survive and escape.\n";
    private int currentIndex = 0; 
    private boolean textDisplayed = false;
    private GreenfootSound typingSound = new GreenfootSound("TextingStory.mp3");
    private boolean spaceVisible = false;
    private int spaceBlinkCount = 0;
    private int spaceBlinkInterval = 30;
    private int timer = 0;
    private int delay = 10; 
    
    /**
     * Constructor for objects of class StoryLine.
     * 
     */
    public StoryLine(){    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(401, 461, 1);
        typingSound.setVolume(45);
    }
    
    public void act(){
        if (!textDisplayed)
        {
            if (timer % delay == 0 && currentIndex <= story1.length())
            {
                displayTextEffect();
            }
            if (currentIndex > story1.length())
            {
                textDisplayed = true;
                typingSound.stop(); 
                spaceVisible = true;
            }
        }
        if (textDisplayed)
        {
            blinkSpaceText();
        }
        displaySpaceText();
        if (textDisplayed && Greenfoot.isKeyDown("space"))
        {
            Greenfoot.playSound("ScaryOpening.mp3");
            Greenfoot.setWorld(new Maze());
        }
        timer++;
    }
    
    private void displayTextEffect(){
        typingSound.play();
        GreenfootImage background = getBackground();
        background.setColor(Color.WHITE);
        background.setFont(new Font("Times New Roman", 37));
        int x = 10;
        int y = 73;
        background.drawString(story1.substring(0, currentIndex), x, y);
        currentIndex++;
    }
    
    private void blinkSpaceText(){
        spaceBlinkCount++;
        if (spaceBlinkCount >= spaceBlinkInterval)
        {
            spaceVisible = !spaceVisible;
            spaceBlinkCount = 0;
        }
    }
    
    void displaySpaceText(){
        if (spaceVisible)
        {
            showText("[Press Space To Continue]", 201, 375);
        }
        else
        {
            showText("", 201, 375);
        }
    }
}
