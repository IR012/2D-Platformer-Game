package game;

import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class GameInfo {

	public GameInfo(ImageIcon image, Graphics2D gtd, GamePanel panel) {
		
		image = new ImageIcon("assets/MenuBG.png");
		image.paintIcon(panel, gtd, 0, 0);
		
		Font text = new Font("Arial", Font.BOLD, 40);
		gtd.setFont(text);
		gtd.setColor(java.awt.Color.BLACK);
		gtd.drawString("W: UP", 10, 40);
		gtd.drawString("S: DOWN", 10, 40+40);
		gtd.drawString("A: LEFT", 10, 40+40*2);
		gtd.drawString("D: RIGHT", 10, 40+40*3);
		gtd.drawString("N: CONTROLS", 10, 40+40*4);
		gtd.drawString("ESC: QUIT/MENU", 10, 40+40*5);
		gtd.drawString("ENTER: START", 10, 40+40*6);
		
	}
		
}
