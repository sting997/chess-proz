package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.colourDemand;
import chess.MoveDemands.movedStatusDemand;


public class KingTest {
	
	@Test
	public void constructorTest() {
		King krol = new King(Piece.pieceColour.black, false);
		assertEquals(false, krol.getMovedStatus());
		assertEquals(Piece.pieceColour.black, krol.getColour());
	}
	
	@Test
	public void moveValidateTest(){
		King king = new King(Piece.pieceColour.black, false);
		assertEquals(false, king.validateMove(3, 2, 0, 0));
		assertEquals(true, king.validateMove(3, 2, 3, 3));
	}
	
	@Test
	public void generateInterveningFieldsTest(){
		King king = new King(Piece.pieceColour.black, false);
		
		MoveDemands demand = new MoveDemands(3, 3, colourDemand.notCurrentColour, 
				movedStatusDemand.noDemand);
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>();
		demands.add(demand);
	
		assertEquals(true, king.generateInterveningFields(3, 2, 3, 3).equals(demands));
	}
	
}
