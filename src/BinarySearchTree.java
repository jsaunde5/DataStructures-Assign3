/*
This class creates the Binary Search Tree that holds IndexRecords (nodes) of all three fields separately.
This class is responsible for creating the tree, inserting, deleting and printing.
*/


public class BinarySearchTree 
{
	private IndexRecord top;
//constructor
	public BinarySearchTree()
	{
		top = null;
	}
// return the top of tree
	public IndexRecord getTop()
	{
		return top;
	}
	
//insert into each tree separately, referring to recordNumber of big array	
	public void insert (String newVal, int recordNumber)
	{
		IndexRecord prev = top;
		IndexRecord current = top;
		boolean wentLeft = false;
	//create new record
		IndexRecord newNode = new IndexRecord (newVal, recordNumber);
		int compVal = 0;
	//if empty	
		if (top == null)
			top = newNode;
	//if not empty
		else
		{
			while (current != null)  //find leaf to insert after
			{
				compVal = newVal.compareToIgnoreCase(current.getKey());
			//go left if less than current
				if (compVal < 0)
				{
					wentLeft = true;
					prev = current;
					current = current.getLeft();
				}
			//go right
				else
				{
					wentLeft = false;
					prev = current;
					current = current.getRight();
				}
			}  //end while
		//set link from previous
			if (wentLeft)
				prev.setLeft(newNode);
			else
				prev.setRight(newNode);
		}
	} //end insert
	
//find inOrder successor for deleting (replace with inOrder)
//straight from book
	public IndexRecord getSuccessor (IndexRecord delNode)
	{
		IndexRecord sucParent = delNode;
		IndexRecord successor = delNode;
		IndexRecord current = delNode.getRight();
		while (current != null)
		{
			sucParent = successor;
			successor = current;
			current = current.getLeft();
		}
		
		if (successor != delNode.getRight())
		{
			sucParent.setLeft(successor.getRight());
			successor.setRight(delNode.getRight());
		}
		return successor;
	}
	
//delete record from BST
//search for RecordNumber, after found, delete key (different for each tree)
	public boolean deleteRec(String delKey, int recordNumber)
	{
		IndexRecord current = top;
		IndexRecord parent = top;
		boolean isLeftChild = true;
		int compVal = 0;
	//search until we find recordNumber	
		while (current.getRecordNumber() != recordNumber)
		{
			parent = current;  //update parent to old current
			
		//going through tree using key to compare because the comparison
		//for the BST is using the key, but searching for recordNumber
			compVal = delKey.compareToIgnoreCase(current.getKey());
			if(compVal < 0)
			{
				isLeftChild = true;
				current = current.getLeft();
			}
			else
			{
				isLeftChild = false;
				current = current.getRight();
			}
			if (current == null)
				return false;  //did not find (should never happen)
		}  //end while
//we found recordNumber, now delete IndexRecord	(node)
		
	//no children
		if (current.getLeft() == null  &&  current.getRight() == null)
		{
			if (current == top)
				top = null;
			else if (isLeftChild)
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
	//no right child	
		else if (current.getRight() == null)
		{
			if (current == top)
				top = current.getLeft();
			else if (isLeftChild)
				parent.setLeft(current.getLeft());
			else
				parent.setRight(current.getLeft());
		}
	//no left child
		else if (current.getLeft() == null)
		{
			if (current == top)
				top = current.getRight();
			else if (isLeftChild)
				parent.setLeft(current.getRight());
			else
				parent.setRight(current.getRight());
		}
	//has two children, replace with inOrder successor
		else
		{                           //use method above
			IndexRecord successor = getSuccessor(current);
			if (current == top)
				top = successor;
			else if (isLeftChild)
				parent.setLeft(successor);
			else
				parent.setRight(successor);
		//connect successor to current's left child
			successor.setLeft(current.getLeft());
		}
		return true;
	}  //end delete
	
//the print line for each of the following two methods finds the record with specific
//record number and prints the entire record.
	
//inOrder traversal for printing
	public void inOrder (IndexRecord localRoot, DataStructure dataStruct)
	{
		if (localRoot != null)
		{
			inOrder(localRoot.getLeft(), dataStruct);
			System.out.println(dataStruct.getRecord(localRoot.getRecordNumber()).toString());
			inOrder(localRoot.getRight(), dataStruct);
		}
	}
	
//reverse order traversal for printing
	public void reverseOrder (IndexRecord localRoot, DataStructure dataStruct)
	{
		if (localRoot != null)
		{
			reverseOrder (localRoot.getRight(), dataStruct);
			System.out.println(dataStruct.getRecord(localRoot.getRecordNumber()).toString());
			reverseOrder (localRoot.getLeft(), dataStruct);
		}
	}
	
}
