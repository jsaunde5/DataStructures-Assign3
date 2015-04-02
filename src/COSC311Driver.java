/* 
 * 
	Jordan Saunders
	COSC 311
	Program #3
	Version #1 (non-threaded)

This program reads a given data set of student Last name, First name and ID number. After reading in the three separate Strings, 
the program creates a DataStructureRecord for each student, putting the record into a DataStructure array called myStructure.
Each field for each student creates a new IndexRecord (a node), referring back to myStructure index number and creates a new
entry in a Binary Search Tree.
*/

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class COSC311Driver
{
	static DataStructure myStructure = new DataStructure();  //big, main array
	static Scanner keyboard=new Scanner(System.in);
    static Stack deleteStack = new Stack();  //stack used to keep index of deleted records
    
    public static void main(String[] args)
    {
    //import Dataset1 (given)
    	Scanner inStream = null;
    	try 
    	{										//hard coded text file
			inStream = new Scanner(new FileInputStream("DataSet1"));
			System.out.println("Importing Data...");
			while (inStream.hasNext())  //import data until DataSet1 is null
			{
				String lastName = inStream.next();
				String firstName = inStream.next();
				String ID = inStream.next();  //may be duplicate
				boolean found1 = false;
				do
				{		        
		        //is imported ID unique ?
		            found1=myStructure.search(ID);
		            if (found1)
		            {
		                System.out.println(ID + " already in use");
		                System.out.println("Please re-enter a unique ID");
		                ID = keyboard.next();
		            }
		        }
		        while (found1);
			//add to our data structure 
				myStructure.insert(firstName, lastName, ID, deleteStack);
			}

		} 
    	catch (FileNotFoundException e) //if file not found
		{
			e.printStackTrace();
		}
    	
        int response;  //response from user
        
        do
        {
            System.out.println(" 1 Add a new student");
            System.out.println(" 2 Delete a student");
            System.out.println(" 3 Find a student by ID");
            System.out.println(" 4 List students by ID increasing");
            System.out.println(" 5 List students by first name increasing");
            System.out.println(" 6 List students by last name increasing");
            System.out.println(" 7 List students by ID decreasing");
            System.out.println(" 8 List students by first name decreasing");
            System.out.println(" 9 List students by last name decreasing");
            System.out.println(" ");
            System.out.println(" 0 End");
            
            response=keyboard.nextInt();
            
            switch (response)
            {
                case 1: addIt();
                        break;
                case 2: deleteIt();
                        break;
                case 3: findIt();
                        break;
                case 4: listItIncreasingID();
                        break;
                case 5: listItIncreasingfName();	
                        break;
                case 6: listItIncreasinglName();
                        break;
                case 7: listItDecreasingID();
                        break;
                case 8: listItDecreasingfName();
                        break;
                case 9: listItDecreasinglName();
                        break;
                default:          
            }
            
        } while (response!=0);

        
        
    }//end of main method

    
// Case 1: Add a new student. We need a unique ID number
    public static void addIt()
    {
        String name1,name2,tempID;  //firstName, lastName, ID
        boolean found = false;
        System.out.println("Enter a unique ID number to add");
        
        do
        {
            tempID=keyboard.next();
        
        //is it unique ?
            found=myStructure.search(tempID);
            if (found)
            {
                System.out.println("ID already in use");
                System.out.println("Please re-enter a unique ID");
            }
        }
       while (found);
       
   // We found a unique ID. Now ask for first and last name       
       System.out.println("Enter first name");
       name1=keyboard.next();
       System.out.println("Enter last name");
       name2=keyboard.next();
       
    // add to our data structure
       myStructure.insert(name1,name2,tempID, deleteStack); 
       System.out.println();
    }

// Case 2: delete a student. A student ID must be prompted for. If the ID number does not exist in the database,
//    print out a message indicating a such, otherwise delete the entire record

    public static void deleteIt()
    {
    	System.out.println("Enter ID to delete");
    	String tempID;
		tempID = keyboard.next();
		int index = myStructure.findID(tempID);  //get index of tempID. Ret -1 if not found
	//if ID is found, then delete
		if (index != -1) 
		{
			myStructure.delete(index);
			System.out.println("Deleting " + myStructure.getRecord(index).toString() + "...");//PRINT THE ENTIRE RECORD
		//if deleteStack is not full, push index of deleted onto stack
			if (!deleteStack.isFull())
			deleteStack.push(index);  
			//no need to decrement nElems because element is still in big array
			//deleted index of big array will be over-written when new insert
		}
		else
			System.out.println("There was no such record found");
		System.out.println();
		
    }


//Case 3: find a student. A student ID must be prompted for. If the ID number is not found, print out a
//    message indicating as such. Otherwise print out the entire record
	public static void findIt() 
	{
		System.out.println("Enter ID");
		String tempID;
		keyboard.nextLine();
		tempID = keyboard.nextLine();
		int index = myStructure.findID(tempID);
	//if ID is found, then print
		if (index != -1) 
		{
			System.out.println(myStructure.getRecord(index).toString());//PRINT THE ENTIRE RECORD
		}
		else
			System.out.println("There was no such record found");
		System.out.println();  //go back to main menu option
	}
        
	// Cases 4 -> 9
    // We'll pass 2 parameters: the first indicates which field, the second indicates which order
    // fieldNum=1 first name
    // fieldNum=2 last name
    // fieldNum=3 ID
    // orderNum=1 increasing
    // orderNum=2 decreasing
    
//Case 4
    public static void listItIncreasingID()
    {
        myStructure.listIt(3,1);
        System.out.println();
    }
    
//Case 5                        
    public static void listItIncreasingfName()
    {
        myStructure.listIt(1,1);
        System.out.println();
    }
         
//Case 6
    public static void listItIncreasinglName()
    {
        myStructure.listIt(2,1);
        System.out.println();
    }
    
//Case 7
    public static void listItDecreasingID()
    {
    	myStructure.listIt(3,2);
    	System.out.println();
    }
    
//Case 8
    public static void listItDecreasingfName()
    {
    	myStructure.listIt(1,2);
    	System.out.println();
    }
    
//Case 9    
    public static void listItDecreasinglName()
    {
    	myStructure.listIt(2,2);
    	System.out.println();
    }
           
        
}   //end of Driver class