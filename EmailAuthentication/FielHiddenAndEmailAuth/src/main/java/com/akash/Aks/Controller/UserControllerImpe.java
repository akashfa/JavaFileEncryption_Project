package com.akash.Aks.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.akash.Aks.DataObjects.UserDao;
import com.akash.Aks.DataObjects.FileDataDao;
import com.akash.Aks.Factory.ServiceFactory;
import com.akash.Aks.Service.IUserService;

public class UserControllerImpe implements IUserController {
 
	
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
	

	@Override
	public void loginUser() throws IOException {
		IUserService service=ServiceFactory.getServiceObject();
		System.out.println("Enter the Email::");
		String email=br.readLine();
		
		if(service.isExists(email)) {
			
			String getotp= service.generateOtp();
			service.sendOto(email, getotp);
			
		    System.out.println("Enter the OTP::");
		    String otp=br.readLine(); 
		    
		    if(otp.equals(getotp)) {
		    	userView(email);
		    }else {
		    	System.out.println("You Enter the Wrong Password");
		    }
		}else {
			System.out.println("User not found::");
		}
		
		
	}

	@Override
	public void signUpUser() throws IOException {
		IUserService service=ServiceFactory.getServiceObject();
		System.out.println("Enter the user name::");
		String name=br.readLine();
		
		System.out.println("Enter the user email::");
		String email=br.readLine();
		boolean flage=service.isExists(email);
		if(flage) {
	    	System.out.println("User already exigst");
    	}else {
		String getotp= service.generateOtp();
		System.out.println(getotp);
		service.sendOto(email, getotp);
	    System.out.println("Enter the OTP::");
	    String otp=br.readLine(); 
	    System.out.println(otp);
	    
	    
	    
	   
	   if(otp.equals(getotp)) {
	    	UserDao user=new UserDao(name, email);
	    	
	    	int response=service.saveUser(user);
	    	
	    	switch(response) {
	    	case 1:System.out.println("User Successfully Signup::");
	    	break;
	    	
	    	}
	    }else {
	    	System.out.println("You enterd worng password::");
	    	
	    	
	    }
    	}
		
		
		
	}

	@Override
	public void userView(String email) {
	 
		IUserService service=ServiceFactory.getServiceObject();
		while(true) {
			 System.out.println("Wlcome " +  email);
	            System.out.println("Press 1 to show hidden files");
	            System.out.println("Press 2 to hide a new file");
	            System.out.println("Press 3 to unhide a file");
	            System.out.println("Press 0 to exit");
	            


			
			
				int choice=0;
				try {	
					choice = Integer.parseInt(br.readLine());
				
			
					switch(choice) {
					
					case 1: 
					       List<FileDataDao>files= service.getAllFiells();
					       System.out.println("ID----FileName::");
					       for (FileDataDao file : files) {
							System.out.println(file.getId()+" "+   file.getFileName());
					       }
					break;
					case 2: 
						System.out.println("Enter the path");
						String path=br.readLine();
						File file=new File(path);
						
						FileDataDao data=new FileDataDao(file.getName(), path, email);
						int result= service.saveFile(data);
						if(result==1) {
							System.out.println("File Successfuly save::");
						}else {
							System.out.println("Something is worang::");
						}
						
					break;
					case 3:
						 List<FileDataDao>fill= service.getAllFiells();
					       
					       System.out.println("Enter the id for Delete file::");
					       int id=Integer.parseInt(br.readLine());
					       
					       int r=0;
					       for(FileDataDao f:fill) {
					    	   if(f.getId()==id) {
					    		r=  service.deleteFile(id);  
					    		
					    		   break;
					    	   }
					       }
					       if(r==1) {
					    	 System.out.println("File deleted Successfully::");
					       }else {
					    	   System.out.println("You Enterd wrong id::");
					       }
					case 0: 
						System.exit(choice);
								
					
					}
				} catch (IOException |NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}


	}

}























