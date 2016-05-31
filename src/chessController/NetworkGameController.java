package chessController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

import chess.Board;
import chess.Square;
import chessGui.ChessFrame;
import chessGui.WinnerFrame;
import chessServer.Decoder;

public class NetworkGameController  implements Runnable{
	private BufferedReader in;
	private PrintWriter out;
	private ChessFrame frame;
	private Square from;
	private Square to;
	private boolean fromTaken;
	private WinnerFrame  Wframe;
	private String colourCode;
	private boolean active;
	
	public NetworkGameController(ChessFrame view){
		frame = view;
		fromTaken = false;
		active = false;
		for(int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				frame.addActionListener(i, j, new ButtonAction(new Square(j, i)));
	}
	
	public synchronized void  run() {
		//establish connection
        String serverAddress = getServerAddress();
        Socket socket;
		try {
			socket = new Socket(serverAddress, 7766);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    out = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//get activation msg from server
		
		try {
			String line;
			line = in.readLine();
			if (line.equals("Activated: W")){
				System.out.println("I am activated: W");
				colourCode = "W";
				active = true;
			}
			else if (line.equals("Activated: B")) {
				System.out.println("I am activated: B");
				colourCode = "B";
				active = true;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
        // process all messages from server by performing moves
		try {
			while (true) {
				String line;
				line = in.readLine();
				if(line.startsWith("Promotion")){
					Square promoted = Decoder.decodePromotedSquare(line);
					frame.changePieceToQueen(promoted);
				}
				else if (line.startsWith("Checkmate")){
					Wframe = new WinnerFrame(Decoder.decodeCheckmateColour(line));
					Wframe.setVisible(true);
					Wframe.setLocationRelativeTo(frame);
					frame.setVisible(false);
					frame.dispose();
					break;
				}
				else{	
		            Square from = Decoder.decodeFrom(line);
		            Square to = Decoder.decodeTo(line);
		            frame.drawMove(from, to);
				}
			} 
			} catch (IOException e) {
				e.printStackTrace();
			}
			
    }
	
	
	private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chess Server",
            JOptionPane.QUESTION_MESSAGE);
    }
	
	private class ButtonAction implements ActionListener{
		public ButtonAction(Square square){
			position = square;
		}
		public void actionPerformed(ActionEvent e) {
			if (active){
				
				if (!fromTaken ) {
					from = position;
					fromTaken = true;
				}
				else {
					to = position;
					String message = colourCode + from.getX() + from.getY() + to.getX() + to.getY();
					out.println(colourCode + from.getX() + from.getY() + to.getX() + to.getY());
					out.flush();
					fromTaken = false;
					System.out.println("Sent " + message);
				}
			}
		}
		private Square position;
	}
}
