package com.overops.manager;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.overops.domain.Credentials;
import com.overops.util.DataRedactionException;
import com.overops.util.DataRedactionPropertyReader;

public class GetAPI {

	private final static Logger log = LoggerFactory.getLogger(GetAPI.class);
	private Credentials credentials = null;

	public GetAPI(Credentials credentials) {
		this.credentials = credentials;
	}

	public JSONObject getAllDataRedaction() {
		try {
			String uriPath = getURIPath(credentials);
			GetRequest request = Unirest.get(uriPath).basicAuth(
				credentials.getUsername(), credentials.getPassword());
			int status = request.asString().getStatus();
			if (status == 200) {
				log.info("Successfully able to export All Data Redaction from host.");
				return request.asJson().getBody().getObject();
			} else {
				handleError(uriPath, request);
				return null;
			}
		} catch (UnirestException e) {
			throw new DataRedactionException(e);
		}
	}

	private String getURIPath(Credentials credentials) {
		String uriPath = DataRedactionPropertyReader.
			getInstance().getProperty("LIST_ALL_DATA_REDACTION_PATH");
		return uriPath.replace("{ServiceID}", credentials.getServiceID());
	}

	private void handleError(String uriPath, GetRequest request) throws UnirestException {
		log.error("Error in export. Could not connect to: " + uriPath);
		log.error("Error is: " + request.asString().getBody());
		throw new DataRedactionException("Error in export. " + 
				"Could not connect to PATH/Username below: " + 
				"\n PATH: " + uriPath +
				"\n Username: " + credentials.getUsername());
	}
}
