/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   Level2Ice
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

//import packages
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Level2Ice extends Rectangle {

	private static final long serialVersionUID = 1L;
	public BufferedImage iceImg; //create ice block image 

	// constructor creates ice blocks at given location for Level 2
	public Level2Ice(int x, int y) {
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

	// draws the current location of the ice blocks in Level 2
	public void draw(Graphics g) {
		g.drawImage(iceImg, x, y, 50, 50, null); // for testing

	}

}