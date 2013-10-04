import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

public class ComposeMessageServlet extends HttpServlet{
	public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException,ServletException {
		HttpSession session = rq.getSession();
		Integer userId = (Integer)session.getAttribute("userId");
		Integer type = (Integer)session.getAttribute("type");
		Integer team = (Integer)session.getAttribute("team");
		/** MM is object of ManageMessage class **/
		ManageMessage MM = new ManageMessage();
		/**
			@brief : deleteMessage deletes message for given message id 
			@parameter: Integer that is message id
			@retval: void
		**/
		MM.call_first();
		/** 
		@ brief: fetchNames is method of class ManageMessage which fetches names of the employees
		@ parameter : userId : Integer that is user id 
					  type   : Integer that is type of the employee
					  team   : Integer that is team of the employee
		@ retval : List of type Employee 
		**/
		List names = MM.fetchNames(userId,type,team);
		session.setAttribute("iname",names); 
		RequestDispatcher rd = rq.getRequestDispatcher("/composeMessage.jsp");
		rd.forward(rq,rs);
	}
	
	public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException{
		doPost(rq,rs);
	}
}
