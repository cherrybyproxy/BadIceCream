/* Leah Huang and Selvahini Kamalarajan
   January 18, 2024
   GameFrame
   GameFrame class establishes the frame (window) for the game
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game and score. */

import java.awt.*;
import javax.swing.*;

// extends JFrame to access the methods for creating frames
public class GameFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	GamePanel panel; //runs the constructor in GamePanel class 

	public GameFrame() {// Start of 'GameFrame' class

		// JFrame setup for window
		panel = new GamePanel(); // run GamePanel constructor
		
		this.add(panel);
		
		this.setTitle("Bad Ice Cream"); // set title for frame

		this.setResizable(false); // frame can't change size

		this.setBackground(Color.white); // set background colour

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // X button will stop program execution

		this.pack();// makes components fit in window 
		
		this.setVisible(true); // makes window visible to user

		this.setLocationRelativeTo(null);// set window in middle of screen
	}

}