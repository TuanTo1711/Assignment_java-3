package com.app.gui;

import com.app.controller.Account;
import com.app.customized.BackgroundImage;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Home extends JFrame implements ActionListener {

    private final String sourceSlider = "com/app/imgs/Banner.png";
    private final Image sliderImgae = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceSlider)
            );

    private final BackgroundImage background = new BackgroundImage(sliderImgae);

    private final String sourceLogo = "com/app/imgs/logo_fpoly.png";
    private final Image logoImgae = Toolkit.getDefaultToolkit()
            .getImage(getClass()
                    .getClassLoader()
                    .getResource(sourceLogo)
            ).getScaledInstance(150, 80, Image.SCALE_SMOOTH);
    final Icon logoFpoly = new ImageIcon(logoImgae);

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
                int width = Window.getWindows()[0].getWidth();
                int height = Window.getWindows()[0].getHeight();
                background.getScaledImage(width, height, Image.SCALE_FAST);
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
        this.add(background, BorderLayout.CENTER);

        header = new JPanel(new FlowLayout(FlowLayout.LEADING, 40, 20));
        header.setBackground(Color.white);
        this.add(header, BorderLayout.NORTH);

        logoLabel = new JLabel(logoFpoly);
        logoLabel.setFont(myFont);
        logoLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        header.add(logoLabel);

        CBDT = new JButton("Mangager");
        CBDT.setFont(myFont);
        CBDT.setFocusPainted(false);
        CBDT.setBorder(null);
        CBDT.setOpaque(false);
        CBDT.setCursor(new Cursor(Cursor.HAND_CURSOR));
        CBDT.addActionListener(this);
        header.add(CBDT);

        SV = new JButton("Student");
        SV.setFont(myFont);
        SV.setFocusPainted(false);
        SV.setBorder(null);
        SV.setOpaque(false);
        SV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        SV.addActionListener(this);
        header.add(SV);

        GV = new JButton("Teacher");
        GV.setFont(myFont);
        GV.setFocusPainted(false);
        GV.setBorder(null);
        GV.setOpaque(false);
        GV.setCursor(new Cursor(Cursor.HAND_CURSOR));
        GV.addActionListener(this);
        header.add(GV);
    }

    private JPanel header;
    private JLabel logoLabel;
    private JButton CBDT, SV, GV;

    @Override
    public void actionPerformed(ActionEvent e) {
        Account.roleSaved = e.getActionCommand();
        System.out.println(Account.roleSaved);
        EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
             this.dispose();
        });
    }
}
