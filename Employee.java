/*This is Pojo class which is a map to "employee" table in the database. it has all getter and setter methods required to access each field in "employee" 
table */

import java.sql.Timestamp;
import java.io.Serializable;
public class Employee implements Serializable {
	int id;								// map for field emp_id
	String name;						// map for field name
	int type;							// map for field type
	int team;							// map for filed team
	int cmc;							// cms stands for current_message_count
	int mml;							// mml stands for maximum_message_limit
	Timestamp ll = new Timestamp(12);	// ll stands for last_login
	boolean ls;							// ls stands for login_status
	
	public Employee(){}
	/**
	@brief : use this consrtuctor to add employee 
	@parameters : name: string
			      type: int
				  team: int
				  cmc : int
				  mml : int
				  ll  : Timestamp
				  ls  : boolean
	**/
	public Employee(String name, int type, int team, int cmc, int mml, Timestamp ll, boolean ls){
	this.name = name;
	this.type = type;
	this.team = team;
	this.cmc  = cmc;
	this.mml  = mml;
	this.ll   = ll;
	this.ls   = ls;
	}
	
	/* getter and setter properties for table name employee */
	
	/**
	* @brief : gets employee id from database captain table employee
	* @paramters : void
	* @retval : returns employee's id 
	**/
	public int getId(){
		return id;
	}
	
	/**
	* @brief : sets employee id in database captain table employee
	* @paramters : integer
	* @retval : returns void 
	**/
	public void setId(int id){
		this.id = id;
	}
	
	/**
	* @brief : gets employee name from database captain table employee
	* @paramters : void
	* @retval : returns employee's name 
	**/
	public String getName(){
		return name;
	}
	
	/**
	* @brief : sets employee name in database captain table employee
	* @paramters : string
	* @retval : void 
	**/
	
	public void setName(String name){
		this.name = name;
	}
	/**
	* @brief : gets employee type i.e emplyoee's designation in company from database captain table employee
	* @paramters : void
	* @retval : returns employee type
	**/
	
	public int getType(){
		return type;
	}
	
	/**
	* @brief : sets employee type i.e employee's designation in company in database captain table employee
	* @paramters : integer
	* @retval : void
	**/
	public void setType(int type){
		this.type = type;
	}
	
	/**
	* @brief : gets employee team i.e team leader's employee id from database captain table employee
	* @paramters : void
	* @retval : returns team number i.e team leader's employee's id
	**/
	
	public int getTeam(){
		return team;
	}
	
	/**
	* @brief : sets employee team i.e team leaders's employee id in database captain table employee
	* @paramters : integer
	* @retval : void
	**/
	public void setTeam(int team){
		this.team = team;
	}
	
	/**
	* @brief : gets employee's sent messages in single day from database captain table employee
	* @paramters : void
	* @retval : number of sent messages  
	**/
	public int getCmc(){
		return cmc;
	}
	
	/**
	* @brief : sets employee's current_meesage_count field in database captain table employee
	* @paramters : integer
	* @retval : void
	**/
	
	public void setCmc(int cmc){
		this.cmc = cmc;
	}
	
	/**
	* @brief : gets employee's maximum message limit in single day from database captain table employee
	* @paramters : void
	* @retval : maximum limit of messages  
	**/
	public int getMml(){
		return mml;
	}
	
	/**
	* @brief : sets employee's maximum message limit in single day in database captain table employee
	* @paramters : integer
	* @retval : void 
	**/
	public void setMml(int mml){
		this.mml = mml;
	}
	
	/**
	* @brief : gets employee's last login TIMESTAMP from database captain table employee
	* @paramters : void
	* @retval : last login TIMESTAMP  
	**/
	
	public Timestamp getLl(){
		return ll;
	}
	
	/**
	* @brief : Sets employee's last login TIMESTAMP in database captain table employee
	* @paramters : TIMESTAMP
	* @retval : void  
	**/
	public void setLl(Timestamp ll){
		this.ll = ll;
	}
	
	/**
	* @brief : gets employee's login status from database captain table employee
	* @paramters : void
	* @retval : login status   
	**/
	
	public boolean getLs(){
		return ls;
	}
	
	/**
	* @brief : sets employee's login status in database captain table employee
	* @paramters : true/false
	* @retval : void 
	**/
	public void setLs(boolean ls){
		this.ls = ls;
	}
	
}
	
