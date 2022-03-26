package spms.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list")
@SuppressWarnings("serial")
public class MemberListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 사용할 JDBC 드라이버를 등록하라
			//DriverManager.registerDriver(new com.mysql.jdbc.Driver());
//			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
//			ServletConfig config = this.getServletConfig();
//			Class.forName(config.getInitParameter("driver"));
//			System.out.println("비교 테스트 : ");
//			System.out.println(getServletInfo() == this.getServletInfo());
			ServletContext ctx = this.getServletContext();
			Class.forName(ctx.getInitParameter("driver"));
			
			// 2. 드라이버를 사용하여 MySQL 서버와 연결
			conn = DriverManager.getConnection(ctx.getInitParameter("url"),
					ctx.getInitParameter("username"),
					ctx.getInitParameter("password"));
			
			// 3. 커넥션 객체로부터 SQL을 던질 객체를 준비
			stmt = conn.createStatement();
			// 4. SQL을 던지는 객체를 사용하여 서버에 질의
			rs = stmt.executeQuery("SELECT MNO, MNAME, EMAIL, CRE_DATE" +
			" from MEMBERS" +
			" order by MNO ASC"
			);
			
			// 5. 서버에서 가져온 데이터를 사용하여 HTML 만들어서 웹브라우저로 출력
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
			out.println("<html><head><title>회원목록</title></head><body>");
			out.println("<h1>회원목록</h1>");	
			out.println("<p><a href='add'> 신규회원</a><p>");
			while(rs.next()) {
				out.println(rs.getInt("MNO") +". " +
						"<a href='update?no=" + rs.getInt("MNO")+ "'>" +
						rs.getString("MNAME") + "</a>, " +
						rs.getString("EMAIL") + ", " +
						rs.getString("CRE_DATE") +
						"<a href='delete?no=" + rs.getInt("MNO") + "'>[삭제]</a>"
						+ "<BR>"
						);
//				System.out.println("만든날짜(getDate) : " + rs.getDate("CRE_DATE") 
//				+ ", 만든날짜(getString) : " + rs.getString("CRE_DATE"));
			}
			out.println("</body></html>");
			
//		} catch (SQLException e) {
		} catch (Exception e) { // classNotFoundException 과, SQL 에러를 Exception으로 처리
			throw new ServletException(e);
		} finally {
			
			try {rs.close();} catch (Exception e) {}
			try {stmt.close();} catch (Exception e) {}
			try {conn.close();} catch (Exception e) {}
			
		}
	}
	

}
