package chessGui;
import chess.Piece;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * frame showed to player after the end of the game
 * gives player game result info on the screen
 * @author michal
 *
 */
public class WinnerFrame extends JFrame {
    private JPanel buttonPanel;
    private Color backgroundColour = new Color(139, 69, 19);
    private Piece.PieceColour CurrentColour;


    public WinnerFrame(Piece.PieceColour colour) {
        CurrentColour = colour;
        setSize(300, 200);
        add(new FontComponent());
        getContentPane().setBackground(backgroundColour);
        buttonPanel = new JPanel();
        JButton exitButton = new JButton("exit");
        ExitButtonAction exit = new ExitButtonAction();
        exitButton.addActionListener(exit);
        buttonPanel.add(exitButton);
        buttonPanel.setBackground(backgroundColour);
        add(buttonPanel, BorderLayout.SOUTH);
        setResizable(false);
    }

    public class ExitButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
        }
    }

    class FontComponent extends JComponent {
        private static final int DEFAULT_WIDTH = 300;

        private static final int DEFAULT_HEIGHT = 200;

        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            String message = "CHECKMATE!!!";
            String message_2 = (CurrentColour == Piece.PieceColour.WHITE) ? "BLACK WINS" : "WHITE WINS";
            Font f = new Font("Droid Sans", Font.BOLD, 36);
            g2.setFont(f);
            FontRenderContext context = g2.getFontRenderContext();
            Rectangle2D bounds = f.getStringBounds(message, context);
            double x = (getWidth() - bounds.getWidth()) / 2;
            double y = (getHeight() - bounds.getHeight()) / 2;
            g2.setPaint(Color.white);
            g2.drawString(message, (int) x, (int) y);
            g2.drawString(message_2, (int) x, (int) (y + bounds.getHeight()));
        }
    }

}

