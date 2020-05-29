package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.State;

public class MouseHandler extends MouseAdapter {
	
	private Game game;
	
	public MouseHandler(Game game) {
		this.game = game;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		switch (Game.gameState) {
		case GAME_OVER:
			// Retry 
			if(mouseOver(mx, my, 220, 40, 115, 50)) {
				game.resetGame();
				Game.gameState = State.PLAYING;
			}
			
			// Quit
			if(mouseOver(mx, my, 350, 40, 115, 50)) {
				game.resetGame();
				Game.gameState = State.MENU;
			}
			
			break;
		case MENU:
			// Play
			if(mouseOver(mx, my, 92, 205, 300, 50)) {
				Game.gameState = State.PLAYING;
			}
			
			// Options 
			if(mouseOver(mx, my, 92, 285, 300, 50)) {
				Game.gameState = State.OPTIONS;
			}
			
			// Quit
			if(mouseOver(mx, my, 92, 365, 300, 50)) {
				System.exit(-1);
			}
			
			break;
		case OPTIONS:
			// Back
			if(mouseOver(mx, my, 143, 400, 200, 50)) {
				Game.gameState = State.MENU;
			}
			
			// FPS Toggle
			if(mouseOver(mx, my, 300, 170, 25, 25)) {
				game.setFPSOption(false);
			}
			else if (mouseOver(mx, my, 400, 170, 25, 25)) {
				game.setFPSOption(true);
			}
			
			break;
		case PLAYING:
			break;
		default:
			break;
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