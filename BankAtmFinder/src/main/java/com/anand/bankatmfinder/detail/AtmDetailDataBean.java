/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.detail;

/**
 *
 * @author vishnu
 */
public class AtmDetailDataBean {
    
    private String cash_percentage;
    private String long_wait_percentage;
    private String normal_wait_percentage;
    private String no_wait_percentage;
    private String planning_percentage;

    public String getCash_percentage() {
        return cash_percentage;
    }

    public void setCash_percentage(String cash_percentage) {
        this.cash_percentage = cash_percentage;
    }

    public String getLong_wait_percentage() {
        return long_wait_percentage;
    }

    public void setLong_wait_percentage(String long_wait_percentage) {
        this.long_wait_percentage = long_wait_percentage;
    }

    public String getNormal_wait_percentage() {
        return normal_wait_percentage;
    }

    public void setNormal_wait_percentage(String normal_wait_percentage) {
        this.normal_wait_percentage = normal_wait_percentage;
    }

    public String getNo_wait_percentage() {
        return no_wait_percentage;
    }

    public void setNo_wait_percentage(String no_wait_percentage) {
        this.no_wait_percentage = no_wait_percentage;
    }

    public String getPlanning_percentage() {
        return planning_percentage;
    }

    public void setPlanning_percentage(String planning_percentage) {
        this.planning_percentage = planning_percentage;
    }
    
    public AtmDetailDataBean()
    {
        
    }
}
