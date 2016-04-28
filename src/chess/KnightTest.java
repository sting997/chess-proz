package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.colourDemand;
import chess.MoveDemands.movedStatusDemand;
import chess.Piece.pieceColour;

public class KnightTest {

	@Test
	public void validateMoveTest() {
		Knight knight = new Knight(pieceColour.black, true);
		assertEquals(true, knight.validateMove(3, 2, 5, 3));
		assertEquals(true, knight.validateMove(3, 2, 2, 0));
		assertEquals(false, knight.validateMove(3, 2, 7, 0));
	}
	
	@Test
	public void generateInterveningFieldsTest(){
		Knight knight = new Knight(Piece.pieceColour.black, false);
		
		MoveDemands demand = new MoveDemands(3, 3, colourDemand.notCurrentColour, 
				movedStatusDemand.noDemand);
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>();
		demands.add(demand);
	
		assertEquals(true, knight.generateInterveningFields(1, 1, 3, 3).equals(demands));
	}

}
