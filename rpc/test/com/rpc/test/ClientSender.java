package com.rpc.test;

import com.rpc.io.client.NioClient;

public class ClientSender implements Runnable{
	private NioClient client;
	
	public ClientSender(NioClient client){
		this.client=client;
	}

	@Override
	public void run() {
	    int n=0;
		while(true){
			try{
				n++;
			    System.out.println("sender:client say Hello ServerSSSSSS>>>>>>>>>>>"+n);
			    System.out.println("accetp:"+client.request("client say Hello ServerSSSSSS>>>>>>>>>>>"+n));
			}catch(Exception e){
				client.connect();
				e.printStackTrace();
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
}
