package chessGui;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;

import chess.Board;
import chessController.LocalGameController;
import chessController.NetworkGameController;

public class ChessProgramme {

	public static void main(String[] args) throws IOException {
		//EventQueue.invokeLater(new Runnable() {
			//run();
		//});
		Board model = new Board();
		ChessFrame view = new ChessFrame(model);
		//LocalGameController controller = new LocalGameController(view, model);
		Thread t = new Thread( new NetworkGameController(view));
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		t.start();
	}
//	public static void run() {
//		Board model = new Board();
//		ChessFrame view = new ChessFrame(model);
//		//LocalGameController controller = new LocalGameController(view, model);
//		NetworkGameController controller = new NetworkGameController(view, model);
//		controller.run();
//		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		view.setVisible(true);
//	}
}