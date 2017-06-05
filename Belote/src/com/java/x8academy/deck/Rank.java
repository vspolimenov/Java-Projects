package com.java.x8academy.deck;

public enum Rank {
    Seven(0,0), Eight(0,0), Nine(0,14), Ten(10,10), Jack(2,20), Queen(3,3), King(4,4), Ace(11,11);

   public int allTrumpsPoints;
	public int noTrumpsPoints;

	Rank(int noTrumpsPoints, int allTrumpsPoints) {
		
		this.noTrumpsPoints = noTrumpsPoints;
		this.allTrumpsPoints = allTrumpsPoints;
	}
}
