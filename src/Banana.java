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
public class Banana extends Rectangle{
  
  public BufferedImage bananaImg;

  //constructor creates ball at given location with given dimensions, and the iceImg
  public Banana(int x, int y){
    super(x, y, 50, 50);
    getIcon(); 

  }

  // this method is used to get the image from source folder
  public void getIcon() {
	  try {

		  bananaImg = ImageIO.read(getClass().getResourceAsStream("/Banana.png"));
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g){

	  // top left bananas
	  g.drawImage(bananaImg, 150, 150, 50, 50, null);
	  g.drawImage(bananaImg, 150, 200, 50, 50, null);
	  g.drawImage(bananaImg, 200, 150, 50, 50, null);
	  
	  // bottom left bananas
	  g.drawImage(bananaImg, 150, 450, 50, 50, null);
	  g.drawImage(bananaImg, 150, 500, 50, 50, null);
	  g.drawImage(bananaImg, 200, 500, 50, 50, null);
	  
	  // top right bananas
	  g.drawImage(bananaImg, 550, 150, 50, 50, null);
	  g.drawImage(bananaImg, 600, 200, 50, 50, null);
	  g.drawImage(bananaImg, 600, 150, 50, 50, null);
	  
	  // bottom right bananas
	  g.drawImage(bananaImg, 600, 450, 50, 50, null);
	  g.drawImage(bananaImg, 550, 500, 50, 50, null);
	  g.drawImage(bananaImg, 600, 500, 50, 50, null);
	  
	  // g.drawImage(bananaImg,  600, 400, 50, 50, null); // for testing
	  
  }
  
}