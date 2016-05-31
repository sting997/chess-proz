package chessServer;

import chess.Square;
import chess.Piece.PieceColour;

public class Decoder {
	public static Square decodeTo(String message){
		int toX = Integer.parseInt(message.substring(3, 4));
		int toY = Integer.parseInt(message.substring(4));
		return new Square(toX, toY);
	}
	
	public static Square decodeFrom(String message){
		int fromX = Integer.parseInt(message.substring(1, 2));
		int fromY = Integer.parseInt(message.substring(2, 3));
		return new Square(fromX, fromY);
	}
	
	/**
	 * Detects who send message
	 * @param message
	 * @return colour of sender
	 */
	public static PieceColour decodeColourFromMessage(String message) {
		PieceColour result = message.startsWith("W") ? PieceColour.WHITE : PieceColour.BLACK;
		return result;
	}
	
	public static Square decodePromotedSquare(String message) {
		int x = Integer.parseInt(message.substring(11, 12));
		int y = Integer.parseInt(message.substring(12));
		return new Square(x, y);
	}
	
	public static PieceColour decodeCheckmateColour(String message) {
		PieceColour result = message.endsWith("W") ? PieceColour.WHITE : PieceColour.BLACK;
		return result;
	}

}
