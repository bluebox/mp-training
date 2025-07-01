abstract class person {
    private String name;
    private int age;
    public void setname(){
        this.name=name;
    }
    public void setage(){
        this.age=age;
    }

 public String getName() {
        return name;
    }
    public int getage(){
        return age;
    }
}
class member extends person{
    private int memberid;
    private String membership;
    public String getmembership(){
        return membership;
    }
    public int getmemberid(){
        return memberid;
    }
    public void setmemberid(){
        this.memberid=memberid;
    }
    public void setmembership(){
        this.membership=membership;
    }
    

}
