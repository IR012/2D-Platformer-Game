package game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class GameMenu {
	
	public GameMenu(ImageIcon image, Graphics2D gtd, GamePanel panel) {
	
		
		image = new ImageIcon("assets/MenuBG.png");
		image.paintIcon(panel, gtd, 0, 0);
		
		Font text = new Font("Arial", Font.BOLD, 40);
		gtd.setFont(text);
		gtd.setColor(java.awt.Color.RED);
		
		gtd.drawString("PLAY", 10, 300-80);
		gtd.drawString("CONTROLS", 10, 300-40);
		gtd.drawString("QUIT", 10, 300);
		
		Font textSmall = new Font("Arial", Font.BOLD, 15);
		gtd.setFont(textSmall);
		gtd.setColor(java.awt.Color.RED);
		
		gtd.drawString("(enter)", 110, 300-80);
		gtd.drawString("(n)", 235, 300-40);
		gtd.drawString("(esc)", 100, 300);
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
	
		
	}
	
}
