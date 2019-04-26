package tsar.hsb;

public class Quadrate {

	private int x, y, numberValue;

	private boolean isMine, isRevealed;

	public Quadrate(int x, int y) {
		this.x = x;
		this.y = y;
		this.isMine = false;
		this.isRevealed = false;
		this.setNumberValue(0);
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return this.x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
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

}