package com.expedia.app.resource;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import com.expedia.app.resource.AppProperties;

public class AppPropertiesTest {

	AppProperties properties;
	@Before
	public void setUp() throws Exception {
		properties = new AppProperties();
	}

	@Test
	public void test() {
		
		String db=properties.getProperties("dbName");
		String host=properties.getProperties("");
		String port=properties.getProperties("xxxx");
		
		//System.out.print(db);
		assertEquals("",host);
		assertEquals("eqcdata",db);
	}	

}
