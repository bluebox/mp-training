package WarriorsForgeGym.comparator;
import WarriorsForgeGym.coreEntities.TrainingProgramBlueprint;
import java.util.Comparator;

public class TrainingProgramComparator implements Comparator<TrainingProgramBlueprint> 
{
    @Override
    public int compare(TrainingProgramBlueprint program1, TrainingProgramBlueprint program2) 
    {
        return Integer.compare(program1.getDurationInMonths(), program2.getDurationInMonths());
    }
}