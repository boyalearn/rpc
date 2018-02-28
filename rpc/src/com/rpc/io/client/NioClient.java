package com.rpc.io.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.rpc.io.config.Config;
import com.rpc.io.context.BaseChannelContext;
import com.rpc.io.context.Context;
import com.rpc.io.handler.Handler;
import com.rpc.io.handler.TcpPacketHandler;

public class NioClient {
	
	//socket通道
	private SocketChannel socket;
	
	//selector通道选择器
	private Selector selector;
	
	//处理IO流的Handler
	private Handler handler;
	
	//将处理发送和读取流操作的封装到Contex中
	private Context context=new BaseChannelContext();
	
	
	public Context getContext() {
		return context;
	}


	public void setContext(Context context) {
		this.context = context;
	}


	public Handler setIOHandler(Handler currentHandler){
		this.handler=currentHandler;
		return currentHandler;
	}
	
	
	public void start(){
		if(null==this.handler){
			throw new NullPointerException("handler未进行设置");
		}
		this.context.setHandler(this.handler);
		connect();
		
	}
	public void listen() throws IOException{
		boolean accept=true;
		int n=0;
		while(accept){
			n=selector.select();
			if(n==0)continue;

			Iterator<SelectionKey> it=selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key=it.next();
				if(key.isReadable()){
		    		SocketChannel socket=(SocketChannel)key.channel();
		    		context.setChannel(socket);
		    		handler.doHandle(context);
		    		accept=false;
		    	}
				it.remove();
			}
		}
	}
	
	public void connect(){
		try{
			System.out.println("connect");
			socket=SocketChannel.open();
			socket.configureBlocking(false);
			selector=Selector.open();
		    socket.register(selector, SelectionKey.OP_CONNECT);
			socket.connect(new InetSocketAddress(Config.HOST,Config.PORT));
			int n=0;
			boolean accept=true;
			while(accept){
				System.out.println("connection"+ " while");
				n=selector.select();
				if(n==0)continue;
				System.out.println("n"+ n);
				Iterator<SelectionKey> it=selector.selectedKeys().iterator();
				while(it.hasNext()){
					SelectionKey key=it.next();
					if(key.isValid()){
						SocketChannel socket=(SocketChannel)key.channel();
						if(key.isConnectable()){
							if(socket.finishConnect()){
								socket.register(selector,SelectionKey.OP_READ);
								//建立好连接就应该设置context的SocketChannel防止发送数据时contex的Socket为空
								this.context.setChannel(socket);
								accept=false;
							}
							else{
								System.out.println("error");
							}
						}
					}
					it.remove();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("connect faild, reconnect after 3s");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {

			}
			connect();
		}
	}
	
	public synchronized String request(String msg) throws IOException{
		this.context.write(msg.getBytes());
		listen();
		return new String(this.context.getContext());
	}
	
	public synchronized byte[] request(byte[] msg) throws IOException{
		this.context.write(msg);
		listen();
		return this.context.getContext();
	}
	
	
	
	public void monitor(){
	}
	private static NioClient client;
	
	public static NioClient getClient(){
		return client;
	}
	static{
		client=new NioClient();
		client.setIOHandler(new TcpPacketHandler());
		client.setContext(new BaseChannelContext());
		client.start();
	}

}
