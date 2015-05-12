package edu.mccc.cos210.plugin.plugins;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.mccc.cos210.Poker.PPanel;
import edu.mccc.cos210.Poker.PView;
import edu.mccc.cos210.plugin.IPlugIn;

public class Poker implements IPlugIn {
	private static final String cardTitle = "POKER";
	private JPanel view = new PPanel(Color.BLACK);
	private JPanel pokerGameView = new PView();
	public JComponent getView() {
		return this.view;
	}
	@Override
	public void init() {
		this.view = new PPanel(new Color(0, 106, 57));
	}
	@Override
	public JComponent getGameView() {
		return pokerGameView;
	}
	@Override
	public String getCardTitle() {
		return cardTitle;
	}
	public String sendMessage() {
		return "";
	}
}
