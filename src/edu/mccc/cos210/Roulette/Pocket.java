package edu.mccc.cos210.Roulette;

public class Pocket {
	
	protected final String value;   
	              
	protected final String color; 
	             
	protected Pocket(String theValue, String theColor) {
		value = theValue;
		color = theColor;
	}
	
	protected String getColor() {
		return color;
	}
	
	protected String getValueAsString() {
		return value;
	}
	protected int getValueAsInt() {
		return Integer.parseInt(value);
	}
}
