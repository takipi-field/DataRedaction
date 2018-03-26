package com.overops.manager;

import java.io.Console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.overops.domain.Credentials;

public class CredentialsManager {

	private final static Logger log = LoggerFactory.getLogger(CredentialsManager.class);

	public Credentials getFromCredentials(String[] args) {
		java.io.Console console = System.console();
		if (console == null) {
			log.info("Using 'Export From' credentials from Arguments: ");
			return getFromCredentialsArgs(args);
		} else {
			log.info("Lets get the Overops Credentials to Export from: ");
			System.out.println("Lets get the Overops Credentials to Export From: ");
			return getCredentials(console);
		}
	}
	
	public Credentials getToCredentials(String[] args) {
		java.io.Console console = System.console();
		if (console == null) {
			log.info("Using 'Import To' credentials from Arguments: ");
			return getToCredentialsArgs(args);
		} else {
			log.info("Lets get the Overops Credentials to Import To: ");
			System.out.println("Lets get the Overops Credentials to Import To: ");
			return getCredentials(console);
		}
	}

	private Credentials getCredentials(Console console) {
		Credentials credentials = new Credentials();
		credentials.setUsername(console.readLine("Username: "));
		credentials.setPassword(new String(console.readPassword("Password: ")));
		credentials.setServiceID(console.readLine("ServiceID: "));
		return credentials;
	}
	
	private Credentials getFromCredentialsArgs(String[] args) {
		Credentials credentials = new Credentials();
		if (args != null && args.length != 0) {
			int i = 0;
			while (i < args.length) {
				if (args[i].contains("fromUsername")) {
					String fromUsername = args[i].substring(13);
					credentials.setUsername(fromUsername);
				}
				if (args[i].contains("fromPassword")) {
					String fromPassword = args[i].substring(13);
					credentials.setPassword(fromPassword);
				}
				if (args[i].contains("fromServiceID")) {
					String fromServiceID = args[i].substring(14);
					credentials.setServiceID(fromServiceID);
				}				
				i++;
			}
		}
		return credentials;
	}
	
	private Credentials getToCredentialsArgs(String[] args) {
		Credentials credentials = new Credentials();
		if (args != null && args.length != 0) {
			int i = 0;
			while (i < args.length) {
				if (args[i].contains("toUsername")) {
					String fromUsername = args[i].substring(11);
					credentials.setUsername(fromUsername);
				}
				if (args[i].contains("toPassword")) {
					String fromPassword = args[i].substring(11);
					credentials.setPassword(fromPassword);
				}
				if (args[i].contains("toServiceID")) {
					String fromServiceID = args[i].substring(12);
					credentials.setServiceID(fromServiceID);
				}				
				i++;
			}
		}
		return credentials;
	}
}
