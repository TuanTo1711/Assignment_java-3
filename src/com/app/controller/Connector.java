package com.app.controller;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connector {

    private static final Logger LOG = Logger.getLogger(Connector.class.getName());

    private String url = "jdbc:sqlserver://localhost;"
            + "databaseName=FPL_DaoTao"
            + "encrypt=true;"
            + "trustServerCertificate=true";

    private String user;

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
    private String pass;
    private String databaseName;

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
        this.databaseName = "FPL_DaoTao";
    }

    public Connector(String user, String pass, String databaseName) {
        this.url = "jdbc:sqlserver://localhost;"
                + "databaseName=" + databaseName + ";"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        this.user = user;
        this.pass = pass;
        this.databaseName = databaseName;
    }

    public boolean connection() {
        try {
            this.connect = DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.connect != null;
    }

    public List<List<String>> getDataFrom(String tableName) {
        // new 2d list to return data got from table in sql server
        List<List<String>> table = new ArrayList<>();
        try {
            final String query = "use " + databaseName
                    + " select * from " + tableName;
            final Statement state = this.connect.createStatement();
            final ResultSet res = state.executeQuery(query);
            final ResultSetMetaData rsmd = res.getMetaData();

            int column_count = rsmd.getColumnCount();

            while (res.next()) {
                List<String> temp = new ArrayList<>();
                for (int i = 1; i <= column_count; i++) {
                    temp.add(res.getString(i));
                }
                table.add(temp);
            }

            table.forEach(row -> Arrays.toString(row.toArray()));
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, "Can't get data from " + tableName, ex);
        }
        return table;
    }

    public List<List<String>> getDataFrom(String tableName, String role) {
        List<List<String>> table = new ArrayList();
        try {
            final String query = "use " + databaseName
                    + " select * from " + tableName
                    + " where Role = " + "\'" + role + "\'";

            final Statement state = this.connect.createStatement();
            final ResultSet res = state.executeQuery(query);
            final ResultSetMetaData rsmd = res.getMetaData();

            int column_count = rsmd.getColumnCount();

            while (res.next()) {
                List<String> temp = new ArrayList<>();
                for (int i = 1; i <= column_count; i++) {
                    temp.add(res.getString(i));
                }
                table.add(temp);
            }
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, "Can't get data from " + tableName, ex);
        }

        return table;
    }

    public List<String> getDeletedRows(
            List<List<String>> dataGUI,
            List<List<String>> dataSQL
    ) {
        var dataForm = dataGUI.stream().map(row -> row.get(0)).toList();

        List<String> deleted = dataSQL.stream()
                .map(row -> row.get(0))
                .filter(value
                        -> !dataForm.stream()
                        .anyMatch(s -> s.equalsIgnoreCase(value))
                )
                .toList();
        return deleted;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
