package chess;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;

public class BoardTest {

	@Test
	public void validateMoveTest() {
		Board board = new Board();
		Square from = new Square(0, 0);
		Square to = new Square(0, 6);
		assertEquals(false, board.validateMove(from, to));
		from.setxCoordinate(1);
		from.setyCoordinate(0);
		to.setxCoordinate(0);
		to.setyCoordinate(2);
		assertEquals(true, board.validateMove(from, to));
	}

}
