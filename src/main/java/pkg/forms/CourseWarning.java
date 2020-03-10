package pkg.forms;

import pkg.application.Application;
import pkg.course.Course;
import pkg.staff.Employee;
import pkg.utils.MainUtils;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CourseWarning extends JDialog {
    private JPanel warningPanel1;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JLabel txt_warning;
    private JLabel lbl_emp;
    private JLabel lbl_maxSum;
    private JLabel lbl_sum;
    private JTextArea txt_course;
    private JLabel lbl_start;
    private JLabel lbl_end;
    private JLabel lbl_price;
    private Application application;
//    private Course course;
//    private Employee employee;

    public CourseWarning(Application application) {

        this.application = application;
        Course course = application.getCourse();
        Employee employee = application.getEmployee();

        setContentPane(warningPanel1);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        txt_warning.setText(application.getStatus().getDescription());


        lbl_emp.setText(employee.getTabNum() + ", " + employee.getFio());
        Date curDate = new Date(System.currentTimeMillis());
        lbl_maxSum.setText(String.valueOf(employee.getGrade().getMaxSum()));
        lbl_sum.setText(String.valueOf(employee.getApplicationSum(curDate.toLocalDate().getYear())));


        txt_course.setText(course.getCourseId() + ". " + course.getName());
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        lbl_start.setText(sdp.format(course.getDateStart()));
        lbl_end.setText(sdp.format(course.getDateEnd()));
        lbl_price.setText(String.valueOf(course.getPrice()));


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("ddd");
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        warningPanel1.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        pack();
        setVisible(true);

    }

    private void onOK() {
        // add your code here
        System.out.println(application);
        MainUtils.setExternalStatus(application);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

//    public static void main(String[] args) {
//        CourseWarning dialog = new CourseWarning();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
