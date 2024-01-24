/* Leah Huang and Selvahini Kamalarajan
January 23, 2024
Grape
Grape draws the images for grapes in GamePanel. 
Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

import java.awt.*; //import packages
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Grape extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public BufferedImage grapeImg; //declare grape image variable

	// constructor creates grape at given location with given dimensions 
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

	// draws the current location of the grape to the screen
	public void draw(Graphics g) {
		g.drawImage(grapeImg, x, y, 50, 50, null); // for testing

	}

}