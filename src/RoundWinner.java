import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class RoundWinner extends Rectangle {
	public static int GAME_WIDTH;//width of the window
	public static int GAME_HEIGHT;//height of the window
	public BufferedImage player1Img;
	public BufferedImage player2Img;
	public BufferedImage crown;
	
	public RoundWinner(int w, int h){
		RoundWinner.GAME_WIDTH = w;
		RoundWinner.GAME_HEIGHT = h;
		getIcon();
	}
	
	public void getIcon() {
		  try {
			  player1Img = ImageIO.read(getClass().getResourceAsStream("/Sorbet.png"));
			  player2Img = ImageIO.read(getClass().getResourceAsStream("/MintChocChip.png"));
			  crown = ImageIO.read(getClass().getResourceAsStream("/Crown.png"));
		  }
		  catch(IOException e) {
			  e.printStackTrace();
		  }
	  }
	
	public void draw(Graphics g) {
		Color c = new Color(248,242,226);
		g.setColor(c);
	    g.fillRect(200, 200, 400, 300); 
	    g.setColor(Color.black);
	    g.setFont(new Font("Consolas", Font.PLAIN, 25)); // set font
	    g.drawImage(player1Img, (int)(GAME_WIDTH*0.35), (int)(GAME_HEIGHT*0.51), 50, 50, null);
	    g.drawImage(player2Img, (int)(GAME_WIDTH*0.6), (int)(GAME_HEIGHT*0.51), 50, 50, null);
	    g.drawString(String.valueOf(Score.score), (int)(GAME_WIDTH*0.35), (int)(GAME_HEIGHT*0.65));
	    g.drawString(String.valueOf(Score.score2), (int)(GAME_WIDTH*0.6), (int)(GAME_HEIGHT*0.65));
	    g.setFont(new Font("Consolas", Font.PLAIN, 35)); // set font
	    if(Score.score > Score.score2) {
		    g.drawString("Player 1 WIN!", (int)(GAME_WIDTH*0.35), (int)(GAME_HEIGHT*0.37));
		    g.drawImage(crown, (int)(GAME_WIDTH*0.365), (int)(GAME_HEIGHT*0.43), 30, 30, null);
	    }
	    else if (Score.score < Score.score2) {
	    	g.drawString("Player 2 WIN!", (int)(GAME_WIDTH*0.35), (int)(GAME_HEIGHT*0.37));
	    	g.drawImage(crown, (int)(GAME_WIDTH*0.615), (int)(GAME_HEIGHT*0.43), 30, 30, null);
	    }
	    else {
	    	g.drawString("DRAW", (int)(GAME_WIDTH*0.5), (int)(GAME_HEIGHT*0.35));
	    }

	}
}
