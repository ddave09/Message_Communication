/*This file is one above level of pojo classes it has methods like call_first() which is required to configure hibernate for specific schema using 
hibernate.cfg.xml here schema is "captain". 
*/
import java.util.*;
import java.sql.Timestamp;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.QueryException;
import org.hibernate.QueryParameterException;
import org.hibernate.mapping.Column;

public class ManageMessage {
	private static SessionFactory factory;
	/**
	@ brief:this function is to create initialize SessionFactory object
	@ parameter: void
	@ retval: void
	**/
	public void call_first(){
	
			try{
					factory = new Configuration().configure().buildSessionFactory();
			}
			catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
			}
			
		}
		/**
		@ brief: addMessage adds message to database. it uses Message class's consructor to save meesage in database 
		@ parameter: to : int
					from : int
					send_date_time : Date
					read: boolean
					messagetext : String 
					reply : boolean
		@ retval: void 
		**/
		public void addMessage(int to, int from, Date send_date_time, boolean read_flag, String message,boolean reply_flag) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer messageID = null;
		
		try{
				tx = session.beginTransaction();
				/** msg is object of class Message **/
				Message msg = new Message(to,from,send_date_time,read_flag,message,reply_flag);
				/** session.saveOrUpdate stores message in database **/
				session.saveOrUpdate(msg);
				tx.commit();
			}
		catch(HibernateException e) {
				if(tx!=null) tx.rollback();
				e.printStackTrace();
			}
		finally{
				session.close();
			}
		
		}
		
		/** 
		@ brief: getMessages gets all messages for logged in user 
		@ parameter: Integer that is user id 
		@ retval : List of type Message 
		**/
		public List getMessages(int u_id){
			Session session = factory.openSession();
			Transaction tx = null;
			List messages = null;
			Object object;
			int count =0;
			try{
				tx = session.beginTransaction();
				/** hibernate query to fetch messages **/
				messages = session.createCriteria(Message.class).add(Restrictions.like("t", u_id)).list();
				tx.commit();
			}
			catch(HibernateException e){
				if(tx!=null) tx.rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
			return messages;
		}
		
		/** 
		@ brief: deleteMessage deletes message of given message id 
		@ parameter: Integer that is message id
		@ retval: void
		**/
		public void deleteMessage(Integer messageID){
			Session session = factory.openSession();
			Transaction tx = null;
				try{
						tx = session.beginTransaction();
						/** msg is object of Message class **/
						Message msg = (Message)session.get(Message.class, messageID);
						session.delete(msg);
						tx.commit();
				}
				catch(HibernateException e){
						if(tx!=null) tx.rollback();
						e.printStackTrace();
				}
				finally{
						session.close();
				}
		}
		
		/** 
		@ brief: setRead_Value sets read value of message with message id to 1
		@ parameter: Integer that is message id 
		@ retval : void 
		**/
		public void setRead_value(Integer messageId ){
			Session session = factory.openSession();
			Transaction tx = null;
			try{
				tx = session.beginTransaction();
				boolean r = true;
				String hql = "UPDATE Message SET read = :r WHERE id = :messageId";
				Query query = session.createQuery(hql);
				query.setParameter("messageId",messageId);
				query.setParameter("r",r);
				query.executeUpdate();
				tx.commit();
			}
			catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			}
			finally {
				session.close(); 
			}
		}
		
		/** 
		@ brief: getSenderNames gets sender name of message of given sender id 
		@ parameter: Integer that sender id 
		@ retval : List of type employee
		**/
		public List getSenderName(Integer senderId){
			Session session = factory.openSession();
			Transaction tx = null;
			List employee = null;
			try{
				tx = session.beginTransaction();
				employee = session.createCriteria(Employee.class).add(Restrictions.like("id", senderId)).list();
				tx.commit();
			}
			catch(HibernateException e){
				if(tx!=null) tx.rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
			return employee;
		}
		
		/** 
		@ brief: gets names of employees to whom user is allowed to message 
		@ parameter: userId : Integer that is user id 
					 type : Integer that is type of the user 
					 team  : Integer that is team of the user
					
		@ retval :  List of Type Employee
		**/
		public List fetchNames(Integer userId,Integer type,Integer team){
			Session session = factory.openSession();
			Transaction tx = null;
			List names = null;
			try{
				tx = session.beginTransaction();
				names = session.createCriteria(Employee.class).add(Restrictions.disjunction().add(Restrictions.and(Restrictions.like("type",type-1),Restrictions.like("team",userId)))
							.add(Restrictions.and(Restrictions.like("type",type),Restrictions.like("team",team))).add(Restrictions.like("id",team))).list();
				tx.commit();
			}
			catch(HibernateException e){
				if(tx!=null) tx.rollback();
				e.printStackTrace();
			}
			finally{
				session.close();
			}
			return names;
		}
		
		/** 
		@ brief:  gets number of sent messages for given user id 
		@ parameter: int that is user id 
		@ retval :  List of Type Employee
		**/
		public List getCountOfMessage(int userId){
				Session session = factory.openSession();
				Transaction tx = null;
				List emp = null;;
				try{
					tx = session.beginTransaction();
					emp = session.createCriteria(Employee.class).add(Restrictions.like("id",userId)).list();
					tx.commit();
				}
				catch(HibernateException e){
					if(tx!=null) tx.rollback();
					e.printStackTrace();
				}
				finally{
					session.close();
				}
			return emp;
			}
		
		/** 
		@ brief: increments sent messages count by 1 for given user id  
		@ parameter: int that is user id 
		@ retval : void
		**/
		public void incCountOfMessage(int userId){
			Session session = factory.openSession();
			Transaction tx = null;
			List emp;
			try{
				tx = session.beginTransaction();
				emp = session.createCriteria(Employee.class).add(Restrictions.like("id",userId)).list();
				Iterator iterator = emp.iterator();
				Employee employee = (Employee)iterator.next();
				int count = employee.getCmc();
				count++;
				String hql = "UPDATE Employee SET cmc = :count WHERE id = :userId";
				Query query = session.createQuery(hql);
				query.setParameter("userId",userId);
				query.setParameter("count",count);
				query.executeUpdate();
				tx.commit();
			}
			catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			}
			finally {
				session.close(); 
			}
		} 
		
		/** 
		@ brief: gets employee type for given user id 
		@ parameter: int user id
		@ retval : int that is employee type
		**/
		public int getEmpType(int userId){
			Session session = factory.openSession();
			Transaction tx = null;
			List emp;
			int type=0;
			try{
				tx = session.beginTransaction();
				emp = session.createCriteria(Employee.class).add(Restrictions.like("id",userId)).list();
				Iterator iterator = emp.iterator();
				Employee employee = (Employee)iterator.next();
				type = employee.getType();
				tx.commit();
			}
			catch (HibernateException e) {
				if (tx!=null) tx.rollback();
				e.printStackTrace(); 
			}
			finally {
				session.close(); 
			}
			return type;
		}
}



	
			
			
			
