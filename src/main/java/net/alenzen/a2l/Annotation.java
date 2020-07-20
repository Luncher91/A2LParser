package net.alenzen.a2l;

import java.util.ArrayList;
import java.util.List;

public class Annotation {
	private String label;
	private String origin;
	private List<String> text = new ArrayList<String>();
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
}
