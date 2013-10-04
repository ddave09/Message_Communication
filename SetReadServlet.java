import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;


public class SetReadServlet extends HttpServlet{
		public ServletConfig sconfig; 

		public void doGet(HttpServletRequest rq, HttpServletResponse rs) throws IOException,ServletException{
		
			/** String mid is message id **/
			String mid = rq.getParameter("mid_");
			/** parsing String to Integer **/
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
			@brief : it is a call to setRead_value() method of ManageMessage class 
			@parameter: Integer that is message id
			@retval: void
			**/
			MM.setRead_value(m_id);  
			
		
		}
}
