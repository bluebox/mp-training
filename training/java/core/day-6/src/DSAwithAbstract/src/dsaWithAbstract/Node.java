package dsaWithAbstract;

class Node extends Listitem {

    public Node(Object value)
    {
        super(value);
    }

    Listitem next()
    {
        return (Listitem) this.rightLink;
    }

    void setNext(Listitem item) 
    {
        this.rightLink = item;
    }
    Listitem previous() 
    {
        return (Listitem) this.leftLink;
    }

    void setPrevious(Listitem item)
    {
        this.leftLink = item;
    }

    int compareTo(Listitem item) {
        if (item != null) 
        {
            
                return ((Comparable) this.value).compareTo(item.getValue());
             
           
        } 
        else 
        {
            return 1;
        }
    }
}
