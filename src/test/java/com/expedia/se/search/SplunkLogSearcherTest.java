/**
 * 
 */
package com.expedia.se.search;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

import com.splunk.Event;
import com.splunk.JobExportArgs;
import com.splunk.MultiResultsReaderJson;
import com.splunk.ResultsReader;
import com.splunk.SearchResults;
import com.splunk.Service;

/**
 * @author 0030276
 * 
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({ MultiResultsReaderJson.class, Service.class })
@SuppressStaticInitializationFor("com.splunk.HttpService")
public class SplunkLogSearcherTest {

	private Service mockService;

	private SplunkLogSearcher searcher;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mockService = mock(Service.class);

		searcher = new SplunkLogSearcher();
		searcher.setService(mockService);
	}

	
	
	@Test
	public void testForEmpty() {

		// Set expected behavior for Mock
        String jsonResult = "{\"preview\":true,\"offset\":4,\"lastrow\":true,\"result\":{\"sourcetype\":\"splunkd_stderr\",\"count\":\"2\"}}";
        Event mockEvent = mock(Event.class);
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(jsonResult.getBytes());
		when(mockService.export(anyString(), any(JobExportArgs.class))).thenReturn(
				inputStream);
		SearchResults mockResults = mock(SearchResults.class);
		//when(mockResults.isPreview()).thenReturn(false);
		// Prepare data

		// Call testing method
		List<com.splunk.Event> list = searcher.search("search");

		// Verify
		verify(mockService).export(anyString(), any(JobExportArgs.class));
		assertEquals(0,list.size());
		
	}
	
	
	@Test
	public void testWithData() {

		// Set expected behavior for Mock
        String jsonResult = "{\"preview\":false,\"offset\":4,\"lastrow\":true,\"result\":{\"sourcetype\":\"splunkd_stderr\",\"count\":\"2\"}}";
        Event mockEvent = mock(Event.class);
        
        ByteArrayInputStream inputStream = new ByteArrayInputStream(jsonResult.getBytes());
		when(mockService.export(anyString(), any(JobExportArgs.class))).thenReturn(
				inputStream);
		SearchResults mockResults = mock(SearchResults.class);
		//when(mockResults.isPreview()).thenReturn(false);
		// Prepare data

		// Call testing method
		List<com.splunk.Event> list = searcher.search("search");

		// Verify
		verify(mockService).export(anyString(), any(JobExportArgs.class));
		assertEquals(1,list.size());
		
	}

	
}
