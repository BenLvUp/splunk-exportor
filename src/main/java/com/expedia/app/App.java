package com.expedia.app;

import java.net.UnknownHostException;

import com.expedia.app.resource.AppProperties;
import com.expedia.se.dao.MongoEventPersister;
import com.expedia.se.search.SplunkLogSearcher;
import com.mongodb.MongoClient;
import com.splunk.Service;
import com.splunk.ServiceArgs;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String dbName = AppProperties.getProperties("dbName");
    	String dbCollection = AppProperties.getProperties("dbCollection");
    	
    	MongoClient dbClient = null;
		try {
			dbClient = new MongoClient();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	//Setup MongoEventPersister
    	MongoEventPersister persister = new MongoEventPersister();
    	persister.setDbClient(dbClient);
    	persister.setDbName(dbName);
    	persister.setDbCollection(dbCollection);
    	
    	//Setup SplunkLogSearcher
    	SplunkLogSearcher logSearcher = new SplunkLogSearcher();
    	//create a map of arguments and add login parameters
    	
    	//get splunk data
    	String splunkUser=AppProperties.getProperties("userName");
    	String splunkPassword=AppProperties.getProperties("password");
    	String splunkHost=AppProperties.getProperties("host");
    	int splunkPort=Integer.parseInt(AppProperties.getProperties("port"));
    	
    			
    	ServiceArgs loginArgs = new ServiceArgs();
    	loginArgs.setUsername(splunkUser);
    	loginArgs.setPassword(splunkPassword);
    	loginArgs.setHost(splunkHost);
    	loginArgs.setPort(splunkPort);
    	
    	Service service = Service.connect(loginArgs);
    	
    	logSearcher.setService(service);

    	//logSearcher.search("*");
    	
    	//persister.save(logSearcher.search("*"));

    	
    	
        System.out.println( splunkHost);
    }
}
