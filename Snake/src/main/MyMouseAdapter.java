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
	
	public MyMouseAdapter(Snake snake, Apple apple, KeyInput ki, GameLogic logicMan, Window window) {
		this.snake = snake;
		this.apple = apple;
		this.window = window;
		this.ki = ki;
		this.logicMan = logicMan;
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
			}
			
			if(mouseOver(mx, my, 350, 40, 115, 50)) {
				window.gameState = GameState.Menu;
				
				snake.reset();
				apple.reset();
				ki.reset();
				
				logicMan.setScore(0);
			}
		}
		
		if(window.gameState == GameState.Menu) {
			// Play 
			if(mouseOver(mx, my, 92, 175, 300, 50)) {
				window.gameState = GameState.Playing;
			}
			
			// Options 
			if(mouseOver(mx, my, 92, 255, 300, 50)) {
				// TODO
			}
			
			// Quit
			if(mouseOver(mx, my, 92, 335, 300, 50)) {
				System.exit(-1);
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
