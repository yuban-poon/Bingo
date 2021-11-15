
/**
 * author PAN, Rubin
 * studentNum 210426767
 * class SE1B
 */

import java.util.Scanner;

public class Bingo {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] arg) {

		int[][] player1Card = { 
			{ 24, 2, 8, 1, 25 }, 
			{ 12, 16, 7, 17, 15 }, 
			{ 5, 6, 20, 19, 13 }, 
			{ 14, 23, 22, 4, 3 },
			{ 10, 18, 11, 21, 9 } 
		};

		int[][] player2Card = { 
			{ 24, 21, 17, 15, 6 }, 
			{ 10, 3, 8, 18, 20 }, 
			{ 14, 7, 16, 12, 5 }, 
			{ 25, 23, 13, 19, 11 },
			{ 22, 4, 9, 1, 2 } 
		};

		int[] input = new int[25];// A array of callNum
		int callNum = 0;// the number of call
		int index = 0;// the index of input
		int[][] newPlayer1Card, newPlayer2Card;// the new card number of each player after modify
		boolean player1Bingo = false, player2Bingo = false;// judge is or not Win(Bingo)

		//display player's card
		System.out.println("Player1's Card:");
		displayCardNum(player1Card);
		System.out.println("Player2's Card:");
		displayCardNum(player2Card);

		while(true) {
			System.out.print("Game Host Call (0 to exit): ");
			callNum = sc.nextInt();

			if (callNum >= 1 && callNum <= 25) {
				// judge callNum is or does not exist
				if (isOrNotExist(input, callNum)) {
					System.out.println("the number " + callNum + " is repeated, please call again!");
					continue;
				} else {
					input[index] = callNum;
					index++;
				}

				// modify the called Number to 0
				newPlayer1Card = modifyCardNum(player1Card, callNum);
				newPlayer2Card = modifyCardNum(player2Card, callNum);

				// check bingo or not
				player1Bingo = checkBingo(newPlayer1Card);
				player2Bingo = checkBingo(newPlayer2Card);
	

				// display player's card
				System.out.println();
				System.out.println("Player1's Card:");
				displayCardNum(player1Card);
				System.out.println("Player2's Card:");
				displayCardNum(player2Card);

				//print bingo
				if (player1Bingo) {
					System.out.println("Player1 Bingo!");
				}
				if (player2Bingo) {
					System.out.println("Player2 Bingo!");
				}
				if (player1Bingo || player2Bingo) {
					break;
				}

			}else if (callNum == 0){
				break;
			}else {
				System.out.println("The number must be between 1 to 25, please call again!");
			}
		};
	}

	/**
	 * display player's card number
	 *
	 * @param arr A 2D array of player's card number
	 */
	public static void displayCardNum(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0)
					System.out.printf("%4s", "XX");
				else
					System.out.printf("%4s", arr[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * judge callNum is or does not exist
	 *
	 * @param arr A array of callNum
	 * @param num the callNum input from console
	 * @return
	 */
	public static boolean isOrNotExist(int[] arr, int num) {
		for (int x : arr) {
			if (x == num) {
				return true;
			}
		}
		return false;
	}

	/**
	 * modify the called Number to 0
	 *
	 * @param arr A 2D array of player's card number
	 * @param num the callNum input from console
	 * @return
	 */
	public static int[][] modifyCardNum(int[][] arr, int num) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == num) {
					arr[i][j] = 0;
				}
			}
		}
		return arr;
	}

	/**
	 * check bingo or not
	 *
	 * @param arr A 2D array of the new card number
	 * @return
	 */
	public static boolean checkBingo(int[][] arr) {
		// The number of 0 value in the array
		int nRow = 0, nCulumn = 0, nDiagonalLeft = 0, nDiagonalRight = 0;

		for (int i = 0; i < arr.length; i++) {
			//check row
			nRow = 0;
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j] == 0) {
					nRow++;
				} else {
					break;
				}
			}
			if (nRow == arr[i].length) {
				return true;
			}

			//check culumn
			nCulumn = 0;
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[j][i] == 0) {
					nCulumn++;
				} else {
					break;
				}
				if (nCulumn == arr.length) {
					return true;
				}
			}
		}

		//check left diagonal
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][i] == 0) {
				nDiagonalLeft++;
			} else {
				break;
			}
			if (nDiagonalLeft == arr.length) {
				return true;
			}
		}

		//check right diagonal
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][arr[i].length - i - 1] == 0) {
				nDiagonalRight++;
			} else {
				break;
			}
			if (nDiagonalRight == arr.length) {
				return true;
			}
		}
		return false;
	}
}