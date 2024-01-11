
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

// extends JPanel to access draw methods
// implements Runnable to run multiple things at once
// implements KeyListener to listen to keyboard input
public class GamePanel extends JPanel implements Runnable, KeyListener, ActionListener, MouseMotionListener {

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

	public Image logo, menubg, playbtn, authors, settings, menu, sound, cross, instructions, instructions2, 
	ming, smokeyb, sorbet, arrow, arrow2, icecream;

	// booleans for certain key input
	boolean playGame;
	boolean gameEnd;
	boolean clickPlay;

	boolean mainMenu;
	boolean controls;
	boolean audio;
	// coordinates for play button on main menu
	int btnX;
	int btnY;
	boolean drawBtn;
	
	boolean icecream1, icecream2, icecream3, icecream4;

	public Igloo igloo;
	JButton play;

	// public IceC[] iceC = new IceC[15]; // might be useless code; i cannot rmbr
	// what this was for
	public ArrayList<IceC> iceC2 = new ArrayList<IceC>();
	public int[] iceCX = new int[20];
	public int[] iceCY = new int[20];

	public Banana[] banana = new Banana[16];
	public boolean[] drawBanana = new boolean[16];
	public ArrayList<Banana> banana2 = new ArrayList<Banana>();

	//private MouseAdapter mouseAdapter; // Declare a MouseAdapter instance
	
		public GamePanel() {

		playGame = false;
		gameEnd = false;

		mainMenu = false;
		clickPlay = true;
		controls = false;
		audio = true;
		
		icecream1=false;
		icecream2=false;
		icecream3=false;
		icecream4 = false;

		ice = new Ice(0, 0);

		player1 = new Player1(350, 400);
		player2 = new Player2(400, 400);
		score = new Score(GAME_WIDTH, GAME_HEIGHT);
		score2 = new Score(GAME_WIDTH, GAME_HEIGHT);
		igloo = new Igloo(350, 300);

		Arrays.fill(drawBanana, true);

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

		this.setFocusable(true); // make everything in this class appear on the screen
		this.addKeyListener(this); // start listening for keyboard input
		this.addMouseMotionListener(this);
		
		// add the MousePressed method from the MouseAdapter - by doing this we can
		// listen for mouse input. We do this differently from the KeyListener because
		// MouseAdapter has SEVEN mandatory methods - we only need one of them, and we
		// don't want to make 6 empty methods
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {

			}
			public void mouseMoved(MouseEvent e) {

			}
		});
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));

		// make this class run at the same time as other classes (without this each
		// class would "pause" while another class runs). By using threading we can
		// remove lag, and also allows us to do features like display timers in real
		// time!
		gameThread = new Thread(this);
		gameThread.start();
	}

	public void paintComponent(Graphics g) {
		// Required for painting graphics and images including all game elements
		super.paintComponent(g);

	}

	// paint is a method in java.awt library that we are overriding. It is a special
	// method - it is called automatically in the background in order to update what
	// appears in the window. You NEVER call paint() yourself
	public void paint(Graphics g) {
		// we are using "double buffering" here - if we draw images directly onto the
		// screen, it takes time and the human eye can actually notice flashes of lag as
		// each pixel on the screen is drawn one at a time. Instead, we are going to
		// draw images OFF the screen (outside dimensions of the frame), then simply
		// move the image on screen as needed.
		image = createImage(GAME_WIDTH, GAME_HEIGHT); // draw off screen
		graphics = image.getGraphics();
		draw(graphics); // update the positions of everything on the screen
		g.drawImage(image, 0, 0, this); // redraw everything on the screen

	}

	public void mouseInput() {

		// Instantiate a MouseAdapter and override the mousePressed method
		MouseAdapter mouseAdapter = new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {

				btnX = e.getX();
				btnY = e.getY();

				if (clickPlay) {

					if (btnX >= 250 && btnX <= 550 && btnY >= 565 && btnY <= 640) {

						System.out.println("You clicked the mouse");

						mainMenu = true;
						clickPlay = false;
					}
				}

				// toggle music on and off
				if (btnX >= GAME_WIDTH - 100 && btnX <= GAME_WIDTH - 60 && btnY >= 0 && btnY <= 40) {

					System.out.println("audio toggle");

					audio = !audio;
					
					//playMusic("arcade-opener.wav"); // plays sound file
					
					repaint();

				}

				// view controls and settings
				if (btnX >= GAME_WIDTH - 50 && btnX <= GAME_WIDTH - 10 && btnY >= 0 && btnY <= 40) {

					System.out.println("You clicked the mouse");

					controls = !controls;
					System.out.println("controls");

				}
				if (mainMenu) {
				//PLAY BUTTON
				if (btnX >= 180 && btnX <= 760 && btnY >= 350 && btnY <= 410) {
					//g.drawLine(180, 350, 580, 350);
					
					playGame = true;
					mainMenu = false;
					
					repaint();
					
				} 
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {

				btnX = e.getX();
				btnY = e.getY();
				
				//PLAY BUTTON
				if (btnX >= 180 && btnX <= 760 && btnY >= 350 && btnY <= 410) {
					//g.drawLine(180, 350, 580, 350);
					
					icecream1 = true;
					System.out.println("1");
					repaint();
					
				} else {
					icecream1 = false;
				}
				if (btnX >= 180 && btnX <= 760 && btnY >= 410 && btnY <= 470) {
					//g.drawLine(180, 350, 580, 350);
				
					icecream2 = true;
					System.out.println("2");
					repaint();
				} else {
					icecream2 = false;
				}
				if (btnX >= 180 && btnX <= 760 && btnY >= 470 && btnY <= 520) {
					//g.drawLine(180, 350, 580, 350);
					
					icecream3 = true;
					repaint();
					System.out.println("3");
				} else {
					icecream3 = false;
				}
				if (btnX >= 180 && btnX <= 760 && btnY >= 520 && btnY <= 570) {
					//g.drawLine(180, 350, 580, 350);
					
					icecream4 = true;
					repaint();
					System.out.println("4");
				
				} else {
					icecream4 = false;
				}
			}
		};
		// Add the mouseAdapter to your component
				addMouseListener(mouseAdapter);
				addMouseMotionListener(mouseAdapter);
			
				repaint();

		
	}


	// call the draw methods in each class to update positions as things move
	public void draw(Graphics g) {

		// MAIN MENU LOGIC
		menubg = Toolkit.getDefaultToolkit().getImage("menubg.gif"); // create image

		logo = Toolkit.getDefaultToolkit().getImage("logo.png"); // create image

		playbtn = Toolkit.getDefaultToolkit().getImage("playbtn.png"); // create image

		authors = Toolkit.getDefaultToolkit().getImage("authors.png"); // create image

		g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw loser image to screen

		g.drawImage(logo, 200, 0, 400, 600, null); // draw loser image to screen

		// Start new thread to implement runnable interface to delay and terminate game
		new Thread(new Runnable() {
			@Override
			public void run() { // override run method

				// catch block used if another thread interrupts this thread
				try {
					while (true) {
						drawBtn = true;
						Thread.sleep(3000);
						
						drawBtn = false;
						// g.dispose();
						Thread.sleep(3000);
						repaint();

					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}).start(); // begins execution of thread

		if (drawBtn) {
			g.drawImage(playbtn, 250, 565, 300, 75, null); // draw loser image to screen

		}
		g.drawImage(authors, 250, GAME_HEIGHT - 50, 300, 50, null); // draw loser image to screen

		settings = Toolkit.getDefaultToolkit().getImage("controls.png"); // create image

		g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw loser image to screen

		sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

		g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw loser image to screen

		mouseInput();

		if (mainMenu) {
			menubg = Toolkit.getDefaultToolkit().getImage("menubg.gif"); // create image
			g.drawImage(menubg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null); // draw loser image to screen

			authors = Toolkit.getDefaultToolkit().getImage("authors.png"); // create image
			g.drawImage(authors, 250, GAME_HEIGHT - 50, 300, 50, null); // draw loser image to screen

			menu = Toolkit.getDefaultToolkit().getImage("menu.png"); // create image
			g.drawImage(menu, 120, -20, 550, 750, null); // draw loser image to screen

			settings = Toolkit.getDefaultToolkit().getImage("controls.png"); // create image

			g.drawImage(sound, GAME_WIDTH - 100, 0, 40, 40, null); // draw loser image to screen

			sound = Toolkit.getDefaultToolkit().getImage("sound.png"); // create image

			g.drawImage(settings, GAME_WIDTH - 50, 0, 40, 40, null); // draw loser image to screen

			g.setColor(Color.white); // set score elements to color white

			g.drawLine(180, 350, 580, 350);
			g.drawLine(180, 410, 580, 410);
			g.drawLine(180, 470, 580, 470);
			g.drawLine(180, 520, 580, 520);
			g.drawLine(180, 570, 580, 570);
			
			icecream = Toolkit.getDefaultToolkit().getImage("icecream.gif"); // create image
			
			
			if (icecream1) {
			g.drawImage(icecream, 180, 350, 60, 50, null); // draw loser image to screen
			g.drawImage(icecream, 520, 350, 60, 50, null); // draw loser image to screen
			//icecream1 = false;
			}
			if (icecream2) {
			g.drawImage(icecream, 180, 410, 60, 50, null); // draw loser image to screen
			g.drawImage(icecream, 520, 410, 60, 50, null); // draw loser image to screen
			//icecream2 = false;
			
			} if (icecream3) {
			g.drawImage(icecream, 180, 470, 60, 50, null); // draw loser image to screen
			g.drawImage(icecream, 520, 470, 60, 50, null); // draw loser image to screen
			//icecream3 = false;
			
			} if (icecream4) {
			g.drawImage(icecream, 180, 520, 60, 50, null); // draw loser image to screen
			g.drawImage(icecream, 520, 520, 60, 50, null); // draw loser image to screen
			//icecream4 = false;
			}
			mouseInput();
		}
		if (!audio) {

			cross = Toolkit.getDefaultToolkit().getImage("cross.png"); // create image

			g.drawImage(cross, GAME_WIDTH - 100, 0, 40, 40, null); // draw loser image to screen
			
		}
		if (!controls) {

			cross = Toolkit.getDefaultToolkit().getImage("cross.png"); // create image

			g.drawImage(cross, GAME_WIDTH - 100, 0, 40, 40, null); // draw loser image to screen
		
		}
		if (playGame) {

			ice.draw(g);

			for (int i = 0; i < 20; i++) {
				iceC2.get(i).draw(g);
			}

			igloo.draw(g);

			for (int j = 0; j < 16; j++) {
				if (drawBanana[j]) {
					banana2.get(j).draw(g);
				}
			}

			score.draw(g);
			player1.draw(g);
			player2.draw(g);

		}

	}

	public void mousePressed(MouseEvent e) {
		/*
		 * playbtnX = e.getX(); playbtnY = e.getY();
		 * 
		 * if(250 > playbtnX && 250 < playbtnX+300 && 550 > playbtnY && 550 <
		 * playbtnY+75) { mainMenu = false;
		 * 
		 * }
		 * 
		 * repaint(); System.out.println("You clicked the mouse");
		 */
	}

	// call the move methods in other classes to update positions
	// this method is constantly called from run(). By doing this, movements appear
	// fluid and natural. If we take this out the movements appear sluggish and
	// laggy
	public void move() {
		player1.move();
		player2.move();
	}

	// handles all collision detection and responds accordingly
	public void checkCollision() {

		for (int m = 0; m < 20; m++) {
			if (player1.intersects(iceC2.get(m)) && player1.x > iceCX[m] && player1.xVelocity < 0) {
				player1.x = iceCX[m] + 50;
			}
			if (player1.intersects(iceC2.get(m)) && player1.x < iceCX[m] && player1.xVelocity > 0) {
				player1.x = iceCX[m] - 50;
			}
			if (player1.intersects(iceC2.get(m)) && player1.y > iceCY[m] && player1.yVelocity < 0) {
				player1.y = iceCY[m] + 50;
			}
			if (player1.intersects(iceC2.get(m)) && player1.y < iceCY[m] && player1.yVelocity > 0) {
				player1.y = iceCY[m] - 50;
			}
		}

		for (int m = 0; m < 20; m++) {
			if (player2.intersects(iceC2.get(m)) && player2.x > iceCX[m] && player2.xVelocity < 0) {
				player2.x = iceCX[m] + 50;
			}
			if (player2.intersects(iceC2.get(m)) && player2.x < iceCX[m] && player2.xVelocity > 0) {
				player2.x = iceCX[m] - 50;
			}
			if (player2.intersects(iceC2.get(m)) && player2.y > iceCY[m] && player2.yVelocity < 0) {
				player2.y = iceCY[m] + 50;
			}
			if (player2.intersects(iceC2.get(m)) && player2.y < iceCY[m] && player2.yVelocity > 0) {
				player2.y = iceCY[m] - 50;
			}
		}

		/*
		 * // strange movement - fix later if(player1.intersects(igloo) && (player1.x +
		 * 100) > igloo.x && player1.xVelocity < 0) { player1.x = igloo.x + 100; }
		 * if(player1.intersects(igloo) && player1.x < igloo.x && player1.xVelocity > 0)
		 * { player1.x = igloo.x - 50; } if(player1.intersects(igloo) && (player1.y +
		 * 100) > igloo.y && player1.yVelocity < 0) { player1.y = igloo.y + 100; }
		 * if(player1.intersects(igloo) && player1.y < igloo.y && player1.yVelocity > 0)
		 * { player1.y = igloo.y - 50; }
		 */

		for (int k = 0; k < 16; k++) {
			if (player1.intersects(banana2.get(k)) && drawBanana[k] == true) {
				Score.score += 150;
				drawBanana[k] = false;
			}
		}

		for (int n = 0; n < 16; n++) {
			if (player2.intersects(banana2.get(n)) && drawBanana[n] == true) {
				Score.score2 += 150;
				drawBanana[n] = false;
			}
		}

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

	// run() method is what makes the game continue running without end. It calls
	// other methods to move objects, check for collision, and update the screen
	public void run() {
		// the CPU runs our game code too quickly - we need to slow it down! The
		// following lines of code "force" the computer to get stuck in a loop for short
		// intervals between calling other methods to update the screen.
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long now;

		while (true) { // this is the infinite game loop
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

	// if key is pressed
	public void keyPressed(KeyEvent e) {
		player1.keyPressed(e);
		player2.keyPressed(e);
	}

	// if a key is released
	public void keyReleased(KeyEvent e) {
		player1.keyReleased(e);
		player2.keyReleased(e);
	}

	// left empty because we don't need it; must be here because it is required to
	// be overridded by the KeyListener interface
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent evt) {

		if (evt.getActionCommand().equals("Reset")) {

		}
		// repaint();

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
				Clip clip = AudioSystem.getClip(); // clip reference object from AudioStream
				clip.open(audioInput);

				clip.start();

			} else { // output file is not found if music file doesn't exist
				System.out.println("\nFile cannot be found.");
			}

		} catch (Exception e) { // catches all other errors
			System.out.println(e);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}