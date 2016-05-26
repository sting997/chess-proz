package chessTests;

import static org.junit.Assert.*;

import org.junit.Test;
import chess.*;

public class BoardTest {

//	@Test
//	public void validateMoveTest() {
//		Board board = new Board();
//		Square from = new Square(0, 0);
//		Square to = new Square(0, 6);
//		assertEquals(false, board.validateMove(from, to));
//		from.setX(1);
//		from.setY(0);
//		to.setX(0);
//		to.setY(2);
//		assertEquals(true, board.validateMove(from, to));
//	}
//	
//	@Test
//	public void countKingThreatsTest(){
//		Board board = new Board();
//		assertEquals(0, board.countKingThreats());
//		board.moveFigure(new Square(3, 0), new Square(3, 7));
//		assertEquals(1, board.countKingThreats());
//
//	}
	@Test
	public void tryAndExecuteMoveTest(){
		Board board = new Board();
		assertEquals(false, board.tryAndExecuteMove(new Square(1, 0), new Square(1, 7)));
		assertEquals(false, board.tryAndExecuteMove(new Square(1, 0), new Square(2, 2)));
		assertEquals(true, board.tryAndExecuteMove(new Square(6, 6), new Square(6, 4)));
		assertEquals(true, board.tryAndExecuteMove(new Square(1, 0), new Square(2, 2)));

	}
	
	@Test
	public void checkmateExaminatorTest(){
		Board board = new Board();
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(4, 6), new Square(4, 4)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(1, 1), new Square(1, 2)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(6, 7), new Square(7, 5)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(1, 2), new Square(1, 3)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(7, 5), new Square(6, 3)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(1, 3), new Square(1, 4)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(3, 7), new Square(5, 5)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(1, 4), new Square(1, 5)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(5, 5), new Square(5, 1)));
		assertEquals(true, board.checkmateExaminator());
		assertEquals(false, board.tryAndExecuteMove(new Square(0, 1), new Square(0, 2)));
	}
	
	@Test
	public void checkmateExaminatorTest2(){
		Board board = new Board();
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(4, 6), new Square(4, 4)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(5, 1), new Square(5, 2)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(true, board.tryAndExecuteMove(new Square(3, 7), new Square(7, 3)));
		assertEquals(false, board.checkmateExaminator());
		assertEquals(false, board.tryAndExecuteMove(new Square(4, 1), new Square(4, 2)));
		assertEquals(false, board.tryAndExecuteMove(new Square(4, 0), new Square(5, 1)));
		assertEquals(true, board.tryAndExecuteMove(new Square(6, 1), new Square(6, 2)));
		
		
	}
}
