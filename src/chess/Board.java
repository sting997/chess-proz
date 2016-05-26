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
		blackKingPosition = new Square(4, 0);
		whiteKingPosition = new Square(4, 7);
		currentKingThreat = null;
	}
	
	/**
	 * Tries to execute move, this method provides
	 * ultimate answer from the board and if it is possible makes move
	 * @param from source field
	 * @param to destination field
	 * @return true if managed to execute move
	 */
	public boolean tryAndExecuteMove(Square from, Square to) {
		if (chessboard[from.getY()][from.getX()] == null || chessboard[from.getY()][from.getX()].getColour() != currentColour) return false;
		if (!validateMove(from, to)) return false;
		moveFigure(from, to);
		if (countKingThreats() > 0) {
			undoLastMove();
			return false;
		}
		chessboard[to.getY()][to.getX()].setMovedStatus(true);
		changeCurrentColour();
		currentKingThreat = null;
		return true;
	}
	
	public boolean examineFigureOwner(Square s) {
		return chessboard[s.getY()][s.getX()] != null && chessboard[s.getY()][s.getX()].getColour() == currentColour;		
	}
	
	/**
	 * Checks if current player has lost the game and cannot escape check
	 * @return true if the game is over and player cannot move
	 */
	public boolean checkmateExaminator() {
		int threats = countKingThreats();
		if (threats == 2) { //the only escape is king moving
			return examineIfKingCannotMove();
		}
		else if (threats == 1) {
			//try to escape checkmate by moving king
			if (!examineIfKingCannotMove()) return false;
			//try to kill current threat or to block its attack by moving to one of the intervening fields
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++){
					if (chessboard[i][j] != null && chessboard[i][j].getColour() == currentColour){
						Square possiblyMovedFigure = new Square(j, i);
						if (examineIfThreadCanBeKilled(possiblyMovedFigure)) return false;
						if (examineIfThreatCanBeBlocked(possiblyMovedFigure)) return false;
					}
				}
			return true;
		}
		else //there is no threat for king
			return false;
	}
	
	/**
	 * Tries to move king
	 * @return true if king cannot be moved
	 */
	private boolean examineIfKingCannotMove() {
		Square currentKingSquare = (currentColour == PieceColour.WHITE) ? whiteKingPosition : blackKingPosition;
		for (int i = -1; i < 2; i++)
			for (int j = -1; j < 2; j++){
				if(currentKingSquare.getX() + j >= 0 && currentKingSquare.getX() + j < 8 && currentKingSquare.getY() + i >= 0 && currentKingSquare.getY() + i < 8){
					Square possibleKingDestination = new Square(currentKingSquare.getX() + j, currentKingSquare.getY() + i);
					if (tryMove(currentKingSquare, possibleKingDestination))
						return false;
				}
			}
		return true;
	}
	
	/**
	 * tries to kill currentKingThreat
	 * @param killingFigure
	 * @return true if currentKingThreat can be killed
	 */
	private boolean examineIfThreadCanBeKilled(Square killingFigure) {
		return tryMove(killingFigure, currentKingThreat);
	}
	
	/**
	 * examines if current thread can be blocked by blockingFigure
	 * @param blockingFigure
	 * @return true if a block is possible
	 */
	private boolean examineIfThreatCanBeBlocked(Square blockingFigure) {
		Square currentKingSquare = (currentColour == PieceColour.WHITE) ? whiteKingPosition : blackKingPosition;
		ArrayList<MoveDemands> demands = 
				chessboard[currentKingThreat.getY()][currentKingThreat.getX()].generateInterveningFields(currentKingThreat.getX(), currentKingThreat.getY(), 
																										currentKingSquare.getX(), currentKingSquare.getY());
		for (int i = 0; i < demands.size() - 1; i++){
			//we do not check last demand as it points to the spot, where stands our king
			if(tryMove(blockingFigure, new Square(demands.get(i).getxCoordinate(), demands.get(i).getyCoordinate())))
				return true;
		}
		return false;
	}
	
	/**
	 * Method used to check if tested move follows all the rules, checks what the situation on the chessboard
	 * will look like after making the move, but then restores previous state of the board
	 * @param from
	 * @param to
	 * @return true if inspected move follows all chess rules
	 */
	private boolean tryMove(Square from, Square to) {
		if (chessboard[from.getY()][from.getX()].getColour() != currentColour) return false;
		if (!validateMove(from, to)) return false;
		moveFigure(from, to);
		if (countKingThreats() > 0) {
			undoLastMove();
			return false;
		}
		undoLastMove();
		return true;
	}
	
	/**
	 * changes current colour to opposite value
	 */
	private void changeCurrentColour() {
		currentColour = (currentColour == PieceColour.WHITE) ? PieceColour.BLACK : PieceColour.WHITE;
	}
	
	/**
	 * counts all opponent's figures that attack current players king
	 * @return
	 */
	private int countKingThreats() {
		int count = 0;
		Square attackedSquare = (currentColour == PieceColour.WHITE) ? whiteKingPosition : blackKingPosition;
		for (int i = 0 ; i < 8; i++){
			for (int j = 0; j < 8; j++){
					if(chessboard[i][j] != null && chessboard[i][j].getColour() != currentColour && validateMove(new Square(j, i), attackedSquare)){
						count++;
						currentKingThreat = new Square(j, i);
					}
			}
		}
		return count;
	}
	
	/**
	 * checks only if move is valid and follows rules, does not check situation on the board
	 * after this partcular move
	 * @param from : source square for the validated move
	 * @param to : destination square
	 * @return true if move is valid from figure and board perspective
	 */
	private boolean validateMove(Square from, Square to) {
		int fromX = from.getX();
		int fromY = from.getY();
		int toX = to.getX();
		int toY = to.getY();
		Piece figure = chessboard[fromY][fromX];
		return figure != null && figure.validateMove(fromX, fromY, toX, toY)
				&& validateDemands(figure.generateInterveningFields(fromX, fromY, toX, toY));
	}
	
	/**
	 * moves figure (changes references in chessboard[][])
	 * @param from : source square for move
	 * @param to : destination square
	 */
	private void moveFigure(Square from, Square to) {
		MoveDetails currentMove = new MoveDetails(from, to, chessboard[from.getY()][from.getX()], 
												  chessboard[to.getY()][to.getX()]);
		lastMove = currentMove;
		chessboard[to.getY()][to.getX()] = chessboard[from.getY()][from.getX()];
		chessboard[from.getY()][from.getX()] = null;
		if(currentColour == PieceColour.BLACK && blackKingPosition.getX() == from.getX() && blackKingPosition.getY() == from.getY())
			blackKingPosition = to;
		else if(currentColour == PieceColour.WHITE && whiteKingPosition.getX() == from.getX() && whiteKingPosition.getY() == from.getY())
			whiteKingPosition = to;
	}
	
	/**
	 * changes the chessboard to the state before last move
	 */
	private void undoLastMove() {
		chessboard[lastMove.getFrom().getY()][lastMove.getFrom().getX()] = lastMove.getMovedFigure();
		chessboard[lastMove.getTo().getY()][lastMove.getTo().getX()] = lastMove.getKilledFigure();
		if(currentColour == PieceColour.BLACK && blackKingPosition.getX() == lastMove.getTo().getX() && blackKingPosition.getY() == lastMove.getTo().getY())
			blackKingPosition = lastMove.getFrom();
		if(currentColour == PieceColour.WHITE && whiteKingPosition.getX() == lastMove.getTo().getX() && whiteKingPosition.getY() == lastMove.getTo().getY())
			whiteKingPosition = lastMove.getFrom();
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
		else if (colour == ColourDemand.BLACK)
			return chessboard[y][x] != null && chessboard[y][x].getColour() == PieceColour.BLACK;
		else if (colour == ColourDemand.WHITE)
			return chessboard[y][x] != null && chessboard[y][x].getColour() == PieceColour.WHITE;
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
	private Square blackKingPosition;
	private Square whiteKingPosition;
	private Square currentKingThreat;
}
