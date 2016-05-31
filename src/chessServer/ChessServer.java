package chessServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ThreadMXBean;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chess.Piece.PieceColour;


public class ChessServer {
	private  static final int port = 7766;
	private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
	
	public static void main(String[] args) {
		System.out.println("Server is running.");
		try {
			int players = 0;
			ServerSocket socket = new ServerSocket(port);
			while (players != 2) {
				players++;
				Socket incoming = socket.accept();
				System.out.println("Connected player nr: " + players);
				Runnable r;
				if(players == 0) 
					r = new ChessNetworkHandler(incoming, PieceColour.WHITE);
				else 
					r = new ChessNetworkHandler(incoming, PieceColour.BLACK);
				Thread thread = new Thread(r);
				thread.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static class ChessNetworkHandler implements Runnable{
		private Socket socket;
		private BufferedReader in;
		private PrintWriter out;
		private PieceColour colour;
		
		public ChessNetworkHandler(Socket s, PieceColour c) {
			socket = s;
			colour = c;
			//run();
		}
		
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				
				writers.add(out);
				
				while (true) {
					//read message
                    String input = in.readLine();
                    if (input == null) {
                        return;
                    }
                    System.out.println("Received: " + input);
                    
                    //send message to opposite player
                    //PieceColour lastMessageAuthor = decodeColourFromMessage(input);
                    //PrintWriter writer = (lastMessageAuthor == PieceColour.WHITE) ? writers.get(1) : writers.get(0);
                    for (PrintWriter writer : writers){
                    	writer.println(input);
                    	System.out.println("Sent: " + input);
                    	writer.flush();
                    }
                    	
                }
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
			}
			
		}
		
		/**
		 * Detects who send message
		 * @param message
		 * @return colour of sender
		 */
		private PieceColour decodeColourFromMessage(String message) {
			PieceColour result = message.startsWith("W") ? PieceColour.WHITE : PieceColour.BLACK;
			return result;
		}
	}
	
}
