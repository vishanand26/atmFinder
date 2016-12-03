/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.location;

/**
 *
 * @author vishnu
 */
public class AtmListResponseBean {

    private String atmId;
    private String atmName;
    private String atmAddress;
    private int cashStatus;
    private int queueStatus;
    private int planningStatus;
    private int viewCount;

    public String getAtmId() {
        return atmId;
    }

    public void setAtmId(String atmId) {
        this.atmId = atmId;
    }

    public String getAtmName() {
        return atmName;
    }

    public void setAtmName(String atmName) {
        this.atmName = atmName;
    }

    public String getAtmAddress() {
        return atmAddress;
    }

    public void setAtmAddress(String atmAddress) {
        this.atmAddress = atmAddress;
    }

    public int getCashStatus() {
        return cashStatus;
    }

    public void setCashStatus(int cashStatus) {
        this.cashStatus = cashStatus;
    }

    public int getQueueSttaus() {
        return queueStatus;
    }

    public void setQueueSttaus(int queueSttaus) {
        this.queueStatus = queueSttaus;
    }

    public int getPlanningStatus() {
        return planningStatus;
    }

    public void setPlanningStatus(int planningStatus) {
        this.planningStatus = planningStatus;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public AtmListResponseBean() {
    }

}
