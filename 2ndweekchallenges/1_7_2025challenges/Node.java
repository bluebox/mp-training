public class Node extends Listitem implements Comparable<Listitem> {
    public Node(Object value){
        super(value);
    }
    public Listitem next(){
        return this.rightLink;
    }
    public Listitem setNext(Listitem li){
        this.rightLink=li.rightLink;
        return this.rightLink;
        

    }
    public Listitem previous(){
        return this.leftLink;
    }
    public Listitem setPrevious(Listitem li){
        this.leftLink=li;
        return this.leftLink;

    }
    
    public int compareTo(Listitem li){
        if((int)this.value<(int)li.value)return -1;
        else if((int)this.value>(int)li.value)return 1;
        return 0;


        

    }

     
}