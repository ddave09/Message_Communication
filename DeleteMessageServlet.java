import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class DeleteMessageServlet extends HttpServlet{
				public ServletConfig sconfig; 
		
		
		
		public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException,ServletException{
			/** String mid is message id **/
			String mid = rq.getParameter("mid");
			Integer m_id = Integer.parseInt(mid);
			/** MM is object of ManageMessage class **/
			ManageMessage MM = new ManageMessage();  
			/**
			@brief : it is a call to call_first() method of ManageMessage class 
			@parameter: void
			@retval: void
			**/
			MM.call_first();	
			/**
			@brief : deleteMessage deletes message for given message id 
			@parameter: Integer that is message id
			@retval: void
			**/
			MM.deleteMessage(m_id);
			
			RequestDispatcher rd=rq.getRequestDispatcher("/vms");
			rd.forward(rq,rs);
		}
		
		public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException{
			doPost(rq,rs);
		}
	

}
