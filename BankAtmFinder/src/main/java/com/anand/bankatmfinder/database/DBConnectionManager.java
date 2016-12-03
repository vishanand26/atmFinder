/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anand.bankatmfinder.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author vishnu
 */
public class DBConnectionManager {

    private final String DATABASE_URL = "jdbc:mysql://localhost/atmfinder";
    private final String DATABASE_CLASS_NAME = "com.mysql.jdbc.Driver";
    private final String DATABASE_USER_NAME = "root";
    private final String DATABASE_PASSWORD = "";

    private Connection connection;

    public DBConnectionManager() throws ClassNotFoundException, SQLException {
        Class.forName(DATABASE_CLASS_NAME);
        this.connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_NAME, DATABASE_PASSWORD);
    }

    public Connection getConnection() {
        return this.connection;
    }

}
