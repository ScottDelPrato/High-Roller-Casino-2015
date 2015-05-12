package edu.mccc.cos210.Slots;

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
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.mccc.cos210.Startup.HighRoller;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SReel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private Map<Integer, String> reelMap = new HashMap<>();
	private BufferedImage bi;
	private static final int STRIDE = 23;
	private static final int NUM_IMAGES = 15;
	private int imageIndex = 0;
	private int strideIndex = 0;
	private int frameHeight = 0;
	private int frameWidth = 0;
	SView view;
	Timer t1;
	Timer t2;
	Color tableGreen = new Color(0, 106, 57);
	public SReel(SView view) {
		this.view = view;
		setLayout(null);
		loadReelMap();
		try {
			bi = ImageIO.read(new File("./slots-pictures/slot-strip2.png"));
		} catch (IOException ex) {
			System.err.println("Bad image.");
			System.exit(-1);
		}
		frameHeight = bi.getHeight() / (NUM_IMAGES + 1);
		frameWidth = bi.getWidth();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.drawRenderedImage(
			bi.getSubimage(
				0, 
				(imageIndex * frameHeight) + (strideIndex * STRIDE), 
				frameWidth, 
				frameHeight	
			), 
			new AffineTransform()
		);
		g2d.dispose();
	}
	public String getReelSelection() {
		return reelMap.get(imageIndex);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(frameWidth, frameHeight);
	}
	@Override
	public Dimension getMaximumSize() {
	 return getPreferredSize();
	}
	void bumpImageIndex() {
		imageIndex++;
		imageIndex = imageIndex > NUM_IMAGES - 1 ? 0 : imageIndex;
	}
	void bumpStrideIndex() {
		strideIndex++;
		if (strideIndex > frameHeight / STRIDE) {
			strideIndex = 0;
			bumpImageIndex();
		}
	}
	public int getImageIndex() {
		return imageIndex;
	}
	public Timer getTimer() {
		return t1;
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		JButton jb = (JButton) ae.getSource();
		jb.setEnabled(false);
		jb.setText("SPINNING!!!");
		view.setReelSpinning(3);
		Timer t2 = new Timer(
			33,
			(ae2) -> {
				bumpStrideIndex();
				repaint();
			}
		);
		Timer t1 = new Timer(
			100, 
			(ae1) -> {
				t2.stop();
				if (view.getReelSpinning() > 0) {
					view.setReelSpinning(view.getReelSpinning() - 1);
				}
				if (view.getReelSpinning() == 0) {
					jb.setText("Pull Lever");
					jb.setEnabled(true);	
					try {
						view.calculateWinnings();
					} catch (Exception e) {
						System.err.println("Rotten Fruit");
						System.exit(-1);
					}
				}
				this.view.bet1.setEnabled(true);
				this.view.lever.setEnabled(false);
				if (strideIndex > 2) {
					bumpImageIndex();
				}
				strideIndex = 0;
				System.out.println(getReelSelection());
				repaint();
			}
		);
		t1.setRepeats(false);
		t1.setInitialDelay(new Random().nextInt(5000) + 3000);
		t1.start();
		t2.start();
	}
	private void loadReelMap() {
		reelMap.put(0, "Orange");
		reelMap.put(1, "Cherries");
		reelMap.put(2, "Seven");
		reelMap.put(3, "Grapes");
		reelMap.put(4, "Lime");
		reelMap.put(5, "Cherries");
		reelMap.put(6, "Grapes");
		reelMap.put(7, "Orange");
		reelMap.put(8, "Grapes");
		reelMap.put(9, "Orange");
		reelMap.put(10, "Lime");
		reelMap.put(11, "Grapes");
		reelMap.put(12, "Cherries");
		reelMap.put(13, "Orange");
		reelMap.put(14, "Orange");
	}

	
}
