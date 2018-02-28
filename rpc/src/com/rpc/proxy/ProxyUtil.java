package com.rpc.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.rpc.io.client.NioClient;
import com.rpc.serialize.JavaSerizalizeTool;
import com.rpc.serialize.SerizalizeTool;

public class ProxyUtil implements InvocationHandler{
	
	private Class<?> type;
	
	public Object build(Class<?> type){
		this.type=type;
		return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), type.getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Invoke invoke=new Invoke(type.getName(),method.getName(),method.getParameterTypes(),args);
		SerizalizeTool serizalizeTool=new JavaSerizalizeTool();
		return serizalizeTool.deserialize(NioClient.getClient().request(serizalizeTool.serialize(invoke)));
		
	}

}
