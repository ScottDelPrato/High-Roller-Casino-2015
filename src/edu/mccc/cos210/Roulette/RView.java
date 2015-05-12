package edu.mccc.cos210.Roulette;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import edu.mccc.cos210.Startup.HighRoller;

public class RView extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Color tableGreen = new Color(0, 106, 57);
	JButton bet1 = new JButton("Bet One");
	public Point yourBet;
	public Point pocketOne = new Point(326, -44);
	int xBuff = 15;
	int yBuff = 30;
	int rectH = 80;
	int rectW = 48;
	int betPocket = 0;
//	private JToggleButton help = new JToggleButton("Winnings Chart");
	BufferedImage img;
	Wheel wheel;
	JPanel rjp = new JPanel();
	public RView() {
		super();
		rjp.setBackground(tableGreen);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setVisible(true);
//		add(createToolbar(), BorderLayout.NORTH);
		//wheel shit
		Wheel w1 = new Wheel(this);
		rjp.add(w1);
		JButton lever = new JButton("Spin Wheel");
		lever.addActionListener(w1);
		rjp.add(lever, BoxLayout.X_AXIS);
		//end wheel shit

		Table t1 = new Table(this);
		rjp.add(t1);
		
		JLabel currentBet = new JLabel("Your current bet is: " + betPocket + "          ");
		currentBet.setFont(getFont().deriveFont(30.0f));
		currentBet.setForeground(Color.WHITE);
		rjp.add(currentBet);
		
		JLabel resultantText = new JLabel("The winning number is... ");
		resultantText.setFont(getFont().deriveFont(30.0f));
		resultantText.setForeground(Color.WHITE);
		rjp.add(resultantText);
		
		
		rjp.add(w1.resultantPocket);
		
		
		addMouseListener(
				new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent me) {
						yourBet = t1.convertMouseToLocal(me.getX(), me.getY());
						System.out.println("X = " + yourBet.getX());
						System.out.println("Y = " + yourBet.getY());
						System.out.println();
						for (int q=0; q<13; q++) {
							for (int i = 0; i < 3; i++) {
								if (yourBet.getX() > pocketOne.getX()-xBuff+(rectW*q) && yourBet.getX() < pocketOne.getX()+xBuff+(rectW*q) && yourBet.getY() > pocketOne.getY()-yBuff+(rectH*i) && yourBet.getY() < pocketOne.getY()+yBuff+(rectH*i)) {
									betPocket = (i+1) + (q*3);
									rjp.revalidate();
									if (betPocket < 37) {
										System.out.println("YOU BET " + betPocket + "!!!!!");
									} else if (betPocket == 37) {
										System.out.println("YOU BET COLUMN " + (i+1) + "!!!!!");
									} else if (betPocket == 38) {
										System.out.println("YOU BET COLUMN " + (i+1) + "!!!!!");
									} else if (betPocket == 39) {
										System.out.println("YOU BET COLUMN " + (i+1) + "!!!!!");
									}
								}
							}
						}
					}
				}
		);
		
		
		
		
		if (w1.getWheelPocket().getValueAsInt() == betPocket) {
			System.out.println("WINNER WINNER WINNER!!!!!!");
		}
		add(rjp);
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == bet1) {
			HighRoller.balance = HighRoller.balance - 1;
			System.out.println(HighRoller.balance);
			HighRoller.label = new JLabel("Balance: $" + HighRoller.balance);
			HighRoller.UpdateToolbar();
			bet1.setEnabled(false);
		}
		
	}
}


