/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.src.controller;

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

        if (obj instanceof JTextField) {
            this.object = ((JTextField) obj);

            String value = ((JTextField) obj).getText();
            return value.isEmpty();
        }

        if (obj instanceof JPasswordField) {
            this.object = ((JPasswordField) obj);

            String value = String.valueOf(((JPasswordField) obj).getPassword());
            return value.isEmpty();
        }

        if (obj instanceof JTextArea) {
            this.object = ((JTextArea) obj);

            String value = String.valueOf(((JTextArea) obj).getText());
            return value.isEmpty();
        }

        if (obj instanceof JComboBox) {
            this.object = ((JComboBox) obj);

            JComboBox cbx = ((JComboBox) obj);
            return cbx.getSelectedItem().equals(cbx.getItemAt(0));
        }

        if (obj instanceof ButtonGroup) {
            this.object = ((ButtonGroup) obj);

            ButtonGroup buttonGroup = ((ButtonGroup) obj);
            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
                JRadioButton temp = (JRadioButton) buttons.nextElement();
                if (temp.isSelected()) {
                    return false;
                }
            }
            return true;
        }

        if (obj instanceof JCheckBox) {
            this.object = ((JCheckBox) obj);

            JCheckBox ticked = ((JCheckBox) obj);
            return !ticked.isSelected();
        }

        return false;
    }

    public boolean isRequired(Object obj, String message) {
        this.message = message.isEmpty() ? "Trường này không được để trống" : message;
        return isRequired(obj);
    }

    public boolean isRequired(JCheckBox... selections) {
        List<Boolean> isTrue = new ArrayList<>();

        Arrays.asList(selections).forEach(obj -> {
            isTrue.add(isRequired(obj));
        });

        return isTrue.stream().allMatch(t -> t);
    }

    public boolean isRequired(Object... objs) {
        List<Boolean> isTrue = new ArrayList<>();

        Arrays.asList(objs).forEach(obj -> {
            isTrue.add(isRequired(obj));
        });

        return isTrue.stream().anyMatch(t -> t);
    }

    public boolean isName(JTextField input) {
        final Pattern regex = Pattern.compile("[a-z\\D_-]{3,}$"
                + "|[aàảãáạăằẳẵắặâầẩẫấậbcdđeèẻẽéẹêềểễếệfghiìỉĩíịjklmnoòỏõóọôồổỗốộơờởỡớợpqrstuùủũúụưừửữứựvwxyỳỷỹýỵz_]+");
        Matcher matcher = regex.matcher(input.getText());
        return matcher.find();
    }

    public boolean isPassword(JPasswordField password) {
        String password_value = String.valueOf(password.getPassword());

        if (password_value != null) {
            final String MIN_LENGHT = "8";
            final String MAX_LENGHT = "20";

            final String ONE_DIGIT = "(?=.*[0-9])";
            final String LOWER_CASE = "(?=.*[a-z])";
            final String UPPER_CASE = "(?=.*[A-Z])";
            final String NO_SPACE = "(?=\\S+$)";
            final String MIN_MAX_CHAR = ".{" + MIN_LENGHT + "," + MAX_LENGHT + "}";

            final String PATTERN = ONE_DIGIT + LOWER_CASE + UPPER_CASE
                    + NO_SPACE + MIN_MAX_CHAR;

            return password_value.matches(PATTERN);
        }

        return false;
    }

    public boolean isEmail(String value) {
        final Pattern regex = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");
        Matcher matcher = regex.matcher(value);
        return matcher.find();
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