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
	static int maxRuns = 1000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int cw = 0; //Changed and won
		int cl = 0; //Changed and lost
		int kw = 0; //Kept and won
		int kl = 0; //Kept and lost
		for (int i = 0; i<maxRuns; i++) {
			MontyHall game = new MontyHall();
			game.init(null);
			game.setQuietMode(true);
			game.run();
			if (game.hasChangedMind()) {
				if (game.didPlayerWin()) {
					cw++;
				} else {
					cl++;
				}
			} else {
				if (game.didPlayerWin()) {
					kw++;
				} else {
					kl++;
				}
			}
		}
		System.out.println("Statistics");
		System.out.println("----------");
		System.out.println("Canged and won " + cw + " times.");
		System.out.println("Canged and lost " + cl + " times.");
		System.out.println("Kept and won " + kw + " times.");
		System.out.println("Kept and lost " + kl + " times.");
	}


}
