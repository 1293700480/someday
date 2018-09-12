package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;




public class Userdao extends BaseDao{
public boolean search(User user) {
boolean flag=false;
Connection conn =null;
PreparedStatement pstat=null;
ResultSet rs=null;
	try {
		
	
	conn = getConnection();
	String sql="select * from user where username=? and password=?";
	 pstat=conn.prepareStatement(sql);
	pstat.setString(1, user.getUsername());
	pstat.setString(2, user.getPassword());
	
	 rs= pstat.executeQuery();
	
		if(rs.next()){
		flag=true;

		
	}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		
	
			closeAll(conn,pstat,rs);
	}
	return flag;
}
	
	



public int add(User user) {
boolean flag=false;
Connection conn =null;
PreparedStatement pstat=null;
int rs=0;
	try {
		
	
	conn = getConnection();
	String sql="insert into user(username,password) values(?,?)";
	 pstat=conn.prepareStatement(sql);
	pstat.setString(1, user.getUsername());
	pstat.setString(2, user.getPassword());
	
	 rs= pstat.executeUpdate();
	
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally{
		
	
			closeAll(conn,pstat,null);
	}
	return rs;
}
}
	