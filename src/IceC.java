import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class IceC extends Rectangle{
  
  public BufferedImage iceImg;

  //constructor creates ball at given location with given dimensions, and the iceImg
  public IceC(int x, int y){
    super(x, y, 50, 50);
    getIcon(); 

  }
  
  // this method is used to get the image from source folder
  public void getIcon() {
	  try {

		  iceImg = ImageIO.read(getClass().getResourceAsStream("/Ice.png"));
	  }
	  catch(IOException e) {
		  e.printStackTrace();
	  }
  }
  //called frequently from the GamePanel class
  //draws the current location of the ball to the screen
  public void draw(Graphics g){
	  g.drawImage(iceImg, x, y, 50, 50, null); // for testing
	  
  }
  
}