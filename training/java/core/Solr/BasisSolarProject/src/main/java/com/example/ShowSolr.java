package com.example;
import java.io.IOException;
import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
public class ShowSolr {
	public static void main(String[] args) throws SolrServerException, IOException {
		Http2SolrClient solr = new Http2SolrClient.Builder("http://localhost:8983/solr/sampleSolr").build();
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("*:*");
		solrQuery.addField("*");
		QueryResponse res = solr.query(solrQuery);
		SolrDocumentList doc1 = res.getResults();
		Iterator<SolrDocument> res1 = doc1.iterator();
		while(res1.hasNext()) {
			System.out.println(res1.next());
		}
		System.out.println(doc1.getNumFound());
	}
}