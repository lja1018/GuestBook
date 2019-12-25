package listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import dao.GuestBookDao;
import util.DBConnectionPool;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
	DBConnectionPool connPool;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		try {
			ServletContext sc = event.getServletContext();
			
			connPool = new DBConnectionPool(
					sc.getInitParameter("driver"),
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password") );
			
			GuestBookDao guestbookDao = new GuestBookDao();
			guestbookDao.setDbConnectionPool(connPool);
			
			sc.setAttribute("guestbookDao", guestbookDao);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		connPool.closeAll();
	}

}
