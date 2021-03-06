package chess;

import java.util.ArrayList;

import chess.MoveDemands.MovedStatusDemand;

import static java.lang.Math.*;

public class Knight extends Piece {

	public Knight(PieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		return (abs(toY - fromY) == 2 && abs(toX - fromX) == 1)
				|| (abs(toX - fromX) == 2 && abs(toY - fromY) == 1);
	}

	@Override
	public ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
		MoveDemands demand = new MoveDemands(toX, toY, createOppositeColourDemand(), MovedStatusDemand.NO_DEMAND);
		result.add(demand);
		return result;
	}
}
