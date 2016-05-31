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

public class NetworkGameController  implements Runnable{
	private BufferedReader in;
	private PrintWriter out;
	private ChessFrame frame;
	private Board board;
	private Square from;
	private Square to;
	private boolean fromTaken;
	private WinnerFrame  Wframe;
	private String colourCode = "B";
	
	public NetworkGameController(ChessFrame view, Board model){
		frame = view;
		board = model;
		fromTaken = false;
		for(int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				frame.addActionListener(i, j, new ButtonAction(new Square(j, i)));
	}
	
	public void run() {
        String serverAddress = getServerAddress();
        Socket socket;
		try {
			socket = new Socket(serverAddress, 7766);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		    out = new PrintWriter(socket.getOutputStream(), true);
		    System.out.println("Soket odjebany");
		} catch (IOException e) {
			e.printStackTrace();
		}

        // Process all messages from server, according to the protocol.
		try {
			while (true) {
            String line;
            System.out.print("WHILE");
			
				line = in.readLine();
				
	            	Square from = decodeFrom(line);
	            	Square to = decodeTo(line);
	            	board.tryAndExecuteMove(from, to);
	            	frame.drawMove(from, to);
	            	fromTaken = false;
			} 
			} catch (IOException e) {
				System.out.println("Cos sie popsulo");
				e.printStackTrace();
			}
			System.out.println("Wyszedlem z while");
    }
	private Square decodeTo(String message){
		int toX = Integer.parseInt(message.substring(3, 4));
		int toY = Integer.parseInt(message.substring(4));
		return new Square(toX, toY);
	}
	
	private Square decodeFrom(String message){
		int fromX = Integer.parseInt(message.substring(1, 2));
		int fromY = Integer.parseInt(message.substring(2, 3));
		return new Square(fromX, fromY);
	}
	
	private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Enter IP Address of the Server:",
            "Welcome to the Chatter",
            JOptionPane.QUESTION_MESSAGE);
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
					out.println("B" + from.getX() + from.getY() + to.getX() + to.getY());
				}
				fromTaken = false;
			}
			else fromTaken = false;
			
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
