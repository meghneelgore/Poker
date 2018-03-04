package com.meghneelgore.poker;

public class Card implements Comparable<Card> {
    public enum Suit {
        SPADES,
        DIAMONDS,
        CLUBS,
        HEARTS;

        public static Suit getSuit(int i) {
            return Suit.values()[i / 13];
        }
    }

    public enum Rank {

        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        int value;

        Rank(int value) {
            this.value = value;
        }

        public static Rank getRank(int value) {
            return Rank.values()[value];
        }

        public int getValue() {
            return value;
        }

        int compare(Rank r) {
            if(value > r.value) return 1;
            if(value < r.value) return -1;
            return 0;
        }
    }

    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }


    @Override
    public int compareTo(Card o) {
        if (rank.value == o.rank.value) return 0;
        if (rank.value > o.rank.value) return 1;
        return -1;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "" + rank + " of " + suit;
    }


}
