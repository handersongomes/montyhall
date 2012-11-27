/**
 * 
 */
package com.comuv.szalai.monty;

import java.util.Random;

/**
 * @author szalail
 *
 */
public class MontyHallRunner {
	
	Random rand = new Random();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MontyHall game = new MontyHall();
		game.run();
	}
	

}
