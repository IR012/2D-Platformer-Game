package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player {

	private GamePanel panel;
	
	private int x;
	private int y;
	private int width;
	private int height;
	
	private int coinScore;
	
	private double xspeed;
	private double yspeed;
	
	private Rectangle hitBox;
	
	private boolean keyLeft;
	private boolean keyRight;
	private boolean keyUp;
	private boolean keyDown;
	
	private boolean isBack;
	
	public Player(int x, int y, int width, int height, GamePanel panel) {
		
		this.panel = panel;
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		xspeed = 0;
		yspeed = 0;
		
		coinScore = 0;
		
		hitBox = new Rectangle(x, y, width, height);	
		isBack = true;
	}
	
	public void set() {
		
		if(keyLeft && keyRight || !keyLeft && !keyRight) xspeed *= 0.8;
		else if(keyLeft && !keyRight) {
			xspeed--;
			isBack = false;
		}
		else if(keyRight && !keyLeft) {
			xspeed++;
			isBack = true;
		}
		
		if(xspeed > 0 && xspeed < 0.75) xspeed = 0;
		if(xspeed < 0 && xspeed > -0.75) xspeed = 0;
		
		if(xspeed > 10) xspeed = 10;
		if(xspeed < -10) xspeed = -10;
		
		if(keyUp) {
			
			hitBox.y++;
			for(Wall wall: panel.walls) {
				if(wall.getHitBox().intersects(hitBox)) yspeed = -15;
			}
			hitBox.y--;
		}
		
		yspeed += 1;
		
		// Horizontal Wall Collision
		hitBox.x += xspeed;
		for(Wall wall: panel.walls) {
			if(hitBox.intersects(wall.getHitBox())) {
				hitBox.x -= xspeed;
				while(!wall.getHitBox().intersects(hitBox)) hitBox.x += Math.signum(xspeed);
				hitBox.x -= Math.signum(xspeed);
				panel.cameraX += x - hitBox.x;
				xspeed = 0;
				hitBox.x = x;
			}
		}
		
		// Vertical Wall Collision
		hitBox.y += yspeed;
		for(Wall wall: panel.walls) {
			if(hitBox.intersects(wall.getHitBox())) {
				hitBox.y -= yspeed;
				while(!wall.getHitBox().intersects(hitBox)) hitBox.y += Math.signum(yspeed);
				hitBox.y -= Math.signum(yspeed);		           
				yspeed = 0;
				y = hitBox.y;
			}
		}
		
		// Coin Collection
		for(int i = 0; i < panel.coins.size() && panel.coins.size() > 0; i++) {
			if(panel.coins.size() > 0 && hitBox.intersects(panel.coins.get(i).getHitBox())) collectCoin(i);
		}
		
		
		panel.cameraX -= xspeed;
		y += yspeed;
		
		hitBox.x = x;
		hitBox.y = y;
		
		// Death code
		if(y > Cons.FRAME_HEIGHT) {
			panel.reset();
			coinScore = 0;
		}
	}

	public void draw(Graphics2D gtd) {
		
		ImageIcon playerImageForward = new ImageIcon("assets/PlayerSprite.png");
		ImageIcon playerImageBack = new ImageIcon("assets/PlayerSpriteFlipped.png");
		ImageIcon scoreCoinImage = new ImageIcon("assets/CoinCounter.png");
		
		Font scoreText = new Font("Arial", Font.BOLD, 40);
		gtd.setFont(scoreText);
		String coinScoreStr = Integer.toString(coinScore);
		gtd.drawString(coinScoreStr, 540 - 22*(coinScoreStr.length()-1), 40);
		
		scoreCoinImage.paintIcon(panel, gtd, 570, 5);
		
		if(isBack) playerImageForward.paintIcon(panel, gtd, x, y);
		else playerImageBack.paintIcon(panel, gtd, x, y);
	}
	
	public void collectCoin(int coin) {
		panel.coins.remove(coin);
		coinScore++;
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

	public double getXspeed() {
		return xspeed;
	}

	public void setXspeed(double xspeed) {
		this.xspeed = xspeed;
	}

	public double getYspeed() {
		return yspeed;
	}

	public void setYspeed(double yspeed) {
		this.yspeed = yspeed;
	}

	public boolean isKeyLeft() {
		return keyLeft;
	}

	public void setKeyLeft(boolean keyLeft) {
		this.keyLeft = keyLeft;
	}

	public boolean isKeyRight() {
		return keyRight;
	}

	public void setKeyRight(boolean keyRight) {
		this.keyRight = keyRight;
	}

	public boolean isKeyUp() {
		return keyUp;
	}

	public void setKeyUp(boolean keyUp) {
		this.keyUp = keyUp;
	}

	public boolean isKeyDown() {
		return keyDown;
	}

	public void setKeyDown(boolean keyDown) {
		this.keyDown = keyDown;
	}

}
