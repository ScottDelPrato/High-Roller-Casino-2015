package edu.mccc.cos210.plugin.plugins;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.mccc.cos210.Roulette.RView;
import edu.mccc.cos210.Roulette.RPanel;
import edu.mccc.cos210.plugin.IPlugIn;

public class Roulette implements IPlugIn {
	private static final String cardTitle = "ROULETTE";
	private JPanel view = new RPanel(Color.BLACK);
	private JPanel rouletteGameView = new RView();
	
	public JComponent getView() {
		return this.view;
	}
	@Override
	public void init() {
		this.view = new RPanel(Color.RED);
	}
	@Override
	public JComponent getGameView() {
		return rouletteGameView;
	}
	@Override
	public String getCardTitle() {
		return cardTitle;
	}
	public String sendMessage() {
		return "";
	}
}
