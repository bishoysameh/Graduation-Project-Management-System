package com.graduationProject.gpManagementSystem.exception;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String resourceName, int id) {
        super("RESOURCE_NOT_FOUND", resourceName + " with ID " + id + " not found");
    }
}



/*
 
 
  
  public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String resourceName, int id) {
        super("RESOURCE_NOT_FOUND", resourceName + " with ID " + id + " not found");
    }
}

public class DuplicateEntryException extends CustomException {
    public DuplicateEntryException(String resourceName, String fieldName, String value) {
        super("DUPLICATE_ENTRY", resourceName + " with " + fieldName + " " + value + " already exists");
    }
}

public class InvalidInputException extends CustomException {
    public InvalidInputException(String fieldName, String message) {
        super("INVALID_INPUT", "Invalid input for " + fieldName + ": " + message);
    }
}


 */