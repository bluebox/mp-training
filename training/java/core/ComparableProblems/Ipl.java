package training.java.core.ComparableProblems;

import java.util.*;

public class Ipl {
    public static void main(String[] args) {
        List<Team> teams= new ArrayList<>();
        Team t1= new Team("rcb","virat");
        Team t2= new Team("mi","rohit");
        Team t3= new Team("srh","warner");
        Team t4= new Team("rcb","abd");
        Team t5= new Team("mi","hardik");
        teams.addAll(Arrays.asList(t1,t2,t3,t4,t5));
        Collections.sort(teams);
        System.out.println(teams);

    }
}
class Team implements Comparable<Team>{
   private String tname;
    private String pname;
    public Team(String tname, String pname) {
        this.tname = tname;
        this.pname = pname;
    }
    public String getTname() {
        return tname;
    }
    public String getPname() {
        return pname;
    }
    public int compareTo(Team other){
        int result=this.tname.compareTo(other.tname);
        if(result==0){
            return this.pname.compareTo(other.pname);
        }
        return result;
    }
    @Override
    public String toString() {
        return "\nTeam [tname=" + tname + ", pname=" + pname + "]";
    }
    
}