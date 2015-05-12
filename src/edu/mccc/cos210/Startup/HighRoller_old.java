package edu.mccc.cos210.Startup;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
//import javax.swing.Timer;

import edu.mccc.cos210.plugin.IPlugIn;


public class HighRoller_old extends JFrame implements Observer, ActionListener {
	
	private static final String PLUGIN_DIRECTORY = "./bin/edu/mccc/cos210/plugin/plugins";
	private static final long serialVersionUID = 1L;
	private static MyJPanel mjp = new MyJPanel();
	public static double balance = 100.00;
	private static JToolBar jtb = new JToolBar();
	private static CardLayout cards;
	private static JPanel cardPanel;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	public static JLabel label = new JLabel("Balance: $" + balance + " ");
	private static JSlider slider = new JSlider(0, 500, 0);
	private static JButton jbMenu = new JButton("Menu");
	public static JButton jbGameSelect = new JButton("Select Game");
	public static JButton jbLeave = new JButton("Leave Table");
	public static final String SPLASH = "splash";
	public static final String MAIN = "main";
	public static final String MENU = "menu";
	private static boolean listener = false;
	private HighRoller_old() {
		super("HighRoller_old");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		int xSize = ((int) tk.getScreenSize().getWidth());
		int ySize = ((int) tk.getScreenSize().getHeight()-80);
		setSize(xSize,ySize);
		setResizable(false);
		setLocationRelativeTo(null);
		add(createToolBar(),BorderLayout.NORTH);
		cards = new CardLayout();
		cardPanel = new JPanel();
		cardPanel.setLayout(cards);
		cardPanel.add(mjp, MAIN);
		convertPlugInsToCards();
		add(cardPanel);
		cards.show(cardPanel, MAIN);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent we) {
				String ObjButtons[] = {"Yes","No"};
				int PromptResult = JOptionPane.showOptionDialog(
									 	null,
									 	"Are you sure you want to Exit?",
									 	"Exit Prompt",
									 	JOptionPane.DEFAULT_OPTION,
									 	JOptionPane.WARNING_MESSAGE,
									 	null,
									 	ObjButtons,
									 	ObjButtons[1]
						 			);
				 if(PromptResult==JOptionPane.YES_OPTION) {
				 	System.exit(0);
				 }
			}
		});
		setVisible(true);
	}
	public static void main(String[] sa) {
		new HighRoller_old();
	}
	public static void setView(String v) {
		cards.show(cardPanel, v);
	}
	@Override
	public void update(Observable o, Object arg) {
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		setView(HighRoller.MENU);
	}
	
	public void convertPlugInsToCards() {
		File[] fa = new File(PLUGIN_DIRECTORY).listFiles();
		
		for (File f : fa) {
			try {
				Class<?> clazz = Class.forName(("edu.mccc.cos210.plugin.plugins." + f.getName()).replace(".class", ""));
				IPlugIn plugIn = (IPlugIn) clazz.newInstance();
				cardPanel.add(plugIn.getGameView(), plugIn.getCardTitle());
			} catch (Exception ex) { continue; }
		}
	}
	private static class MyJPanel extends JPanel {
		private static final long serialVersionUID = 2L;
		public MyJPanel() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(0, 4));
			addPlugIns(this);
		}
		void addPlugIns(MyJPanel mjp) {
			File[] fa = new File(PLUGIN_DIRECTORY).listFiles();
			for (File f : fa) {
				try {
					Class<?> clazz = Class.forName(("edu.mccc.cos210.plugin.plugins." + f.getName()).replace(".class", ""));
					IPlugIn plugIn = (IPlugIn) clazz.newInstance();
					plugIn.init();
					mjp.add(plugIn.getView());
				} catch (Exception ex) { continue; }
			}
		}
	}
	private static JToolBar createToolBar() {
		if (listener == false) {
			jbMenu.addActionListener(alsnr);
			jbGameSelect.addActionListener(alsnr);
			jbLeave.addActionListener(alsnr);
			listener = true;
		}
		label.setHorizontalAlignment(JLabel.CENTER);
		jtb.add(label);
		jtb.addSeparator();
		jtb.setFloatable(false);
		jtb.add(jbMenu);
		jtb.add(slider);
		jtb.addSeparator();
		jtb.add(jbGameSelect);
		jtb.add(jbLeave);
		
		return jtb;
	}
	public static JToolBar UpdateToolbar() {
		jtb.removeAll();
		label.setText("Balance: $" + balance);
		createToolBar();
		return jtb;
	}
	private static ActionListener alsnr = ae -> {
		if (ae.getSource() == jbMenu) {
			mjp.repaint();
		} 
		if (ae.getSource() == jbLeave) {
			String ObjButtons[] = {"Yes","No"};
			 int PromptResult = JOptionPane.showOptionDialog(
									 null,
									 "Are you sure you want to return to the Casino?",
									 "Exit Prompt",
									 JOptionPane.DEFAULT_OPTION,
									 JOptionPane.WARNING_MESSAGE,
									 null,
									 ObjButtons,
									 ObjButtons[1]
					 			);
			  if(PromptResult==JOptionPane.YES_OPTION)
			  {
			    setView(MAIN);
			  }
			  mjp.repaint();
		} 
		if (ae.getSource() == jbGameSelect) {
			String gameButtons[] = {"Blackjack", "Poker", "Roulette", "Slots"};
			int PromptResult = JOptionPane.showOptionDialog(null,
									"What game would you like to play?",
									"Game Selection",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.WARNING_MESSAGE,
									null,
									gameButtons,
									null
								);
			if(PromptResult==0) {
			    setView("BLACKJACK");
			} else 
			if(PromptResult==1) {
			    setView("POKER");
			} else 
			if(PromptResult==2) {
			    setView("ROULETTE");
			} else 
			if(PromptResult==3) {
			    setView("SLOTS");
			} else {
				setView("MAIN");
			}
			mjp.repaint();
		}
	};
}