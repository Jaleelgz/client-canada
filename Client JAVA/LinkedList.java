class LinkedList
{
    Node head;
    public LinkedList()
    {
        head = null;
    }
    public LinkedList(Node n)
    {
        head = n;
    }

    public void append(Node n)
    {

    }

    public void swap(int indexA, int indexB)
    {

    }

    @Override
    public String toString()
    {
        String output = "";
        Node curr = head;
        while (curr != null)
        {
            output += curr;
            curr = curr.getNext();
        }
        output += "EOL";
        return output;
    }

}