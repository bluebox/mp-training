package day7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<SetChallenge> annSetChallenges = TaskData.getData("Ann");
        Set<SetChallenge> bobSetChallenges = TaskData.getData("Bob");
        Set<SetChallenge> carolSetChallenges = TaskData.getData("Carol");
        Set<SetChallenge> allSetChallenges = TaskData.getData("alltasks");
        
//        annSetChallenges.forEach(System.out::println);
//        bobSetChallenges.forEach(System.out::println);
//        carolSetChallenges.forEach(System.out::println);
//        allSetChallenges.forEach(System.out::println);
        
        List<Set<SetChallenge>> listOfSets = new ArrayList<>();
        listOfSets.add(annSetChallenges);
        listOfSets.add(bobSetChallenges);
        listOfSets.add(carolSetChallenges);
        listOfSets.add(allSetChallenges);
        
        union(listOfSets);
        intersect(annSetChallenges,bobSetChallenges);
       
	}
	public static void union(List<Set<SetChallenge>> listOfSets ) {
		Set<SetChallenge> unionOfAllSets = new HashSet<>();
        for (Set<SetChallenge> currentSet : listOfSets) {
          unionOfAllSets.addAll(currentSet);
           }
        System.out.println(unionOfAllSets);
	}
	
	public static void intersect(Set<SetChallenge> l1,Set<SetChallenge> l2) {
		l1.retainAll(l2);
		System.out.println(l1);
	}
}
