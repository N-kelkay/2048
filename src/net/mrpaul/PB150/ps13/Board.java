package net.mrpaul.PB150.ps13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

	private int gameSize;
	//
	private Tile[][] board;
	private int score;

	Board() {
		this.gameSize = 0;
		this.board = new Tile[4][4];
		this.score = 0;
	}

	Board(int size) {
		this.gameSize = size;
	}

	Board(int size, int newScore) {
		this.gameSize = size;
		this.score = newScore;
	}

	Board(String fileName) throws FileNotFoundException {
		File f1 = new File(fileName);
		Scanner reader = new Scanner(f1);
		while (reader.hasNext()) {
			System.out.println(reader.nextLine());
		}
	}

	/**
	 * likely to be called by the filereading Constructor
	 *
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public void loadBoardState(String fileName) throws FileNotFoundException {
		Board board = new Board(fileName);
	}

	/**
	 * sets all Tiles to 0; reset score to 0
	 */
	public void reset() {
		this.board = new Tile[0][0];
		this.score = 0;
	}

	/**
	 * count zero tiles
	 *
	 * @return
	 */
	public int countEmptySpaces() {
		int count = 0;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j].getValue() == 0) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * getter for score
	 *
	 * @return
	 */
	public int getScore() {
		return this.score;
	}

	/**
	 * true if there are no zero tiles; false otherwise
	 *
	 * @return
	 */
	public boolean isFull() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j].getValue() != 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * examines whether or not there is at least one possible move on the board as it is.
	 * If there ARE possible moves, it returns true; else false
	 *
	 * @return
	 */
	public boolean canMove() {
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length; j++) {
				if (this.board[i][j].getValue() != 0) {
					return true;
				} else if ((this.board[i][j].getValue() == this.board[i + 1][i + j].getValue()) || (this.board[i][j].getValue() == this.board[i - 1][i - j].getValue())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * adds one random tile to the board in a random empty location.
	 * 90% of the time, the new tile should be a 2; 10% of the time, it should be a 4.
	 */
	public void addTile() {

		if (isFull() == false) {
			for (int i = 0; i < this.board.length; i++) {
				for (int j = 0; j < this.board[i].length; j++) {
					if (this.board[i][j].getValue() == 0) {
						int x = (int) Math.random() * 101;

						if (x <= 90)
							this.board[i][j] = new Tile(2);
						else
							this.board[i][j] = new Tile(4);
					}
				}
			}
		}

	}

	/**
	 * rotates this Board's 2d Tile array 90 degrees clockwise
	 */
	public void rotateCW() {
		final int M = this.board.length;
		final int N = this.board[0].length;
		Tile[][] ret = new Tile[N][M];
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				ret[c][M - 1 - r] = this.board[r][c];
			}
		}
		this.board = ret;
	}

	/**
	 * checks each row and perform a left shift
	 *
	 * @return
	 */
	public boolean left() {
		boolean changed = false;
		for (int i = 0; i < this.board.length; i++) {
			for (int j = 0; j < this.board[i].length - 1; j++) {
				if (board[i][j + 1].getValue() == board[i][j].getValue()) {
					board[i][j].setValue(board[i][j + 1].getValue() + board[i][j].getValue());
					changed = true;
				}
				if (board[i][j].getValue() == 0) {
					board[i][j].setValue(board[i][j + 1].getValue());
					board[i][j].setValue(0);
				}
			}
		}
		return changed;
	}

	public boolean right() {
		//rotates it 180 degrees
		this.rotateCW();
		this.rotateCW();

		boolean x = this.left();

		//rotates it back 180 degrees
		this.rotateCW();
		this.rotateCW();

		return x;
	}

	public boolean up() {
		this.rotateCW();
		this.rotateCW();
		this.rotateCW();
		boolean x = this.left();
		this.rotateCW();
		return x;
	}

	public boolean down() {
		this.rotateCW();
		boolean x = this.left();
		this.rotateCW();
		this.rotateCW();
		this.rotateCW();

		return x;
	}

	// Not Finished
	public Board copyBoard(){
		Board clone = new Board();
		return clone;
	}

	public void quit() {
		System.exit(0);
	}

	public String toString(){
		String view = score+"\n"+gameSize+"\n";
		for(Tile[] i: board) {
			view = view + Arrays.toString(i)+"\n";
		}
		return view;
	}
}