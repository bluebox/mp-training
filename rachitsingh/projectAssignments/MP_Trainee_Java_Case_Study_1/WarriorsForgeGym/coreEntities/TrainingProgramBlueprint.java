package WarriorsForgeGym.coreEntities;

import WarriorsForgeGym.enums.FitnessGoal;
import WarriorsForgeGym.enums.TrainingProgramType;
import WarriorsForgeGym.enums.Level;
import WarriorsForgeGym.enums.MartialArtType;
import WarriorsForgeGym.enums.TrainingMode;

public class TrainingProgramBlueprint
{
    private String programTitle;
    private TrainingProgramType programType;
    private TrainingMode trainingMode;
    private MartialArtType martialArtType;
    private FitnessGoal goal;
    private Level proficiencyLevel;
    private int durationInMonths;
    private double monthlyCharges;

    public TrainingProgramBlueprint(String programTitle, TrainingProgramType programType, TrainingMode trainingMode, MartialArtType martialArtType, FitnessGoal goal, Level proficiencyLevel, int durationInMonths, double monthlyCharges)
    {
        this.programTitle = programTitle;
        this.programType = programType;
        this.trainingMode = trainingMode;
        this.martialArtType = martialArtType;
        this.goal = goal;
        this.proficiencyLevel = proficiencyLevel;
        this.durationInMonths = durationInMonths;
        this.monthlyCharges = monthlyCharges;
    }

    public String getProgramTitle()
    {
        return programTitle;
    }
    
    public void setPlanID(String programTitle)
    {
        this.programTitle = programTitle;
    }

    public TrainingProgramType getProgramType()
    {
        return programType;
    }
    public void setProgramType(TrainingProgramType programType)
    {
        this.programType = programType;
    }

    public TrainingMode getTrainingMode()
    {
        return trainingMode;
    }

    public void setTrainingMode(TrainingMode trainingMode)
    {
        this.trainingMode = trainingMode;
    }

    public MartialArtType getMartialArtType()
    {
        return martialArtType;
    }

    public void setMartialArtType(MartialArtType martialArtType)
    {
        this.martialArtType = martialArtType;
    }
    
    public FitnessGoal getGoal()
    {
        return goal;
    }

    public void setGoal(FitnessGoal goal)
    {
        this.goal = goal;
    }

    public Level getLevel()
    {
        return proficiencyLevel;
    }

    public void setLevel(Level proficiencyLevel)
    {
        this.proficiencyLevel = proficiencyLevel;
    }

    public int getDurationInMonths()
    {
        return durationInMonths;
    }

    public void setDurationInMonths(int durationInMonths) 
    {
        if (durationInMonths >= 1 || durationInMonths <= 12)
        {
            this.durationInMonths = durationInMonths;
        }
    }

    public double getMonthlyCharges()
    {
        return monthlyCharges;
    }

    public void setMonthlyCharges(double monthlyCharges)
    {
        if (monthlyCharges >= 0.0)
        {
            this.monthlyCharges = monthlyCharges;
        }
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder().append("\nTraining Program Blueprint : [")
                .append("Program Name: ").append(programTitle).append(", ")
                .append("Program Type: ").append(programType).append(", ");

                if (programType == TrainingProgramType.WEIGHT_TRAINING)
                {
                    builder.append("Training Mode: ").append(trainingMode).append(", ");
                }
                else if (programType == TrainingProgramType.MARTIAL_ARTS)
                {
                    builder.append("Martial Art: ").append(martialArtType).append(", ");
                }

                builder.append("Training Goal: ").append(goal).append(", ")
                .append("Proficiency Level: ").append(proficiencyLevel).append(", ")
                .append("Duration: ").append(durationInMonths).append(", ")
                .append("Monthly Charges: Rs.").append(monthlyCharges).append("]").toString();
                
                return builder.toString();
    }
}
