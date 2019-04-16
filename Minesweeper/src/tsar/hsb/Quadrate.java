package tsar.hsb;

public class Quadrate {

	private int x, y, numberValue;

	private boolean isMine;

	public Quadrate(int x, int y) {
		this.x = x;
		this.y = y;
		this.isMine = false;
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

}