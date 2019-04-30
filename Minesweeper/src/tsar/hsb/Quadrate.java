package tsar.hsb;

import java.awt.Color;

import javax.swing.JButton;

public class Quadrate extends JButton {

	private int xArrayPos, yArrayPos, numberValue;

	private boolean isMine, isRevealed;

	public Quadrate(int xArrayPos, int yArrayPos) {
		this.xArrayPos = xArrayPos;
		this.yArrayPos = yArrayPos;
		this.isMine = false;
		this.isRevealed = false;
		this.setNumberValue(0);
	}

	public void setXArrayPos(int xArrayPos) {
		this.xArrayPos = xArrayPos;
	}

	public int getXArrayPos() {
		return this.xArrayPos;
	}

	public void setYArrayPos(int yArrayPos) {
		this.yArrayPos = yArrayPos;
	}

	public int getYArrayPos() {
		return this.yArrayPos;
	}

	public void setIsMine(boolean isMine) {
		this.isMine = isMine;
	}

	public boolean isMine() {
		return this.isMine;
	}

	public void setIsRevealed(boolean isRevealed) {
		this.isRevealed = isRevealed;
	}

	public boolean isRevealed() {
		return this.isRevealed;
	}

	public void setNumberValue(int numberValue) {
		this.numberValue = numberValue;
	}

	public int getNumberValue() {
		return this.numberValue;
	}

	public void setFontColor(Color c) {
		this.setForeground(c);
	}

	public void showNumber() {
		if (isMine) {
			this.setText("M");
		} else {
			this.setText("" + this.getNumberValue());
		}
	}

}