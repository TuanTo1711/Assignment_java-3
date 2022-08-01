package com.app.controller;

import java.io.Serializable;

public interface FileInterface extends Serializable {
    public void openFile();
    public void saveAsFile();
    public void saveFile();
    public void printFile();
    public void closesFile();
}
