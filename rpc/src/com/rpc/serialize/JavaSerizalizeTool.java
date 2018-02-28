package com.rpc.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class JavaSerizalizeTool implements SerizalizeTool{
	public byte[] serialize(Object obj){
		ObjectOutputStream stream = null;
		ByteArrayOutputStream bytes= null;
		try {  
			bytes = new ByteArrayOutputStream();  
		    stream = new ObjectOutputStream(bytes);
		    stream.writeObject(obj);
		    return bytes.toByteArray();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
    public Object deserialize(byte[] bytes){
    	ByteArrayInputStream stream = null;
    	try {
    		stream= new ByteArrayInputStream(bytes);
    		ObjectInputStream ois = new ObjectInputStream(stream);
		    return ois.readObject();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
    }  

}
