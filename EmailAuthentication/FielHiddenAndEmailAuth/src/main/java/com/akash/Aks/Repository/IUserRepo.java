package com.akash.Aks.Repository;

import java.util.List;

import com.akash.Aks.DataObjects.FileDataDao;
import com.akash.Aks.DataObjects.UserDao;

public interface IUserRepo {

	int saveUser(UserDao user);

	boolean isExists(String email);

	List<FileDataDao> getAllFiells();

	int deleteFile(int id);

	int saveFile(FileDataDao data);

}
