package chess;

import java.util.ArrayList;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.PieceColour;

public class Board {
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
		blackKingPostition = new Square(4, 0);
		whiteKingPosition = new Square(4, 7);
	}
	
	/**
	 * changes current colour to opposite value
	 */
	public void changeCurrentColour() {
		currentColour = (currentColour == PieceColour.WHITE) ? PieceColour.BLACK : PieceColour.WHITE;
	}
	
	/**
	 * counts all opponent's figures that attack current players king
	 * @return
	 */
	public int countKingThreats() {
		int count = 0;
		Square attackedSquare = (currentColour == PieceColour.WHITE) ? whiteKingPosition : blackKingPostition;
		for (int i = 0 ; i < 8; i++){
			for (int j = 0; j < 8; j++){
					if(chessboard[i][j] != null && validateMove(new Square(j, i), attackedSquare))
						count++;
			}
		}
		return count;
	}
	
	/**
	 * checks if move is valid and follows rules
	 * @param from : source square for the validated move
	 * @param to : destination square
	 * @return true if move is valid from figure and board perspective
	 */
	public boolean validateMove(Square from, Square to) {
		int fromX = from.getX();
		int fromY = from.getY();
		int toX = to.getX();
		int toY = to.getY();
		Piece figure = chessboard[fromY][fromX];
		return figure.validateMove(fromX, fromY, toX, toY)
				&& validateDemands(figure.generateInterveningFields(fromX, fromY, toX, toY));
	}
	
	/**
	 * moves figure (changes references in chessboard[][])
	 * @param from : source square for move
	 * @param to : destination square
	 */
	public void moveFigure(Square from, Square to) {
		MoveDetails currentMove = new MoveDetails(from, to, chessboard[from.getY()][from.getX()], 
												  chessboard[to.getY()][to.getX()]);
		lastMove = currentMove;
		chessboard[to.getY()][to.getX()] = chessboard[from.getY()][from.getX()];
		chessboard[from.getY()][from.getX()] = null;
	}
	
	/**
	 * changes the chessboard to the state before last move
	 */
	public void undoLastMove() {
		chessboard[lastMove.getFrom().getY()][lastMove.getFrom().getX()] = lastMove.getMovedFigure();
		if(lastMove.getKilledFigure() != null)
			chessboard[lastMove.getTo().getY()][lastMove.getTo().getX()] = lastMove.getKilledFigure();
	}
	
	/**
	 * checks list of demands created by figure which are mandatory for move 
	 * @param demands list of demands that needs to be checked
	 * @return result of validation demands, true if all are fullfilled
	 */
	private boolean validateDemands(ArrayList<MoveDemands> demands) {
		for (int i = 0; i < demands.size(); i++)
			if(! checkColourDemand(demands.get(i)) || !checkMovedStatusDemand(demands.get(i))) 
				return false;
		return true;
	}
	
	/**
	 * checks single colour demand
	 * @param demand : demand that needs to be checked
	 * @return true when demand is achieved
	 */
	private boolean checkColourDemand(MoveDemands demand) {
		int x = demand.getxCoordinate();
		int y = demand.getyCoordinate();
		ColourDemand colour = demand.getPieceColourNeeded();
		if (colour == ColourDemand.EMPTY)
			return chessboard[y][x] == null;
		else if (colour == ColourDemand.NOT_BLACK)
			return chessboard[y][x] == null || chessboard[y][x].getColour() != PieceColour.BLACK;
		else
			return chessboard[y][x] == null || chessboard[y][x].getColour() != PieceColour.WHITE;
	}
	
	/**
	 * checks single moved status demand
	 * @param demand : demand that needs to be checked
	 * @return true when demand is achieved
	 */
	private boolean checkMovedStatusDemand(MoveDemands demand) {
		int x = demand.getxCoordinate();
		int y = demand.getyCoordinate();
		MovedStatusDemand movedStatus = demand.getMovedStatusNeeded();
		return (movedStatus == MovedStatusDemand.NO_DEMAND) || 
				(chessboard[y][x] != null && movedStatus == MovedStatusDemand.NOT_MOVED 
					&& !(chessboard[y][x].getMovedStatus()));
	}
	
	private Piece chessboard[][];
	private Piece.PieceColour currentColour;
	private MoveDetails lastMove;
	private Square blackKingPostition;
	private Square whiteKingPosition;
}
