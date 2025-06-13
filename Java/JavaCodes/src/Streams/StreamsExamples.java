package Streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsExamples {
    public static void main(String []args) {
    	List<String>ans=new ArrayList<>(Arrays.asList("abhi","anand","naresh","abhi"));
        ans.stream()
        		.map(i->i.toUpperCase())
        		.distinct()
                .forEach(i->System.out.println(i));
        List<String>val=ans.stream()
        		.map(i->i.toUpperCase())
        		.collect(Collectors.toList());
        for(String ele:val)System.out.println(ele);
        Long a=ans.stream()
        		.map(i->i.toUpperCase())
        		.collect(Collectors.counting());
        System.out.println(a);
        Map<String,Integer>b=ans.stream()
        		.map(i->i.toUpperCase())
        		.distinct()
        		.collect(Collectors.toMap(s->s,s->s.length()));
        b.forEach((k,v)->System.out.println(k+" "+v));
    }
}
