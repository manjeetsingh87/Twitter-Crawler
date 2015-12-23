package solrindexing;

public class SolrIndexData {
	public static void indexData(){
		SolrJava solrjava = new SolrJava();
		String url="http://ssg8.koding.io:8983/solr/projectb/";
		solrjava.getSolrServer(url);
	}
}
