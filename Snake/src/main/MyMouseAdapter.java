package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import gameobjects.Apple;
import gameobjects.Snake;

public class MyMouseAdapter extends MouseAdapter {
	
	private Snake snake;
	private Apple apple;
	private Window window;
	private KeyInput ki;
	private GameLogic logicMan;
	
	public MyMouseAdapter(Snake snake, Apple apple, GameLogic logicMan, KeyInput ki, Window window) {
		this.snake = snake;
		this.apple = apple;
		this.logicMan = logicMan;
		this.ki = ki;
		this.window = window;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(window.gameState == GameState.GameOver) {
			if(mouseOver(mx, my, 220, 40, 115, 50)) {
				window.gameState = GameState.Playing;
				
				snake.reset();
				apple.reset();
				ki.reset();
				
				logicMan.setScore(0);
				window.setGameSession(window.getGameSession() + 1);
			}
			
			if(mouseOver(mx, my, 350, 40, 115, 50)) {
				window.gameState = GameState.Menu;
				
				snake.reset();
				apple.reset();
				ki.reset();
				
				logicMan.setScore(0);
				window.setGameSession(window.getGameSession() + 1);
			}
		}
		
		if(window.gameState == GameState.Menu) {
			// Play 
			if(mouseOver(mx, my, 92, 205, 300, 50)) {
				window.gameState = GameState.Playing;
			}
			
			// Options 
			if(mouseOver(mx, my, 92, 285, 300, 50)) {
				window.gameState = GameState.Options;
			}
			
			// Quit
			if(mouseOver(mx, my, 92, 365, 300, 50)) {
				System.exit(-1);
			}
		}
		
		if(window.gameState == GameState.Options) {
			// Back
			if(mouseOver(mx, my, 143, 400, 200, 50)) {
				window.gameState = GameState.Menu;
			}
			
			// FPS
			if(mouseOver(mx, my, 300, 170, 25, 25)) {
				window.setFPSOption(false);
			}
			else if (mouseOver(mx, my, 400, 170, 25, 25)) {
				window.setFPSOption(true);
			}
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
