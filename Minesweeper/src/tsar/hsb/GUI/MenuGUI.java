package tsar.hsb.GUI;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tsar.hsb.font.CustomFont;

public class MenuGUI extends JFrame {

	private final int WIDTH = 400, HEIGHT = 500;

	private JPanel startPanel, optionPanel;

	private CustomFont font;

	public MenuGUI() {
		this.setSize(this.WIDTH, this.HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle("Menu GUI");
		this.setLayout(null);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		font = new CustomFont();
	}

	public void start() {
		this.add(addStartButton());
		this.add(addExitButton());
		this.add(addOptionsButton());
		this.add(addLogo("Minesweeper"));
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}

	private JLabel addLogo(String logoText) {
		JLabel logoLabel = new JLabel(logoText);
		logoLabel.setBounds(0, 10, this.WIDTH, 70);
		logoLabel.setForeground(Color.CYAN);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		logoLabel.setVerticalAlignment(JLabel.CENTER);
		logoLabel.setFont(this.font.getFont(60f));
		return logoLabel;
	}

	private JButton addStartButton() {
		JButton startButton = new JButton("Start");
		startButton.setForeground(Color.CYAN);
		startButton.setBackground(Color.DARK_GRAY);
		startButton.setVerticalAlignment(JButton.CENTER);
		startButton.setHorizontalAlignment(JButton.CENTER);
		startButton.setEnabled(true);
		startButton.setFont(this.font.getFont(40f));
		startButton.setBounds((this.WIDTH / 3), 150, this.WIDTH / 3, 40);
		startButton.setFocusPainted(false);
		startButton.setBorderPainted(false);
		return startButton;
	}

	private JButton addExitButton() {
		JButton exitButton = new JButton("Exit");
		exitButton.setForeground(Color.CYAN);
		exitButton.setBackground(Color.DARK_GRAY);
		exitButton.setVerticalAlignment(JButton.CENTER);
		exitButton.setHorizontalAlignment(JButton.CENTER);
		exitButton.setEnabled(true);
		exitButton.setFont(this.font.getFont(40f));
		exitButton.setBounds((this.WIDTH / 3), 290, this.WIDTH / 3, 40);
		exitButton.setFocusPainted(false);
		exitButton.setBorderPainted(false);
		return exitButton;
	}

	private JButton addOptionsButton() {
		JButton optionsButton = new JButton("Options");
		optionsButton.setForeground(Color.CYAN);
		optionsButton.setBackground(Color.DARK_GRAY);
		optionsButton.setVerticalAlignment(JButton.CENTER);
		optionsButton.setHorizontalAlignment(JButton.CENTER);
		optionsButton.setEnabled(true);
		optionsButton.setFont(this.font.getFont(40f));
		optionsButton.setBounds((this.WIDTH / 4), 220, this.WIDTH / 2, 40);
		optionsButton.setFocusPainted(false);
		optionsButton.setBorderPainted(false);
		return optionsButton;
	}

}
