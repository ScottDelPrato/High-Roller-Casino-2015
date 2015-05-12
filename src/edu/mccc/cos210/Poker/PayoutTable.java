package edu.mccc.cos210.Poker;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PayoutTable extends JPanel {
	private static final long serialVersionUID = 1L;
	public PayoutTable() {
		setLayout(new GridLayout(6, 12));
		RoyalFlushHand();
		ThreeOfAKindHand();
		StraightFlushHand();
		TwoPairHand();
		FourOfAKindHand();
		PairOfAcesHand();
		FullHouseHand();
		PairOfKingsHand();
		FlushHand();
		PairOfQueensHand();
		StraightHand();
		PairOfJacksHand();
		revalidate();
	}
	private void RoyalFlushHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Royal Flush:<br>X 800<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		for (int i = 2; i < 7; i++) {
			hand.addCard(new Card(i+8, 0));
		}
		displayHand(hand);
	}
	private void StraightFlushHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Straight Flush:<br>X 50<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		for (int i = 2; i < 7; i++) {
			hand.addCard(new Card(i, 1));
		}
		displayHand(hand);
	}
	private void FourOfAKindHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Four of a Kind:<br>X 40<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		for (int i = 0; i < 4; i++) {
			hand.addCard(new Card(10, i));
		}
		hand.addCard(new Card(13, 2));
		displayHand(hand);
	}
	private void FullHouseHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Full House:<br>X 10<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		for (int i = 0; i < 3; i++) {
			hand.addCard(new Card(3, i));
		}
		for (int i = 1; i < 3; i++) {
			hand.addCard(new Card(5, i));
		}
		displayHand(hand);
	}
	private void FlushHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Flush:<br>X 7<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(3, 1));
		hand.addCard(new Card(14, 1));
		hand.addCard(new Card(9, 1));
		hand.addCard(new Card(2, 1));
		hand.addCard(new Card(5, 1));
		displayHand(hand);
	}
	private void StraightHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Straight:<br>X 5<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(4, 0));
		hand.addCard(new Card(5, 2));
		hand.addCard(new Card(6, 2));
		hand.addCard(new Card(7, 0));
		hand.addCard(new Card(8, 0));
		displayHand(hand);
	}
	private void ThreeOfAKindHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Three of a Kind:<br>X 3<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		for (int i = 1; i < 4; i++) {
			hand.addCard(new Card(2, i));
		}
		hand.addCard(new Card(11, 0));
		hand.addCard(new Card(6, 1));
		displayHand(hand);
	}
	private void TwoPairHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Two Pair:<br>X 2<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(4, 0));
		hand.addCard(new Card(4, 1));
		hand.addCard(new Card(12, 3));
		hand.addCard(new Card(8, 2));
		hand.addCard(new Card(8, 1));
		displayHand(hand);
	}
	private void PairOfAcesHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Pair of Aces:<br>X 1<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(14, 2));
		hand.addCard(new Card(5, 0));
		hand.addCard(new Card(13, 2));
		hand.addCard(new Card(9, 1));
		hand.addCard(new Card(14, 2));
		displayHand(hand);
	}
	private void PairOfKingsHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Pair of Kings:<br>X 1<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(10, 0));
		hand.addCard(new Card(5, 2));
		hand.addCard(new Card(13, 3));
		hand.addCard(new Card(13, 1));
		hand.addCard(new Card(14, 1));
		displayHand(hand);
	}
	private void PairOfQueensHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Pair of Queens:<br>X 1<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(3, 1));
		hand.addCard(new Card(12, 1));
		hand.addCard(new Card(12, 0));
		hand.addCard(new Card(6, 3));
		hand.addCard(new Card(10, 3));
		displayHand(hand);
	}
	private void PairOfJacksHand() {
		Hand hand = new Hand();
		JLabel label = new JLabel("<html>Pair of Jacks:<br>X 1<html>");
		label.setFont(getFont().deriveFont(15.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		add(label);
		hand.addCard(new Card(11, 1));
		hand.addCard(new Card(11, 2));
		hand.addCard(new Card(9, 1));
		hand.addCard(new Card(2, 0));
		hand.addCard(new Card(7, 3));
		displayHand(hand);
	}
	private void displayHand(Hand hand) {
		for(int i = 0; i < 5; i++) {
			JLabel label = new JLabel(hand.getCard(i).getSmallIcon());
			add(label);
		}
		revalidate();
	}
}
