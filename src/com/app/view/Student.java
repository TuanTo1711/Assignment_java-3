package com.app.view;

import com.app.customized.MyMenuBar;
import javax.swing.*;

public class Student extends JFrame{

    public Student() {
        this.initUI();
    }
    
    public Student(boolean isStudent) {
        if (!isStudent) {
            
        }
    }

    private void initUI() {
        menu = new MyMenuBar();
        this.add(menu);
    }
    
    private JMenuBar menu;
}
