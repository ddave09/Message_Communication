/*This servlet gets List of messages and passes it to ViewMessage1.jsp*/


import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.*;
import java.sql.Timestamp;

public class ViewMessageServlet extends HttpServlet implements Serializable{
		
		public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException,ServletException{
			int u_id = 510; // get user id from session variable in full application
			int type = 1; // get user type from session variable in full application
			int team = 511;  // get user team from session variable in full application
			HttpSession session = rq.getSession();
			
			/**
			MM is object of ManageMessage class 
			**/
			ManageMessage MM = new ManageMessage();  
			/**
			@brief : it is a call to call_first() method of ManageMessage class 
			@parameter: void
			@retval: void
			**/
			MM.call_first();	

			/** 
			@ brief: it is a call to getMessages method of ManageMessage class
			@ parameter: Integer which is user id
			@ retval: List of message of type Message
			**/
			List messages =(List)MM.getMessages(u_id);  
			String user = "root";						/* Database user name*/
			String pass = "admin";						/* Database password */
			
			/** session attributes **/
			/* comment sessions of u_id, type, team after the whole application is developed as we will take this variable in session at login time */
			session.setAttribute("userId",u_id);
			session.setAttribute("type",type);
			session.setAttribute("team",team);
			session.setAttribute("messages", messages);
			session.setAttribute("user",user);
			session.setAttribute("pass",pass);
			RequestDispatcher rd=rq.getRequestDispatcher("/viewMessage1.jsp");
			rd.forward(rq,rs);
		}
		
		public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException{
			doPost(rq,rs);
		}
	
		
}
