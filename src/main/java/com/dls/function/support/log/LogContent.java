package com.dls.function.support.log;

import com.dls.arc.Entry;

import java.sql.Timestamp;

public class LogContent extends Entry {
    private int logId;
    private int logAction;
    private int logLevel;
    private String logContent;
    private String logUser;
    private Timestamp logTime;
    private int deleteFlag;
    private Timestamp queryStartTime;
    private Timestamp queryEndTime;

    public LogContent(int logAction) {
        this.logAction = logAction;
    }

    public void setQueryStartTime(Timestamp queryStartTime) {
        this.queryStartTime = queryStartTime;
    }

    public void setQueryEndTime(Timestamp queryEndTime) {
        this.queryEndTime = queryEndTime;
    }

    public Timestamp getQueryEndTime() {

        return queryEndTime;
    }

    public Timestamp getQueryStartTime() {

        return queryStartTime;
    }

    public LogContent() {
    }

    public LogContent(String logUser, String logContent, int logLevel, int logAction) {

        this.logUser = logUser;
        this.logContent = logContent;
        this.logLevel = logLevel;
        this.logAction = logAction;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public void setLogAction(int logAction) {
        this.logAction = logAction;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public void setLogUser(String logUser) {
        this.logUser = logUser;
    }

    public void setLogTime(Timestamp logTime) {
        this.logTime = logTime;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public int getLogId() {

        return logId;
    }

    public int getLogAction() {
        return logAction;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public String getLogContent() {
        return logContent;
    }

    public String getLogUser() {
        return logUser;
    }

    public Timestamp getLogTime() {
        return logTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }
}
