/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qubole.sdk;


public class Account {
    
    private String auth_token;
    
    public Account(String auth_token) {
        this.auth_token = auth_token;
    }
    public String getAuthToken(){
        return this.auth_token;
    }
    public void setAuthToken(String auth_token) {
        this.auth_token = auth_token;
    }
    
}
