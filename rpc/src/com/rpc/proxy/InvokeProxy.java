package com.rpc.proxy;


import java.lang.reflect.Method;

public class InvokeProxy{
	
	private Invoke invoke;
	
	public InvokeProxy(Invoke invoke){
		this.invoke=invoke;
	}

	public Object call() throws Throwable{
		Class<?> obj = null;  
        obj = Class.forName(invoke.getClassName());  
		Method[] methods = obj.getDeclaredMethods();  
		Object instance = obj.newInstance();
        for (int i = 0; i < methods.length; i++) {  
        	if(invoke.getMethodName().equals(methods[i].getName())){
        		return methods[i].invoke(instance, invoke.getArgs());
        	}
        }
        return null;
	}

}
