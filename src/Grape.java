/* 
 *  Leah Huang
 *  ICS 4U1
 *  Dec 18, 2023
 *  
 *  Moon - manages the iceImg, speed, and direction of player 1
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Grape extends Rectangle{
  
  public BufferedImage grapeImg;

  //constructor creates ball at given location with given dimensions, and the iceImg
  public Grape(int x, int y){
    super(x, y, 50, 50);
    getIcon(); 

  }
  
  // this method is used to get the image from source folder
  public void getIcon() {
	  try {

		  grapeImg = ImageIO.read(getClass().getResourceAsStream("/Grape.png"));
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g){
	  g.drawImage(grapeImg, x, y, 50, 50, null); // for testing
	  
  }
  
}