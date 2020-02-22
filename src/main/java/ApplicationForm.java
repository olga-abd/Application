import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ApplicationForm extends JDialog {
    private JPanel app_panel;
    private JLabel lbl_courseId;
    private JTextArea lbl_courseName;
    private JLabel lbl_center;
    private JLabel lbl_date;
    private JLabel lbl_duration;
    private JButton btn_ok;
    private JButton btn_cancel;

    public ApplicationForm(Employee employee, Course course){
        setModal(true);

        lbl_courseId.setText(String.valueOf(course.getCourseId()));
        lbl_courseName.setText(course.getName());
        //lbl_courseName.setWr
        lbl_center.setText(course.getTraningCenter());
        lbl_date.setText(String.valueOf(course.getDateStart()));
        lbl_duration.setText(course.getDuration());

        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainUtils.createApplication(course,employee);
                System.out.println("OK");
                setVisible(false);

            }
        });

        setContentPane(app_panel);

        //setLayout(new FlowLayout());
        setSize(300,250);
        //pack();
        //getRootPane().setDefaultButton(btn_ok);
        //setPreferredSize(new Dimension(100,100));
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}
