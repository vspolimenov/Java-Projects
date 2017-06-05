/**
 * 
 */
package com.java.x8academy.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.java.x8academy.deck.Card;
import com.java.x8academy.deck.Deck;
import com.java.x8academy.player.ComputerPlayer;
import com.java.x8academy.player.HumanPlayer;
import com.java.x8academy.player.Player;
import com.java.x8academy.player.Team;
import com.java.x8academy.rules.Announce;
import com.java.x8academy.rules.Modifier;
import com.java.x8academy.rules.Trump;

/**
 * @author Vasilen Polimenov
 *
 */

public class GameCycle {

	static private Team firstTeam = new Team(new HumanPlayer("Player One"), new ComputerPlayer("Player Two"), "Team 1");
	static private Team secondTeam = new Team(new ComputerPlayer("Player Three"), new ComputerPlayer("Player Four"), "Team 2");

	static List<Player> players = new ArrayList<Player>();

	static private Announce trump = new Announce();
	static private String playerOnHand = firstTeam.firstPlayer.name;
	static private String winningTeamName = firstTeam.name;
	static private Deck deck = new Deck();

	public static void startGame() {

		players.add(firstTeam.firstPlayer);
		players.add(secondTeam.secondPlayer);
		players.add(firstTeam.secondPlayer);
		players.add(secondTeam.secondPlayer);

		while (firstTeam.getScore() < 151 || secondTeam.getScore() < 151) {
			playRound();
		}
	}

	static private void playRound() {

		for (int i = 0; i < 4; i++) {
			players.get(i).fillHand(deck.dealFirstCards());
		}

		trump = getAnnounceToPlay();
		System.out.println(trump.getTrump());

		for (int i = 0; i < 4; i++) {
			players.get(i).fillHand(deck.dealLastCards());
		}

		for (int i = 0; i < 8; i++) {
			System.out.println("Round " + (i +1));
			playOneTurn();
		}

		calculateRoundScore();

	}

	static private List<Card> sort(List<Card> cardsOnTable) {

		Collections.sort(cardsOnTable, new Comparator<Card>() {
			@Override
			public int compare(Card one, Card two) {
				Trump currentTrump = trump.getTrump();
				String currentTrumpName = currentTrump.toString();

				if (currentTrump == Trump.AllTrumps || currentTrump == Trump.NoTrump) {
					currentTrumpName = cardsOnTable.get(0).getSuit().toString();
				}

				String oneSuit = one.getSuit().toString();
				String twoSuit = two.getSuit().toString();

				if (oneSuit.equals(currentTrumpName) && twoSuit.equals(currentTrumpName) == false) {
					return -1;
				} else if (oneSuit.equals(currentTrumpName) == false && twoSuit.equals(currentTrumpName)) {
					return 1;
				} else {
					if (one.getScoce(trump) > two.getScoce(trump)) {
						return -1;
					} else {
						return 1;
					}
				}
			}
		});
		return cardsOnTable;

	}

	static private void calculateRoundScore() {
		firstTeam.calculateScore();
		secondTeam.calculateScore();
	}

	static private void calculateTurnScore(List<Card> playedCards) {

		int score = 0;
		for (Card card : playedCards) {
			score += card.getScoce(trump);
		}

		if (winningTeamName.equals(firstTeam.name)) {
			firstTeam.updateLocalScore(score);
		} else {
			secondTeam.updateLocalScore(score);
		}
	}

	static private Announce getAnnounceToPlay() {

		Announce trump = new Announce();

		for (Player player : players) {

			Announce last = trump;
			trump = player.makeAnnounce(last);
		}

		return trump;

	}

	static private void playOneTurn() {
		List<Card> playedCards = new ArrayList<Card>();

		int numberOfPlayerOnHand = 0;

		for (int i = 0; i < 4; i++) {
			if (playerOnHand.equals(players.get(i).name)) {
				numberOfPlayerOnHand = i;
			}
		}

		for (int i = 0; i < 4; i++) {

			if (numberOfPlayerOnHand == 4) {
				numberOfPlayerOnHand = 0;
			}
			
			Card choosen = players.get(numberOfPlayerOnHand).chooseCard(players.get(numberOfPlayerOnHand).getCardsInHand().size());

			if(playedCards.size() != 0) {
				boolean isValid = true;

				for (int j = 0; j < players.get(numberOfPlayerOnHand).getCardsInHand().size(); j++) {
					if (isValid(players.get(numberOfPlayerOnHand).getCardsInHand().get(j), playedCards)) {
						isValid = false;
					}
				}
				if (isValid == false) {
					while (isValid(choosen, playedCards) == false) {
						choosen = players.get(numberOfPlayerOnHand).chooseCard(players.get(numberOfPlayerOnHand).getCardsInHand().size());
					}
				}
			}
			numberOfPlayerOnHand++;
			playedCards.add(choosen);
		}

		playerOnHand = getWinnerName(playedCards);
		System.out.println(playerOnHand);
		calculateTurnScore(playedCards);
	}

	private static boolean isValid(Card choosen, List<Card> playedCards) {

		Card firstPlayed = playedCards.get(0);
		playedCards = sort(playedCards);
		boolean isValid = false;

		boolean isValidOnTrumps = choosen.getSuit().equals(trump.getTrump())
				&& choosen.compareTo(playedCards.get(playedCards.size() - 1), trump) == 1;
		boolean isValidOnNoTrump = trump.getTrump().equals(Trump.NoTrump)
				&& firstPlayed.getSuit().equals(choosen.getSuit());

		if (isValidOnTrumps || isValidOnNoTrump) {
			isValid = true;
		}

		return isValid;
	}

	static private String getWinnerName(List<Card> playedCards) {

		playedCards = sort(playedCards);
		String playerOnHand = null;

		for (int i = 0; i < 4; i++) {
			if (players.get(i).getLastGiven().equals(playedCards.get(3))) {
				playerOnHand = players.get(i).name;
				if ((i + 1) % 2 == 0) {
					winningTeamName = firstTeam.name;
				} else {
					winningTeamName = secondTeam.name;
				}
				break;
			}
		}

		return playerOnHand;
	}
}
