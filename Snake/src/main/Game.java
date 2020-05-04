package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import objects.Apple;
import objects.ObjectID;
import objects.Snake;

public class Game extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	
	private boolean isRunning = false;
	
	private Image appleScore;
	private Image trophy;
	
	private Handler handler;
	
	private Snake snake;
	private Apple apple;
	private GameLogic logicMan;
	
	public Game(int width, int height, String title) {
		appleScore = Toolkit.getDefaultToolkit().createImage("res/apple.png");
		trophy = Toolkit.getDefaultToolkit().createImage("res/trophy.png");
		
		handler = new Handler();
		apple = new Apple(350, 250, this, ObjectID.Apple);
		handler.addObject(apple);
		
		snake = new Snake(100, 250, ObjectID.Snake);
		handler.addObject(snake);
		
		addKeyListener(new KeyInput(snake));
		
		logicMan = new GameLogic(snake, apple);
		
		setTitle(title);
		
		Dimension dim = new Dimension(width, height);
		setPreferredSize(dim);
		setMaximumSize(dim);
		setMinimumSize(dim);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		requestFocus();
		
		setVisible(true);
	}

	@Override
	public void run() 
	{
		
		long lastTime = System.nanoTime();
		double ticks = 60.0; // Default 60 fps, might change later
		double ns = 1000000000 / ticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		
		while(isRunning) {
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
				frames = 0;
				updates = 0;
			}
		}
		
		stop();
	}
	
	public synchronized void start() {
		if (isRunning) {
			return;
		}
		
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Color scoreBar = new Color(0, 153, 0);
		Color outerColor = new Color(51, 153, 255);
		
		g.setColor(Color.green);
		g.fillRect(0, 100, getWidth(), getHeight());
		g.setColor(scoreBar);
		g.fillRect(0, 0, getWidth(), 100);
		
		g.setColor(outerColor);
		// vertical lines
		for(int i = 0; i < getWidth(); i++) {
			if (i <= 35 || i >= getWidth() - 35) {
				g.drawLine(i, 100, i, getHeight());
			}
		}
		
		// horizontal lines
		for(int i = 100; i < getHeight(); i++) {
			if(i <= 135 || i >= getHeight() - 35) {
				g.drawLine(0, i, getWidth(), i);
			}
		}
		
		g.drawImage(appleScore, 35, 45, this);
		g.drawImage(trophy, 130, 45, this);
		
		handler.render(g);
		logicMan.render(g);
		
		g.dispose();
		bs.show();
	}
	
	private void update() {
		handler.update();
		logicMan.update();
	}
	
	public synchronized void stop() {
		if (!isRunning) {
			return;
		}
		
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		isRunning = false;
	}

}
