package listeners;

import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import dao.GuestBookDao;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		try {
			ServletContext sc = event.getServletContext();
			
			InitialContext initialContext = new InitialContext();
			DataSource ds = (DataSource)initialContext.lookup("java:comp/env/jdbc/studydb");
			
			GuestBookDao guestbookDao = new GuestBookDao();
			guestbookDao.setDataSource(ds);
			
			sc.setAttribute("guestbookDao", guestbookDao);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {}

}
