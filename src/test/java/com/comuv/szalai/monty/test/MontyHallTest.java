package com.comuv.szalai.monty.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.comuv.szalai.monty.MontyHall;

public class MontyHallTest {

	private MontyHall montyhall = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		montyhall = new MontyHall();
	}

	@Test
	public void selectADoorTest() {
		assertTrue (montyhall.selectADoor(1));
		assertTrue (montyhall.selectADoor(2));
		assertTrue (montyhall.selectADoor(3));
		assertFalse (montyhall.selectADoor(4));
	}

	@Test
	public void openADoorTest() {
		boolean thrown = false;
		assertEquals (montyhall.openADoor(1), "Loose");
		assertEquals (montyhall.openADoor(2), "Win");
		assertEquals (montyhall.openADoor(3), "Loose");
		try {
			montyhall.openADoor(4); 
		} catch (ArrayIndexOutOfBoundsException e) {
			thrown = true;
		}
		assertTrue (thrown);
	}
}
