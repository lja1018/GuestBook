package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import vo.GuestBook;

public class GuestBookDao {
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}

	public List<GuestBook> selectList() throws Exception {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select *" + 
					" from guestbooks" + 
					" order by gno desc");
			
			ArrayList<GuestBook> guestbooks = new ArrayList<GuestBook>();
			
			while (rs.next()) {
				guestbooks.add(new GuestBook()
						.setNo(rs.getInt("gno"))
						.setEmail(rs.getString("email"))
						.setContents(rs.getString("contents"))
						.setPassword(rs.getString("pwd"))
						.setCreatedDate(rs.getDate("create_date"))
						.setModifiedDate(rs.getDate("modify_date")) );
			}
			
			return guestbooks;
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try	{if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int insert(GuestBook guestbook) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.prepareStatement(
					"insert into guestbooks(email, pwd, contents, create_date, modify_date)" + 
					" values (?, ?, ?, now(), now())" );
					
			stmt.setString(1, guestbook.getEmail());
			stmt.setString(2, guestbook.getPassword());
			stmt.setString(3, guestbook.getContents());
			
			return stmt.executeUpdate();
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try	{if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int delete(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = ds.getConnection();
			stmt = connection.createStatement();
			return stmt.executeUpdate("delete from guestbooks where gno=" + no);
		
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try	{if (connection != null) connection.close();} catch (Exception e) {}
		}
	}
	
	public int update(GuestBook guestbook) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		
	    try {
			connection = ds.getConnection();
	    	stmt = connection.prepareStatement("update guestbooks set contents=?, modify_date=now() where gno=?");
	    	stmt.setString(1, guestbook.getContents());
	    	stmt.setString(2, Integer.toString(guestbook.getNo()));
	      
	      return stmt.executeUpdate();

	    } catch (Exception e) {
	    	throw e;

	    } finally {
	    	try {if (stmt != null) stmt.close();} catch (Exception e) {}
			try	{if (connection != null) connection.close();} catch (Exception e) {}
	    }
	}
	
	public GuestBook selectOne(int no) throws Exception {
		Connection connection = null;
		Statement stmt = null;
	    ResultSet rs = null;
	    
	    try {
			connection = ds.getConnection();
	    	stmt = connection.createStatement();
	    	rs = stmt.executeQuery("select email, contents, modify_date, gno from guestbooks where gno=" + no);
	    	
	    	if (rs.next()) {
	    		return new GuestBook()
	    				.setNo(rs.getInt("gno"))
	    				.setEmail(rs.getString("email"))
	    				.setContents(rs.getString("contents"))
	    				.setCreatedDate(rs.getDate("modify_date"));
	    		
	    	} else {
	    		throw new Exception("해당 번호의 회원을 찾을 수 없습니다.");	
	    	}
	    	
	    } catch (Exception e) {
	    	throw e;
	    	
	    } finally {
	    	try {if (rs != null) rs.close();} catch(Exception e) {}
	    	try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try	{if (connection != null) connection.close();} catch (Exception e) {}
	    }
	}
}