/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Mint
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Mint extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	// variable declarations for velocity and speed of player 1
	public int xVelocity;
	public int yVelocity;

	public final int SPEED = 3; // movement speed
	public int spriteCounter =0;
	public int spriteNum = 1;
	
	public String direction; // direction of sprite
	
	public Image up1, up2, down1, down2, left1, left2, right1, right2;

	// constructor creates player 2 at given location with given dimensions, and the icon
	public Mint(int x, int y) {
		super(x, y, 40, 40);


		down1 = Toolkit.getDefaultToolkit().getImage("down1.png"); // create image for ball
		down2 = Toolkit.getDefaultToolkit().getImage("down2.png"); // create image for ball


	}

	// updates the direction of player 2 based on user input
	public void keyPressed(KeyEvent e) {
		// set movement and speed
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			// move right
			direction = "right";
			setXDirection(SPEED);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			// move left
			direction = "left";
			setXDirection(SPEED * -1);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			// move up
			direction = "up";
			setYDirection(SPEED*-1);
			move();
		}

		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			// move down
			direction = "down";
			setYDirection(SPEED);
			move();
		}
		spriteCounter++;
		if (spriteCounter > 10) {
			if (spriteNum ==1) {
				spriteNum =2;
			} else if (spriteNum ==2) {
				spriteNum =1;
			}
			spriteCounter =0;
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
		
		
		g.drawImage(down1, x, y, 50, 50, null);
			
		g.drawImage(down2, x, y, 50, 50, null);
	
	}

}