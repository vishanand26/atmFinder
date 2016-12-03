/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.comment;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vishnu
 */
@XmlRootElement
public class AtmCommentBean {

    private String comment;

    public AtmCommentBean() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
