/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   Igloo
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Igloo extends Rectangle {

	private static final long serialVersionUID = 1L;
	public BufferedImage iglooImg;

	// constructor creates igloo image at given location with given dimensions 
	public Igloo(int x, int y) {
		super(x, y, 100, 100);
		getIcon(); //create image

	}

	// this method is used to get the image from source folder
	public void getIcon() {
		try {

			iglooImg = ImageIO.read(getClass().getResourceAsStream("Igloo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of the igloo to the screen
	public void draw(Graphics g) {
		g.drawImage(iglooImg, x, y, 100, 100, null); // for testing

	}

}