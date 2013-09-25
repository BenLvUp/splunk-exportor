package com.expedia.se.search;

import java.util.List;

import com.splunk.Event;

public interface LogSearcher {

	public List<Event> search(String query);

}
