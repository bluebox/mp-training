package com.prana;

import java.net.URI;
import java.net.URL;

public class UriUrl {
    public static void main(String[] args) throws Exception {
    	// TODO Auto-generated method stub
        URI uri = new URI("https://example.com/path?query=test");
        System.out.println("URI Scheme: " + uri.getScheme());
        System.out.println("URI Host: " + uri.getHost());
        System.out.println("URI Path: " + uri.getPath());
        URL url = uri.toURL();
        System.out.println("\nURL Protocol: " + url.getProtocol());
        System.out.println("URL Host: " + url.getHost());
        System.out.println("URL File: " + url.getFile());
    }
}