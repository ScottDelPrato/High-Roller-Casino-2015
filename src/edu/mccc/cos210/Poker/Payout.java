package edu.mccc.cos210.Poker;

public class Payout {

	public static int mult;
	public static String payout(Hand hand) {
		String s;
		if(RoyalFlush(hand) == true) {
			s = ("Royal FLush!");
			mult = 800;
		} else if(StraightFlush(hand) == true) {
			s = ("Straight Flush!");
			mult = 50;
		} else if(FourOfAKind(hand) == true) {
			s = ("Four Of A Kind!");
			mult = 40;
		} else if(FullHouse(hand) == true) {
			s = ("Full House!");
			mult = 10;
		} else if(Flush(hand) == true) {
			s = ("Flush!");
			mult = 7;
		} else if(Straight(hand) == true) {
			s = ("Straight!");
			mult = 5;
		} else if(ThreeOfAKind(hand) == true) {
			s = ("Three Of A Kind!");
			mult = 3;
		} else if(TwoPair(hand) == true) {
			s = ("Two Pair!");
			mult = 2;
		} else if(PairOfAces(hand) == true) {
			s = ("Pair of Aces!");
			mult = 1;
		} else if(PairOfKings(hand) == true) {
			s = ("Pair of Kings!");
			mult = 1;
		} else if(PairOfQueens(hand) == true) {
			s = ("Pair of Queens!");
			mult = 1;
		} else if(PairOfJacks(hand) == true) {
			s = ("Pair of Jacks!");
			mult = 1;
		} else {
			s = ("No matches.");
			mult = 0;
		}
		return s;
	}
	public static boolean RoyalFlush(Hand hand) {
		hand.sortByValue();
		if(hand.getCard(0).getValue() == 10 &&
		hand.getCard(1).getValue() == 11 &&
		hand.getCard(2).getValue() == 12 &&
		hand.getCard(3).getValue() == 13 &&
		hand.getCard(4).getValue() == 14
		) {
			hand.sortBySuit();
			if(hand.getCard(0).getSuit() == hand.getCard(1).getSuit() &&
					hand.getCard(1).getSuit() == hand.getCard(2).getSuit() &&
					hand.getCard(2).getSuit() == hand.getCard(3).getSuit() &&
					hand.getCard(3).getSuit() == hand.getCard(4).getSuit()
					) {
						return true;
					}
		}
		return false;
	}
	public static boolean StraightFlush(Hand hand) {
		hand.sortByValue();
		if(hand.getCard(0).getValue() == hand.getCard(1).getValue() - 1 &&
		hand.getCard(1).getValue() == hand.getCard(2).getValue() - 1 &&
		hand.getCard(2).getValue() == hand.getCard(3).getValue() - 1 &&
		hand.getCard(3).getValue() == hand.getCard(4).getValue() - 1
		) {
			hand.sortBySuit();
			if(hand.getCard(0).getSuit() == hand.getCard(1).getSuit() &&
			hand.getCard(1).getSuit() == hand.getCard(2).getSuit() &&
			hand.getCard(2).getSuit() == hand.getCard(3).getSuit() &&
			hand.getCard(3).getSuit() == hand.getCard(4).getSuit()
			)
				return true;
		}
		return false;
	}
	public static boolean FourOfAKind(Hand hand) {
		hand.sortByValue();
		for(int i = 0; i < 2; i++) {
			if(hand.getCard(i).getValue() == hand.getCard(i + 1).getValue() &&
			hand.getCard(i + 1).getValue() == hand.getCard(i + 2).getValue() &&
			hand.getCard(i + 2).getValue() == hand.getCard(i + 3).getValue()
			) {
				return true;
			}
		}
		return false;
	}
	public static boolean FullHouse(Hand hand) {
		hand.sortByValue();
		if(hand.getCard(0).getValue() == hand.getCard(1).getValue() &&
		hand.getCard(1).getValue() == hand.getCard(2).getValue() &&
		hand.getCard(3).getValue() == hand.getCard(4).getValue()
		) {
			return true;
		}
		if(hand.getCard(0).getValue() == hand.getCard(1).getValue() &&
		hand.getCard(2).getValue() == hand.getCard(3).getValue() &&
		hand.getCard(3).getValue() == hand.getCard(4).getValue()
		) {
			return true;
		}
		return false;
	}
	public static boolean Flush(Hand hand) {
		hand.sortBySuit();
		if(hand.getCard(0).getSuit() == hand.getCard(1).getSuit() &&
		hand.getCard(1).getSuit() == hand.getCard(2).getSuit() &&
		hand.getCard(2).getSuit() == hand.getCard(3).getSuit() &&
		hand.getCard(3).getSuit() == hand.getCard(4).getSuit()
		) {
			return true;
		}
		return false;
	}
	public static boolean Straight(Hand hand) {
		hand.sortByValue();
		if(hand.getCard(0).getValue() == hand.getCard(1).getValue() - 1 &&
		hand.getCard(1).getValue() == hand.getCard(2).getValue() - 1 &&
		hand.getCard(2).getValue() == hand.getCard(3).getValue() - 1 &&
		hand.getCard(3).getValue() == hand.getCard(4).getValue() - 1
		) {
			return true;
		}
		return false;
	}
	public static boolean ThreeOfAKind(Hand hand) {
		hand.sortByValue();
		for(int i = 0; i < 3; i++) {
			if(hand.getCard(i).getValue() == hand.getCard(i + 1).getValue() &&
			hand.getCard(i + 1).getValue() == hand.getCard(i + 2).getValue() 
			) {
				return true;
			}
		}
		return false;
	}
	public static boolean TwoPair(Hand hand) {
		hand.sortByValue();
		if(hand.getCard(0).getValue() == hand.getCard(1).getValue() &&
		hand.getCard(2).getValue() == hand.getCard(3).getValue()		
		) {
			return true;
		}
		if(hand.getCard(0).getValue() == hand.getCard(1).getValue() &&
		hand.getCard(3).getValue() == hand.getCard(4).getValue()		
		) {
			return true;
		}
		if(hand.getCard(1).getValue() == hand.getCard(2).getValue() &&
		hand.getCard(3).getValue() == hand.getCard(4).getValue()		
		) {
			return true;
		}
		return false;
	}
	public static boolean PairOfAces(Hand hand) {
		hand.sortByValue();
		for (int i = 0; i < 4; i++) {
			if (hand.getCard(i).getValue() == 14 && hand.getCard(i+1).getValue() == 14) {
				return true;
			}
		}
		return false;
	}
	public static boolean PairOfKings(Hand hand) {
		hand.sortByValue();
		for (int i = 0; i < 4; i++) {
			if (hand.getCard(i).getValue() == 13 && hand.getCard(i+1).getValue() == 13) {
				return true;
			}
		}
		return false;
	}
	public static boolean PairOfQueens(Hand hand) {
		hand.sortByValue();
		for (int i = 0; i < 4; i++) {
			if (hand.getCard(i).getValue() == 12 && hand.getCard(i+1).getValue() == 12) {
				return true;
			}
		}
		return false;
	}
	public static boolean PairOfJacks(Hand hand) {
		hand.sortByValue();
		for (int i = 0; i < 4; i++) {
			if (hand.getCard(i).getValue() == 11 && hand.getCard(i+1).getValue() == 11) {
				return true;
			}
		}
		return false;
	}
//	public static boolean OnePair(Hand hand) {
//		hand.sortByValue();
//		if(hand.getCard(0).getValue() == hand.getCard(1).getValue()) {
//			return true;
//		}
//		if(hand.getCard(1).getValue() == hand.getCard(2).getValue()) {
//			return true;
//		}
//		if(hand.getCard(2).getValue() == hand.getCard(3).getValue()) {
//			return true;
//		}
//		if(hand.getCard(3).getValue() == hand.getCard(4).getValue()) {
//			return true;
//		}
//		return false;
//	}
//	public static Card HighCard(Hand hand) {
//		hand.sortByValue();
//		return hand.getCard(4);
//	}
}