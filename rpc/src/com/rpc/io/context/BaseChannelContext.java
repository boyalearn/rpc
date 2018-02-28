package com.rpc.io.context;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import com.rpc.io.handler.Handler;

public class BaseChannelContext implements Context{
	
	private byte[] context;
	
	//socket通道
	private SocketChannel socket;
	
	
	//处理IO流的Handler
	private Handler handler;

	@Override
	public void setChannel(SocketChannel channel) {
		socket=channel;
	}

	@Override
	public void setHandler(Handler handler) {
		this.handler=handler;
	}

	@Override
	public void write(ByteBuffer byteBuffer) throws IOException {
		socket.write(ByteBuffer.wrap(handler.encode(byteBuffer)));
		
	}
	@Override
	public void write(byte[] bytes) throws IOException {
		socket.write(ByteBuffer.wrap(handler.encode(bytes)));
		
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public byte[] getContext() {
		// TODO Auto-generated method stub
		return context;
	}

	@Override
	public SocketChannel getChannel() {
		// TODO Auto-generated method stub
		return socket;
	}

	@Override
	public void setContext(byte[] data) {
		// TODO Auto-generated method stub
		this.context=data;
	}

}
