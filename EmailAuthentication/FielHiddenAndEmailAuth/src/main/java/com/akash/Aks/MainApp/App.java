package com.akash.Aks.MainApp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.akash.Aks.Controller.IUserController;
import com.akash.Aks.Controller.UserControllerImpe;

/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws NumberFormatException, IOException
    {
    	IUserController controller=new UserControllerImpe();
    	
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        welcomeScreen(controller,br);
    	

    	
    }
       public static void  welcomeScreen(IUserController controller,BufferedReader br) {
		  while(true) {
			System.out.println("Enter the Right Correct choice::\n");
			System.out.println("Press To 1 Login");
			System.out.println("Press To 2 SignUp");
			System.out.println("Press To 0 exit");
	            
			
			
				int choice=0;
				try {
					choice = Integer.parseInt(br.readLine());
				} catch (NumberFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				switch(choice) {
				
				case 1: 
					try {
						controller.loginUser();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				case 2: 
					try {
						controller.signUpUser();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				break;
				case 0: 
					System.exit(choice);
							
				
				}
			
		}
       
       }
}


