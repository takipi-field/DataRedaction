package com.overops.manager;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.overops.domain.Credentials;
import com.overops.util.DataRedactionException;
import com.overops.util.DataRedactionPropertyReader;

public class AttributeManager {
	
	private final static Logger log = LoggerFactory.getLogger(AttributeManager.class);
	private Credentials credentials = null;
	JSONObject fromJSONObject = null;

	public AttributeManager(Credentials credentials, JSONObject fromJSONObject) {
		this.credentials = credentials;
		this.fromJSONObject = fromJSONObject;
	}

	public void handleAttribute(String attributeType) throws UnirestException {

		String addIdentifierPath = getAddPath(attributeType);
		JSONArray attributes = (JSONArray) fromJSONObject.get(attributeType);
		
		for (int i = 0; i < attributes.length(); i++) {
			JSONObject obj = attributes.getJSONObject(i);
			String value = (String) obj.get("name");
			String newAddIdentifierPath = addIdentifierPath.replace("{attributeName}", value);
			
			HttpResponse<String> response = Unirest.put(newAddIdentifierPath)
			.basicAuth(credentials.getUsername(), credentials.getPassword())
			.header("content-type", "application/json")
			.body(new JsonNode(obj.toString()))
			.asString();
			int status = response.getStatus();
			
			if (status != 200) {
				handleError(newAddIdentifierPath, response);
			}
		}
	}

	private String getAddPath(String attributeType) {
		String uriPath = DataRedactionPropertyReader.
			getInstance().getProperty("ADD_ATTRIBUTE_PATH");
		String uriPath1 = uriPath.replace("{ServiceID}", credentials.getServiceID());
		return uriPath1.replace("{attributeType}", attributeType);
	}

	private void handleError(String uriPath, HttpResponse<String> response) throws UnirestException {
		log.error("Error in import. Could not connect to: " + uriPath);
		log.error("Error is: " + response.getBody());
		throw new DataRedactionException("Error in Import. " + 
				"Could not connect to PATH/Username below: " + 
				"\n PATH: " + uriPath +
				"\n Username: " + credentials.getUsername());
	}
}
