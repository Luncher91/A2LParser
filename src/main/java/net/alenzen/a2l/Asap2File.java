package net.alenzen.a2l;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

public class Asap2File extends A2LSerializer {
	private A2mlVersion a2mlVersion;
	private Asap2Version asap2Version;
	private Project project;

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project p) {
		project = p;
	}

	public Asap2Version getAsap2Version() {
		return asap2Version;
	}

	public void setAsap2Version(Asap2Version asap2Version) {
		this.asap2Version = asap2Version;
	}

	public A2mlVersion getA2mlVersion() {
		return a2mlVersion;
	}

	public void setA2mlVersion(A2mlVersion a2mlVersion) {
		this.a2mlVersion = a2mlVersion;
	}

	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		return toJson(false, false);
	}
	
	public String toMinimizedJson() throws JsonGenerationException, JsonMappingException, IOException {
		return toJson(true, false);
	}
	
	public String toJson(boolean excludeNull, boolean indent) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		
		if(excludeNull) {
			objectMapper.setSerializationInclusion(Include.NON_NULL);
		}
		
		if(indent) {
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		
		return objectMapper.writeValueAsString(this);
	}

	public static String generateJsonSchema() throws JsonProcessingException {
		return generateJsonSchema(false, false);
	}
	
	public static String generateJsonSchema(boolean excludeNull, boolean indent) throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		if(excludeNull) {
			mapper.setSerializationInclusion(Include.NON_NULL);
		}
		
		if(indent) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}
		
		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
		return mapper.writeValueAsString(schemaGen.generateSchema(Asap2File.class));
	}

	public static Asap2File fromJsonFile(String jsonFilename) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new File(jsonFilename), Asap2File.class);
	}

	public static Asap2File fromJsonStream(InputStream jsonInput) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(jsonInput, Asap2File.class);
	}

	public static Asap2File fromJson(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, Asap2File.class);
	}

	public String toA2L() {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		A2LWriter writer = new A2LWriter(bos);
		try {
			this.toA2L(writer);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return new String(bos.toByteArray(), writer.getCharset());
	}

	protected void toA2L(A2LWriter writer) throws IOException {
		writer.write(asap2Version);
		writer.write(a2mlVersion);
		writer.write(project);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Asap2File asap2File = (Asap2File) o;
		return Objects.equals(a2mlVersion, asap2File.a2mlVersion) && Objects
				.equals(asap2Version, asap2File.asap2Version) && Objects.equals(project, asap2File.project);
	}

	@Override
	public int hashCode() {
		return Objects.hash(a2mlVersion, asap2Version, project);
	}
}
