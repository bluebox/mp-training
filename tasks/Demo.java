import java.util.ArrayList;
import java.util.HashMap;
public class Demo{
    public static void main(String[] args){
        // Scanner sc=new Scanner(System.in);
        // System.out.println("enter the string:");
       //String str =sc.nextLine();
        
         ArrayList<String> str = new ArrayList<>();
         str.add("(");
         str.add("<h1>");
         str.add("</h1>");
         str.add(")");

        System.out.println(isvalidornot(str));
    }

        public static boolean isvalidornot(ArrayList<String> str){

        ArrayList<String> l1=new ArrayList<>();
        
        HashMap<String,String> arr= new HashMap<>();
        
        
        arr.put(")", "(");
        arr.put("]", "[");
        arr.put("}", "{");
        arr.put("</h1>", "<h1>");
        for (int i=0;i<str.size();i++){
            String k= str.get(i);
            String k1=str.get(0);
            if (arr.containsKey(k1)){
                return false;

            }
            else{
                if ((k.equals("(")|| k.equals("[")||k.equals("{")||k.equals("<h1>"))){
                    l1.add(k);

                }
                else{
                    if ((k.equals(")")||k.equals("]")|| k.equals("}")|| k.equals("</h1>"))){
                        if (l1.isEmpty()){
                            return false;

                        }
                        else{
                            String last_one=l1.get(l1.size()-1);
                            if (arr.get(k).equals(last_one)){
                                l1.remove(l1.get(l1.size()-1));
                            }
                            else{
                                return false;
                            }
                        }
                    }
                }
            }
        }
                if (l1.isEmpty()){
                    return true;
                }
                else{
                    return false;
                }
            
            

            
                
                    
                
            }

            } 
        


        





    
