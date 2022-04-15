package net.alenzen.a2l;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.JsonSchemaGenerator;

public class Asap2File extends A2LSerializer implements Iterable<IAsap2TreeElement>, IAsap2TreeElement {
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

	public String toJson(boolean excludeNull, boolean indent)
			throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();

		if (excludeNull) {
			objectMapper.setSerializationInclusion(Include.NON_NULL);
		}

		if (indent) {
			objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		}

		return objectMapper.writeValueAsString(this);
	}

	public static String generateJsonSchema() throws JsonProcessingException {
		return generateJsonSchema(false, false);
	}

	public static String generateJsonSchema(boolean excludeNull, boolean indent)
			throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		if (excludeNull) {
			mapper.setSerializationInclusion(Include.NON_NULL);
		}

		if (indent) {
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
		}

		JsonSchemaGenerator schemaGen = new JsonSchemaGenerator(mapper);
		JsonSchema schema = schemaGen.generateSchema(Asap2File.class);
		String schemaString = mapper.writeValueAsString(schema);

		JsonNode node = mapper.readTree(schemaString);

		HashMap<String, String> idPathMap = new HashMap<String, String>();
		collectPathIds(node, "#", idPathMap);
		
		replaceReferences(node, idPathMap);
		removeNamespaces(node);

		return mapper.writeValueAsString(node);
	}

	private static void replaceReferences(JsonNode node, HashMap<String, String> idPathMap) {
		if (node.isObject()) {
			for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
				Map.Entry<String, JsonNode> item = iter.next();
				if(item.getValue().isTextual() && item.getKey().equals("$ref")) {
					String newRef = idPathMap.get(item.getValue().asText());
					ObjectNode nodeAsObject = (ObjectNode) node;
					nodeAsObject.put("$ref", newRef);
				} else {
					replaceReferences(item.getValue(), idPathMap);
				}
			}
		} else if(node.isArray()) {
			for (Iterator<JsonNode> iter = node.iterator(); iter.hasNext();) {
				JsonNode item = iter.next();
				replaceReferences(item,idPathMap);
			}
		}
	}

	private static void removeNamespaces(JsonNode node) {
		if (node.isObject()) {
			for (Iterator<Entry<String, JsonNode>> iter = node.fields(); iter.hasNext();) {
				Map.Entry<String, JsonNode> item = iter.next();
				if(item.getValue().isTextual() && item.getKey().equals("id")) {
					String newId = removeNamespace(item.getValue().asText());
					ObjectNode nodeAsObject = (ObjectNode) node;
					nodeAsObject.put("id", newId);
				} else {
					removeNamespaces(item.getValue());
				}
			}
		} else if(node.isArray()) {
			for (Iterator<JsonNode> iter = node.iterator(); iter.hasNext();) {
				JsonNode item = iter.next();
				removeNamespaces(item);
			}
		}
	}

	private static String removeNamespace(String asText) {
		String[] namespacePath = asText.split(":");
		if(namespacePath.length > 0) {
			return namespacePath[namespacePath.length - 1];
		} else {
			return asText;
		}
	}

	private static void collectPathIds(JsonNode schema, String currentPath, Map<String, String> map) {
		if (schema.isObject()) {
			for (Iterator<Entry<String, JsonNode>> iter = schema.fields(); iter.hasNext();) {
				Map.Entry<String, JsonNode> item = iter.next();
				if(item.getValue().isTextual() && item.getKey().equals("id")) {
					map.put(item.getValue().asText(), currentPath);
				} else {
					collectPathIds(item.getValue(), currentPath + "/" + item.getKey(), map);
				}
			}
		}
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

	public void toA2L(A2LWriter writer) throws IOException {
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
		return Objects.equals(a2mlVersion, asap2File.a2mlVersion)
				&& Objects.equals(asap2Version, asap2File.asap2Version) && Objects.equals(project, asap2File.project);
	}

	@Override
	public int hashCode() {
		return Objects.hash(a2mlVersion, asap2Version, project);
	}

	@Override
	public Asap2FileIterator iterator() {
		return new Asap2FileIterator(this);
	}

	@Override
	public List<IAsap2TreeElement> collectSubNodes() {
		ArrayList<IAsap2TreeElement> subNodes = new ArrayList<IAsap2TreeElement>();
		Asap2FileIterator.addIfNotNull(subNodes, this.a2mlVersion);
		Asap2FileIterator.addIfNotNull(subNodes, this.asap2Version);
		Asap2FileIterator.addIfNotNull(subNodes, this.project);
		return subNodes;
	}
}
