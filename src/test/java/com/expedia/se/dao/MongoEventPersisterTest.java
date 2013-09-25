/**
 * 
 */
package com.expedia.se.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.splunk.Event;

/**
 * @author 0030276
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class MongoEventPersisterTest {

	@Mock
	private MongoClient dbClient;

	private MongoEventPersister persister;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		// Setup MongoEventPersister v
		persister = new MongoEventPersister();
		persister.setDbClient(dbClient);
		persister.setDbName("EventDB");
		persister.setDbCollection("EventColl");

	}
	

	/**
	 * Test method for
	 * {@link com.expedia.se.dao.MongoEventPersister#save(java.util.List)}.
	 */
	@Test
	public void testSaveForNoEvent() {

		// Set expected behavior for Mock
		DBCollection mockCollection = mock(DBCollection.class);

		DB mockDB = mock(DB.class);
		
		
		when(mockDB.getCollection(anyString())).thenReturn(mockCollection);
		when(dbClient.getDB(anyString())).thenReturn(mockDB);

		// Prepare data
		List<Event> events = Collections.emptyList();

		// Call testing method
		persister.save(events);

		// Verify
		verify(dbClient, times(1)).getDB(anyString());
		verify(mockCollection, never()).insert((DBObject) anyVararg());
	}

	/**
	 * Test method for
	 * {@link com.expedia.se.dao.MongoEventPersister#save(java.util.List)}.
	 */
	@Test
	public void testSaveForMultipleEvents() {

		// Set expected behavior for Mock
		DBCollection mockCollection = mock(DBCollection.class);
		DB mockDB = mock(DB.class);
		Event mockEvent =mock(Event.class);
		when(mockDB.getCollection(anyString())).thenReturn(mockCollection);
		when(dbClient.getDB(anyString())).thenReturn(mockDB);
		
		// Prepare data
		List<Event> events = new ArrayList<Event>();
		
		events.add(mockEvent);
		events.add(mockEvent);
		events.add(mockEvent);
		
		// Call testing method
		persister.save(events);

		// Verify
		verify(dbClient, times(1)).getDB(anyString());
		verify(mockCollection, times(3)).insert((DBObject) anyVararg());
	}

}
