package com.akash.Aks.Factory;

import com.akash.Aks.Repository.IUserRepo;
import com.akash.Aks.Repository.UserRepoImpe;

public class RepoFactory {

	private static IUserRepo repo=null;
	
	public static IUserRepo getRepoFactory() {
		if(repo==null) {
		return new UserRepoImpe();
		}
		return repo;
	}
}
