package pkg.forms;

import pkg.exception.AppExceptions;
import pkg.staff.DepHead;
import pkg.staff.Employee;
import pkg.staff.HR;
import pkg.staff.Staff;
import pkg.utils.HibernateUtils;
import pkg.utils.MainUtils;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Vector;


public class MainForm2 extends JFrame{
    private JPanel mainPanel2;
    private JComboBox cb_tn;
    private JTextField tf_login;
    private JPasswordField pf_psw;
    private JButton btn_ok1;
    private JLabel lbl_error;

    public MainForm2(){

        HibernateUtils.getSesstionFactory();
//        pkg.dao.StaffDAO staffDAO = new pkg.dao.StaffDAO();
        //fillCheckBox(staffDAO.getAllStaff());

        setContentPane(mainPanel2);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        btn_ok1.addActionListener(e -> onOk());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel2.registerKeyboardAction(e -> onOk(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    private void onOk() {
        try {
            Staff staff = MainUtils.getStaff(tf_login.getText(), String.valueOf(pf_psw.getPassword()));
            lbl_error.setText(null);

            if (staff instanceof Employee){
                new EmployeeForm((Employee) staff);
            } else if (staff instanceof DepHead){
                new DepheadForm((DepHead) staff);
            } else if (staff instanceof HR){
                new HRForm((HR) staff);
            }
            dispose();

        } catch (AppExceptions ae) {
            lbl_error.setText(ae.getMessage());
        }
    }


    private void fillCheckBox(List<Staff> resutlList){
        Vector data = new Vector();
        for (Staff s : resutlList) {
            data.add(s.getTabNum());
        }
        cb_tn.setModel(new DefaultComboBoxModel(data));

    }


}
