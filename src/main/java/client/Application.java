package main.java.client;

import main.java.ui.Login;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import main.java.LOG;

public class Application {

    public static void main(String[] args) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException exception) {
            LOG.error("Error: ", exception);
        }
        //</editor-fold>
        //</editor-fold>

        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
