package chessGui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import chess.Board;

public class ChessProgramme {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ChessFrame frame = new ChessFrame(new Board());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}