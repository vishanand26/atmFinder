/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.location;

import javax.ws.rs.QueryParam;

/**
 *
 * @author vishnu
 */
public class AtmListResponseParamsbean {

    @QueryParam("latitude")
    private Double latitude;
    @QueryParam("longitude")
    private Double longitude;

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public AtmListResponseParamsbean() {
    }

}
