package application;
import java.util.Scanner;

import model.GameLogic;
/**
 * Class that creates a TextApplication instance which calls from other classes to create a working Maze runner game application.
 * @author Jacky, Sami, Taye 
 */
public class TextApplication extends GameLogic{

	/**
	 * This method initiates the beginning sequence of the game 
	 * with some text welcoming the user, prompting for any input to start, 
	 * then printing out the instructions and the map with the player and monster
	 * at their respective starting positions.
	 */
	@SuppressWarnings("resource")
	public void startTextGame() {
		// Gets starting coordinates of monster, player, and minions from the map instance and sets the locations of these entities.
		this.getMonster().setY(this.getMap().getMonsterStartingYLocation());
		this.getMonster().setX(this.getMap().getMonsterStartingXLocation());
		this.getPlayer().setY(this.getMap().getPlayerStartingYLocation());
		this.getPlayer().setX(this.getMap().getPlayerStartingXLocation());
		for (int i = 0 ; i < this.getMap().getMinionStartingLocations().size() ; i++) {
			if (i == 0) {
				this.getMinion0().setY(this.getMap().getMinionStartingLocations().get(i)[0]);
				this.getMinion0().setX(this.getMap().getMinionStartingLocations().get(i)[1]);
			}
			if (i == 1) {
				this.getMinion1().setY(this.getMap().getMinionStartingLocations().get(i)[0]);
				this.getMinion1().setX(this.getMap().getMinionStartingLocations().get(i)[1]);
			}
			if (i == 2) {
				this.getMinion2().setY(this.getMap().getMinionStartingLocations().get(i)[0]);
				this.getMinion2().setX(this.getMap().getMinionStartingLocations().get(i)[1]);
			}
		}
		
		Scanner userInput = new Scanner(System.in);
		System.out.println("Welcome to our maze!\nEvery input counts as an move, "
				+ "so tread carefully!\nPress enter to start");
		userInput.nextLine();
		System.out.println("You awaken in a dark hall, lit torches line the walls dimly giving light."
				+ " \nA deep bellow much like that of a bull, just very much larger, echoes off the stone. \nYou must escape."
				+ "Use the 'A' key to move to the left, the 'S' key to move down, \nthe 'D' key to move to the right,"
				+ " the 'W' key to move upwards, \nand 'Enter' to keep moving in the same direction. \nBeware those that lurk"
				+ " the halls, loot which riches you can, and use any tools \nyou can find to survive...");
		this.printPlayer();
		this.printMonster();
		this.getMap().printMap();
	}
	
	/**
	 * This method prompts the user to enter a direction to move and creates a listener which sets the player's move according
	 * to their keyboard input.
	 * 
	 * Does not return anything
	 */
	@SuppressWarnings("resource")
	public void action() {
		
		Scanner userAction = new Scanner(System.in);
		String action = "";
		System.out.println("Which direction would you like to move?\n(W = UP)\n(A = LEFT)\n(D = RIGHT)\n(S = DOWN)"
				+ "\n(Enter to continue moving in the same direction)\n(0 = EXIT PROGRAM)");
		action = userAction.nextLine();
		
		// If statements to gather user input.
		if (action.equalsIgnoreCase("W")) this.getPlayer().setMove("moveUp");
		if (action.equalsIgnoreCase("A")) this.getPlayer().setMove("moveLeft");
		if (action.equalsIgnoreCase("D")) this.getPlayer().setMove("moveRight");
		if (action.equalsIgnoreCase("S")) this.getPlayer().setMove("moveDown");
		if (action.equalsIgnoreCase("0")) this.getPlayer().setMove("quit");

	}
	
	/**
	 * Method which prints out text for when the player is killed by the monster.
	 */
	public void playerDeathStatement() {
		System.out.println("The Minotaur viciously tears your limbs from your body.\nYOU DIED.");
	}
	
	/**
	 * Method which prints out text for when the player is killed by a minion.
	 */
	public void playerDeathStatementToMinion() {
		System.out.println("A minion summoned by the Minotaur has brutally mutilated you. \nYOU DIED.");
	}

	/**
	 * Method which prints out text for when the monster is killed by the player.
	 */
	public void monsterDeathStatement() {
		System.out.println("You have victoriously slain the mighty Minotaur, " +
				"the great Cretan Bull of the Labyrinth! You hold high its horn"
				+ " to which you claim as your trophy!");
	}
	
	/**
	 * Method which prints out text for when the player kills a minion.
	 */
	public void minionDeathStatement() {	
		System.out.println("You successfully killed a minion, continue your quest to escape the maze!");
	}
	
	/**
	 * Method which prints lore text when obtaining the sword.
	 */
	public void obtainSwordStatement() {
		System.out.println("You found the Sword of the legendary hero Theseus! "
				+ "\nHis most celebrated adventure was "
				+ "\nhis slaying of the fearsome Minotaur of the Cretan king Minos!");
	}
	
	/**
	 * Method which prints out information for the player inventory.
	 */
	public void printInventory() {
		System.out.println("Move Number: " + this.getPlayer().getMoveCounter() + "\tCoinpouch: " + this.getPlayer().getCoinPouch() +"\nInventory: ");
		if (this.getPlayer().getHasSword() == true) System.out.println("Sword Of Theseus\n");
	}
	/**
	 * This method runs the text-based game.
	 */
	public void run() {
		this.startTextGame();
		
		//This loop keeps the game running until the player reaches the exit location or User decides to quit.
		while (this.getPlayer().getY() != 21 || this.getPlayer().getX() != 37) {
			this.playerMove();
			//Ends the game if player dies or if user inputs to quit.
			if (this.getPlayer().getMove().equals("Quit")) {
				System.out.println("Goodbye!"); 
				return;
			}
			//Applies the player update, runs the Monster tracking system, and prints the new Map and U.I.
			this.printPlayer();
			this.getPlayer().setMoveCounter();
			if (getPlayer().getHasSword() == true) {
				this.getMonster().setStatus(false);
				this.monsterDeathStatement();
			}
			

			//Monster move.
			this.minionMove(this.getMinion0());
			this.minionMove(this.getMinion1());
			this.minionMove(this.getMinion2());
			this.monsterCheck();
			if (this.getMonster().getSeesPlayer() == false && this.getMonster().getHasSeenPlayer() == true) {
				this.monsterMove(this.getMonster());
			}
			
			//Updates and prints the new visual map and inventory.
			this.getMap().printMap();
			this.printInventory();
		}
		//Prints out the ending results and the ending score.
		System.out.println("Congrats, you won in " + this.getPlayer().getMoveCounter() + " moves!\nYour score was: " 
				+ ((this.getPlayer().getCoinPouch() * 100.0) / Math.abs(this.getPlayer().getMoveCounter() - 303.0))*100.0);
}

	/**
	 * Main method.
	 * @param args
	 */
	public static void main(String[] args) {
		TextApplication newApp = new TextApplication();
		newApp.run();
	}
}