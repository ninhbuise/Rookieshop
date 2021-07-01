package com.enashtech.rookieserver.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable{
    private final String jwttoken;

	public JwtResponse(String jwttoken) {
		this.jwttoken = jwttoken;
	}
}
