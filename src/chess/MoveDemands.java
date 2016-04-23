package chess;

class MoveDemands {
	enum colourDemand {black, white, empty, notCurrentColour};
	enum movedStatusDemand {notMoved, moved, noDemand};
	
	private int xCoordinate;
	private int yCoordinate;
	private colourDemand pieceColourNeeded;
	private movedStatusDemand movedStatusNeeded;
	
	
	public MoveDemands(int xCoordinate, int yCoordinate, colourDemand pieceColourNeeded,
			movedStatusDemand movedStatusNeeded) 
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
	
	public colourDemand getPieceColourNeeded() 
	{
		return pieceColourNeeded;
	}
	
	public movedStatusDemand getMovedStatusNeeded() 
	{
		return movedStatusNeeded;
	}

}
