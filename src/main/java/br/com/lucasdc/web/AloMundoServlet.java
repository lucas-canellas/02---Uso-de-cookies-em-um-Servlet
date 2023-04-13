package br.com.lucasdc.web;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/alomundo")
public class AloMundoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String msg = "Alo, mundo!";
		String nome = request.getParameter("nome");

		if (nome != null) {
			Cookie cookie = new Cookie("cookie-nome", nome);
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
			msg = "Alo, " + nome + "!";
		} else {
			Cookie cookies[] = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("cookie-nome"))
						msg = "Alo, " + cookies[i].getValue() + "!";
				}
			}
		}

		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet AloMundoServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<P>" + msg + "</P>");
			out.println("</body>");
			out.println("</html>");
		}
	}
}