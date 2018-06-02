package net.mrpaul.PB150.ps13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Board {

	private int gameSize;

	public Tile[][] board;
	private int score;

	Board() {
		this.score = 0;
		this.gameSize = 0;
		this.board = new Tile[4][4];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = new Tile();
			}
		}

		addTile();addTile();

	}

	Board(int size) {
		this.gameSize = size;
		this.board = new Tile[gameSize][gameSize];
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j] = new Tile();
			}
		}
		addTile();addTile();
	}

	Board(int size, int newScore) {
		this.gameSize = size;
		this.score = newScore;
	}

	//NEWEWEWEW
	Board(Tile[][] a){
		score = 0;
		board = new Tile[4][4];
		for(int i = 0; i < a.length;i++) {
			for (int j = 0; j < a[0].length;j++) {
				this.board[i][j]= a[i][j];
			}
		}
	}

	Board(String fileName) throws FileNotFoundException {
		File f1 = new File(fileName);
		Scanner reader = new Scanner(f1);
		while (reader.hasNext()) {
			System.out.println(reader.nextLine());
		}
	}

	public Tile[][] getTile(){
		return this.board;
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
		this.score = 0;

		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board[i].length; j++){
				board[i][j].setValue(0);
			}
		}
		addTile();addTile();
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


		boolean full = true;
		boolean noMoves = true;


		for(int i = 0; i < board.length;i++) {
			for(int j = 0; j< board.length;j++) {
				if(board[i][j].getValue()==0) {full = false;}
			}
		}
		if(board[board.length-1][board.length-1].getValue()==board[board.length-2][board.length-1].getValue()
				||board[board.length-1][board.length-1].getValue()==board[board.length-1][board.length-2].getValue()) {
			noMoves=false;
		}
		for (int i = 0; i < board.length-1;i++) {
			for (int j =0; j<board.length-1; j++) {
				if(board[i][j].getValue()==board[i+1][j].getValue()||board[i][j].getValue()==board[i][j+1].getValue()) {
					noMoves = false;
				}
			}
		}

		if (full==true&&noMoves == true) {
			return false;
		}
		else {return true;}


		/*
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
		*/
	}

	/**
	 * adds one random tile to the board in a random empty location.
	 * 90% of the time, the new tile should be a 2; 10% of the time, it should be a 4.
	 */
	public void addTile() {
		/*
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
		*/

		int q = (int)(Math.random()*4);
		int w = (int)(Math.random()*4);

		int num = (int) (Math.random()*10);
		int ran;
		if (num >0) {
			ran = 2;
		}
		else {ran = 4;}
		if(board[q][w].getValue()!=0) {
			if(!isFull()) {
				addTile();
			}
			else{
				if(!canMove()){
					quit();
				}
			}
		}
		else{
			board[q][w].setValue(ran);
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

		for (int i = 0; i <board.length; i++){

			//Making a shift Tile set
			Tile[] shift = new Tile[board.length];
			for(int o = 0; o <shift.length; o++){
				shift[o] = new Tile();
			}

			int cap = 0;
			for (int j = 0; j < board.length; j++){
				if(board[i][j].getValue() != 0) {shift[cap].setValue(board[i][j].getValue()); cap++;}
			}
			cap = 0;
			for(Tile val: shift){
				board[i][cap].setValue(val.getValue());
				cap++;
			}

			for (int j =0; j < board.length-1; j++) {//combines values;
				if(board[i][j].getValue() == board[i][j+1].getValue() ) {
					board[i][j].setValue(board[i][j].getValue() + board[i][j].getValue());
					board[i][j+1].setValue(0);
				}
			}

			//Making a shiftAgain Tile set
			Tile[] shiftAgain = new Tile[board.length];
			for(int p = 0; p <shiftAgain.length; p++){
				shiftAgain[p] = new Tile();
			}

			cap = 0;
			for (int j = 0;j<board.length;j++) {
				if(board[i][j].getValue() !=0) {shiftAgain[cap].setValue(board[i][j].getValue()); cap++;}
			}

			cap = 0;
			for(Tile val: shiftAgain) {
				board[i][cap].setValue(val.getValue());
				cap++;
			}
		}

		/*
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
		*/
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

	public boolean same(Board other) {

		if(Arrays.deepEquals(board,other.board)) {return true;}
		else {return false;}
	}


	public String toString(){
		/*
		for(int i = 0; i < board.length; i++){
			System.out.println();
			System.out.print("[ ");
			for(int j = 0; j <board[i].length; j++){
				System.out.print(board[i][j].toString() + " ");
			}
			System.out.print("]");
		}
		*/


		String view = "Score "+ score+"\nGame Size: "+gameSize+"\n";
		for(Tile[] i: board) {
			view = view + Arrays.toString(i)+"\n";
		}
		return view;
	}

	public static void main(String[] args){
		Board test = new Board(4);


	}
}