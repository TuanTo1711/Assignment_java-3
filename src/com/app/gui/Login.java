package com.app.gui;

import com.app.resource.component.RoundedPanel;
import com.app.resource.component.BackgroundImage;
import com.app.resource.component.GradientButton;
import com.app.controller.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.*;

public class Login extends JFrame {

    private final String sourceImage = "com/app/imgs/login-bg.jpg";
    private final Image image = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceImage));
    private final BackgroundImage background = new BackgroundImage(image);

    private final Font titleFont = new Font("Calibri", Font.BOLD, 40);
    private final Font input_font = new Font("Tahoma", Font.PLAIN, 16);
    private final Font label_font = new Font("Tahoma", Font.PLAIN, 14);
    private final Font error_msg_font = new Font("Verdana", Font.ITALIC, 12);

    private final Color text_color = new Color(51, 51, 51);
    private final Color error_color = Color.red;
    private final Color bg_color = Color.white;
    private final Color bg_warning = Color.yellow;

    public Login() throws HeadlessException {
        super("Login");
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.initUI();
        this.setAttributeComponents();
        this.setControls();
        this.setListeners();
    }

    private void setBackgroundImage() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.getScaledImage(getWidth(), getHeight());
                background.setBorder(new EmptyBorder(130, 0, 0, 0));
            }
        });
    }

    private void initUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setType(Type.POPUP);

        final Dimension fullscreenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setMinimumSize(new Dimension(800, 800));
        this.setMaximumSize(fullscreenSize);

        this.setBackgroundImage();
        this.setContentPane(background);
        this.setLocationRelativeTo(null);

        formPanel = new RoundedPanel(20);
        formLayout = new GroupLayout(formPanel);

        title = new JLabel();

        userPane = new JPanel();
        userLayout = new GroupLayout(userPane);
        userLabel = new JLabel();
        username = new JTextField();
        username_error_msg = new JLabel();

        passwordPane = new JPanel();
        passwordLayout = new GroupLayout(passwordPane);
        passwordLabel = new JLabel();
        password = new JPasswordField();
        password_error_msg = new JLabel();

        remember = new JCheckBox();

        loginBtn = new GradientButton("LOGIN");
    }

    private void setAttributeComponents() {
        // set attribute for all components
        formPanel.setBackground(bg_color);
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(65, 55, 54, 55));
        // <editor-fold defaultstate="collapsed" desc="Tilte Form">
        title.setFont(titleFont);
        title.setForeground(text_color);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Login");
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 49, 0));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Username">
        userPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 23, 0));
        userPane.setBackground(bg_color);

        userLabel.setFont(label_font);
        userLabel.setForeground(text_color);
        userLabel.setLabelFor(username);
        userLabel.setText("Username");

        username.setForeground(Color.lightGray);
        username.setFocusable(false);
        username.setPreferredSize(new Dimension(380, 40));
        username.setFont(input_font);

        username_error_msg.setFont(error_msg_font);
        username_error_msg.setForeground(error_color);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Password">
        passwordPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 23, 0));
        passwordPane.setBackground(bg_color);

        passwordLabel.setFont(label_font);
        passwordLabel.setForeground(text_color);
        passwordLabel.setLabelFor(password);
        passwordLabel.setText("Password");

        password_error_msg.setPreferredSize(new Dimension(300, 40));
        password_error_msg.setFont(error_msg_font);
        password_error_msg.setForeground(error_color);

        password.setFont(input_font);
        password.setFocusable(false);
        password.setForeground(Color.lightGray);
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Checkbox remember">
        remember.setFont(label_font);
        remember.setText("Remember");
        remember.setFocusPainted(false);
        remember.setFocusable(false);
        remember.setBorder(new EmptyBorder(0, 0, 35, 12));
        remember.setHorizontalAlignment(SwingConstants.RIGHT);
        remember.setBackground(bg_color);
        remember.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Login button">
        loginBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        loginBtn.setFocusable(false);
        loginBtn.setStartColor(new Color(0x00dbde));
        loginBtn.setEndColor(new Color(0xfc00ff));
        loginBtn.setGradientFocus(500);
        loginBtn.setForeground(Color.white);
        loginBtn.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        // </editor-fold>
        background.add(formPanel);
    }

    private void setListeners() {
        username.addMouseListener(new MouseAdapterImpl());
        username.addFocusListener(new FocusAdapterImpl());

        password.addMouseListener(new MouseAdapterImpl());
        password.addFocusListener(new FocusAdapterImpl());

        loginBtn.addActionListener((act) -> login());
    }

    private void setControls() {
        userNameControl();
        passWordControl();
        formControl();
    }

    /* Put components with group by GroupLayout*/
    // <editor-fold defaultstate="collapsed" desc="Username Controller">
    private void userNameControl() {
        userPane.setLayout(userLayout);
        userLayout.setHorizontalGroup(
                userLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(userLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(username)
                                .addContainerGap()
                        )
                        .addGroup(userLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(username_error_msg)
                                .addContainerGap()
                        )
        );

        userLayout.setVerticalGroup(
                userLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userLayout.createSequentialGroup()
                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(username, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(username_error_msg, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        );
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Password Controller">
    private void passWordControl() {
        passwordPane.setLayout(passwordLayout);
        passwordLayout.setHorizontalGroup(
                passwordLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(passwordLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(passwordLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(password)
                                .addContainerGap())
                        .addGroup(passwordLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(password_error_msg)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        passwordLayout.setVerticalGroup(
                passwordLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(passwordLayout.createSequentialGroup()
                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(password_error_msg, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
        );
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Form Controller">
    private void formControl() {
        formPanel.setLayout(formLayout);
        formLayout.setHorizontalGroup(
                formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(title, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(userPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(GroupLayout.Alignment.TRAILING,
                                formLayout.createSequentialGroup()
                                        .addComponent(remember))
                        .addComponent(loginBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        formLayout.setVerticalGroup(
                formLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(formLayout.createSequentialGroup()
                                .addComponent(title, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(userPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(passwordPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(remember, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }
    // </editor-fold>

    private JPanel formPanel, userPane, passwordPane;
    private JLabel title, userLabel, passwordLabel;
    private JLabel password_error_msg, username_error_msg;
    private JTextField username;
    private JPasswordField password;
    private JCheckBox remember;
    private GradientButton loginBtn;
    private GroupLayout formLayout, userLayout, passwordLayout;
    private final Validator validate = new Validator();

//    private ArrayList<> 
    private boolean validation() {
        return checkValid(username) && checkValid(password);
    }

    private void login() {
        Connector conn = new Connector("sa", "tuan2022", "FPL_DaoTao");
        if (validation() && conn.connect()) {
            System.out.println("Login Successfully");
            // new JFrame
        } else {
            System.out.println("Login Failed");
        }
    }

    private class MouseAdapterImpl extends MouseAdapter {

        public MouseAdapterImpl() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            username.setFocusable(true);
            password.setFocusable(true);

            if (e.getSource() == username) {
                username.requestFocus();
            } else if (e.getSource() == password) {
                password.requestFocus();
            }
        }
    }

    private class FocusAdapterImpl extends FocusAdapter {

        public FocusAdapterImpl() {
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getSource() == username) {
                username.setBackground(bg_color);
                username.setForeground(text_color);
                username.setSelectionStart(0);
                username.setSelectionEnd(username.getText().length());
                username_error_msg.setText("");
            }

            if (e.getSource() == password) {
                password.setBackground(bg_color);
                password.setForeground(text_color);
                if (String.valueOf(password.getPassword()).isEmpty()) {
                    password.setSelectionStart(0);
                    password.setSelectionEnd(String.valueOf(password.getPassword()).length());
                }
                password_error_msg.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            var c = e.getSource() == username ? checkValid(username) 
                    : checkValid(password);
        }
    }

    private boolean checkValid(JTextField text) {
        if (validate.isRequired(text, "Chưa nhập username")) {
            text.setBackground(bg_warning);
            username_error_msg.setText(validate.getMessage());
            return true;
        }

        if (!validate.isID(text, "Username không hợp lệ",
                Pattern.compile("^(PS|ps){1}\\d{5}$"))) {
            username_error_msg.setText(validate.getMessage());
            text.setBackground(bg_warning);
            return true;
        }
        
        return false;
    }

    private boolean checkValid(JPasswordField pass) {
        if (validate.isRequired(pass, "Chưa nhập password")) {
            pass.setBackground(bg_warning);
            password_error_msg.setText(validate.getMessage());
            return true;
        }

        if (!validate.isPassword(pass, "Password không hợp lệ", "123456")) {
            pass.setBackground(bg_warning);
            password_error_msg.setText(validate.getMessage());
            return true;
        }

        return false;
    }
}
