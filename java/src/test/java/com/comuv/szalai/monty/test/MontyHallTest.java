package com.comuv.szalai.monty.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.comuv.szalai.monty.MontyHall;

public class MontyHallTest {

/*
 * For debug:
	String runningTestName = new Object(){}.getClass().getEnclosingMethod().getName();
	System.out.println("Test running: " + runningTestName);
 * Got from:
 * 	http://stackoverflow.com/questions/442747/getting-the-name-of-the-current-executing-method 
 */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	int smi = 1; //Index for getMethodName

	private MontyHall montyhall = null;
	private int maxDoors = MontyHall.maxDoors; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		montyhall = new MontyHall();
	}

	@Test
	public void getSelectedDoorTest() {
		Random rand = new Random();
		int selected = rand.nextInt(maxDoors) + 1;
		assertTrue (montyhall.selectADoor(selected));
		assertTrue (isDoorInRange(montyhall.getSelectedDoor())) ;
		assertTrue (montyhall.getSelectedDoor() == selected) ;
	}


	@Test
	public void selectADoorTest() {
		assertFalse (montyhall.selectADoor(0));
		for (int i = 1; i <= maxDoors; i++)
			assertTrue (montyhall.selectADoor(i));
		assertFalse (montyhall.selectADoor(maxDoors + 1));
	}

	@Test
	public void openADoorTest_InBounds() {
		assertFalse (montyhall.openADoor(1));
		assertTrue (montyhall.openADoor(2));
		assertFalse (montyhall.openADoor(3));
	}

	@Test
	public void openADoorTest_OutOfLowerBound() {
		exception.expect(ArrayIndexOutOfBoundsException.class);
		montyhall.openADoor(0);
	}

	@Test
	public void openADoorTest_OutOfUpperBound() {
		exception.expect(ArrayIndexOutOfBoundsException.class);
		montyhall.openADoor(maxDoors + 1);
	}


	@Test
	public void chooseANotSelectedLooserDoorTest() {
		Random rand = new Random();
		int selected = rand.nextInt(maxDoors) + 1;
		assertTrue (montyhall.selectADoor(selected));
		int result = montyhall.chooseANotSelectedLooserDoor();
		assertTrue (isDoorInRange(result)) ;
		assertTrue (result != selected);
	}

	@Test
	public void chooseANotSelectedLooserDoorTest_NoSelectionWasMade() {
		MontyHall untouchedMontyHall = new MontyHall();
		int result = untouchedMontyHall.chooseANotSelectedLooserDoor();
		assertFalse (isDoorInRange(result)) ;
		assertTrue (result == -1);
	}

	@Test
	public void openADoorTest_OnlyOneDoorIsWinner() {
		int itMustBeOne = 0;
		for (int i = 1; i <= maxDoors; i++) {
			if (montyhall.openADoor(i)) 
				itMustBeOne++;
		}
		assertEquals (1, itMustBeOne);
	}

	@Test
	public void playerChangesSelectionTest() {
		Random rand = new Random();
		int originalSelection = rand.nextInt(maxDoors) + 1;
		assertTrue (montyhall.selectADoor(originalSelection));
		int doorOpenedByHost = montyhall.chooseANotSelectedLooserDoor();
		assertTrue (isDoorInRange(doorOpenedByHost)) ;
		assertTrue (doorOpenedByHost != originalSelection);
		int finalSelection = montyhall.playerCangesSelection();
		assertTrue (isDoorInRange(finalSelection));
		assertFalse (finalSelection == originalSelection);
		assertFalse (finalSelection == doorOpenedByHost);
	}

	@Test
	public void montyHallRunTest() {
		MontyHall game = new MontyHall();
		game.run();
	}

	private boolean isDoorInRange(int door) {
		return (door <= maxDoors) && (door > 0);
	}

}
