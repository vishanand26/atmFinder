/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.detail;

import com.anand.bankatmfinder.database.DatabaseHandling;
import com.anand.bankatmfinder.utils.Constants;
import java.sql.ResultSet;
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
@Path("atmdetail")
public class AtmDetailServiceApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtmDetail(@BeanParam AtmDetailRequestParamBean paramBean) {

        AtmDetailDataBean dataBean = getAtmDetailDataObject(paramBean.getAtm_id());

        DatabaseHandling.insertParticularUserViewCountData(paramBean.getAtm_id());

        AtmDetailResponseBean responseBean = new AtmDetailResponseBean();
        responseBean.setStatus(Constants.RESPONSE_OK);
        responseBean.setMessage("Particular Atm Detail");
        responseBean.setDetail(dataBean);

        Response response = Response.status(Response.Status.OK)
                .entity(responseBean).build();

        return response;
    }

    private AtmDetailDataBean getAtmDetailDataObject(String atm_id) {
        AtmDetailDataBean atmDetail = new AtmDetailDataBean();
        float cash_yes_count = 0, cash_no_count = 0, long_wait_count = 0, normal_wait_count = 0, no_wait_count = 0, planning_yes_count = 0, planning_no_count = 0;

        ResultSet resultSet = DatabaseHandling.getParticularAtmDetailFromDatabase(atm_id);

        if (resultSet != null) {
            int size = DatabaseHandling.getResultSetLength(resultSet);
            if (size > 0) {
                try {
                    resultSet.beforeFirst();
                    while (resultSet.next()) {

                        String cash_status = resultSet.getString(2);
                        if (cash_status.equalsIgnoreCase("1")) {
                            cash_yes_count++;
                        } else {
                            cash_no_count++;
                        }

                        String planning_status = resultSet.getString(5);
                        if (planning_status.equalsIgnoreCase("1")) {
                            planning_yes_count++;
                        } else {
                            planning_no_count++;
                        }

                        String waiting_status = resultSet.getString(3);
                        if (waiting_status.equalsIgnoreCase("0")) {
                            long_wait_count++;
                        } else if (waiting_status.equalsIgnoreCase("1")) {
                            normal_wait_count++;
                        } else {
                            no_wait_count++;
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Float cashPercentage = (cash_yes_count / (cash_yes_count + cash_no_count)) * 100;
                Float planningPercentage = (planning_yes_count / (planning_yes_count + planning_no_count)) * 100;
                Float longWaitPercentage = (long_wait_count / (long_wait_count + normal_wait_count + no_wait_count)) * 100;
                Float normalWaitPercentage = (normal_wait_count / (long_wait_count + normal_wait_count + no_wait_count)) * 100;
                Float noWaitPercentage = (no_wait_count / (long_wait_count + normal_wait_count + no_wait_count)) * 100;

                atmDetail.setCash_percentage(cashPercentage.intValue() + Constants.EXPTY_STRING);
                atmDetail.setPlanning_percentage(planningPercentage.intValue() + Constants.EXPTY_STRING);
                atmDetail.setLong_wait_percentage(longWaitPercentage.intValue() + Constants.EXPTY_STRING);
                atmDetail.setNormal_wait_percentage(normalWaitPercentage.intValue() + Constants.EXPTY_STRING);
                atmDetail.setNo_wait_percentage(noWaitPercentage.intValue() + Constants.EXPTY_STRING);

            } else {
                atmDetail.setCash_percentage(Constants.EXPTY_STRING);
                atmDetail.setPlanning_percentage(Constants.EXPTY_STRING);
                atmDetail.setLong_wait_percentage(Constants.EXPTY_STRING);
                atmDetail.setNormal_wait_percentage(Constants.EXPTY_STRING);
                atmDetail.setNo_wait_percentage(Constants.EXPTY_STRING);
            }
        } else {
            atmDetail.setCash_percentage(Constants.EXPTY_STRING);
            atmDetail.setPlanning_percentage(Constants.EXPTY_STRING);
            atmDetail.setLong_wait_percentage(Constants.EXPTY_STRING);
            atmDetail.setNormal_wait_percentage(Constants.EXPTY_STRING);
            atmDetail.setNo_wait_percentage(Constants.EXPTY_STRING);
        }

        return atmDetail;
    }
}
