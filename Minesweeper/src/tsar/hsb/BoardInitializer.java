package tsar.hsb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tsar.hsb.GUI.GameFrame;

public class BoardInitializer {

	private int width, height, numberOfMines;

	private GameFrame gFrame;

	private Quadrate[][] gameBoard;

	public BoardInitializer(String difficulty) {
		initializeGameData(difficulty.toLowerCase());
		gameBoard = new Quadrate[this.width][this.height];
		initializeQuadrates();
	}

	private void initializeGameData(String difficulty) {

		String[] gameData = new String[4]; // This Actually Doesn't Matter Because It Gets Rewritten Anyway

		try (Stream<String> stream = Files.lines(Paths.get("GameData.txt"))) {
			gameData = stream.filter(s -> s.contains(difficulty)).collect(Collectors.joining()).split(";");
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = Integer.parseInt(gameData[1]);
		this.height = Integer.parseInt(gameData[2]);
		this.numberOfMines = Integer.parseInt(gameData[3]);
	}

	public int[] getGameInitData() {
		return new int[] { this.width, this.height, this.numberOfMines };
	}

	private void initializeQuadrates() {
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				gameBoard[x][y] = new Quadrate(x, y);
			}
		}
		addMines();

		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				if (!gameBoard[x][y].isMine()) {
					gameBoard[x][y].setNumberValue(countMineNeighbours(x, y));
				} else {
					gameBoard[x][y].setNumberValue(-1);
				}
			}
		}
	}

	private void addMines() {
		Random r = new Random();
		int temp = this.numberOfMines;
		while (temp != 0) {
			int x = r.nextInt(this.width);
			int y = r.nextInt(this.height);

			if (!gameBoard[x][y].isMine() && (x != 0 && y != 0) && countMineNeighbours(x, y) < 2) {
				temp--;
				gameBoard[x][y].setIsMine(true);
			}
		}
	}

	private int countMineNeighbours(int x, int y) {
		int neighbourCount = 0;

		for (int a = -1; a < 2; a++) {
			for (int b = -1; b < 2; b++) {
				if (a == 0 && b == 0) {

				} else {
					if (((a + x) >= 0 && (a + x) < width) && ((b + y) >= 0 && (b + y) < height)) {
						if (gameBoard[a + x][b + y].isMine()) {
							neighbourCount++;
						}
					}
				}
			}
		}

		return neighbourCount;
	}

	public Quadrate[][] getGameBoard() {
		return this.gameBoard;
	}

	private void printGameBoard() {
		System.out.println();
		for (int y = 0; y < this.height; y++) {
			for (int x = 0; x < this.width; x++) {
				if (gameBoard[x][y].isMine()) {
					System.out.print("M ");
				} else {
					System.out.print(gameBoard[x][y].getNumberValue() + " ");
				}
			}
			System.out.println("");
		}
	}

}