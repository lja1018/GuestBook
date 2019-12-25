package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import vo.GuestBook;

public class GuestBookDao {
	Connection connection;
	
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public List<GuestBook> selectList() throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
					"select email, contents, create_date" + 
					" from guestbooks" + 
					" order by create_date desc");
			
			ArrayList<GuestBook> guestbooks = new ArrayList<GuestBook>();
			
			while (rs.next()) {
				guestbooks.add(new GuestBook()
						.setEmail(rs.getString("email"))
						.setContents(rs.getString("contents"))
						.setCreatedDate(rs.getDate("create_date")) );
			}
			
			return guestbooks;
			
		} catch (Exception e) {
			throw e;
			
		} finally {
			try {if (rs != null) rs.close();} catch (Exception e) {}
			try {if (stmt != null) stmt.close();} catch (Exception e) {}
		}
	}
	
	public int insert(GuestBook guestbook) throws Exception {
		PreparedStatement stmt = null;
		
		try {
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
		}
	}
	
	public int delete(int no) throws Exception {
		Statement stmt = null;
		
		try {
			stmt = connection.createStatement();
			return stmt.executeUpdate("delete from guestbooks where gno=" + no);
		
		} catch (Exception e) {
			throw e;
			
		}
	}
	
	public int update(GuestBook guestbook) throws Exception {
		PreparedStatement stmt = null;
		
	    try {
	      stmt = connection.prepareStatement(
	          "update guestbooks set contents=?, modify_date=now()"
	              + " where gno=?");
	      stmt.setString(1, guestbook.getContents());
	      stmt.setString(2, Integer.toString(guestbook.getNo()));
	      
	      return stmt.executeUpdate();

	    } catch (Exception e) {
	      throw e;

	    } finally {
	      try {if (stmt != null) stmt.close();} catch (Exception e) {}
	    }
	}
	
	public GuestBook selectOne(int no) throws Exception {
		Statement stmt = null;
	    ResultSet rs = null;
	    
	    try {
	      stmt = connection.createStatement();
	      rs = stmt.executeQuery(
	          "select email, contents, modify_date, gno from guestbooks" + 
	              " where gno=" + no);
	      
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
	    }
	}
}