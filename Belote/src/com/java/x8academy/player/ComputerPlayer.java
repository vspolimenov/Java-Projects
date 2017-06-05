package com.java.x8academy.player;

import java.util.Random;

import com.java.x8academy.deck.Card;
import com.java.x8academy.rules.Announce;
import com.java.x8academy.rules.Modifier;

public class ComputerPlayer extends Player {

	public ComputerPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	private int randomGeneratedChoice() {

		Random rn = new Random();
		int randomNum = getRandomNumberInRange(1, cardsInHand.size());
		return randomNum;
	}

	public Card chooseCard(int maxChoice) {

		int choiceFromHand = this.randomGeneratedChoice();
		lastGiven = cardsInHand.get(choiceFromHand);

		cardsInHand.remove(choiceFromHand);
		return lastGiven;
	}

	public void checkForSpecialAnnounces() {

	}

	private void makeSpecialAnnounce() {

	}

	@Override
	public Announce makeAnnounce(Announce last) {
		int announceNumber = this.generateRandomNumberForAnnounce(last.getAnnounceWeight());

		Announce newAnnounce = new Announce();
		if (announceNumber == 7) {
			newAnnounce = last;
			newAnnounce.setModifier(Modifier.Contra);
		} else if (announceNumber == 8) {
			newAnnounce = last;
			newAnnounce.setModifier(Modifier.ReContra);
		} else {

			newAnnounce = this.createAnnounceFromAnnounceNumber(announceNumber);
		}

		return newAnnounce;
	}

	private int generateRandomNumberForAnnounce(int announceWeight) {

		int maxAnnounceNumber = 6;
		if (announceWeight != 0) {
			maxAnnounceNumber = 7;
		}
		
		int randomNum = getRandomNumberInRange(announceWeight, maxAnnounceNumber);
		return randomNum;
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min - 1;
	}
}
