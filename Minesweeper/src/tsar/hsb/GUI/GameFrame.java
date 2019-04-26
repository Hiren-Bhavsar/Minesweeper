package tsar.hsb.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tsar.hsb.BoardInitializer;
import tsar.hsb.Quadrate;
import tsar.hsb.font.CustomFont;

public class GameFrame extends JFrame {

	private int quadrateSize = 50;

	private int width, height;

	private Quadrate[][] buttonBoard;

	private JPanel menuPanel, gamePanel, scorePanel;

	private JLabel scoreLabel, mineLabel;

	private BoardInitializer boardInitializer;

	private Timer time;
	private boolean isTimeRunning;

	public GameFrame(String difficulty) {
		initBoard(difficulty);
		int[] temp = boardInitializer.getGameInitData();
		initGame(temp[0], temp[1], temp[2]);
	}

	private void initBoard(String difficulty) {
		boardInitializer = new BoardInitializer(difficulty);
	}

	private void initGame(int width, int height, int numMines) {
		if (numMines == 99) {
			quadrateSize = 30;
		}
		this.width = width * quadrateSize;
		this.height = width * quadrateSize;

		buttonBoard = boardInitializer.getGameBoard();

		this.setSize(this.width, this.height);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Minesweeper");
		this.getContentPane().setBackground(Color.BLUE);
		this.setLocationRelativeTo(null);

		initializeMenuBar();
		initializeScorePanel(numMines);
		initializeGamePanel(width, height);
		initializeButtonBoard();

		this.add(menuPanel, BorderLayout.NORTH);
		this.add(scorePanel, BorderLayout.CENTER);
		this.add(gamePanel, BorderLayout.SOUTH);

		this.repaint();
		this.revalidate();
		this.setVisible(true);

		time = new Timer();
		time.scheduleAtFixedRate(new UpdateScore(), 0, 1000);

	}

	private void initializeScorePanel(int numMines) {
		scorePanel = new JPanel();
		scorePanel.setPreferredSize(new Dimension(this.width, 50));
		scorePanel.setBackground(Color.BLACK);
		scorePanel.setLayout(new BorderLayout());
		scorePanel.setVisible(true);

		scoreLabel = new JLabel("" + 0);
		mineLabel = new JLabel("" + numMines);
//		mineLabel.setHorizontalTextPosition(JLabel.RIGHT);
//		scoreLabel.setHorizontalTextPosition(JLabel.LEFT);

		initializeLabels(mineLabel);
		initializeLabels(scoreLabel);

		scorePanel.add(scoreLabel, BorderLayout.EAST);
		scorePanel.add(mineLabel, BorderLayout.WEST);

	}

	private void initializeLabels(JLabel temp) {
		CustomFont font = new CustomFont();
		temp.setBackground(Color.BLACK);
		temp.setForeground(Color.CYAN);
		temp.setFont(font.getFont(20));
		temp.setPreferredSize(new Dimension(this.width / 2, 48));
		temp.setHorizontalAlignment(JLabel.CENTER);
		temp.setVerticalAlignment(JLabel.CENTER);
		temp.setHorizontalTextPosition(JLabel.CENTER);
		temp.setVerticalTextPosition(JLabel.CENTER);
	}

	private void initializeMenuBar() {
		menuPanel = new JPanel();
		menuPanel.setVisible(true);
		menuPanel.setPreferredSize(new Dimension(this.width, 20));
		menuPanel.setBackground(Color.GREEN);
		menuPanel.setLayout(new GridLayout(1, 1));

		JMenuBar menuBar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem menu = new JMenuItem("Menu");

		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isTimeRunning = false;
				System.exit(0);
			}
		});

		menu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isTimeRunning = false;
				MenuGUI menu = new MenuGUI();
				menu.start();
				closeGameFrame();

			}
		});

		file.add(menu);
		file.add(exit);
		menuBar.add(file);
		menuBar.setVisible(true);

		menuPanel.add(menuBar);
	}

	private void initializeGamePanel(int width, int height) {
		gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(this.width, this.height - 70));
		gamePanel.setBackground(Color.DARK_GRAY);
		gamePanel.setLayout(new GridLayout(width, height));
		gamePanel.setVisible(true);
	}

	private void initializeButtonBoard() {
		for (int y = 0; y < buttonBoard[0].length; y++) {
			for (int x = 0; x < buttonBoard.length; x++) {
				buttonBoard[x][y].setPreferredSize(new Dimension(quadrateSize, quadrateSize));
				buttonBoard[x][y].setEnabled(true);
				buttonBoard[x][y].setBackground(Color.DARK_GRAY);
				buttonBoard[x][y].addActionListener(buttonListener);
				buttonBoard[x][y].setLayout(null);
				buttonBoard[x][y].setFocusPainted(false);
				buttonBoard[x][y].setVisible(true);
				gamePanel.add(buttonBoard[x][y]);
			}
		}
	}

	private void closeGameFrame() {
		this.dispose();
	}

	ActionListener buttonListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!isTimeRunning) {
				isTimeRunning = true;
			}
			JButton temp = (JButton) e.getSource();
		}
	};

	class UpdateScore extends TimerTask {

		@Override
		public void run() {
			if (isTimeRunning) {
				scoreLabel.setText("" + (Integer.parseInt(scoreLabel.getText()) + 1));
			}
		}
	}
}
