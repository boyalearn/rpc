package com.rpc.test;

import java.io.IOException;

import com.rpc.io.context.Context;
import com.rpc.io.handler.BaseHandler;

public class ShowHandler extends BaseHandler{

	@Override
	public void handle(Context context) throws IOException {
		System.out.println("SHOW"+new String(context.getContext()));
	}
	

}
