package main.java.ui;

import main.java.*;
import main.java.ui.customized.Navigation;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;

public class Teacher extends JFrame {

    public static final Logger LOG = Logger.getLogger(Teacher.class.getName());
    private final Dimension FULLSCREEN = Toolkit.getDefaultToolkit()
            .getScreenSize();

    final String[] SEARCH_BY_ = new String[]{
        "Search by", "ID", "Name"
    };

    private final Color bg_color = Color.white;

    public Teacher() throws HeadlessException {
        super("Teacher");
        JFrame.setDefaultLookAndFeelDecorated(false);
        this.initUI();
        this.setupAttribute();
        this.getDefaultData();
        this.setupControls();
        this.setListeners();
    }

    public Teacher(boolean isTeacher) throws HeadlessException {
        this();
        if (!isTeacher) {
            disableController(buttonsControl);
            disableController(infoControl);
            disableController(editorControl);
        }
    }

    private void disableController(JPanel pane) {
        Arrays.asList(pane.getComponents())
                .stream()
                .filter(cmp -> cmp instanceof JButton
                || cmp instanceof JTextField)
                .forEach(item -> item.setEnabled(false));
    }

    private void initUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);

        this.setMinimumSize(new Dimension(900, FULLSCREEN.height));
        this.setMaximumSize(FULLSCREEN);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);

        menu = new Navigation();

        main = new JPanel();
        header = new JPanel();
        content = new JPanel();

        searchPanel = new JPanel();
        infoControl = new JPanel();
        editorControl = new JPanel();
        buttonsControl = new JPanel();
        tablePanel = new JPanel();
        headerTablePane = new JPanel();

        titleForm = new JLabel();

        searchLabel = new JLabel();
        search_input = new JTextField();
        place_holder_search_input = new JLabel();
        searchBtn = new JButton();

        searchOptions = new JComboBox<>();
        tableOptions = new JComboBox<>();
        sortOptions = new JComboBox<>();

        nameLabel = new JLabel();
        IDLabel = new JLabel();
        englishMark = new JLabel();
        ITMark = new JLabel();
        PELabel = new JLabel();
        tableOptionLabel = new JLabel();

        name_input = new JTextField();
        ID_input = new JTextField();
        enMark_input = new JTextField();
        ITMark_input = new JTextField();
        PEMark_input = new JTextField();

        newBtn = new JButton();
        saveBtn = new JButton();
        deleteBtn = new JButton();
        updateBtn = new JButton();

        firstBtn = new JButton();
        nextBtn = new JButton();
        previousBtn = new JButton();
        lastBtn = new JButton();

        studentTable = new JTable();
        model = new DefaultTableModel();
        jsp = new JScrollPane(studentTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

    }

    private void setupAttribute() {
        this.setJMenuBar(menu);
        this.add(main);

        main.setLayout(new BorderLayout(20, 20));
        main.setBackground(Color.white);
        main.add(header, BorderLayout.PAGE_START);
        main.add(content, BorderLayout.CENTER);

        main.setBorder(new EmptyBorder(50, 50, 50, 50));

        header.setLayout(new BorderLayout(20, 20));
        header.setOpaque(false);

        header.add(titleForm, BorderLayout.CENTER);
        header.add(searchPanel, BorderLayout.SOUTH);

        titleForm.setText("Student Score Management");
        titleForm.setFont(FONTS.GLOBAL_TITLE);
        titleForm.setForeground(Color.red);
        titleForm.setHorizontalAlignment(JLabel.CENTER);

        content.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 20));
        content.setOpaque(false);
        content.add(infoControl);
        content.add(tablePanel);
        content.add(editorControl);

        //<editor-fold defaultstate="collapsed" desc=" Header search bar ">
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
        searchPanel.setOpaque(false);
        searchPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        searchPanel.add(searchLabel);
        searchPanel.add(search_input);
        searchPanel.add(searchOptions);

        searchLabel.setFont(FONTS.TEACHER_LABEL);
        searchLabel.setText("Seacrh: ");
        searchLabel.setForeground(COLORS.TEACHER_TEXT);

        search_input.setLayout(new FlowLayout(FlowLayout.LEFT, 0, -2));
        search_input.setMinimumSize(new Dimension(100, 30));
        search_input.setPreferredSize(new Dimension(300, 30));
        search_input.setFont(FONTS.TEACHER_INPUT);

        place_holder_search_input.setText("Type something...");
        place_holder_search_input.setOpaque(false);
        place_holder_search_input.setFont(new Font("Open Sans", Font.ITALIC, 18));
        place_holder_search_input.setPreferredSize(new Dimension(260, 30));
        place_holder_search_input.setForeground(Color.lightGray);

        search_input.add(place_holder_search_input);
        search_input.add(searchBtn);

        searchBtn.setIcon(new ImageIcon(IMAGES.FORM_HEADER_SEARCH));
        searchBtn.setFocusable(false);
        searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchBtn.setFocusPainted(false);
        searchBtn.setBorder(null);
        searchBtn.setOpaque(false);

        searchOptions.setPreferredSize(new Dimension(120, 30));
        searchOptions.setFocusable(false);
        searchOptions.setFont(FONTS.TEACHER_LABEL);
        for (String item : SEARCH_BY_) {
            searchOptions.addItem(item);
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Main content ">
        infoControl.setOpaque(false);
        infoControl.setBorder(new CompoundBorder(
                new TitledBorder(
                        null, "INFOMATION",
                        TitledBorder.LEADING,
                        TitledBorder.ABOVE_TOP,
                        new Font("Consolas", Font.PLAIN, 15),
                        COLORS.TEACHER_TEXT
                ),
                new EmptyBorder(5, 5, 5, 5))
        );

        nameLabel.setText("Name: ");
        nameLabel.setFont(FONTS.TEACHER_LABEL);
        nameLabel.setForeground(COLORS.TEACHER_TEXT);
        nameLabel.setHorizontalAlignment(JLabel.LEFT);

        name_input.setText("To Hoang Tuan");
        name_input.setPreferredSize(new Dimension(370, 30));
        name_input.setForeground(COLORS.TEACHER_TEXT);
        name_input.setFont(FONTS.TEACHER_INPUT);
        name_input.setBorder(null);
        name_input.setOpaque(false);
        name_input.setEditable(false);
        name_input.setFocusable(false);

        IDLabel.setText("ID: ");
        IDLabel.setFont(FONTS.TEACHER_LABEL);
        IDLabel.setForeground(COLORS.TEACHER_TEXT);
        IDLabel.setHorizontalAlignment(JLabel.LEFT);

        ID_input.setText("");
        ID_input.setPreferredSize(new Dimension(370, 30));
        ID_input.setForeground(COLORS.TEACHER_TEXT);
        ID_input.setFont(FONTS.TEACHER_INPUT);

        englishMark.setText("English score: ");
        englishMark.setFont(FONTS.TEACHER_LABEL);
        englishMark.setForeground(COLORS.TEACHER_TEXT);
        englishMark.setHorizontalAlignment(JLabel.LEFT);

        enMark_input.setText("");
        enMark_input.setPreferredSize(new Dimension(370, 30));

        enMark_input.setForeground(COLORS.TEACHER_TEXT);
        enMark_input.setFont(FONTS.TEACHER_INPUT);

        ITMark.setText("IT score: ");
        ITMark.setFont(FONTS.TEACHER_LABEL);
        ITMark.setForeground(COLORS.TEACHER_TEXT);
        ITMark.setHorizontalAlignment(JLabel.LEFT);

        ITMark_input.setText("");
        ITMark_input.setPreferredSize(new Dimension(370, 30));

        ITMark_input.setForeground(COLORS.TEACHER_TEXT);
        ITMark_input.setFont(FONTS.TEACHER_INPUT);

        PELabel.setText("PE score: ");
        PELabel.setFont(FONTS.TEACHER_LABEL);
        PELabel.setForeground(COLORS.TEACHER_TEXT);
        PELabel.setHorizontalAlignment(JLabel.LEFT);

        PEMark_input.setText("");
        PEMark_input.setPreferredSize(new Dimension(370, 30));
        PEMark_input.setForeground(COLORS.TEACHER_TEXT);
        PEMark_input.setFont(FONTS.TEACHER_INPUT);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Editor Control ">
        editorControl.setLayout(new FlowLayout(FlowLayout.LEFT, 35, 20));
        editorControl.add(newBtn);
        editorControl.add(saveBtn);
        editorControl.add(deleteBtn);
        editorControl.add(updateBtn);

        editorControl.setOpaque(false);
        editorControl.setBorder(new CompoundBorder(
                new TitledBorder(
                        null, "Edit",
                        TitledBorder.LEFT,
                        TitledBorder.ABOVE_TOP,
                        new Font("Consolas", Font.PLAIN, 15),
                        COLORS.TEACHER_TEXT
                ),
                new EmptyBorder(0, 0, 0, 0))
        );

        newBtn.setText("New");

        newBtn.setPreferredSize(new Dimension(100, 40));
        newBtn.setFont(FONTS.TEACHER_BUTTON);
        newBtn.setFocusable(false);
        newBtn.setFocusPainted(false);
        newBtn.setBorder(null);
        newBtn.setBackground(new Color(0xf6f7f8));
        newBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        saveBtn.setText("Save");
        saveBtn.setPreferredSize(new Dimension(100, 40));
        saveBtn.setFont(FONTS.TEACHER_BUTTON);
        saveBtn.setFocusable(false);
        saveBtn.setFocusPainted(false);
        saveBtn.setBorder(null);
        saveBtn.setBackground(new Color(0xf6f7f8));
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        deleteBtn.setText("Delete");
        deleteBtn.setPreferredSize(new Dimension(100, 40));
        deleteBtn.setFont(FONTS.TEACHER_BUTTON);
        deleteBtn.setFocusable(false);
        deleteBtn.setFocusPainted(false);
        deleteBtn.setBorder(null);
        deleteBtn.setBackground(new Color(0xf6f7f8));
        deleteBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        updateBtn.setText("Update");
        updateBtn.setPreferredSize(new Dimension(100, 40));
        updateBtn.setFont(FONTS.TEACHER_BUTTON);
        updateBtn.setFocusable(false);
        updateBtn.setFocusPainted(false);
        updateBtn.setBorder(null);
        updateBtn.setBackground(new Color(0xf6f7f8));
        updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Button FUNC Control ">
        buttonsControl.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 15));
        buttonsControl.setOpaque(false);

        firstBtn.setIcon(new ImageIcon(IMAGES.FORM_FIRST));
        firstBtn.setFont(FONTS.TEACHER_BUTTON);
        firstBtn.setFocusable(false);
        firstBtn.setFocusPainted(false);
        firstBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        firstBtn.setBorder(null);
        firstBtn.setOpaque(false);

        previousBtn.setIcon(new ImageIcon(IMAGES.FORM_PREVIOUS));
        previousBtn.setFont(FONTS.TEACHER_BUTTON);
        previousBtn.setFocusable(false);
        previousBtn.setFocusPainted(false);
        previousBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        previousBtn.setBorder(null);
        previousBtn.setOpaque(false);

        nextBtn.setIcon(new ImageIcon(IMAGES.FORM_NEXT));
        nextBtn.setFont(FONTS.TEACHER_BUTTON);
        nextBtn.setFocusable(false);
        nextBtn.setFocusPainted(false);
        nextBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nextBtn.setBorder(null);
        nextBtn.setOpaque(false);
        nextBtn.setMargin(new Insets(0, 0, 0, 15));

        lastBtn.setIcon(new ImageIcon(IMAGES.FORM_LAST));
        lastBtn.setFont(FONTS.TEACHER_BUTTON);
        lastBtn.setFocusable(false);
        lastBtn.setFocusPainted(false);
        lastBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lastBtn.setBorder(null);
        lastBtn.setOpaque(false);
        lastBtn.setMargin(new Insets(0, 0, 0, 15));

        buttonsControl.add(firstBtn);
        buttonsControl.add(previousBtn);
        buttonsControl.add(nextBtn);
        buttonsControl.add(lastBtn);
        //</editor-fold>

        tablePanel.setLayout(new BorderLayout(0, 25));
        tablePanel.setBackground(bg_color);

        JPanel leftPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPane.setBackground(bg_color);
        leftPane.add(tableOptionLabel);
        tableOptionLabel.setFont(FONTS.TEACHER_LABEL);
        tableOptionLabel.setText("Option: ???");

        JPanel rightPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPane.setBackground(bg_color);
        sortOptions.setFont(FONTS.TEACHER_LABEL);
        sortOptions.setFocusable(false);
        sortOptions.addItem("Sort by: ???");
        rightPane.add(sortOptions);

        headerTablePane.setLayout(new GridLayout(1, 2));
        headerTablePane.add(leftPane);
        headerTablePane.add(rightPane);

        tablePanel.add(headerTablePane, BorderLayout.NORTH);
        tablePanel.add(jsp, BorderLayout.CENTER);

        studentTable.setForeground(COLORS.TEACHER_TEXT);
        studentTable.setFont(new Font("Arial", Font.PLAIN, 14));
        studentTable.getTableHeader().setFont(new Font("Calibri", Font.BOLD, 16));
        studentTable.getTableHeader().setReorderingAllowed(false);
        studentTable.setBorder(BorderFactory.createLineBorder(COLORS.TEACHER_TEXT));
        studentTable.setRowHeight(25);
        studentTable.setPreferredSize(new Dimension(600, 300));
        studentTable.setFocusable(false);
        studentTable.setFillsViewportHeight(true);
        studentTable.setPreferredScrollableViewportSize(studentTable.getPreferredSize());
    }

    private void getDefaultData() {
        model.setRowCount(0);
        String[] columns = new String[]{"ID", "Name", "English", "IT", "PE", "AVG"};
        String[][] rows = new String[][]{
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"},
            {"PS23577", "Tô Hoàng Tuấn", "7", "10", "7", "8"}
        };
        model.setDataVector(rows, columns);
        studentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        studentTable.setModel(model);
    }

    private void setupControls() {
        infoControl.setLayout(new GridBagLayout());
        GridBagConstraints infoGBC = new GridBagConstraints();
        infoGBC.ipadx = 5;
        infoGBC.fill = GridBagConstraints.HORIZONTAL;
        infoGBC.insets = new Insets(10, 10, 10, 10);

        infoGBC.gridx = 0;
        infoGBC.gridy = 0;
        infoControl.add(nameLabel, infoGBC);
        infoGBC.gridx = 1;
        infoControl.add(name_input, infoGBC);

        infoGBC.gridx = 0;
        infoGBC.gridy = 1;
        infoControl.add(IDLabel, infoGBC);
        infoGBC.gridx = 1;
        infoControl.add(ID_input, infoGBC);

        infoGBC.gridx = 0;
        infoGBC.gridy = 2;
        infoControl.add(englishMark, infoGBC);
        infoGBC.gridx = 1;
        infoControl.add(enMark_input, infoGBC);

        infoGBC.gridx = 0;
        infoGBC.gridy = 3;
        infoControl.add(ITMark, infoGBC);
        infoGBC.gridx = 1;
        infoControl.add(ITMark_input, infoGBC);

        infoGBC.gridx = 0;
        infoGBC.gridy = 4;
        infoControl.add(PELabel, infoGBC);
        infoGBC.gridx = 1;
        infoControl.add(PEMark_input, infoGBC);

        infoGBC.gridx = 1;
        infoGBC.gridy = 5;
        infoControl.add(buttonsControl, infoGBC);
    }

    private void setListeners() {
        search_input.addKeyListener(new KeyAdapterImpl("Type something..."));
    }
    private JMenuBar menu;

    private JPanel main, header, content, searchPanel,
            infoControl, editorControl, buttonsControl,
            tablePanel, headerTablePane;
    private JLabel titleForm, searchLabel, place_holder_search_input,
            nameLabel, IDLabel, englishMark, ITMark, PELabel,
            tableOptionLabel;
    private JButton searchBtn, newBtn, saveBtn, deleteBtn, updateBtn,
            firstBtn, nextBtn, previousBtn, lastBtn;
    private JTextField search_input, name_input, ID_input,
            enMark_input, ITMark_input, PEMark_input;
    private JComboBox<String> searchOptions, tableOptions, sortOptions;
    private JTable studentTable;
    JScrollPane jsp;
    private DefaultTableModel model;

    private class KeyAdapterImpl extends KeyAdapter {

        String pl_text;

        public KeyAdapterImpl() {
        }

        public KeyAdapterImpl(String pl_text) {
            this.pl_text = pl_text;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            String value = search_input.getText();
            if (value.isEmpty()) {
                place_holder_search_input.setText(this.pl_text);
                return;
            }
            place_holder_search_input.setText(null);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            super.keyReleased(e);
            String value = search_input.getText();
            if (value.isEmpty()) {
                place_holder_search_input.setText(this.pl_text);
                return;
            }
            place_holder_search_input.setText(null);
        }
    }
}
