package com.rpc.test;

import com.rpc.io.handler.BaseServerHandler;
import com.rpc.io.handler.TcpPacketHandler;
import com.rpc.io.server.NioServer;
import com.rpc.serialize.JavaSerizalizeTool;

public class TestServer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NioServer server=new NioServer();
		server.setIOHandler(new TcpPacketHandler()).setHandler(new BaseServerHandler().setSerizalizeTool(new JavaSerizalizeTool()));
		server.start();

	}

}
