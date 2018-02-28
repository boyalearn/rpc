package com.rpc.io.tcp.packages;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class TcpPackUtil implements TcpPackage{
	private int length;
    private byte[] sendData;
   

    public TcpPackUtil(){
    	
    }
    public TcpPackUtil(String sendData) {
        this.sendData = sendData.getBytes();
        this.length = this.sendData.length;
    }

    public TcpPackUtil(byte[] sendData) {
        this.sendData = sendData;
        this.length = this.sendData.length;
    }

    public byte[] packingTcp() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(this.length + 4);
        byteBuffer.putInt(this.length);
        byteBuffer.put(sendData);
        return byteBuffer.array();
    }
	public String unPackingTcp(SocketChannel channel) throws IOException{
		int count;
		String result="";
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
		    result=new String(data);
		    return result;
		}
		if(count<0){
			channel.close();
		}

		return result;
	}
	/*@Override
	public byte[] unPackingTcp(SocketChannel socketChannel) {
		// TODO Auto-generated method stub
		byte[] data;
		int count;
		ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
		try {
			while ((count=socketChannel.read(byteBuffer))>0) {
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
			    length = byteBuffer.getInt();
			    if (byteBuffer.remaining() < length) {
			        byteBuffer.position(position);
			        byteBuffer.limit(limit);
			        continue;
			    }
			    data = new byte[length];
			    byteBuffer.get(data, 0, length);
			    byteBuffer.compact();
			}
			if(count<0){
				socketChannel.close();
			}
			*//******************** two ************************//*
		} catch (IOException e) {
			System.out.println("BufferException");
			System.exit(-1);
			byteBuffer.clear();
			return null;
		}
		System.out.println("length1:"+length);
		return null;
	}*/
	public String byteToString(byte[] data){
	    return new String(data);
	}
}
