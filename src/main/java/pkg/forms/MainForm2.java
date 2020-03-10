package pkg.forms;

import pkg.exception.AppExceptions;
import pkg.staff.DepHead;
import pkg.staff.Employee;
import pkg.staff.HR;
import pkg.staff.Staff;
import pkg.utils.HibernateUtils;
import pkg.utils.MainUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;


public class MainForm2 extends JFrame{
    private JPanel mainPanel;
    private JComboBox cb_tn;
    private JTextField tf_login;
    private JPasswordField pf_psw;
    private JButton btn_ok;
    private JLabel lbl_error;

    public MainForm2(){

        HibernateUtils.getSesstionFactory();
//        pkg.staff.StaffDAO staffDAO = new pkg.staff.StaffDAO();
        //fillCheckBox(staffDAO.getAllStaff());

        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int tn = (int) cb_tn.getSelectedItem();
//                pkg.staff.Staff staff = staffDAO.findById(tn);
//                System.out.println(staff.print());
//
//                setVisible(false);
//                if (staff instanceof pkg.staff.Employee) {
//                    new EmployeeForm((pkg.staff.Employee)staff);
//                }
//                dispose();

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
        });

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }


    private void fillCheckBox(List<Staff> resutlList){
        Vector data = new Vector();
        for (Staff s : resutlList) {
            data.add(s.getTabNum());
        }
        cb_tn.setModel(new DefaultComboBoxModel(data));

    }


}
