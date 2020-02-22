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
    private JButton btn_ok2;
    private JLabel lbl_error;

    public MainForm2(){

        HibernateUtils.getSesstionFactory();
//        StaffDAO staffDAO = new StaffDAO();
        //fillCheckBox(staffDAO.getAllStaff());

        setContentPane(mainPanel);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);

        btn_ok2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                int tn = (int) cb_tn.getSelectedItem();
//                Staff staff = staffDAO.findById(tn);
//                System.out.println(staff.print());
//
//                setVisible(false);
//                if (staff instanceof Employee) {
//                    new EmployeeForm((Employee)staff);
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
