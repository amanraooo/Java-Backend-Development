package com.cfs.Security_P03.service;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

	public String getBalance(){
		return "Your balance is = 8000";
	}

	public String closeAccount(){
		return "Account closed";
	}
}
