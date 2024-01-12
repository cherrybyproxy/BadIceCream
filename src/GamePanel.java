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
	public Player1 player1;
	public Player2 player2;
	public Score score;
	public Score score2;
	public BlueCircle blueCircle;
	public BlueCircle blueCircle2;
	public RoundWinner roundWinner;

	public Image logo, menubg, playbtn, authors, settings, menu, mint, sound, cross, playerControls, ming, smokeyb,
			sorbetMenu, arrow, arrow2, icecream, snow, backbtn, scores, charselection, help1, help2, help3;

	// booleans for certain key input
	boolean playGame, exitGame, clickPlay, cornerControls, mainMenu, controls, drawBtn, icecream1, icecream2, icecream3,
			icecream4, scoreBoard;
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
	public ArrayList<Banana> banana2 = new ArrayList<Banana>();
	public int onBanana = 0;

	public boolean[] drawGrape = new boolean[12];
	public ArrayList<Grape> grapeCoord = new ArrayList<Grape>();
	public int onGrape = 0;

	public GamePanel() { // constructor sets values for booleans and creates game elements

		playMusic("menu music.wav"); // plays sound file

		// set game condition booleans
		playGame = false;
		exitGame = false;
		mainMenu = false;
		clickPlay = true;
		controls = false;
		scoreBoard = false;
		audio = true;

		cornerControls = false;

		// set booleans for ice cream graphics
		icecream1 = false;
		icecream2 = false;
		icecream3 = false;
		icecream4 = false;

		// create graphics and set start location on game screen
		ice = new Ice(0, 0);

		// create blue circle graphic
		blueCircle = new BlueCircle(345, 635);
		blueCircle2 = new BlueCircle(395, 635);

		// create ice cream characters and score
		player1 = new Player1(350, 400);
		player2 = new Player2(400, 400);

		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		score2 = new Score(GAME_WIDTH, GAME_HEIGHT);

		roundWinner = new RoundWinner(GAME_WIDTH, GAME_HEIGHT);
		igloo = new Igloo(350, 300);

		// fill Array lists with fruit objects
		Arrays.fill(drawBanana, true);
		Arrays.fill(drawGrape, true);

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

		// add banana graphics to game panel
		banana2.add(new Banana(150, 150));
		banana2.add(new Banana(150, 200));
		banana2.add(new Banana(200, 150));
		banana2.add(new Banana(150, 450));
		banana2.add(new Banana(150, 500));
		banana2.add(new Banana(200, 500));
		banana2.add(new Banana(550, 150));
		banana2.add(new Banana(600, 200));

		banana2.add(new Banana(600, 150));
		banana2.add(new Banana(600, 450));
		banana2.add(new Banana(550, 500));
		banana2.add(new Banana(600, 500));
		banana2.add(new Banana(300, 300));
		banana2.add(new Banana(300, 350));
		banana2.add(new Banana(450, 300));
		banana2.add(new Banana(450, 350));

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

				// conditions for game play based on mouse input
				if (clickPlay) {
					// display starter screen for user to progress to main menu
					if (btnX >= 250 && btnX <= 550 && btnY >= 565 && btnY <= 640) {

						mainMenu = true;
						clickPlay = false;
					}
				}

				// toggle music on and off
				if (btnX >= GAME_WIDTH - 100 && btnX <= GAME_WIDTH - 60 && btnY >= 0 && btnY <= 40) {

					audio = !audio;

					repaint();
				}

				// display settings in top right corner
				if (btnX >= GAME_WIDTH - 50 && btnX <= GAME_WIDTH - 10 && btnY >= 0 && btnY <= 40) {

					cornerControls = !cornerControls; // invert boolean value
				}

				// back to main menu button click
				if (controls | scoreBoard & (btnX >= 300 && btnX <= 500 && btnY >= 200 && btnY <= 650)) {

					// display main menu
					mainMenu = true;
					controls = false;
					scoreBoard = false;
					exitGame = false;

					repaint(); // reset everything on screen
				}

				// Display Level 1 of game
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 350 && btnY <= 410) {

					playGame = true;
					mainMenu = false;

					repaint();
				}

				// Display Score Leader Board
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 410 && btnY <= 470) {

					scoreBoard = true;

					mainMenu = false;
					exitGame = false;

				}
				// Display Player Controls
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 470 && btnY <= 520) {

					controls = true;
					mainMenu = false;
					exitGame = false;

					repaint();

				}

				// Exit Game when Clicked
				if (mainMenu && controls | scoreBoard | btnX >= 180 && btnX <= 760 && btnY >= 520 && btnY <= 570) {

					exitGame = true;

					// hide all screens to exit game
					mainMenu = false;
					playGame = false;

					clickPlay = false;
					exitGame = false;
					controls = false;
				}
			}

			@Override
			public void mouseMoved(MouseEvent e) { // detects mouse motion for hover effects

				// obtain coordinates for mouse motion
				btnX = e.getX();
				btnY = e.getY();

				// display icecream animations when hovering over each menu option
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 350 && btnY <= 410) {

					icecream1 = true;
					repaint();

				} else {
					icecream1 = false;
				}
				// display effects when hovering over second option
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 410 && btnY <= 470) {

					icecream2 = true;
					repaint();

				} else {
					icecream2 = false;
				}
				// display effects when hovering over third option
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 470 && btnY <= 520) {

					icecream3 = true;
					repaint();

				} else {
					icecream3 = false;
				}
				// display effects when hovering over fourth option
				if (mainMenu && btnX >= 180 && btnX <= 760 && btnY >= 520 && btnY <= 570) {

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

		settings = Toolkit.getDefaultToolkit().getImage("settings.png"); // create image

		g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw audio icon to screen

		sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

		g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw settings icon to screen

		mouseInput(); // checks for mouse input

		if (mainMenu) { // run all conditions for main menu screen

			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw background image to screen

			g.drawImage(authors, 250, GAME_HEIGHT - 50, 300, 50, null); // write authors to screen

			menu = Toolkit.getDefaultToolkit().getImage("menu.png"); // create image

			g.drawImage(menu, 120, -20, 550, 750, null); // draw menu options to screen

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw icon to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw icon to screen

			icecream = Toolkit.getDefaultToolkit().getImage("icecream.gif"); // create image

			// draw ice cream graphics for hover effects
			if (icecream1) {
				g.drawImage(icecream, 180, 350, 60, 50, null);
				g.drawImage(icecream, 520, 350, 60, 50, null);

			}
			if (icecream2) {
				g.drawImage(icecream, 180, 410, 60, 50, null);
				g.drawImage(icecream, 520, 410, 60, 50, null);

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

			g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			playerControls = Toolkit.getDefaultToolkit().getImage("playerControls.png"); // create image

			g.drawImage(playerControls, 50, 50, 700, 500, null); // draw image to screen

			backbtn = Toolkit.getDefaultToolkit().getImage("backbtn.png"); // create image

			g.drawImage(backbtn, 300, 550, 200, 100, null); // draw image to screen

		}
		if (exitGame) { // terminate program after delay

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

			g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

			scores = Toolkit.getDefaultToolkit().getImage("scores.png"); // create image

			g.drawImage(scores, 100, 50, 600, 500, null); // draw image to screen

			backbtn = Toolkit.getDefaultToolkit().getImage("backbtn.png"); // create image

			g.drawImage(backbtn, 300, 550, 200, 100, null); // draw image to screen

		}

		if (playGame) { // display game screen

			snow = Toolkit.getDefaultToolkit().getImage("snow.png"); // create background image

			g.drawImage(snow, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw image to screen

			// draw game graphics and objects

			ice.draw(g);

			if (onBanana == 16) {
				blueCircle2.draw(g);
			} else {
				blueCircle.draw(g);
			}

			for (int i = 0; i < 20; i++) {
				iceC2.get(i).draw(g);
			}

			igloo.draw(g); // draw igloo in center

			for (int i = 0; i < 16; i++) {
				if (drawBanana[i]) {
					banana2.get(i).draw(g);
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

				mainMenu = true; // return to main menu
			}

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw image to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw image to screen

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

		if (cornerControls) { // display settings to user

			help1 = Toolkit.getDefaultToolkit().getImage("help1.png"); // create image

			g.drawImage(help1, 150, 150, 500, 400, null); // draw image to screen

			g.setColor(Color.white); // set score elements to color white

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
			if (player1.intersects(banana2.get(i)) && drawBanana[i] == true) {

				Score.score += 150; // increase score

				drawBanana[i] = false;
				onBanana++;
			}
		}

		for (int i = 0; i < 16; i++) {
			// conditions for when player 2 collects fruits
			if (player2.intersects(banana2.get(i)) && drawBanana[i] == true) {

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