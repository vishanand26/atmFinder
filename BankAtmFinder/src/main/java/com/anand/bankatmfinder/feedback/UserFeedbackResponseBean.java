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
public class UserFeedbackResponseBean {

    private String message;
    private int status;

    public UserFeedbackResponseBean() {

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
