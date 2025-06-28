import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class Demo2{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter the string:");
       String str =sc.nextLine();
        
        //  ArrayList<String> str = new ArrayList<>();
        //  str.add("(");
        //  str.add("<h1>");
        //  str.add("</h1>");
        //  str.add(")");

        System.out.println(isvalidornot(str));
    }


        public static boolean isvalidornot(String str){
            if (str.length()==0){
                return true;
            }

        Stack<String> l1=new Stack<>();
        
        HashMap<String,String> arr= new HashMap<>();
        
        
        arr.put(")", "(");
        arr.put("]", "[");
        arr.put("}", "{");
        int i=0;
       
        while (i<str.length()){

        
            Character k= str.charAt(i);
            Character k1=str.charAt(0);
            if (arr.containsKey(k1.toString())){
                return false;

            }
            
             else if (((k.toString()).equals("(")|| (k.toString()).equals("[")||(k.toString()).equals("{"))){
                l1.add(k.toString());
                i++;
             }
                
                
                
                
            else if (((k.toString()).equals(")")|| (k.toString()).equals("]")||(k.toString()).equals("}"))){
 
                
                if ((l1.isEmpty()) || (l1.get(l1.size()-1)!= arr.get(k.toString()))){ 

                return false;

                }
                l1.pop();
                i++;
            }
                else if(((k.toString()).equals("<"))){

                    String new_one="";
                    i++;
                    while (i<str.length() && str.charAt(i)!='>'){

                        new_one+=str.charAt(i);
                        i++;
                    }
                    if (i==str.length()){
                        return false;
                    }
                    if ((new_one.charAt(0))==('/')){
                        String another_one=new_one.substring(i);
                        if (l1.isEmpty() || !l1.peek().equals(another_one)){
                            return false;
                        }
                        l1.pop();

                    }
                    else{
                        l1.add(new_one);
                        i++;
                    }
                }
                    else{
                        i++;
                    }
                    
                }
                return l1.isEmpty();
            


                   
                                
                            }
                        }
                    
                
            
        
                  
            

            
                
                   
                
            

            
        


        





    
