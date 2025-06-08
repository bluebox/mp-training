package WarriorsForgeGym.exceptions;

public class InvalidEnumValueException extends RuntimeException
{
    public InvalidEnumValueException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidEnumValueException(String errorMessage, Throwable errorCause) {
        super(errorMessage, errorCause);
    }
}
