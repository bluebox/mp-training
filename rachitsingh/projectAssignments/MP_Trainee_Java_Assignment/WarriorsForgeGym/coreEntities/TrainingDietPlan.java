package WarriorsForgeGym.coreEntities;

import WarriorsForgeGym.enums.PhysiqueType;
import WarriorsForgeGym.enums.FitnessGoal;
import java.util.List;

public class TrainingDietPlan
{
    private FitnessGoal goal;
    private PhysiqueType physiqueType;
    private List<String> contentOfMeal;

    public TrainingDietPlan(FitnessGoal goal, PhysiqueType physiqueType, List<String> contentOfMeal)
    {
        this.goal = goal;
        this.physiqueType = physiqueType;
        this.contentOfMeal = contentOfMeal;
    }
    public FitnessGoal getFitnessGoal()
    {
        return goal;
    }

    public void setFitnessGoal(FitnessGoal goal)
    {
        this.goal = goal;
    }

    public PhysiqueType getPhysiqueType()
    {
        return physiqueType;
    }

    public void setPhysiqueType(PhysiqueType physiqueType)
    {
        this.physiqueType = physiqueType;
    }

    public List<String> getContentOfMeal()
    {
        return contentOfMeal;
    }

    public void setContentOfMeal(List<String> contentOfMeal)
    {
        this.contentOfMeal = contentOfMeal;
    }

    public void displayTrainingMealPlan()
    {
        System.out.println(
                "\nTraining Diet Plan for " + physiqueType + " physique type | " + goal + " : ");
        for (String meal : contentOfMeal) {
            System.out.println(" # " + meal);
        }
    }
    
    @Override
    public String toString()
    {
        return "TrainingDietPlan {" +
                ", fitnessGoal = " + goal +
                " , physqiqueType = " + physiqueType +
                ", meals = " + contentOfMeal + "}";
    }
}
