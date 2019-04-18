package tsar.hsb.GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private int width, height;

	private final int QUADRATE_SIZE = 50;

	private JButton[][] buttonBoard;

	public GameFrame(int width, int height) {
		this.width = width * QUADRATE_SIZE;
		this.height = height * QUADRATE_SIZE;

		buttonBoard = new JButton[width][height + 1];

		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Minesweeper");
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void initializeButtonBoard() {
		for (int y = 0; y < buttonBoard[0].length; y++) {
			for (int x = 0; x < buttonBoard.length; x++) {

			}
		}
	}

}
