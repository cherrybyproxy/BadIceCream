
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// extends JPanel to access draw methods
// implements Runnable to run multiple things at once
// implements KeyListener to listen to keyboard input
public class GamePanel extends JPanel implements Runnable, KeyListener{

  //dimensions of window
  public static final int GAME_WIDTH = 800;
  public static final int GAME_HEIGHT = 700;
  
  // variable declaration and initialization
  public Thread gameThread;
  public Image image;
  public Graphics graphics;
  public Ice ice;
  public Player1 player1;
  public Banana banana;
  public Score score;

  public GamePanel(){
   
    this.setFocusable(true); //make everything in this class appear on the screen
    this.addKeyListener(this); //start listening for keyboard input
    ice = new Ice(0, 0);
    player1 = new Player1(400, 400);
    banana = new Banana(0, 0);
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
    
    
    //add the MousePressed method from the MouseAdapter - by doing this we can listen for mouse input. We do this differently from the KeyListener because MouseAdapter has SEVEN mandatory methods - we only need one of them, and we don't want to make 6 empty methods
    addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
			}
		});
    this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

    //make this class run at the same time as other classes (without this each class would "pause" while another class runs). By using threading we can remove lag, and also allows us to do features like display timers in real time!
    gameThread = new Thread(this); 
    gameThread.start();
  }

  //paint is a method in java.awt library that we are overriding. It is a special method - it is called automatically in the background in order to update what appears in the window. You NEVER call paint() yourself
  public void paint(Graphics g){
    //we are using "double buffering" here - if we draw images directly onto the screen, it takes time and the human eye can actually notice flashes of lag as each pixel on the screen is drawn one at a time. Instead, we are going to draw images OFF the screen (outside dimensions of the frame), then simply move the image on screen as needed. 
    image = createImage(GAME_WIDTH, GAME_HEIGHT); //draw off screen
    graphics = image.getGraphics();
    draw(graphics); //update the positions of everything on the screen 
    g.drawImage(image, 0, 0, this); //redraw everything on the screen

  }

  //call the draw methods in each class to update positions as things move
  public void draw(Graphics g){
    ice.draw(g);
    banana.draw(g);
    score.draw(g);
    player1.draw(g);
  }
  
  // separate draw for instructions so we can have the illusion of two different "screens" and have them not interfere with each other
  public void drawInstructions(Graphics g) {
	  
  }
  

  //call the move methods in other classes to update positions
  //this method is constantly called from run(). By doing this, movements appear fluid and natural. If we take this out the movements appear sluggish and laggy
  public void move(){
	  player1.move();
  }

  //handles all collision detection and responds accordingly
  public void checkCollision() {
	  if (player1.intersects(banana) && player1.x == banana.x) {
		  Score.score += 150;
	  }
  }
  

  //run() method is what makes the game continue running without end. It calls other methods to move objects,  check for collision, and update the screen
  public void run(){
    //the CPU runs our game code too quickly - we need to slow it down! The following lines of code "force" the computer to get stuck in a loop for short intervals between calling other methods to update the screen. 
    long lastTime = System.nanoTime();
    double amountOfTicks = 60;
    double ns = 1000000000/amountOfTicks;
    double delta = 0;
    long now;

    while(true){ //this is the infinite game loop
      now = System.nanoTime();
      delta = delta + (now-lastTime)/ns;
      lastTime = now;

      //only move objects around and update screen if enough time has passed
      if(delta >= 1){
        move();
        checkCollision();
        repaint();
        delta--;
      }
      

    }
  }
  
  // if key is pressed
  public void keyPressed(KeyEvent e){
	  player1.keyPressed(e);
  }

  //if a key is released
  public void keyReleased(KeyEvent e){
	  player1.keyReleased(e);
  }

  //left empty because we don't need it; must be here because it is required to be overridded by the KeyListener interface
  public void keyTyped(KeyEvent e){

  }

}