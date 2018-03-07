package com.meghneelgore.poker;


import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Deck {

    private int currentDrawIndex = 0;
    private Card[] deck = new Card[52];

    public Deck() {
        for (int i = 0; i < 52; i++) {
            deck[i] = new Card(Card.Suit.getSuit(i), Card.Rank.getRank(i % 13));
        }
        currentDrawIndex = 0;
    }

    public void shuffle() {
        Random rnd = ThreadLocalRandom.current();
        for (int i = deck.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Card a = deck[index];
            deck[index] = deck[i];
            deck[i] = a;
        }
        currentDrawIndex = 0;
    }


    public Card draw() {
        return deck[currentDrawIndex++];
    }
}
