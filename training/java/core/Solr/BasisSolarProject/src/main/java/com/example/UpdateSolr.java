package com.example;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.Http2SolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrInputDocument;

public class UpdateSolr {
	public static void main(String[] args) throws SolrServerException, IOException {
		Http2SolrClient solr = new Http2SolrClient.Builder("http://localhost:8983/solr/sampleSolr").build();
		SolrInputDocument doc = new SolrInputDocument();
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.setAction(UpdateRequest.ACTION.COMMIT, false, false);
		SolrInputDocument myDocumentInstantlycommited = new SolrInputDocument();
		myDocumentInstantlycommited.addField("id", "OTG06641");
		myDocumentInstantlycommited.addField("name", "Rahman");
		myDocumentInstantlycommited.addField("Department", "Designer");
		myDocumentInstantlycommited.addField("Salary", 50000);
		updateRequest.add(myDocumentInstantlycommited);
		UpdateResponse rsp = updateRequest.process(solr);
		System.out.println("Documents Updated");
	}
}
