package WarriorsForgeGym.interfaces;

import WarriorsForgeGym.enums.TrainingProgramType;

public interface TrainableEntity 
{
    void enrollInTrainingProgram(TrainingProgramType trainingProgramType);
    void displayTrainersAbout();
}
