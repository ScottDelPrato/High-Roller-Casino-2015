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
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
//<<<<<<< HEAD
//=======
////import javax.swing.Timer;
//>>>>>>> branch 'master' of https://github.com/michaelharrigan/High-Roller-Casino.git


import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import edu.mccc.cos210.plugin.IPlugIn;
//import edu.mccc.cos210.plugin.plugins.Blackjack;
//import edu.mccc.cos210.plugin.plugins.Poker;
//import edu.mccc.cos210.plugin.plugins.Roulette;
//import edu.mccc.cos210.plugin.plugins.Slots;


public class HighRoller extends JFrame implements Observer, ActionListener {
	
	private static final String PLUGIN_DIRECTORY = "./bin/edu/mccc/cos210/plugin/plugins";
	private static final long serialVersionUID = 1L;
	private static MyJPanel mjp = new MyJPanel();
	public static double balance = 100.00;
	private static JToolBar jtb = new JToolBar();
	private static CardLayout cards;
	private static JPanel cardPanel;
	private Toolkit tk = Toolkit.getDefaultToolkit();
	public static JLabel label;
	//private static JSlider slider = new JSlider(0, 500, 0);
	public static JButton jbGameSelect = new JButton("Select Game");
	public static JButton jbLeave = new JButton("Leave Table");
	public static final String SPLASH = "splash";
	public static final String MAIN = "main";
	public static final String MENU = "menu";
	private static boolean listener = false;
	
	private static ImageIcon cashOut = new ImageIcon("pop-up-icons/Leave_Table_Small.png",
            "collecting chips");
	private static ImageIcon games = new ImageIcon("pop-up-icons/Game_Select_Small.png",
            "game select crest");
	private static ImageIcon exitIcon = new ImageIcon("pop-up-icons/Exit_Small.png",
            "exit arror door");
	
	private HighRoller() {
		super("HighRoller");
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
									 	"Goodbye",
									 	JOptionPane.DEFAULT_OPTION,
									 	JOptionPane.WARNING_MESSAGE,
									 	exitIcon,
									 	ObjButtons,
									 	ObjButtons[1]
						 			);
				 if(PromptResult==JOptionPane.YES_OPTION) {
				 	System.exit(0);
				 }
			}
		});
		setVisible(true);
		playMusic();
	}
	public static void main(String[] sa) {
		new HighRoller();
		//playMusic();
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
//	This method doesn't work when I put everything into a Runnable JAR file but does work while running through Eclipse.
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
//	public void convertPlugInsToCards() {
//		IPlugIn blackjack = new Blackjack();
//		IPlugIn poker = new Poker();
//		IPlugIn roulette = new Roulette();
//		IPlugIn slots = new Slots();
//		cardPanel.add(blackjack.getGameView(), blackjack.getCardTitle());
//		cardPanel.add(poker.getGameView(), poker.getCardTitle());
//		cardPanel.add(roulette.getGameView(), roulette.getCardTitle());
//		cardPanel.add(slots.getGameView(), slots.getCardTitle());
//	}
	private static class MyJPanel extends JPanel {
		private static final long serialVersionUID = 2L;
		public MyJPanel() {
			setBackground(Color.WHITE);
			setLayout(new GridLayout(0, 4));
			addPlugIns(this);
		}
//		Same for this method.
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
//		void addPlugIns(MyJPanel mjp) {
//			IPlugIn blackjack = new Blackjack();
//			IPlugIn poker = new Poker();
//			IPlugIn roulette = new Roulette();
//			IPlugIn slots = new Slots();
//			blackjack.init();
//			poker.init();
//			roulette.init();
//			slots.init();
//			mjp.add(blackjack.getView());
//			mjp.add(poker.getView());
//			mjp.add(roulette.getView());
//			mjp.add(slots.getView());
//		}
	}
	private static JToolBar createToolBar() {
		if (listener == false) {
			jbGameSelect.addActionListener(alsnr);
			jbLeave.addActionListener(alsnr);
			listener = true;
		}
		jtb.setFloatable(false);
		DecimalFormat formatter = new DecimalFormat("#.00");
		formatter.format(balance);
		label = new JLabel("Balance: $" + balance);
		label.setFont(label.getFont().deriveFont(20.0f));
		jtb.add(label);
		jtb.add(Box.createHorizontalGlue());
		jbGameSelect.setFont(jbGameSelect.getFont().deriveFont(20.0f));
		jtb.add(jbGameSelect);
		jbLeave.setFont(jbLeave.getFont().deriveFont(20.0f));
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
		if (ae.getSource() == jbLeave) {
			String ObjButtons[] = {"Yes","No"};
			 int PromptResult = JOptionPane.showOptionDialog(
									 null,
									 "Are you sure you want to return to the Casino?",
									 "Leave Table",
									 JOptionPane.DEFAULT_OPTION,
									 JOptionPane.WARNING_MESSAGE,
									 cashOut,
									 ObjButtons,
									 ObjButtons[1]
					 			);
			  if(PromptResult==JOptionPane.YES_OPTION)
			  {
			    setView(MAIN);
			  }
		} 
		if (ae.getSource() == jbGameSelect) {
			String gameButtons[] = {"Blackjack", "Poker", "Roulette", "Slots"};
			int PromptResult = JOptionPane.showOptionDialog(null,
									"What game would you like to play?",
									"Game Selection",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.WARNING_MESSAGE,
									games,
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
	private static void playMusic() {
		try
		  {
		    String string = "./casino-music.wav";
		    InputStream in = new FileInputStream(string);
		    AudioStream audioStream = new AudioStream(in);
		    AudioPlayer.player.start(audioStream);
		  }
		  catch (Exception e)
		  {
		     e.printStackTrace();
		  }
	}
}
