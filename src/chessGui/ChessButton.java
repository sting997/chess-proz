package chessGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import chess.Square;

public class ChessButton extends JButton{
	
	private static final long serialVersionUID = 1L;

	public ChessButton(Square square, int size, Color background, ImageIcon img) {
		super(img);
		setPreferredSize(new Dimension(size, size));
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(background);
		position = square;
	}
	
	public ChessButton(Square square, int size, Color background) {
		super();
		setPreferredSize(new Dimension(size, size));
		setMargin(new Insets(0, 0, 0, 0));
		//setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(background);
		position = square;
	}

	public Square getPosition() {
		return position;
	}

	private Square position;
}
