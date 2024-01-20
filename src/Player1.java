/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Player1
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

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
			// move right
			setXDirection(SPEED);
			move();
		}

		if (e.getKeyChar() == 'a') {
			// move left
			setXDirection(SPEED * -1);
			move();
		}

		if (e.getKeyChar() == 'w') {
			// move forward
			setYDirection(SPEED * -1);
			move();
		}

		if (e.getKeyChar() == 's') {
			// move backwards
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
		x += xVelocity;
		y += yVelocity;
	}
	
	// draws the current location of player 1 to the screen
	public void draw(Graphics g) {
		// draw image to screen
		/*if (charNum == 1) {
			g.drawImage(sorbet, x, y, 40, 40, null);
		} else if (charNum == 2) {
			g.drawImage(smokeyb, x, y, 40, 40, null);
		} else if (charNum == 3) {
			g.drawImage(mint, x, y, 40, 40, null);
		} */

		g.drawImage(mint, x, y, 40, 40, null);
	}

}