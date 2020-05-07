package gamestates;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import main.Game;

public class Menu {
	
	private Game game;
	
	public Menu(Game game) {
		this.game = game;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;	
		
		g2d.setColor(new Color(51, 153, 255));
		g2d.setStroke(new BasicStroke(5.0f));
		g2d.fillRect(0, 0, game.getWidth(), game.getHeight());
		
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
	
	public void update() {
		
	}
}
