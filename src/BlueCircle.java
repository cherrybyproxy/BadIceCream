/* Leah Huang and Selvahini Kamalarajan
   January 12, 2024
   BlueCircle
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

//import packages
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlueCircle extends Rectangle { // class extends from Rectangle

	private static final long serialVersionUID = 1L;

	public BlueCircle(int x, int y) { // constructor
		super(x, y, 50, 50);
	}

	public void draw(Graphics g) {
		// draw graphics to screen
		Graphics2D g2 = (Graphics2D) g;
		Color c2 = new Color(45, 222, 246);
		g2.setColor(c2);
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(x, y, 50, 50);
	}
}
