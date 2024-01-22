/* Leah Huang and Selvahini Kamalarajan
   January 22, 2024
   GamePanel
   GamePanel class acts as the main "game loop" and continuously runs the game.
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 and 2 of game,
   saving top five high scores to text file and display score leaderboard. */

//import packages for GUI, swing and files
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
//import packages for audio input system
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
//import packages for file reader
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener, MouseMotionListener {
// Child of JPanel and implements KeyListener and Runnable interface

	private static final long serialVersionUID = 1L; // add default serial id for class

	// dimensions of window
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 700;

	// variable and object declarations and initializations
	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public Ice ice;
	public Level2Scenery level2Scenery;
	public Player1 player1;
	public Player2 player2;
	public Cow cow;
	public Score score;
	public Score score2;
	public BlueCircle blueCircle;
	public BlueCircle blueCircle2;
	public BlueCircle blueCircle3;
	public BlueCircle blueCircle4;
	public RoundWinner roundWinner;

	public ArrayList<Integer> recentScores; // array list to store all scores

	//variables to store images 
	public Image logo, menubg, playbtn, authors, settings, menu, mint, sound, cross, playerControls, icecream, snow, backbtn, scores, exitgame, 
	char1, char2, smokeyb, title,confirmBtn, sorbet, downArrow, sorbetMenu, mintMenu, smokeyMenu, error, loading, gameControls;

	//variables for sorting array list
	int temp;
	boolean sorted = false;

	// booleans for game conditions
	public boolean playGame, exitGame, cornerControls, mainMenu, controls, drawBtn, scoreBoard, continueBtn, level1, level2, nextLevel, nextLevel2, returnMain, selectionMenu,
			leftHover, rightHover, left2Hover, right2Hover, character1, character2, charError, displayChar, melted1,
			melted2;

	static boolean audio; 

	// coordinates for play button on main menu
	int btnX;
	int btnY;
	
	public boolean [] icecreamPics = new boolean[4];
	public boolean[] charSelection = new boolean[6];
	public boolean[] charSelection2 = new boolean[6];
	public String[] charSelect = new String[3];

	// Declare the Clip at the class level
	private static Clip clip;
	
	int totalScore1, totalScore2;;

	public Igloo igloo;

// Array List to store ice cubes
	public ArrayList<IceC> iceC2 = new ArrayList<IceC>();
	public int[] iceCX = new int[20];
	public int[] iceCY = new int[20];

	public boolean[] drawBanana = new boolean[16];
	public ArrayList<Banana> bananaCoord = new ArrayList<Banana>();
	public int onBanana = 0;

	public boolean[] drawGrape = new boolean[12];
	public ArrayList<Grape> grapeCoord = new ArrayList<Grape>();
	public int onGrape = 0;

	public ArrayList<Level2Ice> level2Ice = new ArrayList<Level2Ice>();
	public int[] level2IceX = new int[12];
	public int[] level2IceY = new int[12];

	public boolean[] drawBanana2 = new boolean[20];
	public ArrayList<Banana> bananaCoord2 = new ArrayList<Banana>();
	public int onBanana2 = 0;

	public boolean[] drawGrape2 = new boolean[8];
	public ArrayList<Grape> grapeCoord2 = new ArrayList<Grape>();
	public int onGrape2 = 0;

	public GamePanel() { // constructor sets values for booleans and creates game elements

		playMusic("menu music.wav");
		// set game condition booleans
		playGame = false;
		selectionMenu = false;
		level1 = false;
		level2 = false;
		nextLevel = false;
		exitGame = false;
		mainMenu = false;
		controls = false;
		scoreBoard = false;
		nextLevel2 = false;
		returnMain = false;
		audio = true;
		continueBtn = false;

		cornerControls = true;
		charError = false;
		displayChar = false;

		melted1 = false;
		melted2 = false;

		totalScore1 = 0;
		totalScore2 = 0;

		//set boolean arrays to false
		for (int i = 0; i < charSelection.length; i++) {
			//conditions for character selection menu 
			charSelection[i] = false;
		}
		for (int i = 0; i < charSelection2.length; i++) {
			//conditions for character selection menu 
			charSelection2[i] = false;
		}
		for (int i = 0; i < icecreamPics.length; i++) {
			// set booleans for ice cream graphics
			icecreamPics[i] = false;
		}
		// create arrays to store scores
		recentScores = new ArrayList<Integer>();

		// fill array list with temporary coordinates
		recentScores.add(0);
		recentScores.add(0);
		recentScores.add(0);
		recentScores.add(0);
		recentScores.add(0);
		recentScores.add(0);

		sorted = false;

		createFile();

// create graphics and set start location on game screen
		ice = new Ice(0, 0);
		level2Scenery = new Level2Scenery(0, 0);

		// create blue circle graphic
		blueCircle = new BlueCircle(345, 635);
		blueCircle2 = new BlueCircle(395, 635);
		blueCircle3 = new BlueCircle(345, 635);
		blueCircle4 = new BlueCircle(395, 635);

		// create ice cream characters and score
		player1 = new Player1(350, 400, 1);
		player2 = new Player2(400, 400, 1);
		cow = new Cow(650, 100);

		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		score2 = new Score(GAME_WIDTH, GAME_HEIGHT);

		roundWinner = new RoundWinner(GAME_WIDTH, GAME_HEIGHT);
		igloo = new Igloo(350, 300);

// fill Array lists with fruit objects
		Arrays.fill(drawBanana, true);
		Arrays.fill(drawGrape, true);

		Arrays.fill(drawBanana2, true);
		Arrays.fill(drawGrape2, true);
		
		//handle audio input
	/*	try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("menu music.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } */


// coordinates of ice Cs
		iceC2.add(new IceC(200, 200));
		iceC2.add(new IceC(250, 200));
		iceC2.add(new IceC(300, 200));
		iceC2.add(new IceC(200, 450));
		iceC2.add(new IceC(250, 450));
		iceC2.add(new IceC(300, 450));
		iceC2.add(new IceC(200, 250));
		iceC2.add(new IceC(200, 300));
		iceC2.add(new IceC(200, 350));
		iceC2.add(new IceC(200, 400));
		iceC2.add(new IceC(450, 200));
		iceC2.add(new IceC(500, 200));
		iceC2.add(new IceC(550, 200));
		iceC2.add(new IceC(450, 450));
		iceC2.add(new IceC(500, 450));
		iceC2.add(new IceC(550, 450));
		iceC2.add(new IceC(550, 250));
		iceC2.add(new IceC(550, 300));
		iceC2.add(new IceC(550, 350));
		iceC2.add(new IceC(550, 400));

// X coordinates of ice Cs
		iceCX[0] = 200;
		iceCX[1] = 250;
		iceCX[2] = 300;
		iceCX[3] = 200;
		iceCX[4] = 250;
		iceCX[5] = 300;
		iceCX[6] = 200;
		iceCX[7] = 200;
		iceCX[8] = 200;
		iceCX[9] = 200;
		iceCX[10] = 450;
		iceCX[11] = 500;
		iceCX[12] = 550;
		iceCX[13] = 450;
		iceCX[14] = 500;
		iceCX[15] = 550;
		iceCX[16] = 550;
		iceCX[17] = 550;
		iceCX[18] = 550;
		iceCX[19] = 550;

// Y coordinates of ice Cs
		iceCY[0] = 200;
		iceCY[1] = 200;
		iceCY[2] = 200;
		iceCY[3] = 450;
		iceCY[4] = 450;
		iceCY[5] = 450;
		iceCY[6] = 250;
		iceCY[7] = 300;
		iceCY[8] = 350;
		iceCY[9] = 400;
		iceCY[10] = 200;
		iceCY[11] = 200;
		iceCY[12] = 200;
		iceCY[13] = 450;
		iceCY[14] = 450;
		iceCY[15] = 450;
		iceCY[16] = 250;
		iceCY[17] = 300;
		iceCY[18] = 350;
		iceCY[19] = 400;

// add bananaCoord graphics to game panel
		bananaCoord.add(new Banana(150, 150));
		bananaCoord.add(new Banana(150, 200));
		bananaCoord.add(new Banana(200, 150));
		bananaCoord.add(new Banana(150, 450));
		bananaCoord.add(new Banana(150, 500));
		bananaCoord.add(new Banana(200, 500));
		bananaCoord.add(new Banana(550, 150));
		bananaCoord.add(new Banana(600, 200));

		bananaCoord.add(new Banana(600, 150));
		bananaCoord.add(new Banana(600, 450));
		bananaCoord.add(new Banana(550, 500));
		bananaCoord.add(new Banana(600, 500));
		bananaCoord.add(new Banana(300, 300));
		bananaCoord.add(new Banana(300, 350));
		bananaCoord.add(new Banana(450, 300));
		bananaCoord.add(new Banana(450, 350));

// add coordinates of grapes to game panel
		grapeCoord.add(new Grape(100, 100));
		grapeCoord.add(new Grape(650, 100));
		grapeCoord.add(new Grape(650, 550));
		grapeCoord.add(new Grape(100, 550));
		grapeCoord.add(new Grape(375, 100));
		grapeCoord.add(new Grape(100, 325));
		grapeCoord.add(new Grape(375, 550));
		grapeCoord.add(new Grape(650, 325));
		grapeCoord.add(new Grape(250, 250));
		grapeCoord.add(new Grape(500, 250));
		grapeCoord.add(new Grape(250, 400));
		grapeCoord.add(new Grape(500, 400));

		// level 2 ice
		level2Ice.add(new Level2Ice(150, 200));
		level2Ice.add(new Level2Ice(150, 250));
		level2Ice.add(new Level2Ice(150, 300));
		level2Ice.add(new Level2Ice(150, 350));
		level2Ice.add(new Level2Ice(150, 400));
		level2Ice.add(new Level2Ice(150, 450));

		level2Ice.add(new Level2Ice(300, 200));
		level2Ice.add(new Level2Ice(300, 250));
		level2Ice.add(new Level2Ice(300, 300));
		level2Ice.add(new Level2Ice(300, 350));
		level2Ice.add(new Level2Ice(300, 400));
		level2Ice.add(new Level2Ice(300, 450));

		level2Ice.add(new Level2Ice(450, 200));
		level2Ice.add(new Level2Ice(450, 250));
		level2Ice.add(new Level2Ice(450, 300));
		level2Ice.add(new Level2Ice(450, 350));
		level2Ice.add(new Level2Ice(450, 400));
		level2Ice.add(new Level2Ice(450, 450));

		level2Ice.add(new Level2Ice(600, 200));
		level2Ice.add(new Level2Ice(600, 250));
		level2Ice.add(new Level2Ice(600, 300));
		level2Ice.add(new Level2Ice(600, 350));
		level2Ice.add(new Level2Ice(600, 400));
		level2Ice.add(new Level2Ice(600, 450));

		level2IceX[0] = 150;
		level2IceX[1] = 150;
		level2IceX[2] = 150;
		level2IceX[3] = 150;
		level2IceX[4] = 250;
		level2IceX[5] = 250;
		level2IceX[6] = 500;
		level2IceX[7] = 500;
		level2IceX[8] = 600;
		level2IceX[9] = 600;
		level2IceX[10] = 600;
		level2IceX[11] = 600;

		level2IceY[0] = 150;
		level2IceY[1] = 250;
		level2IceY[2] = 400;
		level2IceY[3] = 500;
		level2IceY[4] = 150;
		level2IceY[5] = 500;
		level2IceY[6] = 150;
		level2IceY[7] = 500;
		level2IceY[8] = 150;
		level2IceY[9] = 250;
		level2IceY[10] = 400;
		level2IceY[11] = 500;

		bananaCoord2.add(new Banana(200, 200));
		bananaCoord2.add(new Banana(250, 250));
		bananaCoord2.add(new Banana(200, 300));
		bananaCoord2.add(new Banana(250, 350));
		bananaCoord2.add(new Banana(200, 400));
		bananaCoord2.add(new Banana(250, 450));

		bananaCoord2.add(new Banana(400, 200));
		bananaCoord2.add(new Banana(350, 200));
		bananaCoord2.add(new Banana(400, 300));
		bananaCoord2.add(new Banana(350, 300));

		bananaCoord2.add(new Banana(550, 200));
		bananaCoord2.add(new Banana(500, 250));
		bananaCoord2.add(new Banana(550, 300));
		bananaCoord2.add(new Banana(500, 350));
		bananaCoord2.add(new Banana(550, 400));
		bananaCoord2.add(new Banana(500, 450));

		bananaCoord2.add(new Banana(100, 100));
		bananaCoord2.add(new Banana(100, 550));
		bananaCoord2.add(new Banana(650, 100));
		bananaCoord2.add(new Banana(650, 550));

		grapeCoord2.add(new Grape(150, 100));
		grapeCoord2.add(new Grape(300, 100));
		grapeCoord2.add(new Grape(450, 100));
		grapeCoord2.add(new Grape(600, 100));

		grapeCoord2.add(new Grape(150, 550));
		grapeCoord2.add(new Grape(300, 550));
		grapeCoord2.add(new Grape(450, 550));
		grapeCoord2.add(new Grape(600, 550));

		this.setFocusable(true); // make everything in this class appear on the screen

		this.addKeyListener(this); // start listening for keyboard input

		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT)); // set size of game window

		this.addMouseMotionListener(this); // listen for mouse motion

// add the MousePressed method from the MouseAdapter
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

			}

			public void mouseMoved(MouseEvent e) {

			}
		});

// Runs class simultaneously as other classes and removes lag
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void paintComponent(Graphics g) {
// Required for painting graphics and images including all game elements
		super.paintComponent(g);

	}

// override paint method to automatically run in background to update window
	public void paint(Graphics g) {
// use "double buffering" to draw images off the screen and move the image on
// screen as needed
		image = createImage(GAME_WIDTH, GAME_HEIGHT); // draw off screen
		graphics = image.getGraphics();
		draw(graphics); // update the positions of everything on the screen
		g.drawImage(image, 0, 0, this); // redraw everything on the screen

	}

	public static int[] getHighScore(String filepath) {
		FileReader readFile = null;
		BufferedReader reader = null;

		try {
			readFile = new FileReader(filepath);
			reader = new BufferedReader(readFile);

			// Read the entire line containing scores
			String line = reader.readLine();

			// Split the line into individual scores
			String[] scoreStrings = line.split(" ");

			// Create an integer array to store the scores
			int[] scores = new int[scoreStrings.length];

			// Convert and store each score in the array
			for (int i = 0; i < scoreStrings.length; i++) {
				scores[i] = Integer.parseInt(scoreStrings[i]);
			}

			return scores;
		} catch (Exception e) {
			return new int[0]; // Return an empty array if an error occurs

		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void createFile() {
		File scoreFile = new File("scores.txt");

		// sort the array list from greatest to least using bubble sort
		for (int top = recentScores.size() - 1; top > 0 && !sorted; top--) {

			sorted = true; // flags true for boolean value

			for (int i = 0; i < top; i++) {
				// compares adjacent elements from left to right and switches if out of order
				if (recentScores.get(i) > recentScores.get(i + 1)) {
					// switches array values if out of order
					temp = recentScores.get(i);
					recentScores.set(i, recentScores.get(i + 1));
					recentScores.set(i + 1, temp);
					sorted = false;
				}
			}
		} // end of bubble sort

		if (!scoreFile.exists()) {
			try {
				scoreFile.createNewFile();
				System.out.println("file created " + scoreFile.getCanonicalPath()); // returns the path string
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		FileWriter writeFile = null;
		BufferedWriter writer = null;
		try {
			writeFile = new FileWriter(scoreFile);
			writer = new BufferedWriter(writeFile);

			if (recentScores.size() > 5) {

				// writer.write("Top Five High Scores\n");
				for (int x = 0; x < 5; x++) {
					writer.write(recentScores.get(x) + " ");

				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error while saving highscore",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (Exception e) {
				System.out.println("An error occured");
			}
		}
		// }
	}

	public void mouseInput() { // manually detect mouse input through motion and clicks

		// Instantiate a MouseAdapter and override the mousePressed method
		MouseAdapter mouseAdapter = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // detects mouse clicks for input
				// obtain coordinates for mouse
				btnX = e.getX();
				btnY = e.getY();
				// display starter screen for user to progress to main menu

				if (!playGame && !selectionMenu && btnX >= 250 && btnX <= 550 && btnY >= 565 && btnY <= 640) {
					mainMenu = true;
					selectionMenu = false;
					playGame = false;
					level1 = false;
					level2 = false;
					controls = false;
					scoreBoard = false;
				}
				// toggle player controls on and off
				if (playGame && btnX >= GAME_WIDTH - 100 && btnX <= GAME_WIDTH - 60 && btnY >= 0 && btnY <= 40) {

					cornerControls = !cornerControls; // invert boolean value

					controls = false;
					mainMenu = false;
					exitGame = false;
					returnMain = false;
					scoreBoard = false;
				}
				// back to main menu button click
				if ((!playGame && !level1 && !level2 && scoreBoard && (btnX >= 300 && btnX <= 500 && btnY >= 550 && btnY <= 650))
					      || (!playGame && !level1 && !level2 && controls && (btnX >= 300 && btnX <= 500 && btnY >= 550 && btnY <= 650))) {

					// display main menu
					exitGame = false;
					mainMenu = true; // redirect to main menu so game does not exit
					controls = false;
					scoreBoard = false;
					level1 = false;
					// mainMenu = false;
					level2 = false;

					repaint(); // reset everything on screen
				}

				// Exit Game when Clicked
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 520 && btnY <= 570) {

					if (!scoreBoard && !controls) {
						exitGame = true;
					}
				}
				// display audio in top right corner
				if (btnX >= GAME_WIDTH - 50 && btnX <= GAME_WIDTH - 10 && btnY >= 0 && btnY <= 40) {

					 audio = !audio;
					    System.out.println("Audio Toggle On/Off");

					    if (audio) {
					       clip.start();
					    } else if (!audio) {
					        clip.stop();
					    	stopMusic();
					    }

				}

				// Proceed to Character Selection Menu
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 350 && btnY <= 410) {
					selectionMenu = true;
					controls = false;
					scoreBoard = false;
					exitGame = false;
					mainMenu = false;

					repaint();
				}
				// Display Score Leader Board
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 410 && btnY <= 470) {

					scoreBoard = true;
					playGame = false;
					level1 = false;
					level2 = false;
					controls = false;
					exitGame = false;
					mainMenu = false;
				}
				// Display Player Controls
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 470 && btnY <= 520) {
					playGame = false;
					level1 = false;
					level2 = false;
					controls = true;
					mainMenu = false;
					exitGame = false;
					scoreBoard = false;

					repaint();
				}

				// Draw Box for Character Selection
				if (selectionMenu && btnX >= 50 && btnX <= 135 && btnY >= 310 && btnY <= 410) {
					// create sorbet character
					charSelection2[0] = true;
					charSelection2[1] = false;
					charSelection2[2] = false;
					player1 = new Player1(350, 400, 1);
					character1 = true;
				}
				if (selectionMenu && btnX >= 170 && btnX <= 260 && btnY >= 310 && btnY <= 410) {
					// create smokey bacon character
					charSelection2[0] = false;
					charSelection2[1] = true;
					charSelection2[2] = false;
					player1 = new Player1(350, 400, 2);
					character1 = true;
				}
				if (selectionMenu && btnX >= 270 && btnX <= 380 && btnY >= 310 && btnY <= 410) {
					// create mint choc chip character for player 1
					charSelection2[0] = false;
					charSelection2[1] = false;
					charSelection2[2] = true;
					player1 = new Player1(350, 400, 3);
					character1 = true;
				}
				// Player 2 Selection
				if (selectionMenu && btnX >= 435 && btnX <= 520 && btnY >= 310 && btnY <= 410) {
					// create sorbet character for player 2
					charSelection2[3] = true;
					charSelection2[4] = false;
					charSelection2[5] = false;
					player2 = new Player2(400, 400, 1);
					character2 = true;
				}
				if (selectionMenu && btnX >= 545 && btnX <= 640 && btnY >= 310 && btnY <= 410) {
					// create smokey bacon character for player 2
					charSelection2[3] = false;
					charSelection2[4] = true;
					charSelection2[5] = false;
					player2 = new Player2(400, 400, 2);
					character2 = true;
				}
				if (selectionMenu && btnX >= 660 && btnX <= 760 && btnY >= 310 && btnY <= 410) {
					// create mint choc chip character for player 2
					charSelection2[3] = false;
					charSelection2[4] = false;
					charSelection2[5] = true;
					player2 = new Player2(400, 400, 3);
					character2 = true;
				}
				// Confirm Button to Play Game
				if (character1 && character2 && selectionMenu && !playGame && !mainMenu && btnX >= 275 && btnX <= 525
						&& btnY >= 570 && btnY <= 670) {
					displayChar = true;
				}
				// Confirm Button to Play Game
				if ((!character1 || !character2) && selectionMenu && !playGame && !mainMenu && btnX >= 275
						&& btnX <= 525 && btnY >= 570 && btnY <= 670) {
					charError = true;
				} else {
					charError = false;
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) { // detects mouse motion for hover effects
				// obtain coordinates for mouse motion
				btnX = e.getX();
				btnY = e.getY();
				// display icecream animations when hovering over each menu option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 350 && btnY <= 410
						|| returnMain && btnX >= 180 && btnX <= 580 && btnY >= 350 && btnY <= 410) {

					icecreamPics[0] = true;
					repaint();

				} else {
					icecreamPics[0] = false;
				}
				// display effects when hovering over second option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 410 && btnY <= 470
						|| returnMain && btnX >= 180 && btnX <= 580 && btnY >= 410 && btnY <= 470) {
					icecreamPics[1] = true;
					repaint();
				} else {
					icecreamPics[1] = false;
				}
				// display effects when hovering over third option
				if (mainMenu && btnX >= 190 && btnX <= 580 && btnY >= 470 && btnY <= 520
						|| returnMain && btnX >= 180 && btnX <= 580 && btnY >= 470 && btnY <= 520) {
					icecreamPics[2] = true;
					repaint();
				} else {
					icecreamPics[2] = false;
				}
				// display effects when hovering over fourth option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 520 && btnY <= 570
						|| returnMain && btnX >= 180 && btnX <= 580 && btnY >= 520 && btnY <= 570) {
					icecreamPics[3] = true;
					repaint();
				} else {
					icecreamPics[3] = false;
				}

				// display hover effects for character selection
				if (selectionMenu && btnX >= 50 && btnX <= 135 && btnY >= 310 && btnY <= 410) {
					charSelection[0] = true;
					repaint();
				} else {
					charSelection[0] = false;
				}
				// display hover effects for character selection
				if (selectionMenu && btnX >= 170 && btnX <= 260 && btnY >= 310 && btnY <= 410) {
					charSelection[1] = true;
					repaint();
				} else {
					charSelection[1] = false;
				}
				// display hover effects for character selection
				if (selectionMenu && btnX >= 270 && btnX <= 380 && btnY >= 310 && btnY <= 410) {
					charSelection[2] = true;
					repaint();
				} else {
					charSelection[2] = false;
				}

				if (selectionMenu && btnX >= 435 && btnX <= 520 && btnY >= 310 && btnY <= 410) {
					charSelection[3] = true;
					repaint();
				} else {
					charSelection[3] = false;
				}
				if (selectionMenu && btnX >= 545 && btnX <= 640 && btnY >= 310 && btnY <= 410) {
					charSelection[4] = true;
					repaint();
				} else {
					charSelection[4] = false;
				}
				if (selectionMenu && btnX >= 660 && btnX <= 760 && btnY >= 310 && btnY <= 410) {
					charSelection[5] = true;
					repaint();
				} else {
					charSelection[5] = false;
				}
			}
		};
		// Add the mouseAdapter to your components
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);

		repaint(); // reset graphics on screen
	}

// call the draw methods in each class to update positions as things move
	public void draw(Graphics g) {

		// display main menu options, graphics and controls
		menubg = Toolkit.getDefaultToolkit().getImage("menubg.gif"); // create image
		logo = Toolkit.getDefaultToolkit().getImage("logo.png"); // create image
		playbtn = Toolkit.getDefaultToolkit().getImage("playbtn.png"); // create image
		authors = Toolkit.getDefaultToolkit().getImage("authors.png"); // create image

		g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw background image to screen
		g.drawImage(playbtn, 250, 565, 300, 75, null); // draw button image to screen
		g.drawImage(logo, 200, 0, 400, 600, null); // draw logo image to screen
		g.drawImage(authors, 250, GAME_HEIGHT - 50, 300, 50, null); // write authors

		sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

		g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw settings icon to screen

		mouseInput(); // checks for mouse input

		if (mainMenu || returnMain) { // run all conditions for main menu screen

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw background image to screen
			g.drawImage(authors, 250, GAME_HEIGHT - 50, 300, 50, null); // write authors to screen
			menu = Toolkit.getDefaultToolkit().getImage("menu.png"); // create image
			g.drawImage(menu, 120, -20, 550, 750, null); // draw menu options to screen
			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image
			g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw icon to screen
			icecream = Toolkit.getDefaultToolkit().getImage("icecream.gif"); // create image

			// draw ice cream graphics for hover effects
			if (icecreamPics[0]) {
				g.drawImage(icecream, 190, 350, 60, 50, null);
				g.drawImage(icecream, 520, 350, 60, 50, null);

			}
			if (icecreamPics[1]) {
				g.drawImage(icecream, 190, 410, 60, 50, null);
				g.drawImage(icecream, 520, 410, 60, 50, null); // draw image to screen

			}
			if (icecreamPics[2]) {
				g.drawImage(icecream, 190, 470, 60, 50, null); // draw image to screen
				g.drawImage(icecream, 520, 470, 60, 50, null); // draw image to screen

			}
			if (icecreamPics[3]) {
				g.drawImage(icecream, 190, 520, 60, 50, null); // draw image to screen
				g.drawImage(icecream, 520, 520, 60, 50, null); // draw image to screen

			}
			mouseInput(); // detects mouse input
		}

		if (selectionMenu) {

			// character selection menu
			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw background image to screen

			title = Toolkit.getDefaultToolkit().getImage("charselection.png");
			g.drawImage(title, 150, 25, 500, 100, null); // draw background image to screen

			// PLAYER 1 SELECTION MENU
			char1 = Toolkit.getDefaultToolkit().getImage("player1.png"); // draw character selection menu for player
			g.drawImage(char1, 15, 160, 380, 350, null); // draw image to screen

			// character selection menu for player 1
			sorbet = Toolkit.getDefaultToolkit().getImage("sorbet.png"); // draw character selection menu for player

			g.drawImage(sorbet, 45, 310, 100, 100, null); // draw image to screen
			smokeyb = Toolkit.getDefaultToolkit().getImage("smokeyb.png"); // draw character selection menu for player
			g.drawImage(smokeyb, 160, 310, 100, 100, null); // draw image to screen

			mint = Toolkit.getDefaultToolkit().getImage("MintChocChip.png");
			g.drawImage(mint, 270, 310, 100, 100, null); // draw image to screen

			// PLAYER 2 SELECTION MENU
			char2 = Toolkit.getDefaultToolkit().getImage("player2.png"); // draw character selection menu for player 2
			g.drawImage(char2, 400, 160, 380, 350, null); // draw image to screen

			// character selection menu for player 1
			g.drawImage(sorbet, 430, 310, 100, 100, null); // draw image to screen
			g.drawImage(smokeyb, 540, 310, 100, 100, null); // draw image to screen
			g.drawImage(mint, 660, 310, 100, 100, null); // draw image to screen

			downArrow = Toolkit.getDefaultToolkit().getImage("pointdown.gif");

			// draw arrow when mouse hovers over character
			if (charSelection[0]) {
				g.drawImage(downArrow, 45, 230, 100, 100, null); // draw image to screen
			}
			if (charSelection[1]) {
				g.drawImage(downArrow, 160, 230, 100, 100, null); // draw image to screen
			}
			if (charSelection[2]) {
				g.drawImage(downArrow, 270, 230, 100, 100, null); // draw image to screen
			}
			if (charSelection[3]) {
				g.drawImage(downArrow, 430, 230, 100, 100, null); // draw image to screen
			}
			if (charSelection[4]) {
				g.drawImage(downArrow, 540, 230, 100, 100, null); // draw image to screen
			}
			if (charSelection[5]) {
				g.drawImage(downArrow, 660, 230, 100, 100, null); // draw image to screen
			}

			// draw boxes around selected character
			if (charSelection2[0]) {
				Color c2 = new Color(45, 222, 246);
				g.setColor(c2);
				g.drawRect(45, 310, 100, 100); // draw image to screen
			}
			if (charSelection2[1]) {
				Color c2 = new Color(45, 222, 246);
				g.setColor(c2);
				g.drawRect(160, 310, 100, 100); // draw image to screen
			}
			if (charSelection2[2]) {
				Color c2 = new Color(45, 222, 246);
				g.setColor(c2);
				g.drawRect(270, 310, 100, 100); // draw image to screen
			}
			if (charSelection2[3]) {
				Color c2 = new Color(45, 222, 246);
				g.setColor(c2);
				g.drawRect(430, 310, 100, 100); // draw image to screen
			}
			if (charSelection2[4]) {
				Color c2 = new Color(45, 222, 246);
				g.setColor(c2);
				g.drawRect(540, 310, 100, 100); // draw image to screen
			}
			if (charSelection2[5]) {
				Color c2 = new Color(45, 222, 246);
				g.setColor(c2);
				g.drawRect(660, 310, 100, 100); // draw image to screen
			}
			repaint();
			confirmBtn = Toolkit.getDefaultToolkit().getImage("confirmbtn.png");
			g.drawImage(confirmBtn, 275, 570, 250, 100, null); // draw background image to screen

			if (charError) {
				error = Toolkit.getDefaultToolkit().getImage("error.png");
				g.drawImage(error, 275, 500, 250, 50, null); // draw background image to screen
			}
			sorbetMenu = Toolkit.getDefaultToolkit().getImage("sorbetMenu.gif"); // create image
			smokeyMenu = Toolkit.getDefaultToolkit().getImage("smokeyMenu.png"); // create image
			mintMenu = Toolkit.getDefaultToolkit().getImage("mintMenu.gif"); // create image

			if (displayChar) {
				if (charSelection2[0]) {
					g.drawImage(sorbetMenu, 45, 240, 330, 200, null);

				} else if (charSelection2[1]) {
					g.drawImage(smokeyMenu, 45, 240, 330, 200, null);

				} else if (charSelection2[2]) {
					g.drawImage(mintMenu, 45, 240, 330, 200, null);
				}
				if (charSelection2[3]) {
					g.drawImage(sorbetMenu, 430, 240, 330, 200, null);

				} else if (charSelection2[4]) {
					g.drawImage(smokeyMenu, 430, 240, 330, 200, null);

				} else if (charSelection2[5]) {
					g.drawImage(mintMenu, 430, 240, 330, 200, null);
				}

				loading = Toolkit.getDefaultToolkit().getImage("loading.png"); // create image
				g.drawImage(loading, 275, 525, 225, 25, null);

				// Start new thread to implement runnable interface to delay and terminate game
				new Thread(new Runnable() {
					@Override
					public void run() { // override run method
						// catch block used if another thread interrupts this thread
						try {
							Thread.sleep(3000);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// Performs code asynchronously in Event dispatcher thread
						SwingUtilities.invokeLater(() -> {
							synchronized (GamePanel.this) {
								playGame = true; // change to true after done char menu
								level1 = true;
								level2 = false;
								selectionMenu = false;
								controls = false;
								scoreBoard = false;
								exitGame = false;
								mainMenu = false;
							}
						});
					}
				}).start(); // begins execution of thread
			}
		}

		if (controls) { // display player controls on screen

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

			g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			playerControls = Toolkit.getDefaultToolkit().getImage("playerControls.png"); // create image

			g.drawImage(playerControls, 50, 30, 700, 500, null); // draw image to screen

			backbtn = Toolkit.getDefaultToolkit().getImage("backbtn.png"); // create image

			g.drawImage(backbtn, 300, 580, 200, 100, null); // draw image to screen

			mouseInput(); // checks for mouse input
		}

		if (!scoreBoard && !controls && exitGame) { // terminate program after delay

			exitgame = Toolkit.getDefaultToolkit().getImage("exit-game.gif"); // create image

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw background image to screen

			g.drawImage(exitgame, 125, 50, 550, 600, null); // draw image to screen

			// Start new thread to implement runnable interface to delay and terminate game
			new Thread(new Runnable() {

				@Override
				public void run() { // override run method
					// catch block used if another thread interrupts this thread
					try {
						Thread.sleep(5000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// Performs code asynchronously in Event dispatcher thread
					SwingUtilities.invokeLater(() -> {
						mainMenu = false;
						scoreBoard = false;
						playGame = false;
						level1 = false;
						level2 = false;
						controls = false;
						System.exit(0); // Terminate the program
					});
				}
			}).start(); // begins execution of thread
		}

		if (scoreBoard) { // display score board - will update with saved high scores

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

			g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			scores = Toolkit.getDefaultToolkit().getImage("scores.png"); // create image

			g.drawImage(scores, 100, 30, 600, 500, null); // draw image to screen

			backbtn = Toolkit.getDefaultToolkit().getImage("backbtn.png"); // create image

			g.drawImage(backbtn, 300, 580, 200, 100, null); // draw image to screen

			Font font = new Font("Arial", Font.BOLD, 20);
			g.setFont(font);

			int y = 250;

			int[] scores = getHighScore("scores.txt");

			for (int i = 0; i < scores.length; i++) {
				int score = scores[i];
				System.out.println(score);
				g.drawString(score + "", 200, y);
				y += 30;
			}

			mouseInput(); // checks for mouse input
		}

		if (playGame && level1) { // display game screen

			clip.stop(); // stop music clip
			//playMusic("main theme.wav"); // plays sound file
			mouseInput(); // checks for mouse input

			snow = Toolkit.getDefaultToolkit().getImage("snow.png"); // create background image

			g.drawImage(snow, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

// draw game graphics and objects

			ice.draw(g);
			igloo.draw(g); // draw igloo in center

			// display score and players on screen
			/*
			 * if (highscore==0) { //initialize high score highscore =
			 * Integer.parseInt(getHighScore()); }
			 */
			score.draw(g);

			player1.draw(g);
			player2.draw(g);

			if (onBanana == 16) {
				blueCircle2.draw(g);
			} else {
				blueCircle.draw(g);
			}

			for (int i = 0; i < 20; i++) {
				iceC2.get(i).draw(g);
			}

			for (int i = 0; i < 16; i++) {
				if (drawBanana[i]) {
					bananaCoord.get(i).draw(g);
				}
			}

			if (onBanana == 16) {
				for (int i = 0; i < 12; i++) {
					if (drawGrape[i]) {
						grapeCoord.get(i).draw(g);
					}
				}
			}
			// end condition for winner / loser
			if (onBanana == 16 && onGrape == 12) {
				roundWinner.draw(g);

				if (charSelection2[0]) {
					g.drawImage(sorbet, (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[1]) {
					g.drawImage(smokeyb, (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[2]) {
					g.drawImage(mint, (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.51), 50, 50, null);
				}
				if (charSelection2[3]) {
					g.drawImage(sorbet, (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[4]) {
					g.drawImage(smokeyb, (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[5]) {
					g.drawImage(mint, (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.51), 50, 50, null);
				}

				g.setFont(new Font("Consolas", Font.PLAIN, 20)); // set font type and size
				g.drawString("Press Enter to Play Next Level...", 200, 580); // draw winner result to screen

				totalScore1 += Score.score;
				totalScore2 += Score.score2;
				
				nextLevel = true;

				player1.level++;
				player2.level++;
			}
			// Player Controls Toggle
			if (playGame && cornerControls) { // display settings to user

				gameControls = Toolkit.getDefaultToolkit().getImage("gameControls.gif"); // create image
				g.drawImage(gameControls, 150, 150, 500, 400, null);
			}
			g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			settings = Toolkit.getDefaultToolkit().getImage("settings.png"); // create image

			g.drawImage(settings, GAME_WIDTH - 100, 0, 40, 40, null); // draw image to screen

		}

		if (playGame && level2) {

			snow = Toolkit.getDefaultToolkit().getImage("snow.png"); // create background image
			g.drawImage(snow, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen
			level2Scenery.draw(g);
			if (onBanana2 == 20) {
				blueCircle4.draw(g);
			} else {
				blueCircle3.draw(g);
			}
			// display score and players on screen

			for (int i = 0; i < 24; i++) {
				level2Ice.get(i).draw(g);
			}

			for (int i = 0; i < 20; i++) {
				if (drawBanana2[i]) {
					bananaCoord2.get(i).draw(g);
				}
			}

			if (onBanana2 == 20) {
				for (int i = 0; i < 8; i++) {
					if (drawGrape2[i]) {
						grapeCoord2.get(i).draw(g);
					}
				}
			}
			score.draw(g);
			if (!melted1) {
				player1.draw(g);
			}
			if (!melted2) {
				player2.draw(g);
			}
			cow.draw(g);
			// end condition for winner / loser
			if (onBanana2 == 20 && onGrape2 == 8) {
				roundWinner.draw(g);
				if (charSelection2[0]) {
					g.drawImage(sorbet, (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[1]) {
					g.drawImage(smokeyb, (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[2]) {
					g.drawImage(mint, (int) (GAME_WIDTH * 0.35), (int) (GAME_HEIGHT * 0.51), 50, 50, null);
				}
				if (charSelection2[3]) {
					g.drawImage(sorbet, (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[4]) {
					g.drawImage(smokeyb, (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.51), 50, 50, null);

				} else if (charSelection2[5]) {
					g.drawImage(mint, (int) (GAME_WIDTH * 0.6), (int) (GAME_HEIGHT * 0.51), 50, 50, null);
				}
				g.setFont(new Font("Consolas", Font.PLAIN, 20)); // set font type and size
				g.drawString("Press Enter to Return to Main Menu...", 200, 580); // draw winner result to screen
				totalScore1 += Score.score;
				totalScore2 += Score.score2;

				if (totalScore1 > totalScore2) { // add up total score
					recentScores.add(totalScore1);

				} else if (totalScore1 < totalScore2) {
					recentScores.add(totalScore1);
				} else {
					recentScores.add(totalScore1);
				}
				nextLevel2 = true;
			}
			g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			settings = Toolkit.getDefaultToolkit().getImage("settings.png"); // create image

			g.drawImage(settings, GAME_WIDTH - 100, 0, 40, 40, null); // draw image to screen

		}

		g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

		sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

	/*	if (!audio) { // pause music

			cross = Toolkit.getDefaultToolkit().getImage("cross.png"); // create image

			g.drawImage(cross, GAME_WIDTH - 45, 0, 40, 40, null); // draw image to screen

			// clipTimePosition = clip.getMicrosecondLength();

			clip.stop(); // stop music clip

		} else if (audio) { // 
			//playMusic("menu music.wav");
			try {
				clip.open();
			} catch (LineUnavailableException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY); 
		} */
		
		if (!audio) {
		    cross = Toolkit.getDefaultToolkit().getImage("cross.png");
		    g.drawImage(cross, GAME_WIDTH - 45, 0, 40, 40, null);
		} else {
		    // Render audio icon for the ON state if needed
			g.drawImage(sound, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

		}

		/*
		 * if (!audio) { cross = Toolkit.getDefaultToolkit().getImage("cross.png"); //
		 * create image g.drawImage(cross, GAME_WIDTH - 45, 0, 40, 40, null); // draw
		 * image to screen clipTimePosition = clip.getMicrosecondLength(); clip.stop();
		 * // stop music clip
		 * 
		 * }
		 */
		/*
		 * if (audio) { clip.start(); clip.loop(Clip.LOOP_CONTINUOUSLY); sound =
		 * Toolkit.getDefaultToolkit().getImage("sound.png"); // create image
		 * 
		 * }
		 */
	}

// call the move methods in other classes to update positions for fluid
// movements
	public void move() {
		player1.move();
		player2.move();
		cow.move();
		checkCollision();
	}

// handles all collision detection and responds accordingly
	public void checkCollision() {

		if (level1 == true) {

			// detects collision between player 1 and iceblocks
			for (int i = 0; i < 20; i++) {
				if (player1.intersects(iceC2.get(i)) && player1.x > iceCX[i] && player1.xVelocity < 0) {
					player1.x = iceCX[i] + 50;
				}
				if (player1.intersects(iceC2.get(i)) && player1.x < iceCX[i] && player1.xVelocity > 0) {
					player1.x = iceCX[i] - 50;
				}
				if (player1.intersects(iceC2.get(i)) && player1.y > iceCY[i] && player1.yVelocity < 0) {
					player1.y = iceCY[i] + 50;
				}
				if (player1.intersects(iceC2.get(i)) && player1.y < iceCY[i] && player1.yVelocity > 0) {
					player1.y = iceCY[i] - 50;
				}

			}
			// detects collision between player 2 and iceblocks
			for (int i = 0; i < 20; i++) {
				if (player2.intersects(iceC2.get(i)) && player2.x > iceCX[i] && player2.xVelocity < 0) {
					player2.x = iceCX[i] + 50;
				}
				if (player2.intersects(iceC2.get(i)) && player2.x < iceCX[i] && player2.xVelocity > 0) {
					player2.x = iceCX[i] - 50;
				}
				if (player2.intersects(iceC2.get(i)) && player2.y > iceCY[i] && player2.yVelocity < 0) {
					player2.y = iceCY[i] + 50;
				}
				if (player2.intersects(iceC2.get(i)) && player2.y < iceCY[i] && player2.yVelocity > 0) {
					player2.y = iceCY[i] - 50;
				}
			}

			for (int i = 0; i < 16; i++) {
// conditions for when player intersects with fruits
				if (player1.intersects(bananaCoord.get(i)) && drawBanana[i] == true) {

					Score.score += 150; // increase score

					drawBanana[i] = false;
					onBanana++;
				}
			}

			for (int i = 0; i < 16; i++) {
// conditions for when player 2 collects fruits
				if (player2.intersects(bananaCoord.get(i)) && drawBanana[i] == true) {

					Score.score2 += 150; // increase score

					drawBanana[i] = false;
					onBanana++;
				}
			}

			if (onBanana == 16) {
// display conditions for when player 1 collects grapes
				for (int i = 0; i < 12; i++) {

					if (player1.intersects(grapeCoord.get(i)) && drawGrape[i] == true) {

						Score.score += 150; // increase score
						drawGrape[i] = false;
						onGrape++;
					}
				}

				for (int i = 0; i < 12; i++) {
// grapes disappear when player "collects" (touches) them
					if (player2.intersects(grapeCoord.get(i)) && drawGrape[i] == true) {
						Score.score2 += 150;
						drawGrape[i] = false;
						onGrape++;
					}
				}
			}

			// define game bounds for players
			if (player1.x > 650) {
				player1.x = 650;
			}

			if (player1.x < 100) {
				player1.x = 100;
			}

			if (player1.y > 550) {
				player1.y = 550;
			}

			if (player1.y < 100) {
				player1.y = 100;
			}

			if (player2.x > 650) {
				player2.x = 650;
			}

			if (player2.x < 100) {
				player2.x = 100;
			}

			if (player2.y > 550) {
				player2.y = 550;
			}

			if (player2.y < 100) {
				player2.y = 100;
			}
		}

		if (level2 == true) { // game conditions for level 2
			if (cow.x + 600 >= player1.x && cow.y == player1.y && Cow.xVelocity > 0 && !melted1) {
				Cow.xVelocity = 5;
				if (cow.intersects(player1)) {
					melted1 = true;
					System.out.println("MELTED");
				}
			} else if (cow.x - 600 <= player1.x && cow.y == player1.y && Cow.xVelocity < 0 && !melted1) {
				Cow.xVelocity = -5;
				if (cow.intersects(player1)) {
					melted1 = true;
					System.out.println("MELTED");
				}
			} else if (cow.y + 500 >= player1.y && cow.x == player1.x && Cow.yVelocity > 0 && !melted1) {
				Cow.yVelocity = 5;
				if (cow.intersects(player1)) {
					melted1 = true;
					System.out.println("MELTED");
				}
			} else if (cow.y - 500 >= player1.y && cow.x == player1.x && Cow.yVelocity < 0 && !melted1) {
				Cow.xVelocity = -5;
				if (cow.intersects(player1)) {
					melted1 = true;
					System.out.println("MELTED");
				}
			}

			if (cow.x + 600 >= player2.x && cow.y == player2.y && Cow.xVelocity > 0 && !melted2) {
				Cow.xVelocity = 5;
				if (cow.intersects(player2)) {
					melted2 = true;
					System.out.println("MELTED");
				}
			} else if (cow.x - 600 <= player2.x && cow.y == player2.y && Cow.xVelocity < 0 && !melted2) {
				Cow.xVelocity = -5;
				if (cow.intersects(player2)) {
					melted2 = true;
					System.out.println("MELTED");
				}
			} else if (cow.y + 500 >= player2.y && cow.x == player2.x && Cow.yVelocity > 0 && !melted2) {
				Cow.yVelocity = 5;
				if (cow.intersects(player2)) {
					melted2 = true;
					System.out.println("MELTED");
				}
			} else if (cow.y - 500 >= player2.y && cow.x == player2.x && Cow.yVelocity < 0 && !melted2) {
				Cow.xVelocity = -5;
				if (cow.intersects(player2)) {
					melted2 = true;
					System.out.println("MELTED");
				}
			}

			for (int i = 0; i < 20; i++) {
				// conditions for when player intersects with fruits
				if (player1.intersects(bananaCoord2.get(i)) && drawBanana2[i] == true) {
					Score.score += 150; // increase score
					drawBanana2[i] = false;
					onBanana2++;
				}
			}

			for (int i = 0; i < 20; i++) {
// conditions for when player 2 collects fruits
				if (player2.intersects(bananaCoord2.get(i)) && drawBanana2[i] == true) {
					Score.score2 += 150; // increase score
					drawBanana2[i] = false;
					onBanana2++;
				}
			}

			if (onBanana2 == 20) {
				// display conditions for when player 1 collects grapes
				for (int i = 0; i < 8; i++) {

					if (player1.intersects(grapeCoord2.get(i)) && drawGrape2[i] == true) {

						Score.score += 150; // increase score
						drawGrape2[i] = false;
						onGrape2++;
					}
				}

				for (int i = 0; i < 8; i++) {
					// grapes disappear when player "collects" (touches) them
					if (player2.intersects(grapeCoord2.get(i)) && drawGrape2[i] == true) {
						Score.score2 += 150;
						drawGrape2[i] = false;
						onGrape2++;
					}
				}
			}
		}
	}

// Calls other methods to move objects, check for collision, and update the
// screen
	public void run() {

// prevent CPU from running game code too quickly

// Variable and object declarations
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long now;

// "force" computer to get stuck in a loop to update the screen

		while (true) { // infinite game loop
			now = System.nanoTime();
			delta = delta + (now - lastTime) / ns;
			lastTime = now;

// only move objects around and update screen if enough time has passed
			if (delta >= 1) {
				checkCollision();
				move();
				repaint();
				delta--;
			}
		}
	}

// send key input to classes for processing
	public void keyPressed(KeyEvent e) {

// return to main menu option on controls and game page - cannot be acccessed
// from other pages
		if (e.getKeyCode() == KeyEvent.VK_ENTER && nextLevel) {
			Score.score = 0;
			Score.score2 = 0;
// set game boolean values only if game has ended
			level2 = true;
			level1 = false;
			mainMenu = false;
			controls = false;
			scoreBoard = false;
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER && nextLevel2) {
			Score.score = 0;
			Score.score2 = 0;
// set game boolean values only if game has ended
			mainMenu = true;
			level1 = false;
			level2 = false;
//playGame = false; //add to last game level
			controls = false;
			scoreBoard = false;

			//set all other game variables to false
			playGame = false;
			selectionMenu = false;
			level1 = false;
			level2 = false;
			nextLevel = false;
			exitGame = false;
			controls = false;
			scoreBoard = false;
			nextLevel2 = false;
			returnMain = false;
			//audio = true;
			continueBtn = false;

			cornerControls = true;
			charError = false;
			displayChar = false;

			melted1 = false;
			melted2 = false;

			totalScore1 = 0;
			totalScore2 = 0;
		}

		player1.keyPressed(e);
		player2.keyPressed(e);

	}

// send key input to classes for processing
	public void keyReleased(KeyEvent e) {
		player1.keyReleased(e);
		player2.keyReleased(e);
	}

// overridden by the KeyListener interface
	public void keyTyped(KeyEvent e) {

	}

	/* Music filepath is passed as argument into method to play audio file */
	public static void playMusic(String filepath) {
		// Variable declarations

		 try {
		        File music = new File(filepath);

		        if (music.exists()) {
		            AudioInputStream audioInput = AudioSystem.getAudioInputStream(music);
		            clip = AudioSystem.getClip();
		            clip.open(audioInput);
		           // clip.start();
		            //clip.loop(Clip.LOOP_CONTINUOUSLY);
		        } else {
		            System.out.println("File cannot be found.");
		        }
		    } catch (Exception e) {
		        System.out.println(e);
		    }
	}
	
	public static void stopMusic() {
	    if (clip != null && clip.isRunning()) {
	        clip.stop();
	    }
	}
// classes are required to be overriden for mouse adapter
	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}