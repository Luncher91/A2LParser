package net.alenzen.a2l;

public class Annotation {
	private String label;
	private String origin;
	private AnnotationText text = new AnnotationText();
	
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
	public AnnotationText getText() {
		return text;
	}
	public void setText(AnnotationText text) {
		this.text = text;
	}
}
