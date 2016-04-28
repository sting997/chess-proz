package chess;

import static org.junit.Assert.*;

import org.junit.Test;

import chess.Piece.pieceColour;

public class PawnTest {

	@Test
	public void validdateMovetest() {
		
		Pawn whiteMovedPawn = new Pawn(pieceColour.white, true);        // Pawn which was moved can only go to go one box forward 
		assertEquals(false, whiteMovedPawn.validateMove(3, 6, 3, 4));   // 2 step forward 
		assertEquals(true, whiteMovedPawn.validateMove(3, 6, 3, 5));	   // 1 step forward 
		assertEquals(false, whiteMovedPawn.validateMove(3, 4, 3, 5));   // 1 step back 
			
		Pawn blackMovedPawn = new Pawn(pieceColour.black, true);  		
		assertEquals(false,blackMovedPawn.validateMove(3, 1, 3, 3));	   	//2 step forward 
		assertEquals(true,blackMovedPawn.validateMove(3, 1, 3, 2));		//1 step forward 
		assertEquals(false,blackMovedPawn.validateMove(3, 4, 3, 3));		//1 step back 
		
		Pawn whiteNotMovedPawn = new Pawn(pieceColour.white, false); 		
		assertEquals(true,whiteNotMovedPawn.validateMove(3, 6, 3, 4));	   	//2 step forward 
		assertEquals(true,whiteNotMovedPawn.validateMove(3, 6, 3, 5));		//1 step forward 
		assertEquals(false,whiteNotMovedPawn.validateMove(3, 6, 3, 7));		//1 step back
	
		assertEquals(false,whiteNotMovedPawn.validateMove(3, 6, 4, 5));     // Step on side 
		

		
}

}
