/*
This class has three private String elements (for each field), along with appropriate 
constructors, toString methods, etc. This class creates each record for the data structure.
*/


public class DataStructureRecord 
{
	private String firstName;
	private String lastName;
	private String ID;
	
	
//parameterized constructor
	public DataStructureRecord(String firstName, String lastName, String ID)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.ID = ID;
	}
		
//setter for firstName
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;		
	}
	
//getter for firstName
	public String getFirstName()
	{
		return firstName;
	}
	
//setter for lastName
	public void setLastName(String lastName)
	{
		this.lastName = lastName;		
	}
	
//getter for lastName
	public String getLastName()
	{
		return lastName;
	}
	
//setter for ID
	public void setID(String ID)
	{
		this.ID = ID;		
	}
	
//getter for ID
	public String getID()
	{
		return ID;
	}
	
//toString
	public String toString()
	{
		return ( ID + " " + lastName  + " " + firstName );				
	}
	

}//end DataStructureRecord class