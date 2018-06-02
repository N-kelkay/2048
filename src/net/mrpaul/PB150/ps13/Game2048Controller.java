package net.mrpaul.PB150.ps13;

public class Game2048Controller {
	private Board board;
	private Game2048View view;

	public Game2048Controller(Board b, Game2048View v){
		this.board = b;
		this.view = v;
	}

	public void runGame(){
		boolean isRunning = true;
		while(isRunning){

			if(board.canMove() == true) {
				//Board state = new Board(board.);

				if (view.getUserAction() == UserAction.UP) {
					board.up();
				}
				if (view.getUserAction() == UserAction.DOWN) {
					board.down();
				}
				if (view.getUserAction() == UserAction.LEFT) {
					board.left();
				}
				if (view.getUserAction() == UserAction.RIGHT) {
					board.right();
				}
				if (view.getUserAction() == UserAction.QUIT) {
					isRunning = false;
					board.quit();
				}
				if (view.getUserAction() == UserAction.RESET) {
					board.reset();
				}

				view.setBoard(board);

			}
			else {System.out.println("Invalid, try again");}
		}

	}
}
