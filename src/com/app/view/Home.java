package com.app.view;

import com.app.controller.Account;
import com.app.customized.BackgroundImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {

    private final String sourceSlider = "com/app/imgs/background/Banner.png";
    private final Image sliderImgae = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceSlider)
            );

    private final BackgroundImage background = new BackgroundImage(sliderImgae);

    private final String sourceLogo = "com/app/imgs/background/logo_fpoly.png";
    private final Image logoImgae = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceLogo)
            ).getScaledInstance(150, 80, Image.SCALE_SMOOTH);
    final Icon logoFpoly = new ImageIcon(logoImgae);

    final Image logout_img = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource("com/app/imgs/icon/logout-icon.png")
            ).getScaledInstance(35, 35, Image.SCALE_SMOOTH);
    final Icon logout_icon = new ImageIcon(logout_img);

    final Dimension fullscreenSize = Toolkit.getDefaultToolkit().getScreenSize();

    final Font myFont = new Font("Tahoma", Font.BOLD, 18);

    public Home() throws HeadlessException {
        super("Home");
        this.setBackgroundImage();
        this.initUI();
    }

    private void setBackgroundImage() {
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = (int) fullscreenSize.getWidth();
                int height = (int) fullscreenSize.getHeight();
                background.getScaledImage(width, height, Image.SCALE_SMOOTH);
            }
        });
    }

    private void initUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setType(Type.NORMAL);

        this.setMaximumSize(fullscreenSize);
        this.setMinimumSize(new Dimension(800, 800));
        this.setResizable(false);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);

        background.setBackground(Color.white);

        header = new JPanel(new BorderLayout(0, 0));
        header.setBackground(Color.white);

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, 50, 20));

        leftPanel.setOpaque(false);
        logoLabel = new JLabel(logoFpoly);
        logoLabel.setFont(myFont);
        leftPanel.add(logoLabel);

        CBDT = new JButton("Manager");
        CBDT.setFont(myFont);
        CBDT.setFocusPainted(false);
        CBDT.setBorder(null);
        CBDT.setOpaque(false);
        CBDT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CBDT.addActionListener(this);
        leftPanel.add(CBDT);

        SV = new JButton("Student");
        SV.setFont(myFont);
        SV.setFocusPainted(false);
        SV.setBorder(null);
        SV.setOpaque(false);
        SV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SV.addActionListener(this);
        leftPanel.add(SV);

        GV = new JButton("Teacher");
        GV.setFont(myFont);
        GV.setFocusPainted(false);
        GV.setBorder(null);
        GV.setOpaque(false);
        GV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GV.addActionListener(this);
        leftPanel.add(GV);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 50, 45));
        rightPanel.setOpaque(false);

        JButton logoutBtn = new JButton("Log out");

        logoutBtn.setIcon(logout_icon);
        logoutBtn.setFont(myFont);
        logoutBtn.setForeground(Color.blue);
        logoutBtn.setBorder(null);
        logoutBtn.setFocusPainted(false);
        logoutBtn.setOpaque(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        logoutBtn.setVerticalTextPosition(JButton.CENTER);
        logoutBtn.setHorizontalTextPosition(JButton.LEFT);
        logoutBtn.setIconTextGap(20);

        logoutBtn.addActionListener((act) -> logout());

        rightPanel.add(logoutBtn);
        header.add(leftPanel, BorderLayout.WEST);
        header.add(rightPanel, BorderLayout.EAST);

        this.add(header, BorderLayout.NORTH);
        this.add(background, BorderLayout.CENTER);
    }

    private JPanel header;
    private JLabel logoLabel;
    private JButton CBDT, SV, GV;

    @Override
    public void actionPerformed(ActionEvent e) {
        String roleChoose = e.getActionCommand();

        boolean checkRole = roleChoose.equalsIgnoreCase(Account.roleSaved);

        switch (roleChoose) {
            case "Manager" -> {
                EventQueue.invokeLater(() -> {
                    new Manager(checkRole).setVisible(true);
                });
            }

            case "Student" -> {
                EventQueue.invokeLater(() -> {
                    new Student(checkRole).setVisible(true);
                });
            }

            case "Teacher" -> {
                EventQueue.invokeLater(() -> {
                    new Teacher(checkRole).setVisible(true);
                });
            }

            default ->
                throw new AssertionError();
        }
    }

    private void logout() {
        if (Account.isRemembered) {
            EventQueue.invokeLater(() -> {
                new Login(
                        Account.usernameSaved,
                        Account.passwordSaved,
                        Account.isRemembered
                ).setVisible(true);
            });
        } else {
            EventQueue.invokeLater(() -> {
                new Login().setVisible(true);
            });
        }
        
        this.dispose();
    }
}
