package edu.mccc.cos210.Poker;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PPanel extends JPanel {
	ImageIcon icon = new ImageIcon("game-icons/poker_small.png",
            "a pretty but meaningless splat");
	GridLayout gl = new GridLayout(1, 5, -10, 0);
	Card c1 = new Card(2, 0);
	Card c2 = new Card(7, 2);
	Card c3 = new Card(14, 1);
	Card c4 = new Card(10, 2);
	Card c5 = new Card(5, 3);
	private static final long serialVersionUID = 1L;
	public PPanel(Color color) {
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
		g2d.drawString("Video", 100, 100);
		g2d.drawString("Poker", 100, 150);
	}
}