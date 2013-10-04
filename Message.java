/*This is Member class which is a map to "message" table in the database. it has all getter and setter methods required to access each field in "message" 
table */

import java.util.*;
import java.text.*;
import java.sql.Timestamp;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")

public class Message implements Serializable {
   private int id;				// mapping for the column name "m_id" in database name "captain" and table name "message"
   private int t; 				// mapping for the column name "to" in database name "captain" and table name "message"
   private int frm;   			// mapping for the column name "frm" in database name "captain" and table name "message"
   private boolean read;   		// mappping for the column name "read_flag" in database name "captain" and table name "message"
   private boolean reply;   	// mapping for the column name "reply_flag" in database name "captain" and table name "message"
   private String messagetext;	// mapping for the column name "messagetext" in database name "captain" and table name "message"
   private Date sdt;            // mapping for the column name "send_date_time" in database name "captain" and table name "message"
   
  
   public Message() {}
   /*Initializing Message object to map with the table name "message" in database name "captain" */
   /** 
	@brief: use this consrtuctor to add message 
	@parameter: t : int
				frm : int
				sdt : Date
				read: boolean
				messagetext : String 
				reply : boolean
	**/
   
   public Message(int t, int frm, Date sdt, boolean read, String messagetext, boolean reply) {
      this.t = t;
      this.frm = frm;
	  this.sdt = sdt;
	  this.read = read;
	  this.messagetext = messagetext;
	  this.reply = reply;
   }
   
  
   /* getter and setter properties for table name message */
   
   /**
   @ brief : gets message id 
   @ parameter : void 
   @ retval : message id
   **/
   public int getId() {
      return id;
   }
   
    /**
   @ brief : sets message id 
   @ parameter : integer 
   @ retval : message id
   **/
   
   public void setId( int id ) {
      this.id = id;
   } 
   
    /**
   @ brief : gets employee's id  
   @ parameter : void 
   @ retval : employee's id
   **/
   
   public int getT() {
      return t;
   }
   
    /**
   @ brief : sets employee id 
   @ parameter : integer
   @ retval : void
   **/
   
   public void setT( int t ) {
      this.t = t;
   }
   
    /**
   @ brief : gets employee's id  
   @ parameter : void 
   @ retval : employee's id
   **/
   public int getFrm() {
      return frm;
   }
   
    /**
   @ brief : sets employee's id 
   @ parameter : integer
   @ retval : void
   **/
   public void setFrm( int frm ) {
      this.frm = frm;
   }
   
    /**
   @ brief : gets read flag  
   @ parameter : void
   @ retval : read_flag
   **/
   public boolean getRead() {
      return read;
   }
   
    /**
   @ brief : sets read flag
   @ parameter : true/false
   @ retval : void
   **/
   public void setRead( boolean read ) {
	 // System.out.println(read);
      this.read = read;
   }
   
    /**
   @ brief : gets reply falg
   @ parameter : void 
   @ retval : reply_flag
   **/
   public boolean getReply() {
	  return reply;
   }
   
    /**
   @ brief : sets reply_flag 
   @ parameter : true/false
   @ retval : void
   **/
   public void setReply( boolean reply){
	  this.reply= reply;
   }
   
    /**
   @ brief : gets message sent time 
   @ parameter : void 
   @ retval : message sent time
   **/
   public Date getSdt(){
      return sdt;
   }
   
    /**
   @ brief : sets message sent time
   @ parameter : timestamp 
   @ retval : void
   **/
   public void setSdt( Timestamp sdt){
	  this.sdt = sdt;
   }
   
    /**
   @ brief : gets message text 
   @ parameter : void 
   @ retval : message text
   **/
   public String getMessagetext(){
	  return messagetext;
   }
   
    /**
   @ brief : gets message text 
   @ parameter : String
   @ retval : void
   **/
   public void setMessagetext( String messagetext){
      this.messagetext= messagetext;
   }
   
   
}
