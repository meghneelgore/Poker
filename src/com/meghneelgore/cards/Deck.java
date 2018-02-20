package com.meghneelgore.cards;

import com.meghneelgore.cards.Card.Suit;
import com.meghneelgore.cards.Card.Rank;

public class Deck {

	private Card[] deck = new Card[52];
	
	public Deck() {
		for(int i = 0; i < 52; i++) {
			deck[i] = new Card(Suit.getSuit(i), Rank.getRank(i%13));
		}
	}
}
