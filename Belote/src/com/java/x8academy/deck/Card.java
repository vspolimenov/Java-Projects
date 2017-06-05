/**
 * 
 */
package com.java.x8academy.deck;

import com.java.x8academy.rules.Announce;

/**
 * @author Vasilen Polimenov
 *
 */
public class Card {

	private Rank rank = null;
	private Suit suit = null;

	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}

	public Card() {
		this.rank = Rank.Ace;
		this.suit = Suit.Clubs;
	}

	public int getScoce(Announce announce) {
		if (announce.getTrump().name().equals("AllTrumps") || announce.getTrump().name() == this.suit.name()) {

			return rank.allTrumpsPoints * announce.getModifier().pointsToIncrease;
		} else {
			return rank.noTrumpsPoints * announce.getModifier().pointsToIncrease;
		}
	}

	public Rank getRank() {
		return rank;
	}

	public void setRank(Rank rank) {
		this.rank = rank;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}

	@Override
	public boolean equals(Object o) {
		Card card = (Card) o;
		boolean isEquals = false;
		if (this.getRank() == card.getRank() && this.getSuit() == card.getSuit()) {
			isEquals = true;
		}

		return isEquals;

	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + rank.hashCode();
		result = 31 * result + suit.hashCode();
		return result;
	}

	public int compareTo(Card card, Announce trump) {

		if (this.getScoce(trump) > card.getScoce(trump)) {
			return 1;
		} else {
			return -1;
		}
	}

}
