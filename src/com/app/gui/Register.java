package com.app.gui;

import com.app.customized.*;
import com.app.controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.*;

public class Register extends JFrame {

    final Dimension fullscreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private final String sourceImage = "com/app/imgs/login-bg.jpg";
    private final Image image = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceImage)
            );
    private final BackgroundImage background = new BackgroundImage(image);

    private final Font titleFont = new Font("Consolas", Font.BOLD, 40);
    private final Font label_font = new Font("Monospaced", Font.PLAIN, 14);
    private final Font input_font = new Font("Arial", Font.BOLD, 16);
    private final Font button_font = new Font("Tahoma", Font.BOLD, 18);
    private final Font error_msg_font = new Font("Verdana", Font.ITALIC, 12);

    private final Color text_color = new Color(51, 51, 51);
    private final Color error_color = Color.red;
    private final Color bg_color = Color.white;
    private final Color bg_warning = Color.yellow;

    public Register() throws HeadlessException {
        super("Register");
        JFrame.setDefaultLookAndFeelDecorated(false);
        this.initUI();
        this.setAttributeComponents();
        this.setControls();
        this.setListeners();
    }

    private void setBackgroundImage() {

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = Window.getWindows()[1].getWidth();
                int height = Window.getWindows()[1].getHeight();
                background.getScaledImage(width, height, Image.SCALE_FAST);
                int GAP = (height - formPanel.getHeight()) / 2;
                background.setBorder(new EmptyBorder(GAP, 0, 0, 0));
            }
        });
    }

    private void initUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setType(Window.Type.POPUP);
        background.setBorder(new EmptyBorder(0, 0, 0, 0));
//        this.setSize(new Dimension(800, 800));
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

        confirmPane = new JPanel();
        confirmLayout = new GroupLayout(confirmPane);
        confirmLabel = new JLabel();
        confirm = new JPasswordField();
        confirm_error_msg = new JLabel();

        registerBtn = new GradientButton();
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
        title.setText("Register");
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

        confirmPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 23, 0));
        confirmPane.setBackground(bg_color);

        confirmLabel.setFont(label_font);
        confirmLabel.setForeground(text_color);
        confirmLabel.setLabelFor(confirm);
        confirmLabel.setText("Confirm");

        confirm_error_msg.setPreferredSize(new Dimension(300, 40));
        confirm_error_msg.setFont(error_msg_font);
        confirm_error_msg.setForeground(error_color);

        confirm.setFont(input_font);
        confirm.setFocusable(false);
        confirm.setForeground(Color.lightGray);

        // <editor-fold defaultstate="collapsed" desc="Register button">
        registerBtn.setFont(button_font);
        registerBtn.setText("Register".toUpperCase());
        registerBtn.setFocusable(false);
        registerBtn.setStartColor(new Color(0x00dbde));
        registerBtn.setEndColor(new Color(0xfc00ff));
        registerBtn.setGradientFocus(500);
        registerBtn.setForeground(Color.white);
        registerBtn.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        registerBtn.addActionListener((act) -> register());
        // </editor-fold>
        background.add(formPanel);
    }

    private void setListeners() {
        username.addMouseListener(new MouseAdapterImpl());
        username.addFocusListener(new FocusAdapterImpl());

        password.addMouseListener(new MouseAdapterImpl());
        password.addFocusListener(new FocusAdapterImpl());

        confirm.addMouseListener(new MouseAdapterImpl());
        confirm.addFocusListener(new FocusAdapterImpl());
    }

    private void setControls() {
        userNameControl();
        passWordControl();
        confirmControl();
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

    // <editor-fold defaultstate="collapsed" desc="Confirm Controller">
    private void confirmControl() {
        confirmPane.setLayout(confirmLayout);
        confirmLayout.setHorizontalGroup(
                confirmLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(confirmLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(confirmLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        )
                        .addGroup(confirmLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(confirm)
                                .addContainerGap()
                        )
                        .addGroup(confirmLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(confirm_error_msg)
                                .addContainerGap()
                        )
        );

        confirmLayout.setVerticalGroup(
                confirmLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(confirmLayout.createSequentialGroup()
                                .addComponent(confirmLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(confirm, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(confirm_error_msg, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(confirmPane, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addComponent(confirmPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(registerBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
    }
    // </editor-fold>

    private JPanel formPanel, userPane, passwordPane, confirmPane;
    private JLabel title, userLabel, passwordLabel, confirmLabel;
    private JLabel password_error_msg, username_error_msg, confirm_error_msg;
    private JTextField username;
    private JPasswordField password, confirm;
    private GradientButton registerBtn;
    private GroupLayout formLayout, userLayout, passwordLayout, confirmLayout;
    private final Validator validator = new Validator();

    private void register() {
        System.out.println("Register.Clicked");
    }

    private class MouseAdapterImpl extends MouseAdapter {

        public MouseAdapterImpl() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            username.setFocusable(true);
            password.setFocusable(true);
            confirm.setFocusable(true);

            if (e.getSource() == username) {
                username.requestFocus();
            }

            if (e.getSource() == password) {
                password.requestFocus();
            }

            if (e.getSource() == confirm) {
                confirm.requestFocus();
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
                password.setSelectionStart(0);
                password.setSelectionEnd(Arrays.toString(password.getPassword()).length());
                password_error_msg.setText("");
            }

            if (e.getSource() == confirm) {
                confirm.setBackground(bg_color);
                confirm.setForeground(text_color);
                confirm.setSelectionStart(0);
                confirm.setSelectionEnd(Arrays.toString(confirm.getPassword()).length());
                confirm_error_msg.setText("");
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == username && !checkValid(username)) {
                username_error_msg.setText(validator.getMessage());
            }

            if (e.getSource() == password && !checkValid(password)) {
                password_error_msg.setText(validator.getMessage());
            }

            if (e.getSource() == confirm && !checkValid(confirm)) {
                confirm_error_msg.setText(validator.getMessage());
            }
        }
    }

    private boolean checkValid(JTextField text) {
        if (validator.isRequired(text, "Chưa nhập username")) {
            text.setBackground(bg_warning);
            username_error_msg.setText(validator.getMessage());
            return false;
        }

        if (!validator.isID(text, "Username không hợp lệ",
                Pattern.compile("^(PS|ps){1}\\d{5}$"))) {
            username_error_msg.setText(validator.getMessage());
            text.setBackground(bg_warning);
            return false;
        }

        return true;
    }

    private boolean checkValid(JPasswordField pass) {
        if (validator.isRequired(pass, "Chưa nhập password")) {
            pass.setBackground(bg_warning);
            return false;
        }

        if (!validator.isPassword(pass, "Password không hợp lệ", "123456")) {
            pass.setBackground(bg_warning);
            return false;
        }

        return true;
    }
}
