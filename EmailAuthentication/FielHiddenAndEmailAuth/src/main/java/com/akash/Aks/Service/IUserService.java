package com.akash.Aks.Service;

import java.util.List;

import com.akash.Aks.DataObjects.*;

public interface IUserService {
	
	
	public String generateOtp();
	
	public void sendOto(String email,String otp);
	
	public int saveUser(UserDao user);
	
	
	public boolean isExists(String email);

	public List<FileDataDao> getAllFiells();

	

	public int deleteFile(int id);

	public int saveFile(FileDataDao data);

}
