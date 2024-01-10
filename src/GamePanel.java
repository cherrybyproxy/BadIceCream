
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
  public Score score;
  
  public IceC iceC;
  public IceC iceC2;
  public IceC iceC3;
  public IceC iceC4;
  public IceC iceC5;
  public IceC iceC6;
  public IceC iceC7;
  public IceC iceC8;
  public IceC iceC9;
  public IceC iceC10;
  public IceC iceC11;
  public IceC iceC12;
  public IceC iceC13;
  public IceC iceC14;
  public IceC iceC15;
  public IceC iceC16;
  public IceC iceC17;
  public IceC iceC18;
  public IceC iceC19;
  public IceC iceC20;
  
  public Igloo igloo;
  
  public Banana banana;
  public Banana banana2;
  public Banana banana3;
  public Banana banana4;
  public Banana banana5;
  public Banana banana6;
  public Banana banana7;
  public Banana banana8;
  public Banana banana9;
  public Banana banana10;
  public Banana banana11;
  public Banana banana12;
  public Banana banana13;
  public Banana banana14;
  public Banana banana15;
  public Banana banana16;
  
  // might replace w boolean array
  public boolean drawBanana = true;
  public boolean drawBanana2 = true;
  public boolean drawBanana3 = true;
  public boolean drawBanana4 = true;
  public boolean drawBanana5 = true;
  public boolean drawBanana6 = true;
  public boolean drawBanana7 = true;
  public boolean drawBanana8 = true;
  public boolean drawBanana9 = true;
  public boolean drawBanana10 = true;
  public boolean drawBanana11 = true;
  public boolean drawBanana12 = true;
  public boolean drawBanana13 = true;
  public boolean drawBanana14 = true;
  public boolean drawBanana15 = true;
  public boolean drawBanana16 = true;

  public GamePanel(){
   
    this.setFocusable(true); //make everything in this class appear on the screen
    this.addKeyListener(this); //start listening for keyboard input
    ice = new Ice(0, 0);
    player1 = new Player1(400, 400);
    score = new Score(GAME_WIDTH, GAME_HEIGHT);
    
    iceC = new IceC(200, 200);
    iceC2 = new IceC(250, 200);
    iceC3 = new IceC(300, 200);
    iceC4 = new IceC(200, 450);
    iceC5 = new IceC(250, 450);
    iceC6 = new IceC(300, 450);
    iceC7 = new IceC(200, 250);
    iceC8 = new IceC(200, 300);
    iceC9 = new IceC(200, 350);
    iceC10 = new IceC(200, 400);
    
    igloo = new Igloo(350, 300);
    
    iceC11 = new IceC(450, 200);
    iceC12 = new IceC(500, 200);
    iceC13 = new IceC(550, 200);
    iceC14 = new IceC(450, 450);
    iceC15 = new IceC(500, 450);
    iceC16 = new IceC(550, 450);
    iceC17 = new IceC(550, 250);
    iceC18 = new IceC(550, 300);
    iceC19 = new IceC(550, 350);
    iceC20 = new IceC(550, 400);
    
    
    // bananas
    banana = new Banana(150, 150);
    banana2 = new Banana(150, 200);
    banana3 = new Banana(200, 150);
    banana4 = new Banana(150, 450);
    banana5 = new Banana(150, 500);
    banana6 = new Banana(200, 500);
    banana7 = new Banana(550, 150);
    banana8 = new Banana(600, 200);
    banana9 = new Banana(600, 150);
    banana10 = new Banana(600, 450);
    banana11 = new Banana(550, 500);
    banana12 = new Banana(600, 500);
    banana13 = new Banana(300, 300);
    banana14 = new Banana(300, 350);
    banana15 = new Banana(450, 300);
    banana16 = new Banana(450, 350);
    
    
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
    
    iceC.draw(g);
    iceC2.draw(g);
    iceC3.draw(g);
    iceC4.draw(g);
    iceC5.draw(g);
    iceC6.draw(g);
    iceC7.draw(g);
    iceC8.draw(g);
    iceC9.draw(g);
    iceC10.draw(g);
    iceC11.draw(g);
    iceC12.draw(g);
    iceC13.draw(g);
    iceC14.draw(g);
    iceC15.draw(g);
    iceC16.draw(g);
    iceC17.draw(g);
    iceC18.draw(g);
    iceC19.draw(g);
    iceC20.draw(g);
    
    igloo.draw(g);
    
    if (drawBanana) {
        banana.draw(g);
    }
    if (drawBanana2) {
        banana2.draw(g);
    }
    if (drawBanana3) {
        banana3.draw(g);
    }
    if (drawBanana4) {
        banana4.draw(g);
    }
    if (drawBanana5) {
        banana5.draw(g);
    }
    if (drawBanana6) {
        banana6.draw(g);
    }
    if (drawBanana7) {
        banana7.draw(g);
    }
    if (drawBanana8) {
        banana8.draw(g);
    }
    if (drawBanana9) {
        banana9.draw(g);
    }
    if (drawBanana10) {
        banana10.draw(g);
    }
    if (drawBanana11) {
        banana11.draw(g);
    }
    if (drawBanana12) {
        banana12.draw(g);
    }
    if (drawBanana13) {
        banana13.draw(g);
    }
    if (drawBanana14) {
        banana14.draw(g);
    }
    if (drawBanana15) {
        banana15.draw(g);
    }
    if (drawBanana16) {
        banana16.draw(g);
    }

    score.draw(g);
    player1.draw(g);
  }
  

  //call the move methods in other classes to update positions
  //this method is constantly called from run(). By doing this, movements appear fluid and natural. If we take this out the movements appear sluggish and laggy
  public void move(){
	  player1.move();
  }

  //handles all collision detection and responds accordingly
  public void checkCollision() {
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC) && player1.x > iceC.x && player1.xVelocity < 0) {
		  player1.x = iceC.x + 50;
	  }
	  if(player1.intersects(iceC) && player1.x < iceC.x && player1.xVelocity > 0) {
		  player1.x = iceC.x - 50;
	  }
	  if(player1.intersects(iceC) && player1.y > iceC.y && player1.yVelocity < 0) {
		  player1.y = iceC.y + 50;
	  }
	  if(player1.intersects(iceC) && player1.y < iceC.y && player1.yVelocity > 0) {
		  player1.y = iceC.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC2) && player1.x > iceC2.x && player1.xVelocity < 0) {
		  player1.x = iceC2.x + 50;
	  }
	  if(player1.intersects(iceC2) && player1.x < iceC2.x && player1.xVelocity > 0) {
		  player1.x = iceC2.x - 50;
	  }
	  if(player1.intersects(iceC2) && player1.y > iceC2.y && player1.yVelocity < 0) {
		  player1.y = iceC2.y + 50;
	  }
	  if(player1.intersects(iceC2) && player1.y < iceC2.y && player1.yVelocity > 0) {
		  player1.y = iceC2.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC3) && player1.x > iceC3.x && player1.xVelocity < 0) {
		  player1.x = iceC3.x + 50;
	  }
	  if(player1.intersects(iceC3) && player1.x < iceC3.x && player1.xVelocity > 0) {
		  player1.x = iceC3.x - 50;
	  }
	  if(player1.intersects(iceC3) && player1.y > iceC3.y && player1.yVelocity < 0) {
		  player1.y = iceC3.y + 50;
	  }
	  if(player1.intersects(iceC3) && player1.y < iceC3.y && player1.yVelocity > 0) {
		  player1.y = iceC3.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC4) && player1.x > iceC4.x && player1.xVelocity < 0) {
		  player1.x = iceC4.x + 50;
	  }
	  if(player1.intersects(iceC4) && player1.x < iceC4.x && player1.xVelocity > 0) {
		  player1.x = iceC4.x - 50;
	  }
	  if(player1.intersects(iceC4) && player1.y > iceC4.y && player1.yVelocity < 0) {
		  player1.y = iceC4.y + 50;
	  }
	  if(player1.intersects(iceC4) && player1.y < iceC4.y && player1.yVelocity > 0) {
		  player1.y = iceC4.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC5) && player1.x > iceC5.x && player1.xVelocity < 0) {
		  player1.x = iceC5.x + 50;
	  }
	  if(player1.intersects(iceC5) && player1.x < iceC5.x && player1.xVelocity > 0) {
		  player1.x = iceC5.x - 50;
	  }
	  if(player1.intersects(iceC5) && player1.y > iceC5.y && player1.yVelocity < 0) {
		  player1.y = iceC5.y + 50;
	  }
	  if(player1.intersects(iceC5) && player1.y < iceC5.y && player1.yVelocity > 0) {
		  player1.y = iceC5.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC6) && player1.x > iceC6.x && player1.xVelocity < 0) {
		  player1.x = iceC6.x + 50;
	  }
	  if(player1.intersects(iceC6) && player1.x < iceC6.x && player1.xVelocity > 0) {
		  player1.x = iceC6.x - 50;
	  }
	  if(player1.intersects(iceC6) && player1.y > iceC6.y && player1.yVelocity < 0) {
		  player1.y = iceC6.y + 50;
	  }
	  if(player1.intersects(iceC6) && player1.y < iceC6.y && player1.yVelocity > 0) {
		  player1.y = iceC6.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC7) && player1.x > iceC7.x && player1.xVelocity < 0) {
		  player1.x = iceC7.x + 50;
	  }
	  if(player1.intersects(iceC7) && player1.x < iceC7.x && player1.xVelocity > 0) {
		  player1.x = iceC7.x - 50;
	  }
	  if(player1.intersects(iceC7) && player1.y > iceC7.y && player1.yVelocity < 0) {
		  player1.y = iceC7.y + 50;
	  }
	  if(player1.intersects(iceC7) && player1.y < iceC7.y && player1.yVelocity > 0) {
		  player1.y = iceC7.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC8) && player1.x > iceC8.x && player1.xVelocity < 0) {
		  player1.x = iceC8.x + 50;
	  }
	  if(player1.intersects(iceC8) && player1.x < iceC8.x && player1.xVelocity > 0) {
		  player1.x = iceC8.x - 50;
	  }
	  if(player1.intersects(iceC8) && player1.y > iceC8.y && player1.yVelocity < 0) {
		  player1.y = iceC8.y + 50;
	  }
	  if(player1.intersects(iceC8) && player1.y < iceC8.y && player1.yVelocity > 0) {
		  player1.y = iceC8.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC9) && player1.x > iceC9.x && player1.xVelocity < 0) {
		  player1.x = iceC9.x + 50;
	  }
	  if(player1.intersects(iceC9) && player1.x < iceC9.x && player1.xVelocity > 0) {
		  player1.x = iceC9.x - 50;
	  }
	  if(player1.intersects(iceC9) && player1.y > iceC9.y && player1.yVelocity < 0) {
		  player1.y = iceC9.y + 50;
	  }
	  if(player1.intersects(iceC9) && player1.y < iceC9.y && player1.yVelocity > 0) {
		  player1.y = iceC9.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC10) && player1.x > iceC10.x && player1.xVelocity < 0) {
		  player1.x = iceC10.x + 50;
	  }
	  if(player1.intersects(iceC10) && player1.x < iceC10.x && player1.xVelocity > 0) {
		  player1.x = iceC10.x - 50;
	  }
	  if(player1.intersects(iceC10) && player1.y > iceC10.y && player1.yVelocity < 0) {
		  player1.y = iceC10.y + 50;
	  }
	  if(player1.intersects(iceC10) && player1.y < iceC10.y && player1.yVelocity > 0) {
		  player1.y = iceC10.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC11) && player1.x > iceC11.x && player1.xVelocity < 0) {
		  player1.x = iceC11.x + 50;
	  }
	  if(player1.intersects(iceC11) && player1.x < iceC11.x && player1.xVelocity > 0) {
		  player1.x = iceC11.x - 50;
	  }
	  if(player1.intersects(iceC11) && player1.y > iceC11.y && player1.yVelocity < 0) {
		  player1.y = iceC11.y + 50;
	  }
	  if(player1.intersects(iceC11) && player1.y < iceC11.y && player1.yVelocity > 0) {
		  player1.y = iceC11.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC12) && player1.x > iceC12.x && player1.xVelocity < 0) {
		  player1.x = iceC12.x + 50;
	  }
	  if(player1.intersects(iceC12) && player1.x < iceC12.x && player1.xVelocity > 0) {
		  player1.x = iceC12.x - 50;
	  }
	  if(player1.intersects(iceC12) && player1.y > iceC12.y && player1.yVelocity < 0) {
		  player1.y = iceC12.y + 50;
	  }
	  if(player1.intersects(iceC12) && player1.y < iceC12.y && player1.yVelocity > 0) {
		  player1.y = iceC12.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC13) && player1.x > iceC13.x && player1.xVelocity < 0) {
		  player1.x = iceC13.x + 50;
	  }
	  if(player1.intersects(iceC13) && player1.x < iceC13.x && player1.xVelocity > 0) {
		  player1.x = iceC13.x - 50;
	  }
	  if(player1.intersects(iceC13) && player1.y > iceC13.y && player1.yVelocity < 0) {
		  player1.y = iceC13.y + 50;
	  }
	  if(player1.intersects(iceC13) && player1.y < iceC13.y && player1.yVelocity > 0) {
		  player1.y = iceC13.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC14) && player1.x > iceC14.x && player1.xVelocity < 0) {
		  player1.x = iceC14.x + 50;
	  }
	  if(player1.intersects(iceC14) && player1.x < iceC14.x && player1.xVelocity > 0) {
		  player1.x = iceC14.x - 50;
	  }
	  if(player1.intersects(iceC14) && player1.y > iceC14.y && player1.yVelocity < 0) {
		  player1.y = iceC14.y + 50;
	  }
	  if(player1.intersects(iceC14) && player1.y < iceC14.y && player1.yVelocity > 0) {
		  player1.y = iceC14.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC15) && player1.x > iceC15.x && player1.xVelocity < 0) {
		  player1.x = iceC15.x + 50;
	  }
	  if(player1.intersects(iceC15) && player1.x < iceC15.x && player1.xVelocity > 0) {
		  player1.x = iceC15.x - 50;
	  }
	  if(player1.intersects(iceC15) && player1.y > iceC15.y && player1.yVelocity < 0) {
		  player1.y = iceC15.y + 50;
	  }
	  if(player1.intersects(iceC15) && player1.y < iceC15.y && player1.yVelocity > 0) {
		  player1.y = iceC15.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC16) && player1.x > iceC16.x && player1.xVelocity < 0) {
		  player1.x = iceC16.x + 50;
	  }
	  if(player1.intersects(iceC16) && player1.x < iceC16.x && player1.xVelocity > 0) {
		  player1.x = iceC16.x - 50;
	  }
	  if(player1.intersects(iceC16) && player1.y > iceC16.y && player1.yVelocity < 0) {
		  player1.y = iceC16.y + 50;
	  }
	  if(player1.intersects(iceC16) && player1.y < iceC16.y && player1.yVelocity > 0) {
		  player1.y = iceC16.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC17) && player1.x > iceC17.x && player1.xVelocity < 0) {
		  player1.x = iceC17.x + 50;
	  }
	  if(player1.intersects(iceC17) && player1.x < iceC17.x && player1.xVelocity > 0) {
		  player1.x = iceC17.x - 50;
	  }
	  if(player1.intersects(iceC17) && player1.y > iceC17.y && player1.yVelocity < 0) {
		  player1.y = iceC17.y + 50;
	  }
	  if(player1.intersects(iceC17) && player1.y < iceC17.y && player1.yVelocity > 0) {
		  player1.y = iceC17.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC18) && player1.x > iceC18.x && player1.xVelocity < 0) {
		  player1.x = iceC18.x + 50;
	  }
	  if(player1.intersects(iceC18) && player1.x < iceC18.x && player1.xVelocity > 0) {
		  player1.x = iceC18.x - 50;
	  }
	  if(player1.intersects(iceC18) && player1.y > iceC18.y && player1.yVelocity < 0) {
		  player1.y = iceC18.y + 50;
	  }
	  if(player1.intersects(iceC18) && player1.y < iceC18.y && player1.yVelocity > 0) {
		  player1.y = iceC18.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC19) && player1.x > iceC19.x && player1.xVelocity < 0) {
		  player1.x = iceC19.x + 50;
	  }
	  if(player1.intersects(iceC19) && player1.x < iceC19.x && player1.xVelocity > 0) {
		  player1.x = iceC19.x - 50;
	  }
	  if(player1.intersects(iceC19) && player1.y > iceC19.y && player1.yVelocity < 0) {
		  player1.y = iceC19.y + 50;
	  }
	  if(player1.intersects(iceC19) && player1.y < iceC19.y && player1.yVelocity > 0) {
		  player1.y = iceC19.y - 50;
	  }  
	  // ------------------------------------------------------------------------------------
	  if(player1.intersects(iceC20) && player1.x > iceC20.x && player1.xVelocity < 0) {
		  player1.x = iceC20.x + 50;
	  }
	  if(player1.intersects(iceC20) && player1.x < iceC20.x && player1.xVelocity > 0) {
		  player1.x = iceC20.x - 50;
	  }
	  if(player1.intersects(iceC20) && player1.y > iceC20.y && player1.yVelocity < 0) {
		  player1.y = iceC20.y + 50;
	  }
	  if(player1.intersects(iceC20) && player1.y < iceC20.y && player1.yVelocity > 0) {
		  player1.y = iceC20.y - 50;
	  }  
	  
	  // strange movement - fix later
	  if(player1.intersects(igloo) && (player1.x + 100) > igloo.x && player1.xVelocity < 0) {
		  player1.x = igloo.x + 100;
	  }
	  if(player1.intersects(igloo) && player1.x < igloo.x && player1.xVelocity > 0) {
		  player1.x = igloo.x - 50;
	  }
	  if(player1.intersects(igloo) && (player1.y + 100) > igloo.y && player1.yVelocity < 0) {
		  player1.y = igloo.y + 100;
	  }
	  if(player1.intersects(igloo) && player1.y  < igloo.y && player1.yVelocity > 0) {
		  player1.y = igloo.y - 50;
	  } 
	  
	  
	  if (player1.intersects(banana) && drawBanana == true) {
		  Score.score += 150; 
		  drawBanana = false;
	  }
	  if (player1.intersects(banana2) && drawBanana2 == true) {
		  Score.score += 150;  
		  drawBanana2 = false;
	  }
	  if (player1.intersects(banana3) && drawBanana3 == true) {
		  Score.score += 150;  
		  drawBanana3 = false;
	  }
	  if (player1.intersects(banana4) && drawBanana4 == true) {
		  Score.score += 150;  
		  drawBanana4 = false;
	  }
	  if (player1.intersects(banana5) && drawBanana5 == true) {
		  Score.score += 150;  
		  drawBanana5 = false;
	  }
	  if (player1.intersects(banana6) && drawBanana6 == true) {
		  Score.score += 150;  
		  drawBanana6 = false;
	  }
	  if (player1.intersects(banana7) && drawBanana7 == true) {
		  Score.score += 150;  
		  drawBanana7 = false;
	  }
	  if (player1.intersects(banana8) && drawBanana8 == true) {
		  Score.score += 150;  
		  drawBanana8 = false;
	  }
	  if (player1.intersects(banana9) && drawBanana9 == true) {
		  Score.score += 150;  
		  drawBanana9 = false;
	  }
	  if (player1.intersects(banana10) && drawBanana10 == true) {
		  Score.score += 150;  
		  drawBanana10 = false;
	  }
	  if (player1.intersects(banana11) && drawBanana11 == true) {
		  Score.score += 150;  
		  drawBanana11 = false;
	  }
	  if (player1.intersects(banana12) && drawBanana12 == true) {
		  Score.score += 150;  
		  drawBanana12 = false;
	  }
	  if (player1.intersects(banana13) && drawBanana13 == true) {
		  Score.score += 150;  
		  drawBanana13 = false;
	  }
	  if (player1.intersects(banana14) && drawBanana14 == true) {
		  Score.score += 150;  
		  drawBanana14 = false;
	  }
	  if (player1.intersects(banana15) && drawBanana15 == true) {
		  Score.score += 150;  
		  drawBanana15 = false;
	  }
	  if (player1.intersects(banana16) && drawBanana16 == true) {
		  Score.score += 150;  
		  drawBanana16 = false;
	  }
	  
	  
	  if (player1.x > 650) {
		  player1.x = 650;
	  }
	  
	  if (player1.x < 100) {
		  player1.x = 100;
	  }
	  
	  if (player1.y > 550) {
		  player1.y = 550;
	  }
	  
	  if (player1.y < 100) {
		  player1.y = 100;
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