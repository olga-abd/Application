package pkg.forms;

import pkg.exception.AppExceptions;
import pkg.staff.DepHead;
import pkg.staff.HR;
import pkg.staff.Staff;
import pkg.utils.MainUtils;

import javax.swing.*;
import java.awt.event.*;

public class CourseRejectReason extends JDialog {
    private JPanel contentPane2;
    private JButton buttonOK;
    private JTextField txt_reason;
    private int appId;
    private Staff staff;

    public CourseRejectReason(Staff staff, int appId) {
        this.appId = appId;
        this.staff = staff;

        setContentPane(contentPane2);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });



        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {

            }
        });

        // call onCancel() on ESCAPE
        contentPane2.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        try {
            if (staff instanceof HR) {
                MainUtils.hrApproveApp((HR) staff, appId, false, txt_reason.getText());
            } else if (staff instanceof DepHead) {
                MainUtils.headApproveApp((DepHead) staff, appId, false, txt_reason.getText());
            }
        } catch (AppExceptions appExceptions) {
            appExceptions.printStackTrace();
        }
        dispose();
    }


}
