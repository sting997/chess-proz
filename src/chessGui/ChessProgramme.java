package chessGui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import chess.Board;
import chessController.LocalGameController;

public class ChessProgramme {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Board model = new Board();
				ChessFrame view = new ChessFrame(model);
				LocalGameController controller = new LocalGameController(view, model);
				view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				view.setVisible(true);
			}
		});
	}
}