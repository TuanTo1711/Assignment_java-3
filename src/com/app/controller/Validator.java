/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.controller;

import java.util.*;
import java.util.regex.*;
import javax.swing.*;

/**
 *
 * @author MSI GL62
 */
public class Validator {

    private Object object;
    private String message;

    public Validator() {
    }

    public Validator(Object object) {
        this.object = object;
    }

    public Validator(Object object, String message) {
        this.object = object;
        this.message = message;
    }

    public boolean isRequired(Object obj) {
        if (Objects.isNull(obj)) {
            this.object = null;
            return false;
        }

        if (obj instanceof JTextField jTextField) {
            this.object = jTextField;

            String value = jTextField.getText();
            return value.isEmpty();
        }

        if (obj instanceof JPasswordField jPasswordField) {
            this.object = jPasswordField;

            String value = String.valueOf(jPasswordField.getPassword());
            return value.isEmpty();
        }

        if (obj instanceof JTextArea jTextArea) {
            this.object = jTextArea;

            String value = String.valueOf(jTextArea.getText());
            return value.isEmpty();
        }

        if (obj instanceof JComboBox jComboBox) {
            this.object = jComboBox;

            JComboBox cbx = jComboBox;
            return cbx.getSelectedItem().equals(cbx.getItemAt(0));
        }

        if (obj instanceof ButtonGroup buttonGroup) {
            this.object = buttonGroup;

            ButtonGroup buttonsGroup = buttonGroup;
            for (var buttons = buttonsGroup.getElements(); buttons.hasMoreElements();) {
                JRadioButton temp = (JRadioButton) buttons.nextElement();
                if (temp.isSelected()) {
                    return false;
                }
            }
            return true;
        }

        if (obj instanceof JCheckBox jCheckBox) {
            this.object = jCheckBox;

            JCheckBox ticked = jCheckBox;
            return !ticked.isSelected();
        }

        return false;
    }

    public boolean isRequired(Object obj, String message) {
        this.message = (message == null || message.isEmpty())
                ? "Trường này không được để trống" : message;
        return isRequired(obj);
    }

    public boolean isRequired(Object... objs) {
        this.message = "Trường này không được để trống";
        List<Boolean> isTrue = new ArrayList<>();

        Arrays.asList(objs).forEach(obj -> {
            isTrue.add(isRequired(obj));
        });

        return isTrue.stream().anyMatch(t -> t);
    }

    public boolean isID(JTextField input, String message, String compareID) {
        this.message = (message == null || message.isEmpty())
                ? "This ID is invalid" : message;

        return compareID.equalsIgnoreCase(input.getText());
    }

    public boolean isID(JTextField input, String message, Pattern pattern) {
        this.message = (message == null || message.isEmpty())
                ? "This ID is invalid" : message;

        return pattern.matcher(input.getText()).find();
    }

    public boolean isPassword(JPasswordField password, String message, 
                                String comparePassword) 
    {
        this.message = (message == null || message.isEmpty())
                ? "This password is invalid"
                : message;
        
        String password_value = String.valueOf(password.getPassword());

        return password_value.equalsIgnoreCase(comparePassword);
    }

    public boolean isName(JTextField input, String message) {
        this.message = (message == null || message.isEmpty())
                ? "This name is invalid" : message;
        final Pattern regex = Pattern.compile("^[a-z\\D_-]{6,12}$");

        return regex.matcher(input.getText()).find();
    }

    public boolean isName(JTextField input, String message, String compareName) {
        this.message = (message == null || message.isEmpty())
                ? "This name is invalid" : message;
        final Pattern regex = Pattern.compile("^[a-z\\D_-]{6,12}$");

        return regex.matcher(input.getText()).find()
                && input.getText().equalsIgnoreCase(compareName);
    }

    public boolean isEmail(String email, String message) {
        this.message = message == null || message.isEmpty() 
                ? "Email is invalid" 
                : message;
        return email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
