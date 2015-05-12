package edu.mccc.cos210.plugin.plugins;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.mccc.cos210.Slots.SPanel;
import edu.mccc.cos210.Slots.SReel;
import edu.mccc.cos210.Slots.SView;
import edu.mccc.cos210.plugin.IPlugIn;

public class Slots implements IPlugIn {
	private static final String cardTitle = "SLOTS";
	private JPanel view = new SPanel(Color.BLACK);
	SReel reel;
	private JPanel slotsGameView = new SView(reel);
	public JComponent getView() {
		return this.view;
	}
	@Override
	public void init() {
		this.view = new SPanel(new Color(0, 106, 57));
	}
	@Override
	public JComponent getGameView() {
		return this.slotsGameView;
	}
	@Override
	public String getCardTitle() {
		return cardTitle;
	}
	public String sendMessage() {
		return "";
	}
}
