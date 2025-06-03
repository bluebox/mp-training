package WarriorsForgeGym.exceptions;

public class InvalidInputException extends RuntimeException 
{
    public InvalidInputException(String errorMessage)
    {
        super(errorMessage);
    }

    public InvalidInputException(String errorMessage, Throwable errorCause)
    {
        super(errorMessage, errorCause);
    }
}
