package chessTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import chess.MoveDemands.ColourDemand;
import chess.MoveDemands.MovedStatusDemand;
import chess.Piece.PieceColour;
import chess.*;

public class PawnTest {

	@Test
	public void validdateMovetest() {
		
		Pawn whiteMovedPawn = new Pawn(PieceColour.WHITE, true);        // Pawn which was moved can only go to go one box forward 
		assertEquals(false, whiteMovedPawn.validateMove(3, 6, 3, 4));   // 2 step forward 
		assertEquals(true, whiteMovedPawn.validateMove(3, 6, 3, 5));	   // 1 step forward 
		assertEquals(false, whiteMovedPawn.validateMove(3, 4, 3, 5));   // 1 step back 
			
		Pawn blackMovedPawn = new Pawn(PieceColour.BLACK, true);  		
		assertEquals(false,blackMovedPawn.validateMove(3, 1, 3, 3));	   	//2 step forward 
		assertEquals(true,blackMovedPawn.validateMove(3, 1, 3, 2));		//1 step forward 
		assertEquals(false,blackMovedPawn.validateMove(3, 4, 3, 3));		//1 step back 
		
		Pawn whiteNotMovedPawn = new Pawn(PieceColour.WHITE, false); 		
		assertEquals(true,whiteNotMovedPawn.validateMove(3, 6, 3, 4));	   	//2 step forward 
		assertEquals(true,whiteNotMovedPawn.validateMove(3, 6, 3, 5));		//1 step forward 
		assertEquals(false,whiteNotMovedPawn.validateMove(3, 6, 3, 7));		//1 step back
	
		assertEquals(false,whiteNotMovedPawn.validateMove(3, 6, 4, 5));     // Step on side 
}

	@Test	
public void generateInterveningFieldstest1(){
		
		Pawn whitePawn = new Pawn(PieceColour.WHITE, true); 
		MoveDemands demand = new MoveDemands(3, 3,ColourDemand.NOT_WHITE, MovedStatusDemand.NO_DEMAND); 
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>() ;	
		demands.add(demand);
		assertEquals(true, whitePawn.generateInterveningFields(3, 2, 3, 3).equals(demands));

}
	
	@Test	
public void generateInterveningFieldstest2(){
		
		Pawn whitePawn = new Pawn(PieceColour.WHITE, false); 
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>() ;	
		MoveDemands demand_1 = new MoveDemands(3, 3,ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND); 
		MoveDemands demand_2 = new MoveDemands(3, 4,ColourDemand.NOT_WHITE, MovedStatusDemand.NO_DEMAND); 
		demands.add(demand_1);
		demands.add(demand_2);
		assertEquals(true, whitePawn.generateInterveningFields(3, 2, 3, 4).equals(demands));
	
	}
	@Test	
public void generateInterveningFieldstest3(){
		
		Pawn whitePawn = new Pawn(PieceColour.WHITE, false); 
		ArrayList<MoveDemands> demands = new ArrayList<MoveDemands>() ;	
		MoveDemands demand_1 = new MoveDemands(3, 5,ColourDemand.EMPTY, MovedStatusDemand.NO_DEMAND); 
		MoveDemands demand_2 = new MoveDemands(3, 4,ColourDemand.NOT_WHITE, MovedStatusDemand.NO_DEMAND); 
		demands.add(demand_1);
		demands.add(demand_2);
		assertEquals(true, whitePawn.generateInterveningFields(3, 6, 3, 4).equals(demands));
		}
}
