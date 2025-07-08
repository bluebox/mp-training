import java.util.*;
import java.util.ArrayList;
public class QueryList<T extends Queryitem>  {
    List<T> list=new ArrayList<T>();
    public boolean matches(String field){
        for(T li:list){
            if(li.matches(field)){
                return true;
            }
        }
        return false;
    }
    public void printlist(){
        for(T li:this.list)
        System.out.println(li);
    }
    public void  addstudent(T t){
        list.add(t);


    }
    public void removestudent(T t){
        if(list.contains(t)){
            list.remove(t);
            return;
        }
        System.out.println("no student found");
    }
    
}
