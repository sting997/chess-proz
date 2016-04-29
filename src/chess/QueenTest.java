package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.PieceColour;

public class QueenTest {

	@Test
	public void validateMoveTest() {
		Queen queen = new Queen(PieceColour.WHITE, true);
		assertEquals(true, queen.validateMove(3, 2, 3, 6));
		assertEquals(true, queen.validateMove(3, 2, 5, 4));
		assertEquals(true, queen.validateMove(3, 2, 3, 1));
		assertEquals(false, queen.validateMove(3, 2, 0, 6));
	}
	
	@Test
	public void generateInterveningFieldsTest1() {
		Queen queen = new Queen(PieceColour.BLACK, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(3, 3, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(3, 4, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(3, 5, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		assertEquals(true, a.equals(queen.generateInterveningFields(3, 2, 3, 5)));
	}
	
	@Test
	public void generateInterveningFieldsTest2() {
		Queen queen = new Queen(PieceColour.BLACK, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(5, 0, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(6, 0, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(7, 0, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		assertEquals(true, a.equals(queen.generateInterveningFields(4, 0, 7, 0)));
	}
	
	@Test
	public void generateInterveningFieldsTest3() {
		Queen queen = new Queen(PieceColour.BLACK, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(3, 3, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(4, 4, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(5, 5, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		assertEquals(true, a.equals(queen.generateInterveningFields(2, 2, 5, 5)));
		
	}

}
