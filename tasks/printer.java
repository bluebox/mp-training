import java.util.Scanner;
public class printer {
    private int tonerlevel;
    private int pagesprinted;
    private boolean duplex;
    public printer(int tonerlevel,boolean duplex){
        this.tonerlevel=tonerlevel>-1 && tonerlevel<101 ?tonerlevel :-1;
        this.duplex=duplex;
        this.pagesprinted=0;

    }
    public int addtoner(int toneramount){
        if(toneramount>0 && toneramount<=100){
            if(tonerlevel+toneramount >100){
                return -1;
            }
            else{
                tonerlevel+=toneramount;
                return tonerlevel;
            }
        }
        else{
            return -1;
        }

    }
    public int printpages(int pages){
        int pagestoprint;
        if (duplex){
            System.out.println("printing...");
            pagestoprint=(pages/2)+(pages%2);
        }
        else{
            pagestoprint=pages;
        }
        pagesprinted+=pagestoprint;
        return pagestoprint;
    }
    public int getpagesprinted(){
        return pagesprinted;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("enter toner level:");
        int t=sc.nextInt();
        System.out.println("enter duplex:");
        boolean d=sc.nextBoolean();
        
        printer p=new printer(t,d);
        System.out.println("enter toner amount:");
        int a=sc.nextInt();
        System.out.println(p.addtoner(a));
        System.out.println("enter no of pages:");

        int pa=sc.nextInt();
        System.out.println("intail page count:"+p.getpagesprinted());

        System.out.println("enter toner level:");

        int pagesprinted=p.printpages(pa);
        System.out.println("pages printed::"+pagesprinted);
        System.out.println("new total print count :"+p.getpagesprinted());





        
    }
    
}
