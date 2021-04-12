package game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class PlatformerGame {

	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setSize(Cons.FRAME_WIDTH, Cons.FRAME_HEIGHT);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((int) (screenSize.getWidth()/2 - frame.getSize().getWidth()/2), (int) (screenSize.getHeight()/2 - frame.getSize().getHeight()/2));
		frame.setResizable(false);
		frame.setTitle("2D Platformer Game");
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
		
	}

}
