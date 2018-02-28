package com.rpc.serialize;


public interface SerizalizeTool {
	public byte[] serialize(Object obj);
    public Object deserialize(byte[] bytes);
}
