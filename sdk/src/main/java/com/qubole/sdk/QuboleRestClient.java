/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qubole.sdk;
import org.apache.http.client.HttpClient;

public class QuboleRestClient {
    private String api_token;
    private String base_url = "https://api.qubole.com/api/";
    private String version = "v1.2";
    private int poll_interval = 5;
    private boolean skip_ssl_cert_check = false;
    private Account authAccount;
    private Connection cached_agent = null;
    
    //Overload constructor to support other varieties
    public QuboleRestClient(String api_token, String api_url, String version, int poll_interval, boolean skip_ssl_cert_check) {
		authAccount.setAuthToken(api_token);
        this.base_url = api_url;
        this.version = version;
        this.poll_interval = poll_interval;
        this.skip_ssl_cert_check = skip_ssl_cert_check;
	}
    
    public Connection QuboleAgent() {
        if(cached_agent == null) {
            Connection conn = new Connection();
            cached_agent = conn;
        }
        return cached_agent;
    }
    
}
