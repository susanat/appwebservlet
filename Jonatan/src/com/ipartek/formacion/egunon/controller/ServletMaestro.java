package com.ipartek.formacion.egunon.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import com.ipartek.formacion.egunon.bean.UserLogin;

public class ServletMaestro extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Configuracion basica del Logger
		String prefix = getServletContext().getRealPath("/");
		String log4jPath = getInitParameter("log4j-config");
		if (log4jPath != null) {
			PropertyConfigurator.configure(prefix + log4jPath);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserLogin userLogin = (UserLogin) request.getSession().getAttribute("login");
		String[] refererArray = request.getRequestURI().split("/");
		String referer = refererArray[refererArray.length - 1];

		if ((userLogin != null) || "login".equalsIgnoreCase(referer) || "logout".equalsIgnoreCase(referer) || "index.jsp".equalsIgnoreCase(referer)) {
			super.service(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}
}
