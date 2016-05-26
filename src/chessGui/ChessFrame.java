package chessGui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import chess.Board;
import chess.Square;

public class ChessFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_BUTTON_SIZE = 80;
	private JPanel buttonPanel;
	private ChessButton chessBoard[][] = new ChessButton[8][8];
	private Color color1 = Color.WHITE;
	private Color color2 = new Color(139, 69, 19);
	private Board board;
	private Square from;
	private Square to;
	private boolean fromTaken;
			
	public ChessFrame(Board b){
		board = b;
		fromTaken = false;
		Color current = color2;
		buttonPanel = new JPanel(new GridLayout(8, 8));
		buttonPanel.setBackground(Color.BLACK);
		add(buttonPanel);
		for (int i = 0; i < 8; i++){
			current = (current == color1) ? color2 : color1;
			for (int j = 0; j < 8; j++){ 
				if (i == 1) chessBoard[i][j] = new ChessButton(DEFAULT_BUTTON_SIZE, current, new ImageIcon("img/bp.png"));
				else if (i == 6) chessBoard[i][j] = new ChessButton(DEFAULT_BUTTON_SIZE, current, new ImageIcon("img/wp.png"));
			    else chessBoard[i][j] = new ChessButton(DEFAULT_BUTTON_SIZE, current);
				chessBoard[i][j].addActionListener(new ButtonAction(new Square(j, i)));
				current = (current == color1) ? color2 : color1;
				buttonPanel.add(chessBoard[i][j]);
			}
		}
		chessBoard[0][0].setIcon(new ImageIcon("img/br.png"));
		chessBoard[0][7].setIcon(new ImageIcon("img/br.png"));
		chessBoard[0][1].setIcon(new ImageIcon("img/bn.png"));
		chessBoard[0][6].setIcon(new ImageIcon("img/bn.png"));
		chessBoard[0][2].setIcon(new ImageIcon("img/bb.png"));
		chessBoard[0][5].setIcon(new ImageIcon("img/bb.png"));
		chessBoard[0][3].setIcon(new ImageIcon("img/bq.png"));
		chessBoard[0][4].setIcon(new ImageIcon("img/bk.png"));
		chessBoard[7][0].setIcon(new ImageIcon("img/wr.png"));
		chessBoard[7][7].setIcon(new ImageIcon("img/wr.png"));
		chessBoard[7][1].setIcon(new ImageIcon("img/wn.png"));
		chessBoard[7][6].setIcon(new ImageIcon("img/wn.png"));
		chessBoard[7][2].setIcon(new ImageIcon("img/wb.png"));
		chessBoard[7][5].setIcon(new ImageIcon("img/wb.png"));
		chessBoard[7][3].setIcon(new ImageIcon("img/wq.png"));
		chessBoard[7][4].setIcon(new ImageIcon("img/wk.png"));
		setSize(getPreferredSize());
		setLocationByPlatform(true);
		setTitle("Chess game");
		setIconImage(new ImageIcon("img/wk.png").getImage());
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
					chessBoard[to.getY()][to.getX()].setIcon(chessBoard[from.getY()][from.getX()].getIcon());
					chessBoard[from.getY()][from.getX()].setIcon(null);
				}
				fromTaken = false;
			}
			else fromTaken = false;
			
			if(board.checkmateExaminator()){
				setVisible(false);
				dispose();
			}
		}
		private Square position;
	}
}
