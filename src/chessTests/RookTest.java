package chessTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.PieceColour;
import chess.*;

public class RookTest {

	@Test
	public void validateMoveTest() {
		Rook rook = new Rook(Piece.PieceColour.BLACK, false);
		assertEquals(false, rook.validateMove(3, 2, 0, 0));
		assertEquals(true, rook.validateMove(3, 2, 3, 4));
		assertEquals(true, rook.validateMove(3, 2, 6, 2));
	}
	
	@Test
	public void generateInterveningFieldsTest1() {
		Rook rook = new Rook(PieceColour.BLACK, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(3, 3, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(3, 4, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(3, 5, ColourDemand.NOT_BLACK, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		assertEquals(true, a.equals(rook.generateInterveningFields(3, 2, 3, 5)));
	}
	
	@Test
	public void generateInterveningFieldsTest2() {
		Rook rook = new Rook(PieceColour.BLACK, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(5, 0, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(6, 0, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(7, 0, ColourDemand.NOT_BLACK, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		assertEquals(true, a.equals(rook.generateInterveningFields(4, 0, 7, 0)));
	}

}
