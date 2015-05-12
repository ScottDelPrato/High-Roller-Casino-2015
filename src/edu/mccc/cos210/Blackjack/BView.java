package edu.mccc.cos210.Blackjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import edu.mccc.cos210.Poker.Payout;
import edu.mccc.cos210.Startup.HighRoller;

public class BView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JPanel jp = new JPanel(new GridLayout(2, 1));
	private MyJPanel dmjp = new MyJPanel();
	private MyJPanel pmjp = new MyJPanel();
	private Color tableGreen = new Color(0, 106, 57);
	private JButton bet = new JButton("Bet");
	private JButton deal = new JButton("Deal");
	private JButton hit = new JButton("Hit");
	private JButton stand = new JButton("Stand");
	private JButton next = new JButton("Next Round");
	private JToggleButton help = new JToggleButton("Payout Table");
	private JLabel win = new JLabel("");
	private static int amount = 0;
	private boolean flip = false;
	private Deck deck;
	private Hand dHand;
	private Hand pHand;
	private int dSum = 0;
	private int pSum = 0;
	private int aceDSum = 0;
	private int acePSum = 0;
	
	private static ImageIcon pokerChip = new ImageIcon("pop-up-icons/Poker_Chip_Small.png",
            "poker chip w/ $");
	
	public BView() {
		setLayout(new BorderLayout());
		add(createToolbar(), BorderLayout.NORTH);
		jp.setBackground(tableGreen);
		dmjp.setBackground(tableGreen);
		pmjp.setBackground(tableGreen);
		add(jp);
		jp.add(dmjp);
		jp.add(pmjp);
		add(win, BorderLayout.SOUTH);
		setVisible(true);
		
;	}
	private JToolBar createToolbar() {
		JToolBar jtb = new JToolBar(FlowLayout.LEFT);
		jtb.setFloatable(false);
		bet.addActionListener(this);
		bet.setFont(getFont().deriveFont(20.0f));
		jtb.add(bet);
		jtb.addSeparator();
		deal.addActionListener(this);
		deal.setEnabled(false);
		deal.setFont(getFont().deriveFont(20.0f));
		jtb.add(deal);
		jtb.addSeparator();
		hit.addActionListener(this);
		hit.setEnabled(false);
		hit.setFont(getFont().deriveFont(20.0f));
		jtb.add(hit);
		jtb.addSeparator();
		stand.addActionListener(this);
		stand.setEnabled(false);
		stand.setFont(getFont().deriveFont(20.0f));
		jtb.add(stand);
		jtb.addSeparator();
		next.addActionListener(this);
		next.setEnabled(false);
		next.setFont(getFont().deriveFont(20.0f));
		jtb.add(next);
		jtb.addSeparator();
		help.addActionListener(this);
		help.setFont(getFont().deriveFont(20.0f));
		jtb.add(Box.createHorizontalGlue());
		jtb.add(help);
		jtb.setBackground(tableGreen);
		return jtb;
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == deal) {
			deck = new Deck();
			deck.shuffle();
			dHand = new Hand();
			pHand = new Hand();
			Hand.dealHand(deck, dHand, pHand);
			SumDealerHand(dHand);
			SumPlayerHand(pHand);
			displayDealerHand(dHand);
			displayPlayerHand(pHand);
			deal.setEnabled(false);
			hit.setEnabled(true);
			stand.setEnabled(true);
			checkSums();
		}
		if(ae.getSource() == hit) {
			
			pHand.addCard(deck.dealCard());
			SumPlayerHand(pHand);
			displayPlayerHand(pHand);
			checkSums();
		}
		if(ae.getSource() == stand) {
			dealerHit();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
		
		}
		if (ae.getSource() == next) {
			remove(win);
			dmjp.removeAll();
			pmjp.removeAll();
			jp.revalidate();
			bet.setEnabled(true);
			next.setEnabled(false);
		}
		if(ae.getSource() == bet){
			Object[] options = {1, 5, 10, 15, 20, 25, 30, 50, 75, 100};
			int p = (int) JOptionPane.showInputDialog(
									 null,
									 "What would you like to bet?",
									 "Bet",
									 JOptionPane.PLAIN_MESSAGE,
									 pokerChip,
									 options,
									 null
					 			);
			amount = p;
			HighRoller.balance = HighRoller.balance - p;
			HighRoller.label = new JLabel("Balance: $" + HighRoller.balance);
			HighRoller.UpdateToolbar();
			deal.setEnabled(true);
			bet.setEnabled(false);
		}
	}
	private void displayDealerHand(Hand dh) {
		dmjp.removeAll();
		JLabel labelWithSum = new JLabel("Dealer's Cards:");
		labelWithSum.setFont(getFont().deriveFont(30.0f));
		labelWithSum.setForeground(Color.WHITE);
		dmjp.add(labelWithSum);
		for (int i = 0; i < dh.getCardCount(); i++) {
			if (i == 0 && flip == false) {
				dmjp.add(new JLabel(dh.getCard(0).getBackIcon()));
			} else {
				dmjp.add(new JLabel(dh.getCard(i).getIcon()));
				flip = false;
			}
			dmjp.revalidate();
			jp.revalidate();
		}		
	}
	private void displayPlayerHand(Hand ph) {
		pmjp.removeAll();
		JLabel label = new JLabel("<html>Player's Cards:<br>Low Sum:" + pSum);
		label.setFont(getFont().deriveFont(30.0f));
		label.setForeground(Color.WHITE);
		pmjp.add(label);
		for (int i = 0; i < ph.getCardCount(); i++) {
				pmjp.add(new JLabel(ph.getCard(i).getIcon()));
		}
		pmjp.revalidate();
		jp.revalidate();
				
	}
	private void checkSums() {
		if (aceDSum == 21 && acePSum == 21) {
			flip();
			win = new JLabel("Draw!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount;
			HighRoller.UpdateToolbar();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			revalidate();
		} else if (aceDSum == 21) {
			flip();
			win = new JLabel("Blackjack! Dealer Wins!", Label.LEFT);
			formatWinLabel();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			revalidate();
		} else if (acePSum == 21) {
			flip();
			win = new JLabel("Blackjack! Player Wins!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount*2;
			HighRoller.UpdateToolbar();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			revalidate();
		} else if (dSum == 21 && pSum == 21) {
			flip();
			win = new JLabel("Draw!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount;
			HighRoller.UpdateToolbar();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			revalidate();
		} else if (dSum == 21) {
			flip();
			win = new JLabel("Blackjack! Dealer Wins!", Label.LEFT);
			formatWinLabel();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			revalidate();
		} else if (pSum == 21) {
			flip();
			win = new JLabel("Blackjack! Player Wins!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount*2;
			HighRoller.UpdateToolbar();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			revalidate();
		} else if (dSum > 21) {
			flip();
			win = new JLabel("Dealer Hand busts! Player Wins!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount*1.5;
			HighRoller.UpdateToolbar();
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
		} else if (pSum > 21) {
			flip();
			win = new JLabel("Player Hand busts! Dealer Wins!", Label.LEFT);
			formatWinLabel();
			hit.setEnabled(false);
			stand.setEnabled(false);
			next.setEnabled(true);
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
		}
	}
	private void dealerHit() {
//		if (aceDSum == acePSum) {
//			flip();
//			win = new JLabel("Draw!", Label.LEFT);
//			formatWinLabel();
//			HighRoller.balance = HighRoller.balance + amount;
//			HighRoller.UpdateToolbar();
//			JLabel labelWithSum = new JLabel(" " + dSum);
//			labelWithSum.setFont(getFont().deriveFont(30.0f));
//			labelWithSum.setForeground(Color.WHITE);
//			dmjp.add(labelWithSum);
//			hit.setEnabled(false);
		 if (aceDSum > acePSum) {
			flip();
			win = new JLabel("Dealer Hand Higher! Dealer Wins!", Label.LEFT);
			formatWinLabel();
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
			hit.setEnabled(false);
		} else {
		while (aceDSum < 18) {
			dHand.addCard(deck.dealCard());
			displayDealerHand(dHand);
			SumDealerHand(dHand);
			}
		if (aceDSum < 22) {
			if (aceDSum > acePSum || aceDSum > pSum) {
				flip();
				win = new JLabel("Dealer Hand Higher! Dealer Wins!", Label.LEFT);
				formatWinLabel();
				JLabel labelWithSum = new JLabel(" " + dSum);
				labelWithSum.setFont(getFont().deriveFont(30.0f));
				labelWithSum.setForeground(Color.WHITE);
				dmjp.add(labelWithSum);
				hit.setEnabled(false);
			} 
		} 
		if (acePSum < 22) {
			if (acePSum > aceDSum || acePSum > dSum) {
				flip();
				win = new JLabel("Player Hand Higher! Player Wins!", Label.LEFT);
				formatWinLabel();
				HighRoller.balance = HighRoller.balance + amount*1.5;
				HighRoller.UpdateToolbar();
				JLabel labelWithSum = new JLabel(" " + dSum);
				labelWithSum.setFont(getFont().deriveFont(30.0f));
				labelWithSum.setForeground(Color.WHITE);
				dmjp.add(labelWithSum);
				hit.setEnabled(false);
			}
		} else if (dSum > pSum) {
			flip();
			win = new JLabel("Dealer Hand Higher! Dealer Wins!", Label.LEFT);
			formatWinLabel();
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
			hit.setEnabled(false);
		} else if (pSum > dSum) {
			flip();
			win = new JLabel("Player Hand Higher! Player Wins!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount*1.5;
			HighRoller.UpdateToolbar();
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
			hit.setEnabled(false);
		} else if (dSum == pSum) {
			flip();
			win = new JLabel("Draw!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount;
			HighRoller.UpdateToolbar();
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
			hit.setEnabled(false);
		} else if (aceDSum == acePSum) {
			flip();
			win = new JLabel("Draw!", Label.LEFT);
			formatWinLabel();
			HighRoller.balance = HighRoller.balance + amount;
			HighRoller.UpdateToolbar();
			JLabel labelWithSum = new JLabel(" " + dSum);
			labelWithSum.setFont(getFont().deriveFont(30.0f));
			labelWithSum.setForeground(Color.WHITE);
			dmjp.add(labelWithSum);
			hit.setEnabled(false);
		}
		
		checkSums();
		stand.setEnabled(false);
		}
	}
	private void SumDealerHand(Hand hand) {
		dSum = 0;
		aceDSum = 0;
		for (int i = 0; i < hand.getCardCount(); i++) {
			dSum = dSum + hand.getCard(i).getValue();
			if (hand.getCard(i).getValue() == 1) {
				aceDSum = aceDSum + 11;
			} else {
				aceDSum = aceDSum + hand.getCard(i).getValue();
			}
		}
	}
	private void SumPlayerHand(Hand hand) {
		pSum = 0;
		acePSum = 0;
		for (int i = 0; i < hand.getCardCount(); i++) {
			pSum = pSum + hand.getCard(i).getValue();
			if (hand.getCard(i).getValue() == 1) {
				acePSum = acePSum + 11;
			} else {
				acePSum = acePSum + hand.getCard(i).getValue();
			}
		}
	}
	private void flip() {
		dHand.addCard(0, dHand.getCard(0));
		dHand.removeCard(1);
		flip = true;
		displayDealerHand(dHand);
	}
	private void formatWinLabel() {
		win.setFont(getFont().deriveFont(40.0f));
		win.setOpaque(true);
		win.setForeground(Color.WHITE);
		win.setBackground(tableGreen);
		add(win, BorderLayout.SOUTH);
	}
}
