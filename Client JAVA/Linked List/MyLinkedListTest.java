import java.io.*;
class MyLinkedListTest extends MyLinkedList
{
    public static void main(String[] args)
    {
        System.out.println("=================================");
        System.out.println("Testing Program for MyLinkedList");
        System.out.println("=================================");
        System.out.println();
        
       

        MyLinkedList mylist = new MyLinkedList();
         /*

        System.out.println("=================================");
        System.out.println("Test 1: Testing Insertion on Empty List...");
        mylist.delete(new MyNode(1.0));
        mylist.remove(0);

        System.out.println("=================================");
        System.out.println("Generating Original List...");
        for (double i = 7; i > 2; i--)
        {
            mylist.append(new MyNode(i));
        }
        System.out.println("Done!");
        System.out.println("The Original List is: ");
        System.out.println(mylist);

        System.out.println("=================================");
        System.out.println("Test 2: Testing Insertion...");

        System.out.println("Insert Node 1.0");
        mylist.prepend(new MyNode(1.0));
        System.out.println(mylist);
        

        System.out.println("Insert Node 0.0 at index 0");
        mylist.insert(new MyNode(0.0), 0);
        System.out.println(mylist);

        System.out.println("Insert Node 2.0 at index 2");
        mylist.insert(new MyNode(2.0), 2);
        System.out.println(mylist);
        */
        
        

        System.out.println("Insert Node 10.0 at index (size of the current list)");
        mylist.insert(new MyNode(10.), mylist.getSize());
        System.out.println(mylist);

        System.out.println("=================================");
        System.out.println("Test 3: Testing Deletion...");
        
        /*
        System.out.println("Delete Node 1.0");
        mylist.delete(new MyNode(1.0));
        System.out.println(mylist);

        System.out.println("Delete Node 99.0");
        mylist.delete(new MyNode(99.0));
        System.out.println(mylist);

        System.out.println("Remove Node at index 0");
        mylist.remove(0);
        System.out.println(mylist);

        System.out.println("Remove Node at index 99");
        mylist.remove(99);
        System.out.println(mylist);

        System.out.println("=================================");
        System.out.println("Test 3: Testing Search...");


        System.out.println("Search Node with data 4.0");
        System.out.println(mylist.search(4.0));
        System.out.println("Search Node with data 99.0");
        System.out.println(mylist.search(99.0));
        System.out.println("Search Node at index 0");
        System.out.println(mylist.getMyNodeAt(0));
        System.out.println("Search Node at index (size of the current list - 1)");
        System.out.println(mylist.getMyNodeAt(mylist.getSize() - 1));
        System.out.println("Search Node at index 99");
        System.out.println(mylist.getMyNodeAt(99));

        System.out.println("Get the index of Node 2.0");
        System.out.println(mylist.getIndexOf(2.0));
        System.out.println("Get the index of Node 99.0");
        System.out.println(mylist.getIndexOf(99.0));

        System.out.println("=================================");
        System.out.println("Test 4: Testing Sort...");


        System.out.println("Sorting the list...");
        mylist.sort();
        System.out.println(mylist);

        System.out.println("\nEnd of Testing...");
        
        
        */
        
    }

}



   
// Java program to implement
// a Singly Linked List
class MyLinkedList {
   
    MyNode head; // head of list
   
    // Linked list Node.
    // This inner class is made static
    // so that main() can access it
    static class MyNode {
   
        double data;
        MyNode next;
   
        // Constructor
        MyNode(double d)
        {
            data = d;
            next = null;
        }
    }
   
    // Method to insert a new node
    public static MyLinkedList insert(MyLinkedList list, int data)
    {
        // Create a new node with given data
        MyNode new_node = new MyNode(data);
        new_node.next = null;
   
        // If the Linked List is empty,
        // then make the new node as head
        if (list.head == null) {
            list.head = new_node;
        }
        else {
            // Else traverse till the last node
            // and insert the new_node there
            MyNode last = list.head;
            while (last.next != null) {
                last = last.next;
            }
   
            // Insert the new_node at last node
            last.next = new_node;
        }
   
        // Return the list by head
        return list;
    }
   
    // Method to print the LinkedList.
    public static void printList(MyLinkedList list)
    {
        MyNode currNode = list.head;
    
        System.out.print("LinkedList: ");
    
        // Traverse through the LinkedList
        while (currNode != null) {
            // Print the data at current node
            System.out.print(currNode.data + " ");
    
            // Go to next node
            currNode = currNode.next;
        }
    }
    
    
    
}

