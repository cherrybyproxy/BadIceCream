/* 
 *  Leah Huang
 *  ICS 4U1
 *  Dec 18, 2023
 *  
 *  Moon - manages the icon, speed, and direction of player 1
 */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Player1 extends Rectangle{

	// variable declaration
  public int xVelocity;
  public final int SPEED = 5; //movement speed of ball
  
  public BufferedImage icon;

  //constructor creates ball at given location with given dimensions, and the icon
  public Player1(int x, int y){
    super(x, y, 50, 50);
    getIcon(); 

  }

  //called from GamePanel when any keyboard input is detected
  //updates the direction of the ball based on user input
  //if the keyboard input isn't any of the options (d, a, w, s), then nothing happens
  public void keyPressed(KeyEvent e){
    if(e.getKeyChar() == 'd'){
      setXDirection(SPEED);
      move();
    }

    if(e.getKeyChar() == 'a'){
      setXDirection(SPEED*-1);
      move();
    }

  }

  //called from GamePanel when any key is released (no longer being pressed down)
  //Makes the ball stop moving in that direction
  public void keyReleased(KeyEvent e){
    if(e.getKeyChar() == 'd'){
      setXDirection(0);
      move();
    }

    if(e.getKeyChar() == 'a'){
      setXDirection(0);
      move();
    }

  }

  //called from GamePanel whenever a mouse click is detected
  //changes the current location of the ball to be wherever the mouse is located on the screen
  public void mousePressed(MouseEvent e){

  }

  //called whenever the movement of the ball changes in the x-direction (left/right)
  public void setXDirection(int xDirection){
    xVelocity = xDirection;
  }

  //called frequently from both Moon class and GamePanel class
  //updates the current location of the ball
  public void move(){
    x = x + xVelocity;
  }
  
  // this method is used to get the image from source folder
  public void getIcon() {
	  try {
		  icon = ImageIO.read(getClass().getResourceAsStream("/Sorbet.png"));
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g){
	  g.drawImage(icon, x, y, 50, 50, null);
	  
  }
  
}