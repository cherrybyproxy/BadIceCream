/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   BlueCircle
   BlueCircle class draws the blue circle graphics in GamePanel. 
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

//import packages
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlueCircle extends Rectangle { // class extends from Rectangle

	private static final long serialVersionUID = 1L; //create private serial ID

	public BlueCircle(int x, int y) { // override constructor
		super(x, y, 50, 50); 
	}

	public void draw(Graphics g) {
		// draw Blue Circle graphics to inventory bar in Level 1 and 2
		Graphics2D g2 = (Graphics2D) g;
		Color c2 = new Color(45, 222, 246);
		g2.setColor(c2);
		//blue circle graphic indicates the fruits that need to be collected during a level
		g2.setStroke(new BasicStroke(5));
		g2.drawRect(x, y, 50, 50); //draw blue rectangle in Inventory Bar
	}
}
