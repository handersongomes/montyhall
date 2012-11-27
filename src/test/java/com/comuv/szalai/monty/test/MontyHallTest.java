package com.comuv.szalai.monty.test;

import static org.junit.Assert.*;

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
	private int maxDoors = 3; 

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
		assertFalse (montyhall.selectADoor(4));
	}

	@Test
	public void openADoorTest_InBounds() {
		assertEquals (montyhall.openADoor(1), "Loose");
		assertEquals (montyhall.openADoor(2), "Win");
		assertEquals (montyhall.openADoor(3), "Loose");
	}
	
	@Test
	public void openADoorTest_OutOfLowerBound() {
		exception.expect(ArrayIndexOutOfBoundsException.class);
		montyhall.openADoor(0);
	}

	@Test
	public void openADoorTest_OutOfUpperBound() {
		exception.expect(ArrayIndexOutOfBoundsException.class);
		montyhall.openADoor(4);
	}
}
