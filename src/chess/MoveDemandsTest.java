package chess;

import static org.junit.Assert.*;

import org.junit.Test;
import static chess.MoveDemands.*;

public class MoveDemandsTest {

	@Test
	public void testEquals() {
		MoveDemands a = new MoveDemands(1,1, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		MoveDemands b = new MoveDemands(1,1, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		MoveDemands c = new MoveDemands(2,1, ColourDemand.NOT_CURRENT_COLOUR, MovedStatusDemand.NO_DEMAND);
		assertEquals(true, a.equals(b));
		assertEquals(false, a.equals(c));
	}

}
