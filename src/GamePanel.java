/* Leah Huang and Selvahini Kamalarajan
   January 12, 2024
   GamePanel
   GamePanel class acts as the main "game loop" and continuously runs the game. 
   Completed Features include music/sound effects, main menu, 2 player functionality, level 1 of game and score. */

//import packages for GUI, swing and files
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener, MouseMotionListener {
	// Child of JPanel and implements KeyListener and Runnable interface

	private static final long serialVersionUID = 1L; // add default serial id for class

	// dimensions of window
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 700;

	// variable declaration and initialization
	public Thread gameThread;
	public Image image;
	public Graphics graphics;
	public Ice ice;
	public Level2Scenery level2Scenery;
	public Player1 player1;
	public Player2 player2;
	public Score score;
	public Score score2;
	public BlueCircle blueCircle;
	public BlueCircle blueCircle2;
	public RoundWinner roundWinner;

	public Image logo, menubg, playbtn, authors, settings, menu, mint, sound, cross, playerControls, ming, smokeyb,
			sorbetMenu, arrow, arrow2, icecream, snow, backbtn, scores, charselection;

	Image[] movementPics = new Image[3];
	{

		movementPics[0] = new ImageIcon("help1.png").getImage();
		movementPics[1] = new ImageIcon("help2.png").getImage();
		movementPics[2] = new ImageIcon("help3.png").getImage();
	}

	// booleans for certain key input
	boolean playGame, exitGame, cornerControls, mainMenu, controls, drawBtn, icecream1, icecream2, icecream3, icecream4,
			scoreBoard, gameEnd, continueBtn, level1, level2, nextLevel;

	static boolean audio;

	// coordinates for play button on main menu
	int btnX;
	int btnY;

	// variables for audio effects
	static Clip clip;
	static long clipTimePosition;

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

		playMusic("menu music.wav"); // plays sound file

		// set game condition booleans
		playGame = false;
		level1 = false;
		level2 = true; // change to false when done creating level obviously
		nextLevel = false;
		exitGame = false;
		mainMenu = false;
		controls = false;
		scoreBoard = false;
		gameEnd = false;
		audio = true;
		continueBtn = false;

		cornerControls = false;

		// set booleans for ice cream graphics
		icecream1 = false;
		icecream2 = false;
		icecream3 = false;
		icecream4 = false;

		// create graphics and set start location on game screen
		ice = new Ice(0, 0);
		level2Scenery = new Level2Scenery(0, 0);

		// create blue circle graphic
		blueCircle = new BlueCircle(345, 635);
		blueCircle2 = new BlueCircle(395, 635);

		// create ice cream characters and score
		player1 = new Player1(350, 400);
		player2 = new Player2(400, 400);

		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		score2 = new Score(GAME_WIDTH, GAME_HEIGHT);

		roundWinner = new RoundWinner(GAME_WIDTH, GAME_HEIGHT);
		igloo = new Igloo(300, 275);

		// fill Array lists with fruit objects
		Arrays.fill(drawBanana, true);
		Arrays.fill(drawGrape, true);

		Arrays.fill(drawBanana2, true);
		Arrays.fill(drawGrape2, true);

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

		level2Ice.add(new Level2Ice(150, 150));
		level2Ice.add(new Level2Ice(150, 250));
		level2Ice.add(new Level2Ice(150, 400));
		level2Ice.add(new Level2Ice(150, 500));
		level2Ice.add(new Level2Ice(250, 150));
		level2Ice.add(new Level2Ice(250, 500));
		level2Ice.add(new Level2Ice(500, 150));
		level2Ice.add(new Level2Ice(500, 500));
		level2Ice.add(new Level2Ice(600, 150));
		level2Ice.add(new Level2Ice(600, 250));
		level2Ice.add(new Level2Ice(600, 400));
		level2Ice.add(new Level2Ice(600, 500));

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

		bananaCoord2.add(new Banana(150, 200));
		bananaCoord2.add(new Banana(200, 250));
		bananaCoord2.add(new Banana(150, 300));
		bananaCoord2.add(new Banana(100, 250));
		bananaCoord2.add(new Banana(150, 350));
		bananaCoord2.add(new Banana(200, 400));
		bananaCoord2.add(new Banana(150, 450));
		bananaCoord2.add(new Banana(100, 400));
		bananaCoord2.add(new Banana(600, 200));
		bananaCoord2.add(new Banana(650, 250));
		bananaCoord2.add(new Banana(600, 300));
		bananaCoord2.add(new Banana(550, 250));
		bananaCoord2.add(new Banana(600, 350));
		bananaCoord2.add(new Banana(650, 400));
		bananaCoord2.add(new Banana(600, 450));
		bananaCoord2.add(new Banana(550, 400));
		bananaCoord2.add(new Banana(400, 150));
		bananaCoord2.add(new Banana(350, 150));
		bananaCoord2.add(new Banana(400, 500));
		bananaCoord2.add(new Banana(350, 500));

		grapeCoord2.add(new Grape(200, 150));
		grapeCoord2.add(new Grape(550, 150));
		grapeCoord2.add(new Grape(150, 200));
		grapeCoord2.add(new Grape(600, 200));

		grapeCoord2.add(new Grape(200, 500));
		grapeCoord2.add(new Grape(550, 500));
		grapeCoord2.add(new Grape(150, 450));
		grapeCoord2.add(new Grape(600, 450));

		this.setFocusable(true); // make everything in this class appear on the screen

		this.addKeyListener(this); // start listening for keyboard input

		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT)); // set size of game window

		this.addMouseMotionListener(this); // listen for mouse motion

		// add the MousePressed method from the MouseAdapter
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

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

	} // end of paint method

	public void mouseInput() { // manually detect mouse input through motion and clicks

		// Instantiate a MouseAdapter and override the mousePressed method
		MouseAdapter mouseAdapter = new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) { // detects mouse clicks for input

				// obtain coordinates for mouse
				btnX = e.getX();
				btnY = e.getY();

				// display starter screen for user to progress to main menu

				if (btnX >= 250 && btnX <= 550 && btnY >= 565 && btnY <= 640) {

					mainMenu = true;
					playGame = false;
					level1 = false;
					level2 = false;
					exitGame = false;
					controls = false;
					scoreBoard = false;
				}

				// toggle music on and off
				if (btnX >= GAME_WIDTH - 100 && btnX <= GAME_WIDTH - 60 && btnY >= 0 && btnY <= 40) {

					audio = !audio;

					repaint();
				}

				// display settings in top right corner
				if (btnX >= GAME_WIDTH - 50 && btnX <= GAME_WIDTH - 10 && btnY >= 0 && btnY <= 40) {

					cornerControls = !cornerControls; // invert boolean value
					playGame = true;
					controls = false;
					mainMenu = false;
					exitGame = false;
					scoreBoard = false;
				}

				// back to main menu button click
				if (scoreBoard & (btnX >= 300 && btnX <= 500 && btnY >= 550 && btnY <= 650)
						|| (controls & (btnX >= 300 && btnX <= 500 && btnY >= 550 && btnY <= 650))) {

					// display main menu
					exitGame = false;
					mainMenu = true;
					controls = false;
					scoreBoard = false;
					playGame = false;
					level1 = false;
					level2 = false;

					repaint(); // reset everything on screen
				}

				// Display Level 1 of game
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 350 && btnY <= 410) {

					playGame = true;
					level1 = true;
					level2 = false;
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

				// Exit Game when Clicked
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 520 && btnY <= 570) {

					exitGame = true;

					// hide all screens to exit game
					mainMenu = false;
					playGame = false;
					level1 = false;
					level2 = false;
					controls = false;
					scoreBoard = false;
				}

				// Click Continue Button to see Control Examples
				if (btnX >= 310 && btnX <= 800 && btnY >= 480 && btnY <= 500) {

					continueBtn = true;
					playGame = true;
					mainMenu = false;

					System.out.println("corner controls true");

				}
			}

			@Override
			public void mouseMoved(MouseEvent e) { // detects mouse motion for hover effects

				// obtain coordinates for mouse motion
				btnX = e.getX();
				btnY = e.getY();

				// display icecream animations when hovering over each menu option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 350 && btnY <= 410) {

					icecream1 = true;
					repaint();

				} else {
					icecream1 = false;
				}
				// display effects when hovering over second option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 410 && btnY <= 470) {

					icecream2 = true;
					repaint();

				} else {
					icecream2 = false;
				}
				// display effects when hovering over third option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 470 && btnY <= 520) {

					icecream3 = true;
					repaint();

				} else {
					icecream3 = false;
				}
				// display effects when hovering over fourth option
				if (mainMenu && btnX >= 180 && btnX <= 580 && btnY >= 520 && btnY <= 570) {

					icecream4 = true;
					repaint();

				} else {
					icecream4 = false;
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

		g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw settings icon to screen

		mouseInput(); // checks for mouse input

		if (mainMenu) { // run all conditions for main menu screen

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw background image to screen

			g.drawImage(authors, 250, GAME_HEIGHT - 50, 300, 50, null); // write authors to screen

			menu = Toolkit.getDefaultToolkit().getImage("menu.png"); // create image

			g.drawImage(menu, 120, -20, 550, 750, null); // draw menu options to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw icon to screen

			icecream = Toolkit.getDefaultToolkit().getImage("icecream.gif"); // create image

			// draw ice cream graphics for hover effects
			if (icecream1) {
				g.drawImage(icecream, 180, 350, 60, 50, null);
				g.drawImage(icecream, 520, 350, 60, 50, null);

			}
			if (icecream2) {
				g.drawImage(icecream, 180, 410, 60, 50, null);
				g.drawImage(icecream, 520, 410, 60, 50, null); // draw image to screen

			}
			if (icecream3) {
				g.drawImage(icecream, 180, 470, 60, 50, null); // draw image to screen
				g.drawImage(icecream, 520, 470, 60, 50, null); // draw image to screen

			}
			if (icecream4) {
				g.drawImage(icecream, 180, 520, 60, 50, null); // draw image to screen
				g.drawImage(icecream, 520, 520, 60, 50, null); // draw image to screen

			}
			mouseInput(); // detects mouse input
		}

		if (controls) { // display player controls on screen

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw image to screen

			playerControls = Toolkit.getDefaultToolkit().getImage("playerControls.png"); // create image

			g.drawImage(playerControls, 50, 50, 700, 500, null); // draw image to screen

			backbtn = Toolkit.getDefaultToolkit().getImage("backbtn.png"); // create image

			g.drawImage(backbtn, 300, 550, 200, 100, null); // draw image to screen

			mouseInput(); // checks for mouse input

		}
		if (!scoreBoard && exitGame) { // terminate program after delay

			System.out.println("Bad Ice Cream Game Ended.");

			try { // catches any error and adds a 2 second delay

				Thread.sleep(2000);

			} catch (InterruptedException e) {

				e.printStackTrace();

			} // game ends after 2 seconds

			System.exit(0); // force program to end
		}

		if (scoreBoard) { // display score board - will update with saved high scores

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw image to screen

			scores = Toolkit.getDefaultToolkit().getImage("scores.png"); // create image

			g.drawImage(scores, 100, 50, 600, 500, null); // draw image to screen

			backbtn = Toolkit.getDefaultToolkit().getImage("backbtn.png"); // create image

			g.drawImage(backbtn, 300, 550, 200, 100, null); // draw image to screen

			mouseInput(); // checks for mouse input
		}

		if (playGame && level1) { // display game screen
			mouseInput(); // checks for mouse input

			snow = Toolkit.getDefaultToolkit().getImage("snow.png"); // create background image

			g.drawImage(snow, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

			// draw game graphics and objects

			ice.draw(g);
			igloo.draw(g); // draw igloo in center

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

			// display score and players on screen
			score.draw(g);
			player1.draw(g);
			player2.draw(g);

			// end condition for winner / loser
			if (onBanana == 16 && onGrape == 12) {
				roundWinner.draw(g);
				g.setFont(new Font("Consolas", Font.PLAIN, 20)); // set font type and size
				g.drawString("Press Enter to Return to Main Menu...", 200, 580); // draw winner result to screen
				nextLevel = true;
			}

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw image to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			settings = Toolkit.getDefaultToolkit().getImage("settings.png"); // create image

			g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			// fix settings !!
			if (cornerControls) { // display settings to user

				movementPics[0] = Toolkit.getDefaultToolkit().getImage("help1.png"); //
				// create image

				// movementPics[1] = Toolkit.getDefaultToolkit().getImage("help2.png"); //
				// create image

				// movementPics[2] = Toolkit.getDefaultToolkit().getImage("help3.png"); //
				// create image

				g.drawImage(movementPics[0], 150, 150, 500, 400, null); // draw image to screen

				g.setColor(Color.black); // set score elements to color white

				// draw line for continue button

				g.drawLine(310, 480, 490, 480);

				g.drawLine(310, 500, 490, 500);

				mouseInput(); // detects mouse input

				while (playGame && continueBtn) {

					for (int i = 0; i < movementPics.length; i++) {

						g.drawImage(movementPics[i], 150, 150, 500, 400, null); // draw image to screen

						repaint();
					}
				}
			}
		}

		if (level2 == true) {
			snow = Toolkit.getDefaultToolkit().getImage("snow.png"); // create background image
			g.drawImage(snow, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen
			level2Scenery.draw(g);

			for (int i = 0; i < 12; i++) {
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

			// display score and players on screen
			score.draw(g); // might need a new var
			player1.draw(g);
			player2.draw(g);
		}
		if (!audio) { // pause music

			cross = Toolkit.getDefaultToolkit().getImage("cross.png"); // create image

			g.drawImage(cross, GAME_WIDTH - 95, 0, 40, 40, null); // draw image to screen

			clipTimePosition = clip.getMicrosecondLength();

			clip.stop(); // stop music clip

		} else if (audio) {
			// resume music
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);

		}
	}

	// call the move methods in other classes to update positions for fluid
	// movements
	public void move() {
		player1.move();
		player2.move();
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

			// 300, 275 200, 200,
			/*
			 * if (player1.x >= 300 && player1.x <= 500 && player1.x >= 275 && player1.x <=
			 * 475) { player1.x = igloo.x; player1.y = igloo.y;
			 * 
			 * }
			 */

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

			/*
			 * // define game bounds for players beside igloo if (player1.x >= 250 &&
			 * player1.x <= 300 && player1.y >= 300 && player1.y <= 450) { player1.x = 250;
			 * //player1.y = 250; }
			 */

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

		if (level2 == true) {
			for (int i = 0; i < 12; i++) {
				if (player1.intersects(level2Ice.get(i)) && player1.x >= (level2IceX[i]+50) && player1.xVelocity < 0) {
					player1.x = level2IceX[i] + 50;
					player1.xVelocity = 0;
				}
				if (player1.intersects(level2Ice.get(i)) && player1.x <= level2IceX[i] && player1.xVelocity > 0) {
					player1.x = level2IceX[i] - 50;
					player1.xVelocity = 0;
				}
				if (player1.intersects(level2Ice.get(i)) && player1.y >= (level2IceY[i]+50) && player1.yVelocity < 0) {
					player1.y = level2IceY[i] + 50;
					player1.yVelocity = 0;	
				}
				if (player1.intersects(level2Ice.get(i)) && player1.y <= level2IceY[i] && player1.yVelocity > 0) {
					player1.y = level2IceY[i] - 50;
					player1.yVelocity = 0;	
				}
			}

			// detects collision between player 2 and iceblocks
			for (int i = 0; i < 12; i++) {
				if (player2.intersects(level2Ice.get(i)) && player2.x > level2IceX[i] && player2.xVelocity < 0) {
					player2.x = level2IceX[i] + 50;
				}
				if (player2.intersects(level2Ice.get(i)) && player2.x < level2IceX[i] && player2.xVelocity > 0) {
					player2.x = level2IceX[i] - 50;
				}
				if (player2.intersects(level2Ice.get(i)) && player2.y > level2IceY[i] && player2.yVelocity < 0) {
					player2.y = level2IceY[i] + 50;
				}
				if (player2.intersects(level2Ice.get(i)) && player2.y < level2IceY[i] && player2.yVelocity >= 0) {
					player2.y = level2IceY[i] - 50;
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
				move();
				checkCollision();
				repaint();
				delta--;
			}
		}
	}

	// send key input to classes for processing
	public void keyPressed(KeyEvent e) {

		player1.keyPressed(e);
		player2.keyPressed(e);

		// return to main menu option on controls and game page - cannot be acccessed
		// from other pages
		if (e.getKeyCode() == KeyEvent.VK_ENTER && nextLevel) {
			Score.score = 0;
			Score.score2 = 0;
			// set game boolean values only if game has ended
			level1 = false;
			level2 = true;
			mainMenu = false;
			playGame = false;
			controls = false;
			scoreBoard = false;

		}
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

		File music;
		AudioInputStream audioInput;

		try { // checks code for errors and executes catch
			music = new File(filepath);

			// check if music file exists before playing
			if (music.exists()) {

				audioInput = AudioSystem.getAudioInputStream(music);

				clip = AudioSystem.getClip(); // clip reference object from AudioStream

				clip.open(audioInput);

				clip.start();

				// clip.loop(Clip.LOOP_CONTINUOUSLY);

				clip.setMicrosecondPosition(clipTimePosition);

			} else { // output file is not found if music file doesn't exist
				System.out.println("\nFile cannot be found.");
			}

		} catch (Exception e) { // catches all other errors
			System.out.println(e);
		}
	}

	// classes are required to be overriden for mouse adapter
	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
}