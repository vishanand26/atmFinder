/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.comment;

import com.anand.bankatmfinder.database.DatabaseHandling;
import com.anand.bankatmfinder.detail.AtmDetailRequestParamBean;
import com.anand.bankatmfinder.utils.Constants;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vishnu
 */
@Path("comments")
public class AtmCommentListServiceApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtmCommentList(@BeanParam AtmDetailRequestParamBean paramBean) {

        ArrayList<AtmCommentBean> commentList = getCommentList(paramBean.getAtm_id());

        CommentListResponseBean responseBean = new CommentListResponseBean();
        responseBean.setStatus(Constants.RESPONSE_OK);
        responseBean.setMessage("List Of Comments");
        responseBean.setComment(commentList);

        Response response = Response.status(Response.Status.OK)
                .entity(responseBean).build();

        return response;
    }

    private ArrayList<AtmCommentBean> getCommentList(String atm_id) {
        ArrayList<AtmCommentBean> list = new ArrayList<>();
        ResultSet resultset = DatabaseHandling.getParticularAtmDetailFromDatabase(atm_id);

        if (resultset != null) {
            int size = DatabaseHandling.getResultSetLength(resultset);
            if (size > 0) {
                try {
                    resultset.beforeFirst();
                    while (resultset.next()) {
                        AtmCommentBean bean = new AtmCommentBean();
                        bean.setComment(resultset.getString(4));
                        list.add(bean);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }
}
