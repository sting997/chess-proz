package chessServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chess.Board;
import chess.Piece.PieceColour;
import chess.Square;


public class ChessServer {
	private  static final int port = 7766;
	private static ArrayList<PrintWriter> writers = new ArrayList<PrintWriter>();
	private static volatile boolean bothConnected = false;
	private static Board board = new Board();
	
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
					r = new ChessNetworkHandler(incoming);
				else 
					r = new ChessNetworkHandler(incoming);
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
		
		public ChessNetworkHandler(Socket s) {
			socket = s;
		}
		
		public synchronized void run() {
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintWriter(socket.getOutputStream());
				
				writers.add(out);
				if (writers.size() == 2){
					writers.get(0).println("Activated: W");
					writers.get(0).flush();
					System.out.println("Server: Activated: W");
					writers.get(1).println("Activated: B");
					writers.get(1).flush();
					System.out.println("Server: Activated: B");
					bothConnected = true;
				}
				
				while (true) {
					if (bothConnected) {
						//read message
				        String input = in.readLine();
				        if (input == null) {
				            return;
				        }
				        System.out.println("Received: " + input);
				        
				        //decode last message
				        PieceColour requestColour = Decoder.decodeColourFromMessage(input);
				        Square from = Decoder.decodeFrom(input);
				        Square to = Decoder.decodeTo(input);
				        //validate move and notify all if was valid
				        if (requestColour == board.getCurrentColour() && board.tryAndExecuteMove(from, to)){
				        	for (PrintWriter writer : writers){
					        	writer.println(input);
					        	System.out.println("Sent: " + input);
					        	writer.flush();
				        	}
				        }
				        if (board.CheckPromotion() != null){
				        	Square promoted = board.CheckPromotion();
			        		board.performPawnPromotion(promoted);
			        		input = "Promotion: " + promoted.getX() + promoted.getY();
				        	for (PrintWriter writer : writers){
					        	writer.println(input);
					        	System.out.println("Sent: " + input);
					        	writer.flush();
				        	}
				        }
				        if (board.checkmateExaminator()){
				        	String winner = (board.getCurrentColour() == PieceColour.WHITE) ? "W" : "B";
			        		input = "Checkmate: " + winner;
				        	for (PrintWriter writer : writers){				        		
					        	writer.println(input);
					        	System.out.println("Sent: " + input);
					        	writer.flush();
				        	}
				        }
					}  	
                }
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			finally{
				if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } 
                catch (IOException e) {
                }
			}
			
		}
	}
}
