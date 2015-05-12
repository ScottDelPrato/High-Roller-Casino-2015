package edu.mccc.cos210.Roulette;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Table extends JPanel implements Observer, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Color tableGreen = new Color(0, 106, 57);
	Color tableRed = new Color(218, 37, 28);
	Color tableBlack = new Color(31, 26, 23);
	double scalar = .8;
	double rectWidth = 60.0*scalar;
	double rectHeight = 100.0*scalar;
	double shiftXAll = -rectWidth;
	double shiftYAll = -60;
	double rect2Width = 0;
	double rect2Height = rectHeight*.5;
	
	public BufferedImage[] imageList = new BufferedImage[36];
	BufferedImage bi00;
	BufferedImage bi0;
	BufferedImage bi2to1;
	BufferedImage bifirst12;
	BufferedImage bisecond12;
	BufferedImage bithird12;
	BufferedImage bi1to18;
	BufferedImage bi19to36;
	BufferedImage bieven;
	BufferedImage biodd;
	BufferedImage bired;
	BufferedImage biblack;
	
	RView view;
	
	public Table(RView view) {
		super();
		this.view = view;
		setLayout(null);
		setBackground(tableGreen);
		for(int i = 0; i < 36; i++) {
			try {
				imageList[i] = ImageIO.read(new File("Numbers/"+(i+1)+".png"));
			
				bi00 = ImageIO.read(new File("Numbers/00.png"));
				bi0 = ImageIO.read(new File("Numbers/0.png"));
				bi2to1 = ImageIO.read(new File("Numbers/2to1_Small.png"));
				bifirst12 = ImageIO.read(new File("Numbers/first12.png"));
				bisecond12 = ImageIO.read(new File("Numbers/second12.png"));
				bithird12 = ImageIO.read(new File("Numbers/third12.png"));
				bi1to18 = ImageIO.read(new File("Numbers/1to18.png"));
				bi19to36 = ImageIO.read(new File("Numbers/19to36.png"));
				bieven = ImageIO.read(new File("Numbers/even.png"));
				biodd = ImageIO.read(new File("Numbers/odd.png"));
				bired = ImageIO.read(new File("Numbers/red.png"));
				biblack = ImageIO.read(new File("Numbers/black.png"));
			} catch (IOException ex) {
				System.err.println("Bad image." + i);
				System.exit(-1);
			}
		}
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension((int) (1024*scalar), (int) (768*scalar));
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		AffineTransform gat = AffineTransform.getTranslateInstance(
				getWidth() / 4.0,
				getHeight() / 2.0
			);
		gat.scale(1.0, -1.0);
		g2d.transform(gat);
		
		//start betting table
		drawTable(rectWidth, rectHeight, g2d, shiftXAll, shiftYAll);
		for (int i = 0; i < 3; i++) {
			addCustomRectangle(g2d, rectWidth*4, rect2Height, ((shiftXAll)+((rectWidth*4)*i)), shiftYAll-rect2Height, tableGreen);
		}
		for (int i = 0; i < 6; i++) {
			addCustomRectangle(g2d, rectWidth*2, rect2Height, ((shiftXAll)+((rectWidth*2)*i)), shiftYAll-(rect2Height*2), tableGreen);
		}
		drawPentagon(g2d, -64, -76);
		drawPentagon(g2d, -64, 44);
		//end betting table	
		
		//start text for betting table
		addText(g2d);
		//end text for betting table
		g2d.dispose();
	}
	
	
	
	public void drawPentagon(Graphics2D g, int x, int y) {
		Path2D.Double path = new Path2D.Double();
		path.moveTo(x, y);
		path.lineTo(x-rectWidth, y);
		path.lineTo(x-rectWidth-24, y+60);
		path.lineTo(x-rectWidth, y+120);
		path.lineTo(x, y+120);
		path.closePath();
		AffineTransform at = AffineTransform.getTranslateInstance(0, 0);
		Shape s = at.createTransformedShape(path);
		g.setPaint(Color.WHITE);
		g.setStroke(new BasicStroke(3.0f));
		g.draw(s);
	}
	
		public void addNewEllipse(Graphics2D graphic, int x, int y) {
			Ellipse2D e2d = new Ellipse2D.Double(-8.0, -8.0, 400.0, 400.0);
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			Shape s = at.createTransformedShape(e2d);
			graphic.setPaint(Color.RED);
			graphic.fill(s);
			graphic.setPaint(Color.WHITE);
			graphic.setStroke(new BasicStroke(3.0f));
			graphic.draw(s);
		}
		
		public void addCustomRectangle(Graphics2D g2d, double width, double height, double shiftX, double shiftY, Color c/*, String str*/) {
			Rectangle2D rect = new Rectangle2D.Double(-16.0, -16.0, width, height);
			AffineTransform at = AffineTransform.getTranslateInstance(shiftX, shiftY);
			Shape s = at.createTransformedShape(rect);
			g2d.setPaint(c);
			g2d.fill(s);
			g2d.setPaint(Color.WHITE);
			g2d.setStroke(new BasicStroke(3.0f));
			//g2d.drawString(str, ((int) ((rect.getWidth())/2)), ((int) ((rect.getHeight())/2)));
			g2d.draw(s);
		}
		
		public void addRedRectangle(Graphics2D g2d, double x, double y/*, String str*/) {
			Rectangle2D rect = new Rectangle2D.Double(-16.0, -16.0, rectWidth, rectHeight);
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			Shape s = at.createTransformedShape(rect);
			g2d.setPaint(tableRed);
			g2d.fill(s);
			g2d.setPaint(Color.WHITE);
			g2d.setStroke(new BasicStroke(3.0f));
			//g2d.drawString(str, ((int) ((rect.getWidth())/2)), ((int) ((rect.getHeight())/2)));
			g2d.draw(s);
			
		}
		
		public void addBlackRectangle(Graphics2D g2d, double x, double y/*, String str*/) {
			Rectangle2D rect = new Rectangle2D.Double(-16.0, -16.0, rectWidth, rectHeight);
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			Shape s = at.createTransformedShape(rect);
			g2d.setPaint(tableBlack);
			g2d.fill(s);
			g2d.setPaint(Color.WHITE);
			g2d.setStroke(new BasicStroke(3.0f));
			//g2d.drawString(str, ((int) ((rect.getWidth())/2)), ((int) ((rect.getHeight())/2)));
			g2d.draw(s);
		}
		
		public void addClearRectangle(Graphics2D g2d, double x, double y/*, String str*/) {
			Rectangle2D rect = new Rectangle2D.Double(-16.0, -16.0, rectWidth, rectHeight);
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			Shape s = at.createTransformedShape(rect);
			g2d.setPaint(Color.WHITE);
			g2d.setStroke(new BasicStroke(3.0f));
			//g2d.drawString(str, ((int) ((rect.getWidth())/2)), ((int) ((rect.getHeight())/2)));
			g2d.draw(s);
		}
		
		public void drawTable(double widthNum, double heightNum, Graphics2D gg2d, double shiftXAll2, double shiftYAll2) {
			
			int k = 1;
			double shiftX = shiftXAll2;
			double shiftY = shiftYAll2;
			
			for (int i = 0; i < 13; i++) {
				for (int j = 0; j < 3; j++) {
					if (k < 11 || (k > 18 && k < 29)) {
						if (k % 2 == 1) {
							addRedRectangle(gg2d, ((widthNum*i) + shiftX), ((heightNum*j) + shiftY)/*, Integer.toString(k)*/);
						} else {
							addBlackRectangle(gg2d, ((widthNum*i) + shiftX), ((heightNum*j) + shiftY)/*, Integer.toString(k)*/);
						}
					} else if ((k > 10 && k < 19) || (k > 28 && k < 37)) {
						if (k % 2 == 1) {
							addBlackRectangle(gg2d, ((widthNum*i) + shiftX), ((heightNum*j) + shiftY)/*, Integer.toString(k)*/);
						} else {
							addRedRectangle(gg2d, ((widthNum*i) + shiftX), ((heightNum*j) + shiftY)/*, Integer.toString(k)*/);
						}
					} else if (k > 36 && k < 40){
						addClearRectangle(gg2d, ((widthNum*i) + shiftX), ((heightNum*j) + shiftY)/*, Integer.toString(k)*/);
					}
					k++;
				}
			}
		}
		
		public void addText(Graphics2D g2d) {
			//start nums
					int r = 0;
					for (int i = 0; i < 12; i++) {
						for (int q = 0; q < 3; q++) {
							if (r < 9) {
								g2d.drawImage(imageList[r], null, (int) (-59+(rectWidth*i)), (int) (-45+(rectHeight*q)));
							} else {
								g2d.drawImage(imageList[r], null, (int) (-59+(rectWidth*i)), (int) (-45-15+(rectHeight*q)));
							}
							r++;
						}
					}
					//end nums
					//
					//start 2to1
					for (int i = 0; i < 3; i++) {
						g2d.drawImage(bi2to1, null, (int) (-59+7+(rectWidth*12)), (int) (-45-23+(rectHeight*i)));
					}
					//end 2to1
					//
					//start 0 + 00
					g2d.drawImage(bi0, null, (int) (-59-52), (int) (-45+16));
					g2d.drawImage(bi00, null, (int) (-59-52), (int) (-45+16+105));
					//end 0 + 00
					//
					//start the 12s
					g2d.drawImage(bifirst12, null, (int) (-14+(rectWidth*4*0)), -108);
					g2d.drawImage(bisecond12, null, (int) (-14+(rectWidth*4*1)), -108);
					g2d.drawImage(bithird12, null, (int) (-14+(rectWidth*4*2)), -108);
					//end the 12s
					//
					//start bottom text
					g2d.drawImage(bi1to18, null, (int) (-14-43+(rectWidth*2*0)), -108-37);
					g2d.drawImage(bieven, null, (int) (-14-43+(rectWidth*2*1)+15), -108-37);
					g2d.drawImage(bired, null, (int) (-14-43+(rectWidth*2*2)+20), -108-37);
					g2d.drawImage(biblack, null, (int) (-14-43+(rectWidth*2*3)+5), -108-37);
					g2d.drawImage(biodd, null, (int) (-14-43+(rectWidth*2*4)+15), -108-37);
					g2d.drawImage(bi19to36, null, (int) (-14-43+(rectWidth*2*5)), -108-37+2);
					//end bottom text
		}
		
		//end getters and setters for jailx and jaily
		Point convertMouseToLocal(final int x, final int y) {
			return new Point(x - getWidth() / 2, -y + getHeight() / 2);
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			
		}
}
