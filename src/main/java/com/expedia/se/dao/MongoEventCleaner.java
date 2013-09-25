package com.expedia.se.dao;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.MongoClient;

/**
 * to clean the database
 * 
 * @author Ben Aug 19, 2013 5:23:16 PM 
 */
public class MongoEventCleaner implements EventCleaner {

	MongoClient dbClient;

	public void setDbClient(MongoClient client){
		this.dbClient=client;
	}
	
	public  void clear(String dbName) {
		try {
			dbClient.dropDatabase(dbName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
