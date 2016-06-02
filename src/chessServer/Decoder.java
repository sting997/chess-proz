package chessServer;

import chess.Square;
import chess.Piece.PieceColour;


/**
 * simple class with static methods only
 * used by both server and clients to decode messages
 * @author michal
 *
 */
public class Decoder {
	
	/**
	 * decodes destination Square for the move given in the message
	 * @param message
	 * @return
	 */
	public static Square decodeTo(String message){
		int toX = Integer.parseInt(message.substring(3, 4));
		int toY = Integer.parseInt(message.substring(4));
		return new Square(toX, toY);
	}
	
	/**
	 * decodes source Square for the move given in the message
	 * @param message
	 * @return
	 */
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
	
	/**
	 * method which decodes square from promotion message given by server
	 * @param message
	 * @return
	 */
	public static Square decodePromotedSquare(String message) {
		int x = Integer.parseInt(message.substring(11, 12));
		int y = Integer.parseInt(message.substring(12));
		return new Square(x, y);
	}
	
	
	/**
	 * decodes colour of player whose king is attacked with no escape: checkmate
	 * @param message
	 * @return
	 */
	public static PieceColour decodeCheckmateColour(String message) {
		PieceColour result = message.endsWith("W") ? PieceColour.WHITE : PieceColour.BLACK;
		return result;
	}

}
