package pkg.forms;

import pkg.utils.MainUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class NewCourseForm extends JDialog{
    private JTextField tf_name;
    private JTextField tf_center;
    private JTextField tf_startDate;
    private JTextField tf_endDate;
    private JTextField tf_duration;
    private JTextField tf_price;
    private JTextField tf_maxCount;
    private JButton btn_save;
    private JPanel newCoursePanel;
    private JLabel lbl_error;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public NewCourseForm () {

        tf_startDate.setInputVerifier(new DateInputVerifier());
        tf_endDate.setInputVerifier(new DateInputVerifier());
        tf_maxCount.setInputVerifier(new IntInputVerifier());
        tf_price.setInputVerifier(new FloatInputVerifier());

        btn_save.addActionListener(new SaveButtonListener());

        setModal(true);
        setContentPane(newCoursePanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }


    public class DateInputVerifier extends InputVerifier{

        @Override
        public boolean verify(JComponent input) {
            String txt = ((JTextField) input).getText();
            try {
                Date dt = dateFormat.parse(txt);
                lbl_error.setText(null);
                if(tf_startDate.getText().trim().length() > 0 && tf_endDate.getText().trim().length() > 0) {
                    if (dateFormat.parse(tf_startDate.getText()).compareTo(dateFormat.parse(tf_endDate.getText())) > 0) {
                        lbl_error.setText("Дата окончания должна быть позже даты начала");
                        return false;
                    }
                }
                return true;
            } catch (ParseException e) {
                lbl_error.setText("Дата должна быть введена в формате YYYY-MM-DD");
                return false;
            }

        }
    }

    public class FloatInputVerifier extends InputVerifier{

        @Override
        public boolean verify(JComponent input) {
            String txt = ((JTextField) input).getText();
            try {
                Float val = new Float(txt);
                lbl_error.setText(null);
                return true;
            } catch (NumberFormatException e) {
                lbl_error.setText("Введено неверное число");
                return false;
            }
        }
    }

    public class IntInputVerifier extends InputVerifier{

        @Override
        public boolean verify(JComponent input) {
            String txt = ((JTextField) input).getText();
            try {
                Integer val = new Integer(txt);
                lbl_error.setText(null);
                return true;
            } catch (NumberFormatException e) {
                lbl_error.setText("Введено неверное число");
                return false;
            }
        }
    }

    public class SaveButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (tf_center.getText().trim().length() > 0 && tf_center.isValid() &&
                tf_endDate.getText().trim().length() > 0 && tf_endDate.isValid() &&
                tf_maxCount.getText().trim().length() > 0 && tf_maxCount.isValid() &&
                tf_name.getText().trim().length() > 0 &&
                tf_price.getText().trim().length() > 0 && tf_price.isValid() &&
                tf_startDate.getText().trim().length() > 0 && tf_startDate.isValid()
            ) {
                try {
                    MainUtils.createNewCourse(tf_name.getText(),
                            tf_center.getText(),
                            new java.sql.Date(dateFormat.parse(tf_startDate.getText()).getTime()),
                            new java.sql.Date (dateFormat.parse(tf_endDate.getText()).getTime()),
                            tf_duration.getText(),
                            new Float(tf_price.getText()),
                            new Integer(tf_maxCount.getText()));
                } catch (ParseException ae) {
                    ae.printStackTrace();
                }
                setVisible(false);
            }  else {
                lbl_error.setText("Проверьте заполненные поля");
            }
        }
    }
}
