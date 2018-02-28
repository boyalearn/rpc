package com.rpc.io.context;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.rpc.io.handler.Handler;

public interface Context {
	public void setChannel(SocketChannel channel);
	public SocketChannel getChannel();
	public void setHandler(Handler handler);
	public void write(ByteBuffer byteBuffer) throws IOException;
	public void flush();
	public byte[] getContext();
	public void setContext(byte[] data);
	void write(byte[] bytes) throws IOException;
}
