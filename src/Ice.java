/* Leah Huang and Selvahini Kamalarajan
   January 12, 2024
   Ice
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// extends Rectangle because drawing and managing collisions is easy
public class Ice extends Rectangle {

	private static final long serialVersionUID = 1L;
	
	public BufferedImage iceImg;
	public BufferedImage iglooImg;
	public BufferedImage bananaImg;
	public BufferedImage grapeImg;

	// constructor creates ball at given location with given dimensions, and the
	// iceImg
	public Ice(int x, int y) {
		super(x, y, 50, 50);
		getIcon();

	}

	// this method is used to get the image from source folder
	public void getIcon() {

		try {
			iceImg = ImageIO.read(getClass().getResourceAsStream("/Ice.png"));
			iglooImg = ImageIO.read(getClass().getResourceAsStream("/Igloo.png"));
			bananaImg = ImageIO.read(getClass().getResourceAsStream("Banana.png"));
			grapeImg = ImageIO.read(getClass().getResourceAsStream("Grape.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// draws the current location of the ball to the screen
	public void draw(Graphics g) {
		// create graphics objects to display
		Graphics2D g2 = (Graphics2D) g;
		Color c2 = new Color(74, 171, 203);
		g2.setColor(c2);
		g2.setStroke(new BasicStroke(100));
		g2.drawRect(0, 0, 800, 700);

		// create ice border on the left
		g.drawImage(iceImg, 50, 50, 50, 50, null);
		g.drawImage(iceImg, 50, 100, 50, 50, null);
		g.drawImage(iceImg, 50, 100, 50, 50, null);
		g.drawImage(iceImg, 50, 150, 50, 50, null);
		g.drawImage(iceImg, 50, 200, 50, 50, null);
		g.drawImage(iceImg, 50, 250, 50, 50, null);
		g.drawImage(iceImg, 50, 300, 50, 50, null);
		g.drawImage(iceImg, 50, 350, 50, 50, null);
		g.drawImage(iceImg, 50, 400, 50, 50, null);
		g.drawImage(iceImg, 50, 450, 50, 50, null);
		g.drawImage(iceImg, 50, 500, 50, 50, null);
		g.drawImage(iceImg, 50, 550, 50, 50, null);
		g.drawImage(iceImg, 50, 600, 50, 50, null);

		// create ice border on right
		g.drawImage(iceImg, 700, 50, 50, 50, null);
		g.drawImage(iceImg, 700, 100, 50, 50, null);
		g.drawImage(iceImg, 700, 100, 50, 50, null);
		g.drawImage(iceImg, 700, 150, 50, 50, null);
		g.drawImage(iceImg, 700, 200, 50, 50, null);
		g.drawImage(iceImg, 700, 250, 50, 50, null);
		g.drawImage(iceImg, 700, 300, 50, 50, null);
		g.drawImage(iceImg, 700, 350, 50, 50, null);
		g.drawImage(iceImg, 700, 400, 50, 50, null);
		g.drawImage(iceImg, 700, 450, 50, 50, null);
		g.drawImage(iceImg, 700, 500, 50, 50, null);
		g.drawImage(iceImg, 700, 550, 50, 50, null);
		g.drawImage(iceImg, 700, 600, 50, 50, null);

		// create ice border on top of screen
		g.drawImage(iceImg, 100, 50, 50, 50, null);
		g.drawImage(iceImg, 150, 50, 50, 50, null);
		g.drawImage(iceImg, 200, 50, 50, 50, null);
		g.drawImage(iceImg, 250, 50, 50, 50, null);
		g.drawImage(iceImg, 300, 50, 50, 50, null);
		g.drawImage(iceImg, 350, 50, 50, 50, null);
		g.drawImage(iceImg, 400, 50, 50, 50, null);
		g.drawImage(iceImg, 450, 50, 50, 50, null);
		g.drawImage(iceImg, 500, 50, 50, 50, null);
		g.drawImage(iceImg, 550, 50, 50, 50, null);
		g.drawImage(iceImg, 600, 50, 50, 50, null);
		g.drawImage(iceImg, 650, 50, 50, 50, null);

		// create ice border on bottom
		g.drawImage(iceImg, 100, 600, 50, 50, null);
		g.drawImage(iceImg, 150, 600, 50, 50, null);
		g.drawImage(iceImg, 200, 600, 50, 50, null);
		g.drawImage(iceImg, 250, 600, 50, 50, null);
		g.drawImage(iceImg, 300, 600, 50, 50, null);
		g.drawImage(iceImg, 350, 600, 50, 50, null);
		g.drawImage(iceImg, 400, 600, 50, 50, null);
		g.drawImage(iceImg, 450, 600, 50, 50, null);
		g.drawImage(iceImg, 500, 600, 50, 50, null);
		g.drawImage(iceImg, 550, 600, 50, 50, null);
		g.drawImage(iceImg, 600, 600, 50, 50, null);
		g.drawImage(iceImg, 650, 600, 50, 50, null);

		// create additional graphics objects
		Graphics2D g4 = (Graphics2D) g;
		Color c4 = new Color(248, 242, 226);
		g4.setColor(c4);
		g4.fillRect(175, 625, 450, 70);

		Graphics2D g3 = (Graphics2D) g;
		Color c3 = new Color(249, 186, 47);
		g3.setColor(c3);
		g3.setStroke(new BasicStroke(10));
		g3.drawRect(175, 625, 450, 70);

		// draw images to screen
		g.drawImage(bananaImg, 350, 640, 40, 40, null);
		g.drawImage(grapeImg, 400, 640, 40, 40, null);
	}

}