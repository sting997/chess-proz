package chess;

import java.util.ArrayList;
import static java.lang.Math.*;
import static chess.MoveDemands.*;

public class King extends Piece {

	public King(PieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		return abs(fromX - toX) <= 1 && abs(fromY - toY) <= 1;
		// TODO castling rules need to be added here
	}

	@Override
	public ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
		MoveDemands demand = new MoveDemands(toX, toY, ColourDemand.NOT_CURRENT_COLOUR, 
											MovedStatusDemand.NO_DEMAND);
		result.add(demand);
		return result;
		// TODO castling rules need to be added here
	}

}
