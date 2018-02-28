package com.rpc.proxy;

import java.io.Serializable;
import java.util.Arrays;

public class Invoke implements Serializable{

	private static final long serialVersionUID = -1659850286620915949L;
	
	private String className;
	
	private String methodName;
	
	private Class<?>[] parameterTypes;
	
	private Object[] args;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	
	public Invoke(String className, String methodName, Class<?>[] parameterTypes, Object[] args) {
		super();
		this.className = className;
		this.methodName = methodName;
		this.parameterTypes = parameterTypes;
		this.args = args;
	}

	@Override
	public String toString() {
		return "Invoke [className=" + className + ", methodName=" + methodName + ", parameterTypes="
				+ Arrays.toString(parameterTypes) + ", args=" + Arrays.toString(args) + "]";
	}
	
	
	

}
