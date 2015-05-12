package edu.mccc.cos210.Slots;

import java.util.ArrayList;
import javax.swing.ImageIcon;

public class SFruit {
    public final static int SEVEN = 0,
    						LIME = 1,
    						CHERRIES = 2,
    						GRAPES = 3,
    						ORANGE = 4;
    private final int fruit;
    private ImageIcon icon; 
    public SFruit(int fruitytooty) {
    	fruit = fruitytooty;
        icon = new ImageIcon("fruit/" + getFruitAsString() +  ".png");
    } 
    public int getFruit() {
        return fruit;
    }
    public ImageIcon getIcon() {
    	return icon;
    }
    public String getFruitAsString() {
        switch ( fruit ) {
           case SEVEN:   	return "Seven";
           case LIME:   	return "Lime";
           case CHERRIES: 	return "Cherry";
           case GRAPES:    	return "Grape";
           case ORANGE:		return "Orange";
           default:       	return "??";
        }
    }
}
class GroupFruit {
	private ArrayList<SFruit> groupfruit;
	public GroupFruit() {
		 groupfruit = new ArrayList<SFruit>();
	}
	public void addFruit(SFruit f) {
	    if (f == null)
	       throw new NullPointerException("Can't add a null card to a hand.");
	    groupfruit.add(f);
	 } 
	   public SFruit getFruit(int position) {
		      if (position < 0 || position >= groupfruit.size())
		         throw new IllegalArgumentException("Position does not exist in hand: "
		               + position);
		       return (SFruit)groupfruit.get(position);
		   }
}