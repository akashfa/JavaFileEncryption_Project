package com.akash.Aks.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.akash.Aks.DataObjects.FileDataDao;
import com.akash.Aks.DataObjects.UserDao;
import com.akash.Aks.JDBCUtil.JdbcConnection;

public class UserRepoImpe implements IUserRepo{

	private static final String SQL_INSERT_QUERY = "insert into student(name,email) values(?,?)";
	private static final String SQL_SELECT_QUERY = "select email from student where email=?";
	private static final String SQL_SELECT2_QUERY = "select * from filedata";
	private static final String SQL_INSERT1_QUERY = "insert into filedata(fileName,filePath,email,binry_data) values(?,?,?,?)";
	private static final String SQL_DELETE_QUERY = "Delete from filedata Where id = ?";
	private static final String SQL_SELECT3_QUERY = "select * from filedata where id=?";

	@Override
	public int saveUser(UserDao user) {
		try {
			Connection conn=JdbcConnection.getDBConnection();
			  PreparedStatement pstm=conn.prepareStatement(SQL_INSERT_QUERY);
			  pstm.setString(1, user.getName());
			  pstm.setString(2, user.getEmail());
			  return pstm.executeUpdate();
		} catch (SQLException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public boolean isExists(String email) {
		boolean flage=false;
		try {
			Connection conn =JdbcConnection.getDBConnection();
			PreparedStatement  pstm= conn.prepareStatement(SQL_SELECT_QUERY);
			  pstm.setString(1, email);
			  ResultSet result=pstm.executeQuery();
			  if(result.next()) {
			  if(result.getString(1).equals(email)) {
				  flage=true;
				
			  }
			  }
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flage;
	}

	@Override
	public List<FileDataDao> getAllFiells() {
		 List<FileDataDao> file=new ArrayList<FileDataDao>();
		try {
			Connection conn =JdbcConnection.getDBConnection();
			PreparedStatement  pstm= conn.prepareStatement(SQL_SELECT2_QUERY);
			  
			  ResultSet result=pstm.executeQuery();
			  while(result.next()) {
				   int id=result.getInt(1);
				   String fileName=result.getString(2);
				   FileDataDao data=new FileDataDao();
				   data.setId(id);
				   data.setFileName(fileName);
				   file.add(data);
				  
			  }
			  
			  
			  
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return file;
		
	
	}

	@Override
	public int deleteFile(int id) {
		
		try {
			Connection conn =JdbcConnection.getDBConnection();
			PreparedStatement  pstm= conn.prepareStatement(SQL_SELECT3_QUERY);
			 pstm.setInt(1, id);
		        ResultSet rs = pstm.executeQuery();
		        rs.next();
		        String path = rs.getString("filePath");
		        Clob c = rs.getClob("binry_data");

		        Reader r = c.getCharacterStream();
		        FileWriter fw = new FileWriter(path);
		        int i;
		        while ((i = r.read()) != -1) {
		            fw.write((char) i);
		        }
		        fw.close();
			
			
			PreparedStatement  pstm1= conn.prepareStatement(SQL_DELETE_QUERY);
			  
			pstm1.setInt(1, id);
			return pstm1.executeUpdate();
			 
			  
			  
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int saveFile(FileDataDao data) {
	    File f = new File(data.getPath());
	    FileInputStream fileInputStream = null;
	    
	    try {
	        Connection conn = JdbcConnection.getDBConnection();
	        PreparedStatement pstm = conn.prepareStatement(SQL_INSERT1_QUERY);
	        pstm.setString(1, data.getFileName());
	        pstm.setString(2, data.getPath());
	        pstm.setString(3, data.getEmail());

	       
	        fileInputStream = new FileInputStream(f);
	        pstm.setBinaryStream(4, fileInputStream, (int) f.length());

	       
	        int result = pstm.executeUpdate();

	      
	        fileInputStream.close();

	        if (result > 0) {
	           
	            boolean isDeleted = f.delete();
	            if (!isDeleted) {
	                System.out.println("Failed to delete file: " + f.getAbsolutePath());
	                
	                if (f.exists()) {
	                    System.out.println("File still exists and could be locked by another process.");
	                }
	            } else {
	                System.out.println("File successfully deleted: " + f.getAbsolutePath());
	            }
	        }
	        return result;

	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    } finally {
	        // Close the input stream if it's still open
	        try {
	            if (fileInputStream != null) {
	                fileInputStream.close();
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return 0;
	}


}
