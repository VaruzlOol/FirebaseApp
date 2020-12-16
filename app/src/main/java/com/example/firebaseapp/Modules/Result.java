package com.example.firebaseapp.Modules;

public class Result {
    private Boolean successful;
    private Boolean error;
    private String message;

    Result(){
        this.successful = false;
        this.error = true;
        this.message = "N/A";
    }

    private void setData(Boolean successful, Boolean error, String message){
        this.successful = successful;
        this.error = error;
        this.message = message;
    }

    public void setSuccessful(String message){
        setData(true, false, message);
    }

    public void setError(String message){
        setData(true, error, message);
    }

    public Boolean getSuccessful() {
        return successful;
    }

    public Boolean getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
