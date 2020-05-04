package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.LinkedList;

public class Snake extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LinkedList<Point> snakeBody = new LinkedList<Point>();

	public Snake(int xPos, int yPos, ObjectID id) {
		super(xPos, yPos, 10, 10, id);
		
		for(int i = 0; i < 500; i++) {
			snakeBody.add(new Point(xPos, yPos));
		}
	}

	@Override
	public void update() {
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(10.0f));
		
		for(Point p : snakeBody) {
			g2d.drawLine(p.x, p.y+5, p.x, p.y+5);
		}
		
		snakeBody.removeLast();
		snakeBody.push(new Point(x, y));

	}

}
