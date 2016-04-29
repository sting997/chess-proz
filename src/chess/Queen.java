package chess;

import static java.lang.Math.abs;

import java.util.ArrayList;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;


public class Queen extends Piece {

	public Queen(PieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		return fromX == toX || fromY == toY || abs(toX - fromX) == abs(toY - fromY);
	}

	@Override
	public ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
		if (fromX == toX) {
			int direction = (toY - fromY > 0) ? 1 : -1;
			int start = fromY + direction;
			while (start != toY) {
				MoveDemands tmp = new MoveDemands(toX, start, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
				result.add(tmp);
				start += direction;
			}
			MoveDemands tmp = new MoveDemands(toX, toY, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
			result.add(tmp);
			return result;
		}
		else if (fromY == toY) {
			int direction = (toX - fromX > 0) ? 1 : -1;
			int start = fromX + direction;
			while (start != toX) {
				MoveDemands tmp = new MoveDemands(start, toY, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
				result.add(tmp);
				start += direction;
			}
		}
		else {
			int directionX = (toX - fromX > 0) ? 1 : -1;
			int directionY = (toY - fromY > 0) ? 1 : -1;
			for (int x = fromX + directionX, y = fromY + directionY; x != toX
			&& y != toY; x += directionX, y += directionY) {
				
				MoveDemands tmp = new MoveDemands(x, y, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
				result.add(tmp);
			}
		}
		MoveDemands tmp = new MoveDemands(toX, toY, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		result.add(tmp);
		return result;
	}
}
