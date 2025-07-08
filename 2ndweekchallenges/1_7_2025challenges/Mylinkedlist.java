public class Mylinkedlist implements Nodelist,Comparable<Listitem> {
    private Listitem root;
    public Mylinkedlist(Listitem root){
        this.root=root;
    }
    public Listitem getRoot() {
        return root;
    }
    public boolean additem(Listitem li){
        Listitem root=getRoot();
        while(root!=null){
            if(root.compareTo(li)==1){
                break;
            }
            root=root.rightLink;
        }
        Listitem temp=root;
        root.previous().rightLink=li;
        li.rightLink=temp;
        return false;
    }
    public boolean removeitem(Listitem li){
        Listitem root=getRoot();
        while(root!=null){ 
            if(li.leftLink==root.leftLink && li.rightLink==root.rightLink){
                break;
            }
            root=root.rightLink;

        } 
        if(root!=null){
            Listitem temp=root.leftLink;
            temp.rightLink=root.rightLink;
            return true;
            
        }

return false;
        
    }
    public void traverse(Listitem root){
       if(root==null){
                System.out.println(" list is empty");
                return;

       }
        while(root!=null){
            
            System.out.println("value"+root.value);
            root=root.rightLink;
            
        }
    }
    @Override
    public int compareTo(Listitem o) {
        // TODO Auto-generated method stub
        if((int)this.root.value<(int)o.value)return -1;
        else if((int)this.root.value>(int)o.value)return 1;
        return 0;
    }
   
    
   
    
}   
