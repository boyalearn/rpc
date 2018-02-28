package com.rpc.io.handler;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.rpc.io.context.Context;
import com.rpc.serialize.SerizalizeTool;

public interface Handler {
	public Handler setHandler(Handler handler);
	public void doHandle(Context context) throws IOException;
	public void handle(Context context) throws IOException;
	public byte[] encode(ByteBuffer byteBuffer);
	public byte[] encode(byte[] bytes);
	public BaseHandler setSerizalizeTool(SerizalizeTool serizalizeTool);
}
