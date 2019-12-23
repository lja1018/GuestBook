package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.GuestBook;

//예외 발생 시 Error.jsp로 포워딩 
@WebServlet("/list")
public class GuestBookListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			ServletContext sc = this.getServletContext();
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password"));
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(
					"select email, pwd, contents, create_date, modify_date" + 
					" from guestbooks" +
					" order by modify_date desc");
			
			response.setContentType("text/html; charset=UTF-8");
			ArrayList<GuestBook> guestbooks = new ArrayList<GuestBook>();

			// 데이터베이스에서 방명록 목록을 가져와 GuestBook에 담는다.
			// 그리고 GuestBook객체를 ArrayList에 추가한다.
			while(rs.next()) {
				guestbooks.add(new GuestBook()
							.setEmail(rs.getString("email"))
							.setPassword(rs.getString("pwd"))
							.setContents(rs.getString("contents"))
							.setCreatedDate(rs.getDate("create_date"))
							.setModifiedDate(rs.getDate("modify_date")));
			}

			// request에 방명록 데이터를 보관한다.
			request.setAttribute("guestbooks", guestbooks);
			
			// JSP로 출력을 위임한다.
			RequestDispatcher rd = request.getRequestDispatcher("/list/GuestBookList.jsp");
			rd.include(request, response);
		} catch (Exception e) {
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		} finally {
			try {if (rs != null) rs.close();} catch(Exception e) {}
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
			try {if (conn != null) conn.close();} catch(Exception e) {}
		}
	}
}
