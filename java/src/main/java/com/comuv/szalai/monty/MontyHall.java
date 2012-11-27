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



	public MontyHall() {
		super();
		/*
		 * To random, or not to random - This is the question
		 * There is two way ahead 
		 * - leave constructor as untouched as possible and implement an init() method 
		 * - trick the constructor to get or create a Random
		 * 
		 * At first attempt I'll pick the first way
		 */
		theWinnerDoor = Random.nextInt(maxDoors) + 1;

		/* 
		 * With the following initialization we assure that only one
		 * of all doors will be the winner door.
		 */
		for (int i = 1; i <= maxDoors; i++)
			doors[i] = false;
		doors[theWinnerDoor] = true;
	}

	public int getSelectedDoor() {
		return selectedDoor;
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
		return selectedDoor;
	}
	
	private int dice(Random rand, int from, int to) {
		return rand.nextInt(to) + from;
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
		Random rand = new Random();
		run(rand);
	}

	public void run(Random rand) {
		//Player selects a door
		if (selectADoor(dice(rand, 1, maxDoors))){
			System.out.println("Player selected Door No." + getSelectedDoor());
			//Host opens a non-selected looser door
			int ld = chooseANotSelectedLooserDoor();
			System.out.println("Host opened Door No." + ld);
			System.out.println("The door opened by Host is obviously a " + evaluateDoor(ld));
			if (dice(rand, 1, 100) < 50) {
				playerCangesSelection();
				System.out.println("Player changed her mind.");
			}
			System.out.println("Now the door selected by Player is Door No." + getSelectedDoor());
			System.out.println("The door selected by Player is obviously a " + evaluateDoor(getSelectedDoor()));
		}
	}


}
