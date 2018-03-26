package com.overops.main;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.overops.domain.Credentials;
import com.overops.manager.CredentialsManager;
import com.overops.manager.GetAPI;
import com.overops.manager.PutAPI;

public class DataRedactionMain {
	
	private final static Logger log = LoggerFactory.getLogger(DataRedactionMain.class);
    
	public static void main(String[] args) {
		try {
			CredentialsManager credMgr = new CredentialsManager();
			Credentials fromCredentials = credMgr.getFromCredentials(args);
			Credentials toCredentials = credMgr.getToCredentials(args);
			
			GetAPI getAPI = new GetAPI(fromCredentials);
			JSONObject fromJSONObject = getAPI.getAllDataRedaction();
			
			PutAPI putAPI = new PutAPI(toCredentials);
			putAPI.process(fromJSONObject);
		} catch (Exception e) {
			log.error("Could not complete Export/Import ", e);
		}
	}
}