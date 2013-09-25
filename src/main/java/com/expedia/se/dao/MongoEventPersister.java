/**
 * 
 */
package com.expedia.se.dao;

import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.splunk.Event;

/**
 * @author 0030276
 * 
 */
public class MongoEventPersister implements EventPersister {

	private MongoClient dbClient;
	private String dbName;
	private String dbCollection;

	public void save(List<Event> events) {

		try {
			DB db = dbClient.getDB(dbName);
			DBCollection collection = db.getCollection(dbCollection);
			for (Event event : events) {
				//BasicDBObject doc = new BasicDBObject(event.getContents());
				BasicDBObject doc = new BasicDBObject(event);
				collection.insert(doc);
			}
		} finally {
			dbClient.close();
		}
	}

	public void setDbClient(MongoClient dbClient) {
		this.dbClient = dbClient;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public void setDbCollection(String dbCollection) {
		this.dbCollection = dbCollection;
	}



}
