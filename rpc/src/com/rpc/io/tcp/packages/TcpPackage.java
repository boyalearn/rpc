package com.rpc.io.tcp.packages;

import java.io.IOException;
import java.nio.channels.SocketChannel;

public interface TcpPackage {
	public byte[] packingTcp();
	public String unPackingTcp(SocketChannel socketChannel) throws IOException;
	public String byteToString(byte[] data); 
}
