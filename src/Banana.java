/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   Banana
   Banana class draws the banana fruits in GamePanel. 
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

//import packages
import java.awt.*;

// extends Rectangle because drawing and managing collisions is easy
public class Banana extends Rectangle {

	private static final long serialVersionUID = 1L; //create private serial ID

	public Image bananaImg; //create banana image

	// constructor creates banana at given location with given dimensions
	public Banana(int x, int y) {

		super(x, y, 50, 50);

		bananaImg = Toolkit.getDefaultToolkit().getImage("Banana.png"); // get image for banana graphic

	}

	// draws the current location of the banana to the screen
	public void draw(Graphics g) {

		g.drawImage(bananaImg, x, y, 50, 50, null); // draws image to screen

	}

}