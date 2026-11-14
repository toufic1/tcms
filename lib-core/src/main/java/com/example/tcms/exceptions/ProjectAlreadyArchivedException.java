package com.example.tcms.exceptions;

public class ProjectAlreadyArchivedException extends RuntimeException{
    public ProjectAlreadyArchivedException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
