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
public class Ice extends Rectangle{
  
  public BufferedImage iceImg;
  public BufferedImage iglooImg;

  //constructor creates ball at given location with given dimensions, and the iceImg
  public Ice(int x, int y){
    super(x, y, 50, 50);
    getIcon(); 

  }

  // this method is used to get the image from source folder
  public void getIcon() {
	  try {
		  iceImg = ImageIO.read(getClass().getResourceAsStream("/Ice.png"));
		  iglooImg = ImageIO.read(getClass().getResourceAsStream("/Igloo.png"));
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g){
	  Graphics2D g2 = (Graphics2D) g;
	  Color c1 = new Color (74,171,203);
	  g2.setColor(c1);
	  g2.setStroke(new BasicStroke(100));
	  g2.drawRect(0, 0, 800, 700);
	  
	  // ice border - left
	  g.drawImage(iceImg, 50, 50, 50, 50, null);
	  g.drawImage(iceImg, 50, 100, 50, 50, null);
	  g.drawImage(iceImg, 50, 100, 50, 50, null);
	  g.drawImage(iceImg, 50, 150, 50, 50, null);
	  g.drawImage(iceImg, 50, 200, 50, 50, null);
	  g.drawImage(iceImg, 50, 250, 50, 50, null);
	  g.drawImage(iceImg, 50, 300, 50, 50, null);
	  g.drawImage(iceImg, 50, 350, 50, 50, null);
	  g.drawImage(iceImg, 50, 400, 50, 50, null);
	  g.drawImage(iceImg, 50, 450, 50, 50, null);
	  g.drawImage(iceImg, 50, 500, 50, 50, null);
	  g.drawImage(iceImg, 50, 550, 50, 50, null);
	  g.drawImage(iceImg, 50, 600, 50, 50, null);
	  
	  
	  // ice border - right
	  g.drawImage(iceImg, 700, 50, 50, 50, null);
	  g.drawImage(iceImg, 700, 100, 50, 50, null);
	  g.drawImage(iceImg, 700, 100, 50, 50, null);
	  g.drawImage(iceImg, 700, 150, 50, 50, null);
	  g.drawImage(iceImg, 700, 200, 50, 50, null);
	  g.drawImage(iceImg, 700, 250, 50, 50, null);
	  g.drawImage(iceImg, 700, 300, 50, 50, null);
	  g.drawImage(iceImg, 700, 350, 50, 50, null);
	  g.drawImage(iceImg, 700, 400, 50, 50, null);
	  g.drawImage(iceImg, 700, 450, 50, 50, null);
	  g.drawImage(iceImg, 700, 500, 50, 50, null);
	  g.drawImage(iceImg, 700, 550, 50, 50, null);
	  g.drawImage(iceImg, 700, 600, 50, 50, null);
	  
	  // ice border - top
	  g.drawImage(iceImg, 100, 50, 50, 50, null);
	  g.drawImage(iceImg, 150, 50, 50, 50, null);
	  g.drawImage(iceImg, 200, 50, 50, 50, null);
	  g.drawImage(iceImg, 250, 50, 50, 50, null);
	  g.drawImage(iceImg, 300, 50, 50, 50, null);
	  g.drawImage(iceImg, 350, 50, 50, 50, null);
	  g.drawImage(iceImg, 400, 50, 50, 50, null);
	  g.drawImage(iceImg, 450, 50, 50, 50, null);
	  g.drawImage(iceImg, 500, 50, 50, 50, null);
	  g.drawImage(iceImg, 550, 50, 50, 50, null);
	  g.drawImage(iceImg, 600, 50, 50, 50, null);
	  g.drawImage(iceImg, 650, 50, 50, 50, null);
	  
	  // ice border - bottom
	  g.drawImage(iceImg, 100, 600, 50, 50, null);
	  g.drawImage(iceImg, 150, 600, 50, 50, null);
	  g.drawImage(iceImg, 200, 600, 50, 50, null);
	  g.drawImage(iceImg, 250, 600, 50, 50, null);
	  g.drawImage(iceImg, 300, 600, 50, 50, null);
	  g.drawImage(iceImg, 350, 600, 50, 50, null);
	  g.drawImage(iceImg, 400, 600, 50, 50, null);
	  g.drawImage(iceImg, 450, 600, 50, 50, null);
	  g.drawImage(iceImg, 500, 600, 50, 50, null);
	  g.drawImage(iceImg, 550, 600, 50, 50, null);
	  g.drawImage(iceImg, 600, 600, 50, 50, null);
	  g.drawImage(iceImg, 650, 600, 50, 50, null);
	  
	  // ice C - left
	  g.drawImage(iceImg, 200, 200, 50, 50, null);
	  g.drawImage(iceImg, 250, 200, 50, 50, null);
	  g.drawImage(iceImg, 300, 200, 50, 50, null);
	  g.drawImage(iceImg, 200, 450, 50, 50, null);
	  g.drawImage(iceImg, 250, 450, 50, 50, null);
	  g.drawImage(iceImg, 300, 450, 50, 50, null);
	  g.drawImage(iceImg, 200, 250, 50, 50, null);
	  g.drawImage(iceImg, 200, 300, 50, 50, null);
	  g.drawImage(iceImg, 200, 350, 50, 50, null);
	  g.drawImage(iceImg, 200, 400, 50, 50, null);
	  
	  
	  // ice C - right
	  g.drawImage(iceImg, 450, 200, 50, 50, null);
	  g.drawImage(iceImg, 500, 200, 50, 50, null);
	  g.drawImage(iceImg, 550, 200, 50, 50, null);
	  g.drawImage(iceImg, 450, 450, 50, 50, null);
	  g.drawImage(iceImg, 500, 450, 50, 50, null);
	  g.drawImage(iceImg, 550, 450, 50, 50, null);
	  g.drawImage(iceImg, 550, 250, 50, 50, null);
	  g.drawImage(iceImg, 550, 300, 50, 50, null);
	  g.drawImage(iceImg, 550, 350, 50, 50, null);
	  g.drawImage(iceImg, 550, 400, 50, 50, null);
	  
	  g.drawImage(iglooImg, 350, 300, 100, 100, null);
	  
  }
  
}