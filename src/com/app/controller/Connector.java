package com.app.controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import java.text.*;
import java.util.logging.*;

public class Connector {

    private String url = "jdbc:sqlserver://localhost;"
            + "databaseName=FPL_DaoTao"
            + "encrypt=true;"
            + "trustServerCertificate=true";

    private String user;
    private String pass;

    private String tableName;

    Connection connect;

    public Connector() {
    }

    public Connector(String user, String pass) {
        this.url = "jdbc:sqlserver://localhost;"
                + "databaseName=FPL_DaoTao;"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        this.user = user;
        this.pass = pass;
    }

    public Connector(String user, String pass, String databaseName) {
        this.url = "jdbc:sqlserver://localhost;"
                + "databaseName=" + databaseName + ";"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        this.user = user;
        this.pass = pass;
    }

    /**
     * @return <i>true</i> if server is connected. Otherwise <i>false</i> if the
     * connect is null
     */
    public boolean isConnected() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.connect = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.WARNING, "Can't connect server", ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.connect != null;
    }

    public List<List<String>> getDataFrom(String tableName) {
        List<List<String>> table = new ArrayList<>();

        try {
            final String query = "select * from " + tableName;
            final Statement state = this.connect.createStatement();
            final ResultSet res = state.executeQuery(query);
            final ResultSetMetaData rsmd = res.getMetaData();

            int column_count = rsmd.getColumnCount();
            
            System.out.println(column_count);

            while (res.next()) {
                List<String> temp = new ArrayList<>();
                for (int i = 1; i <= column_count; i++) {
                    temp.add(res.getString(i));
                }
                table.add(temp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.WARNING, null, ex);
        }
        return table;
    }
    
    /**
     * 
     * @return 
     */

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
