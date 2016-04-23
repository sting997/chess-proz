package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import static chess.MoveDemands.*;

public class MoveDemandsTest {

	@Test
	public void testEquals() {
		MoveDemands a = new MoveDemands(1,1, colourDemand.black, movedStatusDemand.noDemand);
		MoveDemands b = new MoveDemands(1,1, colourDemand.black, movedStatusDemand.noDemand);
		MoveDemands c = new MoveDemands(2,1, colourDemand.black, movedStatusDemand.noDemand);
		assertEquals(true, a.equals(b));
		assertEquals(false, a.equals(c));
	}

}
