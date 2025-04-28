package chapter6.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter6.beans.UserMessage;
import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/editMessage" })

public class EditServlet  extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String messageId = request.getParameter("id");
		List<UserMessage> messages =new MessageService().messageSelect(messageId);

		request.setAttribute("messages", messages);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}
}
