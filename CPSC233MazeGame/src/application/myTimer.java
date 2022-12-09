package application;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.GameLogic;

/**
 * @author Jacky, Sami, Taye
 *
 */

public class myTimer extends AnimationTimer {
	
	private GameLogic newLogic;
	private GUIAnimationAppController newController;
	
	private static boolean updatedMinotaurImage = false;
	private static boolean updatedMinion0Image = false;
	private static boolean updatedMinion1Image = false;
	private static boolean updatedMinion2Image = false;
	Boolean minion0 = true;
	Boolean minion1 = true;
	Boolean minion2 = true;

	public myTimer(GameLogic aLogic, GUIAnimationAppController aController) {
		newLogic = aLogic;
		newController = aController;
	}
	
	@Override
	public void handle(long arg0) {
		if (arg0 % 2000 == 0) {
			if (newLogic.getMinion0().getStatus() == false) {
				if (updatedMinion0Image == false) newController.killMinion(0);
				updatedMinion0Image = true;
				minion0 = false;
			}
			if (newLogic.getMinion1().getStatus() == false) {
				if (updatedMinion1Image == false) newController.killMinion(1);
				updatedMinion1Image = true;
				minion1 = false;
			}
			if (newLogic.getMinion2().getStatus() == false) {
				if (updatedMinion2Image == false) newController.killMinion(2);
				updatedMinion2Image = true;
				minion2 = false;
			}
				if (newController.getMazeStarted() == true) {
					if (minion0) {
						newLogic.minionMove(newLogic.getMinion0());
						newController.updateMinion0();
					}
					if (minion1) {
						newLogic.minionMove(newLogic.getMinion1());
						newController.updateMinion1();
					}
					if (minion2) {
						newLogic.minionMove(newLogic.getMinion2());
						newController.updateMinion2();
					}
				}
			if (newLogic.getMonster().getStatus() == false) {
				if (updatedMinotaurImage == false) newController.killMonster();
				updatedMinotaurImage = true;
			}
			else {
			newLogic.monsterCheck();
			if (newController.getMazeStarted() == true) {
				newController.updateMonster();
				if (newLogic.getInCombat() == true) {
					this.stop();
					newController.combatView();
				}
			}
			}
			if (newLogic.getMonster().getSeesPlayer() == false && newLogic.getMonster().getHasSeenPlayer() == true) {
				newLogic.monsterMove(newLogic.getMonster());
			}
		}
		if (newLogic.getPlayer().getMove().equals("Quit")) { 
			newController.death();
			this.stop();
		}
	}
}