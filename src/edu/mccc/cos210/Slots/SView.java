package edu.mccc.cos210.Slots;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.OverlayLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import edu.mccc.cos210.Startup.HighRoller;

public class SView extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	private MyJPanel helpme = new MyJPanel();
	ImagePanel panel = new ImagePanel(new ImageIcon("./slots-pictures/mask.png").getImage());
	ImagePanel panel1 = new ImagePanel(new ImageIcon("./slots-pictures/slotsbg.png").getImage());
	Color tableGreen = new Color(0, 106, 57);
	SReel reel;
	private int reelSpinning;
	private int winnings;
	SReel reel0 = new SReel(this);
	SReel reel1 = new SReel(this);
	SReel reel2 = new SReel(this);
	JPanel sjp = new JPanel();
	SPayout sp = new SPayout();
	JButton bet1 = new JButton("Bet One");
	JButton lever = new JButton("Pull Lever");
	private JToggleButton help = new JToggleButton("Winnings Chart");
	private JLabel win = new JLabel("");
	public SView(SReel reels) {
		this.reel = reels;
		panel1.setLayout(new OverlayLayout(panel1));
//		add(win, BorderLayout.SOUTH);
//		setBorder(
//				new CompoundBorder(
//					new EtchedBorder(),
//					new EmptyBorder(4, 4, 4, 4)
//				)
//			);
		reel0.setOpaque(false);
		reel1.setOpaque(false);
		reel2.setOpaque(false);
		panel1.add(createToolbar());
		lever.addActionListener(reel0);
		lever.addActionListener(reel1);
		lever.addActionListener(reel2);
		lever.setEnabled(false);
		sjp.add(lever);
		helpme.setOpaque(false);
		panel.setOpaque(false);
		sjp.setOpaque(false);
		createPanel(helpme);
		panel1.add(sjp);
		panel1.add(panel);
		panel1.add(helpme);
		add(panel1, BorderLayout.CENTER);
		setVisible(true);
	}
	private void createPanel(JPanel helpme) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 3, 3, 0);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.LINE_END;
		helpme.add(reel0, gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		helpme.add(reel1, gbc);
		gbc.gridx = 2;
		gbc.gridy = 0;
		helpme.add(reel2, gbc);
	}
	private JToolBar createToolbar() {
		JToolBar jtb = new JToolBar(FlowLayout.LEFT);
		jtb.setFloatable(false);
		jtb.setOpaque(false);
		bet1.addActionListener(this);
		jtb.add(bet1);
		help.addActionListener(this);
		jtb.add(Box.createHorizontalGlue());
		jtb.add(help);
		return jtb;
	}
	public int getReelSpinning() {
		return reelSpinning;
	}
	public void setReelSpinning(int reelSpinning) {
		this.reelSpinning = reelSpinning;
	}
	public void calculateWinnings() throws Exception {
		if(reel0.getReelSelection() == reel1.getReelSelection() 
			&& reel0.getReelSelection()== reel2.getReelSelection()) {
			if (reel0.getReelSelection() == "Orange") {
				winnings = 5;
				if (reel0.getReelSelection() == "Grapes") {
					winnings = 10;
					if (reel0.getReelSelection() == "Lime") {
						winnings = 25;
						if (reel0.getReelSelection() == "Cherries") {
							winnings = 50;
							if (reel0.getReelSelection() == "Seven") {
								winnings = 75;
							}
						}
					}
				}
			}
				HighRoller.balance = HighRoller.balance + winnings;
				HighRoller.label = new JLabel("Balance: $" + HighRoller.balance);
				HighRoller.UpdateToolbar();
		} else {
			winnings = 0;
		}
	}
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == help) {
			if(help.isSelected()) {
				helpme.setVisible(false);
				win.setVisible(false);
				sjp.setVisible(false);
				add(sp);
			} else {
				helpme.setVisible(true);
				win.setVisible(true);
				sjp.setVisible(true);
				remove(sp);
			}
		}
		if(ae.getSource() == bet1) {
			HighRoller.balance = HighRoller.balance - 1;
			System.out.println(HighRoller.balance);
			HighRoller.label = new JLabel("Balance: $" + HighRoller.balance);
			HighRoller.UpdateToolbar();
			bet1.setEnabled(false);
			lever.setEnabled(true);
		}
	}
class MyJPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public MyJPanel(){
		setLayout(new GridBagLayout());
	}
}
class ImagePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image img;
	public ImagePanel(String img) {
		this(new ImageIcon(img).getImage());
	}
	public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	 }
	public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
}
}