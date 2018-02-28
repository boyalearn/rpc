package com.rpc.test;


import com.rpc.io.client.NioClient;

public class ClientAcceptor implements Runnable{
	private NioClient client;
	
	public ClientAcceptor(NioClient client){
		this.client=client;
	}

	@Override
	public void run() {
		this.client.monitor();	
	}
	
	
}
