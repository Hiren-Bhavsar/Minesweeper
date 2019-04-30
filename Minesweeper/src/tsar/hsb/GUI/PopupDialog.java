package tsar.hsb.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tsar.hsb.font.CustomFont;

public class PopupDialog extends JFrame {

	public enum GameState {
		WIN, LOSS
	}

	private JLabel dialogLabel;
	private JButton restartButton, menuButton, quitButton;
	private GameFrame gf;

	public PopupDialog(GameState g, GameFrame gf) {
		this.gf = gf;
		String text = g == GameState.WIN ? "Congratulations! You've Won!" : "Oh No! You Lose!";
		this.setSize(350, 200);
		this.setTitle("Minesweeper");
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.DARK_GRAY);
		this.setLocationRelativeTo(null);

		initializeJLabel(text);

		menuButton = new JButton("Menu");
		quitButton = new JButton("Quit");
		restartButton = new JButton("Replay");
		initializeButton(menuButton);
		initializeButton(quitButton);
		initializeButton(restartButton);
		addActionListeners();

		this.add(dialogLabel, BorderLayout.PAGE_START);
		this.add(restartButton, BorderLayout.CENTER);
		this.add(menuButton, BorderLayout.LINE_START);
		this.add(quitButton, BorderLayout.LINE_END);
		this.repaint();
		this.revalidate();
		this.setVisible(true);
	}

	private void initializeJLabel(String text) {
		dialogLabel = new JLabel(text);
		CustomFont font = new CustomFont();
		dialogLabel.setBackground(Color.DARK_GRAY);
		dialogLabel.setForeground(Color.CYAN);
		dialogLabel.setFont(font.getFont(25f));
		dialogLabel.setPreferredSize(new Dimension(350, 80));
		dialogLabel.setHorizontalAlignment(JLabel.CENTER);
		dialogLabel.setVerticalAlignment(JLabel.CENTER);
		dialogLabel.setHorizontalTextPosition(JLabel.CENTER);
		dialogLabel.setVerticalTextPosition(JLabel.CENTER);
		dialogLabel.setVisible(true);
	}

	private void initializeButton(JButton temp) {
		Font customFont = new CustomFont().getFont(25f);
		temp.setEnabled(true);
		temp.setFont(customFont);
		temp.setBackground(Color.DARK_GRAY);
		temp.setForeground(Color.CYAN);
		temp.setFocusPainted(false);
		temp.setVisible(true);
	}

	private void closeDialog() {
		this.dispose();
	}

	private void addActionListeners() {
		restartButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
				String d = gf.getDifficulty();
				gf.closeGameFrame();
				GameFrame g = new GameFrame(d);
			}
		});

		menuButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				closeDialog();
				gf.closeGameFrame();
				MenuGUI m = new MenuGUI();
				m.start();
			}
		});

		quitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

}
