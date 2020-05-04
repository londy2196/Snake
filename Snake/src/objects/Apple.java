package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import main.Game;

public class Apple extends GameObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Image appleImg = Toolkit.getDefaultToolkit().createImage("res/appleingame.png");
	
	private Game game;
	
	private static final int WIDTH = 32;
	private static final int HEIGHT = 32;
	
	public Apple(int xPos, int yPos, Game game, ObjectID id) {
		super(xPos, yPos, WIDTH, HEIGHT, id);
		this.game = game;
	}

	@Override
	public void update() {
		velX = 0;
		velY = 0;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(appleImg, this.x, this.y, game);
	}
	
	
	

}
