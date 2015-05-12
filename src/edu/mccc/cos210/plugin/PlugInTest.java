package edu.mccc.cos210.plugin;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;

public class PlugInTest extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final String PLUGIN_DIRECTORY = "./bin/edu/mccc/cos210/plugin/plugins";
	private static PlugInTest plt = null;
	private MyJPanel mjp = new MyJPanel();
	private JSlider slider = new JSlider(0, 100, 0);
	public double balance = 10.55;
	
	public PlugInTest() throws Exception {
		super("PlugIn Test");
		JFrame jf = new JFrame("HighRoller");
		jf.setDefaultCloseOperation(EXIT_ON_CLOSE);
		jf.add(
				createToolBar(),
				BorderLayout.NORTH
			);
		jf.add(mjp, BorderLayout.CENTER);
		jf.setSize(1024, 768);
		jf.setResizable(true);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	public static void main(String[] sa) throws Exception {
		EventQueue.invokeAndWait(
			() -> {
				try {
					plt = new PlugInTest();
				} catch (Exception ex) { }
			}
		);
		plt.doIt();
	}
	public JSlider getSlider() {
		return this.slider;
	}
	private ActionListener alsnr = ae -> {
		if (ae.getSource() instanceof JButton) {
			@SuppressWarnings("unused")
			JButton jb = (JButton) ae.getSource();
			System.out.println("you clicked a button!");
			mjp.repaint();
		}
	};
	private ActionListener alsnrEx = ae -> {
		if (ae.getSource() instanceof JButton) {
			String ObjButtons[] = {"Yes","No"};
			 int PromptResult = JOptionPane.showOptionDialog(
									 null,
									 "Are you sure you want to exit?",
									 "Exit Prompt",
									 JOptionPane.DEFAULT_OPTION,
									 JOptionPane.WARNING_MESSAGE,
									 null,
									 ObjButtons,
									 ObjButtons[1]
					 			);
			  if(PromptResult==JOptionPane.YES_OPTION)
			  {
			    System.exit(0);
			  }
		}
	};
	private JToolBar createToolBar() {
		JToolBar jtb = new JToolBar();
		JLabel label = new JLabel("Balance: $" + balance + " ");
		label.setHorizontalAlignment(JLabel.CENTER);
		jtb.add(label);
		jtb.setFloatable(false);
		JButton jbmenu = new JButton("Menu");
		jtb.add(jbmenu);
		JSlider slider = new JSlider(0, 500, 0);
		jtb.add(slider);
		jtb.addSeparator();
		jbmenu.addActionListener(alsnr);
		JButton jbuser = new JButton("Users");
		jtb.add(jbuser);
		jbuser.addActionListener(alsnr);
		JButton jbexit = new JButton("Exit");
		jtb.add(jbexit);
		jbexit.addActionListener(alsnrEx);
		return jtb;
	}
	private void doIt() throws Exception {
		WatchService ws = FileSystems.getDefault().newWatchService();
		Paths.get(PLUGIN_DIRECTORY).register(ws, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
		for (;;) {
			WatchKey wk = null;
			try {
				wk = ws.take();
			} catch (InterruptedException ex) {
				continue;
			}
			for (@SuppressWarnings("unused") WatchEvent<?> event : wk.pollEvents()) { }
			wk.reset();
			mjp.removeAll();
			mjp.addPlugIns(mjp);
			mjp.validate();
			mjp.repaint();
		}
	}
	private class MyJPanel extends JPanel {
		private static final long serialVersionUID = 1L;
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
}
