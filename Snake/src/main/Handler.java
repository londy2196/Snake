package main;

import java.awt.Graphics;
import java.util.LinkedList;

import objects.GameObject;

public class Handler {
	
	LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
	
	public void update() {
		for(GameObject object : gameObjects) {
			object.update();
		}
	}
	
	public void render(Graphics g) {
		for(GameObject object : gameObjects) {
			object.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.gameObjects.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.gameObjects.remove(object);
	}

}
