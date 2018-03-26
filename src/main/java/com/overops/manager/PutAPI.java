package com.overops.manager;

import java.util.Set;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.overops.domain.AttributeType;
import com.overops.domain.Credentials;
import com.overops.util.DataRedactionException;

public class PutAPI {

	private final static Logger log = LoggerFactory.getLogger(PutAPI.class);
	private Credentials credentials = null;

	public PutAPI(Credentials credentials) {
		this.credentials = credentials;
	}

	public void process(JSONObject fromJSONObject) {
		try {
			log.info("Importing All Data Redaction Attributes ...");
			AttributeManager attrMgr = new AttributeManager(credentials, fromJSONObject);
			Set<String> fromKeySet = fromJSONObject.keySet();

			handleAttributeType(attrMgr, fromKeySet, AttributeType.IDENTIFIERS);
			handleAttributeType(attrMgr, fromKeySet, AttributeType.CLASSES);
			handleAttributeType(attrMgr, fromKeySet, AttributeType.PACKAGES);
			handleAttributeType(attrMgr, fromKeySet, AttributeType.PATTERNS);
			
			log.info("Import complete.");
		} catch(UnirestException e) {
			throw new DataRedactionException(e);
		}
	}

	private void handleAttributeType(AttributeManager attrMgr, 
			Set<String> fromKeySet, String attributeType) throws UnirestException {
		if (fromKeySet.contains(attributeType)) {
			log.info("Lets handle attributeType: " + attributeType);
			attrMgr.handleAttribute(attributeType);
			log.info("Successfully able to import All Data Redaction for attributeType: " + attributeType);
		} else {
			log.warn("Nothing found for attributeType: " + attributeType);
		}
	}
}
