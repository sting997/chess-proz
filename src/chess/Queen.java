package chess;

import java.util.Vector;

public class Queen extends Piece {

	public Queen(pieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
