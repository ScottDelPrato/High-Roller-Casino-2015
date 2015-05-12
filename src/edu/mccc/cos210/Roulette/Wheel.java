package edu.mccc.cos210.Roulette;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wheel extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	static protected Pocket[] wheel;
	private BufferedImage bi;
	private static final double ROTATION = (Math.PI/19.0202);
	private static final int NUM_POCKETS = 38;
	private int pocketIndex = 0;
	private double rotationIndex = 0;
	private int frameHeight = 0;
	private int frameWidth = 0;
	RView view;
	Color tableGreen = new Color(0, 106, 57);
	JLabel resultantPocket = new JLabel();
	public Wheel(RView view) {
		this.view = view;
		setLayout(null);
		setBackground(tableGreen);
		loadWheel();
		try {
			bi = ImageIO.read(new File("roulette-picz/Wheel-small.png"));
		} catch (IOException ex) {
			System.err.println("Bad image.");
			System.exit(-1);
		}
		frameHeight = bi.getHeight();
		frameWidth = bi.getWidth();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		 AffineTransform at = new AffineTransform();

         // 4. translate it to the center of the component
         at.translate(getWidth() / 2, getHeight() / 2);

         // 3. do the actual rotation
         at.rotate(rotationIndex);
         System.out.println(rotationIndex);

         // 1. translate the object so that you rotate it around the 
         //    center (easier :))
         at.translate(-bi.getWidth()/2, -bi.getHeight()/2);

        g2d.drawImage(bi, at, null);
		g2d.dispose();
	}
	public Pocket getWheelPocket() {
		return wheel[pocketIndex];
	}
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(frameWidth, frameHeight);
	}
	void bumpPocketIndex() {
		pocketIndex++;
		pocketIndex = pocketIndex > NUM_POCKETS - 1 ? 0 : pocketIndex;
	}
	void bumpRotationIndex() {
		rotationIndex = rotationIndex + ROTATION;
		if (rotationIndex > (Math.PI*2)) {
			rotationIndex = 0;
			bumpPocketIndex();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton jb = (JButton) ae.getSource();
		resultantPocket.setText("");
		jb.setEnabled(false);
		jb.setText("SPINNING!!!");
		Timer t2 = new Timer(
			50,
			(ae2) -> {
				System.out.println("SPIN");
				bumpRotationIndex();
				repaint();
			}
		);
		Timer t1 = new Timer(
			100, 
			(ae1) -> {
				t2.stop();
				jb.setText("Spin Wheel");
				jb.setEnabled(true);
				if (rotationIndex > 2) {
					bumpPocketIndex();
				}
				//rotationIndex = 0;
				System.out.println(getWheelPocket().getValueAsString());
				resultantPocket.setFont(getFont().deriveFont(30.0f));
				resultantPocket.setForeground(Color.WHITE);
//				resultantPocket.setText("The winning number is: " + getWheelPocket().getValueAsString() + "!");
				resultantPocket.setText(getWheelPocket().getValueAsString() + "!");
				repaint();
			}
		);
		t1.setRepeats(false);
		t1.setInitialDelay(new Random().nextInt(5000) + 3000);
		t1.start();
		t2.start();
	}
	
	protected void loadWheel() {
        wheel = new Pocket[38];
        wheel[0] = new Pocket("0", "green");
		wheel[1] = new Pocket("28", "black");
		wheel[2] = new Pocket("9", "red");
		wheel[3] = new Pocket("26", "black");
		wheel[4] = new Pocket("30", "red");
		wheel[5] = new Pocket("11", "black");
		wheel[6] = new Pocket("7", "red");
		wheel[7] = new Pocket("20", "black");
		wheel[8] = new Pocket("32", "red");
		wheel[9] = new Pocket("17", "black");
		wheel[10] = new Pocket("5", "red");
		wheel[11] = new Pocket("22", "black");
		wheel[12] = new Pocket("34", "red");
		wheel[13] = new Pocket("15", "black");
		wheel[14] = new Pocket("3", "red");
		wheel[15] = new Pocket("24", "black");
		wheel[16] = new Pocket("36", "red");
		wheel[17] = new Pocket("13", "black");
		wheel[18] = new Pocket("1", "red");
		wheel[19] = new Pocket("00", "green");
		wheel[20] = new Pocket("27", "red");
		wheel[21] = new Pocket("10", "black");
		wheel[22] = new Pocket("25", "red");
		wheel[23] = new Pocket("29", "black");
		wheel[24] = new Pocket("12", "red");
		wheel[25] = new Pocket("8", "black");
		wheel[26] = new Pocket("19", "red");
		wheel[27] = new Pocket("31", "black");
		wheel[28] = new Pocket("18", "red");
		wheel[29] = new Pocket("6", "black");
		wheel[30] = new Pocket("21", "red");
		wheel[31] = new Pocket("33", "black");
		wheel[32] = new Pocket("16", "red");
		wheel[33] = new Pocket("4", "black");
		wheel[34] = new Pocket("23", "red");
		wheel[35] = new Pocket("35", "black");
		wheel[36] = new Pocket("14", "red");
		wheel[37] = new Pocket("2", "black");
    }
}
