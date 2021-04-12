package game;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {

	enum STATE{
		MENU,
		INFO,
		GAME
	}; 
	STATE State;
	GameMenu gameMenu;
	GameInfo gameInfo;
	
	Player player;
	Timer gameTimer;
	
	ArrayList<Wall> walls = new ArrayList<Wall>();
	ArrayList<Coin> coins = new ArrayList<Coin>();
	
	int cameraX;
	int offsetWalls;
	int offsetCoins;
	
	ImageIcon backgroundImage;
	ImageIcon backgroundImageSecond;
	
	
	public GamePanel() {
		
		State = STATE.MENU;
		
		player = new Player(0, 0, Cons.PLAYER_WIDTH, Cons.PLAYER_HEIGHT, this);
		
		reset();
		
		gameTimer = new Timer();
		gameTimer.schedule(new TimerTask() {
			
			public void run() {
				
				if(State == STATE.GAME) { 
				
					if(walls.get(walls.size() - 1).getX() < Cons.FRAME_WIDTH - Cons.WALL_SIZE) {
						offsetWalls += Cons.FRAME_WIDTH;
						createPlatform(offsetWalls);
					}
					
					if(coins.size() == 0) {
						offsetCoins += Cons.FRAME_WIDTH;
						System.out.println("Walls: " + walls.size() + "    Coins: " + coins.size());
						generateCoins(offsetCoins);
					}
					else if(coins.get(coins.size() - 1).getX() < Cons.FRAME_WIDTH - Cons.WALL_SIZE) {
						offsetCoins += Cons.FRAME_WIDTH;
						generateCoins(offsetCoins);
					}
					
					player.set();
					for(Wall wall: walls) wall.set(cameraX);
					for(Coin coin: coins) coin.set(cameraX);
					
					for(int i=0; i<walls.size(); i++) {
						if(walls.get(i).getX() < -Cons.FRAME_WIDTH) walls.remove(i);
					}
						
					for(int i=0; i<coins.size() && coins.size() > 0; i++) {
						if(coins.get(i).getX() < -Cons.FRAME_WIDTH) coins.remove(i);
					}
				}
			
				repaint();
			
			}
		}, 0, 17);
	}
	
	public void reset() {
		player.setX(Cons.WALL_SIZE * 3);
		player.setY(0);
		cameraX = Cons.WALL_SIZE * 3;
		player.setXspeed(0);
		player.setYspeed(0);
		walls.clear();
		coins.clear();
		offsetCoins = offsetWalls  = -Cons.WALL_SIZE * 3;
		createPlatform(offsetWalls);
		generateCoins(offsetCoins);
	}
	
	public void createPlatform(int offset) {
		
		int[] platLengths = new int[] {
				(int)(Cons.FRAME_WIDTH/Cons.WALL_SIZE+Cons.WALL_SIZE),
				(int)(Cons.FRAME_WIDTH/Cons.WALL_SIZE)-2,
				(int)(Cons.FRAME_WIDTH/Cons.WALL_SIZE)-3,
				(int)(Cons.FRAME_WIDTH/Cons.WALL_SIZE)-4,
				(int)(Cons.FRAME_WIDTH/Cons.WALL_SIZE)-5
		};
		
		Random rand = new Random();
		for(int i=0; i < platLengths[rand.nextInt(platLengths.length)]; i++) {
			walls.add(new Wall((offset + i*Cons.WALL_SIZE),(int)(Cons.FRAME_HEIGHT*0.76),Cons.WALL_SIZE,Cons.WALL_SIZE,this));		
		}	
	}
	
	public void generateCoins(int offset) {
		
		int[] coinSteps = new int[] {
				Cons.WALL_SIZE*4,
				Cons.WALL_SIZE*3,
				Cons.WALL_SIZE*2,
		};
		
		Random rand = new Random();
		for(int i=0; i < rand.nextInt(Cons.FRAME_WIDTH/Cons.WALL_SIZE); i++) {
			coins.add(new Coin((offset+Cons.WALL_SIZE*4 + i*Cons.WALL_SIZE*(rand.nextInt(1)+1)),coinSteps[rand.nextInt(coinSteps.length)],Cons.WALL_SIZE,Cons.WALL_SIZE,this));
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D gtd = (Graphics2D) g;
		
		if(State == STATE.MENU) {
			gameMenu = new GameMenu(backgroundImage, gtd, this);
		}
		else if (State == STATE.INFO) {
			gameInfo = new GameInfo(backgroundImage, gtd, this);
		}
		else if (State == STATE.GAME) {
			backgroundImage = new ImageIcon("assets/CloudBG.jpg");
			backgroundImageSecond = new ImageIcon("assets/CloudBG.jpg");
	
			// Scrolling Background
			backgroundImageSecond.paintIcon(this, gtd, cameraX - Cons.WALL_SIZE*3 - Cons.FRAME_WIDTH, 0);
			backgroundImage.paintIcon(this, gtd, cameraX - Cons.WALL_SIZE*3 + Cons.FRAME_WIDTH*(int)Math.abs((cameraX-Cons.WALL_SIZE*3)/Cons.FRAME_WIDTH), 0);
			backgroundImageSecond.paintIcon(this, gtd, cameraX - Cons.WALL_SIZE*3 + Cons.FRAME_WIDTH*(1+(int)Math.abs((cameraX-Cons.WALL_SIZE*3)/Cons.FRAME_WIDTH)), 0);

			player.draw(gtd);
			for(Wall wall: walls) wall.draw(gtd);
			for(Coin coin: coins) coin.draw(gtd);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

	public void keyPressed(KeyEvent e) {
		
		if(State == STATE.MENU) {
			if(e.getKeyChar() == KeyEvent.VK_ESCAPE) System.exit(0);
			if(e.getKeyChar() == 'n') State = STATE.INFO;
			if(e.getKeyChar() == KeyEvent.VK_ENTER) State = STATE.GAME;
		}
		
		if(State == STATE.INFO) {
			if(e.getKeyChar() == KeyEvent.VK_ESCAPE) State = STATE.MENU;
			if(e.getKeyChar() == KeyEvent.VK_ENTER) State = STATE.GAME;
		}
		
		if (State == STATE.GAME) {
			if(e.getKeyChar() == 'a') player.setKeyLeft(true);
			if(e.getKeyChar() == 'w') player.setKeyUp(true);
			if(e.getKeyChar() == 'd') player.setKeyRight(true);
			if(e.getKeyChar() == 's') player.setKeyDown(true);
			if(e.getKeyChar() == KeyEvent.VK_ESCAPE) State = STATE.MENU;
		}
	}

	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyChar() == 'a') player.setKeyLeft(false);
		if(e.getKeyChar() == 'w') player.setKeyUp(false);
		if(e.getKeyChar() == 'd') player.setKeyRight(false);
		if(e.getKeyChar() == 's') player.setKeyDown(false);
	}
	
}
