import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class RandomAcessFilepractise {
    
    private static final Map<Long,Long> indexedmap=new LinkedHashMap<>();
    public static void main(String[] args) throws IOException {
        
                RandomAccessFile raf = new RandomAccessFile("practise.txt", "rw");
                raf.seek(0);
                int totalrecords=raf.readInt();
                for(int i=0;i<=totalrecords;i++){
                    indexedmap.put(raf.readLong(), raf.readLong());
                }
                Scanner sc =new Scanner(System.in);
                long id=sc.nextLong();
                
                raf.seek(indexedmap.get(id));
                String employee=raf.readUTF();
                System.out.println(employee);
                String salary="";
                int index=employee.indexOf("salary");
                while(!Character.isDigit(employee.charAt(index))){
                    index++;
                }
                while(Character.isDigit(employee.charAt(index))){
                    salary=salary+employee.charAt(index);
                }
                String newsalary=""+Integer.parseInt(salary)*2;
                employee.replace(salary, newsalary);
                raf.writeUTF(newsalary);
                




                


    }
}
