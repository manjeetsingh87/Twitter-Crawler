package solrserverfactory;

import org.apache.solr.client.solrj.impl.HttpSolrServer;

public class SingletonSolrSession {
	
	public static HttpSolrServer serverInstance = null;
    
	private SingletonSolrSession() {
        // private constructor
    }
 
    public static synchronized HttpSolrServer getInstance(String URI) throws Exception{
        if(null == serverInstance);
        	serverInstance = new HttpSolrServer(URI);
        
        return serverInstance;	
    }
}