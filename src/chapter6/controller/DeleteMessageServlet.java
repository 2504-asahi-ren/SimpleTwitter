package chapter6.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/deleteMessage" })

public class DeleteMessageServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String messageId = request.getParameter("id");

		new MessageService().delete(messageId);
		response.sendRedirect("./");

	}
}
