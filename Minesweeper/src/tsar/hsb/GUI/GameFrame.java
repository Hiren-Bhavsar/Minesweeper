package tsar.hsb.GUI;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private int width, height;

	private final int QUADRATE_SIZE = 40;

	public GameFrame(int width, int height) {
		this.width = width * QUADRATE_SIZE;
		this.height = height * QUADRATE_SIZE;
	}

}
