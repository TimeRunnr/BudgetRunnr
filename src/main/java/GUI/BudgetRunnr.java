package GUI;

import com.github.lgooddatepicker.components.DatePicker;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BudgetRunnr {
    private JTable tblLastTransactions;
    private JButton btnIncome;
    private JPanel rootPanel;
    private JButton btnExpense;
    private JPanel panelTransactionOperations;
    private JPanel panelTransactionSearch;
    private JLabel lblSearchTransactions;
    private JComboBox comboSearchTransactionType;
    private JScrollPane scrollTblLastTransactions;
    private JPanel panelTblLastTransactions;
    private DatePicker datePickerSearch;
    private JCheckBox chkDate;
    private JFormattedTextField txtValueSearch;
    private JCheckBox chkValue;
    private JLabel lblAmountAvailable;
    private JPanel panelGeneralInfo;
    private JLabel lblAccount;
    private JComboBox spinAccountSelect;
    private JLabel lblAvailable;
    private JButton btnStartSearch;
    private JCheckBox chkCause;
    private JFormattedTextField txtCause;

    public BudgetRunnr() {
        createLastTransactionsTable();
        createListeners();

        PlainDocument doc = (PlainDocument) txtValueSearch.getDocument();
        doc.setDocumentFilter(new IntFilter());
        chkCause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBox cb = (JCheckBox) e.getSource();
                if (cb.isSelected()) {
                    txtCause.setEnabled(true);
                } else {
                    txtCause.setEnabled(false);
                }
            }
        });
    }

    private void createListeners() {
        chkDate.addActionListener(e -> {
            JCheckBox cb = (JCheckBox) e.getSource();
            if (cb.isSelected()) {
                datePickerSearch.setEnabled(true);
            } else {
                datePickerSearch.setEnabled(false);
            }
        });

        chkValue.addActionListener(e -> {
            JCheckBox cb = (JCheckBox) e.getSource();
            if (cb.isSelected()) {
                txtValueSearch.setEnabled(true);
            } else {
                txtValueSearch.setEnabled(false);
            }
        });
    }

    private void createLastTransactionsTable() {
        tblLastTransactions.setModel(new DefaultTableModel(
                null,
                new String[]{"Type", "Amount", "Date", "Cause", "Account"}
        ));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private class IntFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string,
                                 AttributeSet attr) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.insert(offset, string);

            if (test(sb.toString())) {
                super.insertString(fb, offset, string, attr);
            } else {
                // TODO warn the user and don't allow the insert
            }
        }

        private boolean test(String text) {
            try {
                Integer.parseInt(text);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text,
                            AttributeSet attrs) throws BadLocationException {

            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.replace(offset, offset + length, text);

            if (test(sb.toString())) {
                super.replace(fb, offset, length, text, attrs);
            } else {
                // TODO warn the user and don't allow the insert
            }

        }

        @Override
        public void remove(FilterBypass fb, int offset, int length)
                throws BadLocationException {
            Document doc = fb.getDocument();
            StringBuilder sb = new StringBuilder();
            sb.append(doc.getText(0, doc.getLength()));
            sb.delete(offset, offset + length);

            if (test(sb.toString())) {
                super.remove(fb, offset, length);
            } else {
                //  TODO warn the user and don't allow the insert
            }

        }
    }
}
