package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/update")
@SuppressWarnings("serial")
public class MemberUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));

			// 2. 드라이버를 사용하여 MySQL 서버와 연결
			conn = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("username"),
					ctx.getInitParameter("password"));

			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"select MNO, EMAIL, MNAME, CRE_DATE from MEMBERS" + " where MNO=" + req.getParameter("no"));

			rs.next();

			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();

			out.println("<html><head><title>회원정보</title></head>");
			out.println("<body><h1>회원정보</h1>");
			out.println("<form action='update' method='post'>");
			out.println(
					"번호: <input type='text' name='no' value='" + req.getParameter("no") + "' readonly><br>");
			out.println("이름: <input type='text' name='name' value='" + rs.getString("MNAME") + "'><br>");
			out.println("이메일: <input type='text' name='email' value='" + rs.getString("EMAIL") + "'><br>");
			out.println("가입일: " + rs.getDate("CRE_DATE") + "<br>");
			out.println("<input type='submit' value='저장'>");
			out.println("<input type='button' value='삭제' onclick='location.href=\"delete?no="
					+ req.getParameter("no") + "\"'>");
			out.println("<input type='button' value='취소' onclick='location.href=\"list\"'>");
			out.println("</form>");
			out.println("</body></html>");
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
			} catch (Exception e) {
			}
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// filter 맵핑해서 /*전체에 적용
		// req.setCharacterEncoding("UTF-8");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			
			// 2. 드라이버를 사용하여 MySQL 서버와 연결
			conn = DriverManager.getConnection(ctx.getInitParameter("url"),
					ctx.getInitParameter("username"),
					ctx.getInitParameter("password"));

			stmt = conn.prepareStatement("UPDATE MEMBERS SET EMAIL=?, MNAME=?, MOD_DATE=now()" + " WHERE MNO=?");
			stmt.setString(1, req.getParameter("email"));
			stmt.setString(2, req.getParameter("name"));
			System.out.println("no = "+ Integer.parseInt(req.getParameter("no")));
			stmt.setInt(3, Integer.parseInt(req.getParameter("no")));

			stmt.executeUpdate();

			res.sendRedirect("list");

		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (Exception e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
	}
}
