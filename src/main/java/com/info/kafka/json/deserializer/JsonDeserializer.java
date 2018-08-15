package com.info.kafka.json.deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.kafka.model.SystemDetails;

public class JsonDeserializer {

	public SystemDetails deserialize(String data) {
		ObjectMapper mapper = new ObjectMapper();
		SystemDetails object = null;
		try {
			object = mapper.readValue(data, SystemDetails.class);
		} catch (Exception exception) {
			System.out.println("Exception in deserializing string " + exception);
		}
		return object;
	}
}