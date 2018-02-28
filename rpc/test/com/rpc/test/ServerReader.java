package com.rpc.test;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import com.rpc.io.tcp.packages.TcpPackUtil;

public class ServerReader implements Runnable{
    
	private Selector selector;
	public ServerReader(Selector selector){
		this.selector=selector;
	}
	@Override
	public void run() {
		try{
			int n;
			while(true){
				n=selector.select();
				if(n==0)continue;
	
				Iterator<SelectionKey> it=selector.selectedKeys().iterator();
				while(it.hasNext()){
					SelectionKey key=it.next();
					if(key.isReadable()){
			    		SocketChannel socket=(SocketChannel)key.channel();
			    		try{
			    		new TcpPackUtil().unPackingTcp(socket);
			    		}catch(Exception e){
			    			
			    		}finally{
			    			//socket.close();
			    		}
			    	}
					it.remove();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	

}
