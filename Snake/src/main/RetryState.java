package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import objects.Apple;
import objects.Snake;

public class RetryState implements MouseListener {
	
	private Game game;
	private Snake snake;
	private Apple apple;
	private KeyInput ki;
	private GameLogic logicMan;
	
	public RetryState(Snake snake, Apple apple, Game game, KeyInput ki, GameLogic logicMan) {
		this.game = game;
		this.snake = snake;
		this.apple = apple;
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
	
	public void update() {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
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

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		Graphics g = game.getGraphics();
//		g.setColor(Color.LIGHT_GRAY);
//		
//		int mx = e.getX();
//		int my = e.getY();
//		if(mouseOver(mx, my, 220, 40, 115, 50)) {
//			System.out.println("mx: " + mx + " " + "my: " + my); // testing 
//			g.drawRect(240, 40, 115, 50);
//		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
