package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import objects.Snake;

public class KeyInput extends KeyAdapter {
	
	private Snake snake;

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	public KeyInput(Snake snake) {
		this.snake = snake;
		
		up = false;
		down = false;
		left = false;
		right = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// ignore
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_W && !down) {
			snake.setVelY(-2d);
			snake.setVelX(0d);
			up = true;
			left = false;
			right = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_D && !left) {
			snake.setVelX(2d);
			snake.setVelY(0d);
			right = true;
			left = false;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_A && !right) {
			snake.setVelX(-2d);
			snake.setVelY(0d);
			left = true;
			right = false;
			up = false;
			down = false;
		}
		
		if(key == KeyEvent.VK_S && !up) {
			snake.setVelY(2d);
			snake.setVelX(0d);
			down = true;
			up = false;
			left = false;
			right = false;
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(-1); // remove when finished
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// ignore
	}

}
