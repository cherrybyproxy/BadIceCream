/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Player2
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Player2 extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	// variable declarations for velocity and speed of player 1
	public int xVelocity;
	public int yVelocity;

	public final int SPEED = 3; // movement speed

	public int char2Num; // stores value for paddle 1 or paddle 2

	public Image sorbet, smokeyb, mint; // create image for player 1

	// constructor creates player 2 at given location with given dimensions, and the
	// icon
	public Player2(int x, int y, int num) {
		super(x, y, 40, 40);
		
		sorbet = Toolkit.getDefaultToolkit().getImage("sorbet.png"); //get image for broomstick paddle 
		mint = Toolkit.getDefaultToolkit().getImage("MintChocChip.png"); //get image for broomstick paddle 
		smokeyb = Toolkit.getDefaultToolkit().getImage("smokeyb.png"); //get image for broomstick paddle 
		
		char2Num = num;
	}

	// updates the direction of player 2 based on user input
	public void keyPressed(KeyEvent e) {
		// set movement and speed
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// move right
			setXDirection(SPEED);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// move left
			setXDirection(SPEED * -1);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			// move up
			setYDirection(SPEED * -1);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// move down
			setYDirection(SPEED);
			move();
		}

	}

	// Makes player 2 stop moving in that direction
	public void keyReleased(KeyEvent e) {
		// stop all movement and speed
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			setXDirection(0);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			setXDirection(0);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			setYDirection(0);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			setYDirection(0);
			move();
		}
	}

	// called whenever the movement of player 2 changes in the x-direction
	// (left/right)
	public void setXDirection(int xDirection) {
		xVelocity = xDirection;
	}

	public void setYDirection(int yDirection) {
		yVelocity = yDirection;
	}

	// updates the current location of player 2
	public void move() {
		x += xVelocity;
		y += yVelocity;
	}

	// draws the current location of player 2 to the screen
	public void draw(Graphics g) {
	/*	if (char2Num == 1) {
			g.drawImage(sorbet, x, y, 40, 40, null);
		} else if (char2Num == 2) {
			g.drawImage(smokeyb, x, y, 40, 40, null);
		} else if (char2Num == 3) {
			g.drawImage(mint, x, y, 40, 40, null);
		} */
		g.drawImage(mint, x, y, 40, 40, null);
	}

}