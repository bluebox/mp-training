package WarriorsForgeGym.exceptions;

public class TrainingPlanNotFoundException extends Exception 
{
    public TrainingPlanNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }

    public TrainingPlanNotFoundException(String errorMessage, Throwable errorCause)
    {
        super(errorMessage, errorCause);
    }
}
