package com.example;
import java.io.IOException;
import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
public class DeleteSolr {
	public static void main(String[] args) throws SolrServerException, IOException {
		Http2SolrClient solr = new Http2SolrClient.Builder("http://localhost:8983/solr/sampleSolr").build();
		UpdateResponse x = solr.deleteByQuery("ID:OTG06641");
		System.out.println(x.getResponse());
	}
}
