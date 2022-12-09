package model;

/**
 * This class contains the underlying logic that both the text-based and graphical user interface versions of our game rely on.
 * This class is also a singleton instance so that it can be updated by Game.java, GUIAnimationAppController.java, and myTimer.java.
 * 
 * @author Jacky, Sami, Taye
 *
 */

public class GameLogic {

	// THE CONSTRUCTOR FOR THIS SINGLETON INSTANCE CANNOT BE MADE PRIVATE AS TEXTAPPLICATION.JAVA RELIES ON THE PUBLIC CONTRUCTOR 
	// SINCE IT EXTENDS GAMELOGIC.

	//Instance variables to create new map and store player, monster, and minion data.
	private Map newMap = new Map();
	private Player player = new Player();
	private Monster minotaur = new Monster();
	private Minion minion0 = new Minion();
	private Minion minion1 = new Minion();
	private Minion minion2 = new Minion();
	private static GameLogic singleton;
	private Boolean inCombat = false;
	

	/**
	 * @return the inCombat
	 */
	public Boolean getInCombat() {
		return inCombat;
	}

	/**
	 * @param inCombat the inCombat to set
	 */
	public void setInCombat(Boolean inCombat) {
		this.inCombat = inCombat;
	}

	/**
	 * Get the single instance of Factory.
	 * @return the single instance of factory.
	 */
	public static GameLogic getInstance() {
		if (singleton == null) {
			singleton = new GameLogic();
		}
		return singleton;
	}

	/**
	 * Getter method for player.
	 * @return the player instance.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Getter method for monster.
	 * @return the monster instance
	 */
	public Monster getMonster() {
		return minotaur;
	}

	/**
	 * Getter method for map.
	 * @return the map instance.
	 */
	public Map getMap() {
		return newMap;
	}

	/**
	 * Getter method for minion0.
	 * @return the minion0 instance.
	 */
	public Minion getMinion0() {
		return minion0;
	}

	/**
	 * Getter method for minion1.
	 * @return the minion0 instance.
	 */
	public Minion getMinion1() {
		return minion1;
	}

	/**
	 * Getter method for minion2.
	 * @return the minion2 instance.
	 */
	public Minion getMinion2() {
		return minion2;
	}

	/**
	 * This method finds the current y and x location of the player
	 * and uses them to represent an index location on the Map .
	 * Prints the '@' character at that location.
	 */
	public void printPlayer() {
		int playerXLocation = player.getX();
		int playerYLocation = player.getY();
		newMap.setChar(playerYLocation, playerXLocation, '@');
	}

	/**
	 * This method finds the current y and x location of the monster
	 * and uses them to represent an index location on the Map .
	 * Prints the 'M' character at that location.
	 */
	public void printMonster() {
		int minotaurXLocation = minotaur.getX();
		int minotaurYLocation = minotaur.getY();
		newMap.setChar(minotaurYLocation, minotaurXLocation, 'M');
	}

	/**
	 * This method finds the current y and x location of the minion
	 * passed as an argument and uses them to represent an index 
	 * location on the map. 
	 * Prints the 'm' character at that location.
	 * @param minion
	 */
	public void printMinion(Minion minion) {
		int minionXLocation = minion.getX();
		int minionYLocation = minion.getY();
		newMap.setChar(minionYLocation, minionXLocation, 'm');
	}

	/**
	 * This method deletes the char representation of the player '@'
	 * from the Map at it's corresponding x and y coordinate, 
	 * leaving it as a blank char ' '.
	 */
	public void deletePreviousPlayer() {
		int playerXLocation = player.getX();
		int playerYLocation = player.getY();
		newMap.deleteChar(playerYLocation, playerXLocation);
	}

	/**
	 * This method deletes the char representation of the monster 'M'
	 * from the Map at it's corresponding x and y coordinate, 
	 * leaving it as a blank char ' '.
	 */
	public void deletePreviousMonster() {
		int minotaurXLocation = minotaur.getX();
		int minotaurYLocation = minotaur.getY();
		newMap.deleteChar(minotaurYLocation, minotaurXLocation);
	}

	/**
	 * This method deletes the char representation of the minion 'm'
	 * from the Map at it's corresponding x and y coordinate, 
	 * leaving it as a blank char ' '.
	 */
	public void deletePreviousMinion(Minion minion) {
		int minionXLocation = minion.getX();
		int minionYLocation = minion.getY();
		newMap.deleteChar(minionYLocation, minionXLocation);
	}

	/**
	 * This is a helper method which is called in playerMoveUp(), playerMoveDown(), playerMoveRight(), and playerMOveLeft().
	 * The helper method allows the player to change their coordinate location based upon the string argument given, which
	 * is different for each movement direction.
	 * @param intendedDirection
	 */
	public void playerMove(String intendedDirection) {
		// If statements which update the player's coordinates based upon argument given by playerMove methods
		if (intendedDirection.equals("up")) player.moveUp();
		else if (intendedDirection.equals("down")) player.moveDown();
		else if (intendedDirection.equals("left")) player.moveLeft();
		else if (intendedDirection.equals("right")) player.moveRight();
		//If the next space in the up direction is the character '$' representing money, it increases the CoinPouch count.
		if (newMap.getChar(player.getY(), player.getX()) == '$') player.pickUpCoin();
		//If the next space in the up direction is the character '?' representing the sword, the character picks it up allowing 
		//them to kill the Minotaur.
		if (newMap.getChar(player.getY(), player.getX()) == '?') {
			player.setHasSword(true);
			this.obtainSwordStatement();
		}
		//If the next move in the up direction is an invalid move, moves the player back to it's original position.
		if (newMap.getChar(player.getY(), player.getX()) == '#' ) {
			if (intendedDirection.equals("up")) player.moveDown();
			else if (intendedDirection.equals("down")) player.moveUp();
			else if (intendedDirection.equals("left")) player.moveRight();
			else if (intendedDirection.equals("right")) player.moveLeft();
		}
		//If the player encounters the Minotaur, the program checks to see if the player has the sword to kill the Minotaur. 
		//Otherwise the player dies and the game ends.
		if (newMap.getChar(player.getY(), player.getX()) == 'M') {
			if (player.getHasSword() == true) {
				
				setInCombat(true);			

			}
			else {
				this.printMonster();
				this.playerDeathStatement();
				player.setMove("Quit");
			}
		}
		// If the player encounters a minion, the status of the corresponding minion will be updated to false if the player 
		//has the sword. 
		if (newMap.getChar(player.getY(), player.getX()) == 'm') {
			if (player.getHasSword() == true) {

				if (minion0.getY() == player.getY() && minion0.getX() == player.getX())	{
					minion0.setStatus(false);
				}
				else if (minion1.getY() == player.getY() && minion1.getX() == player.getX())	{
					minion1.setStatus(false);
				}
				else if (minion2.getY() == player.getY() && minion2.getX() == player.getX())	{
					minion2.setStatus(false);
				}
				this.minionDeathStatement();
				
				setInCombat(true);
				minotaur.setStatus(false);
				this.monsterDeathStatement();
			}
			// If the player does not have the sword, the map is updated and the death statement is run.
			else {
				if (minion0.getY() == player.getY() && minion0.getX() == player.getX())	{
					this.printMinion(minion0);
					this.playerDeathStatementToMinion();
					player.setMove("Quit");
				}
				else if (minion1.getY() == player.getY() && minion1.getX() == player.getX())	{
					minion1.setStatus(false);
					this.printMinion(minion1);
					this.playerDeathStatementToMinion();
					player.setMove("Quit");
				}
				else if (minion2.getY() == player.getY() && minion2.getX() == player.getX())	{
					minion2.setStatus(false);
					this.printMinion(minion2);
					this.playerDeathStatementToMinion();
					player.setMove("Quit");
				}
			}
		}
	}

	/**
	 * Allows user to move in the up direction.
	 */
	public void playerMoveUp() {
		playerMove("up");
	}

	/**
	 * Allows user to move in the down direction.
	 */
	public void playerMoveDown() {
		playerMove("down");
	}

	/**
	 * Allows user to move in the right direction.
	 */
	public void playerMoveRight() {

		playerMove("right");
	}

	/**
	 * Allows user to move in the left direction.
	 */
	public void playerMoveLeft() {

		playerMove("left");


	}

	/**
	 *Methods that need to be changed in their classes
	 */
	public void playerDeathStatement() {
	}

	public void playerDeathStatementToMinion() {	
	}

	public void monsterDeathStatement() {
	}

	public void minionDeathStatement() {	
	}

	public void obtainSwordStatement() {
	}

	public void printInventory() {
	}
	
	public void action() {
	}


	/**
	 * This method is the monster tracking system. This allows for the Monster object to actively search for the player in 
	 * it's line of sight (straight line left and right). Should the player move into it's line of sight, it will
	 * actively move towards the player until the player moves out of sight or if the monster moves onto the same
	 * space as the player, initiating combat. If the player has found the sword, they will kill the monster.
	 * Otherwise, the player is killed and the game ends.
	 */
	public void monsterCheck() {
		if (minotaur.getStatus() == true) {	
			//Set an origin for the 'x' location representing the minotaur's physical location.
			int xOrigin = minotaur.getX();
			int yOrigin = minotaur.getY();
			int loopCounter = 0;
			// Loop which iterates up to four times so that the minotaur can check for the player in all four directions
			while (loopCounter < 4) {
				this.getMonster().setSeesPlayer(false);
				//This allows for the Minotaur to keep analyzing each space to it's left until it reaches a 'wall' (#).
				while (newMap.getChar(minotaur.getY(), minotaur.getX()) != '#') {
					// If statements which move the minotaur in different directions depending on how many times the loop
					// has been run.
					if (loopCounter == 0) minotaur.moveLeft();
					else if (loopCounter == 1) minotaur.moveRight();
					else if (loopCounter == 2) minotaur.moveUp();
					else if (loopCounter == 3) minotaur.moveDown();
					//If the Minotaur sees the player within a space to it's left before a 'wall', this function allows 
					//for it to move 1 space towards the player.
					if (newMap.getChar(minotaur.getY(), minotaur.getX()) == '@') {
						this.getMonster().setSeesPlayer(true);
						minotaur.setHasSeenPlayer(true);
						// If statements which reset the minotaur's location differently depending on how many times the loop
						// has been run.
						if (loopCounter == 0 || loopCounter == 1) minotaur.setX(xOrigin);
						else if (loopCounter == 2 || loopCounter == 3) minotaur.setY(yOrigin);
						this.deletePreviousMonster();
						// If statements which move the minotaur in different directions depending on how many times the loop
						// has been run.
						if (loopCounter == 0) minotaur.moveLeft();
						else if (loopCounter == 1) minotaur.moveRight();
						else if (loopCounter == 2) minotaur.moveUp();
						else if (loopCounter == 3) minotaur.moveDown();
						//If the Minotaur reaches the player, the player dies if the player has not yet obtained the sword. Game ends.
						if (newMap.getChar(minotaur.getY(), minotaur.getX()) == '@' && player.getHasSword() == false) {
							this.printMonster();
							this.playerDeathStatement();
							player.setMove("Quit");
						}
						//If the Minotaur reaches the player, the player slays the Minotaur if the player has obtained the sword 
						//and the game continues. 
						else if (newMap.getChar(minotaur.getY(), minotaur.getX()) == '@' && player.getHasSword() == true) {
							setInCombat(true);
						}
						else this.printMonster();
						break;
					} 
				}
				//If the Minotaur has not detected the player in the left direction, resets it's location.
				if (this.getMonster().getSeesPlayer() == false) {
					// If statements which reset the minotaur's location differently depending on how many times the loop
					// has been run.
					if (loopCounter == 0 || loopCounter == 1) minotaur.setX(xOrigin);
					else if (loopCounter == 2 || loopCounter == 3) minotaur.setY(yOrigin);
				}
				else return;
				loopCounter ++;
			}
		}
	}
	
	/** 
	 * This method causes a monster to move in random directions throughout the halls of the maze through use of a random
	 * number generator.
	 * @param monsterType, which is a monster type
	 */
	public void monsterMove(Monster monsterType) {
		Boolean monsterMoved = false;
		Boolean invalidMove = false;
		this.deletePreviousMonster();
		// List of characters that monster cannot move to so attempts to move there will not count as a move
		char[] staticCharacters = {'#', 'X', '$', '?'};
		
		// While loop which does not end until a valid monster move occurs.
		while(!monsterMoved) {
			int intRandom = (int) Math.floor(Math.random()*(4-1+1)+1);
			// If statements which move the monster in all four directions depending on the random number that was generated.
			if (intRandom == 1) {
				invalidMove = false;
				monsterType.moveLeft();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(monsterType.getY(), monsterType.getX()) == staticCharacters[index]) {
						monsterType.moveRight();
						invalidMove = true;

					}
					else if (index == 3 && !invalidMove) monsterMoved = true;
				}
			}

			if (intRandom == 2) {
				invalidMove = false;
				monsterType.moveDown();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(monsterType.getY(), monsterType.getX()) == staticCharacters[index]) {
						monsterType.moveUp();
						invalidMove = true;


					}
					else if (index == 3 && !invalidMove) monsterMoved = true;
				}
			}
			if (intRandom == 3) {
				invalidMove = false;
				monsterType.moveRight();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(monsterType.getY(), monsterType.getX()) == staticCharacters[index]) {
						monsterType.moveLeft();
						invalidMove = true;
					}
					else if (index == 3 && !invalidMove) monsterMoved = true;
				}
			}
			if (intRandom == 4) {
				invalidMove = false;
				monsterType.moveUp();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(monsterType.getY(), monsterType.getX()) == staticCharacters[index]) {
						monsterType.moveDown();
						invalidMove = true;

					}
					else if (index == 3 && !invalidMove) monsterMoved = true;
				}
			}
		}
		// New location of the monster is updated onto the map.
		this.printMonster();
	}
	
	/**
	 * This method causes a minion to move in random directions throughout the halls of the maze through use of a random
	 * number generator. This method also handles minion combat with the player.
	 * @param minionType, which is a specific minion instance
	 * 
	 * Does not return anything.
	 */
	public void minionMove(Minion minionType) {
		Boolean minionMoved = false;
		Boolean invalidMove = false;
		// List of characters that monster cannot move to so attempts to move there will not count as a move
		char[] staticCharacters = {'#', 'X', '$', '?'};
		this.deletePreviousMinion(minionType);
		// While loop which does not end until a valid monster move occurs.
		while(!minionMoved) {
			int intRandom = (int) Math.floor(Math.random()*(4-1+1)+1);
			// If statements which move the monster in all four directions depending on the random number that was generated.
			if (intRandom == 1) {
				invalidMove = false;
				minionType.moveLeft();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(minionType.getY(), minionType.getX()) == staticCharacters[index]) {
						minionType.moveRight();
						invalidMove = true;
					}
					else if (index == 3 && !invalidMove) minionMoved = true;
				}
			}
			if (intRandom == 2) {
				invalidMove = false;
				minionType.moveDown();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(minionType.getY(), minionType.getX()) == staticCharacters[index]) {
						minionType.moveUp();
						invalidMove = true;
					}
					else if (index == 3 && !invalidMove) minionMoved = true;
				}
			}
			if (intRandom == 3) {
				invalidMove = false;
				minionType.moveRight();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(minionType.getY(), minionType.getX()) == staticCharacters[index]) {
						minionType.moveLeft();
						invalidMove = true;
					}
					else if (index == 3 && !invalidMove) minionMoved = true;
				}
			}
			if (intRandom == 4) {
				invalidMove = false;
				minionType.moveUp();
				for (int index = 0 ; index < staticCharacters.length ; index ++) {
					if (newMap.getChar(minionType.getY(), minionType.getX()) == staticCharacters[index]) {
						minionType.moveDown();
						invalidMove = true;
					}
					else if (index == 3 && !invalidMove) minionMoved = true;
				}
			}
		}
		// If the minion moves onto the same location as the player, the minion will kill that player if the 
		// Player does not have the sword.
		if (newMap.getChar(minionType.getY(), minionType.getX()) == '@' && player.getHasSword() == false) {
			this.printMinion(minionType);
			this.playerDeathStatementToMinion();
			player.setMove("Quit");
		}
		// If the player does have the sword, the minion status is set to false and the death statement is run.
		else if (newMap.getChar(minionType.getY(), minionType.getX()) == '@' && player.getHasSword() == true) {
			minionType.setStatus(false);
			this.minionDeathStatement();
		}
		// New location of the monster is updated onto the map.
		else this.printMinion(minionType);
	}

	/**
	 * This method handles the coordinate location movement of the player based upon what the desired player move
	 * is set to.
	 * Does not return anything.
	 */
	public void playerMove() {
		if (player.getMove() != "Quit") this.action();
		this.deletePreviousPlayer();
		//Allows user to move in the up direction.
		if (player.getMove().equals("moveUp")) this.playerMoveUp();
		//Same as above, but in the down direction.
		if (player.getMove().equals("moveDown")) this.playerMoveDown();
		//Same as above, but in the right direction.
		if (player.getMove().equals("moveRight")) this.playerMoveRight();
		//Same as above but in the left direction.
		if (player.getMove().equals("moveLeft")) this.playerMoveLeft();
		//Ends the game.
		if (player.getMove().equals("Quit")) {
			return;
		}	
	}
}


