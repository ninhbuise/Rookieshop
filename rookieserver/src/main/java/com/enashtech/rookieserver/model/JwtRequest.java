package com.enashtech.rookieserver.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtRequest implements Serializable{
    private String username;
	private String password;
}
