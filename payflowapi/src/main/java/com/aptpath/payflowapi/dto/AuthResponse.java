package com.aptpath.payflowapi.dto;

public class AuthResponse {
    private String token;
   
    public AuthResponse(String token) {
    	System.out.println("Login Success");
        this.token = token;
        
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

    
}

