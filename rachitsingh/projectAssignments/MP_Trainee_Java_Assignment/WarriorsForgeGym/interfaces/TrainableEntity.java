package interfaces;

import enums.TrainingPlanType;

public interface TrainableEntity 
{
    void enrollInTrainingProgram(TrainingPlanType trainingPlanType);
    void displayTrainersAbout();
}
