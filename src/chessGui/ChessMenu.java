package chessGui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import chess.Piece;
import chess.Board;
import chessController.LocalGameController;


public class ChessMenu extends JFrame {

    private MenuPanel mp;
    private Color backgroundColour = new Color(139, 69, 19);
    private final String[] MENU={"Local Game","Network Game","Exit"};
    private ImageComponent imgComponent;

    public ChessMenu() {

        super("ChessGame Menu");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex)
        {}
        setSize(500,500);
        mp = new MenuPanel();
        mp.setBounds(0,225,500,250);
        add(mp);
        for(int i = 0; i<MENU.length;i++)
        {
            JButton mb = new JButton(MENU[i]);
            if(i==0){
                LocalGameButtonAction localGameListener = new LocalGameButtonAction();
                mb.addActionListener(localGameListener);
            }
            mb.setBounds((getWidth()-200)/2,50+32*i,200,30);
            mp.add(mb);
        }
        imgComponent = new ImageComponent();
        imgComponent.setBounds(0,0,500,500);
        add(imgComponent);


        setVisible(true);
    }


public class LocalGameButtonAction implements ActionListener
{
    @Override
    public void actionPerformed(ActionEvent e) {
                Board model = new Board();
				ChessFrame view = new ChessFrame(model);
				LocalGameController controller = new LocalGameController(view, model);
				view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				view.setVisible(true);
    }
}
class MenuPanel extends JPanel{
    public MenuPanel() {
        super();
        setLayout(null);
        setBackground(backgroundColour);
        setSize(250,150);
        setVisible(true);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        String message = new String("Chess Game");
        Font f =new Font ("Verdana",Font.BOLD,42);
        g2.setFont(f);
        g2.setPaint(Color.BLACK);
        g2.drawString(message,110,200);

    }
}
class ImageComponent extends JPanel {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 250;
    private Image img;

    public ImageComponent() {
        this.img = new ImageIcon("img/chss.png").getImage();
        setLayout(null);
        setSize(WIDTH,HEIGHT);
        setBackground(backgroundColour);
        setVisible(true);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 125, 0, null);
        setBackground(backgroundColour);
    }
}

}
