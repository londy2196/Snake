package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private Image appleScore = Toolkit.getDefaultToolkit().createImage("res/apple.png");
	private Image appleImg = Toolkit.getDefaultToolkit().createImage("res/appleingame.png");
	private Image trophy = Toolkit.getDefaultToolkit().createImage("res/trophy.png");
	private Image trophyMenu = Toolkit.getDefaultToolkit().createImage("res/trophymenu.png");
	private Image tbIcon = Toolkit.getDefaultToolkit().createImage("res/tbIcon.png");
	
	private Random r;
	
	private boolean gameOver;
	private boolean fpsSelected = false;

	private Thread thread;
	
	private int appleX, appleY;
	
	private int[] tailX;
	private int[] tailY;
	private int tailLength;
	
	private int score;

	private int highScore = 0;
	
	// For fps counter
	private int fps;
	
	private long gameSession = 0;
	
	private enum Direction {
		STOP, LEFT, RIGHT, UP, DOWN;
	}
	
	private Direction dir;
	
	public enum State {
		MENU, PLAYING, OPTIONS, GAME_OVER;
	}
	
	public static State gameState = State.MENU;
	
	private MouseHandler mouseMan;
	
	public Game(int width, int height, String title) {
		setTitle(title);
				
		Dimension dim = new Dimension(width, height);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		setResizable(false);
		
		setIconImage(tbIcon);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.BLACK);
		addKeyListener(new KeyInput());
		
		setVisible(true);
		
		init();
		addMouseListener(mouseMan);
		
		start();
	}
	
	private void init() {
		dir = Direction.STOP;
		r = new Random();
		mouseMan = new MouseHandler(this);
		
		tailX = new int[2000];
		tailY = new int[2000];
		tailLength = 15;
		
		for(int i = 0; i < tailLength; i++) {
			tailX[i] = 100;
			tailY[i] = 260;
		}
		
		score = 0;
		
		appleX = 350;
		appleY = 250;
	}
	
	@Override
	public void run() 
	{
		long lastTime = System.nanoTime();
		double ticks = 60.0; // Runs at 60fps
		double ns = 1000000000 / ticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(!gameOver) {
			long currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / ns;
			lastTime = currentTime;
			while(delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " / TICKS: " + updates);
				fps = frames;
				
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		switch (gameState) 
		{
			case GAME_OVER:
				g2d.setStroke(new BasicStroke(3.0f));
			
				g.setFont(new Font("Arial", Font.BOLD, 30));
				g.setColor(Color.red);
				g.drawString("Game Over!", 160, 127);
			
				g.setColor(Color.BLUE);
				g.drawString("Retry", 238, 75);
				g.drawString("Quit", 377, 75);
			
				g.setColor(Color.black);
				g.drawRect(220, 40, 115, 50);
				g.drawRect(350, 40, 115, 50);
			
				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, 23));
			
				String scoreStr = String.valueOf(score);
				String hScoreStr = String.valueOf(highScore);
				g.drawString(scoreStr, 80, 75);
				g.drawString(hScoreStr, 175, 75);
			
				break;
				
			case MENU:
				g2d.setColor(new Color(51, 153, 255));
				g2d.setStroke(new BasicStroke(5.0f));
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				
				g2d.setColor(Color.black);
				g2d.setFont(new Font("Arial", Font.BOLD, 40));
				g2d.drawString("Snake!", 175, 100);
				
				g2d.setFont(new Font("Arial", Font.BOLD, 35));
				g2d.drawRect(92, 205, 300, 50);
				g2d.drawString("Play", 200, 240);
				
				g2d.drawRect(92, 285, 300, 50);
				g2d.drawString("Options", 176, 320);
				
				g2d.drawRect(92, 365, 300, 50);
				g2d.drawString("Quit", 200, 403);
				
				if(gameSession != 0) {
					drawCurHighScore(g);
				}
				
				break;
			case OPTIONS:
				g2d.setColor(new Color(51, 153, 255));
				g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
				
				g2d.setFont(new Font("Arial", Font.BOLD, 40));
				g2d.setColor(Color.BLACK);
				g2d.drawString("Options", 170, 100);

				g2d.setStroke(new BasicStroke(5.0f));
				g2d.setFont(new Font("Arial", Font.BOLD, 35));
				
				// FPS Counter
				g2d.setFont(new Font("Arial", Font.BOLD, 25));
				g2d.drawString("Turn on FPS Counter: ", 10, 190);
				g2d.drawString("Off", 295, 150);
				g2d.drawString("On", 395, 150);
				g2d.drawRect(300, 170, 25, 25);
				g2d.drawRect(400, 170, 25, 25);
				
				if(fpsSelected) {
					g2d.fillRect(400, 170, 25, 25);
				}
				else {
					g2d.fillRect(300, 170, 25, 25);
				}
			
				// Back
				g2d.setFont(new Font("Arial", Font.BOLD, 35));
				g2d.drawRect(143, 400, 200, 50);
				g2d.drawString("Back", 200, 438);
				break;
				
			case PLAYING:
				g.setColor(Color.green);
				g.fillRect(0, 100, this.getWidth(), this.getHeight());
				g.setColor(new Color(0, 153, 0));
				g.fillRect(0, 0, this.getWidth(), 100);
				
				// vertical lines
				g.setColor(new Color(51, 153, 255));
				for(int i = 0; i < this.getWidth(); i++) 
				{
					if (i <= 35 || i >= this.getWidth() - 35) 
					{
						g.drawLine(i, 100, i, this.getHeight());
					}
				}
				
				// horizontal lines
				for(int i = 100; i < this.getHeight(); i++) 
				{
					if(i <= 135 || i >= this.getHeight() - 35) 
					{
						g.drawLine(0, i, this.getWidth(), i);
					}
				}
				
				g.drawImage(appleImg, appleX, appleY, this);
				
				g.setColor(Color.BLUE);
				for(int i = 0; i < tailLength; i++) 
				{
					g.fillRect(tailX[i], tailY[i], 20, 20);
				}
				
				// apple and trophy images 
				g.drawImage(appleScore, 35, 45, this);
				g.drawImage(trophy, 130, 45, this);
				
				g.setColor(Color.white);
				g.setFont(new Font("Arial", Font.BOLD, 23));
				
				scoreStr = String.valueOf(score);
				hScoreStr = String.valueOf(highScore);
				g.drawString(scoreStr, 80, 75);
				g.drawString(hScoreStr, 175, 75);
				
				gameSession++;
				
				break;	
			default:
				break;
		}
		
		if(fpsSelected) {
			g.setFont(new Font("Arial", Font.BOLD, 18));
			g.setColor(Color.white);
			g.drawString("FPS: " + fps, 398, 45);
		}
		
		Toolkit.getDefaultToolkit().sync();
		
		g.dispose();
		g2d.dispose();
		bs.show();
	}
	
	private void update() 
	{
		if(gameState == State.PLAYING) 
		{
			switch(dir) 
			{
				case UP:
					tailY[0] -= 2;
					break;
				case DOWN:
					tailY[0] += 2;
					break;
				case LEFT:
					tailX[0] -= 2;
					break;
				case RIGHT:
					tailX[0] += 2;
					break;
				default:
					break;
			}
			
			if(new Rectangle(tailX[0], tailY[0], 20, 20).intersects(new Rectangle(appleX, appleY, 32, 32))) 
			{
				tailLength += 30;
				
				int randX = r.nextInt((440 - 35) + 1) + 35;
				int randY = r.nextInt((420 - 135) + 1) + 135;
				
				for(int i = 0; i < tailLength; i++) 
				{
					if(new Rectangle(randX, randY, 32, 32).intersects(new Rectangle(tailX[i], tailY[i], 20, 20))) 
					{
						randX = r.nextInt((440 - 35) + 1) + 35;
						randY = r.nextInt((420 - 135) + 1) + 135;
					}
				}
				
				appleX = randX;
				appleY = randY;
				
				if(score != highScore)
				{
					score++;
				}
				else {
					score++;
					highScore++;
				}
			}
			
			for(int i = 1; i < tailLength; i++) 
			{
				if(tailX[i] == tailX[0] && tailY[i] == tailY[0] && i > 15) 
				{
					gameState = State.GAME_OVER;
				}
			}
			
			if((tailX[0] > 447  || tailX[0] < 35) || (tailY[0] > 447 || tailY[0] < 135)) 
			{
				gameState = State.GAME_OVER;
			}
			
			for(int i = tailLength; i > 0; i--) 
			{
				tailX[i] = tailX[i - 1];
				tailY[i] = tailY[i - 1];
			}
		}
	}
	
	private synchronized void start() 
	{
		if(gameOver) return;
		
		thread = new Thread(this);
		thread.start();
		gameOver = false;
	}
	
	private synchronized void stop() 
	{
		if(!gameOver) return;
		
		try 
		{
			thread.join();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		gameOver = true;
	}
	
	/*
	 * Sets apple and snake in original position.
	 *  Also resets the score to zero.
	 */
	public void resetGame() 
	{
		init();
	}
	
	private void drawCurHighScore(Graphics g) {
		String scoreStr = String.valueOf(highScore);

		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(scoreStr, 260, 165);

		g.drawImage(trophyMenu, 150, 120, this);
	}
	
	/*
	 * Nested class for key input handling
	 */
	private class KeyInput extends KeyAdapter {
		
		private boolean up = false;
		private boolean down = false;
		private boolean left = false;
		private boolean right = false;
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if(key == KeyEvent.VK_W && !down) 
			{
				dir = Direction.UP;	
				up = true;
				left = false;
				right = false;
			}
			
			if(key == KeyEvent.VK_S && !up) 
			{
				dir = Direction.DOWN;
				down = true;
				left = false;
				right = false;
			}
			
			if(key == KeyEvent.VK_A && !right) 
			{
				dir = Direction.LEFT;
				left = true;
				up = false;
				down = false;
			}
			
			if(key == KeyEvent.VK_D && !left) 
			{
				dir = Direction.RIGHT;
				right = true;
				up = false;
				down = false;
			}
			
			if(key == KeyEvent.VK_ESCAPE) System.exit(-1); // remove when finished
		}	
	}
	
	// GETTERS & SETTERS
	public void setFPSOption(boolean fpsSelected) {
		this.fpsSelected = fpsSelected;
	}

}
