package edu.mccc.cos210.Blackjack;

public class Deck {
    private Card[] deck;
    private int cardsUsed;
    public Deck() {
        deck = new Card[52];
        int cardCt = 0;
        for ( int suit = 0; suit <= 3; suit++ ) {
            for ( int value = 2; value <= 14; value++ ) {
                deck[cardCt] = new Card(value,suit);
                cardCt++;
            }
        }
        cardsUsed = 0;
    }
    public void shuffle() {
        for ( int i = deck.length-1; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }
    public Card dealCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        return deck[cardsUsed - 1];
    }
}