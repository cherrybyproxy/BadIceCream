/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   Cow
   Cow class determines behaviors, movement and collision detection for cow troll. 
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

import java.awt.*; //import packages

public class Cow extends Rectangle { // extends Rectangle because drawing and managing collisions is easy

	private static final long serialVersionUID = 1L; // add default serial id for class

	// Variable declarations
	public static int xVelocity; // sets velocity of troll in horizontal direction

	public static int yVelocity; // sets velocity in vertical direction

	public Image cow; // create image for cow troll

	boolean canRight = true, canLeft = true, canUp = true, canDown = true; // set booleans for collision checker

	// constructor creates cow at given location with given dimensions, and the icon
	public Cow(int x, int y) {
		super(x, y, 40, 40);
		//calibrate y-velocity of cow troll to even speed number
		xVelocity = 0;
		yVelocity = 2;
		cow = Toolkit.getDefaultToolkit().getImage("Cow.png"); // get image for cow trll
	}

	// updates the current location of the cow troll and detects collision with other objects
	public void move() {
		// manually check for collision before movement in vertical direction
		if (y <= 100 && yVelocity < 0) {
			canUp = false;
		} else if (y >= 550 && yVelocity > 0) {
			canDown = false;
		}
		// manually check for collisions before movement in horizontal direction
		else if (x <= 100 && xVelocity < 0) {
			canLeft = false;
		} else if (x >= 650 && xVelocity > 0) {
			canRight = false;
		}

		// determine game bounds for cow troll in all directions in ice column 1 of level 2
		else if (x >= 100 && x < 200 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		} else if (x > 100 && x <= 200 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		} else if (x > 100 && x < 200 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		} else if (x > 100 && x < 200 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}

		// determine game bounds for cow troll in all directions in ice column 2 of level 2
		else if (x >= 250 && x < 350 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		} else if (x > 250 && x <= 350 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		} else if (x > 250 && x < 350 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		} else if (x > 250 && x < 350 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}

		// determine game bounds for cow troll in all directions in ice column 3 of level 2
		else if (x >= 400 && x < 500 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		} else if (x > 400 && x <= 500 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		} else if (x > 400 && x < 500 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		} else if (x > 400 && x < 500 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		}

		// determine game bounds for cow troll in all directions in ice column 4 of level 2
		else if (x >= 550 && x < 650 && y > 150 && y < 500 && xVelocity > 0) {
			canRight = false;
		} else if (x > 550 && x <= 650 && y > 150 && y < 500 && xVelocity < 0) {
			canLeft = false;
		} else if (x > 550 && x < 650 && y > 150 && y <= 500 && yVelocity < 0) {
			canUp = false;
		} else if (x > 550 && x < 650 && y >= 150 && y < 500 && yVelocity > 0) {
			canDown = false;
		} else {
			// set booleans to true so cow troll can move if theres no collisions or bounds
			canUp = true;
			canDown = true;
			canRight = true;
			canLeft = true;
		}
		// set the velocities of cow troll based on movement restrictions
		if (!canRight) {
			yVelocity = 2;
			xVelocity = 0;
		}
		// set velocities of cow troll to patrol around ice border
		if (!canDown) {
			xVelocity = -2;
			yVelocity = 0;
		}
		if (!canLeft) {
			yVelocity = -2;
			xVelocity = 0;
		}
		if (!canUp) {
			xVelocity = 2;
			yVelocity = 0;
		}

		//add y velocities of cow troll to increase speed in vertical direction
		if (canUp && canDown) {
			y += yVelocity;
		}
		//add x velocities of cow troll to increase speed in horizontal direction
		if (canRight && canLeft) {
			x += xVelocity;
		}

	}

	// draws the current location of cow troll to the screen
	public void draw(Graphics g) {
		g.drawImage(cow, x, y, 40, 40, null);
	}

}