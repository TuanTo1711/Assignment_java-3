package com.app.controller;

import java.io.Serializable;

public class Account implements Serializable {

    public static volatile String usernameSaved;
    public static volatile String passwordSaved;
    public static volatile boolean isRemembered = false;
    public static volatile String roleSaved;

    public Account() {
        this.setDefaultAccount();
    }

    private void setDefaultAccount() {
        usernameSaved = "";
        passwordSaved = "";
        roleSaved = "";
    }
}
