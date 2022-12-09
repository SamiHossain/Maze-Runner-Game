package model;
/**
 * A class to contain the coordinates and movement of the monster.
 * @author Jacky, Sami, Taye
 * 
 */
public class Monster extends Entity {
	
	//Instance variable using true to denote the status of the monster as alive.
	private Boolean status = true;
	private Boolean hasSeenPlayer = false;
	private Boolean seesPlayer = false;

	/**
	 * Getter method which returns if the monster is dead or alive
	 * @return status
	 */
	public Boolean getStatus() {
		return status;
	}
	
	/**
	 * Setter method which sets the status of the monster as alive or dead.
	 * Does not return anything.
	 * @param vitality
	 */
	public void setStatus(Boolean vitality) {
		this.status = vitality;
	}

	/**
	 * @return the hasSeenPlayer
	 */
	public Boolean getHasSeenPlayer() {
		return hasSeenPlayer;
	}

	/**
	 * @param hasSeenPlayer the hasSeenPlayer to set
	 */
	public void setHasSeenPlayer(Boolean hasSeenPlayer) {
		this.hasSeenPlayer = hasSeenPlayer;
	}	
	
	/**
	 * @return the seesPlayer
	 */
	public Boolean getSeesPlayer() {
		return seesPlayer;
	}

	/**
	 * @param seesPlayer the seesPlayer to set
	 */
	public void setSeesPlayer(Boolean seesPlayer) {
		this.seesPlayer = seesPlayer;
	}
}
