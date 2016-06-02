package chess;

import java.util.*;
/**
 * abstract class extended by all figures
 * provides some abstract methods for move validation ect
 */
import chess.MoveDemands;
import chess.MoveDemands.ColourDemand;
public abstract class Piece 
{
	public enum PieceColour {BLACK, WHITE};
	
	private PieceColour colour;
	private boolean movedStatus;
	
	public abstract boolean validateMove(int fromX, int fromY, int toX, int toY);
	public abstract ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY);

	protected Piece(PieceColour colour, boolean movedStatus) 
	{
		this.colour = colour;
		this.movedStatus = movedStatus;
	}
	
	protected ColourDemand createOppositeColourDemand () {
		ColourDemand demand = (colour == PieceColour.WHITE) ? ColourDemand.NOT_WHITE: ColourDemand.NOT_BLACK;
		return demand;
	}
	
	public PieceColour getColour() 
	{
		return colour;
	}
	
	public void setColour(PieceColour colour) 
	{
		this.colour = colour;
	}
	
	public boolean getMovedStatus() 
	{
		return movedStatus;
	}
	
	public void setMovedStatus(boolean movedStatus) 
	{
		this.movedStatus = movedStatus;
	}
	
}
