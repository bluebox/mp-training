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
public class CreateSolr {
	public static void main(String[] args) throws SolrServerException, IOException {
		Http2SolrClient solr = new Http2SolrClient.Builder("http://localhost:8983/solr/sampleSolr").build();
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("id", "OTG06641");
		doc.addField("name", "Bhanu");
		doc.addField("department","Discounts");
		doc.addField("salary", 600000);
		solr.add(doc);
		System.out.println("Document added");
	}
}
