package tsar.hsb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tsar.hsb.GUI.GameFrame;

public class Controller {

	private int width, height, numberOfMines;
	private GameFrame gFrame;

	public Controller(String difficulty) {
		initializeGameData(difficulty.toLowerCase());
		gFrame = new GameFrame(this.width, this.height);
	}

	private void initializeGameData(String difficulty) {

		String[] gameData = new String[4]; // This Actually Doesn't Matter Because It Gets ReWritten Anyway

		try (Stream<String> stream = Files.lines(Paths.get("GameData.txt"))) {
			gameData = stream.filter(s -> s.contains(difficulty)).collect(Collectors.joining()).split(";");
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.width = Integer.parseInt(gameData[1]);
		this.height = Integer.parseInt(gameData[2]);
		this.numberOfMines = Integer.parseInt(gameData[3]);

		System.out.println(this.width + "  " + this.height + "  " + this.numberOfMines);

	}

}