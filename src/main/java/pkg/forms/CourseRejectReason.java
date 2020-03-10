package pkg.forms;

import pkg.application.Application;
import pkg.exception.AppExceptions;
import pkg.staff.HR;
import pkg.utils.MainUtils;

import javax.swing.*;
import java.awt.event.*;

public class CourseRejectReason extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextField txt_reason;
    private int appId;
    private HR hr;

    public CourseRejectReason(HR hr, int appId) {
        this.appId = appId;
        this.hr = hr;

        setContentPane(contentPane);
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        pack();
        setVisible(true);
    }

    private void onOK() {
        // add your code here
        try {
            MainUtils.hrApproveApp(hr, appId, false, txt_reason.getText());
        } catch (AppExceptions appExceptions) {
            appExceptions.printStackTrace();
        }
        dispose();
    }


}
