package chess;

/**
 * class representing details of particular move
 * @author michal
 *
 */
public class MoveDetails {
	
	public MoveDetails(Square from, Square to, Piece movedFigure, Piece killedFigure) {
		this.from = from;
		this.to = to;
		this.movedFigure = movedFigure;
		this.killedFigure = killedFigure;
	}
	
	public MoveDetails(Square from, Square to){
		this.from = from;
		this.to = to;
	}
	
	public Square getFrom() {
		return from;
	}
	public Square getTo() {
		return to;
	}
	public Piece getMovedFigure() {
		return movedFigure;
	}
	public Piece getKilledFigure() {
		return killedFigure;
	}

	private Square from;
	private Square to;
	private Piece movedFigure; //figure that was moved
	private Piece killedFigure; //figure that was on destination square, null => square was empty
}
