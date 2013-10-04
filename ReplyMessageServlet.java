/*This Servlet get called on Reply action. it will pass name of recepient 
to jsp page */

import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;

public class ReplyMessageServlet extends HttpServlet{
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException{
		HttpSession session = rq.getSession();
		/** sender_id is employee id who sends message **/
		String sender_id = rq.getParameter("sender_id");
		Integer s_id = Integer.parseInt(sender_id);
		/** MM is object of ManageMessage class **/
		ManageMessage MM = new ManageMessage();
		/**
			@brief : it is a call to call_first() method of ManageMessage class 
			@parameter: void
			@retval: void
			**/
		MM.call_first();
		/** 
		@ brief: getSenderName is method of ManageMessage class which gets sender's name 
		@ parameter : Integer that is sender's employee id
		@ retval: List of type Employee
		**/
		List iname = (List)MM.getSenderName(s_id);
		session.setAttribute("iname",iname);
		RequestDispatcher rd = rq.getRequestDispatcher("/composeMessage.jsp");
		rd.forward(rq,rs);		
	}
	
	public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException,IOException{
		doPost(rq,rs);
	}
}
