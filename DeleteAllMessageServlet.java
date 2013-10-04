import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class DeleteAllMessagesServlet extends HttpServlet{
			
		
		
		public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException,ServletException{
			HttpSession session = rq.getSession();
			/** messages is list of messages **/
			List messages = (List)session.getAttribute("messages");
			/** MM is object of ManageMessage class **/
			ManageMessage MM = new ManageMessage();  
			/**
			@brief : it is a call to call_first() method of ManageMessage class 
			@parameter: void
			@retval: void
			**/
			MM.call_first();	
				/** foreach loop loops for every message **/
				for(Object obj : messages){
					/** msg is object of class Message  **/
					Message msg = (Message) obj;
					/**
					@brief : deleteMessage deletes message for given message id 
					@parameter: Integer that is message id
					@retval: void
					**/
					MM.deleteMessage(msg.getId());
				}
			
			RequestDispatcher rd=rq.getRequestDispatcher("/vms");
			rd.forward(rq,rs);
		}
		
		public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException{
			doPost(rq,rs);
		}
	

}
