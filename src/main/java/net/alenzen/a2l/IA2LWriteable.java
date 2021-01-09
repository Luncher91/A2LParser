package net.alenzen.a2l;

import java.io.IOException;

public interface IA2LWriteable {
	void writeTo(A2LWriter writer) throws IOException;
}
