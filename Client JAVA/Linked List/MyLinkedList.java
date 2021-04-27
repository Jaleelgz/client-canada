package CPSC101;
import java.io.*;
   
// Java program to implement
// a Singly Linked List
public class MyLinkedList {
   
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
