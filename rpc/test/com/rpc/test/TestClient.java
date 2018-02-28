package com.rpc.test;

import java.io.IOException;

import com.rpc.io.client.NioClient;
import com.rpc.io.context.BaseChannelContext;
import com.rpc.io.handler.TcpPacketHandler;
import com.rpc.proxy.ProxyUtil;
import com.rpc.serialize.JavaSerizalizeTool;

@SuppressWarnings("unused")
public class TestClient {
	public static void main(String[] args) throws IOException{
	    /*NioClient client=new NioClient();
		client.setIOHandler(new TcpPacketHandler()).setHandler(new ShowHandler());
		client.setContext(new BaseChannelContext());
		client.start();
		System.out.println(client.request("dfsdfsd"));
		new Thread(new ClientSender(client),"client-sender").start();*/
		User user=new User();
		user.setAge(20);
		user.setUserName("dfdsfsdf");
		String dfsdfd="¹þ¹þ";
		JavaSerizalizeTool tool=new JavaSerizalizeTool();
		//System.out.println(tool.deserialize(tool.serialize(user)));
		Catulate catulate=(Catulate)new ProxyUtil().build(CatulateData.class);
		int n=0;
		for(int i=0;;i++){
			n++;
		    String hah=catulate.catulateDate("dfsfs"+n);
		    System.out.println("result:"+hah);
		}
	}

}
