package edu.mccc.cos210.Poker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import edu.mccc.cos210.Startup.HighRoller;

public class PView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private MyJPanel mjp = new MyJPanel();
	private PayoutTable pt = new PayoutTable();
	private Color tableGreen = new Color(0, 106, 57);
	private Deck deck = new Deck();
	private Hand hand = new Hand();
	private JButton deal = new JButton("Deal Cards");
	private JButton bet = new JButton("Bet");
	private JButton remove = new JButton("Discard Selected Cards");
	private JButton lock = new JButton("Lock in your Cards?");
	private JButton next = new JButton("Next Round");
	private JToggleButton help = new JToggleButton("Payout Table");
	private JLabel win = new JLabel("");
	private static int amount = 0;
	private JCheckBox c1;
	private JCheckBox c2;
	private JCheckBox c3;
	private JCheckBox c4;
	private JCheckBox c5;
	
	private static ImageIcon pokerChip = new ImageIcon("pop-up-icons/Poker_Chip_Small.png",
            "poker chip w/ $");
	
	public PView() {
		setLayout(new BorderLayout());
		add(createToolbar(), BorderLayout.NORTH);
		mjp.setBackground(tableGreen);
		add(mjp, BorderLayout.CENTER);
		add(win, BorderLayout.SOUTH);
		pt.setBackground(tableGreen);
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
		remove.addActionListener(this);
		remove.setEnabled(false);
		remove.setFont(getFont().deriveFont(20.0f));
		jtb.add(remove);
		jtb.addSeparator();
		lock.addActionListener(this);
		lock.setEnabled(false);
		lock.setFont(getFont().deriveFont(20.0f));
		jtb.add(lock);
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
		if(ae.getSource() == bet) {
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
		if(ae.getSource() == deal) {
			deck = new Deck();
			deck.shuffle();
			hand = new Hand();
			hand.dealHand(deck);
			displayHand(hand);
			displayRemovals();
			bet.setEnabled(false);
			deal.setEnabled(false);
			remove.setEnabled(true);
			lock.setEnabled(true);
		}
		if(ae.getSource() == remove) {
			if (c1.isSelected()) {
				hand.removeCard(0);
			hand.addCard(0, deck.dealCard());
			}
			if (c2.isSelected()) {
				hand.removeCard(1);
			hand.addCard(1, deck.dealCard());
			}
			if (c3.isSelected()) {
				hand.removeCard(2);
			hand.addCard(2, deck.dealCard());
			}
			if (c4.isSelected()) {
				hand.removeCard(3);
			hand.addCard(3, deck.dealCard());
			}
			if (c5.isSelected()) {
				hand.removeCard(4);
			hand.addCard(4, deck.dealCard());
			}
			mjp.removeAll();
			displayHand(hand);
			displayRemovals();
			revalidate();
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			c4.setEnabled(false);
			c5.setEnabled(false);
			remove.setEnabled(false);
			lock.setEnabled(false);
			next.setEnabled(true);
			win = new JLabel(Payout.payout(hand), Label.LEFT);
			win.setFont(getFont().deriveFont(40.0f));
			win.setOpaque(true);
			win.setForeground(Color.WHITE);
			win.setBackground(tableGreen);
			add(win, BorderLayout.SOUTH);
			Pay();
			
		}
		if(ae.getSource() == lock) {
			c1.setEnabled(false);
			c2.setEnabled(false);
			c3.setEnabled(false);
			c4.setEnabled(false);
			c5.setEnabled(false);
			remove.setEnabled(false);
			lock.setEnabled(false);
			next.setEnabled(true);
			win = new JLabel(Payout.payout(hand), Label.LEFT);
			win.setFont(getFont().deriveFont(40.0f));
			win.setOpaque(true);
			win.setForeground(Color.WHITE);
			win.setBackground(tableGreen);
			add(win, BorderLayout.SOUTH);
			Pay();
		}
		if(ae.getSource() == next) {
			deck = new Deck();
			bet.setEnabled(true);
			deal.setEnabled(false);
			remove.setEnabled(false);
			lock.setEnabled(false);
			next.setEnabled(false);
			mjp.removeAll();
			remove(win);
			mjp.revalidate();
			revalidate();
		}
		if (ae.getSource() == help) {
			if(help.isSelected()) {
				mjp.setVisible(false);
				win.setVisible(false);
				add(pt);
			} else {
				mjp.setVisible(true);
				win.setVisible(true);
				remove(pt);
			}
		}
	}
	private void displayHand(Hand h) {
		for(int i = 0; i < 5; i++) {
			JLabel label = new JLabel(h.getCard(i).getIcon());
			mjp.add(label);
		}
		mjp.revalidate();
	}
	private void displayRemovals() {
		c1 = new JCheckBox(hand.getCard(0).toString());
		c2 = new JCheckBox(hand.getCard(1).toString());
		c3 = new JCheckBox(hand.getCard(2).toString());
		c4 = new JCheckBox(hand.getCard(3).toString());
		c5 = new JCheckBox(hand.getCard(4).toString());
		c1.setForeground(Color.WHITE);
		c2.setForeground(Color.WHITE);
		c3.setForeground(Color.WHITE);
		c4.setForeground(Color.WHITE);
		c5.setForeground(Color.WHITE);
		c1.setBackground(tableGreen);
		c2.setBackground(tableGreen);
		c3.setBackground(tableGreen);
		c4.setBackground(tableGreen);
		c5.setBackground(tableGreen);
		c1.setFont(c1.getFont().deriveFont(24.0f));
		c2.setFont(c1.getFont().deriveFont(24.0f));
		c3.setFont(c1.getFont().deriveFont(24.0f));
		c4.setFont(c1.getFont().deriveFont(24.0f));
		c5.setFont(c1.getFont().deriveFont(24.0f));
		c1.setHorizontalAlignment(SwingConstants.CENTER);
		c2.setHorizontalAlignment(SwingConstants.CENTER);
		c3.setHorizontalAlignment(SwingConstants.CENTER);
		c4.setHorizontalAlignment(SwingConstants.CENTER);
		c5.setHorizontalAlignment(SwingConstants.CENTER);
		mjp.add(c1);
		mjp.add(c2);
		mjp.add(c3);
		mjp.add(c4);
		mjp.add(c5);
		mjp.revalidate();
	}
	static void Pay() {
		int winnings = amount * Payout.mult;
		HighRoller.balance = HighRoller.balance + winnings;
		HighRoller.label = new JLabel("Balance: $" + HighRoller.balance);
		HighRoller.UpdateToolbar();
	}
}