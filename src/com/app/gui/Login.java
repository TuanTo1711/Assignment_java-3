package com.app.gui;

import com.app.customized.*;
import com.app.controller.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.border.*;

public class Login extends JFrame {

    public static final Logger LOG = Logger.getLogger(Login.class.getName());
    private final Dimension fullscreenSize = Toolkit.getDefaultToolkit()
            .getScreenSize();
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

    private final Color bg_color = Color.white;
    private final Color text_color = new Color(51, 51, 51);
    private final Color error_text_color = Color.red;
    private final Color bg_warning = Color.yellow;

    private final Color left_button_color = new Color(0x00dbde);
    private final Color right_button_color = new Color(0xfc00ff);

    private final Validator validator = new Validator();

    public Login() throws HeadlessException {
        super("Login");
        JFrame.setDefaultLookAndFeelDecorated(false);
        this.setBackgroundImage();
        this.initUI();
        this.setAttributeComponents();
        this.setControls();
        this.setListeners();
    }

    public Login(
            final String user,
            final String pwd,
            final boolean isChecked
    ) throws HeadlessException {
        this();
        username.setText(user);
        password.setText(pwd);
        remember.setSelected(isChecked);

        username.setForeground(text_color);
        password.setForeground(text_color);
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

        this.setMinimumSize(new Dimension(800, 800));
        this.setMaximumSize(fullscreenSize);

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
        username.setLayout(new FlowLayout(FlowLayout.RIGHT));
        username.setFocusable(false);
        username.setPreferredSize(new Dimension(380, 40));
        username.setFont(input_font);

        username_error_msg.setFont(error_msg_font);
        username_error_msg.setForeground(error_text_color);
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
        password_error_msg.setForeground(error_text_color);

        password.setFont(input_font);
        password.setFocusable(false);
        password.setForeground(Color.lightGray);

        password.setLayout(new FlowLayout(FlowLayout.RIGHT));
        password.add(showHiddenText(password));

        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Checkbox remember">
        remember.setFont(label_font);
        remember.setText("Remember");

        remember.setFocusable(false);
        remember.setBorder(new EmptyBorder(0, 0, 35, 12));
        remember.setHorizontalAlignment(SwingConstants.RIGHT);
        remember.setBackground(bg_color);
        remember.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // </editor-fold>
        // <editor-fold defaultstate="collapsed" desc="Login button">
        loginBtn.setFont(button_font);
        loginBtn.setFocusable(false);
        loginBtn.setStartColor(left_button_color);
        loginBtn.setEndColor(right_button_color);
        loginBtn.setGradientFocus(500);
        loginBtn.setForeground(Color.white);
        loginBtn.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        // </editor-fold>
        background.add(formPanel);
    }

    private Component showHiddenText(final JPasswordField typePassword) {
        final JButton show = new JButton("Show");
        show.setBorder(null);
        show.setFocusPainted(false);
        show.setOpaque(false);
        show.setCursor(new Cursor(Cursor.HAND_CURSOR));
        show.setFont(label_font);

        final char defaultEchoChar = typePassword.getEchoChar();

        show.addActionListener((act) -> {
            boolean echoCharIsSet = typePassword.echoCharIsSet();
            if (echoCharIsSet) {
                typePassword.setEchoChar((char) 0);
                show.setText("Hidden");
            } else {
                typePassword.setEchoChar(defaultEchoChar);
                show.setText("Show");
            }
        });

        return show;
    }

    private void setListeners() {
        username.addMouseListener(new MouseAdapterImpl());
        username.addFocusListener(new FocusAdapterImpl());

        password.addMouseListener(new MouseAdapterImpl());
        password.addFocusListener(new FocusAdapterImpl());

        this.addMouseListener(new MouseAdapterImpl());
        formPanel.addMouseListener(new MouseAdapterImpl());

        loginBtn.addActionListener((act) -> login());
        loginBtn.addMouseListener(new MouseAdapterImpl());
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

    private boolean isValid(final JTextField text) {
        if (validator.isRequired(text, "Please enter username!")) {
            text.setBackground(bg_warning);
            username_error_msg.setText(validator.getMessage());
            return false;
        }

        if (!validator.isID(text, "Username không hợp lệ",
                Pattern.compile("^(PS|ps){1}\\d{5}$"))) {
            text.setBackground(bg_warning);
            username_error_msg.setText(validator.getMessage());
            return false;
        }

        return true;
    }

    private boolean isValid(final JPasswordField pass) {
        if (validator.isRequired(pass, "Please enter password!")) {
            pass.setBackground(bg_warning);
            password_error_msg.setText(validator.getMessage());
            return false;
        }

        if (!validator.isPassword(pass, "Password must be 6 - 12 characters!",
                Pattern.compile("^.{6,}$"))) {
            pass.setBackground(bg_warning);
            password_error_msg.setText(validator.getMessage());
            return false;
        }

        return true;
    }

    private boolean validation() {
        return isValid(username) && isValid(password);
    }

    private void rememberCheck() {
        if (!Account.isRemembered) {
            Account.usernameSaved = username.getText();
            Account.passwordSaved = String.valueOf(password.getPassword());
            Account.isRemembered = true;
        }
    }

    private void login() {
        // connect to user and password and your database
        Connector connector = new Connector("sa", "123456", "FPL_DaoTao");
        if (validation() && connector.connection()) {
            System.out.println("Connect Server Successfully");
            var dataSQL = connector.getDataFrom("Users", Account.roleSaved);

            String usernameValue = username.getText();
            String passwordValue = String.valueOf(password.getPassword());

            boolean accountValid = dataSQL.stream().anyMatch(row
                    -> row.get(0).equals(usernameValue)
                    && row.get(1).equals(passwordValue)
            );

            if (accountValid) {
                System.out.println("Login Successfully");
                rememberCheck();
                run(Account.roleSaved);
            } else {
                System.out.println("Login Failed"); 
                JOptionPane.showMessageDialog(this, "Account doesn't exists!");
            }
        }
    }

    private void run(final String role) {
        switch (role) {
            case "Teacher" -> {
                EventQueue.invokeLater(() -> {
                    new Teacher().setVisible(true);
                });
            }

            case "Student" -> {
                EventQueue.invokeLater(() -> {
                    new Student().setVisible(true);
                });
            }

            case "Manager" -> {
                EventQueue.invokeLater(() -> {
                    new Manager().setVisible(true);
                });
            }

            default ->
                throw new AssertionError();
        }
        this.dispose();
    }

    private class MouseAdapterImpl extends MouseAdapter {

        public MouseAdapterImpl() {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mouseClicked(e);
            username.setFocusable(true);
            password.setFocusable(true);

            if (e.getSource() == username) {
                username.requestFocus();
            } else if (e.getSource() == password) {
                password.requestFocus();
            } else {
                username.setFocusable(false);
                password.setFocusable(false);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            if (e.getSource() == loginBtn) {
                loginBtn.setStartColor(right_button_color);
                loginBtn.setEndColor(left_button_color);
            }
            super.mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if (e.getSource() == loginBtn) {
                loginBtn.setStartColor(left_button_color);
                loginBtn.setEndColor(right_button_color);
            }
            super.mouseExited(e);
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
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getSource() == username && !isValid(username)) {
                username_error_msg.setText(validator.getMessage());
            }

            if (e.getSource() == password && !isValid(password)) {
                password_error_msg.setText(validator.getMessage());
            }
        }
    }
}
