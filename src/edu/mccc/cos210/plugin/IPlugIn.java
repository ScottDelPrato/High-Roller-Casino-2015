package edu.mccc.cos210.plugin;

import javax.swing.JComponent;

public interface IPlugIn {
	public void init();
	public JComponent getView();
	public JComponent getGameView();
	public String getCardTitle();
	public String sendMessage();
}
