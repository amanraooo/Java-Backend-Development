package org.amn;

public class PetrolEngine implements Engine {

	public PetrolEngine() {
		System.out.println("petrol constructor....");
	}

	public int start() {
		return 1;
	}
}