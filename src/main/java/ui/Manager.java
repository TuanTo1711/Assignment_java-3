package main.java.ui;

import main.java.ui.customized.Navigation;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import main.java.COLORS;
import main.java.FONTS;
import main.java.IMAGES;

public class Manager extends JFrame {

    public static final Logger LOG = Logger.getLogger(Teacher.class.getName());
    private final Dimension FULLSCREEN = Toolkit.getDefaultToolkit()
            .getScreenSize();

    final String[] SEARCH_BY_ = new String[]{
        "Search by", "ID", "Name"
    };
    
    public Manager() throws HeadlessException {
        super("Teacher");
        JFrame.setDefaultLookAndFeelDecorated(false);
        this.initUI();
        this.setupFrame();
        this.setLocationRelativeTo(null);
    }

    public Manager(boolean isManager) throws HeadlessException {
        this();
        if (!isManager) {
            content.setEnabledComponent(false);
        }
    }

    private void initUI() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        this.setMinimumSize(new Dimension(900, FULLSCREEN.height));
        this.setMaximumSize(FULLSCREEN);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        menu = new Navigation();

        main = new JPanel();
        header = new Header();
        content = new Content();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
    }

    private void setupFrame() {
        this.setJMenuBar(menu);
        this.add(main);

        main.setLayout(new BorderLayout(0, 0));
        main.setBackground(Color.white);
        main.add(header, BorderLayout.NORTH);
        main.add(content, BorderLayout.CENTER);
    }

    private JPanel main;
    private Navigation menu;
    private Header header;
    private Content content;

    final class Header extends JPanel {

//        private final Font FONTS.MANAGER_LABEL_HEADER = new Font("Roboto", Font.PLAIN, 14);
//        private final Font FONTS.MANAGER_INPUT_HEADER = new Font("Tahoma", Font.PLAIN, 16);

        public Header() {
            this.initHeader();
            this.setup();
            this.listen();
        }

        private void initHeader() {
            title = new JLabel();
            search_label = new JLabel();
            place_holder_search = new JLabel();
            search_pane = new JPanel();
            search_input = new JTextField();
            searchBtn = new JButton();
            searchOptions = new JComboBox<>();
        }

        private void setup() {
            this.setLayout(new BorderLayout(10, 10));
            this.setBorder(new EmptyBorder(20, 0, 20, 0));
            title.setHorizontalAlignment(JLabel.CENTER);
            title.setText("Student Management");
            title.setFont(FONTS.GLOBAL_TITLE);
            title.setForeground(Color.red);
            title.setHorizontalAlignment(JLabel.CENTER);

            search_pane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 5));
            search_pane.setOpaque(false);
            search_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

            search_pane.add(search_label);
            search_pane.add(search_input);

            search_label.setFont(FONTS.MANAGER_LABEL_HEADER);
            search_label.setText("Seacrh: ");
            search_label.setForeground(COLORS.MANAGER_TEXT);

            search_input.setLayout(new FlowLayout(FlowLayout.LEFT, 0, -2));
            search_input.setMinimumSize(new Dimension(100, 30));
            search_input.setPreferredSize(new Dimension(300, 30));
            search_input.setFont(FONTS.MANAGER_INPUT_HEADER);

            place_holder_search.setText("Type something...");
            place_holder_search.setOpaque(false);
            place_holder_search.setFont(FONTS.MANAGER_INPUT_HEADER);
            place_holder_search.setPreferredSize(new Dimension(260, 30));
            place_holder_search.setForeground(Color.lightGray);

            search_input.add(place_holder_search);
            search_input.add(searchBtn);
            
            searchBtn.setIcon(new ImageIcon(IMAGES.FORM_HEADER_SEARCH));
            searchBtn.setFocusable(false);
            searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            searchBtn.setFocusPainted(false);
            searchBtn.setBorder(null);
            searchBtn.setOpaque(false);

            searchOptions.setPreferredSize(new Dimension(120, 30));
            searchOptions.setFocusable(false);
            searchOptions.setFont(FONTS.MANAGER_LABEL_HEADER);
            for (String item : SEARCH_BY_) {
                searchOptions.addItem(item);
            }

            search_pane.add(searchOptions);
            this.add(title, BorderLayout.NORTH);
            this.add(search_pane, BorderLayout.CENTER);

        }

        private void listen() {
            search_input.addKeyListener(new KeyAdapterImpl("Type something..."));
        }

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
                    place_holder_search.setText(this.pl_text);
                    return;
                }
                place_holder_search.setText(null);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String value = search_input.getText();
                if (value.isEmpty()) {
                    place_holder_search.setText(this.pl_text);
                    return;
                }
                place_holder_search.setText(null);
            }
        }

        private JLabel title, search_label, place_holder_search;
        private JPanel search_pane;
        private JTextField search_input;
        private JButton searchBtn;
        private JComboBox<String> searchOptions;
    }

    final class Content extends JPanel {

        public Content() {
            this.createContent();
        }

        public void setEnabledComponent(boolean isEnabled) {
            Arrays.asList(eastButtons.getComponents()).stream()
                    .filter(cmp -> cmp instanceof JButton)
                    .forEach(item -> item.setEnabled(false));

        }

        private void createContent() {
            this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 50));
            form = new Form();
            east = new JPanel();
            tableManager = new Table();

            JPanel imgPane = new JPanel();
            imgPane.setLayout(new BoxLayout(imgPane, BoxLayout.Y_AXIS));

            avatar = new JLabel(new ImageIcon(IMAGES.MANAGER_NO_AVATAR), 
                    JLabel.CENTER);
            avatar.setBorder(new EmptyBorder(30, 50, 0, 0));
            avatar.setPreferredSize(new Dimension(350, 300));

            imgPane.add(avatar);
            east.setLayout(new GridLayout(2, 1, 0, 10));
            east.setBorder(new EmptyBorder(0, 0, 100, 100));

            east.setPreferredSize(new Dimension(400, 200));
            buttonFunc();

            east.add(imgPane, BorderLayout.NORTH);
            east.add(eastButtons, BorderLayout.CENTER);

            this.add(form);
            this.add(east);
            this.add(tableManager);
        }

        private void buttonFunc() {
            eastButtons = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(15, 15, 15, 15);

            addBtn = new JButton("Add", new ImageIcon(IMAGES.FORM_NEW_BUTTON));
            saveBtn = new JButton("Save", new ImageIcon(IMAGES.FORM_SAVE_BUTTON));
            updateBtn = new JButton("Update", new ImageIcon(IMAGES.FORM_UPDATE_BUTTON));
            deletebtn = new JButton("Delete", new ImageIcon(IMAGES.FORM_DELETE_BUTTON));

            addBtn.setFocusable(false);
            saveBtn.setFocusable(false);
            updateBtn.setFocusable(false);
            deletebtn.setFocusable(false);

            addBtn.setFont(FONTS.MANAGER_BUTTON_CONTENT);
            saveBtn.setFont(FONTS.MANAGER_BUTTON_CONTENT);
            updateBtn.setFont(FONTS.MANAGER_BUTTON_CONTENT);
            deletebtn.setFont(FONTS.MANAGER_BUTTON_CONTENT);

            gbc.gridx = 0;
            gbc.gridy = 0;

            eastButtons.add(addBtn, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            eastButtons.add(saveBtn, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            eastButtons.add(updateBtn, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            eastButtons.add(deletebtn, gbc);
        }

        private Form form;
        private JPanel east, eastButtons;
        private JLabel avatar;
        private JButton addBtn, saveBtn, updateBtn, deletebtn;
        private Table tableManager;
    }

    final class Form extends JPanel {

        public Form() {
            this.initForm();
            this.setAttributes();
        }

        private void initForm() {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.setBorder(new EmptyBorder(20, 20, 20, 20));

            this.setPreferredSize(new Dimension(500, 200));
            pnlID = new JPanel();
            lblID = new JLabel();
            txtID = new JTextField();

            pnlName = new JPanel();
            lblName = new JLabel();
            txtName = new JTextField();

            pnlEmail = new JPanel();
            lblEmail = new JLabel();
            txtEmail = new JTextField();

            pnlPhone = new JPanel();
            lblPhone = new JLabel();
            txtPhone = new JTextField();

            pnlGender = new JPanel();
            lblGender = new JLabel();
            genderGroup = new ButtonGroup();
            maleBtn = new JRadioButton();
            femaleBtn = new JRadioButton();

            pnlAddress = new JPanel();
            lblAddress = new JLabel();
            taNotes = new JTextArea();

            footer = new JPanel();

            firstBtn = new JButton();
            previousBtn = new JButton();
            nextBtn = new JButton();
            lastBtn = new JButton();

            jsp = new JScrollPane();

            formControl(pnlID, lblID, txtID);
            formControl(pnlName, lblName, txtName);
            formControl(pnlEmail, lblEmail, txtEmail);
            formControl(pnlPhone, lblPhone, txtPhone);
            formControl(pnlGender, lblGender, maleBtn, femaleBtn);
            formControl(pnlAddress, lblAddress, jsp);

            this.add(pnlID);
            this.add(pnlName);
            this.add(pnlPhone);
            this.add(pnlEmail);
            this.add(pnlGender);
            this.add(pnlAddress);
            this.add(footer);
        }

        private void setAttributes() {
            lblID.setText("ID");
            lblID.setFont(FONTS.MANAGER_LABEL_FORM);
            txtID.setFont(FONTS.MANAGER_INPUT_FORM);
            txtID.setMinimumSize(new Dimension(100, 30));
            txtID.setPreferredSize(new Dimension(100, 30));

            lblName.setText("Name");
            lblName.setFont(FONTS.MANAGER_LABEL_FORM);
            txtName.setFont(FONTS.MANAGER_INPUT_FORM);
            txtName.setMinimumSize(new Dimension(100, 30));
            txtName.setPreferredSize(new Dimension(100, 30));

            lblEmail.setText("Email");
            lblEmail.setFont(FONTS.MANAGER_LABEL_FORM);
            txtEmail.setFont(FONTS.MANAGER_INPUT_FORM);
            txtEmail.setMinimumSize(new Dimension(100, 30));
            txtEmail.setPreferredSize(new Dimension(100, 30));

            lblPhone.setText("Phone");
            lblPhone.setFont(FONTS.MANAGER_LABEL_FORM);
            txtPhone.setFont(FONTS.MANAGER_INPUT_FORM);
            txtPhone.setMinimumSize(new Dimension(100, 30));
            txtPhone.setPreferredSize(new Dimension(100, 30));

            lblGender.setText("Gender");
            lblGender.setFont(FONTS.MANAGER_LABEL_FORM);
            lblGender.setPreferredSize(new Dimension(60, 30));
            lblGender.setBorder(new EmptyBorder(0, 10, 0, 0));
            maleBtn.setText("Male");
            maleBtn.setFont(FONTS.MANAGER_LABEL_FORM);
            maleBtn.setBorder(new EmptyBorder(0, 25, 0, 0));
            maleBtn.setFocusable(false);
            femaleBtn.setText("Female");
            femaleBtn.setFont(FONTS.MANAGER_LABEL_FORM);
            femaleBtn.setBorder(new EmptyBorder(0, 50, 0, 0));
            femaleBtn.setFocusable(false);
            genderGroup.add(maleBtn);
            genderGroup.add(femaleBtn);

            lblAddress.setText("Address");
            lblAddress.setFont(FONTS.MANAGER_LABEL_FORM);
            lblAddress.setPreferredSize(new Dimension(60, 150));
            lblAddress.setVerticalAlignment(JLabel.TOP);
            taNotes.setPreferredSize(new Dimension(100, 150));
            jsp.setViewportView(taNotes);
            taNotes.setColumns(20);
            taNotes.setRows(8);
            taNotes.setFont(FONTS.MANAGER_INPUT_FORM);

            footer.setLayout(new FlowLayout(FlowLayout.LEFT, 35, 0));
            footer.setBorder(new EmptyBorder(0, 40, 50, 0));
            firstBtn = new JButton(new ImageIcon(IMAGES.FORM_FIRST));
            firstBtn.setBorder(null);
            previousBtn = new JButton(new ImageIcon(IMAGES.FORM_PREVIOUS));
            previousBtn.setBorder(null);
            nextBtn = new JButton(new ImageIcon(IMAGES.FORM_NEXT));
            nextBtn.setBorder(null);
            lastBtn = new JButton(new ImageIcon(IMAGES.FORM_LAST));
            lastBtn.setBorder(null);

            firstBtn.setFocusable(false);
            previousBtn.setFocusable(false);
            nextBtn.setFocusable(false);
            lastBtn.setFocusable(false);

            firstBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            previousBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            nextBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            lastBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

            footer.add(firstBtn);
            footer.add(previousBtn);
            footer.add(nextBtn);
            footer.add(lastBtn);
        }

        private void formControl(JPanel panel, JLabel label, JTextField input) {
            GroupLayout controller = new GroupLayout(panel);
            panel.setLayout(controller);
            controller.setHorizontalGroup(
                    controller.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(controller.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(input, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
            );
            controller.setVerticalGroup(
                    controller.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, controller.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(controller.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(input, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
        }

        private void formControl(JPanel panel, JLabel label, JRadioButton... buttons) {
            FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT, 0, 5);
            panel.setLayout(flowLayout);
            panel.add(label);

            Arrays.asList(buttons).forEach(button -> panel.add(button));
        }

        private void formControl(JPanel panel, JLabel label, JScrollPane jsp) {
            GroupLayout controller = new GroupLayout(panel);
            panel.setLayout(controller);
            controller.setHorizontalGroup(
                    controller.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(controller.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(label, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jsp, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
            );
            controller.setVerticalGroup(
                    controller.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(GroupLayout.Alignment.TRAILING, controller.createSequentialGroup()
                                    .addGap(15, 15, 15)
                                    .addGroup(controller.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jsp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addContainerGap())
            );
        }

        public JPanel pnlID, pnlName, pnlEmail, pnlPhone, pnlGender, pnlAddress, footer;
        private JLabel lblID, lblName, lblEmail, lblPhone, lblGender, lblAddress;
        private JTextField txtID, txtName, txtEmail, txtPhone;
        private JButton firstBtn, previousBtn, nextBtn, lastBtn;
        private JRadioButton maleBtn, femaleBtn;
        private ButtonGroup genderGroup;
        private JScrollPane jsp;
        private JTextArea taNotes;
    }

    final class Table extends JPanel {

        public Table() {
            this.createInitTable();
            this.getDefaultData();
        }

        private void createInitTable() {
            this.setLayout(new BorderLayout(0, 25));
            this.setPreferredSize(new Dimension(200, 200));
            this.setBorder(new EmptyBorder(0, 15, 50, 20));
            this.setBackground(COLORS.GLOBAL_BACKGROUND);
            this.setOpaque(false);

            tableOptionLabel = new JLabel();
            sortOptions = new JComboBox<>();
            headerTablePane = new JPanel();
            model = new DefaultTableModel();
            studentTable = new JTable();
            jsp = new JScrollPane(studentTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            JPanel leftPane = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
            leftPane.setOpaque(false);
            leftPane.add(tableOptionLabel);
            tableOptionLabel.setFont(FONTS.MANAGER_LABEL_TABLE);
            tableOptionLabel.setText("Option: ???");

            JPanel rightPane = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            rightPane.setOpaque(false);

            sortOptions.setFont(FONTS.MANAGER_LABEL_TABLE);
            sortOptions.setFocusable(false);
            sortOptions.addItem("Sort by: ???");
            rightPane.add(sortOptions);

            headerTablePane.setLayout(new GridLayout(1, 2));
            headerTablePane.add(leftPane);
            headerTablePane.add(rightPane);

            this.add(headerTablePane, BorderLayout.NORTH);
            this.add(jsp, BorderLayout.CENTER);

            studentTable.setForeground(COLORS.MANAGER_TEXT);
            studentTable.setFont(FONTS.MANAGER_CONTENT_TABLE);
            studentTable.getTableHeader().setFont(FONTS.MANAGER_HEADER_TABLE);
            studentTable.getTableHeader().setReorderingAllowed(false);
            studentTable.setBorder(BorderFactory.createLineBorder(COLORS.MANAGER_TEXT));
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

        private JLabel tableOptionLabel;
        JPanel headerTablePane;
        private JComboBox<String> sortOptions;
        private JTable studentTable;
        private JScrollPane jsp;
        private DefaultTableModel model;
    }
}
