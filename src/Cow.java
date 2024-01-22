/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Player1
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;

// extends Rectangle because drawing and managing collisions is easy
public class Cow extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	// Variable declarations
	public static int xVelocity;

	public static int yVelocity; // sets velocity in vertical direction

	//public static int SPEED = 2; // movement speed of player 1
	public Image cow; // create image for player 1

	boolean canRight = true, canLeft = true, canUp = true, canDown = true;
	// constructor creates player 1 at given location with given dimensions, and the
	// icon
	public Cow(int x, int y) {
		super(x, y, 40, 40);
		xVelocity = 0;
		yVelocity = 2;
		cow = Toolkit.getDefaultToolkit().getImage("Cow.png"); //get image for broomstick paddle 
	}


	// updates the current location of player 1
	public void move() {
		if (y <= 100 && yVelocity < 0) {
			canUp = false;
		}
		else if (y >= 550 && yVelocity > 0) {
			canDown = false;
		}
		else if (x <= 100 && xVelocity < 0) {
			canLeft = false;
		}
		else if (x >= 650 && xVelocity > 0) {
			canRight = false;
		}
		
		// column 1
		else if (x >= 100 && x < 200 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		}
		else if (x > 100 && x <= 200 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		}
		else if (x > 100 && x < 200 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		}
		else if (x > 100 && x < 200 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}
		
		// column 2
		else if (x >= 250 && x < 350 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		}
		else if (x > 250 && x <= 350 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		}
		else if (x > 250 && x < 350 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		}
		else if (x > 250 && x < 350 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}
		
		// column 3
		else if (x >= 400 && x < 500 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		}
		else if (x > 400 && x <= 500 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		}
		else if (x > 400 && x < 500 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		}
		else if (x > 400 && x < 500 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}
		
		// column 4
		else if (x >= 550 && x < 650 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		}
		else if (x > 550 && x <= 650 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		}
		else if (x > 550 && x < 650 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		}
		else if (x > 550 && x < 650 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}
		else {
			canUp = true;
			canDown = true;
			canRight = true;
			canLeft = true;
		}
		if (!canRight) {
			yVelocity = 2;
			xVelocity = 0;
		}
		if (!canDown) {
			xVelocity = -2;
			yVelocity = 0;
		}
		if (!canLeft) {
			yVelocity = -2;
			xVelocity = 0;
		}
		if(!canUp) {
			xVelocity = 2;
			yVelocity = 0;
		}

		if (canUp && canDown) {
			y += yVelocity;
		}
		if (canRight && canLeft) {
			x += xVelocity;
		}
		
		
		
	}
	
	// draws the current location of player 1 to the screen
	public void draw(Graphics g) {
		g.drawImage(cow, x, y, 40, 40, null);
	}
	
}