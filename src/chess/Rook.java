package chess;

import java.util.ArrayList;
import chess.MoveDemands.*;

public class Rook extends Piece {

	public Rook(PieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		return fromX == toX || fromY == toY;
	}

	@Override
	public ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		if (fromX == toX) {
			ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
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
		else{
			ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
			int direction = (toX - fromX > 0) ? 1 : -1;
			int start = fromX + direction;
			while (start != toX) {
				MoveDemands tmp = new MoveDemands(start, toY, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
				result.add(tmp);
				start += direction;
			}
			MoveDemands tmp = new MoveDemands(toX, toY, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
			result.add(tmp);
			return result;
		}
	}

}
