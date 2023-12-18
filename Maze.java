import greenfoot.*;
import java.util.Random;
/**
 *  TITLE: Desolated
 *  AUTHOR: Elvizto Juan Khresnanda & Ibrahim Nur Huda
 *  DATE:  October .., 2023
 *  
 */

public class Maze extends World
{
    public static final int MazeWidth = 30, MazeHeight = 20, Unit = 30; // constants
    
    public Scroller scroller;
    public Player player;
    private int countdown; // Add a countdown timer variable
    private int countdown2;
    private boolean gameOver; // Variable to track game over state
    private GreenfootSound bgm2;
    public static Door door11, door12, door13;
    public static Door2 door2;
    public static Door3 door3;
    public static Battery battery;
    public static Key key;
    public static Key2 key2;
    public static Key3 key3;
    public static Heart heart;
    
    // Animasi SpaceButton
    private ArrowKey arrowKey;
    
    /** build "universe" */
    public Maze(){
        super(401, 461, 1, false);
        setPaintOrder(Element.class, Player.Light.class);
        Element panel = new Element();
        GreenfootImage img = new GreenfootImage(getWidth(), 60);
        img.setColor(Color.GRAY);
        img.fill();
        panel.setImage(img);
        addObject(panel, getWidth()/2, getHeight()-30);
        scroller = new Scroller(this, null, (MazeWidth*2+1)*Unit+1, (MazeHeight*2+1)*Unit+1);
        scroller.crop(0, 60);
        createMaze();
        player = new Player();
        addObject(player, 1050, 990);
        scroll();
        
        // Making countdown timer
        countdown = 50 * 60; // 45 seconds converted to frames
        countdown2 = 250 * 60;
           
        // Putar musik latar belakang kedua ketika permainan dimulai
        bgm2 = new GreenfootSound("InMaze.mp3");
        bgm2.setVolume(20); // Sesuaikan volume jika perlu
        bgm2.playLoop(); // Putar musik dalam loop
    }
    
    /** controls scrolling */
    public void act(){
        if (Player.score == 1) {
            // Decrement the countdown timer
            showText("Find Your Heart.", 315, 28);
            showText("Time Left: " + (countdown / 60) + "s", 310, 428); // Display time left
            showText("Keep Running.", 105,428);
            countdown--;
            if(countdown == 0){
                // Player has collected 5 items, they win
                bgm2.stop();
                Greenfoot.setWorld(new Lose()); // Notify game over with "Congratulations"
            }
        } else if (Player.score == 2){
            countdown = 0;
            showText("      Find Your Way Back.", 105,428);
            showText("", 315, 28);
            showText("", 310, 428);
            showText("      Find The Door.", 300, 25);
        } else if(Player.score == 3){
            showText("Keep It Straight.", 105,428);
            showText("", 315, 28);
            showText("", 310, 428);
            showText("", 300, 25);
            showText("Find The Key.", 315, 28);
            showText("Time Left: " + (countdown2 / 60) + "s", 310, 428);
            countdown2--;
            if(countdown2 == 0){
                // Player has collected 5 items, they win
                bgm2.stop();
                Greenfoot.setWorld(new Lose()); // Notify game over with "Congratulations"
            } else if (Player.keys == 3){
                showText("", 315, 28);
                showText("Find The Exit Door.", 305, 28);
            }
        } else if(Player.score == 4){
            bgm2.stop();
            Greenfoot.setWorld(new Win(player));
        }
        scroll(); // Using scroll class that has been made before   
    }
    
    private void showGameOver(int score){
        if (score == 2) {
            //Greenfoot.setWorld(new Win(player));
        } else {
            // Ganti dunia ke Lose saat pemain kalah
            Greenfoot.setWorld(new Lose());
        }
    }
    
    /** maintains player in center of world when possible */
    private void scroll(){
        // determine horizontal and vertical offsets of player from center of world
        int dx = player.getX()- getWidth()/2;
        int dy = player.getY()-(getHeight()-60)/2;
        // scroll to limits of "universe", while attempting to place player back in center of world
        scroller.scroll(dx, dy);
    }
    
    /** creates the initial structure of a maze */
    private void createMaze(){
        door11 = new Door(); 
        door12 = new Door();
        door13 = new Door();
        addObject(door11, 1125, 1035);
        addObject(door12, 1125, 1065);
        addObject(door13, 1125, 1095);
        door11.setRotation(90); door12.setRotation(90); door13.setRotation(90);
        door2 = new Door2();
        addObject(door2,885,915);
        door3 = new Door3();
        addObject(door3, 645, 1065);
        door3.setRotation(90);
        battery = new Battery();
        addObject(battery, 1000, 1000);
        heart = new Heart();
        addObject(heart, 1705, 105);
        key = new Key();
        addObject(key, 685, 960);
        key2 = new Key2();
        addObject(key2, 1780, 50);
        key3 = new Key3();
        addObject(key3, 885, 65);
        
        showText("Use Arrow to Move", 105,428);
        
        // Tambahkan 4 obstacle pada posisi yang berbeda
        //addObject(new Obstacle("UP"), 200, 200);
        addObject(new Obstacle("DOWN"), 1670, 500);
        addObject(new Obstacle("LEFT"), 1505, 880);
        addObject(new Obstacle("RIGHT"), 1250, 77);
        
        // Tambahkan dua monster ke dalam dunia Maze
        Monster monsterRight = new Monster("monsterRIGHT.png");
        addObject(monsterRight, 1485, 973); // Atur koordinat sesuai keinginan

        Monster monsterLeft = new Monster("monsterLEFT.png");
        addObject(monsterLeft, 1575, 134); // Atur koordinat sesuai keinginan
        
        char[][] maze = new char[MazeHeight*2+1][MazeWidth*2+1]; // for map of the maze
        char[][] maze2 = new char[MazeHeight*2+1][MazeWidth*2+1]; // for map of the invisible maze
        // represent walls at top and bottom edges of maze
        for (int x=0; x<MazeWidth*2+1; x++)
        {
            maze[0][x] = 'w';
            maze[MazeHeight*2][x] = 'w';
        }
        // represent walls at left and right edges of maze
        for (int y=0; y<MazeHeight*2+1; y++)
        {
            maze[y][0] = 'w';
            maze[y][MazeWidth*2] = 'w';
        }
        // represent walls at all possible wall crossings in central area of maze
        for (int y=2; y<MazeHeight*2; y+=2) 
            for (int x=2; x<MazeWidth*2; x+=2) {
                //maze[y][x] = 'w';
            }
        Random random = new Random();
        
        for (int x = 22; x <= 36; x++) maze[30][x] = 'w'; // y = 910 (horizontal wall)
        for (int y = 30; y <= MazeHeight*2; y++){maze[y][21] = 'w'; maze[y][37] = 'w';}// x = 705 & 1065 (vertical wall)
        
        // Sector 1 of stage 1
        for (int x = 37; x <= MazeWidth*2-6; x++)
            for(int y = 30; y <= MazeHeight*2-1; y++){

                maze[y][x] = 'w';
                maze[32][55] = 'w'; maze[31][55] = 'w';
                maze[33][55] = 'w'; maze[33][56] = 'w';
                maze[32][49] = ' ';

                if(x >= 38 && x<=MazeWidth*2-6 && y >= 34 && y <= 38){
                    maze[y][x] = ' ';
                    // Boulder 1
                    maze[34][41] = 'w'; maze[34][40] = 'w'; maze[34][42] = 'w'; 
                    maze[35][41] = 'w'; maze[35][40] = 'w'; 
                    maze[36][41] = 'w'; 

                    // Boulder 2
                    maze[36][45] = 'w'; maze[36][46] = 'w';
                    maze[37][45] = 'w'; maze[37][46] = 'w';
                    maze[38][44] = 'w'; maze[38][45] = 'w'; maze[38][46] = 'w';
                }

                if(x > 46 && x <= MazeWidth*2-7 && y >= 36 && y <= 38){
                    maze[y][x] = 'w';
                }
                
                maze[38][53] = ' ';
                maze[39][51] = ' '; maze[39][52] = ' '; maze[39][53] = ' '; maze[39][54] = ' ';
                
                maze[37][59] = 'w'; maze[38][59] = 'w';
                maze[39][58] = 'w'; maze[39][59] = 'w';
            }
        
        //More obstacles for sector 1 of stage 1
        for(int y = 11; y <= 16; y++) {
            maze[y][54] = 'w'; maze[y][55] = 'w';
            maze[16][55] = ' ';
        }
        for(int y = 15; y <= 29; y++) maze[y][59] = 'w';
        for(int y = 16; y <= 27; y++) maze[y][58] = 'w';
        for(int y = 19; y <= 24; y++) maze[y][57] = 'w';
        for (int x = 53; x <= MazeWidth*2-1; x++) maze[6][x] = 'w';  
        
        // Sector 2 & 3 of stage 1
        for (int x = 40; x <= MazeWidth*2-7 ; x++)
            for(int y = 1 ; y <= 29; y++){
                maze[y][x] = 'w';
                
                //maze[25][49] = ' '; maze[25][50] = ' ';
                if(y > 7 && y <= 10 && x >= 51 && x <= 54) {
                    maze[y][x] = ' ';
                }
                
                if(y > 7 && y <= 29 && x >= 48 && x <= 50){
                    maze[y][x] = ' '; // Making hall 1
                    
                    if(y > 10 && y <= 25 && x >= 49 && x <= 50 && y%4 == 1 ){
                        maze[y][x] = 'w';
                    }
                    
                    maze[15][48] = 'w'; maze[15][49] = 'w';
                    maze[19][48] = 'w'; maze[19][49] = 'w';
                    maze[23][48] = 'w'; maze[23][50] = 'w';
                    maze[25][48] = 'w'; maze[25][50] = ' ';
                    maze[27][48] = 'w'; maze[27][50] = 'w';
                }
                
                //U turn          
                if(y > 27 && y <= 29 && x >= 45 && x <= 47){
                    maze[y][x] = ' ';
                }
                
                // Hall 2
                if(y > 1 && y <= 29 && x >= 41 && x <= 45){
                    maze[y][x] = ' ';
                }
                
                if(y > 4 && y <= 27 && y%4 == 1){
                    maze[y][41] = 'w'; maze[y][43] = 'w'; maze[y][45] = 'w';
                }
                
                if(y > 4 && y <= 27 && y%4 == 1){
                    maze[y+2][42] = 'w'; maze[y+2][44] = 'w'; //maze[y][45] = 'w';
                }
                
                if(x >= 46 && x <=53){
                    maze[2][x] = ' ';
                    maze[4][x-1] = ' ';
                }
                
                maze[4][51] = 'w';
                maze[11][51] = ' '; 
            }
        
        // Stage 2
        for(int x = 19; x<= 39; x++){
            for(int y = 1; y <= 28; y++){
                maze2[y][x] = 'w';

                if(y >= 1 && y <= 28){
                    maze2[y][28] = ' '; maze2[y][29] = ' '; maze2[y][30] = ' ';
                }
                
                if(y >= 2 && y <= 8)maze2[y][20] = ' '; 
                if(x >= 20 && x <= 25)maze2[2][x] = ' ';
                if(x >= 20 && x <= 25)maze2[4][x] = ' ';
                if(x >= 20 && x <= 25)maze2[6][x] = ' '; 
                
                if(y >= 2 && y <= 13)maze2[y][38] = ' '; maze2[9][20] = ' ';
                if(x >= 32 && x <= 37)maze2[2][x] = ' '; maze2[3][33] = ' ';
                if(x >= 32 && x <= 36)maze2[4][x] = ' '; maze2[3][32] = ' ';
                if(y >= 4 && y <= 9)maze2[y][36] = ' ';
                if(x >= 31 && x <= 36)maze2[9][x] = ' ';
                maze2[8][29] = 'w';
                maze2[10][28] = 'w'; maze2[10][30] = 'w';
                
                maze2[15][27] = ' ';maze2[14][27] = ' ';
                maze2[15][28] = 'w';maze2[15][29] = 'w';maze2[15][30] = ' ';
                maze2[20][28] = 'w';maze2[20][29] = 'w';maze2[20][30] = 'w';
                                    maze2[21][29] = 'w';
                
                maze2[17][21] = ' ';
                maze2[18][21] = ' ';
                if(x >= 22 && x <= 27)maze2[9][x] = ' '; maze2[9][20] = ' ';
                if(x >= 21 && x <= 27)maze2[11][x] = ' ';maze2[10][20] = ' '; maze2[11][20] = ' ';
                if(x >= 21 && x <= 26){
                    maze2[14][x] = ' '; maze2[14][23] = 'w';
                    maze2[15][x] = ' '; maze2[15][23] = 'w';
                    maze2[16][x] = ' ';
                    
                    
                    maze2[15][27] = 'w';
                }
                if(x >= 31 && x <= 38){
                    maze2[14][x] = ' '; maze2[20][31] = ' ';
                    maze2[15][x] = ' ';
                    maze2[16][x] = ' ';
                }
                
                if(x >= 21 && x <= 26)maze2[19][x] = ' '; maze2[20][21] = ' ';
                if(x >= 21 && x <= 27)maze2[21][x] = ' ';
                if(x >= 31 && x <= 38)maze2[19][x] = ' '; maze2[20][31] = ' ';
                if(x >= 31 && x <= 38)maze2[21][x] = ' ';
                
                if(x >= 20 && x <= 27)maze2[25][x] = ' '; maze2[26][20] = ' ';
                if(x >= 20 && x <= 27)maze2[27][x] = ' ';
                if(x >= 31 && x <= 38)maze2[25][x] = ' '; maze2[26][38] = ' ';
                if(x >= 31 && x <= 38)maze2[27][x] = ' ';
                
                maze2[26][29] = 'w'; maze2[26][30] = 'w';
                maze2[29][27] = 'w'; maze2[29][31] = 'w';
                
                maze2[16][27] = ' ';
                //maze2[19][29] = ' '; maze2[20][29] = ' ';
                //maze2[19][30] = ' '; maze2[20][30] = ' ';
            }
        }
        
        // add walls to world ("universe") according to map
        for (int y=0; y<MazeHeight*2+1; y++) 
            for (int x=0; x<MazeWidth*2+1; x++){
                
                //Minor addition to sector 1 of stage 1
                maze[7][57] = 'w'; maze[7][58] = 'w'; maze[7][59] = 'w';
                maze[8][59] = 'w';
                
                if (y <= 36 && y >= 34 && maze[y][x] == 'w'){
                    maze[y][37] = ' '; //For gate to stage 1
                    maze[y][21] = ' '; //For gate to stage 3
                }

                if (x >= 28 && x <= 30 && maze[y][x] == 'w'){
                    maze[30][x] = ' '; // For gate to stage 2
                }
                /*
                if(x == 4 && y <= 5 && maze[y][x] != 'w'){
                maze[y][x] = 'w';
                }
                //if(y == 35 && x > 23 && x <= 35 && maze[y][x] != 'w')
                if(x == 35 && y >= 35 && y <= MazeHeight*2){
                //maze[y][x] = 'w';
                }*/
                //if(x == 23 && y >= 35 && y <= MazeHeight*2)
                if (maze[y][x] == 'w')
                    addObject(new Wall(), Unit*x+Unit/2+scroller.getScrolledX(), Unit*y+Unit/2+scroller.getScrolledY());
                if (maze2[y][x] == 'w')
                    addObject(new InvisWall(), Unit*x+Unit/2+scroller.getScrolledX(), Unit*y+Unit/2+scroller.getScrolledY());
            }
    }
}