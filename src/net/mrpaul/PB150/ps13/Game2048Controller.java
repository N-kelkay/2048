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
			System.out.println(board.toString());
			UserAction move = null;
			if(board.canMove() == true) {
				move = Game2048View.getUserAction();

				Board state = new Board(board.getTile());

				if (move == UserAction.UP) {
					board.up();
				}
				else if (move == UserAction.DOWN) {
					board.down();
				}
				else if (move == UserAction.LEFT) {
					board.left();
				}
				else if (move == UserAction.RIGHT) {
					board.right();
				}
				else if (move == UserAction.QUIT) {
					isRunning = false;
					board.quit();
				}
				else if (move == UserAction.RESET) {
					board.reset();
				}

				if(state.same(board)) {
					board.addTile();
				}

				view.setBoard(board);

			}
			else {System.out.println("Invalid, try again");}
		}
	}
}
