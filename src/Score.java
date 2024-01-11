/*
 *  Leah Huang
 *  ICS 4U1
 *  Dec 18, 2023
 *  
 *  Score - keeps the score of player 1
 */

import java.awt.*;

// extends Rectangle because its easy to draw
public class Score extends Rectangle{

	// variable declaration
  public static int GAME_WIDTH;//width of the window
  public static int GAME_HEIGHT;//height of the window
  public static int score;
  public static int score2;

  //constructor sets score to 0 and establishes dimensions of game window
  public Score(int w, int h){
    score = 0;
    Score.GAME_WIDTH = w;
    Score.GAME_HEIGHT = h;
  }

  //called frequently from GamePanel class
  //updates the current score and draws it to the screen
  public void draw(Graphics g){
    g.setColor(Color.black); // set colour
    g.setFont(new Font("Consolas", Font.PLAIN, 50)); // set font
    g.drawString(String.valueOf(score), (int)(GAME_WIDTH*0.2), (int)(GAME_HEIGHT*0.2)); 
    g.drawString(String.valueOf(score2), (int)(GAME_WIDTH*0.5), (int)(GAME_HEIGHT*0.2)); 
  }
}
