/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.utils;

/**
 *
 * @author vishnu
 */
public class Utilities {

    public static ErrorResponseBean getErrorResponseObject(int status, String message) {
        ErrorResponseBean errorBean = new ErrorResponseBean();
        errorBean.setStatus(status);
        errorBean.setMessage(message);

        return errorBean;
    }

    public static CommonResponseBean getCommonResponseBean(int status, String message) {
        CommonResponseBean commonResponseBean = new CommonResponseBean();
        commonResponseBean.setStatus(status);
        commonResponseBean.setMessage(message);

        return commonResponseBean;
    }

}
