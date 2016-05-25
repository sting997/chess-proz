package chess;

import java.util.ArrayList;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;

import static java.lang.Math.abs;

public class Pawn extends Piece {

	public Pawn(PieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		if (fromX != toX)
			return false;		
		if(getColour()==PieceColour.WHITE){ 
			if(getMovedStatus() == false)
				return (toY-fromY>=-2)  && (toY-fromY<0);
			else
				return (toY-fromY>=-1) &&  (toY-fromY<0);
		}
		else{
			if(getMovedStatus() == false)
				return (toY-fromY<=2) && (toY-fromY>0);
			else
				return (toY-fromY<=1) && (toY-fromY>0);
		}
	} 

	@Override
	public ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY) {
		ArrayList<MoveDemands> result = new ArrayList<MoveDemands>();
		int direction = (toY - fromY > 0) ? 1 : -1;
		int start = fromY;
		if(abs(toY-fromY)==2){
			start += direction;
			MoveDemands tmp = new MoveDemands(toX, start, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
			result.add(tmp);
		}
		
		start += direction;
		MoveDemands tmp2= new MoveDemands(toX, start, createOppositeColourDemand(), MovedStatusDemand.NO_DEMAND);	
		result.add(tmp2);
		return result;
		
	}
}