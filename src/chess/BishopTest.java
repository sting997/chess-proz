package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.colourDemand;
import chess.MoveDemands.movedStatusDemand;
import chess.Piece.*;

public class BishopTest {

	@Test
	public void validateMoveTest() {
		Bishop bishop = new Bishop(pieceColour.black, false);
		assertEquals(true, bishop.validateMove(1, 4, 3, 2));
		assertEquals(false, bishop.validateMove(1, 4, 1, 5));
		assertEquals(true, bishop.validateMove(0, 0, 3, 3));
	}

	@Test
	public void generateInterveningFieldsTest() {
		Bishop bishop = new Bishop(pieceColour.black, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(3, 3, colourDemand.empty, movedStatusDemand.noDemand);
		a.add(tmp);
		tmp = new MoveDemands(4, 4, colourDemand.empty, movedStatusDemand.noDemand);
		a.add(tmp);
		tmp = new MoveDemands(5, 5, colourDemand.notCurrentColour, movedStatusDemand.noDemand);
		a.add(tmp);
		assertEquals(true, a.equals(bishop.generateInterveningFields(2, 2, 5, 5)));
	}

}
