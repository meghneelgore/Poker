package com.meghneelgore.poker;


import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>(52);

    public Deck() {
        for (int i = 0; i < 52; i++) {
            deck.add(i, new Card(Card.Suit.getSuit(i), Card.Rank.getRank(i % 13)));
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }


    public Card draw() {
        return deck.remove(deck.size() - 1);
    }
}
