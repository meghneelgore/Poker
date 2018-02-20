package com.meghneelgore.cards;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import com.meghneelgore.cards.Card.Rank;
import com.meghneelgore.cards.Card.Suit;

public class PokerHand {

	public enum Evaluation {
		ROYAL_FLUSH,
		STRAIGHT_FLUSH,
		FOUR_OF_A_KIND,
		FULL_HOUSE,
		FLUSH,
		STRAIGHT,
		THREE_OF_A_KIND,
		TWO_PAIR,
		PAIR,
		HIGH_CARD
	}
	
	Card[] hand = new Card[5];
	HashMap<Rank, Integer> map = new HashMap<>();
	
	public PokerHand(Card card1, Card card2, Card card3, Card card4, Card card5) {
		hand[0] = card1;
		hand[1] = card2;
		hand[2] = card3;
		hand[3] = card4;
		hand[4] = card5;
		Arrays.sort(hand);
		hashMapize(hand);
	}
	
	private void hashMapize(Card[] hand) {
		for(int i = 0; i < 5; i++) {
			Integer numCount = map.get(hand[i].getRank());
			if(numCount == null) {
				map.put(hand[i].getRank(), 1);
			} else {
				map.put(hand[i].getRank(), numCount + 1);
			}
		}
	}

	public Evaluation evaluateHand() {
		if(isRoyalFlush()) return Evaluation.ROYAL_FLUSH;
		if(isStraightFlush()) return Evaluation.STRAIGHT_FLUSH;
		if(isFourOfAKind()) return Evaluation.FOUR_OF_A_KIND;
		if(isFullHouse()) return Evaluation.FULL_HOUSE;
		if(isFlush()) return Evaluation.FLUSH;
		if(isStraight()) return Evaluation.STRAIGHT;
		if(isThreeOfAKind()) return Evaluation.THREE_OF_A_KIND;
		if(isTwoPair()) return Evaluation.TWO_PAIR;
		if(isPair()) return Evaluation.PAIR;
		return Evaluation.HIGH_CARD;
		
	}

	private boolean isRoyalFlush() {
		return isFlush() && isRoyalStraight();
	}
	
	private boolean isStraightFlush() {
		return isFlush() && isStraight();
	}
	
	private boolean isFourOfAKind() {
		return map.values().contains(4);
	}
	
	private boolean isFullHouse() {
		return map.containsValue(3) && map.containsValue(2);
	}
	
	private boolean isFlush() {
		Suit firstCardSuit = hand[0].getSuit();
		for(int i = 1; i < 5; i++) {
			if(hand[i].getSuit() != firstCardSuit) return false;
		}
		return true;
	}
	
	private boolean isRoyalStraight() {
		return Collections.frequency(map.values(), 1) == 5 && hand[0].getRank().getValue() == 1 && hand[4].getRank().getValue() == 13; 	
	}
	
	private boolean isStraight() {
		
		return Collections.frequency(map.values(), 1) == 5 && hand[4].getRank().getValue() - hand[0].getRank().getValue() == 4;
	}
	
	private boolean isThreeOfAKind() {
		return map.containsValue(3);
	}
	
	private boolean isTwoPair() {
		return  Collections.frequency(map.values(), 2) == 2;
	}
	
	private boolean isPair() {
		return  Collections.frequency(map.values(), 2) == 1;
	}
}
