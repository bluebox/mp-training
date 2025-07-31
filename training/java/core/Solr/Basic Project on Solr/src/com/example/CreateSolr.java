package com.example;
import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.common.SolrInputDocument;
public class CreateSolr {
	public static void main(String[] args) throws SolrServerException, IOException {
		Http2SolrClient solr = new Http2SolrClient.Builder("http://localhost:8983/solr/#/solarSample").build();
		SolrInputDocument doc = new SolrInputDocument();
		doc.addField("ID", "OTG06641");
		doc.addField("name", "Bhanu");
		doc.addField("department","Discounts");
		doc.addField("salary", 600000);
		solr.add(doc);
		solr.commit();
		System.out.println("Document added");
	}
}
