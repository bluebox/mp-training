package day7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Gameplay {
	public static void main(String[] args) {
		List<Game> levels=Stream.generate(Game::generateIslands).limit(3).distinct().collect(Collectors.toList());
		//levels.forEach(System.out::println);
		Random r= new Random();
		
		Game level1=levels.get(0);
		Game level2=levels.get(1);
		Game level3=levels.get(2);
		
		int score =0;
		Scanner sc=new Scanner(System.in);
		
		Map<String,Integer> value=new HashMap<>();
		value.put("wild wolf", 200);
		value.put("dragon", 300);
		value.put("fire breather", 100);
		value.put("hound", 250);
		value.put("gold", 150);
		value.put("silver", 100);
		value.put("diamonds", 200);
		value.put("sword", 50);
		value.put("magic wand",75);
		int c=0;
		System.out.println("Welcome to Arena");
		while(true) {
			System.out.println("you are at "+level1.getIsland()+" island");
			System.out.println("""
					Select input :
					1 get valuables
					2 get score
					3 attack bandit
					4 exit
					""");
			int s=sc.nextInt();
			if(s==1) {
				if(value.get(level1.getResources())>0) {
				System.out.println("you got lucky with : "+level1.getResources());
				score+=value.get(level1.getResources());
				value.put(level1.getResources(), 0);}
				else {
					System.out.println("Resources are obtained");
				}
			}
			if(s==2) {
				System.out.println("Player Score : "+score);
			}
			if(s==3) {
				if(value.get(level1.getBandits())<=0) {
					System.out.println("enemy slayed ");
					score+=300;
					value.put("wild wolf", 200);
					value.put("dragon", 300);
					value.put("fire breather", 100);
					value.put("hound", 250);
					value.put("gold", 150);
					value.put("silver", 100);
					value.put("diamonds", 200);
					value.put("sword", 50);
					value.put("magic wand",75);
					level1.setHealth(500);
					
					System.out.println("score : "+score);
					c++;
					if(c==1) {
						level1=level2;
					}
					if(c==2) {
						level1=level3;
					}
					
					
				}
				else if(level1.getHealth()<=0){
					System.out.println("you died \n Game over \n final score : "+score);
					value.put("wild wolf", 200);
					value.put("dragon", 300);
					value.put("fire breather", 100);
					value.put("hound", 250);
					value.put("gold", 150);
					value.put("silver", 100);
					value.put("diamonds", 200);
					value.put("sword", 50);
					value.put("magic wand",75);
					level1.setHealth(500);
					break;
				}
				System.out.println(level1.getBandits()+" has appeared");
					int num=r.nextInt(1,5);
					if(num==1)
						System.out.println("both attack are missed");
					else if(num==2) {
						System.out.println("you attacked and but opponent missed ");
						value.put(level1.getBandits(),value.get(level1.getBandits())-value.get(level1.getWeapon()));
					}
					else if(num==3) {
						System.out.println("your attack is missed but opponent hit you ");
						level1.setHealth(level1.getHealth() - value.get(level1.getBandits()));
					}
					else {
						System.out.println("both attack and got hit ");
						level1.setHealth(level1.getHealth() - value.get(level1.getBandits()));
						value.put(level1.getBandits(),value.get(level1.getBandits())-value.get(level1.getWeapon()));
											}	
					
			}
			if(s==4) {
				value.put("wild wolf", 200);
				value.put("dragon", 300);
				value.put("fire breather", 100);
				value.put("hound", 250);
				value.put("gold", 150);
				value.put("silver", 100);
				value.put("diamonds", 200);
				value.put("sword", 50);
				value.put("magic wand",75);
				level1.setHealth(500);
				break;
			}
		}
	}
}
