package chapter6.controller;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chapter6.beans.Message;
import chapter6.exception.NoRowsUpdatedRuntimeException;
import chapter6.service.MessageService;

@WebServlet(urlPatterns = { "/editMessage" })

public class EditServlet  extends HttpServlet{
	/**
	* ロガーインスタンスの生成
	*/
	Logger log = Logger.getLogger("twitter");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {


		log.info(new Object() {
		}.getClass().getEnclosingClass().getName() +
				" : " + new Object() {
				}.getClass().getEnclosingMethod().getName());

		String messageId = request.getParameter("id");

		if(!messageId.matches(("^[0-9]+$"))) {
			request.getRequestDispatcher("./").forward(request,response);

		}

		Message message =new MessageService().messageSelect(messageId);

		request.setAttribute("message", message);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//		HttpSession session = request.getSession();

//		List<String> errorMessages = new ArrayList<String>();

		Message message = new Message();
		message.setId(Integer.parseInt(request.getParameter("id")));
		message.setText(request.getParameter("text"));
			try {
				new MessageService().update(message);
			} catch (NoRowsUpdatedRuntimeException e) {
			}
//		session.setAttribute("loginUser", message);
		response.sendRedirect("./");
	}
}
