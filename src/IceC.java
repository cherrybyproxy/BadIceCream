/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   IceC
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class IceC extends Rectangle {

	private static final long serialVersionUID = 1L;
	public BufferedImage iceImg; //create ice block image

	// constructor creates ice cubes at given location with given dimensions
	public IceC(int x, int y) {
		super(x, y, 50, 50);
		getIcon();

	}

	// this method is used to get the image from source folder
	public void getIcon() {
		try {

			iceImg = ImageIO.read(getClass().getResourceAsStream("/Ice.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of the c-shape ice blocks to the screen
	public void draw(Graphics g) {
		g.drawImage(iceImg, x, y, 50, 50, null); 

	}

}