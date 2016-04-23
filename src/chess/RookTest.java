package chess;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.colourDemand;
import chess.MoveDemands.movedStatusDemand;
import chess.Piece.pieceColour;

public class RookTest {

	@Test
	public void validateMoveTest() {
		Rook rook = new Rook(Piece.pieceColour.black, false);
		assertEquals(false, rook.validateMove(3, 2, 0, 0));
		assertEquals(true, rook.validateMove(3, 2, 3, 4));
		assertEquals(true, rook.validateMove(3, 2, 6, 2));
	}
	
	@Test
	public void generateInterveningFieldsTest1() {
		Rook rook = new Rook(pieceColour.black, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(3, 3, colourDemand.empty, movedStatusDemand.noDemand);
		a.add(tmp);
		tmp = new MoveDemands(3, 4, colourDemand.empty, movedStatusDemand.noDemand);
		a.add(tmp);
		tmp = new MoveDemands(3, 5, colourDemand.notCurrentColour, movedStatusDemand.noDemand);
		a.add(tmp);
		assertEquals(true, a.equals(rook.generateInterveningFields(3, 2, 3, 5)));
		assertEquals(false, a.equals(rook.generateInterveningFields(7, 2, 3, 5)));
	}
	
	@Test
	public void generateInterveningFieldsTest2() {
		Rook rook = new Rook(pieceColour.black, false);
		ArrayList<MoveDemands> a = new ArrayList<MoveDemands>();
		MoveDemands tmp = new MoveDemands(5, 0, colourDemand.empty, movedStatusDemand.noDemand);
		a.add(tmp);
		tmp = new MoveDemands(6, 0, colourDemand.empty, movedStatusDemand.noDemand);
		a.add(tmp);
		tmp = new MoveDemands(7, 0, colourDemand.notCurrentColour, movedStatusDemand.noDemand);
		a.add(tmp);
		assertEquals(true, a.equals(rook.generateInterveningFields(4, 0, 7, 0)));
	}

}
