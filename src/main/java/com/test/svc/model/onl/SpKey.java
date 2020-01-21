package com.test.svc.model.onl;

public class SpKey {
    private String spId;

    private String callDt;

    public String getSpId() {
        return spId;
    }

    public void setSpId(String spId) {
        this.spId = spId == null ? null : spId.trim();
    }

    public String getCallDt() {
        return callDt;
    }

    public void setCallDt(String callDt) {
        this.callDt = callDt == null ? null : callDt.trim();
    }
}