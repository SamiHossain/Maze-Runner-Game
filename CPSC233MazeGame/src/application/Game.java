package application;

import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

/**
 *Class which contains the main method for the game GUI. This class initiates the game by loading the first scene.
 * @author Jacky, Sami, Taye
 *
 */

public class Game extends Application{

	private GameLogic newGame = GameLogic.getInstance();
	private Stage primaryStage;
	private Boolean validInput = true;

	String combatSequence = "ZXCOYLNMQE";
	static String playerCombatSequence = "";


	private void setupView() {
		FXMLLoader loader = new FXMLLoader();
		Scene scene;

		try {
			Parent setupView = (Parent) loader.load(new FileInputStream("src/application/GUIAnimationAppView.fxml"));
			GUIAnimationAppController appController = loader.getController();
			final myTimer newTimer = new myTimer(newGame, appController);
			scene = new Scene(setupView);
			newTimer.start();
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					switch (event.getCode()) {
					case W: 
						newGame.getPlayer().setMove("moveUp");
						break;
					case S:  
						newGame.getPlayer().setMove("moveDown");
						break;
					case A:  
						newGame.getPlayer().setMove("moveLeft");
						break;
					case D: 
						newGame.getPlayer().setMove("moveRight");
						break;
					case Z:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "Z";
							appController.getCombatInterface().setText("PRESS X");
						}
						break;
					case X:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "X";
							appController.getCombatInterface().setText("PRESS C");
						}
						break;
					case C:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "C";
							appController.getCombatInterface().setText("PRESS O");
						}
						break;
					case O:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "O";
							appController.getCombatInterface().setText("PRESS Y");
						}
						break;
					case Y:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "Y";
							appController.getCombatInterface().setText("PRESS L");
						}
						break;
					case L:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "L";
							appController.getCombatInterface().setText("PRESS N");
						}
						break;
					case N:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "N";
							appController.getCombatInterface().setText("PRESS M");
						}
						break;
					case M:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "M";
							appController.getCombatInterface().setText("PRESS Q");
						}
						break;
					case Q:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "Q";
							appController.getCombatInterface().setText("PRESS E");
						}
						break;
					case E:
						if (newGame.getInCombat() == true) {
							playerCombatSequence += "E";
						}
						break;
					default:
						validInput = false;
						break;
					}
					if (newGame.getPlayer().getMove() == "moveLeft") appController.getPlayerImage().setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
					else if(newGame.getPlayer().getMove() == "moveRight") appController.getPlayerImage().setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
					if (validInput) {

					if (playerCombatSequence.length() == combatSequence.length()) {
						if (playerCombatSequence.equals(combatSequence)) {
							newGame.setInCombat(false);
							newGame.getMonster().setStatus(false);
							appController.endCombat();
						}
						else appController.death();
					}
					
					if (validInput && newGame.getInCombat() == false) {

						int oldPouchValue = newGame.getPlayer().getCoinPouch();
						newGame.playerMove();
						newGame.getPlayer().setMoveCounter();
						if (newGame.getPlayer().getMove().equals("Quit")) {
							appController.death();
							newTimer.stop();
						}
						if (newGame.getPlayer().getHasSword()) appController.deleteSword();
						if (oldPouchValue != newGame.getPlayer().getCoinPouch()) appController.deleteCoin();
						if (newGame.getMap().getChar(newGame.getPlayer().getY(),newGame.getPlayer().getX()) == 'X') {
							appController.victory();
						}
						newGame.printPlayer();
						appController.increaseMoveCounter();
						appController.updatePlayer();
						

					}
					validInput = true;
				}
				}});
			
			
			primaryStage.setScene(scene);
			primaryStage.show();		
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.print("Error, fxml file not found.");
		} 
		catch (IOException e) {
			System.out.print("Error, there has been an IO error. ");
			e.printStackTrace();
		}
	}



	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		setupView();
	}

	public static void main(String[] args) throws FileNotFoundException {
		launch(args);
	}
}

