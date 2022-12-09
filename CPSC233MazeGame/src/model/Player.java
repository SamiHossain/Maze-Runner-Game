package model;
/**
 * A class that creates an instance of a player that represents the user and can be used to navigate through the map.
 * @author Jacky, Sami, Taye
 */
public class Player extends Entity{
	
	// Instance variables.
	private int coinPouch = 0;
	private Boolean hasSword = false;
	private int moveCounter = 0;

	/**
	 * Getter method which returns the number of coins.
	 * @return coinPouch
	 */
	public int getCoinPouch() {
		return coinPouch;
	}
	
	/**
	 * Setter method which takes in an integer and sets the coinPouch value to that integer.
	 * @param coinPouch
	 */
	public void setCoinPouch(int coinPouch) {
		this.coinPouch = coinPouch;
	}
	
	/**
	 * Getter method which returns true or false depending if you have the sword.
	 * @return hasSword
	 */
	public Boolean getHasSword() {
		return hasSword;
	}
	
	/**
	 * Setter method just sets hasSword as true or false.
	 * Does not return anything.
	 * @param hasSword
	 */
	public void setHasSword(Boolean hasSword) {
		this.hasSword = hasSword;
	}
	
	/**
	 * Getter method which retrieves the number of moves taken by the user during the instance of the game.
	 * @return moveCounter
	 */
	public int getMoveCounter() {
		return moveCounter;
	}
	
	/**
	 * Setter method which incrementally increases the number of moves taken by the user during the instance of the game.
	 */
	public void setMoveCounter() {
		moveCounter++;
	}
	
	/**
	 *  Method to add one coin to the coin pouch.
	 *  Does not return anything.
	 */
	public void pickUpCoin() {
		int coins = this.getCoinPouch();
		coins++;
		this.setCoinPouch(coins);
	}

}
