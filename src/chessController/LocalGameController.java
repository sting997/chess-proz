package chessController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chess.Board;
import chess.Square;
import chessGui.ChessFrame;
import chessGui.WinnerFrame;

public class LocalGameController {
	private ChessFrame frame;
	private Board board;
	private Square from;
	private Square to;
	private boolean fromTaken;
	private WinnerFrame  Wframe;
	public LocalGameController(ChessFrame view, Board model) {
		frame = view;
		board = model;
		fromTaken = false;
		for(int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				frame.addActionListener(i, j, new ButtonAction(new Square(j, i)));
	}
	
	private class ButtonAction implements ActionListener{
		public ButtonAction(Square square){
			position = square;
		}
		public void actionPerformed(ActionEvent e) {
			if (!fromTaken && board.examineFigureOwner(position)) {
				from = position;
				fromTaken = true;
			}
			else if (fromTaken){
				to = position;
				if (board.tryAndExecuteMove(from, to)){
					frame.drawMove(from, to);
				}
				fromTaken = false;
			}
			else fromTaken = false;
			
			if (board.CheckPromotion() != null){
				Square promotedPlace = board.CheckPromotion();
				board.performPawnPromotion(promotedPlace);
				frame.changePieceToQueen(promotedPlace);
			}
			if(board.checkmateExaminator()){
				Wframe = new WinnerFrame(board.getCurrentColour());
				Wframe.setVisible(true);
				Wframe.setLocationRelativeTo(frame);
				frame.setVisible(false);
				frame.dispose();
			}
		}
		private Square position;
	}

}

