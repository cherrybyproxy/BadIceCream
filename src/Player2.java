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

	public BufferedImage icon;

	// constructor creates player 2 at given location with given dimensions, and the icon
	public Player2(int x, int y) {
		super(x, y, 40, 40);
		getIcon();

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

	// this method is used to get the image from source folder
	public void getIcon() {
		try {
			icon = ImageIO.read(getClass().getResourceAsStream("/MintChocChip.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of player 2 to the screen
	public void draw(Graphics g) {
		g.drawImage(icon, x, y, 40, 40, null);

	}

}