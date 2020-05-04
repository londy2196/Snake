package main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected double velX, velY;
	protected ObjectID id;
	
	public GameObject(int xPos, int yPos, int width, int height, ObjectID id) {
		super(xPos, yPos, width, height);
		this.id = id;
	}
	
	public abstract void update();
	public abstract void render(Graphics g);
	
	// GETTERS & SETTERS
	public double getX() {
		return x;
	}
	
	public void setX(double x) {
		this.x = (int) x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public void setY(double y) {
		this.y = (int) y;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public ObjectID getID() {
		return id;
	}

}
