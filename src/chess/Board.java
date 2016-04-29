package chess;

import java.util.ArrayList;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.PieceColour;

public class Board {
	private Piece chessboard[][];
	private Piece.PieceColour currentColour;
	public Board() {
		currentColour = PieceColour.WHITE;
		chessboard = new Piece[8][8];
		chessboard[0][0] = new Rook(PieceColour.BLACK, false);
		chessboard[7][0] = new Rook(PieceColour.BLACK, false);
		chessboard[1][0] = new Knight(PieceColour.BLACK, false);
		chessboard[6][0] = new Knight(PieceColour.BLACK, false);
		chessboard[2][0] = new Bishop(PieceColour.BLACK, false);
		chessboard[5][0] = new Bishop(PieceColour.BLACK, false);
		chessboard[3][0] = new Queen(PieceColour.BLACK, false);
		chessboard[4][0] = new King(PieceColour.BLACK, false);
		chessboard[0][7] = new Rook(PieceColour.WHITE, false);
		chessboard[7][7] = new Rook(PieceColour.WHITE, false);
		chessboard[1][7] = new Knight(PieceColour.WHITE, false);
		chessboard[6][7] = new Knight(PieceColour.WHITE, false);
		chessboard[2][7] = new Bishop(PieceColour.WHITE, false);
		chessboard[5][7] = new Bishop(PieceColour.WHITE, false);
		chessboard[3][7] = new Queen(PieceColour.WHITE, false);
		chessboard[4][7] = new King(PieceColour.WHITE, false);
		for (int i = 0; i < 8; i++) chessboard[i][1] = new Pawn(PieceColour.BLACK, false);
		for (int i = 0; i < 8; i++) chessboard[i][6] = new Pawn(PieceColour.WHITE, false);
	}
	
	public boolean validateDemands(ArrayList<MoveDemands> demands) {
		if (demands.size() == 0) return false;
		for (int i = 0; i < demands.size(); i++)
			if(! checkColourDemand(demands.get(i)) || !checkMovedStatusDemand(demands.get(i))) 
				return false;
		
		return true;
	}
	
	private boolean checkColourDemand(MoveDemands demand) {
		//returns true when demand is achieved
		int x = demand.getxCoordinate();
		int y = demand.getyCoordinate();
		ColourDemand colour = demand.getPieceColourNeeded();
		if (colour == ColourDemand.EMPTY)
			return chessboard[y][x] == null;
		
		else 
			return chessboard[y][x] != null && chessboard[y][x].getColour() != this.currentColour;
		
	}
	
	private boolean checkMovedStatusDemand(MoveDemands demand) {
		//returns true when demand is achieved
		int x = demand.getxCoordinate();
		int y = demand.getyCoordinate();
		MovedStatusDemand movedStatus = demand.getMovedStatusNeeded();
		return (movedStatus == MovedStatusDemand.NO_DEMAND) || 
				(movedStatus == MovedStatusDemand.NOT_MOVED && !(chessboard[y][x].getMovedStatus()));
	}
	
}
