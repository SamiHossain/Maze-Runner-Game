package model;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads a map for the game from a text file in the form of an array that updates with player and monster moves. 
 * Using the printMap() function, this map can be displayed to the console.
 * @author Jacky, Sami, Taye
 *
 */
public class Map {
	
	// Instance variable to generate map.
	// Game map is 23 x 39 ( y(rows) by x(columns));
	private char[][] mapFrame;
	private static int columnCounter = 0;
	private static int rowCounter = 0;
	private int monsterStartingYLocation;
	private int monsterStartingXLocation;
	private int playerStartingYLocation;
	private int playerStartingXLocation;
	private ArrayList<int[]> minionStartingLocations = new ArrayList<int[]>();
	
	/**
	 * Constructor method which creates an instance of a map by reading from a text file, initiating a char array of 
	 * appropriate size, then copies the text file to the array as a map.
	 */
	public Map()   {
		try {
			readUsingScanner();
			mapFrame = new char[getRowCounter()][getColumnCounter()];
			addTextFile();
		} 
		catch (FileNotFoundException e) {
			System.out.print("Map file not found.");
		}
	}

	/**
	 * Getter method which returns the current mapFrame array.
	 * @return mapFrame
	 */
	public char[][] getMapFrame() {
		return mapFrame;
	}
	
	/**
	 * Setter method which takes in an array as a parameter and sets that array to mapFrame. 
	 * Does not return anything.
	 * @param mapFrame
	 */
	public void setMapFrame(char[][] mapFrame) {
		this.mapFrame = mapFrame;
	}
	
	/**
	 * Getter method which takes in a y-coordinate and an x-coordinate and returns the character at that location in mapFrame.
	 * @param newY
	 * @param newX
	 * @return mapFrame[newY][newX]
	 */
	public char getChar(int newY, int newX) {
		return mapFrame[newY][newX];
	}
	
	/**
	 * Setter method which takes in a y-coordinate, a x-coordinate, and a character, setting the value of that location in mapFrame as the character.
	 * Does not return anything.
	 * @param yCoord
	 * @param xCoord
	 * @param entity
	 */
	public void setChar(int yCoord, int xCoord, char entity) {
		mapFrame[yCoord][xCoord] = entity;
	}
	
	/**
	 * Setter method which takes in a y-coordinate and a x-coordinate, setting the value of that location in mapFrame as an empty character.
	 * Does not return anything.
	 * @param yCoord
	 * @param xCoord
	 */
	public void deleteChar(int yCoord, int xCoord) {
		mapFrame[yCoord][xCoord] = ' ';
	}
	
	/**
	 * Prints out a string visualization of the map to the console.
	 * Does not return anything.
	 */
	public void printMap() {
		for (int i = 0; i < 23; i++) {
			System.out.print(getMapFrame()[i]);
			System.out.println();
		}
	}	
	
	/**
	 * Method that initially reads the text file containing the map and obtains the dimensions needed for the char array.
	 * @throws FileNotFoundException
	 */
	public void readUsingScanner() throws FileNotFoundException {
		Scanner reader = new Scanner(new File("src/model/newMap.txt"));
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			setColumnCounter(line.length());
			setRowCounter(getRowCounter() + 1);	
		}
	}
	
	/**
	 * Method that reads the text file the second time around and adds it to the char array line by line.
	 * @throws FileNotFoundException
	 */
	public void addTextFile() throws FileNotFoundException {
		Scanner reader = new Scanner(new File("src/model/newMap.txt"));
		setRowCounter(0);
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			for (int i = 0; i < line.length(); i++) {
				mapFrame[getRowCounter()][i] = line.charAt(i);
				if (line.charAt(i) == 'M') {
					setMonsterStartingYLocation(getRowCounter());
					setMonsterStartingXLocation(i);
				}
				else if (line.charAt(i) == 'S') {
					setPlayerStartingYLocation(getRowCounter());
					setPlayerStartingXLocation(i);
				}
				else if (line.charAt(i) == 'm') {
					int[] minion = {getRowCounter(), i};
					getMinionStartingLocations().add(minion);
				}
			}
			setRowCounter(getRowCounter() + 1);
		}
	}

	/**
	 * @return the monsterStartingYLocation
	 */
	public int getMonsterStartingYLocation() {
		return monsterStartingYLocation;
	}

	/**
	 * @param monsterStartingYLocation the monsterStartingYLocation to set
	 */
	public void setMonsterStartingYLocation(int monsterStartingYLocation) {
		this.monsterStartingYLocation = monsterStartingYLocation;
	}

	/**
	 * @return the monsterStartingXLocation
	 */
	public int getMonsterStartingXLocation() {
		return monsterStartingXLocation;
	}

	/**
	 * @param monsterStartingXLocation the monsterStartingXLocation to set
	 */
	public void setMonsterStartingXLocation(int monsterStartingXLocation) {
		this.monsterStartingXLocation = monsterStartingXLocation;
	}

	/**
	 * @return the playerStartingYLocation
	 */
	public int getPlayerStartingYLocation() {
		return playerStartingYLocation;
	}

	/**
	 * @param playerStartingYLocation the playerStartingYLocation to set
	 */
	public void setPlayerStartingYLocation(int playerStartingYLocation) {
		this.playerStartingYLocation = playerStartingYLocation;
	}

	/**
	 * @return the playerStartingXLocation
	 */
	public int getPlayerStartingXLocation() {
		return playerStartingXLocation;
	}

	/**
	 * @param playerStartingXLocation the playerStartingXLocation to set
	 */
	public void setPlayerStartingXLocation(int playerStartingXLocation) {
		this.playerStartingXLocation = playerStartingXLocation;
	}

	/**
	 * @return the minionStartingLocations
	 */
	public ArrayList<int[]> getMinionStartingLocations() {
		return minionStartingLocations;
	}

	/**
	 * @param minionStartingLocations the minionStartingLocations to set
	 */
	public void setMinionStartingLocations(ArrayList<int[]> minionStartingLocations) {
		this.minionStartingLocations = minionStartingLocations;
	}

	/**
	 * @return the columnCounter
	 */
	public static int getColumnCounter() {
		return columnCounter;
	}

	/**
	 * @param columnCounter the columnCounter to set
	 */
	public static void setColumnCounter(int columnCounter) {
		Map.columnCounter = columnCounter;
	}

	/**
	 * @return the rowCounter
	 */
	public static int getRowCounter() {
		return rowCounter;
	}

	/**
	 * @param rowCounter the rowCounter to set
	 */
	public static void setRowCounter(int rowCounter) {
		Map.rowCounter = rowCounter;
	}	
}