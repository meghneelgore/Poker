package com.meghneelgore.poker;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.meghneelgore.poker.Card.Rank;
import com.meghneelgore.utils.maps.ImmutableMultiBiMap;
import com.meghneelgore.utils.maps.ImmutableTreeMultiBiMap;

public class PokerHand implements Comparable {

    public enum Evaluation {
        ROYAL_FLUSH(10),
        STRAIGHT_FLUSH(9),
        FOUR_OF_A_KIND(8),
        FULL_HOUSE(7),
        FLUSH(6),
        STRAIGHT(5),
        THREE_OF_A_KIND(4),
        TWO_PAIR(3),
        PAIR(2),
        HIGH_CARD(1);

        int value;

        Evaluation(int value) {
            this.value = value;
        }
    }

    static class EvaluationWithKickers {
        Evaluation evaluation;
        Card[] kickers;
    }

    protected Card[] hand;
    protected ImmutableMultiBiMap<Rank, Integer> map;

    public PokerHand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        hand = new Card[5];
        hand[0] = card1;
        hand[1] = card2;
        hand[2] = card3;
        hand[3] = card4;
        hand[4] = card5;
        Arrays.sort(hand);
        hashMapize(hand);
    }

    private void hashMapize(Card[] hand) {
        final HashMap<Rank, Integer> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            Integer numCount = map.get(hand[i].getRank());
            if (numCount == null) {
                map.put(hand[i].getRank(), 1);
            } else {
                map.put(hand[i].getRank(), numCount + 1);
            }
        }
        final ImmutableTreeMultiBiMap.Builder<Rank, Integer> builder = new ImmutableTreeMultiBiMap.Builder<>();

        for (Rank r : map.keySet()) {
            builder.put(r, map.get(r));
        }
        this.map = builder.build();
    }

    public Evaluation evaluateHand() {
        if (isRoyalFlush()) return Evaluation.ROYAL_FLUSH;
        if (isStraightFlush()) return Evaluation.STRAIGHT_FLUSH;
        if (isFourOfAKind()) return Evaluation.FOUR_OF_A_KIND;
        if (isFullHouse()) return Evaluation.FULL_HOUSE;
        if (isFlush()) return Evaluation.FLUSH;
        if (isStraight()) return Evaluation.STRAIGHT;
        if (isThreeOfAKind()) return Evaluation.THREE_OF_A_KIND;
        if (isTwoPair()) return Evaluation.TWO_PAIR;
        if (isPair()) return Evaluation.PAIR;
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
        Card.Suit firstCardSuit = hand[0].getSuit();
        for (int i = 1; i < 5; i++) {
            if (hand[i].getSuit() != firstCardSuit) return false;
        }
        return true;
    }

    private boolean isRoyalStraight() {
        return Collections.frequency(map.values(), 1) == 5 && hand[0].getRank().getValue() == 10 && hand[4].getRank().getValue() == 14;
    }

    private boolean isStraight() {
        boolean isRegularStraight = Collections.frequency(map.values(), 1) == 5 && hand[4].getRank().getValue() - hand[0].getRank().getValue() == 4;
        boolean isWheel = Collections.frequency(map.values(), 1) == 5 && hand[0].getRank().getValue() == 14 && hand[4].getRank().getValue() == 5;
        return isRegularStraight || isWheel;
    }

    private boolean isThreeOfAKind() {
        return map.containsValue(3);
    }

    private boolean isTwoPair() {
        return Collections.frequency(map.values(), 2) == 2;
    }

    private boolean isPair() {
        return Collections.frequency(map.values(), 2) == 1;
    }

    @Override
    public int compareTo(Object o) {
        Evaluation eval = evaluateHand();
        PokerHand otherHand = (PokerHand) o;
        Evaluation evalOther = otherHand.evaluateHand();

        if (eval.value > evalOther.value) return 1;
        if (eval.value < evalOther.value) return -1;
        if (eval.value == evalOther.value) {
            return compareSameEvaluations(this, otherHand, eval);
        }
        return 0;
    }

    private int compareSameEvaluations(PokerHand thisHand, PokerHand otherHand, Evaluation eval) {
        List<Rank> thisHandSingles = (List<Rank>) this.map.inverse().get(1);
        List<Rank> otherHandSingles = (List<Rank>) otherHand.map.inverse().get(1);
        int kickerComparison = compareSortedListsOfRanks(thisHandSingles, otherHandSingles);

        List<Rank> thisHandPairList = (List<Rank>) this.map.inverse().get(2);
        List<Rank> otherHandPairList = (List<Rank>) otherHand.map.inverse().get(2);
        int pairComparison = compareSortedListsOfRanks(thisHandPairList, otherHandPairList);

        List<Rank> thisHandTrioList = (List<Rank>) this.map.inverse().get(3);
        List<Rank> otherHandTrioList = (List<Rank>) otherHand.map.inverse().get(3);
        int trioComparison = compareSortedListsOfRanks(thisHandTrioList, otherHandTrioList);

        List<Rank> thisHandQuadList = (List<Rank>) this.map.inverse().get(4);
        List<Rank> otherHandQuadList = (List<Rank>) otherHand.map.inverse().get(4);
        int quadComparison = compareSortedListsOfRanks(thisHandQuadList, otherHandQuadList);

        switch (eval) {

            case ROYAL_FLUSH:
                return 0; //Two separate royal flushes! but they are equal

            case HIGH_CARD:
            case STRAIGHT:
            case FLUSH:
            case STRAIGHT_FLUSH:
                //Compare cards
                return kickerComparison;

            case PAIR:
                //Compare pair ranks
                if (pairComparison != 0) {
                    return pairComparison;
                }

                //Compare kickers
                return kickerComparison;

            case TWO_PAIR:
                //Compare pair ranks
                if (pairComparison != 0) {
                    return pairComparison;
                }

                //Compare kickers
                return kickerComparison;

            case THREE_OF_A_KIND:
                //Compare three of a kind lists
                if (trioComparison != 0) {
                    return trioComparison;
                }

                //Compare kickers
                return kickerComparison;

            case FULL_HOUSE:
                //Compare house
                if (trioComparison != 0) {
                    return trioComparison;
                }
                //Compare residents of house
                return pairComparison;

            case FOUR_OF_A_KIND:
                if(quadComparison != 0) {
                    return quadComparison;
                }
                return kickerComparison;

            default:
                return 0;
        }
    }

    private int compareSortedListsOfRanks(List<Rank> thisList, List<Rank> otherList) {
        if (thisList.size() != otherList.size()) throw new IllegalArgumentException("Lists are of different sizes!");
        for (int i = thisList.size() - 1; i >= 0; i--) {
            int comparison = thisList.get(i).compare(otherList.get(i));
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "[ " + hand[0].toString() + ", "
                + hand[1].toString() + ", "
                + hand[2].toString() + ", "
                + hand[3].toString() + ", "
                + hand[4].toString() + " ]";

    }
}
