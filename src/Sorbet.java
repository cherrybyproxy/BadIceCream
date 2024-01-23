/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   Mint
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.event.*;

import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

// extends Rectangle because drawing and managing collisions is easy
public class Sorbet extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	// variable declarations for velocity and speed of player 1
	public int xVelocity;
	public int yVelocity;
	
	KeyHandler keyH;

	public final int SPEED = 4; // movement speed
	public int spriteCounter = 0;
	public int spriteNum = 1;
	int charNum;

	boolean canRight = true, canLeft = true, canUp = true, canDown = true;

	int level = 1;

	public String direction; // direction of sprite

	public Image up1, up2, down1, down2, left1, left2, right1, right2;

	// constructor creates player 2 at given location with given dimensions, and the
	// icon
	public Sorbet(int x, int y, int num) {
		super(x, y, 40, 40);

		charNum = num;

		down1 = Toolkit.getDefaultToolkit().getImage("sdown1.png");
		down2 = Toolkit.getDefaultToolkit().getImage("sdown2.png");
		up1 = Toolkit.getDefaultToolkit().getImage("sback1.png");
		up2 = Toolkit.getDefaultToolkit().getImage("sback2.png");
		left1 = Toolkit.getDefaultToolkit().getImage("sleft1.png");
		left2 = Toolkit.getDefaultToolkit().getImage("sleft2.png");
		right1 = Toolkit.getDefaultToolkit().getImage("sright1.png");
		right2 = Toolkit.getDefaultToolkit().getImage("sright2.png");

		direction = "down";
		
		this.keyH = keyH;
	}

	// updates the direction of player 2 based on user input
	public void update(KeyEvent e) {
		// set movement and speed

		if (charNum == 2) { // player 2
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
				setYDirection(SPEED * -1);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				// move down
				direction = "down";
				setYDirection(SPEED);
				move();
			}
			spriteCounter++;
			if (spriteCounter > 5) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		if (charNum == 1) { //player 1
			if (e.getKeyCode() == KeyEvent.VK_D) {
				// move right
				direction = "right";
				setXDirection(SPEED);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				// move left
				direction = "left";
				setXDirection(SPEED * -1);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_W) {
				// move up
				direction = "up";
				setYDirection(SPEED * -1);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				// move down
				direction = "down";
				setYDirection(SPEED);
				move();
			}
			spriteCounter++;
			if (spriteCounter > 5) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	// Makes player 2 stop moving in that direction
	public void keyReleased(KeyEvent e) {
		// stop all movement and speed

		if (charNum == 2) {
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
		if (charNum == 1) {
			if (e.getKeyCode() == KeyEvent.VK_D) {
				setXDirection(0);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_A) {
				setXDirection(0);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_W) {
				setYDirection(0);
				move();
			}

			if (e.getKeyCode() == KeyEvent.VK_S) {
				setYDirection(0);
				move();
			}
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
		if (level == 1) {
			if (y <= 100 && yVelocity < 0) {
				canUp = false;
			} else if (y >= 550 && yVelocity > 0) {
				canDown = false;
			} else if (x <= 100 && xVelocity < 0) {
				canLeft = false;
			} else if (x >= 650 && xVelocity > 0) {
				canRight = false;
			}
			// column
			else if (x >= 150 && x < 250 && xVelocity > 0 && (y > 150 && y < 500)) {
				canRight = false;
			} else if (x > 150 && x <= 250 && xVelocity < 0 && (y > 200 && y < 450)) {
				canLeft = false;
			}

			// top row
			else if (y >= 150 && y < 250 && yVelocity > 0 && (x > 150 && x < 350)) {
				canDown = false;
			} else if (y > 150 && y <= 250 && yVelocity < 0 && (x > 200 && x < 350)) {
				canUp = false;
			} else if (x >= 300 && x <= 350 && y > 150 && y < 250 && xVelocity < 0) {
				canLeft = false;
			}

			// bottom row
			else if (y >= 400 && y < 500 && yVelocity > 0 && (x > 150 && x < 350)) {
				canDown = false;
			} else if (y > 450 && y <= 500 && yVelocity < 0 && (x > 150 && x < 350)) {
				canUp = false;
			} else if (x <= 350 && x > 150 && y > 400 && y < 500 && xVelocity < 0) {
				canLeft = false;
			}

			// column 2
			else if (x >= 500 && x < 600 && xVelocity > 0 && (y > 200 && y < 450)) {
				canRight = false;
			} else if (x > 500 && x <= 600 && xVelocity < 0 && (y > 150 && y < 500)) {
				canLeft = false;
			}

			// top row 2
			else if (y >= 150 && y < 250 && yVelocity > 0 && (x > 400 && x < 600)) {
				canDown = false;
			} else if (y > 150 && y <= 250 && yVelocity < 0 && (x > 400 && x < 550)) {
				canUp = false;
			} else if (x >= 400 && x <= 450 && y > 150 && y < 250 && xVelocity > 0) {
				canRight = false;
			}

			// bottom row 2
			else if (y >= 400 && y < 500 && yVelocity > 0 && (x > 400 && x < 550)) {
				canDown = false;
			} else if (y > 450 && y <= 500 && yVelocity < 0 && (x > 400 && x < 600)) {
				canUp = false;
			} else if (x >= 400 && x <= 450 && y > 400 && y < 500 && xVelocity > 0) {
				canRight = false;
			}

			// igloo
			else if (x > 350 && x <= 450 && y > 250 && y < 350 && xVelocity < 0) {
				canLeft = false;
			} else if (x >= 300 && x < 450 && y > 250 && y < 350 && xVelocity > 0) {
				canRight = false;
			}

			else if (x > 300 && x < 450 && y > 250 && y <= 350 && yVelocity < 0) {
				canUp = false;
			} else if (x > 300 && x < 450 && y >= 250 && y < 350 && yVelocity > 0) {
				canDown = false;
			} else {
				x += xVelocity;
				y += yVelocity;
			}
			/*
			 * else { canUp = true; canDown = true; canRight = true; canLeft = true; } if
			 * (canUp && canDown) { y += yVelocity; } if (canRight && canLeft) { x +=
			 * xVelocity; }
			 */

		}
		if (level == 2) {
			if (y <= 100 && yVelocity < 0) {
				canUp = false;
			} else if (y >= 550 && yVelocity > 0) {
				canDown = false;
			} else if (x <= 100 && xVelocity < 0) {
				canLeft = false;
			} else if (x >= 650 && xVelocity > 0) {
				canRight = false;
			}

			// column 1
			else if (x >= 100 && x < 200 && y > 150 && y < 500 && xVelocity > 0) {
				canRight = false;
			} else if (x > 100 && x <= 200 && y > 150 && y < 500 && xVelocity < 0) {
				canLeft = false;
			} else if (x > 100 && x < 200 && y > 150 && y <= 500 && yVelocity < 0) {
				canUp = false;
			} else if (x > 100 && x < 200 && y >= 150 && y < 500 && yVelocity > 0) {
				canDown = false;
			}

			// column 2
			else if (x >= 250 && x < 350 && y > 150 && y < 500 && xVelocity > 0) {
				canRight = false;
			} else if (x > 250 && x <= 350 && y > 150 && y < 500 && xVelocity < 0) {
				canLeft = false;
			} else if (x > 250 && x < 350 && y > 150 && y <= 500 && yVelocity < 0) {
				canUp = false;
			} else if (x > 250 && x < 350 && y >= 150 && y < 500 && yVelocity > 0) {
				canDown = false;
			}

			// column 3
			else if (x >= 400 && x < 500 && y > 150 && y < 500 && xVelocity > 0) {
				canRight = false;
			} else if (x > 400 && x <= 500 && y > 150 && y < 500 && xVelocity < 0) {
				canLeft = false;
			} else if (x > 400 && x < 500 && y > 150 && y <= 500 && yVelocity < 0) {
				canUp = false;
			} else if (x > 400 && x < 500 && y >= 150 && y < 500 && yVelocity > 0) {
				canDown = false;
			}

			// column 4
			else if (x >= 550 && x < 650 && y > 150 && y < 500 && xVelocity > 0) {
				canRight = false;
			} else if (x > 550 && x <= 650 && y > 150 && y < 500 && xVelocity < 0) {
				canLeft = false;
			} else if (x > 550 && x < 650 && y > 150 && y <= 500 && yVelocity < 0) {
				canUp = false;
			} else if (x > 550 && x < 650 && y >= 150 && y < 500 && yVelocity > 0) {
				canDown = false;
			}

			else {
				x += xVelocity;
				y += yVelocity;
			}
		}
	}

	// draws the current location of player 2 to the screen
	public void draw(Graphics g2) {

		Image image = null;

		switch (direction) {
		case "down":
			if (spriteNum ==1) {
			image = down1;
			} if (spriteNum ==2) {
				image = down2;	
			}
			break;

		case "up":
			if (spriteNum ==1) {
			image = up1;
			}if (spriteNum ==2) {
				image = up2;
			}
			
			break;

		case "left":
			if (spriteNum ==1) {
			image = left1;
			}if (spriteNum ==2) {
				image = left2;
			}
			break;

		case "right":
			if (spriteNum ==1) {
			image = right1;
			}if (spriteNum ==2) {
				image = right2;
			}
			break;

		}
		g2.drawImage(image, x, y, 40, 40, null);
	}
}