package edu.mccc.cos210.plugin.plugins;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.mccc.cos210.Blackjack.BPanel;
import edu.mccc.cos210.Blackjack.BView;
//import edu.mccc.cos210.Poker.PPanel;
import edu.mccc.cos210.plugin.IPlugIn;

public class Blackjack implements IPlugIn {
	private static final String cardTitle = "BLACKJACK";
	private JPanel view = new BPanel(Color.BLACK);
	private JPanel blackjackView = new BView();
	public JComponent getView() {
		return this.view;
	}
	@Override
	public void init() {
		this.view = new BPanel(Color.RED);
	}
	@Override
	public JComponent getGameView() {
		return blackjackView;
	}
	@Override
	public String getCardTitle() {
		return cardTitle;
	}
	public String sendMessage() {
		return "";
	}
}