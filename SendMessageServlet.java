/*This servlet sends message and goes to composeMessage.jsp*/


import javax.servlet.*;
import javax.servlet.http.*;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Iterator;
import java.io.*;
import java.sql.Timestamp;

public class SendMessageServlet extends HttpServlet implements Serializable{
				
		public void doPost(HttpServletRequest rq, HttpServletResponse rs) throws IOException,ServletException{
			HttpSession session = rq.getSession();
			/** String eid is employee id **/
			String eid = rq.getParameter("eid");
			int to = Integer.parseInt(eid);
			/** String message is messagetext **/
			String message = rq.getParameter("message");
			Date date= new Date();
			/** ts is current timestamp used to set sent message time **/
			Date ts = new Timestamp(Calendar.getInstance().getTime().getTime());
			int userId = (Integer)session.getAttribute("userId");
			/** flag is value to be set in read flag and reply flag **/
			boolean flag = false;
			/** sent flag is value for message successfully sent **/
			int sent_flag = 0;
			/** MM is object of ManageMessage class **/
			ManageMessage MM = new ManageMessage();
			/**
			@brief : it is a call to call_first() method of ManageMessage class 
			@parameter: void
			@retval: void
			**/
			MM.call_first();		

			/**
			@brief: getEmpType is method of ManageMessage class which gets employee's type to check whether employee is player or not 
			@parameter: Integer that is user id.
			@retval : employee type.
			**/
			int type = MM.getEmpType(userId);
			/** checks whether employee is player or not if yes checks sent messages in single day if messages are less than count of 3
			player is allowed to send message otherwise not or if employee is not player than employee is allowed to send message 
			**/
			/** type "1" is set for players **/
			if(type==1){
				/** 
				@brief: getCountOfMessage is method of ManageMessage class 
				@parameter: Integer that is user id.
				@retval: List of type Employee.
				**/
				List emp = (List)MM.getCountOfMessage(userId);
				Iterator iterator = emp.iterator();
				Employee employee = (Employee)iterator.next();
				/** 
				@ brief: getCmc() is method of Employee class 
				@ parameter: void
				@ retval: number of sent messages 
				**/
				int count = employee.getCmc();
				if(count>=3){
				/** not_sent_flag is flag indicates that message is not sent */
				int not_sent_flag = 1;
				/** sets session attrribute **/
				rq.setAttribute("not_sent_flag",not_sent_flag);
				RequestDispatcher rd=rq.getRequestDispatcher("/composeMessage.jsp");
				rd.forward(rq,rs);
				}
				else{
					/**
					@ brief: addMessage() is method of ManageMessage class. it adds message in database
					@ parameter : 
									@ to : Integer that is message sent to
									@ userId : Integer that is meesage sent from
									@ ts  : Timestamp that is message sent time 
									@ flag: boolean that is read_flag
									@ message : String that is messagetext
									@ flag: boolean that is reply_flag
					@ retval : void 
					**/
					MM.addMessage(to,userId,ts,flag,message,flag);  
					/**
					@ brief: incCountOfMessage is method of ManageMessage class. it increments count of sent message in database
					@ parameter: Integer that is user id.
					@ retval : void 
					**/
					MM.incCountOfMessage(userId);
					/** sent_flag is indicates message is sent successfully **/
					sent_flag = 1;
					rq.setAttribute("sent_flag",sent_flag);
					RequestDispatcher rd=rq.getRequestDispatcher("/composeMessage.jsp");
					rd.forward(rq,rs);
				}
			}
			else{
				/**
					@ brief: addMessage() is method of ManageMessage class. it adds message in database
					@ parameter : 
									@ to : Integer that is message sent to
									@ userId : Integer that is meesage sent from
									@ ts  : Timestamp that is message sent time 
									@ flag: boolean that is read_flag
									@ message : String that is messagetext
									@ flag: boolean that is reply_flag
					@ retval : void 
					**/
				MM.addMessage(to,userId,ts,flag,message,flag); 
				/** sent_flag is indicates message is sent successfully **/
				sent_flag = 1;
				rq.setAttribute("sent_flag",sent_flag);
				RequestDispatcher rd=rq.getRequestDispatcher("/composeMessage.jsp");
				rd.forward(rq,rs);
			}
			
		}
		
		public void doGet(HttpServletRequest rq,HttpServletResponse rs) throws IOException,ServletException{
			doPost(rq,rs);
		}
	
		
}
