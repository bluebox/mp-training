public class customer{
private String name;
private int creditlimit;
private String email;
public customer(String name, int creditlimit, String email)
{
this.name=name;
this.creditlimit=creditlimit;
this.email=email;
System.out.println("constructor is called");
}
public customer()
{
 this("gopi",1000,"gopi@com");
System.out.println("no args constructor");
}
public customer(String name, String email)
{
  this("vejas",9000,"vejas@com");
System.out.println("constructor with two arguments");  
}
public String getname()
{
 return this.name;
}
public int getcreditlimit()
{
 return this.creditlimit;
}
public String getemail()
{
 return this.email;
}
}