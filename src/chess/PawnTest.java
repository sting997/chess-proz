package chess;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.Piece.pieceColour;

public class PawnTest {

	@Test
	public void validdateMovetest() {
		
		Pawn whitepawn = new Pawn(pieceColour.white, true);
		assertEquals(false, whitepawn.validateMove(3, 6, 3, 4));
		assertEquals(true, whitepawn.validateMove(3, 6, 3, 5));
		
		Pawn blackpawn = new Pawn(pieceColour.black, true);
		assertEquals(false,blackpawn.validateMove(3, 1, 3, 3));
		assertEquals(true,blackpawn.validateMove(3, 1, 3, 2));
		
}

}
