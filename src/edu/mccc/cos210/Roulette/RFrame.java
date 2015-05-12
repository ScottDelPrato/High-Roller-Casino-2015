package edu.mccc.cos210.Roulette;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import edu.mccc.cos210.Poker.MyJPanel;
public class RFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public RFrame() {
		super("Poker");
		setSize(1024, 768);
		setLocationRelativeTo(null);
		setVisible(true);
		add(new MyJPanel());
	}
}
