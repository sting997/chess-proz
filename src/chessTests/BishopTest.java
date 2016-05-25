package chessTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.*;
import chess.*;
public class BishopTest {

	@Test
	public void validateMoveTest() {
		Bishop bishop = new Bishop(PieceColour.BLACK, false);
		assertEquals(true, bishop.validateMove(1, 4, 3, 2));
		assertEquals(false, bishop.validateMove(1, 4, 1, 5));
		assertEquals(true, bishop.validateMove(0, 0, 3, 3));
	}

	@Test
	public void generateInterveningFieldsTest() {
		Bishop bishop = new Bishop(PieceColour.BLACK, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(3, 3, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(4, 4, ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		tmp = new MoveDemands(5, 5, ColourDemand.NOT_BLACK, MovedStatusDemand.NO_DEMAND);
		a.add(tmp);
		assertEquals(true, a.equals(bishop.generateInterveningFields(2, 2, 5, 5)));
	}

}
