package com.jobsPortal.jobs_finite.exception;


import java.util.Date;

public class ErrorMessage {

    private Date date;

    private String error;

    private String errorDescription;

    public Date getDate() {
        return date;
    }

    public ErrorMessage(Date date, String error, String errorDescription) {
        this.date = date;
        this.error = error;
        this.errorDescription = errorDescription;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
