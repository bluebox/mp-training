package Generics;

import java.util.*;

class Cricket{
	public int noOfPlayers;
	public int overs;
	public Cricket(int no,int overs) {
		this.noOfPlayers=no;
		this.overs=overs;
	}
}
class FootBall{
	public int noOfPlayers;
	public int time;
	public FootBall(int no,int time) {
		this.noOfPlayers=no;
		this.time=time;
	}
}
class Ground<T extends Cricket>{
	List<T>ans;
	public Ground() {
		ans=new ArrayList<>();
	}
	public void addSport(T a) {
		ans.add(a);
	}
	public List<T> getSports(){
		return this.ans;
	}
}
public class GenericUsingExtendForParticularClass {

	public static void main(String[] args) {
	    Ground<Cricket> g=new Ground<>();
	    g.addSport(new Cricket(11,20));
	  //  g.addSport(new FootBall(11,90)); this gives error
	    g.addSport(new Cricket(11,10));
	    List<Cricket>ans=g.getSports();
	    for(Cricket ele:ans) {
	    	System.out.println(ele.getClass().getSimpleName());
	    }
	}

}
