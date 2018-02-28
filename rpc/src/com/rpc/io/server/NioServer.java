package com.rpc.io.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.rpc.io.context.BaseChannelContext;
import com.rpc.io.context.Context;
import com.rpc.io.handler.Handler;


public class NioServer implements Server{
	
	private Selector selector;
	
	private ServerSocketChannel socketChannel;
	
	private ServerSocket serverSocket;
	
	private Boolean isBlocking;
	
	//处理IO流的Handler
	private Handler handler;
		
	//将处理发送和读取流操作的封装到Contex中
	private Context context=new BaseChannelContext();
	

	public Handler setIOHandler(Handler currentHandler) {
		this.handler = currentHandler;
		return currentHandler;
	}

	public void setContext(Context context) {
		this.context = context;
	}

	public NioServer init() throws IOException{
		return init(false);
	}
	
	public NioServer init(Boolean isBlocking) throws IOException{
		this.context.setHandler(this.handler);
		this.isBlocking=isBlocking;
		//创建selector
		selector=Selector.open();
		//绑定端口
		socketChannel=ServerSocketChannel.open();
		socketChannel.configureBlocking(this.isBlocking);
		serverSocket=socketChannel.socket();
		//将通道注册到选择器上
		socketChannel.register(selector, SelectionKey.OP_ACCEPT);
		return this;
	}
	
	
	
	public void bind() throws IOException{
		serverSocket.bind(new InetSocketAddress(9090));
		System.out.println("Server is start !!! Listening port 9090");
	}
	
	public void listen() throws IOException{
		
		int n=0;
		while(true){
			n=selector.select();
			if(n==0)continue;

			Iterator<SelectionKey> it=selector.selectedKeys().iterator();
			while(it.hasNext()){
				SelectionKey key=it.next();
				if(key.isAcceptable()){	
					System.out.println("new connection");
					socketChannel=(ServerSocketChannel)key.channel(); 
		            SocketChannel channel=socketChannel.accept(); 
		            channel.configureBlocking(false); 
		            channel.register(selector, SelectionKey.OP_READ); 
		            this.context.setChannel(channel);
		    	}
				if(key.isReadable()){	
					SocketChannel channel=(SocketChannel)key.channel(); 
					try{
					context.setChannel(channel);
		    		handler.doHandle(context);
					}catch(Exception e){
						context.setContext(null);
						channel.close();
					}
		    	}
				it.remove();
			}
		}
	}
	public void start(){
		try {
			init();
			bind();
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
