package com.a.omc;

import com.a.omc.client.NFVClient;

public class ClientEntrance {
	public static void main(String[] args) {
		NFVClient nfvClient = new NFVClient();
		Thread thread = new Thread(nfvClient);
		thread.start();
	}
}
