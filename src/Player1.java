/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Player1
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.event.*;

// extends Rectangle because drawing and managing collisions is easy
public class Player1 extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	// Variable declarations
	public int xVelocity;

	public int yVelocity; // sets velocity in vertical direction

	public final int SPEED = 3; // movement speed of player 1

	public int charNum; // stores value for paddle 1 or paddle 2

	public Image sorbet, smokeyb, mint; // create image for player 1

	boolean up, down, left, right;
	boolean canRight = true, canLeft = true, canUp = true, canDown = true;
	
	int level = 1; 

	// constructor creates player 1 at given location with given dimensions, and the
	// icon
	public Player1(int x, int y, int num) {
		super(x, y, 40, 40);

		sorbet = Toolkit.getDefaultToolkit().getImage("sorbet.png"); //get image for broomstick paddle 
		mint = Toolkit.getDefaultToolkit().getImage("MintChocChip.png"); //get image for broomstick paddle 
		smokeyb = Toolkit.getDefaultToolkit().getImage("smokeyb.png"); //get image for broomstick paddle 
		
		up = false;
		down = false;
		left = false;
		right = false;

		charNum = num;
	}

	// called from GamePanel when any keyboard input is detected
	public void keyPressed(KeyEvent e) {
		// controls for player 1
		if (e.getKeyChar() == 'd') {
			setXDirection(SPEED);
			move();
		}

		if (e.getKeyChar() == 'a') {
			setXDirection(SPEED*-1);
			move();
		}

		if (e.getKeyChar() == 'w') {
			setYDirection(SPEED *-1);
			move();
		}

		if (e.getKeyChar() == 's') {
			setYDirection(SPEED);
			move();
		}
		

	}

	// Makes player 1 stop moving when keys are released
	public void keyReleased(KeyEvent e) {
		// set all speed and movement to 0
		if (e.getKeyChar() == 'd') {
			setXDirection(0);
			move();
		}

		if (e.getKeyChar() == 'a') {
			setXDirection(0);
			move();
		}

		if (e.getKeyChar() == 'w') {
			setYDirection(0);
			move();
		}

		if (e.getKeyChar() == 's') {
			setYDirection(0);
			move();
		}

	}

	// called whenever the movement of player 1 changes in the x-direction
	// (left/right)
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	// updates the current location of player 1
	public void move() {
		if (level == 1) {
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
			// column
			else if (x >= 150 && x < 250 && xVelocity > 0 && (y > 150 && y < 500)) {
				canRight = false;
			}
			else if (x > 150 && x <= 250 && xVelocity < 0 && (y > 200 && y < 450)) {
				canLeft = false;
			}
			
			// top row
			else if (y >= 150 && y < 250 && yVelocity > 0 && (x > 150 && x < 350)) {
				canDown = false;
			}
			else if (y > 150 && y <= 250 && yVelocity < 0 && (x > 200 && x < 350)) {
				canUp = false;
			}
			else if (x >= 300 && x <= 350 && y > 150 && y < 250 && xVelocity < 0) {
				canLeft = false;
			}
			
			//bottom row
			else if (y >= 400 && y < 500 && yVelocity > 0 && (x > 150 && x < 350)) {
				canDown = false;
			}
			else if (y > 450 && y <= 500 && yVelocity < 0 && (x > 150 && x < 350)) {
				canUp = false;
			}
			else if (x <= 350 && x > 150 && y > 400 && y < 500 && xVelocity < 0 ) {
				canLeft = false;
			}
			
			// column 2
			else if (x >= 500 && x < 600 && xVelocity > 0 && (y > 200 && y < 450)) {
				canRight = false;
			}
			else if (x > 500 && x <= 600 && xVelocity < 0 && (y > 150 && y < 500)) {
				canLeft = false;
			}
			
			// top row 2
			else if (y >= 150 && y < 250 && yVelocity > 0 && (x > 400 && x < 600)) {
				canDown = false;
			}
			else if (y > 150 && y <= 250 && yVelocity < 0 && (x > 400 && x < 550)) {
				canUp = false;
			}
			else if (x >= 400 && x <= 450 && y > 150 && y < 250 && xVelocity > 0) {
				canRight = false;
			}
			
			//bottom row 2
			else if (y >= 400 && y < 500 && yVelocity > 0 && (x > 400 && x < 550)) {
				canDown = false;
			}
			else if (y > 450 && y <= 500 && yVelocity < 0 && (x > 400 && x < 600)) {
				canUp = false;
			}
			else if (x >= 400 && x <= 450 && y > 400 && y < 500 && xVelocity > 0 ) {
				canRight = false;
			}
			
			// igloo
			else if (x > 350 && x <= 450 && y > 250 && y < 350 && xVelocity < 0) {
				canLeft = false;
			}
			else if (x >= 300 && x < 450 && y > 250 && y < 350 && xVelocity > 0) {
				canRight = false;
			}
			
			else if (x > 300 && x < 450 && y > 250 && y <= 350 && yVelocity < 0) {
				canUp = false;
			}
			else if (x > 300 && x < 450 && y >= 250 && y < 350 && yVelocity > 0) {
				canDown = false;
			}
			else {
				x += xVelocity;
				y += yVelocity;
			}
			/*
			else {
				canUp = true;
				canDown = true;
				canRight = true;
				canLeft = true;
			}
			if (canUp && canDown) {
				y += yVelocity;
			}
			if (canRight && canLeft) {
				x += xVelocity;
			}
			*/
			
		}
		if (level == 2) {
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
				x += xVelocity;
				y += yVelocity;
			}
		}
	}
	
	// draws the current location of player 1 to the screen
	public void draw(Graphics g) {
		// draw image to screen
		if (charNum == 1) {
			g.drawImage(sorbet, x, y, 40, 40, null);
		} else if (charNum == 2) {
			g.drawImage(smokeyb, x, y, 40, 40, null);
		} else if (charNum == 3) {
			g.drawImage(mint, x, y, 40, 40, null);
		} 
	}
	
}