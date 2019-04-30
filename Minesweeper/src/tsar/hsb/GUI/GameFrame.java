package tsar.hsb.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import tsar.hsb.BoardInitializer;
import tsar.hsb.Quadrate;
import tsar.hsb.GUI.PopupDialog.GameState;
import tsar.hsb.font.CustomFont;

public class GameFrame extends JFrame {

	private int quadrateSize = 50, numMines;

	private int width, height;
	private float fontSize = 20f;

	private Quadrate[][] buttonBoard;

	private JPanel menuPanel, gamePanel, scorePanel;

	private JLabel scoreLabel, mineLabel;

	private BoardInitializer boardInitializer;

	private Timer time;
	private boolean isTimeRunning;

	private String difficulty;

	public GameFrame(String difficulty) {
		this.difficulty = difficulty;
		initBoard(difficulty);
		int[] temp = boardInitializer.getGameInitData();
		initGame(temp[0], temp[1], temp[2]);
	}

	private void initBoard(String difficulty) {
		boardInitializer = new BoardInitializer(difficulty);
	}

	private void initGame(int width, int height, int numMines) {
		this.numMines = numMines;
		if (numMines == 99) {
			quadrateSize = 45;
			fontSize = 15f;
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
		Font customFont = new CustomFont().getFont(fontSize);
		for (int y = 0; y < buttonBoard[0].length; y++) {
			for (int x = 0; x < buttonBoard.length; x++) {
				buttonBoard[x][y].setPreferredSize(new Dimension(quadrateSize, quadrateSize));
				buttonBoard[x][y].setEnabled(true);
				buttonBoard[x][y].setFont(customFont);
				buttonBoard[x][y].setBackground(Color.DARK_GRAY);
				buttonBoard[x][y].addMouseListener(mouseListener);
				buttonBoard[x][y].setFocusPainted(true);
				buttonBoard[x][y].setVisible(true);
				gamePanel.add(buttonBoard[x][y]);
			}
		}
	}

	public void closeGameFrame() {
		this.dispose();
	}

	MouseListener mouseListener = new MouseListener() {

		@Override
		public void mouseReleased(MouseEvent e) {
		}
		@Override
		public void mousePressed(MouseEvent e) {
		}
		@Override
		public void mouseExited(MouseEvent e) {
		}
		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			Quadrate temp = (Quadrate) e.getSource();

			if (e.getButton() == MouseEvent.BUTTON1) {
				if (!isTimeRunning) {
					isTimeRunning = true;
				}
				clickedSettingsUpdater(temp);
			}

			if (e.getButton() == MouseEvent.BUTTON3) {
				if (temp.getText().equals("F")) {
					temp.setText("");
					mineLabel.setText("" + (Integer.parseInt(mineLabel.getText()) + 1));
				} else if (!mineLabel.getText().equals("0")) {
					temp.setText("F");
					temp.setForeground(Color.RED);
					mineLabel.setText("" + (Integer.parseInt(mineLabel.getText()) - 1));
				}
			}
			checkWin();
		}
	};

	private void clickedSettingsUpdater(Quadrate temp) {
		if (temp.isEnabled()) {
			if (temp.isMine()) {
				playerLoss();
			}
			temp.showNumber();
			temp.setBackground(colorUpdater(temp.getNumberValue()));
			temp.setEnabled(false);
			if (temp.getNumberValue() == 0) {
				floodFillZeros(temp.getXArrayPos(), temp.getYArrayPos());
			}
		}
	}

	private void floodFillZeros(int x, int y) {
		for (int a = -1; a < 2; a++) {
			for (int b = -1; b < 2; b++) {
				if (a == 0 && b == 0) {
					// Do Nothing
				} else {
					if (((a + x) >= 0 && (a + x) < buttonBoard.length)
							&& ((b + y) >= 0 && (b + y) < buttonBoard[0].length)) {
						if (buttonBoard[a + x][b + y].getNumberValue() == 0) {
							clickedSettingsUpdater(buttonBoard[a + x][b + y]);
						}
					}
				}
			}
		}
	}

	private void checkWin() {
		boolean win = false;
		int enabledButtons = 0;
		int detectedMines = 0;
		for (Quadrate[] qArr : buttonBoard) {
			for (Quadrate q : qArr) {
				if (q.isEnabled()) {
					enabledButtons++;
				}
				if (q.isMine() && q.getText().equals("F")) {
					detectedMines++;
				}
			}
		}
		if (detectedMines == this.numMines || enabledButtons == this.numMines) {
			win = true;
		}

		if (win) {
			new PopupDialog(GameState.WIN, this);
			
		}
	}

	private void playerLoss() {
		for (Quadrate[] qArr : buttonBoard) {
			for (Quadrate q : qArr) {
				if (q.isMine()) {
					q.showNumber();
				}
				q.setEnabled(false);
			}
		}
		new PopupDialog(GameState.LOSS, this);
	}

	private Color colorUpdater(int num) {
		Color[] colorArray = { Color.DARK_GRAY, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.RED };
		num = num > 4 ? 4 : num < 0 ? 0 : num;
		return colorArray[num];
	}


	public String getDifficulty() {
		return this.difficulty;
	}

	class UpdateScore extends TimerTask {

		@Override
		public void run() {
			if (isTimeRunning) {
				scoreLabel.setText("" + (Integer.parseInt(scoreLabel.getText()) + 1));
			}
		}
	}
}
