package model;
import java.util.Scanner;

/**
 * Parent class which creates an instance with variable and methods shared between the player class and
 * the monster class.
 * @author jacky.tran1
 */
public class Entity {
	
	// Variables
	private int x;
	private int y;
	private String move = "moveDown";
	
	/**
	 * Getter method which returns the current x-location of the entity.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Setter method which takes in an integer and sets the x-location of the entity to that value.
	 * Does not return anything.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Getter method which returns the current y-location of the entity.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Setter method which takes in an integer and sets the y-location of the entity to that value.
	 * Does not return anything.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Getter method which returns a string of the current direction that entity will move in.
	 * @return move
	 */
	public String getMove() {
		return this.move;
	}
	
	/**
	 * Setter method which takes in one of five valid strings and sets the direction the entity will move in as that string.
	 * Does not return anything.
	 * @param move
	 */
	public void setMove(String move) {
		this.move = move;
	}
	
	/**
	 *  Method to determine where to move the entity.
	 *  Does not return anything.
	 */
	
	@SuppressWarnings("resource")
	public void action() {

		// Get user input
		Scanner userInput = new Scanner(System.in);
		String action = "";
		System.out.println("Which direction would you like to move?\n(W = UP)\n(A = LEFT)\n(D = RIGHT)\n(S = DOWN)\n(Enter to continue moving in the same direction)\n(0 = EXIT PROGRAM)");
		action = userInput.nextLine();
		// If statements to gather user input.
		if (action.equalsIgnoreCase("W")) move = "moveUp";
		if (action.equalsIgnoreCase("A")) move = "moveLeft";
		if (action.equalsIgnoreCase("D")) move = "moveRight";
		if (action.equalsIgnoreCase("S")) move = "moveDown";
		if (action.equalsIgnoreCase("0")) move = "Quit";
	}
	
	/**
	 * Method to move the entity up if the string "W" is chosen in the action method.
	 * This method subtracts one from the current y-location of the entity.
	 * Does not return anything.
	 */
	public void moveUp(){
		int previousYValue = this.getY();
		int newYValue = previousYValue - 1;
		this.setY(newYValue);
	}
	
	/** 
	 * Method to move the entity down if "S" is selected in the action method.
	 * This method adds one from the current y-location of the entity.
	 * Does not return anything.
	 */
	public void moveDown() {
		int previousYValue = this.getY();
		int newYValue = previousYValue + 1;
		this.setY(newYValue);
	}
		
	/**
	 *  Method to move the entity left if "S" is selected in the action method.
	 *  This method subtracts one from the current x-location of the entity.
	 *  Does not return anything.
	 */
	public void moveLeft() {
		int previousXValue = this.getX();
		int newXValue = previousXValue - 1;
		this.setX(newXValue);
	}
	
	/** 
	 * Method to move the entity right if "S" is selected in the action method.
	 * This method adds one from the current x-location of the entity.
	 * Does not return anything.
	 */
	public void moveRight() {
		int previousXValue = this.getX();
		int newXValue = previousXValue + 1;
		this.setX(newXValue);
	}
}
