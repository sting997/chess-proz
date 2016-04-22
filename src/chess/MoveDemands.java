package chess;

class MoveDemands {
	enum colourDemand {black, white, noDemand};
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
