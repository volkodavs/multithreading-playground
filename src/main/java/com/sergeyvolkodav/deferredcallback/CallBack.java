package com.sergeyvolkodav.deferredcallback;

public class CallBack {

    private long executeAt;
    private String message;

    public CallBack(long executeAfter, String message) {
        this.executeAt = System.currentTimeMillis() + executeAfter * 1000;
        this.message = message;
    }

    public long getExecuteAt() {
        return executeAt;
    }

    public void setExecuteAt(long executeAt) {
        this.executeAt = executeAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
