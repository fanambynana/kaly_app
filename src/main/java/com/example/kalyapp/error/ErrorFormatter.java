package com.example.kalyapp.error;

public class ErrorFormatter {
    public static String formatError(Exception exception, String action, Object data) {
        String BASE_MESSAGE = "An error occurred while %s:\n   > %s";
        if (data != null) {
            BASE_MESSAGE = BASE_MESSAGE.concat("\nObject: %s");
        }
        return String.format(
                BASE_MESSAGE,
                action,
                exception.getMessage(),
                data
        );
    }
}
