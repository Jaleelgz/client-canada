public class MyLinkedList{
    //Represent the head and tail of the singly linked list    
    public MyNode head = null;    
    public MyNode tail = null; 
    
    
    //Insertion Methods
    
    //append(MyNode n): Appends a new node at the end of the list. 
    public void append(MyNode n) {    
        MyNode newNode = n;
            
        //Checks if the list is empty    
        if(head == null) {    
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;    
        }    
        else {    
            //newNode will be added after tail such that tail's next will point to newNode    
            tail.next = newNode;    
            //newNode will become new tail of the list    
            tail = newNode;    
        }    
    }
    
    //prepend(MyNode n): Prepends a new node at the beginning of the list. 
    public void prepend(MyNode n){
		MyNode newNode = n;
		
		if(head == null) {    
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;    
        }  
        else{
			
		MyNode temp = null;
		temp=head;
		head=newNode;
		head.next=temp;
	}
	}
	
	
	//insert(MyNode n, int index): Insert a new node at a specific index. 
	public void insert(MyNode n,int pos)
	{
		MyNode newNode = n;
		if(head == null) { 
			if(pos==0){   
            //If list is empty, both head and tail will point to new node    
            head = newNode;    
            tail = newNode;  }
            else{return;}  
        }
        
        if (head != null && pos == 0) {
            newNode.next = this.head;
            this.head = newNode;
            return;
        }
        MyNode current = head;
        MyNode previous = null;
        int i=0;
       while(i<pos)
        {
			previous = current;
            current = current.next;
            if (current == null)
            {
				break;
			}
			i++;
		}

      previous.next=newNode;
      newNode.next=current;
  
}
				
		
    //Utility Methods
    
    //isEmpty(): Returns true if the list is currently empty, false otherwise. 
    public Boolean isEmpty()
    {
		if(head==null){
			return true;
		}
		else{
			return false;
		}
	}
	//getSize(): Returns the size of the linked list. 
	public int getSize()
	{
	   int i=0;
	   MyNode current = head;  
       while(current!=null)
        {
            current = current.next;
			i++;
		}
		return i;
	}
	//toString(): Returns a string representation of the linked list. 
	public String toString(){
		MyNode current = head;   
        String str1=""; 
        while(current != null) {    
            String str=("["+current.data +"]"+ "->");
            str1=str1+str;    
            current = current.next;    
        }  
        return str1; 
	}
	
	// Deletion Methods 
	
	
	//remove(int index): Removes a node from a specific index
	public void remove(int index){
		if(head==null)
		{
			System.out.println("Cannot remove node at ["+index+"]! List is empty! ");
			return;
		}
		if(head!=null && index==0){
			head=head.next;
		}
		if(index>getSize()){
			System.out.println("Cannot remove node at ["+index+"]!");
		} 
		if(head!=null && index!=0 && index<getSize()){
		MyNode current = head;
		MyNode previouse = null;       
		int i=0;
		while(i<index){
			previouse=current;
		    current=current.next;
		    i++;
		    }
		    previouse.next=current.next;
		
		}
	}
	
	//delete(MyNode n): Deletes a specific node from the list
	public void delete(MyNode n){
		if(head==null){
			System.out.println("Node [ "+n.data+" ] ->  does not exist!"); 
		}
		else{
			
		MyNode current = head;
		MyNode previouse = null;       
		int i=0,flag=0;
		while(current!=null){
			if(current.data==n.data)
			{
				flag=1;
				if(current==head){
					head=head.next;
					return;
				}
				else{
					previouse.next=current.next;
					return;
				}
				}
			previouse=current;
		    current=current.next;
		    i++;
		
	}
		if(flag==0){
			System.out.println("Node [ "+n.data+" ] ->  does not exist!"); 
		}
		
	}
}
	
			
	//reset(): Empties the list. 		
	public void reset(){
		head=null;
		
		}
	
	
	//Search Methods
	
	//search(double key): Searches list with a specific key. Returns a reference to the node if it is found, otherwise, returns null. 
	public String search(double key){
		String str=null;
		if(head==null){
			 str=null;}
		else{
		MyNode current = head;
		int flag=0;
		while(current!=null)
		{
			if(current.data==key)
			{
				flag=1;
				str=("["+current.data +"]"+ "->");
			}
			current=current.next;
		}
		if(flag==0){
		
		str=null;
	}
	}
	return str;
	}
	
	//getIndexOf(double key): Searches list for a specific key. Returns the index of the first appearance of the node that contains the key. If the key cannot be found, returns -1. 
	public int getIndexOf(double key){
		int ret=-1;
		if(head==null){
			 ret=-1;}
		else{
		MyNode current = head;
		int flag=0;
		int i=0;
		while(current!=null)
		{
			if(current.data==key)
			{
				flag=1;
				ret=i;
			}
			current=current.next;
			i++;
		}
		if(flag==0){
		
		ret=-1;
	}
	}
	return ret;
	}
	
	//getMyNodeAt(int index): Searches list with a specific index. Returns a reference to the node at the index. If the index cannot be reach, return null. 
	public String getMyNodeAt(int index){
		String str=null;
		if(head==null){
			 str=null;}
		else{
		MyNode current = head;
		int flag=0,i=0;
		while(current!=null)
		{
			if(i==index)
			{
				flag=1;
				str=("["+current.data +"]"+ "->");
			}
			current=current.next;
			i++;
		}
		if(flag==0){
		
		str=null;
	}
	}
	return str;
	}
	
	//Other Methods (Sorting)
	//sort(): Sort the list in ascending order. 
	public void sort()
	{
		MyNode current=head,index=null;
		double temp;
		if(head==null)
		{
			return;
		}
		else{
			while(current!=null){
				index=current.next;
				while(index!=null){
					if(current.data > index.data){
						temp=current.data;
						current.data=index.data;
						index.data=temp;
					}
					index=index.next;
				}
				current=current.next;
			}
		}
	}
	

}

