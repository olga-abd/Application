package pkg.forms;

import pkg.application.Application;
import pkg.application.ApplicationDAO;
import pkg.course.Course;
import pkg.course.CourseDAO;
import pkg.staff.Employee;
import pkg.staff.EmployeeCourse;
import pkg.staff.EmployeeCourseDAO;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeForm extends JFrame{
    private JLabel lbl_tn;
    private JLabel lbl_fio;
    private JLabel lbl_age1;
    private JLabel lbl_grade;
    private JTable tbl_finishedCources;
    private JTable tbl_applications;
    private JTable tbl_offerCourses;
    private JPanel panelEmployee;
    private JButton btn_createApp;
    private int checkCourseId;
    private Employee empl;

    public EmployeeForm(Employee employee) {
        empl = employee;
        // заполняем шапку
        System.out.println(employee.print());
        lbl_age1.setText(String.valueOf(employee.getAge()));
        lbl_fio.setText(employee.getFio());
        lbl_grade.setText(String.valueOf(employee.getGrade().getGradeId()));
        lbl_tn.setText(String.valueOf(employee.getTabNum()));

        // заполняем таблицу с курсами
        CourseDAO courseDAO = new CourseDAO();
        fillOfferCourses(courseDAO.getUserCourses(employee.getTabNum()));

        ApplicationDAO applicationDAO = new ApplicationDAO();
        fillApplications(applicationDAO.getApplicationsByUser(employee.getTabNum()));

        EmployeeCourseDAO ecDAO = new EmployeeCourseDAO();
        fillFinishedCoursed(ecDAO.getEmployeeCourses());

        btn_createApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // тут будет выведено окно для создания заявки для курса checkCourseId
                Course course = courseDAO.fingById(checkCourseId);
                new ApplicationForm(employee, course);
//                new ApplicationDialog();
                fillApplications(applicationDAO.getApplicationsByUser(employee.getTabNum()));
                System.out.println(1);
                tbl_applications.revalidate();
                System.out.println(2);
                //tbl_offerCourses.getSelectionModel().clearSelection();
                fillOfferCourses(courseDAO.getUserCourses(employee.getTabNum()));
                System.out.println(3);
                tbl_offerCourses.revalidate();
                System.out.println(4);
            }
        });


        // LISTENER
        ListSelectionModel selectionModel = tbl_offerCourses.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_offerCourses.getSelectedRow();
                //System.out.println("selrow " + selectedRow);
                if (selectedRow != -1) {
                    checkCourseId = (int) tbl_offerCourses.getValueAt(selectedRow, 0);
                    btn_createApp.setEnabled(true);
                } else {btn_createApp.setEnabled(false);}
            }
        });

        setContentPane(panelEmployee);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                updateForm(employee.getTabNum());
            }
        });
        thread.start();
    }

    private void fillOfferCourses(List<Course> courses){
        Vector<String> tableHeaders = new Vector<>();
        Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Название");
        tableHeaders.add("Учебный центр");
        tableHeaders.add("Дата начала");
        tableHeaders.add("Продолжительность");
        tableHeaders.add("Макс кол-во участников");
        tableHeaders.add("Цена");

        Date currentDate = new Date(System.currentTimeMillis());

        courses.sort(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getDateStart().toLocalDate().compareTo(o2.getDateStart().toLocalDate());
            }
        });


        for (Course course : courses){
            if (course.getDateStart().toLocalDate().compareTo(currentDate.toLocalDate()) > 0) {
                Vector oneRow = new Vector();
                oneRow.add(course.getCourseId());
                oneRow.add(course.getName());
                oneRow.add(course.getTraningCenter());
                oneRow.add(course.getDateStart());
                oneRow.add(course.getDuration());
                oneRow.add(course.getMaxCount());
                oneRow.add(course.getPrice());
                tableData.add(oneRow);
            }
        }
        System.out.println("курсы: " + tableData);
        tbl_offerCourses.setModel(new DefaultTableModel(tableData,tableHeaders));
        //tbl_offerCourses.setRowSelectionAllowed(false);





    }

    private void fillApplications(List<Application> applications){
        Vector<String> tableHeaders = new Vector<>();
        tableHeaders.add("Номер");
        tableHeaders.add("Название курса");
        tableHeaders.add("Дата курса");
        tableHeaders.add("Продолжительность");
        tableHeaders.add("Статус заявки");
        tableHeaders.add("Комментарий");

        applications.sort(new Comparator<Application>() {
            @Override
            public int compare(Application o1, Application o2) {
                return o1.getDateChange().compareTo(o2.getDateChange());
            }
        });

        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");

        Vector tableData = new Vector();
        for (Application application : applications){
            Vector row = new Vector();
            row.add(application.getAppId());
            row.add(application.getCourse().getName());
            row.add(sdp.format(application.getCourse().getDateStart()));
            row.add(application.getCourse().getDuration());
            row.add(application.getStatus().getDescription());
            row.add(application.getComment());
            tableData.add(row);
        }

        System.out.println("заявки: " + tableData);
        tbl_applications.setModel(new DefaultTableModel(tableData,tableHeaders));


    }

    private void fillFinishedCoursed(List<EmployeeCourse> employeeCourses){
        Vector<String> tableHeaders = new Vector<>();
        Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Название");
        tableHeaders.add("Учебный центр");
        tableHeaders.add("Дата начала");
        tableHeaders.add("Дата окончания");
        tableHeaders.add("Продолжительность");
        tableHeaders.add("Статус");

        Collections.sort(employeeCourses);

        for (EmployeeCourse employeeCourse : employeeCourses){
            if (employeeCourse.getEmployee().equals(empl)){
                Vector row = new Vector();
                Course course = employeeCourse.getCourse();
                row.add(course.getCourseId());
                row.add(course.getName());
                row.add(course.getTraningCenter());
                row.add(course.getDateStart());
                row.add(course.getDateEnd());
                row.add(course.getDuration());
                row.add(employeeCourse.getStatus().getDescription());
                tableData.add(row);
            }
        }

        tbl_finishedCources.setModel(new DefaultTableModel(tableData,tableHeaders));
    }


    private void updateForm (int tn) {
        CourseDAO courseDAO = new CourseDAO();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        EmployeeCourseDAO ecDAO = new EmployeeCourseDAO();

        while(true){
            try {
                Thread.sleep(1000 * 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fillOfferCourses(courseDAO.getUserCourses(tn));
            fillApplications(applicationDAO.getApplicationsByUser(tn));
            //fillFinishedCoursed(ecDAO.getEmployeeCourses());
        }
    }

}
