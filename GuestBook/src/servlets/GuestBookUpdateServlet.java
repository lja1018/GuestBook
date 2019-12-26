package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.GuestBookDao;
import vo.GuestBook;

@WebServlet("/update")
public class GuestBookUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ServletContext sc = this.getServletContext();
			
			GuestBookDao guestbookDao = (GuestBookDao)sc.getAttribute("guestbookDao");
			
			GuestBook gb = guestbookDao.selectOne(Integer.parseInt(request.getParameter("no")));
			
			request.setAttribute("guestbook", gb);
			
			RequestDispatcher rd = request.getRequestDispatcher("/guestbook/GuestBookUpdateForm.jsp");
			rd.forward(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ServletContext sc = this.getServletContext();
			
			GuestBookDao guestbookDao = (GuestBookDao)sc.getAttribute("guestbookDao");
			
			guestbookDao.update(new GuestBook()
					.setNo(Integer.parseInt(request.getParameter("no")))
					.setContents(request.getParameter("contents")) );			
			
			response.sendRedirect("list");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
			
		}
		
	}
}
