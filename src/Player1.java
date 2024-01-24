/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   Player1
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

import java.awt.*;
import java.awt.event.*;

// extends Rectangle because drawing and managing collisions is easy
public class Player1 extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	// Variable declarations
	public int xVelocity;

	public int yVelocity; // sets velocity in vertical direction

	public final int SPEED = 2; // movement speed of player 1

	//variables for sprite counters
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public static boolean isMelted = false; //set to false
	
	public String direction; // direction of sprite
	
	public int charNum; // stores value for each character

	//stores sprite images for player 1
	public Image sdown1, sdown2, sup1, sup2, sleft1, sleft2, sright1, sright2, smelted, bdown1, bdown2,
	bup, bdown, bleft, bright, bmelted, mup1, mup2, mdown1, mdown2, mleft1, mleft2, mright1, mright2, mmelted; // create image for player 1

	boolean canRight = true, canLeft = true, canUp = true, canDown = true;
	
	int level = 1; //player 1 level

	// constructor creates player 1 at given location with given dimensions, and the  icon
	public Player1(int x, int y, int num) {
		super(x, y, 40, 40);

		//store sprite images for sorbet
		sdown1 = Toolkit.getDefaultToolkit().getImage("sdown1.png");
		sdown2 = Toolkit.getDefaultToolkit().getImage("sdown2.png");
		sup1 = Toolkit.getDefaultToolkit().getImage("sback1.png");
		sup2 = Toolkit.getDefaultToolkit().getImage("sback2.png");
		sleft1 = Toolkit.getDefaultToolkit().getImage("sleft1.png");
		sleft2 = Toolkit.getDefaultToolkit().getImage("sleft2.png");
		sright1 = Toolkit.getDefaultToolkit().getImage("sright1.png");
		sright2 = Toolkit.getDefaultToolkit().getImage("sright2.png");
		smelted = Toolkit.getDefaultToolkit().getImage("SorbetMelted.png");

		//store sprite images for smokey bacon
		bdown1 = Toolkit.getDefaultToolkit().getImage("bdown1.png");
		bdown2 = Toolkit.getDefaultToolkit().getImage("bdown2.png");
		bup = Toolkit.getDefaultToolkit().getImage("bback.png");
		bleft = Toolkit.getDefaultToolkit().getImage("bleft.png");
		bright = Toolkit.getDefaultToolkit().getImage("b-right.png");
		bmelted = Toolkit.getDefaultToolkit().getImage("SmokeyBMelted.png");
		
		//store sprite images for mint choc chip
		mdown1 = Toolkit.getDefaultToolkit().getImage("down1.png"); 
		mdown2 = Toolkit.getDefaultToolkit().getImage("down2.png"); 
		mup1 = Toolkit.getDefaultToolkit().getImage("up1.png");
		mup2 = Toolkit.getDefaultToolkit().getImage("up2.png"); 
		mleft1 = Toolkit.getDefaultToolkit().getImage("left1.png");
		mleft2 = Toolkit.getDefaultToolkit().getImage("left2.png"); 
		mright1 = Toolkit.getDefaultToolkit().getImage("right1.png"); 
		mright2 = Toolkit.getDefaultToolkit().getImage("right2.png"); 
		mmelted = Toolkit.getDefaultToolkit().getImage("MintMelted.png");
		
		charNum = num; //store character number
		
		direction = "down"; //store movement direction
	}

	// called from GamePanel when any keyboard input is detected
	public void keyPressed(KeyEvent e) {
		
		if (!GamePanel.melted1) {
			// controls for player 1
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
			// restrict player 1 movement for column
			else if (x >= 150 && x < 250 && xVelocity > 0 && (y > 150 && y < 500)) {
				canRight = false;
			}
			else if (x > 150 && x <= 250 && xVelocity < 0 && (y > 200 && y < 450)) {
				canLeft = false;
			}
			
			//restrict player 1 movement in top row
			else if (y >= 150 && y < 250 && yVelocity > 0 && (x > 150 && x < 350)) {
				canDown = false;
			}
			else if (y > 150 && y <= 250 && yVelocity < 0 && (x > 200 && x < 350)) {
				canUp = false;
			}
			else if (x >= 300 && x <= 350 && y > 150 && y < 250 && xVelocity < 0) {
				canLeft = false;
			}
			
			//restrict player 1 movement in bottom row
			else if (y >= 400 && y < 500 && yVelocity > 0 && (x > 150 && x < 350)) {
				canDown = false;
			}
			else if (y > 450 && y <= 500 && yVelocity < 0 && (x > 150 && x < 350)) {
				canUp = false;
			}
			else if (x <= 350 && x > 150 && y > 400 && y < 500 && xVelocity < 0 ) {
				canLeft = false;
			}
			
			// restrict player 1 movement in column 2
			else if (x >= 500 && x < 600 && xVelocity > 0 && (y > 200 && y < 450)) {
				canRight = false;
			}
			else if (x > 500 && x <= 600 && xVelocity < 0 && (y > 150 && y < 500)) {
				canLeft = false;
			}
			
			//restrict player 1 movement in top row 2
			else if (y >= 150 && y < 250 && yVelocity > 0 && (x > 400 && x < 600)) {
				canDown = false;
			}
			else if (y > 150 && y <= 250 && yVelocity < 0 && (x > 400 && x < 550)) {
				canUp = false;
			}
			else if (x >= 400 && x <= 450 && y > 150 && y < 250 && xVelocity > 0) {
				canRight = false;
			}
			
			//restrict player 1 movement  in bottom row 2
			else if (y >= 400 && y < 500 && yVelocity > 0 && (x > 400 && x < 550)) {
				canDown = false;
			}
			else if (y > 450 && y <= 500 && yVelocity < 0 && (x > 400 && x < 600)) {
				canUp = false;
			}
			else if (x >= 400 && x <= 450 && y > 400 && y < 500 && xVelocity > 0 ) {
				canRight = false;
			}
			
			// collision checker between player 1 and igloo
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
				//player 1 moves as normal
				x += xVelocity;
				y += yVelocity;
			}

			
		}
		if (level == 2) {
			//collision checker for level 2 objects and player 1
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
			
			// restrict player 1 movement for column 1
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
			
			// restrict player 1 movement in column 2
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
			
			// restrict player 1 movement in column 3
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
			
			// restrict player 1 movement in column 4
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
				//player 1 moves as normal
				x += xVelocity;
				y += yVelocity;
			}
		}
	}
	
	// draws the current location of player 1 to the screen
	public void draw(Graphics g) {
		// draw image to screen based on direction
		Image image = null;
		
		if (GamePanel.melted1 == true) {
			direction = "melted";
		}
		
		if (charNum == 1) { //sorbet character movement
			switch (direction) {
			case "down":
				if (spriteNum ==1) {
				image = sdown1;
				g.drawImage(image, x, y, 50, 50, null);
				} if (spriteNum ==2) {
					image = sdown2;	
					g.drawImage(image, x, y, 50, 50, null);
				}
				break;

			case "up":
				if (spriteNum ==1) {
				image = sup1;
				g.drawImage(image, x, y, 50, 50, null);
				}if (spriteNum ==2) {
					image = sup2;
					g.drawImage(image, x, y, 50, 50, null);
				}
				
				break;

			case "left":
				if (spriteNum ==1) {
				image = sleft1;
				g.drawImage(image, x, y, 50, 50, null);
				}if (spriteNum ==2) {
					image = sleft2;
					g.drawImage(image, x, y, 50, 50, null);
				}
				break;

			case "right":
				if (spriteNum ==1) {
				image = sright1;
				g.drawImage(image, x, y, 50, 50, null);
				}if (spriteNum ==2) {
					image = sright2;
					g.drawImage(image, x, y, 50, 50, null);
				}
				break;
				
			case "melted":
				image = smelted;
				g.drawImage(image, x, y, 50, 50, null);
				isMelted = true;
				break;
			}
			
		} else if (charNum == 2) { //smokey bacon character movement
			switch (direction) {
			case "down":
				if (spriteNum == 1) {
					image = bdown1;
					g.drawImage(image, x, y, 55, 55, null);
				}
				if (spriteNum == 2) {
					image = bdown2;
					g.drawImage(image, x, y, 55, 55, null);
				}
				break;

			case "up":

				image = bup;
				g.drawImage(image, x, y, 50, 50, null);
				break;

			case "left":
				image = bleft;
				g.drawImage(image, x, y, 50, 50, null);
				break;

			case "right":
				image = bright;
				g.drawImage(image, x, y, 50, 50, null);
				break;
				
			case "melted":
				image = bmelted;
				g.drawImage(image, x, y, 50, 50, null);
				isMelted = true;
				break;

			}
		} else if (charNum == 3) { //mint choc chip character movement
			switch (direction) {
			case "down":
				if (spriteNum ==1) {
				image = mdown1;
				g.drawImage(image, x, y, 50, 50, null);
				} if (spriteNum ==2) {
					image =mdown2;	
					g.drawImage(image, x, y, 50, 50, null);
				}
				break;

			case "up":
				if (spriteNum ==1) {
				image =mup1;
				g.drawImage(image, x, y, 50, 50, null);
				}if (spriteNum ==2) {
					image = mup2;
					g.drawImage(image, x, y, 50, 50, null);
				}
				
				break;

			case "left":
				if (spriteNum ==1) {
				image = mleft1;
				g.drawImage(image, x, y, 50, 50, null);
				}if (spriteNum ==2) {
					image = mleft2;
					g.drawImage(image, x, y, 50, 50, null);
				}
				break;

			case "right":
				if (spriteNum ==1) {
				image = mright1;
				g.drawImage(image, x, y, 50, 50, null);
				}if (spriteNum ==2) {
					image = mright2;
					g.drawImage(image, x, y, 50, 50, null);
				}
				break;
				
			case "melted":
				image = mmelted;
				g.drawImage(image, x, y, 50, 50, null);
				isMelted = true;
				break;

			}
		} 
	}
	
}