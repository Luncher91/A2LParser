package net.alenzen.a2l;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Annotation extends A2LSerializer implements IA2LWriteable, IAsap2TreeElement {
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Annotation that = (Annotation) o;
		return Objects.equals(label, that.label) && Objects.equals(origin, that.origin) && Objects
				.equals(text, that.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(label, origin, text);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		ArrayList<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.text);
		return subNodes;
	}
}
