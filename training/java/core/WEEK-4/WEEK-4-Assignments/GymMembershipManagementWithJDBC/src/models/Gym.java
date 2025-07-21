package models;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Gym {
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<MembershipPlan> plans = new ArrayList<>();

    public void addNewMember(Member member) {
        members.add(member);
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void addPlan(MembershipPlan plan) {
        plans.add(plan);
    }

    public ArrayList<MembershipPlan> getPlans() {
        return plans;
    }

    public Member getMemberById(int id) {
        for (Member m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }
    
    public Member getMemberByPhone(String phone) {
        for (Member m : members) {
            if (m.getPhone().equals(phone)) return m;
        }
        return null;
    }
    
    public void assignMembershipPlan(int id, MembershipPlan mp) {
    	Member existingMember=this.getMemberById(id);
    	existingMember.setMembershipPlan(mp);
    }
    
    public MembershipPlan getPlanById(int planId) {
    	MembershipPlan plan=getPlans().stream().filter(p -> p.getId()==planId).collect(Collectors.toList()).get(0);
    	return plan;
    }
    
    public void generateReport() {
    	Path report=Path.of("report.txt");
    	boolean flag=Files.exists(report);
    	if(flag) {
    		try {
				Files.delete(report);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	try(BufferedWriter writer=Files.newBufferedWriter(report, StandardOpenOption.CREATE, StandardOpenOption.APPEND);) {
			writer.write("												Gym Members Report");
			writer.newLine();
			writer.newLine();
			int count=(int) this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==1).count();
			writer.write("============Basic Plan Members (Total Count -> %d)============".formatted(count));
			writer.newLine();
			writer.newLine();
			if(count==0) {
				writer.write("No Basic Plan Members Available");
				writer.newLine();
				writer.newLine();
			}
			this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==1).forEach(s -> {
				try {
					writer.write(s.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			count=(int) this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==2).count();
			writer.write("============Premium Plan Members (Total Count -> %d)============".formatted(count));
			writer.newLine();
			writer.newLine();
			if(this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==2).count()==0) {
				writer.write("No Premium Plan Members Available");
				writer.newLine();
				writer.newLine();
			}
			this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==2).forEach(s -> {
				try {
					writer.write(s.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			count=(int) this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==3).count();
			writer.write("============Gold Plan Members (Total Count -> %d)============".formatted(count));
			writer.newLine();
			writer.newLine();
			if(this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==3).count()==0) {
				writer.write("No Gold Plan Members Available");
				writer.newLine();
				writer.newLine();
			}
			this.getMembers().stream().filter(m -> m.getMembershipPlan().getId()==3).forEach(s -> {
				try {
					writer.write(s.toString());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					writer.newLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void editUserdetails() {
    	System.out.println("Update Choices...");
    	System.out.println("1. Update User Name");
    	System.out.println("2. Update User Age");
    	try(Scanner sc=new Scanner(System.in);) {
    		System.out.print("Choice : ");
    		int choice=Integer.parseInt(sc.nextLine());
    		if(choice<1 || choice>2) {
    			return;
    		}
    		switch(choice) {
    			case 1:{
    				System.out.println("Enter New Name : ");
    				String updatedName=sc.nextLine();
    			}
    		}
    		
    	}catch(NumberFormatException e) {
    		e.printStackTrace();
    	}
    }
}
