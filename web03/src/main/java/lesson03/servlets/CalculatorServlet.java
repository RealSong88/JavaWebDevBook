package lesson03.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/calc")
@SuppressWarnings("serial")
public class CalculatorServlet extends GenericServlet {
	
	@Override
	public void service(
			ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String operator = request.getParameter("op");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		int result = 0;
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		switch (operator) {
		case "+": result = v1 + v2; break;
		case "-": result = v1 - v2; break;
		case "*": result = v1 * v2; break;
		case "/": 
			if (v2 == 0) {
				out.println("0 으로 나눌 수 없습니다!");
				return;
			}
			
			result = v1 / v2; 
			break;
		}
		
		out.println(v1 + " " + operator + " " + v2 + " = " + result);
		
		// my Test
		System.out.println(request.getRemoteAddr() + "\n" + 
				request.getRemoteAddr() + "\n" +
				request.getScheme() + "\n" +
				request.getProtocol() + "\n" +
				request.getParameterNames().toString() + "\n" +
				request.getParameterValues("v1").toString() + "\n" +
				request.getParameterMap().get("v1") + "\n" 
		
		);
		Enumeration<String> e = request.getParameterNames();
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement());
		}
		
		System.out.println("----------------------");
		String[] arr = request.getParameterValues("v1");
		for(String l : arr) {
			System.out.println(l);
		}
		System.out.println("----------------------");
		
		 Map<String, String[]> map = request.getParameterMap();
		 map.forEach((key, value) 
				 -> System.out.println("key : " + key + ", value : " + value[0]));
	}

}
