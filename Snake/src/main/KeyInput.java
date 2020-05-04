package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Snake;

public class KeyInput extends KeyAdapter {
	
	private Snake snake;
	
	public KeyInput(Snake snake) {
		this.snake = snake;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ignore
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W) {
			snake.setVelY(-1d);
			snake.setVelX(0d);
		}
		
		if(key == KeyEvent.VK_D) {
			snake.setVelX(1d);
			snake.setVelY(0d);
		}
		
		if(key == KeyEvent.VK_A) {
			snake.setVelX(-1d);
			snake.setVelY(0d);
		}
		
		if(key == KeyEvent.VK_S) {
			snake.setVelY(1d);
			snake.setVelX(0d);
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(-1); // remove when finished
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ignore
	}

}
