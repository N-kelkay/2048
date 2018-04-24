package net.mrpaul.PB150.ps13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {

	private int gameSize;
	private Tile[][] tile;
	private int score;

	Board() {
		this.gameSize = 0;
		this.tile = new Tile[4][4];
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
		this.tile = new Tile[0][0];
		this.score = 0;
	}

	/**
	 * count zero tiles
	 *
	 * @return
	 */
	public int countEmptySpaces() {
		int count = 0;
		for (int i = 0; i < this.tile.length; i++) {
			for (int j = 0; j < this.tile[i].length; j++) {
				if (this.tile[i][j].getValue() == 0) {
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
		for (int i = 0; i < this.tile.length; i++) {
			for (int j = 0; j < this.tile[i].length; j++) {
				if (this.tile[i][j].getValue() != 0) {
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
		for (int i = 0; i < this.tile.length; i++) {
			for (int j = 0; j < this.tile[i].length; j++) {
				if (this.tile[i][j].getValue() != 0) {
					return true;
				} else if ((this.tile[i][j].getValue() == this.tile[i + 1][i + j].getValue()) || (this.tile[i][j].getValue() == this.tile[i - 1][i - j].getValue())) {
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
		for (int i = 0; i < this.tile.length; i++) {
			for (int j = 0; j < this.tile[i].length; j++) {
				if (this.tile[i][j].getValue() == 0) {
					//this.tile[i][j] = new Tile[2][2];
				}
			}
		}

	}
}