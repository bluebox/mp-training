package serviceImplementation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import daoImplementation.PersonDAOImplementation;
import service.GymService;

public class GymServiceImplementation implements GymService {
    private List<MemberServiceImplementation> members = new ArrayList<>();
    private List<MembershipPlanServiceImplementation> plans = new ArrayList<>();

    public void addNewMember(MemberServiceImplementation member) {
        members.add(member);
    }

    public List<MemberServiceImplementation> getMembers() {
        return members;
    }
    
    public void setMembers(List<MemberServiceImplementation> members) {
    	this.members=members;
    }

    public void addPlan(MembershipPlanServiceImplementation plan) {
        plans.add(plan);
    }

    public List<MembershipPlanServiceImplementation> getPlans() {
        return plans;
    }

    public MemberServiceImplementation getMemberById(int id) {
        for (MemberServiceImplementation m : members) {
            if (m.getMemberId() == id) return m;
        }
        return null;
    }
    
    public MemberServiceImplementation getMemberByPhone(String phone) {
        for (MemberServiceImplementation m : members) {
            if (m.getPhone().equals(phone)) return m;
        }
        return null;
    }
    
    public void assignMembershipPlan(int id, MembershipPlanServiceImplementation mp) {
    	MemberServiceImplementation existingMember=this.getMemberById(id);
    	existingMember.setMembershipPlan(mp);
    }
    
    public MembershipPlanServiceImplementation getPlanById(int planId) {
    	MembershipPlanServiceImplementation plan=getPlans().stream().filter(p -> p.getId()==planId).collect(Collectors.toList()).get(0);
    	return plan;
    }
    
//    Function to generate report
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
					e.printStackTrace();
				}
				try {
					writer.newLine();
				} catch (IOException e) {
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
					e.printStackTrace();
				}
				try {
					writer.newLine();
				} catch (IOException e) {
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
					e.printStackTrace();
				}
				try {
					writer.newLine();
			} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
//    Function to edit user details
    public void editUserDetails(MemberServiceImplementation existing, Scanner sc) {
    	System.out.println("Update Choices...");
    	System.out.println("1. Update User Name");
    	System.out.println("2. Update User Age");
    	try {
    		System.out.print("Choice : ");
    		int choice=Integer.parseInt(sc.nextLine());
    		if(choice<1 || choice>2) {
    			System.out.println("Invalid choice, choice must be 1 or 2...");
    			return;
    		}
    		switch(choice) {
    			case 1:{
    				System.out.println("Enter New Name : ");
    				String updatedName=sc.nextLine();
    				if(updatedName.strip().length()==0) {
    					System.out.println("Updaed name cannot be empty...");
    					return;
    				}
    				else if(existing.getName()==updatedName) {
    					System.out.println("Updated name cannot be equal to existing name...");
    					return;
    				}
    				existing.setName(updatedName);
    				new PersonDAOImplementation().updateName(existing.getPhone(), updatedName);
    				System.out.println("Name updated succesfully...");
    				break;
    			}
    			case 2:{
    				System.out.println("Enter New Age : ");
    				String input=sc.nextLine();
    				try {
    					int updatedAge=Integer.parseInt(input);
    					if(existing.getAge()==updatedAge) {
        					System.out.println("Updated Age cannot be equal to existing age...");
        					return;
        				}
        				existing.setAge(updatedAge);
        				new PersonDAOImplementation().updateAge(existing.getPhone(), updatedAge);
        				System.out.println("Age updated succesfully...");
    				}catch(NumberFormatException e) {
    					System.out.println("Age must an integer...");
    				}
    				break;
    			}
    		}
    		
    	}catch(NumberFormatException e) {
    		System.out.println("Choice must be an integer between 1 and 2...");
    	}
    }
    
//    Function to delete user by id
    public void deleteMemberByPhone(String phone) {
        MemberServiceImplementation existing=this.getMemberByPhone(phone);
        if(existing==null) {
        	System.out.println("Given phone number does not exist, Please add the member first...");
        	return;
        }
        this.setMembers(this.getMembers().stream().filter(m -> !m.getPhone().equals(phone)).collect(Collectors.toList()));
        new PersonDAOImplementation().deleteUser(phone);
        System.out.println("User deleted succesfully...");
    }
}
