/**
 * 
 */
package com.expedia.se.dao;

import java.util.List;
import com.splunk.Event;

/**
 * @author 0030276
 * 
 */
public interface EventPersister {

	void save(List<Event> events);

}
