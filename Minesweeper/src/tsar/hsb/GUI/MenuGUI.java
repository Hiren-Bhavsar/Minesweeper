package tsar.hsb.GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
		this.setTitle("Minesweeper By Tsar");
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
		startPanel.add(addBackButton());
		for (JButton b : addGameButtons()) {
			startPanel.add(b);
		}

		menuPanel.setBounds(0, 0, this.WIDTH, this.HEIGHT);
		menuPanel.setBackground(Color.DARK_GRAY);
		menuPanel.setLayout(null);
		menuPanel.setVisible(true);
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
		buttonInitializer(startButton, 40f);
		startButton.setBounds((this.WIDTH / 3), 170, this.WIDTH / 3, 40);
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
		buttonInitializer(exitButton, 40f);
		exitButton.setBounds((this.WIDTH / 3), 250, this.WIDTH / 3, 40);
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
		buttonInitializer(backButton, 32f);
		backButton.setBounds((this.WIDTH / 3), 355, this.WIDTH / 3, 40);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				swapToMenuPanel();
			}
		});
		return backButton;
	}

	private JButton[] addGameButtons() {
		JButton gameButtons[] = new JButton[3];

		for (int x = 0; x < 3; x++) {
			gameButtons[x] = new JButton();
			buttonInitializer(gameButtons[x], 32f);
			gameButtons[x].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					JButton temp = (JButton) e.getSource();

					switch (temp.getText()) {

					case "Easy":
						// TODO
						break;

					case "Medium":
						// TODO
						break;

					case "Hard":
						// TODO
						break;

					default:
						break;
					}
				}
			});
		}

		gameButtons[0].setText("Easy");
		gameButtons[1].setText("Medium");
		gameButtons[2].setText("Hard");

		gameButtons[0].setBounds((this.WIDTH / 3), 160, this.WIDTH / 3, 40);
		gameButtons[0].setForeground(Color.GREEN);
		gameButtons[1].setBounds((this.WIDTH / 3), 220, this.WIDTH / 3, 40);
		gameButtons[1].setForeground(Color.ORANGE);
		gameButtons[2].setBounds((this.WIDTH / 3), 280, this.WIDTH / 3, 40);
		gameButtons[2].setForeground(Color.RED);

		return gameButtons;
	}

	MouseListener buttonHover = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
			JButton temp = (JButton) e.getSource();
			temp.setBackground(Color.DARK_GRAY);
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton temp = (JButton) e.getSource();
			temp.setBackground(Color.DARK_GRAY);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton temp = (JButton) e.getSource();
			temp.setBackground(Color.DARK_GRAY.darker());
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	};

	private void buttonInitializer(JButton b, float fSize) {
		b.addMouseListener(buttonHover);
		b.setForeground(Color.CYAN);
		b.setBackground(Color.DARK_GRAY);
		b.setVerticalAlignment(JButton.CENTER);
		b.setHorizontalAlignment(JButton.CENTER);
		b.setEnabled(true);
		b.setFont(this.font.getFont(fSize));
		b.setFocusPainted(false);
		b.setBorderPainted(false);
	}
}
