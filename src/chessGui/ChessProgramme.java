package chessGui;


import java.io.IOException;

import javax.swing.JFrame;

import chess.Board;
import chessController.LocalGameController;
import chessController.NetworkGameController;

public class ChessProgramme {

	public static void main(String[] args) throws IOException {
		ChessMenu menu = new ChessMenu();
		//Board model = new Board();
		//ChessFrame view = new ChessFrame();
		//LocalGameController controller = new LocalGameController(view, model);
		/*Thread t = new Thread( new NetworkGameController(view));
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		view.setVisible(true);
		t.start();*/
	}
}