package com.comuv.szalai.monty.test;

import static org.junit.Assert.*;

import java.util.Random;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.comuv.szalai.monty.MontyHall;

public class MontyHallTest {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

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
		assertTrue ((result < maxDoors) && (result > 0)) ;
		assertTrue (result != selected);
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

}
