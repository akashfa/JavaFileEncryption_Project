package com.akash.Aks.Controller;

import java.io.IOException;

public interface IUserController {
	
	
	
	public void loginUser() throws IOException;
	
	public void signUpUser() throws IOException;
	
	
	public void userView(String email);
	

}
