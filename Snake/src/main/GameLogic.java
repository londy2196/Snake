package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import objects.Apple;
import objects.Snake;

public class GameLogic {
	
	private int score = 0;
	private int highScore = 0;
	
	private Snake snake;
	private Apple apple;
	
	private Random r;
	
	public GameLogic(Snake snake, Apple apple) {
		this.snake = snake;
		this.apple = apple;
		r = new Random();
	}
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD, 23));
		
		String scoreStr = String.valueOf(score);
		String hScoreStr = String.valueOf(highScore);
		g.drawString(scoreStr, 80, 75);
		g.drawString(hScoreStr, 175, 75);
	}
	
	public void update() {
		collisions(snake, apple);
	}
	
	private void collisions(Snake snake, Apple apple) {
		if(snake.intersects(apple)) {
			int randX = r.nextInt((440 - 35) + 1) + 35;
			int randY = r.nextInt((440 - 135) + 1) + 135;
			apple.setBounds(randX, randY, apple.width, apple.height);
			
			score++;
			highScore++;
			
			for(int i = 0; i < 200; i++) {
				snake.snakeBody.add(new Point(snake.x, snake.y));
			}
		}
		
		// TODO
		if((snake.x > 455 || snake.x < 40) || (snake.y > 455 || snake.y < 135)) {
			snake.setBounds(0, 0, snake.width, snake.height);
		}
	}
	
}
