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
		    // ����bytebuffer״̬
		    int position = byteBuffer.position();
		    int limit = byteBuffer.limit();
		    byteBuffer.flip();
		    // �ж����ݳ����Ƿ��ײ�����
		    if (byteBuffer.remaining() < 4) {
		        byteBuffer.position(position);
		        byteBuffer.limit(limit);
		        continue;
		    }
		    // �ж�bytebuffer��ʣ�������Ƿ��㹻һ����
		    int length = byteBuffer.getInt();
		    if (byteBuffer.remaining() < length) {
		        byteBuffer.position(position);
		        byteBuffer.limit(limit);
		        continue;
		    }
		    // �õ�ʵ�����ݰ�
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
