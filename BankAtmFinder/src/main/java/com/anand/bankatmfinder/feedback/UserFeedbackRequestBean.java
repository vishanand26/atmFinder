/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.feedback;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vishnu
 */
@XmlRootElement
public class UserFeedbackRequestBean {

    private String atm_id;
    private String cash_status;
    private String waiting_status;
    private String comment;
    private String planning_status;

    public String getPlanning_status() {
        return planning_status;
    }

    public void setPlanning_status(String planning_status) {
        this.planning_status = planning_status;
    }

    public String getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(String atm_id) {
        this.atm_id = atm_id;
    }

    public String getCash_status() {
        return cash_status;
    }

    public void setCash_status(String cash_status) {
        this.cash_status = cash_status;
    }

    public String getWaiting_status() {
        return waiting_status;
    }

    public void setWaiting_status(String waiting_status) {
        this.waiting_status = waiting_status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
