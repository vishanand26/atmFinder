/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.detail;

import javax.ws.rs.QueryParam;

/**
 *
 * @author vishnu
 */
public class AtmDetailRequestParamBean {
    
    @QueryParam("atm_id") private String atm_id;

    public String getAtm_id() {
        return atm_id;
    }

    public void setAtm_id(String atm_id) {
        this.atm_id = atm_id;
    }

    public AtmDetailRequestParamBean() {
    }
    
    
}
