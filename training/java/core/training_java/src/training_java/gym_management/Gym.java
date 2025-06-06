

import java.util.ArrayList;
import java.util.Comparator;

public class Gym {
	private String name;
	private ArrayList<Member> members;
	private ArrayList<Trainer> trainers;

	public Gym(String name) {
		this.name = name;
		members = new ArrayList<Member>();
		trainers = new ArrayList<Trainer>();
	}

	public String getGymName() {
		return name;
	}

	public void addMember(long memberId, String name, int age, String gender) {
		Member member = this.getMember(memberId);
		if (member == null) {
			member = new Member(memberId, name, age, gender.toUpperCase());
			members.add(member);
		} else {
			System.out.println("The Member details are already added");
		}
//		member.showDetails();
	}

	public void addTrainer(long trainerId, String type, String name, int age, String gender) {
		Trainer trainer = this.getTrainer(trainerId);
		if (trainer == null) {
			trainer = new Trainer(trainerId, type, name, age, gender.toUpperCase());
			trainers.add(trainer);
		} else {
			System.out.println("The Trainer details are already added");
		}
//		trainer.showDetails();
	}

	public void updateMemberName(long memberId, String name) {
		Member member = this.getMember(memberId);
		if (member == null) {
			System.out.println("Please register in the GYM First");
		} else {
			member.setName(name);
			System.out.println("Name Updated Successfully for Id " + member.getMemberId());
		}
	}

	public void updateMemberAge(long memberId, int age) {
		Member member = this.getMember(memberId);
		if (member == null) {
			System.out.println("Please register in the GYM First");
		} else {
			member.setAge(age);
			System.out.println("Age Updated Successfully for Id " + member.getMemberId());
		}
	}

	public void assignPlan(Member member, String plan) {
		if (!member.isMemberShip()) {
			member.setMemberShip(plan.toUpperCase());
			System.out.println("The MemberShip Plan assigned for " + member.getName() + " with Id "
					+ member.getMemberId() + " is" + member.getPlan());
		} else {
			System.out.println("A Plan is already assigned...! Want to Update Plan ? ");
			member.showDetails();
		}
	}

	public boolean assignTrainer(Member member, Trainer trainer) {
		if (!member.isTrainer()) {
			boolean flag=this.setTrainer(member,trainer);
			if(flag) {
			System.out.println("The trainer " + trainer.getName() + " is assigned to " + member.getName());
			return true;
		} 
		}else {
			System.out.println("A trainer is already assigned " + member.getTrainer());
			System.out.println("Want to update Trainer?");
		}
		return false;
	}

	public boolean setTrainer(Member member, Trainer trainer) {
		if (member.getPlan().equals(trainer.getTrainerType())) {
			member.setTrainer(trainer);
			return true;
		} else {
			System.out.println("Please Choose Trainers from your " + member.getPlan() + " Plan");
			return false;
		}
	}

	public void showMemberDetails(long memberId) {
		Member member = this.getMember(memberId);
		if (member == null) {
			System.out.println("No Member Found with that Id");
		} else {
			member.showDetails();
		}
	}

	public void getAllMembersDetails() {
		members.sort(Comparator.comparing(Member::getName).thenComparing(Member::getAge));
		System.out.println("-".repeat(100));
		System.out.println("Name" + " ".repeat(20) + "|" + "MemberId" + " ".repeat(10) + "|" + "Plan" + " ".repeat(10)
				+ "|" + "Trainer" + " ".repeat(15) + "|");
		System.out.println("-".repeat(100));
		for (var member : members) {
			System.out.println("|%-23s|%-18s|%-14s|%-22s|".formatted(member.getName(), member.getMemberId(),
					member.getPlan(), member.getTrainer()));
		}
	}

	public void getAllTrainersDetails() {
		trainers.sort(Comparator.comparing(Trainer::getName).thenComparing(Trainer::getAge));
		System.out.println("-".repeat(100));
		System.out.println(
				"Name" + " ".repeat(20) + "|" + "TrainerId" + " ".repeat(9) + "|" + "Plan" + " ".repeat(10) + "|");
		System.out.println("-".repeat(100));
		for (var trainer : trainers) {
			System.out.println(
					"|%-23s|%-18s|%-14s|".formatted(trainer.getName(), trainer.getTrainerId(), trainer.getDomain()));
		}
	}

	public Member getMember(long memberId) {
		for (var member : members) {
			if (member.getMemberId().equals(String.valueOf(memberId))) {
				return member;
			}
		}
		return null;
	}

	public Trainer getTrainer(long trainerId) {
		for (var trainer : trainers) {
			if (trainer.getTrainerId().equals(String.valueOf(trainerId))) {
				return trainer;
			}
		}
		return null;
	}

	public void showTrainersOfPlan(String plan) {
		System.out.println("Trainers of " + plan);
		System.out.println("-".repeat(100));
		System.out.println(
				"TrainerId" + " ".repeat(15) + "|" + "Name" + " ".repeat(20) + "|" + "Age" + " ".repeat(10) + "|");
		System.out.println("-".repeat(100));
		for (var trainer : trainers) {
			if (plan.equals(trainer.getDomain())) {
				System.out.print(
						"%-24s|%-24s|%-13s|".formatted(trainer.getTrainerId(), trainer.getName(), trainer.getGender()));
			}
		}
	}
	
	public void deleteMember(Member member) {
		members.remove(member);
	}
	public void deleteTrainer(Trainer trainer) {
		trainers.remove(trainer);
	}

	public void updatePlan(Member member, String plan, Trainer trainer) {
		member.setMemberShip(plan.toUpperCase());
		member.setTrainer(trainer);
	}
	
	//added another function
	public void showAvailablePlans() {
        System.out.println("---- Available Membership Plans ----");
        for (PlanConstants plan : PlanConstants.values()) {
//            MemberShip membership = new MemberShip(plan);
            System.out.println("Plan: " + plan);
            System.out.println("Duration: " + plan.getDurationMonths() + " months");
            System.out.println("Fee: ₹" + plan.getFee());
            System.out.println("-------------------------------");
        }
	}

}
