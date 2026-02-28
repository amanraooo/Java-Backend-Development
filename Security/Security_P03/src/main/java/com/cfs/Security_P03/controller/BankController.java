package com.cfs.Security_P03.controller;

import com.cfs.Security_P03.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BankController {

	AccountService accountService;

	@GetMapping("/balance")
	public String getBalance(){
		return accountService.getBalance();
	}

	@PostMapping("/close")
	public String closeAccount(){
		return accountService.closeAccount();
	}

	@GetMapping("/about")
	public String about()
	{
		return "LUNCH TIME";
	}


}
