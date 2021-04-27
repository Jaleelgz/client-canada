package CPSC101;
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
        //mylist.delete(new MyNode(1.0));
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
        //mylist.prepend(new MyNode(1.0));
        System.out.println(mylist);
        */

        System.out.println("Insert Node 0.0 at index 0");
        mylist.insert(new MyNode(0.0), 0);
        System.out.println(mylist);

        System.out.println("Insert Node 2.0 at index 2");
        mylist.insert(new MyNode(2.0), 2);
        System.out.println(mylist);
        
        /*

        System.out.println("Insert Node 10.0 at index (size of the current list)");
        mylist.insert(new MyNode(10.), mylist.getSize());
        System.out.println(mylist);

        System.out.println("=================================");
        System.out.println("Test 3: Testing Deletion...");

        System.out.println("Delete Node 1.0");
        //mylist.delete(new MyNode(1.0));
        System.out.println(mylist);

        System.out.println("Delete Node 99.0");
        //mylist.delete(new MyNode(99.0));
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
