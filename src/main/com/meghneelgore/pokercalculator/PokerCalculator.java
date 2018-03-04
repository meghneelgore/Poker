package com.meghneelgore.pokercalculator;

import com.meghneelgore.poker.Deck;
import com.meghneelgore.poker.PokerHand;

public class PokerCalculator {

    public static void main(String[] args) {

        for(int i = 0; i < 10; i++ ) {
            System.out.println("Try " + i);
            Deck deck = new Deck();
            deck.shuffle();
            PokerHand p1 = new PokerHand(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw());
            PokerHand p2 = new PokerHand(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw());

            System.out.println("Hand 1: " + p1 + " Evaluation: " + p1.evaluateHand());
            System.out.println("Hand 1: " + p2 + " Evaluation: " + p2.evaluateHand());

            System.out.println(p1.compareTo(p2) > 0 ? "Hand 1 wins" : p1.compareTo(p2) < 0 ? "Hand 2 wins" : "Hands are equal");
        }

    }
}
