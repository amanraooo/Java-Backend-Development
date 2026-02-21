package com.cfs.Security_P02.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class BankController {

	@GetMapping("/contact")
		public String ContactUs(){
		return "Contact us at 9898786789";
	}

	@GetMapping("/transfer")
	public String Transfer(){
		return "Money transfer successfull";
	}

	@GetMapping("/admin")
	public String Admin(){
		return "Welcome admin saab";
	}

	@GetMapping("/about")
	public String About(){
		return "Aman founder of No Bank";
	}
}
