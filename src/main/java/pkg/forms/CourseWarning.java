package pkg.forms;

import pkg.application.ApplicationStatus;
import pkg.course.Course;
import pkg.staff.Employee;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CourseWarning extends JDialog {
    private JPanel contentPane;
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
    private Course course;
    private Employee employee;

    public CourseWarning(Course course, Employee employee, ApplicationStatus status) {

        this.course = course;
        this.employee = employee;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        if (status == ApplicationStatus.NOVACANT) {
            txt_warning.setText("Записано максимальное количество участников");
        } else if (status == ApplicationStatus.BADDATE) {
            //todo: исправить статус
            txt_warning.setText("Сумма по сотруднику исчерпана");
        }

        lbl_emp.setText(employee.getTabNum() + ", " + employee.getFio());
        Date curDate = new Date(System.currentTimeMillis());

        lbl_sum.setText(String.valueOf(employee.getApplicationSum(curDate.toLocalDate().getYear())));


        txt_course.setText(course.getCourseId() + ". " + course.getName());
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        lbl_start.setText(sdp.format(course.getDateStart()));
        lbl_end.setText(sdp.format(course.getDateEnd()));
        lbl_price.setText(String.valueOf(course.getPrice()));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
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
