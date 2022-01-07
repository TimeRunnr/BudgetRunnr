import GUI.BudgetRunnr;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                createGUI();
            }
        });
    }

    private static void createGUI() {
        BudgetRunnr tableUi = new BudgetRunnr();
        JPanel root = tableUi.getRootPanel();
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
