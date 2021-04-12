package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {

		GamePanel panel = new GamePanel();
		
		panel.setLocation(0,0);
		panel.setSize(this.getSize());
		panel.setVisible(true);
		this.add(panel);
		
		addKeyListener(new KeyChecker(panel));
	}

}
