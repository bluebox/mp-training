package WarriorsForgeGym.exceptions;

public class MemberNotFoundException extends Exception 
{
    public MemberNotFoundException(String errorMessage)
    {
        super(errorMessage);
    }

    public MemberNotFoundException(String errorMessage, Throwable errorCause)
    {
        super(errorMessage, errorCause);
    }
}
