/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
----------------- */
package com.anand.bankatmfinder.location;

import com.anand.bankatmfinder.database.DatabaseHandling;
import com.anand.bankatmfinder.utils.Constants;
import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.LatLng;
import com.google.maps.model.PlaceType;
import com.google.maps.model.PlacesSearchResponse;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

/**
 *
 * @author vishnu
 */
@Path("atmlist")
public class AtmListServiceApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAtmList(@BeanParam AtmListResponseParamsbean paramBean) {

        List<AtmListResponseBean> atmList = new ArrayList<>();
        LatLng latlong = new LatLng(paramBean.getLatitude(), paramBean.getLongitude());

        List<AtmListResponseBean> list = getAtmListFromDatabase(paramBean.getLatitude(), paramBean.getLongitude());
        if (list.size() > 0) {

            atmList.addAll(list);
        } else {
            PlacesSearchResponse results = getAtmResponseFroLocation(latlong);
            if (results != null) {

                for (int i = 0; i < results.results.length; i++) {
                    AtmListResponseBean bean = new AtmListResponseBean();
                    bean.setAtmId(results.results[i].placeId);
                    bean.setAtmName(results.results[i].name);
                    bean.setAtmAddress(results.results[i].vicinity);
                    bean.setCashStatus(9);
                    bean.setPlanningStatus(9);
                    bean.setQueueSttaus(9);
                    bean.setViewCount(0);
                    atmList.add(bean);
                }
                DatabaseHandling.insertAtmListDataintoDatabase(atmList, paramBean.getLatitude(), paramBean.getLongitude());
            }
        }

        AtmListBaseResponseBean responseBean = new AtmListBaseResponseBean();
        responseBean.setStatus(Constants.RESPONSE_OK);
        responseBean.setMessage("List of Atms");
        responseBean.setAtmList(atmList);

        Response response = Response.status(Status.OK)
                .entity(responseBean).build();

        return response;
    }

    private PlacesSearchResponse getAtmResponseFroLocation(LatLng location) {
        PlacesSearchResponse results = null;

        GeoApiContext context = new GeoApiContext().setApiKey(Constants.API_KEY);

        try {
            results = PlacesApi.nearbySearchQuery(context, location)
                    .location(location)
                    .radius(500)
                    .type(PlaceType.ATM)
                    .await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    private ArrayList<AtmListResponseBean> getAtmListFromDatabase(Double latitude, Double longitude) {
        ArrayList<AtmListResponseBean> list = new ArrayList<>();

        ResultSet resultSet = DatabaseHandling.getAtmListDataFromDatabase(latitude, longitude);
        if (resultSet != null) {
            try {
                while (resultSet.next()) {
                    AtmListResponseBean bean = new AtmListResponseBean();
                    bean.setAtmId(resultSet.getString(1));
                    bean.setAtmName(resultSet.getString(2));
                    bean.setAtmAddress(resultSet.getString(3));

                    int viewCount = DatabaseHandling.getParticularAtmUserViewCount(bean.getAtmId());
                    bean.setViewCount(viewCount);

                    bean = setWaitingDataintobean(bean);
                    list.add(bean);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return list;
    }

    private AtmListResponseBean setWaitingDataintobean(AtmListResponseBean bean) {
        float cash_yes_count = 0, cash_no_count = 0, long_wait_count = 0, normal_wait_count = 0, no_wait_count = 0, planning_yes_count = 0, planning_no_count = 0;

        ResultSet resultSet = DatabaseHandling.getParticularAtmDetailFromDatabase(bean.getAtmId());
        if (resultSet != null) {
            int size = DatabaseHandling.getResultSetLength(resultSet);
            if (size > 0) {
                try {
                    resultSet.beforeFirst();
                    while (resultSet.next()) {

                        String cash_status = resultSet.getString(2);
                        if (cash_status.equalsIgnoreCase("1")) {
                            cash_yes_count = cash_yes_count + 1;
                        } else {
                            cash_no_count = cash_no_count + 1;
                        }

                        String planning_status = resultSet.getString(5);
                        if (planning_status.equalsIgnoreCase("1")) {
                            planning_yes_count = planning_yes_count + 1;
                        } else {
                            planning_no_count = planning_no_count + 1;
                        }

                        String waiting_status = resultSet.getString(3);
                        if (waiting_status.equalsIgnoreCase("0")) {
                            long_wait_count = long_wait_count + 1;
                        } else if (waiting_status.equalsIgnoreCase("1")) {
                            normal_wait_count = normal_wait_count + 1;
                        } else {
                            no_wait_count = no_wait_count + 1;
                        }

                    }

                    if (cash_yes_count >= cash_no_count) {
                        bean.setCashStatus(1);
                    } else {
                        bean.setCashStatus(0);
                    }

                    if (planning_yes_count >= planning_no_count) {
                        bean.setPlanningStatus(1);
                    } else {
                        bean.setPlanningStatus(0);
                    }

                    if (long_wait_count >= normal_wait_count && long_wait_count >= no_wait_count) {
                        bean.setQueueSttaus(0);
                    } else if (normal_wait_count >= no_wait_count && normal_wait_count >= long_wait_count) {
                        bean.setQueueSttaus(1);
                    } else {
                        bean.setQueueSttaus(2);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                bean.setCashStatus(9);
                bean.setPlanningStatus(9);
                bean.setQueueSttaus(9);
            }
        } else {
            bean.setCashStatus(9);
            bean.setPlanningStatus(9);
            bean.setQueueSttaus(9);
        }

        return bean;
    }

}
