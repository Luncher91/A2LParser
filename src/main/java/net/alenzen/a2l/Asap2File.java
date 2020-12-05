package net.alenzen.a2l;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

public class Asap2File {
	private Version a2mlVersion;
	private Version asap2Version;
	private Project project;

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project p) {
		project = p;
	}

	public Version getAsap2Version() {
		return asap2Version;
	}

	public void setAsap2Version(Version asap2Version) {
		this.asap2Version = asap2Version;
	}

	public Version getA2mlVersion() {
		return a2mlVersion;
	}

	public void setA2mlVersion(Version a2mlVersion) {
		this.a2mlVersion = a2mlVersion;
	}
	
	public String toJson() throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(this);
	}
	
	public static String generateJsonSchema() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
		return mapper.writeValueAsString(schemaGen.generateSchema(Asap2File.class));
	}
	
	public static Asap2File fromJsonFile(String jsonFilename) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(new File(jsonFilename), Asap2File.class);
	}
	
	public static Asap2File fromJson(String json) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, Asap2File.class);
	}
}
