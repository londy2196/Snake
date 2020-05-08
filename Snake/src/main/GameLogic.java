package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import gameobjects.Apple;
import gameobjects.Snake;

public class GameLogic {
	
	private int score = 0;
	private int highScore = 0;
	
	private Snake snake;
	private Apple apple;
	private Window window;
	
	private Random r = new Random();
	
	public GameLogic(Snake snake, Apple apple, Window window) {
		this.snake = snake;
		this.apple = apple;
		this.window = window;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 23));
		
		String scoreStr = String.valueOf(score);
		String hScoreStr = String.valueOf(highScore);
		g.drawString(scoreStr, 80, 75);
		g.drawString(hScoreStr, 175, 75);
		
		checkWallCollisions(snake, apple);
	}
	
	public void update() {
		checkAppleCollisions(snake, apple);
	}
	
	private void checkAppleCollisions(Snake snake, Apple apple) {
		if(snake.intersects(apple)) {
			int randX = r.nextInt((440 - 35) + 1) + 35;
			int randY = r.nextInt((420 - 135) + 1) + 135;
			apple.setBounds(randX, randY, apple.width, apple.height);
			
			if(score != highScore) {
				score++;
			}
			else {
				score++;
				highScore++;
			}
			
			// make snake longer
			for(int i = 0; i < 200; i++) {
				snake.snakeBody.add(new Point(snake.x, snake.y));
			}
		}
	}
	
	private void checkWallCollisions(Snake snake, Apple apple) {
		if((snake.x > 455 || snake.x < 35) || (snake.y > 455 || snake.y < 135)) {
			snake.setVelX(0);
			snake.setVelY(0);
			snake.snakeBody.clear();
			window.gameState = GameState.GameOver;
		}
	}
	
	// GETTERS & SETTERS
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
}
