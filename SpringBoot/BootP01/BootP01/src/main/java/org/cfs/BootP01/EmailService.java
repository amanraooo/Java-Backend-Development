package org.cfs.BootP01;

public class EmailService implements MessageService{

	@Override
	public String getMessage() {
		return "Email : you hva recieved a new message.";
	}
}
