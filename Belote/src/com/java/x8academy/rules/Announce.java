/**
 * 
 */
package com.java.x8academy.rules;

/**
 * @author Vasilen Polimenov
 *
 */
public class Announce {

	public Announce(Trump trump, Modifier modifier) {
		this.trump = trump;
		this.modifier = modifier;
	}

	public Announce() {
		this.trump = Trump.NoAnnounce;
		this.modifier = Modifier.Normal;
	}

	private Trump trump = null;
	private Modifier modifier = null;
	private int announceWeight = 0;

	public Trump getTrump() {
		return trump;
	}

	public void setTrump(Trump trump) {
		this.trump = trump;
	}

	public Modifier getModifier() {
		return modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;

	}

	public int getAnnounceWeight() {
		
		if (trump.toString().equals("Clubs")) {

			announceWeight = 1;
		} else if (trump.toString().equals("Dyamonds")) {

			announceWeight = 2;
		} else if (trump.toString().equals("Hearts")) {

			announceWeight = 3;
		} else if (trump.toString().equals("Spade")) {

			announceWeight = 4;
		} else if (trump.toString().equals("NoTrump")) {
			announceWeight = 5;
		} else if (trump.toString().equals("AllTrumps")) {
			announceWeight = 6;
		} else {
			announceWeight = 0;
		}

		return announceWeight;
	}

}
