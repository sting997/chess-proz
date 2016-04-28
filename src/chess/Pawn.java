package chess;

import java.util.ArrayList;
import static chess.Piece.pieceColour;
public class Pawn extends Piece {

	public Pawn(pieceColour colour, boolean movedStatus) {
		super(colour, movedStatus);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean validateMove(int fromX, int fromY, int toX, int toY) {
		
		
		
		if(getColour()==pieceColour.white){ 
			
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
		// TODO Auto-generated method stub
		return null;
	}
}