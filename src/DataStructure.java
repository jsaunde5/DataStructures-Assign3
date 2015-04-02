/*
this data structure is comprised of an array of DataStructureRecord. This structure is large enough to hold 100
records. We have an index for the ID, for the LastName and for the FirstName. Each index creates Binary
Search Tree for each field. This class also contains methods for organizing data structure.
*/

public class DataStructure 
{
	private DataStructureRecord [] data;  //big array of all students
	private int nextRec;
	private int nElems;
	private BinarySearchTree fNameIndex;
	private BinarySearchTree lNameIndex;
	private BinarySearchTree IDindex;
	
//DataStructure constructor with size 100
	public DataStructure()
	{
		data = new DataStructureRecord [100];
		nextRec = 0;  
		nElems = 0;
		fNameIndex = new BinarySearchTree();  //size 100, field type
		lNameIndex = new BinarySearchTree();
		IDindex = new BinarySearchTree();
	}
//get BinarySearchTree of each field
	public BinarySearchTree getFnameIndex()
	{
		return fNameIndex;
	}	
	public BinarySearchTree getLnameIndex()
	{
		return lNameIndex;
	}
	public BinarySearchTree getIDindex()
	{
		return IDindex;
	}
	
//get nElems of big array
	public int getNelems()
	{
		return nElems;
	}
//get record within big array at specific index
	public DataStructureRecord getRecord(int index)
	{
		return this.data[index];
	}
	
//calling findID(), Return false if ID is not found.
	public boolean search(String tempID) 
	{
		return ((findID(tempID)) != -1); 
	}

 //find a key (used for ID search)	
	public int findID (String key)
	{
		IndexRecord temp = IDindex.getTop();
		int compVal = 0;
		while (temp != null)
		{
			compVal = temp.getKey().compareToIgnoreCase(key);
			if (compVal == 0)  //found it
				return temp.getRecordNumber();
			if (compVal > 0)  //go left
				temp = temp.getLeft();
			else
				temp = temp.getRight();
		}
		return -1;  //if null, not found
	}

//insert into big array
//check if deleteStack is empty. Insert in popped index from stack if available
	public void insert(String name1, String name2, String tempID, Stack deleteStack) 
	{
		if (deleteStack.isEmpty())
		{
			nextRec = nElems;	
			nElems++; //only add nElems if put at end of Big Array (for lastRecord)
		}
		else
		{
			nextRec = deleteStack.pop();  //put new record in deleted index
		}
	//create new dataStructureRecord	
		DataStructureRecord newRecord = new DataStructureRecord( name1,  name2,  tempID);
		fNameIndex.insert(name1, nextRec);  //(String key, reference to big array)
		lNameIndex.insert(name2, nextRec);
		IDindex.insert(tempID, nextRec);
		data[nextRec] = newRecord;  //big Array
	}

//different listings: Ascending/Descending order by field type fName, lName, ID
//using inOrder/reverseOrder methods in BinarySearchTree to print each record
	public void listIt(int field, int order) 
	{
		switch (order)
		{
	//ascending
		case 1:
		// 1 -> First Name
			if(field == 1)
				fNameIndex.inOrder(fNameIndex.getTop(), this); //pass dataStructure instance
		// 2 -> Last Name	
			else if(field == 2)
				lNameIndex.inOrder(lNameIndex.getTop(), this);
		// 3 -> ID	
			else
				IDindex.inOrder(IDindex.getTop(), this);
			break;
	//descending	
		case 2:
		// 1 -> First Name
			if(field == 1)
				fNameIndex.reverseOrder(fNameIndex.getTop(), this); 
		// 2 -> Last Name	
			else if(field == 2)
				lNameIndex.reverseOrder(lNameIndex.getTop(), this);
		// 3 -> ID	
			else
				IDindex.reverseOrder(IDindex.getTop(), this);
			break;
		}  //end switch

	}

//takes in index from Big Array and uses deleteRec to delete from each tree from recordNumber index
	public void delete(int index) 
	{                    
		fNameIndex.deleteRec(getRecord(index).getFirstName() , index);
		lNameIndex.deleteRec(getRecord(index).getLastName(), index);
		IDindex.deleteRec(getRecord(index).getID(), index);	
	}


}//end DataStructure class
