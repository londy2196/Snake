package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import objects.Apple;
import objects.Snake;

public class RetryState extends MouseAdapter {
	
	private Game game;
	private Snake snake;
	private Apple apple;
	private KeyInput ki;
	private GameLogic logicMan;
	
	public RetryState(Snake snake, Apple apple, Game game, KeyInput ki, GameLogic logicMan) {
		this.snake = snake;
		this.apple = apple;
		this.game = game;
		this.ki = ki;
		this.logicMan = logicMan;
	}
	
	public void render(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 30));
		g.setColor(Color.red);
		g.drawString("Game Over!", 160, 127);
		
		g.setColor(Color.BLUE);
		g.drawString("Retry", 238, 75);
		g.drawString("Quit", 377, 75);
		
		g.setColor(Color.black);
		g.drawRect(220, 40, 115, 50);
		g.drawRect(350, 40, 115, 50);
	}
	
	// might use later
	public void update() {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(mouseOver(mx, my, 220, 40, 115, 50)) {
			game.gameState = GameState.Playing;
			
			snake.reset();
			apple.reset();
			ki.reset();
			
			logicMan.setScore(0);
		}
		
		if(mouseOver(mx, my, 350, 40, 115, 50)) {
			System.exit(-1);
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}

}
