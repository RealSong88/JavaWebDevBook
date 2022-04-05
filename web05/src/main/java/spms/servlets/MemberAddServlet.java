package spms.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/add")
@SuppressWarnings("serial")
public class MemberAddServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		
		RequestDispatcher rd = req.getRequestDispatcher("/member/MemberForm.jsp");
		rd.include(req, res);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		//filter 맵핑해서 /*전체에 적용
		//req.setCharacterEncoding("UTF-8"); 
		try {
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
//			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3307/studydb", //JDBC URL
//					"study",	// DBMS 사용자 아이디
//					"study");	// DBMS 사용자 암호
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			memberDao.insert(new Member()
					.setEmail(req.getParameter("email"))
					.setName(req.getParameter("name"))
					.setPassword(req.getParameter("password")));
			
			
			RequestDispatcher rd = req.getRequestDispatcher("/member/AddSuccess.jsp");
			System.out.println("test... ==================");
			rd.forward(req, res);
		} catch (Exception e) {
//			throw new ServletException(e);
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/member/AddFail.jsp");
			rd.forward(req, res);
		} finally {
			try {if (stmt != null) stmt.close();} catch(Exception e) {}
		}
	}
}
