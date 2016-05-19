package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.PieceColour;

public class KnightTest {

	@Test
	public void validateMoveTest() {
		Knight knight = new Knight(PieceColour.BLACK, true);
		assertEquals(true, knight.validateMove(1, 0, 0, 2));
		assertEquals(true, knight.validateMove(3, 2, 2, 0));
		assertEquals(false, knight.validateMove(3, 2, 7, 0));
	}
	
	@Test
	public void generateInterveningFieldsTest(){
		Knight knight = new Knight(Piece.PieceColour.BLACK, false);
		
		MoveDemands demand = new MoveDemands(3, 3, ColourDemand.NOT_CURRENT_COLOUR, 
				MovedStatusDemand.NO_DEMAND);
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>();
		demands.add(demand);
	
		assertEquals(true, knight.generateInterveningFields(1, 1, 3, 3).equals(demands));
	}

}
