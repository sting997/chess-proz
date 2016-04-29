package chess;

import java.util.*;
import chess.MoveDemands;
public abstract class Piece 
{
	enum PieceColour {BLACK, WHITE};
	
	private PieceColour colour;
	private boolean movedStatus;
	
	public abstract boolean validateMove(int fromX, int fromY, int toX, int toY);
	public abstract ArrayList<MoveDemands> generateInterveningFields(int fromX, int fromY, int toX, int toY);

	protected Piece(PieceColour colour, boolean movedStatus) 
	{
		this.colour = colour;
		this.movedStatus = movedStatus;
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
