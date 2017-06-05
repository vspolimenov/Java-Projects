/**
 * 
 */
package com.java.x8academy.player;

import java.util.List;

import com.java.x8academy.deck.Card;
import com.java.x8academy.io.ConsoleIOManager;
import com.java.x8academy.rules.Announce;
import com.java.x8academy.rules.Modifier;
import com.java.x8academy.rules.Trump;

/**
 * @author Vasilen Polimenov
 *
 */
public class HumanPlayer extends Player {

	public HumanPlayer(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Announce makeAnnounce(Announce last) {
		int announceNumber = ConsoleIOManager.getPlayerAnnounceNumber(last.getAnnounceWeight());

		Announce newAnnounce = new Announce();
		if (checkIsValidAnnounce(announceNumber, last)) {
		if (announceNumber == 7) {
			newAnnounce = last;
			newAnnounce.setModifier(Modifier.Contra);
		} else if (announceNumber == 8) {
			newAnnounce = last;
			newAnnounce.setModifier(Modifier.ReContra);
		} else {
			
				newAnnounce = this.createAnnounceFromAnnounceNumber(announceNumber);
			}
		} else {
			System.out.println("Invalid Announce!");
		}
		
		return newAnnounce;
	}
	
	@Override
	public Card chooseCard(int maxChoice) {
		for(Card c: cardsInHand){
		//	System.out.println(c.getRank() +" " +  c.getSuit());
		}
		int choiceFromHand = ConsoleIOManager.getPlayerCardChoice(maxChoice);
		lastGiven = cardsInHand.get(choiceFromHand);

		cardsInHand.remove(choiceFromHand);
		return lastGiven;

	}

	@Override
	public void checkForSpecialAnnounces() {
		// TODO Auto-generated method stub

	}

	private void makeSpecialAnnounce() {

	}

}
