package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.dao.MemberDao;
import spms.vo.Member;

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;

		try {
			ServletContext sc = this.getServletContext();
//			Class.forName(sc.getInitParameter("driver"));
//
//			// 2. 드라이버를 사용하여 MySQL 서버와 연결
//			conn = DriverManager.getConnection(sc.getInitParameter("url"), sc.getInitParameter("username"),
//					sc.getInitParameter("password"));
			
			conn = (Connection) sc.getAttribute("conn");

			Member member = new Member();
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			member = memberDao.selectOne(Integer.parseInt(req.getParameter("no")));
			res.setContentType("text/html; charset=UTF-8");
			
			req.setAttribute("member", member);
			RequestDispatcher rd = req.getRequestDispatcher("/member/MemberUpdateForm.jsp");
			rd.include(req, res);
			
		} catch (Exception e) {
//			throw new ServletException(e);
			req.setAttribute("error",  e);
			RequestDispatcher rd = req.getRequestDispatcher("/Error.jsp");
			rd.forward(req, res);
		} finally {
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// filter 맵핑해서 /*전체에 적용
		// req.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ServletContext sc = this.getServletContext();
			
			conn = (Connection) sc.getAttribute("conn");
			
			
			MemberDao memberDao = new MemberDao();
			memberDao.setConnection(conn);
			
			memberDao.update(new Member()
					.setEmail(req.getParameter("email"))
					.setName(req.getParameter("name"))
					.setNo(Integer.parseInt(req.getParameter("no")))
					);
			

			res.sendRedirect("list");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
		}
	}
}