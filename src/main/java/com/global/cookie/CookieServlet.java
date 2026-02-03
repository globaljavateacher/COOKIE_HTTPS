package com.global.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println(this);
        String uuid = UUID.randomUUID().toString();
        Cookie secureCookie = new Cookie("uuid", uuid);
        secureCookie.setHttpOnly(true);
        secureCookie.setSecure(true);
        secureCookie.setPath("/");
        secureCookie.setAttribute("SameSite", "Strict");
        resp.addCookie(secureCookie);
        PrintWriter pw = resp.getWriter();
        pw.printf("<h1>%s</h1>", uuid);
        pw.printf("<h3>scheme:%s, secure:%s</h3>", req.getScheme(), req.isSecure());
        pw.close();
	}
}

