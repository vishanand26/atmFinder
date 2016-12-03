/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.detail;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vishnu
 */
@XmlRootElement
public class AtmDetailResponseBean {

    private String message;
    private int status;
    private AtmDetailDataBean detail;

    public AtmDetailResponseBean() {
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

    public AtmDetailDataBean getDetail() {
        return detail;
    }

    public void setDetail(AtmDetailDataBean detail) {
        this.detail = detail;
    }
}
