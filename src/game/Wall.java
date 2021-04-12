package game;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Wall {
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int startX;
	
	private Rectangle hitBox;
	private GamePanel panel;
	
	public Wall(int x, int y, int width, int height, GamePanel panel) {
		
		this.panel = panel;
		this.x = x;
		startX = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		hitBox = new Rectangle(x, y, width, height);
	}
	
	public void draw(Graphics2D gtd) {
		
		ImageIcon wallImage = new ImageIcon("assets/GroundSprite.jpg");
		wallImage.paintIcon(panel, gtd, x, y);
	}
	
	public int set(int cameraX) {
		x = startX + cameraX;
		hitBox.x = x;
		
		return x;
	}

	// Getters & Setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getHitBox() {
		return hitBox;
	}

	public void setHitBox(Rectangle hitBox) {
		this.hitBox = hitBox;
	}
}


