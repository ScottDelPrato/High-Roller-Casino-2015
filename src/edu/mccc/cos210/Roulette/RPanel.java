package edu.mccc.cos210.Roulette;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RPanel extends JPanel {
	GridLayout gl = new GridLayout(1, 5, -10, 0);
	ImageIcon icon = new ImageIcon("roulette-picz/noun_90387_cc_small.png",
            "a pretty but meaningless splat");
	
	private static final long serialVersionUID = 1L;
	public RPanel(Color color) {
		setBackground(color);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		this.add(new JLabel(icon));
		this.setLayout(gl);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setPaint(Color.WHITE);
		g2d.setFont(g2d.getFont().deriveFont(50.0f));
		g2d.drawString("Roulette", 80, 100);
		//g2d.drawString("Poker", 100, 150);
		g2d.dispose();
	}
}