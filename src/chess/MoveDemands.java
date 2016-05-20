package chess;

public class MoveDemands {
	public enum ColourDemand {EMPTY, NOT_CURRENT_COLOUR};
	public enum MovedStatusDemand {NOT_MOVED, NO_DEMAND};
	
	private int xCoordinate;
	private int yCoordinate;
	private ColourDemand pieceColourNeeded;
	private MovedStatusDemand movedStatusNeeded;
	
	
	public MoveDemands(int xCoordinate, int yCoordinate, ColourDemand pieceColourNeeded,
			MovedStatusDemand movedStatusNeeded) 
	{
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.pieceColourNeeded = pieceColourNeeded;
		this.movedStatusNeeded = movedStatusNeeded;
	}
	
	public boolean equals(Object otherObject) {
	    if (this == otherObject) return true;
	    if (!(this.getClass().equals(otherObject.getClass()) )) return false;
	    MoveDemands otherDemand = (MoveDemands) otherObject;
	    return (getxCoordinate() == otherDemand.getxCoordinate())
	        && getyCoordinate() == otherDemand.getyCoordinate()
	        && getMovedStatusNeeded().equals(otherDemand.getMovedStatusNeeded())
	        && getPieceColourNeeded().equals(otherDemand.getPieceColourNeeded());
	  }

	
	public int getxCoordinate() 
	{
		return xCoordinate;
	}
	
	public int getyCoordinate() 
	{
		return yCoordinate;
	}
	
	public ColourDemand getPieceColourNeeded() 
	{
		return pieceColourNeeded;
	}
	
	public MovedStatusDemand getMovedStatusNeeded() 
	{
		return movedStatusNeeded;
	}

}
