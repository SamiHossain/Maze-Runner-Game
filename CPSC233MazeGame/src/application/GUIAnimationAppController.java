package application;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import model.GameLogic;
import model.Map;

/**
 * @author Jacky, Sami, Taye
 *
 */

public class GUIAnimationAppController {

	@FXML
	private GridPane mainMenuWindow;

	@FXML
	private GridPane newGrid;

	@FXML
	private BorderPane gameWindow;

	@FXML
	private AnchorPane combatWindow;

	@FXML
	private VBox instructionsWindow;

	@FXML
	private VBox creditsWindow;

	@FXML
	private Button startGameButton;

	@FXML
	private Button instructionsButton;

	@FXML
	private Button returnToMenuButton;

	@FXML
	private Button creditsButton;

	@FXML
	private Label coinBox;

	@FXML
	private Label moveCounterLabel;

	@FXML
	private Label textInterface;

	@FXML
	private Label combatInterface;

	@FXML
	private GridPane inventorySword;

	@FXML
	private ImageView combatMinotaur;

	@FXML
	private ImageView combatPlayer;

	private ImageView minionImage0 = new ImageView();
	private ImageView minionImage1 = new ImageView();
	private ImageView minionImage2 = new ImageView();
	private ImageView deadMinionImage0 = new ImageView();
	private ImageView deadMinionImage1 = new ImageView();
	private ImageView deadMinionImage2 = new ImageView();
	private ImageView minotaurImage = new ImageView();
	private ImageView deadMonsterImage = new ImageView();
	private ImageView playerImage = new ImageView();




	Image Minotaur = new Image(getClass().getResourceAsStream("Minotaur.png"));
	Image deadMonster = new Image(getClass().getResourceAsStream("deadMonster.png"));
	Image Player = new Image(getClass().getResourceAsStream("player.png"));
	Image Title = new Image(getClass().getResourceAsStream("gameTitle.png"));
	Image playerCombatImage = new Image(getClass().getResourceAsStream("combatPlayer.png"));
	Image minotaurCombatImage = new Image(getClass().getResourceAsStream("combatMinotaur.png"));
	Image minionImage = new Image(getClass().getResourceAsStream("minion.png"));
	Image deadMinion = new Image(getClass().getResourceAsStream("deadMinion.png"));

	

	
	
	private Boolean mazeStarted = false;
	private Boolean monsterDead = false;
	private Boolean minion0Dead = false;
	private Boolean minion1Dead = false;
	private Boolean minion2Dead = false;
	private Boolean swordObtained = false;
	private Boolean gameRunning = false;
	private Boolean hasFoughtOnce = false;
	private int oldMinotaurYLocation;
	private int oldMinotaurXLocation;

	private int oldPlayerYLocation;
	private int oldPlayerXLocation;
	Game Game1 = new Game();

	GameLogic newGame = GameLogic.getInstance();
	myTimer timer = new myTimer(newGame, this);

	@FXML
	public void initializeGame() {
		if (mainMenuWindow.isVisible()) mainMenuWindow.setVisible(false);
		else if (instructionsWindow.isVisible()) instructionsWindow.setVisible(false);
		else if (creditsWindow.isVisible()) creditsWindow.setVisible(false);
		gameWindow.setVisible(true);
		if (gameRunning == false) {
			this.createNewGame();
			gameRunning = true;
			textInterface.setText(("You awaken in a dark hall, lit torches line the walls dimly giving light."
					+ " \nA deep bellow much like that of a bull, just very much larger, echoes off the stone.\nYou must escape.\nBeware those that lurk the halls, "
					+ "loot which riches you can, and use any tools you can find to survive..."));
		}
	}

	@FXML
	public void viewInstructions() {
		mainMenuWindow.setVisible(false);
		instructionsWindow.setVisible(true);
	}

	@FXML
	public void combatView() {
		newGrid.setVisible(false);
		combatWindow.setVisible(true);
		getCombatInterface().setText("PRESS Z");
		gameWindow.setCenter(combatWindow);
		if (getHasFoughtOnce() == false) {
			setHasFoughtOnce(true);
			combatPlayer.setImage(playerCombatImage);
			combatMinotaur.setImage(minotaurCombatImage);
		}

	}

	@FXML
	public void endCombat() {
		timer.start();
		Game.playerCombatSequence = "";
		combatWindow.setVisible(false);
		newGrid.setVisible(true);
		gameWindow.setCenter(newGrid);
	}

	@FXML
	public void returnToMenu() {
		if (instructionsWindow.isVisible()) {
			instructionsWindow.setVisible(false);	
		}
		else if (creditsWindow.isVisible()) creditsWindow.setVisible(false);
		else if (gameWindow.isVisible()) gameWindow.setVisible(false);
		mainMenuWindow.setVisible(true);
	}

	@FXML
	public void goToCredits() {
		if (mainMenuWindow.isVisible()) mainMenuWindow.setVisible(false);
		creditsWindow.setVisible(true);
	}

	@FXML
	public void createNewGame() {
		AudioClip startingMusic = new AudioClip(this.getClass().getResource("Music.mp3").toString());
		startingMusic.setVolume(0.05);
		startingMusic.play();
		setMazeStarted(true);
		Image Coin = new Image(getClass().getResourceAsStream("coin.png"));
		Image Wall = new Image(getClass().getResourceAsStream("Wall.png"));
		Image Sword = new Image(getClass().getResourceAsStream("sword.png"));
		Image Exit = new Image(getClass().getResourceAsStream("Exit.png"));
		int minionCounter = 0;
		combatPlayer.setImage(playerCombatImage);
		combatMinotaur.setImage(minotaurCombatImage);
		for (int i = 0; i < Map.getRowCounter(); i++) {
			for (int index = 0; index <Map.getColumnCounter(); index++) {

				ImageView landScape = new ImageView();

				if (newGame.getMap().getChar(i, index) == 'M') {
					newGame.getMonster().setY(i);
					newGame.getMonster().setX(index);
					minotaurImage.setFitWidth(20);
					minotaurImage.setFitHeight(20);
					minotaurImage.setImage(Minotaur);
					getNewGrid().add(minotaurImage, index, i);
				}

				else if (newGame.getMap().getChar(i, index) == 'm') {
					if (minionCounter == 0) {
						newGame.getMinion0().setY(i);
						newGame.getMinion0().setX(index);
						minionImage0.setFitWidth(20);
						minionImage0.setFitHeight(20);
						minionImage0.setImage(minionImage);
						getNewGrid().add(minionImage0, index, i);
						minionCounter ++;

					}
					else if (minionCounter == 1) {
						newGame.getMinion1().setY(i);
						newGame.getMinion1().setX(index);
						minionImage1.setFitWidth(20);
						minionImage1.setFitHeight(20);
						minionImage1.setImage(minionImage);
						getNewGrid().add(minionImage1, index, i);
						minionCounter ++;
					}
					else if (minionCounter == 2) {
						newGame.getMinion2().setY(i);
						newGame.getMinion2().setX(index);
						minionImage2.setFitWidth(20);
						minionImage2.setFitHeight(20);
						minionImage2.setImage(minionImage);
						getNewGrid().add(minionImage2, index, i);
					}


				}
				else if (newGame.getMap().getChar(i, index) == 'S') {
					newGame.getPlayer().setY(i);
					newGame.getPlayer().setX(index);
					getPlayerImage().setFitWidth(20);
					getPlayerImage().setFitHeight(20);
					getPlayerImage().setImage(Player);
					getNewGrid().add(getPlayerImage(), index, i);
				}
				else if (newGame.getMap().getChar(i, index) == '$') {
					landScape.setFitHeight(15);
					landScape.setFitWidth(15);
					landScape.setImage(Coin);
					landScape.setViewOrder(3);
					getNewGrid().add(landScape, index, i);
				}
				else if (newGame.getMap().getChar(i, index) == '#') {
					landScape.setFitHeight(28);
					landScape.setFitWidth(25);
					landScape.setRotate(90);;
					landScape.setImage(Wall);
					getNewGrid().add(landScape, index, i);
				}
				else if (newGame.getMap().getChar(i, index) == '?') {
					landScape.setFitWidth(20);
					landScape.setFitHeight(20);
					landScape.setViewOrder(3);
					landScape.setImage(Sword);
					getNewGrid().add(landScape, index, i);
				}
				else if (newGame.getMap().getChar(i, index) == 'X') {
					landScape.setFitWidth(25);
					landScape.setFitHeight(27);
					landScape.setImage(Exit);
					getNewGrid().add(landScape, index, i);
				}	
			}
		}
		gameWindow.setCenter(getNewGrid());
	}

	@FXML 
	public void deleteCoin() {
		ImageView beGoneCoin = new ImageView();
		Image Path = new Image(getClass().getResourceAsStream("path.png"));
		beGoneCoin.setFitHeight(20);
		beGoneCoin.setFitWidth(20);
		beGoneCoin.setImage(Path);
		newGrid.add(beGoneCoin, newGame.getPlayer().getX(), newGame.getPlayer().getY());
		beGoneCoin.setViewOrder(0);
		coinBox.setText("" + newGame.getPlayer().getCoinPouch());
	}

	@FXML
	public void deleteSword() {
		if (!swordObtained) {
			ImageView beGoneSword = new ImageView();
			Image Path = new Image(getClass().getResourceAsStream("path.png"));
			beGoneSword.setFitHeight(20);
			beGoneSword.setFitWidth(20);
			beGoneSword.setImage(Path);
			beGoneSword.setViewOrder(2);
			newGrid.add(beGoneSword, newGame.getPlayer().getX(), newGame.getPlayer().getY());
			this.obtainSwordStatement();
			swordObtained = true;
		}
		ImageView swordInInventory = new ImageView();
		Image Sword = new Image(getClass().getResourceAsStream("sword2.png"));
		swordInInventory.setFitHeight(105);
		swordInInventory.setFitWidth(105);
		swordInInventory.setRotate(15);
		swordInInventory.setImage(Sword);
		inventorySword.add(swordInInventory, 0, 0);;
	}

	public void updateMonster() {
		getNewGrid().getChildren().removeAll(minotaurImage);
		getNewGrid().add(minotaurImage, newGame.getMonster().getX(), newGame.getMonster().getY());
	}

	public void updatePlayer() {
		getNewGrid().getChildren().removeAll(getPlayerImage());
		getNewGrid().add(getPlayerImage(), newGame.getPlayer().getX(), newGame.getPlayer().getY());
	}

	public void updateMinion0() {
		getNewGrid().getChildren().removeAll(minionImage0);
		getNewGrid().add(minionImage0, newGame.getMinion0().getX(), newGame.getMinion0().getY());
	}

	public void updateMinion1() {
		getNewGrid().getChildren().removeAll(minionImage1);
		getNewGrid().add(minionImage1, newGame.getMinion1().getX(), newGame.getMinion1().getY());
	}
	public void updateMinion2() {
		getNewGrid().getChildren().removeAll(minionImage2);
		getNewGrid().add(minionImage2, newGame.getMinion2().getX(), newGame.getMinion2().getY());
	}

	@FXML
	public void killMonster() {
		newGame.setInCombat(true);
		combatView();
		getNewGrid().getChildren().removeAll(minotaurImage);
		deadMonsterImage.setFitHeight(20);
		deadMonsterImage.setFitWidth(20);
		deadMonsterImage.setImage(deadMonster);
		getNewGrid().add(deadMonsterImage, newGame.getMonster().getX(), newGame.getMonster().getY());
		deadMonsterImage.setViewOrder(1);
		if (!monsterDead) {
			this.monsterDeathStatement();
			monsterDead = true;
		}
	}
	public void killMinion(int minionNumber) {
		if (minionNumber == 0) {
			getNewGrid().getChildren().removeAll(minionImage0);
			deadMinionImage0.setFitWidth(20);
			deadMinionImage0.setFitHeight(20);
			deadMinionImage0.setImage(deadMinion);
			getNewGrid().add(deadMinionImage0, newGame.getMinion0().getX(), newGame.getMinion0().getY());
			if (!minion0Dead) {
				this.minionDeathStatement();
				minion0Dead = true;
			}
		}
		if (minionNumber == 1) {
			getNewGrid().getChildren().removeAll(minionImage1);
			deadMinionImage1.setFitWidth(20);
			deadMinionImage1.setFitHeight(20);
			deadMinionImage1.setImage(deadMinion);
			getNewGrid().add(deadMinionImage1, newGame.getMinion1().getX(), newGame.getMinion1().getY());
			if (!minion1Dead) {
				this.minionDeathStatement();
				minion1Dead = true;
			}
		}
		if (minionNumber == 2) {
			getNewGrid().getChildren().removeAll(minionImage2);
			deadMinionImage2.setFitWidth(20);
			deadMinionImage2.setFitHeight(20);
			deadMinionImage2.setImage(deadMinion);
			getNewGrid().add(deadMinionImage2, newGame.getMinion2().getX(), newGame.getMinion2().getY());
			if (!minion2Dead) {
				this.minionDeathStatement();
				minion2Dead = true;
			}
		}
	}

	@FXML
	public void death() {
		ImageView deathScreen = new ImageView();
		Image deathImage = new Image(getClass().getResourceAsStream("death.png"));
		deathScreen.setImage(deathImage);
		newGrid.setVisible(false);
		gameWindow.setCenter(deathScreen);
		this.playerDeathStatement();
	}

	@FXML
	public void victory() {
		ImageView victoryScreen = new ImageView();
		Image victoryImage = new Image(getClass().getResourceAsStream("victory.png"));
		victoryScreen.setFitHeight(533);
		victoryScreen.setFitWidth(1080);
		victoryScreen.setImage(victoryImage);
		newGrid.setVisible(false);
		gameWindow.setCenter(victoryScreen);
		textInterface.setText("Congrats, you won in " + newGame.getPlayer().getMoveCounter() + " moves!\nYour score was: " 
				+ String.format("%.2f", ((newGame.getPlayer().getCoinPouch() * 100.0) / Math.abs(newGame.getPlayer().getMoveCounter() - 303.0))*100.0));
	}

	@FXML
	public void increaseMoveCounter() {
		moveCounterLabel.setText("" + newGame.getPlayer().getMoveCounter());
	}

	public void playerDeathStatement() {
		textInterface.setText("The Minotaur viciously tears your limbs from your body.");
	}

	public void playerDeathStatementToMinion() {
		textInterface.setText("A minion summoned by the Minotaur has brutally mutilated you. \nYOU DIED.");
	}

	public void monsterDeathStatement() {
		textInterface.setText("You have victoriously slain the mighty Minotaur, " +
				"the great Cretan Bull of the Labyrinth! \nYou hold high its horn"
				+ " to which you claim as your trophy!");
	}

	public void minionDeathStatement() {	
		textInterface.setText("You successfully killed a minion, continue your quest to escape the maze!");
	}


	public void obtainSwordStatement() {
		textInterface.setText("You found the Sword of the legendary hero Theseus! "
				+ "\nHis most celebrated adventure was "
				+ "his slaying of the fearsome Minotaur of the Cretan king Minos!");
	}

	public void printInventory() {
		// TODO Auto-generated method stub

	}


	public void action() {
		// TODO Auto-generated method stub

	}

	public Boolean getMazeStarted() {
		return mazeStarted;
	}

	public void setMazeStarted(Boolean mazeStarted) {
		this.mazeStarted = mazeStarted;
	}

	/**
	 * @return the newGrid
	 */
	public GridPane getNewGrid() {
		return newGrid;
	}

	/**

	 * @return the playerImage
	 */
	public ImageView getPlayerImage() {
		return playerImage;
	}

	/**
	 * @param playerImage the playerImage to set
	 */
	public void setPlayerImage(ImageView playerImage) {
		this.playerImage = playerImage;
	}

	/**
	 * 
	 * @return the hasFoughtOnce
	 */
	public Boolean getHasFoughtOnce() {
		return hasFoughtOnce;
	}

	/**
	 * @param hasFoughtOnce the hasFoughtOnce to set
	 */
	public void setHasFoughtOnce(Boolean hasFoughtOnce) {
		this.hasFoughtOnce = hasFoughtOnce;
	}

	/**
	 * @return the combatInterface
	 */
	public Label getCombatInterface() {
		return combatInterface;
	}

	/**
	 * @param combatInterface the combatInterface to set
	 */
	public void setCombatInterface(Label combatInterface) {
		this.combatInterface = combatInterface;

	}
}





