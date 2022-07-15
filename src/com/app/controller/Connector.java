package com.app.controller;

import java.sql.*;
//import java.text.*;
import java.util.logging.*;

public class Connector {

    private String server;
    private String user;
    private String pass;
    protected Connection connect;

    public Connector() {
    }

    public Connector(String user, String pass) {
        this.server = "jdbc:sqlserver://localhost;"
                + "databaseName=master;"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        this.user = user;
        this.pass = pass;
    }

    /**
     * This constructor is create default local host is <b>1433</b>
     *
     * @param user is login username
     * @param pass is password of user
     * @param databaseName is name of database needed query
     */
    public Connector(String user, String pass, String databaseName) {
        this.server = "jdbc:sqlserver://localhost;"
                + "databaseName=" + databaseName + ";"
                + "encrypt=true;"
                + "trustServerCertificate=true";
        this.user = user;
        this.pass = pass;
    }

    /**
     *
     * @param localhost is customize local host necessary to connect
     * @param user is login username
     * @param pass is password of user
     * @param databaseName is name of database to query
     */
    public Connector(String localhost, String user, String pass, String databaseName) {
        this.server = "jdbc:sqlserver://localhost:" + localhost + ";"
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
    public boolean connect() {
        try {
            this.connect = DriverManager.getConnection(server, user, pass);
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.WARNING, "Can't connect server", ex);
        }
        return this.connect != null;
    }

    public void getDataFrom(String dataName) {
        try {
            final String query = "select * from " + dataName;
            Statement state = this.connect.createStatement();
            ResultSet res = state.executeQuery(query);
            
            System.out.println(res.getString(0));
        } catch (SQLException ex) {
            Logger.getLogger(Connector.class.getName()).log(Level.WARNING, null, ex);
        }
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
