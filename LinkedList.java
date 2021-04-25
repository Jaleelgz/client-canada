class LinkedList
{
    private Node head;

    public LinkedList()
    {
        head = null;
    }
    public LinkedList(Node n)
    {
        head = n;
    }

    public int getSize()
    {
		Node temp = head;
		int count = 0;
		while (temp != null)
		{
			count++;
			temp = temp.getNext();
		}
		return count;
	}

    public void append(Node n)
    {
		Node last = head;

		if (head == null)
		{
			head = n;
		}
		else
		{
			while (last.getNext() != null)
			{
				last = last.getNext();
			}
			last.setNext(n);
		}
    }

	public void remove(int index)
	{
		if (head == null)
		{
			System.out.println("Nah, that's not going to happen. =)");
		}
		else
		{
			Node temp = head;

			if (index == 0)
			{
				head = temp.getNext();
			}
			else
			{

				for (int i = 0; temp != null && i < index - 1; i++)
				{
					temp = temp.getNext();
				}

				if (temp == null || temp.getNext() == null)
				{
					System.out.println("Nah, that's not going to happen. =)");
				}
				else
				{
					temp.setNext((temp.getNext()).getNext());
				}
			}
		}
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