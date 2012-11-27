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
 *  - Host opens a door which leads to loose
 *  -- Q:what if there is two looser doors present?
 *  -- D:we will show the first found not selected looser door 
 */
package com.comuv.szalai.monty;

import java.util.Random;

/**
 * @author ljszalai
 *
 */
public class MontyHall {

	public static final int maxDoors = 3;

	private boolean[] doors = new boolean[maxDoors + 1];
	private int theWinnerDoor = 0; 
	private int selectedDoor = 0;
	private int otherDoor = 0;
	private Random rand = null;
	
	private boolean changedMind = false;
	private boolean playerWon = false;


	public MontyHall() {
		super();
	}

	public void init(Random rand0) {
		// working around the Random Seed Issue
		if (rand0 != null) {
			this.rand = rand0;
		} else {
			this.rand = new Random();
		}
		
		theWinnerDoor = rand.nextInt(maxDoors) + 1;

		/* 
		 * With the following initialization we assure that only one
		 * of all doors will be the winner door.
		 */
		for (int i = 1; i <= maxDoors; i++)
			doors[i] = false;
		doors[theWinnerDoor] = true;
	}

	//Getter
	public int getSelectedDoor() {
		return selectedDoor;
	}

	//Getter
	public boolean hasChangedMind() {
		return changedMind;
	}

	//Getter
	public boolean didPlayerWin() {
		return playerWon;
	}

	public boolean selectADoor(int i) {
		boolean result = false;
		if (i < doors.length && i > 0) {
			selectedDoor = i;
			result = true;
		} else {
			//result = false;
		}
		return result;
	}

	public boolean openADoor(int i) {
		boolean result = doors[i]; 
		if (i < doors.length && i > 0) {
			return result; 
		} else {
			throw new ArrayIndexOutOfBoundsException();
		}
	}

	public int chooseANotSelectedLooserDoor() {
		int result = 0;
		int i = 1;
		otherDoor = 0;
		if (selectedDoor != 0) {
			while ((i <= maxDoors) && (result == 0)) {
				if ((i != selectedDoor) && (!openADoor(i)))
					result = i;
				i++;
			}
			int o = 1;
			while ((o <= maxDoors) && (otherDoor == 0)) {
				if ((o != selectedDoor) && (o != result))
					otherDoor = o;
				o++;
			}
		} else {
			result = -1;
		}
		return result;
	}

	public int playerCangesSelection() {
		int tmp = 0;
		tmp = selectedDoor; selectedDoor = otherDoor; otherDoor = tmp;
		changedMind = true;
		return selectedDoor;
	}
	
	private int dice(int from, int to) {
		Random rand1 = null;
		if (this.rand != null) {
			rand1 = this.rand;
		} else {
			rand1 = new Random();
		}
		return rand1.nextInt(to) + from;
	}

	private String evaluateDoor(int no) {
		String result = "Wrong";
		if (doors[no]) {
			result = "Winner";
		} else {
			result = "Looser";
		}
		return result;
	}
	
	public void run() {
		//printConfiguration();
		printMsg("");
		//Player selects a door
		if (selectADoor(dice(1, maxDoors))){
			printMsg("Player selected Door No." + getSelectedDoor());
			//Host opens a non-selected looser door
			int ld = chooseANotSelectedLooserDoor();
			printMsg("Host opened Door No." + ld);
			printMsg("The door opened by Host is obviously a " + evaluateDoor(ld));
			int chm = dice(1, 100);
			printMsg("Player changed her mind. (" + chm + ").");
			if (chm < 50) {
				playerCangesSelection();
				printMsg("Player changed her mind.");
			}
			printMsg("Now the door selected by Player is Door No." + getSelectedDoor());
			printMsg("The door selected by Player is obviously a " + evaluateDoor(getSelectedDoor()));
			playerWon = doors[getSelectedDoor()];
		}
	}

	public void printMsg(String msg) {
		System.out.println(msg);
	}

	public void printConfiguration() {
		System.out.print("Configuration details:");
		for (int i = 1; i <= maxDoors; i++) {
			System.out.print(" Door No." + i + ", " + doors[i]); 
		}
	}


}
