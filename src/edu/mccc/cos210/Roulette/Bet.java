package edu.mccc.cos210.Roulette;

public class Bet {
	
	protected Pocket pocketB;
	
	protected double valueB;
	
	public Bet(Pocket thePocket, double theValue) {
		valueB = theValue;
		pocketB = thePocket;
	}
	
	protected int pocket;
	protected double bet;
	
	protected void makeStraightBet(int thePocket, double theBet) {
		Bet bet1 = new Bet(Wheel.wheel[0], 12);
	}

}
