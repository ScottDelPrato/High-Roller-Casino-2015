package edu.mccc.cos210.Slots;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SPayout extends JPanel {
	private static final long serialVersionUID = 1L;
	public SPayout() {
		setLayout(new GridLayout(5, 7, 0, 0));
		Seven();
		Lime();
		Cherry();
		Grape();
		Orange();
		revalidate();
	}
	private void Seven() {
		GroupFruit group = new GroupFruit();
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(0));
		}
		displayFruit(group);
		JLabel label = new JLabel("75");
		label.setFont(getFont().deriveFont(32.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(0));
		}
		displayFruit(group);
	}
	private void Lime() {
		GroupFruit group = new GroupFruit();
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(1));
		}
		displayFruit(group);
		JLabel label = new JLabel("50");
		label.setFont(getFont().deriveFont(32.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(1));
		}
		displayFruit(group);
	}
	private void Cherry() {
		GroupFruit group = new GroupFruit();
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(2));
		}
		displayFruit(group);
		JLabel label = new JLabel("25");
		label.setFont(getFont().deriveFont(32.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(2));
		}
		displayFruit(group);
	}
	private void Grape() {
		GroupFruit group = new GroupFruit();
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(3));
		}
		displayFruit(group);
		JLabel label = new JLabel("10");
		label.setFont(getFont().deriveFont(32.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(3));
		}
		displayFruit(group);
	}
	private void Orange() {
		GroupFruit group = new GroupFruit();
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(4));
		}
		displayFruit(group);
		JLabel label = new JLabel("5");
		label.setFont(getFont().deriveFont(32.0f));
		label.setForeground(Color.WHITE);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		add(label);
		for (int i = 0; i < 3; i++) {
			group.addFruit(new SFruit(4));
		}
		displayFruit(group);
	}
	private void displayFruit(GroupFruit group) {
		for(int i = 0; i < 3; i++) {
			JLabel label = new JLabel(group.getFruit(i).getIcon());
			add(label);
		}
		revalidate();
	}
}

