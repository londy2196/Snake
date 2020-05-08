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
	
	private GameLogic logicMan;
	private Handler handler;
	private Window window;
	
	public Game(GameLogic logicMan, Handler handler, Window window) {
		this.logicMan = logicMan;
		this.handler = handler;
		this.window = window;
	}
	
	public void render(Graphics g) {
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
			Graphics2D g2d = (Graphics2D) g;	
			
			g2d.setColor(new Color(51, 153, 255));
			g2d.setStroke(new BasicStroke(5.0f));
			g2d.fillRect(0, 0, window.getWidth(), window.getHeight());
			
			g2d.setColor(Color.black);
			g2d.setFont(new Font("Arial", Font.BOLD, 40));
			g2d.drawString("Snake!", 175, 100);
			
			g2d.setFont(new Font("Arial", Font.BOLD, 35));
			g2d.drawRect(92, 175, 300, 50);
			g2d.drawString("Play", 200, 210);
			
			g2d.drawRect(92, 255, 300, 50);
			g2d.drawString("Options", 176, 290);
			
			g2d.drawRect(92, 335, 300, 50);
			g2d.drawString("Quit", 200, 370);
		}
	}
	
	public void update() {
		
	}

}
