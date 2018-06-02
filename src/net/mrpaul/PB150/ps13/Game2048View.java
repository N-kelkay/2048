package net.mrpaul.PB150.ps13;

import java.util.Scanner;

public class Game2048View {
	private Board currentBoard;

	public  Game2048View(Board brd){
		this.currentBoard = brd;
	}

	/**
	 * updates our board instance variable with the most current version of the board
	 * @param brd
	 */
	public void setBoard(Board brd){
		this.currentBoard = brd;
	}

	public Board getBoard(){
		return currentBoard;
	}

	/**
	 * Prompt the user to type in
	 * "Next action: [W]Up, [A]Left, [S]Down, [D]Right, [Q]uit, [R]eset: "
	 * and return the appropriate UserAction enum value
	 * @return
	 */
	public static UserAction getUserAction(){
		System.out.println("Next action: [W]Up, [A]Left, [S]Down, [D]Right, [Q]uit, [R]eset: ");

		Scanner input = new Scanner(System.in);
		String action = input.nextLine();

		action = action.toUpperCase();

		switch (action){
			case "W": return UserAction.UP;
			case "A": return UserAction.LEFT;
			case "S": return UserAction.DOWN;
			case "D": return UserAction.RIGHT;
			case "Q": return UserAction.QUIT;
			case "R": return UserAction.RESET;
			default: System.out.println("Invalid, try again"); return UserAction.INVALID;
		}
	}

	/**
	 * output the current sate of the board to the console
	 */
	public void updateDisplay(){
		this.currentBoard.toString();
	}
}
