package coreEntities;

import enums.TrainingPlanType;
import enums.MartialArtType;
import enums.TrainingMode;
import interfaces.TrainableEntity;
import java.util.HashSet;
import java.util.Set;

public class TrainerProfile implements TrainableEntity 
{

    private String trainerID;
    private String name;
    private TrainingPlanType specializedIn;
    private TrainingMode trainingMode;
    private MartialArtType martialArtType;
    private int experienceInYears;
    private Set<TrainingPlanType> assignedTrainingPrograms = new HashSet<>();

    // this parameterised constructor is for trainers who are specialized in WeightTraining
    public TrainerProfile(String trainerID, String name, TrainingMode trainingMode, int experienceInYears) 
    {
        this.trainerID = trainerID;
        this.name = name;
        this.specializedIn = TrainingPlanType.WEIGHT_TRAINING;
        this.trainingMode = trainingMode;
        this.experienceInYears = experienceInYears;
    }

    // this parameterised constructor is for trainers who are specialized in Martial Arts
    public TrainerProfile(String trainerID, String name, MartialArtType martialArtType, int experienceInYears) 
    {
        this.trainerID = trainerID;
        this.name = name;
        this.specializedIn = TrainingPlanType.MARTIAL_ARTS;
        this.martialArtType = martialArtType;
        this.experienceInYears = experienceInYears;
    }

    public String getTrainerID() 
    {
        return trainerID;
    }
    
    public TrainingPlanType getSpecializedIn()
    {
        return specializedIn;
    }

    public void setTrainerID(String trainerID) 
    {
        this.trainerID = trainerID;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public TrainingMode getTrainingMode() 
    {
        return trainingMode;
    }

    public MartialArtType getMartialArtType() 
    {
        return martialArtType;
    }

    public void setTrainingMode(TrainingMode trainingMode) 
    {
        this.trainingMode = trainingMode;
    }
    
    public void setMartialArtType(MartialArtType martialArtType) 
    {
        this.martialArtType = martialArtType;
    }

    public int getExperienceInYears() 
    {
        return experienceInYears;
    }

    public void setExperienceInYears(int experienceInYears) 
    {
        if (experienceInYears > 0)
        {
            this.experienceInYears = experienceInYears;
        }
    }
    // to avoid assigning an already assigned program to the same trainer
    @Override
    public void enrollInTrainingProgram(TrainingPlanType trainingPlanType)
    {
        if (assignedTrainingPrograms.contains(trainingPlanType)) 
        {
            System.out.println("\nTrainer " + name + " is already assigned to: " + trainingPlanType + " program");
        } 
        else 
        {
            assignedTrainingPrograms.add(trainingPlanType);
            System.out.println("\n" +
                    name + " has been successfully registered as a trainer for: " + trainingPlanType + " program");
        }
    }
    
    @Override
    public void displayTrainersAbout()
    {
        System.out.println("----------------------- Trainer's About ---------------------------");
        System.out.println("Name: " + name);

        if (trainingMode != null) 
        {
            System.out.println("Specialized In : " + trainingMode + " (Fitness Mode)");
        } 
        else if (martialArtType != null) 
        {
            System.out.println("Specialized In : " + martialArtType + " (Martial Art)");
        } 
        else 
        {
            System.out.println("Specialized In : Not specified");
        }
        System.out.println("Experience: " + experienceInYears + " years");
    }
    @Override
    public String toString() 
    {
        return "TrainerProfile {" +
                "trainerID='" + trainerID + '\'' +
                ", name='" + name + '\'' +
                ", specializedIn=" + specializedIn +
                ", experienceInYears=" + experienceInYears +
                '}';
    }
}

