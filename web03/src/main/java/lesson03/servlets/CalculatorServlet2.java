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

//@WebServlet("/calc2")
@WebServlet(urlPatterns={"/calc2", "/calc.do", "/calc.action"})
@SuppressWarnings("serial")
public class CalculatorServlet2 extends GenericServlet {
	
	@Override
	public void service(
			ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String operator = request.getParameter("op");
		int v1 = Integer.parseInt(request.getParameter("v1"));
		int v2 = Integer.parseInt(request.getParameter("v2"));
		String s1 = request.getParameter("hangul");
		int result = 0;
		System.out.println(s1);
		response.setContentType("text/plain;chartset=UTF-8");
		response.setCharacterEncoding("UTF-8");
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
		allCal(v1, v2, out, s1);
		out.println();
		out.println(v1 + " " + operator + " " + v2 + " = " + result);
		
	}
	
	public void allCal(int v1, int v2, PrintWriter out, String s1) {
		
		out.println("a=" + v1 + ",b=" + v2 + " 한글 테스트" + s1);
		out.println("a + b = " + (v1 + v2));
		out.println();
		out.println("a - b = " + (v1 - v2));
		out.println();
		out.println("a * b = " + v1 * v2);
		out.println();
		out.println("a / b = " + v1 / v2);
		out.println();
		out.println("a % b = " + v1 % v2);
	}

}

