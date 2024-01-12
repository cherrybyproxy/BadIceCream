/* Leah Huang and Selvahini Kamalarajan
   January 12, 2024
   Grape
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Grape extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public BufferedImage grapeImg;

	// constructor creates ball at given location with given dimensions and the
	// iceImg
	public Grape(int x, int y) {
		super(x, y, 50, 50);
		getIcon();

	}

	// this method is used to get the image from source folder
	public void getIcon() {
		try {

			grapeImg = ImageIO.read(getClass().getResourceAsStream("/Grape.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of the ball to the screen
	public void draw(Graphics g) {
		g.drawImage(grapeImg, x, y, 50, 50, null); // for testing

	}

}