package com.qubole.sdk;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;

import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.UnsupportedEncodingException;

public class Connection {
    private HttpClient httpclient;
    
    public Connection() {
        ClientConnectionManager mgr = null;
        mgr = new ThreadSafeClientConnManager();
        ((ThreadSafeClientConnManager) mgr).setDefaultMaxPerRoute(10);
        
        this.setHttpclient(new DefaultHttpClient(mgr));
    }
    
    public void setHttpclient(HttpClient httpclient) {
		this.httpclient = httpclient;
	}

	public HttpClient getHttpClient() {
		return httpclient;
    }
    
    private HttpUriRequest buildMethod(String method, String path, List<NameValuePair> params) {
		if (method.equalsIgnoreCase("GET")) {
			return generateGetRequest(path, params);
		} else if (method.equalsIgnoreCase("POST")) {
			return generatePostRequest(path, params);
		} else if (method.equalsIgnoreCase("PUT")) {
			return generatePutRequest(path, params);
		} else if (method.equalsIgnoreCase("DELETE")) {
			return generateDeleteRequest(path, params);
		} else {
			throw new IllegalArgumentException("Unknown Method: " + method);
		}
	}
    
    private URI buildUri(String path) {
		return buildUri(path, null);
	}
    
    private URI buildUri(String path, List<NameValuePair> queryStringParams) {
		StringBuilder sb = new StringBuilder();
		sb.append(path);

		if (queryStringParams != null && queryStringParams.size() > 0) {
			sb.append("?");
			sb.append(URLEncodedUtils.format(queryStringParams, "UTF-8"));
		}

		URI uri;
		try {
			uri = new URI(sb.toString());
		} catch (URISyntaxException e) {
			throw new IllegalStateException("Invalid uri", e);
		}

		return uri;
	}
    
    private HttpGet generateGetRequest(String path, List<NameValuePair> params) {

		URI uri = buildUri(path, params);
		return new HttpGet(uri);
	}
    
    private UrlEncodedFormEntity buildEntityBody(List<NameValuePair> params) {
		UrlEncodedFormEntity entity = null;
		try {
			entity = new UrlEncodedFormEntity(params, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return entity;
	}
    
    private HttpPost generatePostRequest(String path, List<NameValuePair> params) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildEntityBody(params);

		HttpPost post = new HttpPost(uri);
		post.setEntity(entity);

		return post;
	}
    
    private HttpPut generatePutRequest(String path, List<NameValuePair> params) {
		URI uri = buildUri(path);

		UrlEncodedFormEntity entity = buildEntityBody(params);

		HttpPut put = new HttpPut(uri);
		put.setEntity(entity);

		return put;
	}
    
    private HttpDelete generateDeleteRequest(String path,
			List<NameValuePair> params) {
		URI uri = buildUri(path);

		HttpDelete delete = new HttpDelete(uri);
		return delete;
	}
    
    


}
