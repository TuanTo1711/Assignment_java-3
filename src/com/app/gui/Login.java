package com.app.gui;

import com.app.mycomponent.RoundedPanel;
import com.app.mycomponent.BackgroundImage;
import com.app.mycomponent.GradientButton;
import com.app.controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Login extends JFrame {

    private final String sourceImage = "com/app/imgs/login-bg.jpg";
    private final Image image = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceImage));
    private final BackgroundImage background = new BackgroundImage(image);

    private final Font titleFont = new Font("Poppins-Bold", Font.BOLD, 39);
    private final Font label_font = new Font("SansSerif", Font.PLAIN, 14);
    private final Font input_font = new Font("SansSerif", Font.PLAIN, 16);

    private final Color text_color = new Color(51, 51, 51);
    private final Color bg_color = Color.white;
    private final Color bg_warning = Color.yellow;

    public Login() throws HeadlessException {
        super("Login");
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setBackgroundImage();
        this.initUI();
    }

    private void setBackgroundImage() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                background.getScaledImage(getWidth(), getHeight());
            }

        });

        background.setBorder(new EmptyBorder(130, 0, 0, 0));
    }

    private void initUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setType(Type.POPUP);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setContentPane(background);
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

        // set attribute all components
        formPanel.setBackground(bg_color);
        formPanel.setOpaque(false);
        formPanel.setBorder(new EmptyBorder(65, 55, 54, 55));

        title.setFont(titleFont);
        title.setForeground(text_color);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setText("Login");
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 49, 0));

        userPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 23, 0));
        userPane.setBackground(bg_color);

        userLabel.setFont(label_font);
        userLabel.setForeground(text_color);
        userLabel.setLabelFor(username);
        userLabel.setText("Username");

        username.setFocusable(false);
        username.setPreferredSize(new Dimension(300, 40));
        username.addMouseListener(new MouseAdapterImpl());
        username.addFocusListener(new FocusAdapterImpl());
        username.setFont(input_font);
        username.setForeground(text_color);

        username_error_msg.setPreferredSize(new Dimension(300, 40));
        username_error_msg.setFont(new Font("Verdana", Font.ITALIC, 12));
        username_error_msg.setForeground(Color.red);
        userNameControl();

        passwordPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 23, 0));
        passwordPane.setBackground(bg_color);

        passwordLabel.setFont(label_font);
        passwordLabel.setForeground(text_color);
        passwordLabel.setLabelFor(password);
        passwordLabel.setText("Password");

        password_error_msg.setPreferredSize(new Dimension(300, 40));
        password_error_msg.setFont(new Font("Verdana", Font.ITALIC, 12));
        password_error_msg.setForeground(Color.red);

        password.setFocusable(false);
        password.addMouseListener(new MouseAdapterImpl());
        password.addFocusListener(new FocusAdapterImpl());
        password.setFont(input_font);
        password.setForeground(text_color);

        passWordControl();

        remember.setFont(label_font);
        remember.setText("Remember");
        remember.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        remember.setHorizontalAlignment(SwingConstants.RIGHT);
        remember.setBackground(bg_color);
        remember.setFocusable(false);

        loginBtn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        loginBtn.setFocusable(false);
        loginBtn.setStartColor(new Color(0x00dbde));
        loginBtn.setEndColor(new Color(0xfc00ff));
        loginBtn.setGradientFocus(500);
        loginBtn.setForeground(Color.white);
        loginBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        loginBtn.addActionListener((act) -> login());

        formControl();
        background.add(formPanel);
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
                                .addGap(0, 0, 0)
                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
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
                        //                        .addComponent(rolesPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(loginBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(remember, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                //                                .addComponent(rolesPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                //                                .addGap(0, 0, 0)
                                .addComponent(remember, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
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

//    private ArrayList<> 
    private boolean validation() {
        Validator validate = new Validator();

        boolean usernameSuccessfully = checkValid(username);
        boolean passwordSuccessfully = checkValid(password);

        String userValue = "PS23577";
        String passValue = "123456";

        if (validate.isRequired(username, "Username không được để trống")
                || !validate.isID(username, "Username không trùng khớp", userValue)) {
            username.requestFocus();
            username.setBackground(bg_warning);
            username_error_msg.setText(validate.getMessage());
            usernameSuccessfully = true;
        } else {
            username.setFocusable(false);
            username.setBackground(bg_color);
            username_error_msg.setText(null);
        }

        if (validate.isRequired(password, "Password không được để trống")
                || !validate.isPassword(password,
                        "Password không trùng khớp", passValue)) {
            password.requestFocus();
            password.setBackground(bg_warning);
            password_error_msg.setText(validate.getMessage());
            passwordSuccessfully = true;
        } else {
            password.setFocusable(false);
            password.setBackground(bg_color);
            password_error_msg.setText(null);
        }

        return usernameSuccessfully && passwordSuccessfully;
    }

    private void login() {
        if (validation()) {
        }
    }

    private boolean checkValid(Object username) {
        return true;
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
            System.out.println(e.getSource() == username);
        }

        @Override
        public void focusLost(FocusEvent e) {
            validation();
        }
    }
}
