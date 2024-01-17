/* Leah Huang and Selvahini Kamalarajan
   January 12, 2024
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
	
	//Variable declarations
	public int xVelocity;

	public int yVelocity; // sets velocity in vertical direction

	public final int SPEED = 3; // movement speed of player 1

	public BufferedImage icon; // create image for player 1
	
	boolean up, down, left, right;

	// constructor creates player 1 at given location with given dimensions, and the
	// icon
	public Player1(int x, int y) {
		super(x, y, 40, 40);
		getIcon();
		up = false;
		down = false;
		left = false;
		right = false;
	}

	// called from GamePanel when any keyboard input is detected
	public void keyPressed(KeyEvent e) {

	/*	// controls for player 1
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
*/
		switch  (e.getKeyChar()) {
			
			case 'w':
				up = true;
				down = false;
				left = false;
				right = false;
				break;
			case 's':
				
				up = false;
				down = true;
				left = false;
				right = false;
				break;
			case 'a':
				up = false;
				down = false;
				left = true;
				right = false;
				break;
			case 'd':
				
				up = false;
				down = false;
				left = false;
				right = true;
				break;
		}
		if (up) {
			setYDirection(SPEED * -1); //paddle 1 moves up for keyboard input 'w'
			
			move(); //updates movement of paddle 1
		}
		if (down) {
			setYDirection(SPEED);
			move();
		}
		if (left) {
			setXDirection(SPEED * -1);
			move();
		}
		if (right) {
			setXDirection(SPEED);
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

	// called whenever the movement of the ball changes in the x-direction
	// (left/right)
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	// updates the current location of the ball
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	// this method is used to get the image from source folder
	public void getIcon() {
		try {
			icon = ImageIO.read(getClass().getResourceAsStream("/Sorbet.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of the ball to the screen
	public void draw(Graphics g) {
		// draw image to screen
		g.drawImage(icon, x, y, 40, 40, null);

	}

}