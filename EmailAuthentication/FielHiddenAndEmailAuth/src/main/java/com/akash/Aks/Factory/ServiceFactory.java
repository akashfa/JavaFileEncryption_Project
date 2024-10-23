package com.akash.Aks.Factory;

import com.akash.Aks.Service.IUserService;
import com.akash.Aks.Service.UserServiceImpe;

public class ServiceFactory {
	
	private static IUserService service=null;
	
	public static IUserService getServiceObject() {
		
		if(service==null) {
			return new UserServiceImpe();
		}
		return service;
	}

}
