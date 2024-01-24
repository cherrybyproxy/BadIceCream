/* Leah Huang and Selvahini Kamalarajan
   January 23, 2024
   RoundWinner
    Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file, display score leaderboard and animated sprites.  */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class RoundWinner extends Rectangle {

	private static final long serialVersionUID = 1L; // add default serial id for class

	public static int GAME_WIDTH;// width of the window
	public static int GAME_HEIGHT;// height of the window

	//Declare variables for images
	public Image crown;
	public Image cross;

	public RoundWinner(int w, int h) {
		crown = Toolkit.getDefaultToolkit().getImage("Crown.png"); 
		cross = Toolkit.getDefaultToolkit().getImage("cross.png"); 

		RoundWinner.GAME_WIDTH = w;
		RoundWinner.GAME_HEIGHT = h;
	}

	public void draw(Graphics g) {
		// draw end card or end condition for player 
		Color c = new Color(248, 242, 226);
		g.setColor(c);
		g.fillRect(200, 200, 400, 300);
		g.setColor(Color.black);
		g.setFont(new Font("Consolas", Font.PLAIN, 25)); // set font
		
		g.drawString(String.valueOf(Score.score), (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.65));
		g.drawString(String.valueOf(Score.score2), (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.65));
		g.setFont(new Font("Consolas", Font.PLAIN, 35)); // set font

		// determine winner based on score
		if (Player1.isMelted && Player2.isMelted) {
			g.drawString("TOTAL MELTDOWN", (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.37));
			g.drawImage(cross, (int) (GAME_WIDTH * 0.365), (int) (GAME_HEIGHT * 0.43), 30, 30, null);
			g.drawImage(cross, (int) (GAME_WIDTH * 0.615), (int) (GAME_HEIGHT * 0.43), 30, 30, null);
		}
		//determine winenr based on scores
		else if (Score.score > Score.score2) {
			//displayer player 1 wins
			g.drawString("Player 1 WIN!", (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.37));
			g.drawImage(crown, (int) (GAME_WIDTH * 0.365), (int) (GAME_HEIGHT * 0.43), 30, 30, null);
			
		} else if (Score.score < Score.score2) {
			//display player 2 wins
			g.drawString("Player 2 WIN!", (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.37));
			g.drawImage(crown, (int) (GAME_WIDTH * 0.615), (int) (GAME_HEIGHT * 0.43), 30, 30, null);
			
		} else { //display a draw
			g.drawString("DRAW", (int) (GAME_WIDTH * 0.5), (int) (GAME_HEIGHT * 0.35));
		}

	}
}