package tsar.hsb.GUI;

import javax.swing.JFrame;

public class MenuGUI extends JFrame {

	private final int WIDTH = 500, HEIGHT = 800;

	public MenuGUI() {
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Menu GUI");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void start() {
		this.setVisible(true);
	}

	private void addStartButton() {
		// TODO
	}

	private void addExitButton() {
		// TODO
	}

	private void addOptionsButton() {
		// TODO
	}

}
