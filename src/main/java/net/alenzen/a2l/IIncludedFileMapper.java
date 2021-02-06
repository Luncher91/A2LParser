package net.alenzen.a2l;

import java.io.FileNotFoundException;
import java.io.InputStream;

public interface IIncludedFileMapper {
	InputStream includeMao(String includedFilename) throws FileNotFoundException;
}
