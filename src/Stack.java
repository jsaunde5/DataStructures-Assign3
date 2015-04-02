//this class contains methods for my stack used for deleted records, so we can
//re-use memory already allocated for our array for new records.


public class Stack 
{
	private int nextElem;
	private int maxSize;
	private int[] data;	

//Constructor
	public Stack()
	{
		this.nextElem = -1;
		this.maxSize = 20;
		this.data = new int [20];		
	}

//returns true if stack is empty
	public boolean isEmpty()
	{
		return (nextElem == -1);
	}


//returns true if stack is full
	public boolean isFull()
	{
		return (nextElem == (maxSize-1));  
	}
	
//push method (Must check if full within main method)
//push when deleting a database entry from big array
	public void push (int val)  //enter the index from the record in the big array
	{
		nextElem++;
		data[nextElem] = val;		
	}
	
//pop method (Must check if empty within main method)
//pop when adding new entry into big array
//the data from the stack will return a free index in the big array
	public int pop()
	{
		int temp = data[nextElem];
		nextElem--;
		return(temp);
	}



} //end class Stack
