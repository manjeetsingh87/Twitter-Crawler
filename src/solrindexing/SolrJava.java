package solrindexing;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import solrserverfactory.SingletonSolrSession;

public class SolrJava {
	private static HttpSolrServer server = null;
	public void getSolrServer(String URI) {
		try {
			server = SingletonSolrSession.getInstance(URI);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public SolrDocumentList querySolr(String userQuery){
		StringBuilder querySB = new StringBuilder();
		String formattedQuery;
		SolrDocumentList docList = new SolrDocumentList();
		for (int i = 0; i < userQuery.length(); i++) {
			char c = userQuery.charAt(i);
			if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
					|| c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
					|| c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
					|| Character.isWhitespace(c)) {
				querySB.append('\\');
			}
			querySB.append(c);
		}
		formattedQuery = querySB.toString();
		SolrQuery query = new SolrQuery();
		query.setQuery(formattedQuery);
		query.setStart(0);
		query.setRows(1000);
		query.setFields("id, score");

		QueryResponse rsp;
		try {
			server.query(query);
			rsp = server.query(query);
			docList = rsp.getResults();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return docList;
	}
}