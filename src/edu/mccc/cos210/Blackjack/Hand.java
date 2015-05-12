package edu.mccc.cos210.Blackjack;
import java.util.ArrayList;

public class Hand {
   private ArrayList<Card> hand;
   public Hand() {
      hand = new ArrayList<Card>();
   }
   public void addCard(Card c) {
      if (c == null)
         throw new NullPointerException("Can't add a null card to a hand.");
      hand.add(c);
   } 
   public void addCard(int position, Card c) {
	   if (c == null)
		     throw new NullPointerException("Can't add a null card to a hand.");
		  hand.add(position, c);
   }
   public void removeCard(Card c) {
      hand.remove(c);
   }
   public void removeCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
      hand.remove(position);
   }
   public Card getCard(int position) {
      if (position < 0 || position >= hand.size())
         throw new IllegalArgumentException("Position does not exist in hand: "
               + position);
       return (Card)hand.get(position);
   }
   public int getCardCount() {
	      return hand.size();
	   }
   public void sortBySuit() {
      ArrayList<Card> newHand = new ArrayList<Card>();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         Card c = (Card)hand.get(0);  // Minimal card.
         for (int i = 1; i < hand.size(); i++) {
            Card c1 = (Card)hand.get(i);
            if ( c1.getSuit() < c.getSuit() ||
                    (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
                pos = i;
                c = c1;
            }
         }
         hand.remove(pos);
         newHand.add(c);
      }
      hand = newHand;
   }
   public void sortByValue() {
      ArrayList<Card> newHand = new ArrayList<Card>();
      while (hand.size() > 0) {
         int pos = 0;  // Position of minimal card.
         Card c = (Card)hand.get(0);  // Minimal card.
         for (int i = 1; i < hand.size(); i++) {
            Card c1 = (Card)hand.get(i);
            if ( c1.getValue() < c.getValue() ||
                    (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                pos = i;
                c = c1;
            }
         }
         hand.remove(pos);
         newHand.add(c);
      }
      hand = newHand;
   }
   public static void dealHand(Deck deck, Hand dh, Hand ph) {
	   for(int i = 0; i < 2; i++)
		{
			ph.addCard(deck.dealCard());
			dh.addCard(deck.dealCard());
		}
   }
}