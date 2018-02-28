package com.rpc.io.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.rpc.io.context.Context;
import com.rpc.serialize.SerizalizeTool;

public class TcpPacketHandler extends BaseHandler{
	
	
	@Override
	public void handle(Context context) throws IOException {
		SocketChannel channel=context.getChannel();
		int count;
		ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
		while ((count=channel.read(byteBuffer))>0) {
		    // 保存bytebuffer状态
		    int position = byteBuffer.position();
		    int limit = byteBuffer.limit();
		    byteBuffer.flip();
		    // 判断数据长度是否够首部长度
		    if (byteBuffer.remaining() < 4) {
		        byteBuffer.position(position);
		        byteBuffer.limit(limit);
		        continue;
		    }
		    // 判断bytebuffer中剩余数据是否足够一个包
		    int length = byteBuffer.getInt();
		    if (byteBuffer.remaining() < length) {
		        byteBuffer.position(position);
		        byteBuffer.limit(limit);
		        continue;
		    }
		    // 拿到实际数据包
		    byte[] data = new byte[length];

		    byteBuffer.get(data, 0, length);
		    byteBuffer.compact();
		    context.setContext(data);
		    
		}
		if(count<0){
			channel.close();
		}
		
	}

	@Override
	public BaseHandler setSerizalizeTool(SerizalizeTool serizalizeTool) {
		// TODO Auto-generated method stub
		return this;
		
	}

	

}
