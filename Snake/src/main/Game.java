package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Game {
	
	private Image appleScore = Toolkit.getDefaultToolkit().createImage("res/apple.png");
	private Image trophy = Toolkit.getDefaultToolkit().createImage("res/trophy.png");
	private Image trophyMenu = Toolkit.getDefaultToolkit().createImage("res/trophymenu.png");
	
	private GameLogic logicMan;
	private Handler handler;
	private Window window;
	
	public Game(GameLogic logicMan, Handler handler, Window window) {
		this.logicMan = logicMan;
		this.handler = handler;
		this.window = window;
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		if(window.gameState == GameState.Playing) {
			g.setColor(Color.green);
			g.fillRect(0, 100, window.getWidth(), window.getHeight());
			g.setColor(new Color(0, 153, 0));
			g.fillRect(0, 0, window.getWidth(), 100);
			
			// vertical lines
			g.setColor(new Color(51, 153, 255));
			for(int i = 0; i < window.getWidth(); i++) {
				if (i <= 35 || i >= window.getWidth() - 35) {
					g.drawLine(i, 100, i, window.getHeight());
				}
			}
			
			// horizontal lines
			for(int i = 100; i < window.getHeight(); i++) {
				if(i <= 135 || i >= window.getHeight() - 35) {
					g.drawLine(0, i, window.getWidth(), i);
				}
			}
			
			// apple and trophy images 
			g.drawImage(appleScore, 35, 45, window);
			g.drawImage(trophy, 130, 45, window);
			
			handler.render(g);
			logicMan.render(g);
		}
		
		if(window.gameState == GameState.GameOver) {
			g2d.setStroke(new BasicStroke(1.0f));
			
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.setColor(Color.red);
			g.drawString("Game Over!", 160, 127);
			
			g.setColor(Color.BLUE);
			g.drawString("Retry", 238, 75);
			g.drawString("Quit", 377, 75);
			
			g.setColor(Color.black);
			g.drawRect(220, 40, 115, 50);
			g.drawRect(350, 40, 115, 50);
			
			logicMan.render(g);
		}
		
		if(window.gameState == GameState.Menu) {	
			g2d.setColor(new Color(51, 153, 255));
			g2d.setStroke(new BasicStroke(5.0f));
			g2d.fillRect(0, 0, window.getWidth(), window.getHeight());
			
			g2d.setColor(Color.black);
			g2d.setFont(new Font("Arial", Font.BOLD, 40));
			g2d.drawString("Snake!", 175, 100);
			
			g2d.setFont(new Font("Arial", Font.BOLD, 35));
			g2d.drawRect(92, 205, 300, 50);
			g2d.drawString("Play", 200, 240);
			
			g2d.drawRect(92, 285, 300, 50);
			g2d.drawString("Options", 176, 320);
			
			g2d.drawRect(92, 365, 300, 50);
			g2d.drawString("Quit", 200, 400);
			
			if(window.getGameSession() != 0) {
				drawCurHighScore(g);
			}
		}
		
		if(window.gameState == GameState.Options) {
			g2d.setColor(new Color(51, 153, 255));
			g2d.fillRect(0, 0, window.getWidth(), window.getHeight());
			
			g2d.setFont(new Font("Arial", Font.BOLD, 40));
			g2d.setColor(Color.BLACK);
			g2d.drawString("Options", 170, 100);

			g2d.setStroke(new BasicStroke(5.0f));
			g2d.setFont(new Font("Arial", Font.BOLD, 35));
			
			// FPS
			g2d.setFont(new Font("Arial", Font.BOLD, 25));
			g2d.drawString("Turn on FPS Counter: ", 10, 190);
			g2d.drawString("Off", 295, 150);
			g2d.drawString("On", 395, 150);
			g2d.drawRect(300, 170, 25, 25);
			g2d.drawRect(400, 170, 25, 25);
			
			if(window.getFPSOption()) {
				g2d.fillRect(400, 170, 25, 25);
			}
			else {
				g2d.fillRect(300, 170, 25, 25);
			}
		
			// Back
			g2d.setFont(new Font("Arial", Font.BOLD, 35));
			g2d.drawRect(143, 400, 200, 50);
			g2d.drawString("Back", 200, 438);	
		}
	}
	
	public void drawFPSCounter(Graphics g) {
		String fps = String.valueOf(window.getFPS());
		
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.setColor(Color.white);
		g.drawString("FPS: " + fps, 405, 45);
	}
	
	private void drawCurHighScore(Graphics g) {
		String scoreStr = String.valueOf(logicMan.getHighScore());
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(scoreStr, 260, 165);
		
		g.drawImage(trophyMenu, 150, 120, window);
	}
	
}
