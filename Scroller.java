import greenfoot.*;
/**
 * CLASS: Scroller (extends Object)<br>
 * AUTHOR: danpost (greenfoot.org username)<br>
 * DATE: November 11, 2016<br>
 * MODIFIED: December 22, 2016 (fixed 'scroll' method for limited no-image scrolling)<br>
 *           February 15, 2023 (added an offset at line 96 to compensate for non-scrollable area)<br>
 *             NOTE: when adding offsets at line 95 and 96, the scrolling limits need to be
 *             increased by double the offsets; one for the added area and one for the scrolling
 *             to traverse that added area.  So far, this is the ONLY reason I have come across
 *             that requires this class to be modified.
 * <br>          
 * DESCRIPTION:  This is a support class for a scrolling world.  It contains two constructors;
 * one for unlimited scrolling and one for limited scrolling.  Both constructors have an 'image'
 * parameter.  Because image manipulation can hog up CPU time, it is important to remember that
 * it is better not to have a scrolling background image (having an Actor for the background is
 * probably worse than having the background scroll).  For unlimited scrolling using a background
 * image, the smaller that background image to be tiled, the better.  Making the viewport (the
 * size of the world that is visible) smaller can help in CPU expense, also.  Scrolling worlds
 * should be unbounded, allowing actors to move beyond the visible area.  Ensuring that actors
 * are removed from the world if no longer needed when out of view will help to prevent lag,
 * as well.<br>
 * <br>
 * It is the responsibility of the World object that creates a Scroller object to determine when
 * to scroll and by how much.
 */
public class Scroller
{
    private int cropL, cropR, cropT, cropB;
    
    private World world; // view window world
    private GreenfootImage scrollImage; // scrolling image
    private boolean limited; // flag to indicate whether scrolling is limited or not
    private int scrolledX, scrolledY; // current scrolled distances
    private int wide, high; // if limited, dimensions of scrolling area else of image to wrap
  
    /**
     * This constructor is for an unlimited scrolling world;
     * If 'image' is null, the background will not change; else the given image is wrapped
     * 
     * @param viewWorld the world that scrolling will be performed on
     * @param image the background image that will be tiled, if needed, and wrap with scrolling
     */
    public Scroller(World viewWorld, GreenfootImage image)
    {
        world = viewWorld;
        scrollImage = image;
        if (image != null)
        {
            wide = image.getWidth();
            high = image.getHeight();
        }
        scroll(0, 0); // sets initial background image
    }
  
    /**
     * This constructor is for a limited scrolling world;
     * If 'image' is smaller than the given total scrolling area, it will be tiled
     * If 'image' is null, the background will not change
     * 
     * @param viewWorld the world that scrolling will be performed on
     * @param image the background image that will be tiled, if needed, to fill the scrolling area
     * @param wide the width of the visible area encompassed through scrolling;
     * the given value must be at least equal to the width of 'viewWorld' and
     * is given in world cells (not in pixels)
     * @param high the height of the visible area encompassed through scrolling;
     * the given value must be at least equal to the height of 'viewWorld' and
     * is given in world cells (not in pixels)
     */
    public Scroller(World viewWorld, GreenfootImage image, int wide, int high)
    {
        this.wide = wide;
        this.high = high;
        limited = true;
        world = viewWorld;
        if (image != null)
        {
            // create an image as large as scrolling area; tiled, if needeed
            scrollImage = new GreenfootImage(wide*world.getCellSize(), high*world.getCellSize());
            for (int x=0; x<wide*world.getCellSize(); x+= image.getWidth())
                for (int y=0; y<high*world.getCellSize(); y+=image.getHeight())
                    scrollImage.drawImage(image, x, y);
            // set initial background image
            scroll(0, 0);
        }
    }
  
    /**
     * performs scrolling on 'world' by the given distances along the horizontal and vertical;
     * if 'limited' is false, requested distances are actual scrolling distances;
     * if 'limited' is true, the distances may be adjusted due to the limits of scrolling
     *
     * @param dsx the requested distance to shift everything horizontally
     * @param dsy the requested distance to shift everything vertically
     */
    public void scroll(int dsx, int dsy)
    {
        // adjust scroll amounts and scroll background image
        if (limited)
        {
            // calculate limits of scrolling
            int maxX = wide-world.getWidth()-cropR;
            int maxY = high-world.getHeight()-cropB;
            // apply limits to distances to scroll
            if (scrolledX+dsx < 0) dsx = -scrolledX;
            if (scrolledX+dsx >= maxX) dsx = maxX-scrolledX;
            if (scrolledY+dsy < 0) dsy = -scrolledY;
            if (scrolledY+dsy >= maxY) dsy = maxY-scrolledY;
            // update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            // scroll background image
            if (scrollImage != null)
            {
                world.getBackground().drawImage
                (   
                    scrollImage,
                    -scrolledX*world.getCellSize(),
                    -scrolledY*world.getCellSize()
                );
            }
        }
        else // unlimited image wrapping
        {
            // update scroll positions
            scrolledX += dsx;
            scrolledY += dsy;
            // scroll background image
            if (scrollImage != null)
            {
                // create working variables of scroll positions
                int imageX = scrolledX*world.getCellSize();
                int imageY = scrolledY*world.getCellSize();
                // get equivalent near-zero values for scrolling positioin
                imageX = imageX%wide;
                imageY = imageY%high;
                // adjust position, eliminating any negative values
                if (imageX < 0) imageX += wide;
                if (imageY < 0) imageY += high;
                // create image of appropriate size and tile fill 'scrollImage' onto it
                GreenfootImage hold = new GreenfootImage(scrollImage);
                hold.drawImage(scrollImage, -imageX, -imageY);
                if (imageX > 0) hold.drawImage(scrollImage, wide-imageX, -imageY);
                if (imageY > 0) hold.drawImage(scrollImage, -imageX, high-imageY);
                if (imageX > 0 && imageY > 0)
                    hold.drawImage(scrollImage, wide-imageX, high-imageY);
                // set image to background of 'world'
                world.setBackground(hold);
            }
        }
        // adjust position of all actors (that can move with 'setLocation')
        for (Object obj : world.getObjects(null))
        {
            Actor actor = (Actor) obj;
            actor.setLocation(actor.getX()-dsx, actor.getY()-dsy);
        }
    }
    
    /**
     * setter method for reducing scrolling window from right and bottom edges
     * 
     * @param right the width of right side not used by scrolling background
     * @param bottom the height of bottom side not used by scrolling background
     */
    public void crop(int right, int bottom)
    {
        cropR = right;
        wide += 2*right;
        cropB = bottom;
        high += 2*bottom;
    }
    
    /**
     * getter method for the current total scrolled distance horizontally
     *
     * @return the current total offset of horizontal scrolling
     */
    public int getScrolledX()
    {
        return scrolledX;
    }
  
    /**
     * getter method for the current total scrolled distance vertically
     *
     * @return the current total offset of vertical scrolling
     */
    public int getScrolledY()
    {
        return scrolledY;
    }
}