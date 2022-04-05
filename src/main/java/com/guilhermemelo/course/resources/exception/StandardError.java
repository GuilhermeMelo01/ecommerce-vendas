package com.guilhermemelo.course.resources.exception;

public class StandardError {

    private Integer Status;
    private String String;
    private Long timeStamp;

    public StandardError(Integer status, java.lang.String string, Long timeStamp) {
        Status = status;
        String = string;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public java.lang.String getString() {
        return String;
    }

    public void setString(java.lang.String string) {
        String = string;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
