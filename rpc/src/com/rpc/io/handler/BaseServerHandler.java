package com.rpc.io.handler;

import java.io.IOException;

import com.rpc.io.context.Context;
import com.rpc.proxy.Invoke;
import com.rpc.proxy.InvokeProxy;
import com.rpc.serialize.SerizalizeTool;

public class BaseServerHandler extends BaseHandler{
	
	private SerizalizeTool serizalizeTool;

	@Override
	public void handle(Context context) throws IOException {
		Invoke invoke=(Invoke)serizalizeTool.deserialize(context.getContext());
		System.out.println(invoke);
		try {
			context.write(serizalizeTool.serialize(new InvokeProxy(invoke).call()));
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public BaseHandler setSerizalizeTool(SerizalizeTool serizalizeTool) {
		this.serizalizeTool=serizalizeTool;
		return this;
	}
	

}
