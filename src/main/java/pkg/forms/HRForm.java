package pkg.forms;

import pkg.application.Application;
import pkg.application.ApplicationDAO;
import pkg.application.ApplicationStatus;
import pkg.course.Course;
import pkg.course.CourseDAO;
import pkg.exception.AppExceptions;
import pkg.staff.HR;
import pkg.utils.MainUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class HRForm extends JFrame{
    private JPanel hrPanel;
    private JLabel lbl_tn;
    private JLabel lbl_fio;
    private JLabel lbl_age1;
    private JLabel lbl_grade;
    private JTable tbl_applications;
    private JButton btn_ok;
    private JButton btn_cancel;
    private JTable tbl_courses;
    private JButton btn_add;
    private JLabel lbl_error;
    private int checkAppId;

    public HRForm (HR hr) {
        System.out.println(hr.print());

        // заполняем шапку
        lbl_age1.setText(String.valueOf(hr.getAge()));
        lbl_fio.setText(hr.getFio());
        lbl_grade.setText(String.valueOf(hr.getGrade()));
        lbl_tn.setText(String.valueOf(hr.getTabNum()));

        ApplicationDAO appDAO = new ApplicationDAO();
        List<Application> applications = appDAO.getAllApplications();
        fillApplications(applications);

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courses = courseDAO.getAllCourses();
        fillCourses(courses);

        // LISTENER
        ListSelectionModel selectionModel = tbl_applications.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_applications.getSelectedRow();
                if (selectedRow > -1) {
                    btn_ok.setEnabled(true);
                    btn_cancel.setEnabled(true);
                    checkAppId = (int) tbl_applications.getValueAt(selectedRow, 2);
                } else {
                    btn_cancel.setEnabled(false);
                    btn_ok.setEnabled(false);
                }
            }
        });


        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lbl_error.setText(null);
                    MainUtils.hrApproveApp(hr, checkAppId, true);
                    fillApplications(appDAO.getAllApplications());
                    tbl_applications.revalidate();
                } catch (AppExceptions ae) {
                    lbl_error.setText(ae.getMessage());
                }
            }
        });

        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lbl_error.setText(null);
                    MainUtils.hrApproveApp(hr, checkAppId, false);
                    fillApplications(appDAO.getAllApplications());
                    tbl_applications.revalidate();
                } catch (AppExceptions ae) {
                    lbl_error.setText(ae.getMessage());
                }
            }
        });

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NewCourseForm();
                fillCourses(courseDAO.getAllCourses());
                tbl_courses.revalidate();
            }
        });


        setContentPane(hrPanel);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void fillApplications (List<Application> applications){
        Vector<String> tabHeader = new Vector<>();
        tabHeader.add("Таб номер");
        tabHeader.add("ФИО");
        tabHeader.add("Id заявки");
        tabHeader.add("Id курса");
        tabHeader.add("Курс");
        tabHeader.add("Дата начала");
        tabHeader.add("Продолжительность");
        tabHeader.add("Учебный центр");
        tabHeader.add("Статус заявки");
        tabHeader.add("Дата заявки");
        tabHeader.add("Макс кол-во");
        tabHeader.add("Кол-во занятых мест");

        Vector tabData = new Vector();

        applications.sort(new Comparator<Application>() {
            @Override
            public int compare(Application o1, Application o2) {
                return o1.getDateChange().compareTo(o2.getDateChange());
            }
        });

        for (Application application : applications){
            if (application.getStatus() == ApplicationStatus.AGREED){
                Vector row = new Vector();
                row.add(application.getEmployee().getTabNum());
                row.add(application.getEmployee().getFio());
                row.add(application.getAppId());
                Course course = application.getCourse();
                row.add(course.getCourseId());
                row.add(course.getName());
                row.add(course.getDateStart());
                row.add(course.getDuration());
                row.add(course.getTraningCenter());
                row.add(application.getStatus().getDescription());
                row.add(application.getDateChange());
                row.add(course.getMaxCount());
                row.add(course.getEmployeeCourses().size());
                tabData.add(row);
            }
        }

        tbl_applications.setModel(new DefaultTableModel(tabData,tabHeader));
    }

    public void fillCourses (List<Course> courses) {
        Vector<String> tableHeaders = new Vector<>();
        Vector tableData = new Vector();
        tableHeaders.add("Номер");
        tableHeaders.add("Название");
        tableHeaders.add("Учебный центр");
        tableHeaders.add("Дата начала");
        tableHeaders.add("Дата окончания");
        tableHeaders.add("Продолжительность");
        tableHeaders.add("Макс кол-во участников");
        tableHeaders.add("Кол-во записанных");
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
                oneRow.add(course.getDateEnd());
                oneRow.add(course.getDuration());
                oneRow.add(course.getMaxCount());
                oneRow.add(course.getEmployeeCourses().size());
                oneRow.add(course.getPrice());
                tableData.add(oneRow);
            }
        }
        //System.out.println("курсы: " + tableData);
        tbl_courses.setModel(new DefaultTableModel(tableData,tableHeaders));

    }

}
