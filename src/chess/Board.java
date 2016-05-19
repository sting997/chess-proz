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
		chessboard[0][7] = new Rook(PieceColour.BLACK, false);
		chessboard[0][1] = new Knight(PieceColour.BLACK, false);
		chessboard[0][6] = new Knight(PieceColour.BLACK, false);
		chessboard[0][2] = new Bishop(PieceColour.BLACK, false);
		chessboard[0][5] = new Bishop(PieceColour.BLACK, false);
		chessboard[0][3] = new Queen(PieceColour.BLACK, false);
		chessboard[0][4] = new King(PieceColour.BLACK, false);
		chessboard[7][0] = new Rook(PieceColour.WHITE, false);
		chessboard[7][7] = new Rook(PieceColour.WHITE, false);
		chessboard[7][1] = new Knight(PieceColour.WHITE, false);
		chessboard[7][6] = new Knight(PieceColour.WHITE, false);
		chessboard[7][2] = new Bishop(PieceColour.WHITE, false);
		chessboard[7][5] = new Bishop(PieceColour.WHITE, false);
		chessboard[7][3] = new Queen(PieceColour.WHITE, false);
		chessboard[7][4] = new King(PieceColour.WHITE, false);
		for (int i = 0; i < 8; i++) chessboard[1][i] = new Pawn(PieceColour.BLACK, false);
		for (int i = 0; i < 8; i++) chessboard[6][i] = new Pawn(PieceColour.WHITE, false);
	}
	
	public void setCurrentColour(Piece.PieceColour colour) {
		currentColour = colour;
	}
	
	public boolean validateMove(Square from, Square to) {
		int fromX = from.getxCoordinate();
		int fromY = from.getyCoordinate();
		int toX = to.getxCoordinate();
		int toY = to.getyCoordinate();
		Piece figure = chessboard[fromY][fromX];
		return figure.validateMove(fromX, fromY, toX, toY)
				&& validateDemands(figure.generateInterveningFields(fromX, fromY, toX, toY));
	}
	
	private boolean validateDemands(ArrayList<MoveDemands> demands) {
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
			return chessboard[y][x] == null || chessboard[y][x].getColour() != this.currentColour;
	}
	
	private boolean checkMovedStatusDemand(MoveDemands demand) {
		//returns true when demand is achieved
		int x = demand.getxCoordinate();
		int y = demand.getyCoordinate();
		MovedStatusDemand movedStatus = demand.getMovedStatusNeeded();
		return (movedStatus == MovedStatusDemand.NO_DEMAND) || 
				(chessboard[y][x] != null && movedStatus == MovedStatusDemand.NOT_MOVED 
					&& !(chessboard[y][x].getMovedStatus()));
	}
}
