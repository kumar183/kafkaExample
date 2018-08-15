package com.info.kafka.jsonserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.kafka.model.SystemDetails;

public class JsonSerializer  {

	public static String serialize(SystemDetails data) {
		String value = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			value = objectMapper.writeValueAsString(data);
		} catch (Exception exception) {
			System.out.println("Exception in serializing object" + data);
		}
		return value;
	}

}