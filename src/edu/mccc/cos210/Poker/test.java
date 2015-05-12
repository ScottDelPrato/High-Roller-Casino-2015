package edu.mccc.cos210.Poker;

import edu.mccc.cos210.Startup.HighRoller;

public class test {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		deck.shuffle();
		Hand hand = new Hand();
		//hand.dealHand(deck);
		hand.addCard(new Card(14, 2));
		hand.addCard(new Card(13, 0));
		hand.addCard(new Card(12, 2));
		hand.addCard(new Card(11, 1));
		hand.addCard(new Card(10, 2));
		int amount = 5;
		HighRoller.balance = HighRoller.balance - amount;
		System.out.println(HighRoller.balance);
		
		for(int i = 0; i < 5; i++ ) {
			//hand.addCard(deck.dealCard());
			
			System.out.println(hand.getCard(i));
		}
		System.out.println(Payout.payout(hand));
		Payout.payout(hand);
		
		int winnings = amount * Payout.mult;
		HighRoller.balance = HighRoller.balance + winnings;
		
		System.out.println(HighRoller.balance);
	}
}
