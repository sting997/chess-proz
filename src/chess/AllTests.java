package chess;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ KingTest.class, MoveDemandsTest.class, RookTest.class, BishopTest.class,
				QueenTest.class, KnightTest.class})
public class AllTests {

}
