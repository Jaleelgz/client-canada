class LinkedListTest
{
    public static void main(String[] args)
    {
        DNode a = new DNode(3.5);
        DNode b = new DNode(4.5);
        INode c = new INode(5);
        INode d = new INode(6);

        LinkedList mylist = new LinkedList();

        mylist.append(a);
        mylist.append(b);
        mylist.append(c);
        mylist.append(d);

        System.out.println("The size of the list is: " + mylist.getSize());
        System.out.println(mylist);

        mylist.remove(0);
        System.out.println(mylist);
        mylist.remove(2);
        System.out.println(mylist);

        System.out.println("The size of the list is: " + mylist.getSize());

    }

}
