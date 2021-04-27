class LinkedListTest
{
    public static void main(String[] args)
    {
        LinkedList mylist = new LinkedList();
        for (int i = 0; i < 5; i++)
        {
            mylist.append(new Node(i));
        }

        System.out.println("The Original List is: ");
        System.out.println(mylist);
        System.out.println("===================================");

        int i, j;

        i = 1; j = 3;
        System.out.println("Swapping index " + i + " and " + j + ": ");
        mylist.swap(i, j);
        System.out.println("Result: " + mylist);

        i = 3; j = 1;
        System.out.println("Swapping index " + i + " and " + j + ": ");
        mylist.swap(i, j);
        System.out.println("Result: " + mylist);

        i = 0; j = 4;
        System.out.println("Swapping index " + i + " and " + j + ": ");
        mylist.swap(i, j);
        System.out.println("Result: " + mylist);

        i = 4; j = 0;
        System.out.println("Swapping index " + i + " and " + j + ": ");
        mylist.swap(i, j);
        System.out.println("Result: " + mylist);

        i = 1; j = 9;
        System.out.println("Swapping index " + i + " and " + j + ": ");
        mylist.swap(i, j);
        System.out.println("Result: " + mylist);

        i = -1; j = 5;
        System.out.println("Swapping index " + i + " and " + j + ": ");
        mylist.swap(i, j);
        System.out.println("Result: " + mylist);

        System.out.println("End of Processing...");
    }

}
