import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlueCircle extends Rectangle {
	
	public BlueCircle(int x, int y){
		super(x, y, 50, 50);
	}
	
	public void draw(Graphics g) {
		  Graphics2D g2 = (Graphics2D) g;
		  Color c2 = new Color (45,222,246);
		  g2.setColor(c2);
		  g2.setStroke(new BasicStroke(5));
		  g2.drawRect(x, y, 50, 50);
	}
}
