package com.meghneelgore.pokercalculator;

import com.meghneelgore.poker.Deck;
import com.meghneelgore.poker.PokerHand;

public class PokerCalculator {

    public static final PokerHand.Evaluation TARGET_EVAL = PokerHand.Evaluation.ROYAL_FLUSH;

    public static void main(String[] args) {
        PokerHand p1 = null, p2 = null;
        PokerHand.Evaluation e1 = null, e2 = null;
        int i = 0;
        Deck deck = new Deck();
        long time = System.nanoTime();
        do {

            deck.shuffle();

            for (int j = 0; j < 5; j++) {


                p1 = new PokerHand(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw());

                p2 = new PokerHand(deck.draw(), deck.draw(), deck.draw(), deck.draw(), deck.draw());

                e1 = p1.evaluateHand();

                e2 = p2.evaluateHand();
                i++;

                if (e1 == TARGET_EVAL || e2 == TARGET_EVAL) {
                    System.out.println("Try " + i);
                    long timeElapsed = System.nanoTime() - time;
                    System.out.println("Time elapsed: " + timeElapsed + " ns; " + (timeElapsed / i) + " ns / try");
                    System.out.println("Hand 1: " + p1 + " Evaluation: " + e1);
                    System.out.println("Hand 2: " + p2 + " Evaluation: " + e2);
                    System.out.println(p1.compareTo(p2) > 0 ? "Hand 1 wins" : p1.compareTo(p2) < 0 ? "Hand 2 wins" : "Hands are equal");
                }
            }
        } while (e1 != TARGET_EVAL || e2 != TARGET_EVAL);
        System.out.println("Try " + i++);
        System.out.println("Hand 1: " + p1 + " Evaluation: " + e1);
        System.out.println("Hand 2: " + p2 + " Evaluation: " + e2);
        System.out.println(p1.compareTo(p2) > 0 ? "Hand 1 wins" : p1.compareTo(p2) < 0 ? "Hand 2 wins" : "Hands are equal");
    }
}
