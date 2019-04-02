package tsar.hsb.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tsar.hsb.font.CustomFont;

public class MenuGUI extends JFrame {

	private final int WIDTH = 400, HEIGHT = 500;

	private JPanel startPanel, menuPanel;

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

		startPanel = new JPanel();
		menuPanel = new JPanel();
	}

	public void start() {
		initializePanels();
		swapToMenuPanel();
		this.setVisible(true);
	}

	private void initializePanels() {
		startPanel.setBounds(0, 0, this.WIDTH, this.HEIGHT);
		startPanel.add(addLogo("Minesweeper"));
		startPanel.setBackground(Color.DARK_GRAY);
		startPanel.setLayout(null);
		startPanel.setVisible(true);
		startPanel.setEnabled(true);
		startPanel.add(addBackButton());

		menuPanel.setBounds(0, 0, this.WIDTH, this.HEIGHT);
		menuPanel.setBackground(Color.DARK_GRAY);
		menuPanel.setLayout(null);
		menuPanel.setVisible(true);
		menuPanel.setEnabled(true);
		menuPanel.add(addExitButton());
		menuPanel.add(addStartButton());
		menuPanel.add(addLogo("Minesweeper"));
	}

	private void swapToStartPanel() {
		this.remove(menuPanel);
		this.add(startPanel);
		this.repaint();
		this.revalidate();
	}

	private void swapToMenuPanel() {
		this.remove(startPanel);
		this.add(menuPanel);
		this.repaint();
		this.revalidate();
	}

	private JLabel addLogo(String logoText) {
		JLabel logoLabel = new JLabel(logoText);
		logoLabel.setBounds(0, 30, this.WIDTH, 80);
		logoLabel.setForeground(Color.CYAN);
		logoLabel.setHorizontalAlignment(JLabel.CENTER);
		logoLabel.setVerticalAlignment(JLabel.CENTER);
		logoLabel.setFont(this.font.getFont(65f));
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
		startButton.setBounds((this.WIDTH / 3), 170, this.WIDTH / 3, 40);
		startButton.setFocusPainted(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swapToStartPanel();
			}
		});
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
		exitButton.setBounds((this.WIDTH / 3), 250, this.WIDTH / 3, 40);
		exitButton.setFocusPainted(false);
		exitButton.setBorderPainted(false);
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		return exitButton;
	}

	private JButton addBackButton() {
		JButton backButton = new JButton("Back");
		backButton.setForeground(Color.CYAN);
		backButton.setBackground(Color.DARK_GRAY);
		backButton.setVerticalAlignment(JButton.CENTER);
		backButton.setHorizontalAlignment(JButton.CENTER);
		backButton.setEnabled(true);
		backButton.setFont(this.font.getFont(40f));
		backButton.setBounds((this.WIDTH / 3), 355, this.WIDTH / 3, 40);
		backButton.setFocusPainted(false);
		backButton.setBorderPainted(false);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swapToMenuPanel();
			}
		});
		return backButton;
	}

}
