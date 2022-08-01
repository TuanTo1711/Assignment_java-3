package com.app.customized;

import com.app.controller.FileInterface;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class MyMenuBar extends JMenuBar implements ActionListener, FileInterface {

    private JMenu fileMenu, editMenu, helpMenu;
    private JMenuItem openFile, saveFile, saveAsFile, printFile, closeFile, exitFile;
    private JMenuItem undoEdit, redoEdit, copyEdit, cutEdit, pasteEdit, deleteEdit;
    private JMenuItem startPage, aboutMe, contactMe;

    public MyMenuBar() {
        this.initMenuBar();
        this.setAltMenuItem();
        this.putIcons();
    }

    private void setAltMenuItem() {
        // file menu
        openFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
                InputEvent.CTRL_DOWN_MASK));
        saveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK));
        saveAsFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
        printFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                InputEvent.CTRL_DOWN_MASK));
        closeFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W,
                InputEvent.CTRL_DOWN_MASK));
        exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                InputEvent.CTRL_DOWN_MASK));
        // edit menu
        undoEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
                InputEvent.CTRL_DOWN_MASK));
        redoEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
                InputEvent.CTRL_DOWN_MASK));
        copyEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
                InputEvent.CTRL_DOWN_MASK));
        cutEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
                InputEvent.CTRL_DOWN_MASK));
        pasteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
                InputEvent.CTRL_DOWN_MASK));
        deleteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE,
                InputEvent.SHIFT_DOWN_MASK));
    }

    private void putIcons() {
        final String openIconSrc = "com/app/imgs/icon/menubar/open_file-icon.png";
        Icon openIcon = getItemIcon(openIconSrc);
        openFile.setIcon(openIcon);

        final String saveIconSrc = "com/app/imgs/icon/menubar/save_file-icon.png";
        Icon saveIcon = getItemIcon(saveIconSrc);
        saveFile.setIcon(saveIcon);

        final String saveAsIconSrc = "com/app/imgs/icon/menubar/save_as_file-icon.png";
        Icon saveAsIcon = getItemIcon(saveAsIconSrc);
        saveAsFile.setIcon(saveAsIcon);

        final String printIconSrc = "com/app/imgs/icon/menubar/print_file-icon.png";
        Icon printIcon = getItemIcon(printIconSrc);
        printFile.setIcon(printIcon);

        final String closeIconSrc = "com/app/imgs/icon/menubar/close_file-icon.png";
        Icon closeIcon = getItemIcon(closeIconSrc);
        closeFile.setIcon(closeIcon);

        final String exitIconSrc = "com/app/imgs/icon/menubar/exit_file-icon.png";
        Icon exitIcon = getItemIcon(exitIconSrc);
        exitFile.setIcon(exitIcon);

        final String undoIconSrc = "com/app/imgs/icon/menubar/undo_edit-icon.png";
        Icon undoIcon = getItemIcon(undoIconSrc);
        undoEdit.setIcon(undoIcon);

        final String redoIconSrc = "com/app/imgs/icon/menubar/redo_edit-icon.png";
        Icon redoIcon = getItemIcon(redoIconSrc);
        redoEdit.setIcon(redoIcon);

        final String copyIconSrc = "com/app/imgs/icon/menubar/copy_edit-icon.png";
        Icon copyIcon = getItemIcon(copyIconSrc);
        copyEdit.setIcon(copyIcon);

        final String cutIconSrc = "com/app/imgs/icon/menubar/cut_edit-icon.png";
        Icon cutIcon = getItemIcon(cutIconSrc);
        cutEdit.setIcon(cutIcon);

        final String pasteIconSrc = "com/app/imgs/icon/menubar/paste_edit-icon.png";
        Icon pasteIcon = getItemIcon(pasteIconSrc);
        pasteEdit.setIcon(pasteIcon);

        final String deleteIconSrc = "com/app/imgs/icon/menubar/delete_edit-icon.png";
        Icon deleteIcon = getItemIcon(deleteIconSrc);
        deleteEdit.setIcon(deleteIcon);

        final String homeHelpSrc = "com/app/imgs/icon/menubar/home_help-icon.png";
        Icon homeHelp = getItemIcon(homeHelpSrc);
        startPage.setIcon(homeHelp);

        final String contactSrc = "com/app/imgs/icon/menubar/contact_help-icon.png";
        Icon contact = getItemIcon(contactSrc);
        contactMe.setIcon(contact);

        final String aboutSrc = "com/app/imgs/icon/menubar/about_help-icon.png";
        Icon about = getItemIcon(aboutSrc);
        aboutMe.setIcon(about);
    }

    private Icon getItemIcon(final String src) {
        return new ImageIcon(Toolkit.getDefaultToolkit()
                .getImage(getClass()
                        .getClassLoader()
                        .getResource(src)
                ).getScaledInstance(25, 25, Image.SCALE_SMOOTH)
        );
    }

    private void initMenuBar() {
        fileMenu = new JMenu("File");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");
        saveAsFile = new JMenuItem("Save As...");
        printFile = new JMenuItem("Print");
        closeFile = new JMenuItem("Close");
        exitFile = new JMenuItem("Exit");

        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.add(printFile);
        fileMenu.add(closeFile);
        fileMenu.add(exitFile);
        fileMenu.setMnemonic(KeyEvent.VK_F);
        this.add(fileMenu);

        editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);

        undoEdit = new JMenuItem("Undo");
        redoEdit = new JMenuItem("Redo");
        copyEdit = new JMenuItem("Copy");
        cutEdit = new JMenuItem("Cut");
        pasteEdit = new JMenuItem("Paste");
        deleteEdit = new JMenuItem("Delete");

        editMenu.add(undoEdit);
        editMenu.add(redoEdit);
        editMenu.add(copyEdit);
        editMenu.add(cutEdit);
        editMenu.add(pasteEdit);
        editMenu.add(deleteEdit);
        this.add(editMenu);

        helpMenu = new JMenu("Help");
        startPage = new JMenuItem("Start Page");
        aboutMe = new JMenuItem("About Me");
        contactMe = new JMenuItem("Contact Me");
        helpMenu.add(startPage);
        helpMenu.add(aboutMe);
        helpMenu.add(contactMe);
        this.add(helpMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String item = e.getActionCommand();

        switch (item) {
            case "Open" ->
                openFile();
            case "Save" ->
                saveFile();
            case "Save As..." ->
                saveAsFile();
            case "Print" ->
                printFile();
            case "Close" ->
                closesFile();
            case "Exit" ->
                exit();
            default ->
                throw new AssertionError();
        }
    }

    private void exit() {
        System.exit(0);
    }

    @Override
    public void openFile() {
        JFileChooser fc = new JFileChooser();

        fc.setCurrentDirectory(new File("."));
        int res = fc.showOpenDialog(null);

        if (res == JFileChooser.APPROVE_OPTION) {
            File path = fc.getSelectedFile().getAbsoluteFile();
            try {
                readFile(path);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyMenuBar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void saveAsFile() {
    }

    @Override
    public void saveFile() {
    }

    @Override
    public void printFile() {
    }

    @Override
    public void closesFile() {
    }

    private void readFile(File path) throws FileNotFoundException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));

        try {
            while ((line = reader.readLine()) != null) {

            }
        } catch (IOException ex) {
        }
    }
}
