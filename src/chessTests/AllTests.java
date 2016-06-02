package chessTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ KingTest.class, MoveDemandsTest.class, RookTest.class, BishopTest.class,
				QueenTest.class, KnightTest.class, PawnTest.class, BoardTest.class})
/**
 * all tests for game logic classes
 * @author michal
 *
 */
public class AllTests {

}
