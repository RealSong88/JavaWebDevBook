package spms.listners;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import spms.dao.MemberDao;

public class ContextLoaderListner implements ServletContextListener{
	Connection conn;
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {

		try {
			ServletContext sc = event.getServletContext();
			
			Class.forName(sc.getInitParameter("driver"));
			conn = DriverManager.getConnection(
					sc.getInitParameter("url"),
					sc.getInitParameter("username"),
					sc.getInitParameter("password")
					);
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			sc.setAttribute("memberDao", memberDao);
		} catch(Throwable e) {
			e.printStackTrace();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			conn.close();
		} catch (Exception e) {}
	}

}
