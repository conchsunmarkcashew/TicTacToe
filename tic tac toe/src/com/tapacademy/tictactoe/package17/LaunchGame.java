package com.tapacademy.tictactoe.package17;

import java.util.Random;
import java.util.Scanner;

class TicTacToe{
	
	static char[][] board; // creating referance as board of 2D character array!! static to access everywhere using classname
	
	public TicTacToe() //creating constructor because it should execute During object creation
	{
		board = new char[3][3]; // creating object of 3row's and 3colm's
		initBoard();
		//dispBoard();
		
	}
	
	void initBoard(){   // creating empty board
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[i].length;j++) {
				board[i][j]=' '; //initialization to empty 
			}
		}
	}
	
	static void dispBoard() {                  // Displaying the empty tic tac toe board
		System.out.println("-------------");
		for(int i=0;i<board.length;i++) {
			System.out.print("| ");     // first straight line or pipe
			for(int j=0;j<board[i].length;j++) {
				
				System.out.print(board[i][j] + " | "); // second (the next to or beside first line) straight line or pipe
				
			}
		System.out.println();
		System.out.println("-------------");
		}
	}
	
	static void placeMark(int row, int col, char mark) {             //placing marks i.e 'x' and 'o' in the board with the position i.e placeMark(0,0, 'x')
		board[row][col] = mark;
	}
	
	 boolean checkColWin(){  // checking for column win where row is fixed and column is changing so we use j variable to traverse column
		for(int j=0;j<=2;j++) {
			if(board[0][j] !=  ' ' && board[0][j] == board[1][j] && board[1][j] == board[2][j] ) {  // in condition we check whether the first character is empty or not
				return true;
			}
		}
		return false;
	}
	
	static boolean checkRowWin(){ // checking for row win where column is fixed and row is changing so we use i variable to traverse row
		for(int i=0;i<=2;i++) {
			if(board[i][0] !=  ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2] ) { // in condition we check whether the first character is empty or not
				return true;
			}
		}
		return false;
	}
	
	static boolean checkDiagWin() { // checking diagonal win and condition is hardcoded and i and j is fixed
		
		if(board[0][0] !=  ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]   // in condition we check whether the first character is empty or not
			|| board[0][2] !=  ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
			return true;
		}else {
			return false;
		}
	}
	
	boolean checkDraw() { // checking for draw when two player's where good and  no one win's
		
		for(int i=0;i<=2;i++) {
			for(int j=0;j<=2;j++) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}
}

abstract class Player{  // abstract because it is incomplete class and inside the class there is minimum one abstract method is there so class should be abstract
	
	String name;  //name of the player
	char mark;	// mark for the player
	
	abstract void makeMove();
	
	boolean isValidMove(int row, int col) {  // validating the move by checking whether board is empty or not
		if(row >=0 && row <=2 &&
				col >=0 && col <=2) {
			if(TicTacToe.board[row][col] == ' ') {
				return true;
			}
		}
		return false;
	}
	
}

// Using this class two player's can play tictactoe 
class HumanPlayer extends Player{
	
	
	
	HumanPlayer(String name, char mark){ //parameterised constructor while creating object we need to give the input
		this.name = name;  // to avoid shadowing problem we use this keyword
		this.mark = mark;
	}
	
	void makeMove() {  // to make the move we use this method
		
		Scanner scan = new Scanner(System.in);
		
		int row,col;
		do {
			System.out.println("Enter the row and column");  // taking row and column position as input
			row = scan.nextInt();
			col = scan.nextInt();
		}while(!(isValidMove(row,col)));  // as long as condition is !(false) = true it will execute it will validate the move and it don't want empty space because we need to give row and col position
		
		TicTacToe.placeMark(row,col,mark); // giving mark in row and col position
	}
	
}


// this class is made for playing tictactoe with AI when you want to play with AI or computer instead of human.
class AIPlayer extends Player{
	
	AIPlayer(String name, char mark){
		this.name = name;
		this.mark = mark;
		
	}
	
	void makeMove() {   // to make the move we use this method here inbulid methods are used to make move by AI.
		
		int row;
		int col;
		
		do {
			Random r = new Random(); // Random is build in class for which we created object and using that referance we access that class
			
			row = r.nextInt(3);
			col = r.nextInt(3);
			// random integer it will take where 3 is excluded from 0,1,2 position it will take for both row and col
		}while(!(isValidMove(row, col)));
		
		TicTacToe.placeMark(row,col,mark); // giving mark in row and col position
	}
	
}






public class LaunchGame {

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();  // object creation for tictactoe class and calling constructor during object creation
		
		HumanPlayer p1 = new HumanPlayer("Bob ", 'X');   // first player object creation and constructor call
		
		AIPlayer p2 = new AIPlayer("AI ", 'O'); // second player object creation and constructor call
		
		Player cp;  // creatin referance variable i.e pass by or assign referance operation
		
		cp =p1;
		
		while(true) {  // as long as true
			System.out.println(cp.name + " turn ");
			
			cp.makeMove();
			
			TicTacToe.dispBoard(); //caling board using class name because it is static method
			
			if(t.checkColWin() || TicTacToe.checkDiagWin() || TicTacToe.checkRowWin()) {  // checking for win  , we already created object of that class so we can use referance to call or class name for static methods to call one and the same.
				System.out.println(cp.name + " has Won ");  // if someone win then come out of loop so use break
				break;
			}
			else if(t.checkDraw()){
				System.out.println("Game is Draw");
				break;
			}
			else {
				if(cp == p1) {  // interchanging player's if player 1 than player 2 or if playre 2 than player 1
					cp = p2;
				}else {
					cp = p1;
				}
			}
		}
		
		
	}

}
