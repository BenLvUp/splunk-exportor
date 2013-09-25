package com.expedia.se.dao;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.MongoClient;

public class MongoEventCleanerTest {

	MongoEventCleaner eventsCleaner = new MongoEventCleaner();

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void test() {

		MongoClient mockClient = mock(MongoClient.class);
		eventsCleaner.setDbClient(mockClient);
		eventsCleaner.clear("dbName");

		verify(mockClient, times(1)).dropDatabase("dbName");
	}

}
