package chess;

import java.util.ArrayList;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;

import static java.lang.Math.*;

public class Bishop extends Piece {

	public Bishop(PieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		return toX != fromX && toY != fromY && abs(fromX - toX) == abs(fromY - toY);
	}

	@Override
	public ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
		int directionX = (toX - fromX > 0) ? 1 : -1;
		int directionY = (toY - fromY > 0) ? 1 : -1;

		for (int x = fromX + directionX, y = fromY + directionY; x != toX
				&& y != toY; x += directionX, y += directionY) {
			MoveDemands tmp = new MoveDemands(x, y, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
			result.add(tmp);
		}

		MoveDemands tmp = new MoveDemands(toX, toY, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		result.add(tmp);
		return result;
	}
}
