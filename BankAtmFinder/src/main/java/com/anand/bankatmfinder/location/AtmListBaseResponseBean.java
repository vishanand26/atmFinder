/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.location;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vishnu
 */
@XmlRootElement
public class AtmListBaseResponseBean {

    private String message;
    private int status;
    List<AtmListResponseBean> atmList;

    public AtmListBaseResponseBean() {
    }

    public List<AtmListResponseBean> getAtmList() {
        return atmList;
    }

    public void setAtmList(List<AtmListResponseBean> atmList) {
        this.atmList = atmList;
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
