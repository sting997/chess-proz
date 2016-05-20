package chessTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.*;

public class KingTest {
	
	@Test
	public void constructorTest() {
		King krol = new King(Piece.PieceColour.BLACK, false);
		assertEquals(false, krol.getMovedStatus());
		assertEquals(Piece.PieceColour.BLACK, krol.getColour());
	}
	
	@Test
	public void moveValidateTest(){
		King king = new King(Piece.PieceColour.BLACK, false);
		assertEquals(false, king.validateMove(3, 2, 0, 0));
		assertEquals(true, king.validateMove(3, 2, 3, 3));
	}
	
	@Test
	public void generateInterveningFieldsTest(){
		King king = new King(Piece.PieceColour.BLACK, false);
		
		MoveDemands demand = new MoveDemands(3, 3, ColourDemand.NOT_CURRENT_COLOUR, 
				MovedStatusDemand.NO_DEMAND);
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>();
		demands.add(demand);
	
		assertEquals(true, king.generateInterveningFields(3, 2, 3, 3).equals(demands));
	}
	
}
