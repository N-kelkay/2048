package net.mrpaul.PB150.ps13;

/**
 * Main class. This is where the Program runs
 *
 * @author Natenael Kelkay
 */
public class Game2048 {
	private Board board;
	private Game2048View view;
	private Game2048Controller controller;

	/**
	 * constructor
	 */
	public Game2048 (){
		board = new Board();
		view = new Game2048View(board);
		controller = new Game2048Controller(view.getBoard(), view);
	}

	/**
	 * Runs the program
	 * @param args
	 */
	public static void main(String[] args){
		Game2048 play = new Game2048();
		play.controller.runGame();
	}
}
