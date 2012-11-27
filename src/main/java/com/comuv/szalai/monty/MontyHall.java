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
 *  -- Q:what if there is two looser doors present?
 *  -- D:we will show the first found not selected looser door 
 * TODO - Host opens the door selected by Player (evaluation)
 * 
 */
package com.comuv.szalai.monty;

/**
 * @author ljszalai
 *
 */
public class MontyHall {
	
	public static final int maxDoors = 3;
	
	private boolean[] doors = new boolean[maxDoors + 1];
	private int theWinnerDoor = 0; 
	private int selectedDoor = 0;
	
	

	public MontyHall() {
		super();
		theWinnerDoor = 2;

		/* 
		 * With the following initialization we assure that only one
		 * of all doors will be the winner door.
		 */
		for (int i = 1; i <= maxDoors; i++)
			doors[i] = false;
		doors[theWinnerDoor] = true;
	}

	public boolean selectADoor(int i) {
		boolean result = false;
		if (i < doors.length && i > 0) {
			selectedDoor = i;
			result = true;
		}
		return result;
	}

	public boolean openADoor(int i) {
		if (i < doors.length && i > 0) {
			return doors[i];
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public int chooseANotSelectedLooserDoor() {
		// TODO Auto-generated method stub
		return 0;
	}

}
