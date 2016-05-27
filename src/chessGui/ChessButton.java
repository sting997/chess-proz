package chessGui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ChessButton extends JButton {
	
	private static final long serialVersionUID = 1L;

	public ChessButton(int size, Color background, ImageIcon img) {
		super(img);
		setPreferredSize(new Dimension(size, size));
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(background);
	}
	
	public ChessButton(int size, Color background) {
		super();
		setPreferredSize(new Dimension(size, size));
		setMargin(new Insets(0, 0, 0, 0));
		setBackground(background);
	}

}