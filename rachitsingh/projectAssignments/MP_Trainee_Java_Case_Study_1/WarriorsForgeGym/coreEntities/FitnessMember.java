package WarriorsForgeGym.coreEntities;

import WarriorsForgeGym.enums.*;

public class FitnessMember extends Person 
{
    private String memberID;
    private TrainingProgramBlueprint membershipPlan;
    private FitnessGoal fitnessGoal;
    private TrainingDietPlan trainingDietPlan;
    private PhysiqueType physiqueType;
    private TrainerProfile trainerAssigned;

    public FitnessMember(String memberID, String name, int age, Gender gender,FitnessGoal fitnessGoal, PhysiqueType physiqueType)
    {
        super(name, age, gender);
        this.memberID = memberID;
        this.fitnessGoal = fitnessGoal;
        this.physiqueType = physiqueType;
    }
    public String getMemberID() 
    {
        return memberID;
    }

    public TrainingProgramBlueprint getMembershipPlan() 
    {
        return membershipPlan;
    }

    public void setMembershipPlan(TrainingProgramBlueprint membershipPlan) 
    {
        this.membershipPlan = membershipPlan;
    }

    public FitnessGoal getFitnessGoal() 
    {
        return fitnessGoal;
    }

    public void setFitnessGoal(FitnessGoal fitnessGoal) 
    {
        this.fitnessGoal = fitnessGoal;
    }

    public TrainingDietPlan getTrainingDietPlan() 
    {
        return trainingDietPlan;
    }

    public void setDietPlan(TrainingDietPlan trainingDietPlan) 
    {
        this.trainingDietPlan = trainingDietPlan;
    }

    public TrainerProfile getTrainerAssigned() 
    {
        return trainerAssigned;
    }

    public void setAssignedTrainer(TrainerProfile trainerAssigned) 
    {
        this.trainerAssigned = trainerAssigned;
    }

    public PhysiqueType getPhysiqueType() 
    {
        return physiqueType;
    }

    public void setPhysiqueType(PhysiqueType physiqueType) 
    {
        this.physiqueType = physiqueType;
    }
    @Override
    public void displayPersonDetails() 
    {
        System.out.println("---- Details of Enrolled Member ----");
        System.out.println("Member ID   : " + memberID);
        System.out.println("Name        : " + getName());
        System.out.println("Age         : " + getAge());
        System.out.println("Gender      : " + getGender());
        System.out.println("Physique Type   : " + physiqueType);
        System.out.println("Fitness Goal: " + fitnessGoal);

        if (membershipPlan != null) 
        {
            System.out.println("Membership  : " + membershipPlan.getProgramTitle() +
                    " [" + membershipPlan.getProgramType());

            if (membershipPlan.getProgramType() == TrainingProgramType.WEIGHT_TRAINING) 
            {
                System.out.print(" - " + membershipPlan.getTrainingMode());
            } 
            else if (membershipPlan.getProgramType() == TrainingProgramType.MARTIAL_ARTS) 
            {
                System.out.print(" - " + membershipPlan.getMartialArtType());
            }

            System.out.println("]");
        } 
        else 
        {
            System.out.println("Membership  : Not granted");
        }

        if (trainerAssigned != null) 
        {
            System.out.println("Trainer     : " + trainerAssigned.getName() +
                               " (" + trainerAssigned.getSpecializedIn() + ")");
        } else 
        {
            System.out.println("Trainer     : Not Assigned");
        }

        if (trainingDietPlan != null) 
        {
            System.out.println("Training Diet Plan   : ");
            trainingDietPlan.displayTrainingMealPlan();
        } else 
        {
            System.out.println("Training Diet Plan   : Not Available");
        }
    }

    @Override
    public String toString() 
    {
        return "GymMember{" +
                "memberId='" + memberID + '\'' +
                ", name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender=" + getGender() +
                ", goal=" + fitnessGoal +
                ", physiqueType=" + physiqueType +
                '}';
    }
}

