package com.rpc.io.handler;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.rpc.io.context.Context;
import com.rpc.serialize.SerizalizeTool;

public abstract class BaseHandler implements Handler{
	
	private Handler handler;

	@Override
	public Handler setHandler(Handler handler) {
		this.handler=handler;
		return handler;
	}

	@Override
	public void handle(Context context) throws IOException {	
	}
	

	@Override
	public void doHandle(Context context) throws IOException {
		handle(context);
		if(null!=handler){
			handler.doHandle(context);
		}
	}
	
	@Override
	public byte[] encode(ByteBuffer byteBuffer){
		int length=byteBuffer.limit()-byteBuffer.position()+1;
		ByteBuffer buffer = ByteBuffer.allocate(length + 4);
        buffer.putInt(length);
        buffer.put(byteBuffer);
        return buffer.array();
	}
	
	@Override
	public byte[] encode(byte[] bytes) {
		int length=bytes.length;
		ByteBuffer buffer = ByteBuffer.allocate(length + 4);
        buffer.putInt(length);
        buffer.put(bytes);
        return buffer.array();
	}

	@Override
	public BaseHandler setSerizalizeTool(SerizalizeTool serizalizeTool) {
		return this;
		
	}
	
	

}
