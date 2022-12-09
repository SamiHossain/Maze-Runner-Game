package model;
/**
 * A class to contain the coordinates and movement of the the minions.
 * @author Jacky, Sami, Taye
 * 
 */
public class Minion extends Entity {
	
	private Boolean status = true;

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
}
