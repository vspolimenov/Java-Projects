/**
 * 
 */
package com.java.x8academy.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.java.x8academy.deck.Card;
import com.java.x8academy.rules.Announce;
import com.java.x8academy.rules.SpecialAnnounce;
import com.java.x8academy.rules.Trump;

/**
 * @author Vasilen Polimenov
 *
 */
public abstract class Player {

	protected List<Card> cardsInHand = new ArrayList<>();
	private List<SpecialAnnounce> specialAnounces = new ArrayList<>();
	protected Card lastGiven = new Card();

	public Player(String name) {
		this.name = name;
	}
	
	public List<Card> getCardsInHand() {
		return this.cardsInHand;
	}
	
	public void fillHand(List<Card> cards) {
		for (Card card : cards) {
			cardsInHand.add(card);
		}
	}

	public String name = null;
	public String team = null;

	// A tierce — a sequence of three (sequences are in the "A K Q J 10 9 8 7"
	// order
	// of the same suit) — is worth 20 points
	// A quarte — a sequence of four — is worth 50 points.
	// A quint — a sequence of five - is worth 100 points (longer sequences are
	// not
	// awarded, a sequence of eight is counted as a quint plus a tierce)
	// A carré of Jacks is worth 200 points.
	// A carré of Nines is worth 150 points.
	// A carré of Aces, Kings, Queens, or Tens is worth 100 points. (Sevens and
	// Eights are not awarded

	public List<Card> sort(List<Card> cardsOnTable) {

		Collections.sort(cardsOnTable, new Comparator<Card>() {
			@Override
			public int compare(Card one, Card two) {

				if (one.getSuit().compareTo(two.getSuit()) == 0) {
					return one.getRank().compareTo(two.getRank());
				}
				return one.getSuit().compareTo(two.getSuit());

			}
		});
		return cardsOnTable;
	}

	private void checkForTierce() {

	}

	protected void checkForSpecialAnnounces() {

	};

	public abstract Announce makeAnnounce(Announce last);

	protected boolean checkIsValidAnnounce(int announceNumber, Announce last) {
		boolean isValid = false;
		if (last.getAnnounceWeight() < announceNumber) {
			isValid = true;
		}
		//System.out.println("--" + last.getTrump());
		return isValid;

	}

	protected Announce createAnnounceFromAnnounceNumber(int announceNumber) {
		Announce newAnnounce = new Announce();

		if (announceNumber == 1) {
			newAnnounce.setTrump(Trump.Clubs);
		} else if (announceNumber == 2) {
			newAnnounce.setTrump(Trump.Dyamonds);
		} else if (announceNumber == 3) {
			newAnnounce.setTrump(Trump.Hearts);
		} else if (announceNumber == 4) {
			newAnnounce.setTrump(Trump.Spade);
		} else if (announceNumber == 5) {
			newAnnounce.setTrump(Trump.NoTrump);
		} else if (announceNumber == 6) {
			newAnnounce.setTrump(Trump.AllTrumps);
		}

		return newAnnounce;
	}

	public abstract Card chooseCard(int maxChoice);

	public Card getLastGiven() {
		return lastGiven;
	}

}