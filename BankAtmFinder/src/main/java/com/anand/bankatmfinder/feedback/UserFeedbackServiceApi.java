/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.feedback;

import com.anand.bankatmfinder.database.DatabaseHandling;
import com.anand.bankatmfinder.utils.CommonResponseBean;
import com.anand.bankatmfinder.utils.Constants;
import com.anand.bankatmfinder.utils.ErrorResponseBean;
import com.anand.bankatmfinder.utils.Utilities;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author vishnu
 */
@Path("userfeedback")
public class UserFeedbackServiceApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response insertUserFeedback(UserFeedbackRequestBean feedback) {

        Response response;
        int status = DatabaseHandling.insertUserFeedbackDataIntoDatabase(feedback);

        if (status > 0) {
            CommonResponseBean commonBean = Utilities.getCommonResponseBean(Constants.RESPONSE_CREATED, "User feedback Successfully inserted");

            response = Response.status(Response.Status.CREATED)
                    .entity(commonBean).build();
        } else {
            ErrorResponseBean errorBean = Utilities.getErrorResponseObject(Constants.RESPONSE_SERVER_NOT_IMPLEMENTED, "Failed to insert the Feedback record");

            response = Response.status(Response.Status.NOT_IMPLEMENTED)
                    .entity(errorBean).build();
        }

        return response;
    }

}
