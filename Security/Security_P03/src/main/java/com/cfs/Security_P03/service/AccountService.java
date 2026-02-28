package com.cfs.Security_P03.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

	@PreAuthorize("hasRole('USER')")
	public String getBalance(){
		return "Your balance is = 8000";
	}

	@PreAuthorize("hasRole('ADMIN')")
	public String closeAccount(){
		return "Account closed";
	}
}
