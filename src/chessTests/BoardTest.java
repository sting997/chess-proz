package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;
import chess.*;

public class BoardTest {

	@Test
	public void validateMoveTest() {
		Board board = new Board();
		Square from = new Square(0, 0);
		Square to = new Square(0, 6);
		assertEquals(false, board.validateMove(from, to));
		from.setX(1);
		from.setY(0);
		to.setX(0);
		to.setY(2);
		assertEquals(true, board.validateMove(from, to));
	}
	
	@Test
	public void countKingThreatsTest(){
		Board board = new Board();
		assertEquals(0, board.countKingThreats());
		board.moveFigure(new Square(3, 0), new Square(3, 7));
		assertEquals(1, board.countKingThreats());

	}
}
