package tsar.hsb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tsar.hsb.GUI.GameFrame;

public class Controller {

	private int width, height, numberOfMines;

	private GameFrame gFrame;

	private Quadrate[][] gameBoard;

	public Controller(String difficulty) {
		initializeGameData(difficulty.toLowerCase());

		gFrame = new GameFrame(this.width, this.height);

		gameBoard = new Quadrate[this.width][this.height];

		initializeQuadrates();

		printGameBoard();

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

	private void initializeQuadrates() {
		for (int x = 0; x < this.width; x++) {
			for (int y = 0; y < this.height; y++) {
				gameBoard[x][y] = new Quadrate(x, y);
			}
		}
		addMines();
	}

	private void addMines() {
		//TODO Allow a mine to be added only if 2 or less neighbors are also mines. Prevent Grouping
		Random r = new Random();
		int temp = this.numberOfMines;
		while (temp != 0) {
			int x = r.nextInt(this.width);
			int y = r.nextInt(this.height);

			if (!gameBoard[x][y].isMine() && (x != 0 && y != 0)) {
				temp--;
				gameBoard[x][y].setIsMine(true);
			}
		}
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