package chess;

public class Square {
	
	public Square(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	
	public int getX() {
		return xCoordinate;
	}

	public int getY() {
		return yCoordinate;
	}
	
	public void setX(int xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public void setY(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	private int xCoordinate;
	private int yCoordinate;
}
