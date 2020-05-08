package gameobjects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import main.Window;

public class Apple extends GameObject {

	private static final long serialVersionUID = 1L;

	private static final Image appleImg = Toolkit.getDefaultToolkit().createImage("res/appleingame.png");
	
	private Window window;
	
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	public Apple(int xPos, int yPos, ObjectID id, Window window) {
		super(xPos, yPos, WIDTH, HEIGHT, id);
		this.window = window;
	}

	@Override
	public void update() {
		velX = 0;
		velY = 0;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(appleImg, x, y, window);
	}

	@Override
	public void reset() {
		this.setBounds(350, 250, width, height);
	}
	
	
	

}
