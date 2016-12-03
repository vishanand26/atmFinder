/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.database;

import com.anand.bankatmfinder.feedback.UserFeedbackRequestBean;
import com.anand.bankatmfinder.location.AtmListResponseBean;
import com.anand.bankatmfinder.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author vishnu
 */
public class DatabaseHandling {

    public static int getResultSetLength(ResultSet resultSet) {
        int size = 0;
        try {
            if (resultSet != null) {
                resultSet.beforeFirst();
                resultSet.last();
                size = resultSet.getRow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;

    }

    public static int insertUserFeedbackDataIntoDatabase(UserFeedbackRequestBean feedback) {
        Connection connection;
        PreparedStatement statement;
        int status = 0;

        try {
            connection = new DBConnectionManager().getConnection();
            statement = connection.prepareStatement("insert into userfeedback(atm_id,cash_status,waiting_status,comment,planning_status) values (?,?,?,?,?)");

            statement.setString(1, feedback.getAtm_id());
            statement.setString(2, feedback.getCash_status());
            statement.setString(3, feedback.getWaiting_status());
            statement.setString(4, feedback.getComment());
            statement.setString(5, feedback.getPlanning_status());

            status = statement.executeUpdate();

            connection.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static ResultSet getParticularAtmDetailFromDatabase(String atm_id) {
        ResultSet resultSet = null;
        try {
            Connection connection = new DBConnectionManager().getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from userfeedback where atm_id=?");
            ps.setString(1, atm_id);

            resultSet = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public static int insertParticularUserViewCountData(String atm_id) {
        Connection conn;
        PreparedStatement ps;
        int status = 0;

        try {
            conn = new DBConnectionManager().getConnection();
            int count = getParticularAtmUserViewCount(atm_id);
            if (count > 0) {
                ps = conn.prepareStatement("UPDATE viewcount " + "  SET count = ? " + "WHERE atm_id = ?");
                ps.setString(1, (count + 1) + Constants.EXPTY_STRING);
                ps.setString(2, atm_id);

                status = ps.executeUpdate();

                ps.close();
            } else {
                ps = conn.prepareStatement("insert into viewcount(atm_id,count) values (?,?)");
                ps.setString(1, atm_id);
                ps.setString(2, 1 + Constants.EXPTY_STRING);

                status = ps.executeUpdate();

                ps.close();
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    public static int getParticularAtmUserViewCount(String atm_id) {
        int count = 0;
        Connection connection;
        PreparedStatement ps;

        try {
            connection = new DBConnectionManager().getConnection();
            ps = connection.prepareStatement("select * from viewcount where atm_id=?");
            ps.setString(1, atm_id);

            ResultSet rs = ps.executeQuery();

            int size = getResultSetLength(rs);

            if (size > 0) {
                rs.beforeFirst();
                while (rs.next()) {
                    count = Integer.parseInt(rs.getString(2));
                }
            }

            connection.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    public static ResultSet getAtmListDataFromDatabase(Double latitude, Double longitude) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try {
            connection = new DBConnectionManager().getConnection();
            ps = connection.prepareStatement("select * from atmlist where atm_latitude=? And atm_longitude=?");
            ps.setString(1, latitude + Constants.EXPTY_STRING);
            ps.setString(2, longitude + Constants.EXPTY_STRING);

            resultSet = ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultSet;
    }

    public static void insertAtmListDataintoDatabase(List<AtmListResponseBean> list, Double latitude, Double longitude) {

        Connection connection;
        PreparedStatement statement;

        try {
            int i = 0;
            connection = new DBConnectionManager().getConnection();
            statement = connection.prepareStatement("insert into atmlist(atm_id,atm_name,atm_address,atm_latitude,atm_longitude,atm_timestamp) values (?,?,?,?,?,?)");

            for (AtmListResponseBean entity : list) {
                statement.setString(1, entity.getAtmId());
                statement.setString(2, entity.getAtmName());
                statement.setString(3, entity.getAtmAddress());
                statement.setString(4, latitude + "");
                statement.setString(5, longitude + "");
                statement.setString(6, System.currentTimeMillis() + "");

                statement.addBatch();
                i++;
                if (i % 1000 == 0 || i == list.size()) {
                    statement.executeBatch();
                }
            }

            connection.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
