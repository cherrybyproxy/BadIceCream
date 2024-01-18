/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Banana
   Banana class draws the banana fruits in GamePanel. 
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game and score. */

//import packages
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Banana extends Rectangle {

	private static final long serialVersionUID = 1L;

	public BufferedImage bananaImg;

	// constructor creates banana at given location with given dimensions
	public Banana(int x, int y) {

		super(x, y, 50, 50);
		getIcon();

	}

	// this method is used to get the image from source folder
	public void getIcon() {
		try {

			bananaImg = ImageIO.read(getClass().getResourceAsStream("/Banana.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of the banana to the screen
	public void draw(Graphics g) {

		g.drawImage(bananaImg, x, y, 50, 50, null); // draws image to screen

	}

}