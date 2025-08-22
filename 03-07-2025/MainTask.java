package com.prana;

import java.util.*;

public class MainTask{

    public static void main(String[] args) {
        Set<Task> master = TaskData.getMasterTasks();
        Set<Task> ann = TaskData.getTasks("Ann");
        Set<Task> bob = TaskData.getTasks("Bob");
        Set<Task> carol = TaskData.getTasks("Carol");
        
        Set<Task> fullTaskList = getUnion(List.of(master, ann, bob, carol));
        System.out.println(" Full Task List:");
        fullTaskList.forEach(System.out::println);

        Set<Task> assigned = getUnion(List.of(ann, bob, carol));
        System.out.println("\n Tasks Assigned to Team:");
        assigned.forEach(System.out::println);

        Set<Task> unassigned = getDifference(master, assigned);
        System.out.println("\n Tasks Still Unassigned:");
        unassigned.forEach(System.out::println);

        Set<Task> common = getIntersect(ann, carol);  
        System.out.println("\n Tasks Assigned to Multiple Members:");
        common.forEach(System.out::println);
    }

    public static Set<Task> getUnion(List<Set<Task>> sets) {
        Set<Task> result = new HashSet<>();
        for (Set<Task> set : sets) {
            result.addAll(set);
        }
        return result;
    }

    public static Set<Task> getIntersect(Set<Task> s1, Set<Task> s2) {
        Set<Task> result = new HashSet<>(s1);
        result.retainAll(s2);
        return result;
    }

    public static Set<Task> getDifference(Set<Task> s1, Set<Task> s2) {
        Set<Task> result = new HashSet<>(s1);
        result.removeAll(s2);
        return result;
    }
}
