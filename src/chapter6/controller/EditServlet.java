package chapter6.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import chapter6.beans.Message;
import chapter6.beans.User;
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
		}.getClass().getEnclosingClass().getName() +" : " + new Object() {
				}.getClass().getEnclosingMethod().getName());

		HttpSession session = request.getSession();
		List<String> errorMessages = new ArrayList<String>();

		String messageId = request.getParameter("id");
		//つぶやきの編集画面でIDがnullの時、数字以外の時にエラーを出す。
		if(messageId == null || !messageId.matches("^[0-9]+$")) {
			errorMessages.add("不正なパラメータが入力されました");
			session.setAttribute("errorMessages",errorMessages);
			request.getRequestDispatcher("./").forward(request,response);
		}

		Message message =new MessageService().messageSelect(messageId);
		User user = (User)session.getAttribute("loginUser");

		//つぶやきの編集画面で存在しないIDを入力したとき。
		if(message == null ||message.getUserId() != user.getId()){
			errorMessages.add("不正なパラメータが入力されました");
			session.setAttribute("errorMessages",errorMessages);
			request.getRequestDispatcher("./").forward(request,response);
		}

		request.setAttribute("message", message);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Message message = new Message();
		HttpSession session = request.getSession();
		List<String> errorMessages = new ArrayList<String>();

		message.setId(Integer.parseInt(request.getParameter("id")));
		message.setText(request.getParameter("text"));

		if (StringUtils.isBlank(request.getParameter("text"))) {
			errorMessages.add("メッセージを入力してください");
		} else if (140 < request.getParameter("text").length()) {
			errorMessages.add("140文字以下で入力してください");
		}

		if (errorMessages.size() != 0) {
			request.setAttribute("errorMessages",errorMessages);
			session.setAttribute("message", message);
			request.getRequestDispatcher("edit.jsp").forward(request,response);
		return;
		}

		new MessageService().update(message);

		response.sendRedirect("./");
	}
}
