/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.comment;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vishnu
 */
@XmlRootElement
public class CommentListResponseBean {

    private int status;
    private String message;
    private List<AtmCommentBean> comment;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AtmCommentBean> getComment() {
        return comment;
    }

    public void setComment(List<AtmCommentBean> comment) {
        this.comment = comment;
    }

    public CommentListResponseBean() {
    }

}
