package com.cherry.cherry_anddemo.ui.interview.observer.normal;

public class EventMsg {

    private String msg;
    private String from;

    public EventMsg(String msg, String from) {
        this.msg = msg;
        this.from = from;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "EventMsg{" +
                "msg='" + msg + '\'' +
                ", from='" + from + '\'' +
                '}';
    }
}
