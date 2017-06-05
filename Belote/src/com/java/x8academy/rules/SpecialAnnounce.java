/**
 * 
 */
package com.java.x8academy.rules;

/**
 * @author Vasilen Polimenov
 *
 */
public enum SpecialAnnounce {
	Belote(20), Trierce(20), Quarte(50), Quint(100), CarreOfJacks(200), CarreOfNines(150), CarreOfAces(100);
	
	public int points;
	SpecialAnnounce(int points) {
		this.points = points;
	}
}

// A tierce — a sequence of three (sequences are in the "A K Q J 10 9 8 7" order
// of the same suit) — is worth 20 points
// A quarte — a sequence of four — is worth 50 points.
// A quint — a sequence of five - is worth 100 points (longer sequences are not
// awarded, a sequence of eight is counted as a quint plus a tierce)
// A carré of Jacks is worth 200 points.
// A carré of Nines is worth 150 points.
// A carré of Aces, Kings, Queens, or Tens is worth 100 points. (Sevens and
// Eights are not awarded