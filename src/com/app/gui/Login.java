package com.app.gui;

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

    public Login() throws HeadlessException {
        super("Login");
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setLocationRelativeTo(null);

        this.setBackgroundImage();
        this.initUI();
        this.setContentPane(background);
    }

    private void setBackgroundImage() {
        ComponentAdapterImpl cmpResized = new ComponentAdapterImpl();
        this.addComponentListener(cmpResized);

        if (cmpResized.resized) {
            background.getScaledImage(this.getWidth(), this.getHeight());
        }

        background.setBorder(new EmptyBorder(120, 0, 0, 0));
    }

    private void initUI() {
        formPanel = new RoundedPanel(20);
        formLayout = new GroupLayout(formPanel);
        title = new JLabel();

        userPane = new JPanel();
        userLayout = new GroupLayout(userPane);
        userLabel = new JLabel();
        username = new JTextField();

        passwordPane = new JPanel();
        passwordLayout = new GroupLayout(passwordPane);
        passwordLabel = new JLabel();
        password = new JPasswordField();

        groupRadioButton = new ButtonGroup();
        rolesPane = new JPanel();
        rolesLayout = new GroupLayout(rolesPane);
        rolesLabel = new JLabel();
        role1 = new JRadioButton();
        role2 = new JRadioButton();
        role3 = new JRadioButton();

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
        username.setPreferredSize(new Dimension(380, 40));
        username.addMouseListener(new MouseAdapterImpl(username));
        username.setFont(input_font);
        username.setForeground(text_color);
        username.setBorder(new CompoundBorder(
                new TitledBorder(""),
                new EmptyBorder(0, 0, 00, 0)
        ));
        userNameControl();

        passwordPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 23, 0));
        passwordPane.setBackground(bg_color);

        passwordLabel.setFont(label_font);
        passwordLabel.setForeground(text_color);
        passwordLabel.setLabelFor(password);
        passwordLabel.setText("Password");

        password.setFocusable(false);
        password.addMouseListener(new MouseAdapterImpl(password));
        password.setFont(input_font);
        password.setForeground(text_color);
//        password.setBorder(new Flat);

        passWordControl();

//        rolesPane.setBackground(bg_color);
//
//        rolesLabel.setFont(label_font);
//        rolesLabel.setForeground(text_color);
//        rolesLabel.setText("Role");
//
//        role1.setFont(label_font);
//        role1.setForeground(text_color);
//        role1.setText("Sinh Viên");
//        role1.setBackground(bg_color);
//        role1.setFocusable(false);
//
//        role2.setFont(label_font);
//        role2.setForeground(text_color);
//        role2.setText("Giáo Viên");
//        role2.setBackground(bg_color);
//        role2.setFocusable(false);
//
//        role3.setFont(label_font);
//        role3.setForeground(text_color);
//        role3.setText("Phòng ÐT");
//        role3.setBackground(bg_color);
//        role3.setFocusable(false);
//
//        groupRadioButton.add(role1);
//        groupRadioButton.add(role2);
//        groupRadioButton.add(role3);
//        rolesControl();

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
        loginBtn.addActionListener((act) -> validation());

        formControl();
        background.add(formPanel, BorderLayout.CENTER);
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
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(userLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(username)
                                .addContainerGap())
        );

        userLayout.setVerticalGroup(
                userLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(userLayout.createSequentialGroup()
                                .addComponent(userLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(username, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
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
        );
        passwordLayout.setVerticalGroup(
                passwordLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(passwordLayout.createSequentialGroup()
                                .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(password, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
        );
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Roles Controller">
    private void rolesControl() {
        rolesPane.setLayout(rolesLayout);
        rolesLayout.setHorizontalGroup(
                rolesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rolesLayout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(rolesLabel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(role1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(role2, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(role3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(3, 3, 3))
        );
        rolesLayout.setVerticalGroup(
                rolesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(rolesLayout.createSequentialGroup()
                                .addGroup(rolesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(rolesLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(role1)
                                        .addComponent(role2)
                                        .addComponent(role3))
                                .addGap(0, 0, Short.MAX_VALUE))
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

    private JPanel formPanel, userPane, passwordPane, rolesPane;
    private JLabel title, userLabel, passwordLabel, rolesLabel;
    private JTextField username;
    private JPasswordField password;
    private JRadioButton role1, role2, role3;
    private ButtonGroup groupRadioButton;
    private JCheckBox remember;
    private GradientButton loginBtn;
    private GroupLayout formLayout, userLayout, passwordLayout, rolesLayout;

    private class ComponentAdapterImpl extends ComponentAdapter {

        boolean resized;

        public ComponentAdapterImpl() {
        }

        @Override
        public void componentResized(ComponentEvent e) {
            resized = true;
            background.getScaledImage(getWidth(), getHeight());
        }
    }

    private class MouseAdapterImpl extends MouseAdapter {

        Object object;
        
        public MouseAdapterImpl(Object obj) {
            this.object = obj;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            
            ((Component)object).setFocusable(true);
            ((Component)object).requestFocus();
        }
    }

    private void validation() {
        Validator validate = new Validator();

        if (validate.isRequired(username)
                || validate.isName(username)) {
            System.out.println(validate.getMessage());
        }

        if (validate.isRequired(password, "Password không được để trống")) {
//            System.out.println(validate.getMessage());
        }
    }
}
