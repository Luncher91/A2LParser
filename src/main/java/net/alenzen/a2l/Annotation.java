package net.alenzen.a2l;

import java.io.IOException;

public class Annotation implements IA2LWriteable {
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

	@Override
	public void writeTo(A2LWriter writer) throws IOException {
		writer.writelnBeginSpaced("ANNOTATION");
		writer.indent();
		
		if(label != null) {
			writer.writelnSpaced("ANNOTATION_LABEL", A2LWriter.toA2LString(label));
		}
		
		if(origin != null) {
			writer.writelnSpaced("ANNOTATION_ORIGIN", A2LWriter.toA2LString(origin));
		}
		
		writer.write(text);
		
		writer.dedent();
		writer.writelnEnd("ANNOTATION");
	}
}
