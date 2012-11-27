/**
 * This is the first attempt for me to try TDD in a wild
 * MontyHall is a simple class attended to implement something
 * about I've understood about the legendary Monty Hall
 * paradoxon.
 * For details see this URL:
 * 		http://en.wikipedia.org/wiki/Monty_Hall_problem
 * 
 * Game rules (excerpt from the URL above):
 * Player is on a game show, and she's given the choice 
 * of three doors: Behind one door is a car (Player wins); 
 * behind the others, goats (Player looses). 
 * Player picks a door identified by a number, say No. 1, 
 * and the host, who knows what's behind the doors, 
 * opens another door, say No. 3, which has a goat. 
 * He then says to you, "Do you want to pick door No. 2?" 
 * Is it to your advantage to switch your choice?
 * 
 * Implemented features:
 *  - select a door and mark it to be opened
 *  - open a door and show content behind
 * Ongoing - Host opens a door which leads to loose
 * TODO - Host opens the door selected by Player
 * TODO - Player selects a door
 * 
 */
package com.comuv.szalai.monty;

/**
 * @author ljszalai
 *
 */
public class MontyHall {
	
	private boolean[] doors = new boolean[4];
	private int selectedDoor = 0;
	
	

	public MontyHall() {
		super();
		doors[1] = false;
		doors[2] = true;
		doors[3] = false;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean selectADoor(int i) {
		boolean result = false;
		if (i < doors.length && i > 0) {
			selectedDoor = i;
			result = true;
		}
		return result;
	}

	public String openADoor(int i) {
		String result = null;
		if (i < doors.length && i > 0) {
			if (doors[i]){
				result = "Win";
			} else {
				result = "Loose";
			}
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
		return result;
	}

}
