package com.meghneelgore.pokercalculator;

import com.meghneelgore.cards.Card;
import com.meghneelgore.cards.PokerHand;

public class PokerCalculator {

	public static void main(String[] args) {
		
		{
			//Royal flush
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.ACE),
					new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
					new Card(Card.Suit.HEARTS, Card.Rank.JACK),
					new Card(Card.Suit.HEARTS, Card.Rank.TEN),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected ROYAL_FLUSH");
		}
		
		{
			//Straight flush
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.NINE),
					new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
					new Card(Card.Suit.HEARTS, Card.Rank.JACK),
					new Card(Card.Suit.HEARTS, Card.Rank.TEN),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected STRAIGHT_FLUSH");
		}
		
		{
			//Four of a kind
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.ACE),
					new Card(Card.Suit.DIAMONDS, Card.Rank.ACE),
					new Card(Card.Suit.SPADES, Card.Rank.ACE),
					new Card(Card.Suit.CLUBS, Card.Rank.ACE),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected FOUR_OF_A_KIND");
		}
		
		{
			//Full house
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.ACE),
					new Card(Card.Suit.HEARTS, Card.Rank.KING),
					new Card(Card.Suit.CLUBS, Card.Rank.ACE),
					new Card(Card.Suit.DIAMONDS, Card.Rank.KING),
					new Card(Card.Suit.DIAMONDS, Card.Rank.ACE)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected FULL_HOUSE");
		}
		
		{
			//Flush
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.NINE),
					new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
					new Card(Card.Suit.HEARTS, Card.Rank.JACK),
					new Card(Card.Suit.HEARTS, Card.Rank.SIX),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected FLUSH");
		}
		
		{
			//Straight 
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.CLUBS, Card.Rank.NINE),
					new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
					new Card(Card.Suit.HEARTS, Card.Rank.JACK),
					new Card(Card.Suit.HEARTS, Card.Rank.TEN),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected STRAIGHT");
		}
		
		{
			//Three of a kind
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.CLUBS, Card.Rank.QUEEN),
					new Card(Card.Suit.HEARTS, Card.Rank.QUEEN),
					new Card(Card.Suit.DIAMONDS, Card.Rank.QUEEN),
					new Card(Card.Suit.HEARTS, Card.Rank.TEN),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected THREE_OF_A_KIND");
		}
		
		{
			//Two pair
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.NINE),
					new Card(Card.Suit.CLUBS, Card.Rank.NINE),
					new Card(Card.Suit.HEARTS, Card.Rank.JACK),
					new Card(Card.Suit.CLUBS, Card.Rank.JACK),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected TWO_PAIR");
		}
		
		{
			//Pair
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.NINE),
					new Card(Card.Suit.CLUBS, Card.Rank.NINE),
					new Card(Card.Suit.HEARTS, Card.Rank.JACK),
					new Card(Card.Suit.HEARTS, Card.Rank.TEN),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected PAIR");
		}
		
		{
			//Straight flush
			PokerHand pokerHand = new PokerHand(new Card(Card.Suit.HEARTS, Card.Rank.NINE),
					new Card(Card.Suit.CLUBS, Card.Rank.SIX),
					new Card(Card.Suit.DIAMONDS, Card.Rank.JACK),
					new Card(Card.Suit.SPADES, Card.Rank.TEN),
					new Card(Card.Suit.HEARTS, Card.Rank.KING)
					);
			
			System.out.println(pokerHand.evaluateHand() + " expected HIGH_CARD");
		}
	}
}
