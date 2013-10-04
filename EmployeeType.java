/* this is Pojo class which is a map to "employee_type" table in database. with all getter and setter methods */

public class EmployeeType{
	int id;						// map to primary key of employee_type table in captain database
	int type;					// map to type field of employee_type table in captain database
	String designation;			// map to designation field of employee_type table in captain database
	
	public EmployeeType(){}
	
	/**
	@brief : use this constructor to add employee type with designation
	@parameter : type : int
				designation : String
	**/
	public EmployeeType(int type,String designation){
		this.type = type;
		this.designation = designation;
	}
	
	/*getter and setter properties for table name employee_type */
	/**
	@brief : gets row id
	@parameter : void
	@retval:  row id 
	**/
	
	public int getId(){
		return id;
	}
	
	/**
	@brief : sets row id
	@parameter : integer
	@retval:  void 
	**/
	
	public void setId(int id){
		this.id = id;
	}
	
	/**
	@brief : gets employee's type
	@parameter : void
	@retval:  employee's type
	**/
	public int getType(){
		return type;
	}
	
	/**
	@brief : sets employee's typle 
	@parameter : integer
	@retval:  void
	**/
	public void setType(int type){
		this.type = type;
	}
	
	/**
	@brief : gets employee's designation 
	@parameter : void
	@retval:  employee's designation
	**/
	public String getDesignation(){
		return designation;
	}
	
	/**
	@brief : sets employee's designation
	@parameter : String
	@retval:  void 
	**/
	public void setDesignation(String designation){
		this.designation = designation;
	}
}
	
	
