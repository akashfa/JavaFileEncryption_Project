package com.akash.Aks.DataObjects;

public class FileDataDao {
	private Integer id;
	
	private String fileName;
	private String path;
	private String email;
	
	public FileDataDao() {


		
	}
	
	
	public FileDataDao(Integer id, String fileName, String path, String email) {
	
		this.id = id;
		this.fileName = fileName;
		this.path = path;
		this.email = email;
	}
   
	
	public FileDataDao( String fileName, String path, String email) {
		
	
		this.fileName = fileName;
		this.path = path;
		this.email = email;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
}
