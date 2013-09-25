/**
 * 
 */
package com.expedia.se.search;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.splunk.CollectionArgs;
import com.splunk.Event;
import com.splunk.Job;
import com.splunk.JobExportArgs;
import com.splunk.MultiResultsReaderJson;
import com.splunk.MultiResultsReaderXml;
import com.splunk.ResultsReaderXml;
import com.splunk.SearchResults;
import com.splunk.Service;
import com.splunk.ServiceArgs;

/**
 * @author 0030276
 * 
 */
public class SplunkLogSearcher implements LogSearcher {

	private Service service;

	public List<Event> search(String query) { // fromTime to toTime
		// Set up the job properties
		String mySearch = "search " + query;

		JobExportArgs jobArgs = new JobExportArgs();
		jobArgs.setOutputMode(JobExportArgs.OutputMode.JSON);

		// Create the job
		InputStream stream = service.export(mySearch, jobArgs);

		List<Event> events = new ArrayList<Event>();
		// Display previews
		try {

			MultiResultsReaderJson multiResultsReader = new MultiResultsReaderJson(stream);

			int counterSet = 0;

			for (SearchResults searchResults : multiResultsReader) {
				// Display whether the results is a preview (search in progress)
				// or
				// final (search is finished)
				String resultSetType = searchResults.isPreview() ? "Preview" : "Final";
				System.out.println(resultSetType + " result set " + counterSet++ + " ********");
				// if the type is Final means you've done the search, then store
				if (resultSetType.equals("Final")) {
//					int counterEvent = 0;
					for (Event event : searchResults) {
//						System.out.println("Event " + counterEvent++ + " --------");
//						for (String key : event.keySet())
//							System.out.println("   " + key + ":  " + event.get(key));
//						// insert to db
						events.add(event);
					}
				}
			}

			multiResultsReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return events;

	}

	public void setService(Service service) {
		this.service = service;

	}
}
