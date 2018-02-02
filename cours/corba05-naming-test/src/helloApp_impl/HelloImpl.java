package helloApp_impl;

import helloApp.HelloPOA;

public class HelloImpl extends HelloPOA{
	
	@Override
	public String sayHello() {
		return "\nHello world !!\n";
	}

}
