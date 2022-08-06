package main.java.controller;

import java.io.Serializable;

public final class Account implements Serializable {
    public static volatile String usernameSaved;
    public static volatile String passwordSaved;
    public static volatile boolean isRemembered;
    public static volatile String roleSaved;
}