package pkg.forms;

import pkg.application.Application;
import pkg.dao.ApplicationDAO;
import pkg.application.ApplicationStatus;
import pkg.course.Course;
import pkg.course.CourseStatus;
import pkg.dao.EmployeeCourseDAO;
import pkg.dao.StaffDAO;
import pkg.exception.AppExceptions;
import pkg.staff.*;
import pkg.utils.MainUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

public class DepheadForm extends JFrame{
    private JPanel dephead_panel;
    private JLabel lbl_tn;
    private JLabel lbl_fio;
    private JLabel lbl_age2;
    private JLabel lbl_grade;
    private JTable tbl_applications;
    private JButton btn_ok;
    private JButton btn_cancel;
    private JTable tbl_emp;
    private JTable tbl_approved;
    private JLabel lbl_errApp;
    private JTable tbl_futureCourses;
    private List<Employee> employees;
    private StaffDAO staffDAO;
    private ApplicationDAO appDAO;
    private EmployeeCourseDAO ecDAO;
    public int checkAppId;



    public DepheadForm(DepHead dh){

        System.out.println(dh.print());

        staffDAO = new StaffDAO();
        employees = staffDAO.getSubordinatesById(dh.getTabNum());
        appDAO = new ApplicationDAO();
        List<Application> applications = appDAO.getApplicationsByUsers(employees);
        ecDAO = new EmployeeCourseDAO();


        // заполняем шапку
        lbl_age2.setText(String.valueOf(dh.getAge()));
        lbl_fio.setText(dh.getFio());
        lbl_grade.setText(String.valueOf(dh.getGrade().getGradeId()));
        lbl_tn.setText(String.valueOf(dh.getTabNum()));



        // LISTENER
        ListSelectionModel selectionModel = tbl_applications.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int selectedRow = tbl_applications.getSelectedRow();
                if (selectedRow > -1) {
                    checkAppId = (int) tbl_applications.getValueAt(selectedRow,2);
                    btn_ok.setEnabled(true);
                    btn_cancel.setEnabled(true);
                } else {
                    btn_cancel.setEnabled(false);
                    btn_ok.setEnabled(false);
                }
            }
        });

        fillApplications(applications);
        fillEmp(ecDAO.getEmployeeCourses());

        btn_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("form: " + dh);
                    lbl_errApp.setText(null);
                    MainUtils.headApproveApp(dh, checkAppId, true);
                    fillApplications(appDAO.getApplicationsByUsers(employees));
                    tbl_applications.revalidate();
                    tbl_approved.revalidate();
                } catch (AppExceptions ae){
                    lbl_errApp.setText(ae.getMessage());
                }

            }
        });

        btn_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try {
//                    lbl_errApp.setText(null);
                    //MainUtils.headApproveApp(dh, checkAppId, false);
                    new CourseRejectReason(dh, checkAppId);
                    fillApplications(appDAO.getApplicationsByUsers(employees));
                    tbl_applications.revalidate();
//                } catch (AppExceptions ae) {
//                    lbl_errApp.setText(ae.getMessage());
//                }
            }
        });


        setContentPane(dephead_panel);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Thread thread = new Thread(() -> updateForm());
        thread.start();


    }

    private void fillApplications (List<Application> applications){
        Vector<String> tabHeader = new Vector<>();
        tabHeader.add("Таб номер");
        tabHeader.add("ФИО");
        tabHeader.add("Id заявки");
        tabHeader.add("Курс");
        tabHeader.add("Дата начала");
        tabHeader.add("Продолжительность");
        tabHeader.add("Учебный центр");
        tabHeader.add("Статус заявки");
        tabHeader.add("Дата заявки");

        Vector<String> tabH = tabHeader;
        tabH.add("Комментарий");

        Vector tabData = new Vector();
        Vector tabDataApr = new Vector();


        applications.sort(new Comparator<Application>() {
            @Override
            public int compare(Application o1, Application o2) {
                return o1.getDateChange().compareTo(o2.getDateChange());
            }
        });


        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(applications);
        for (Application app : applications){
            Vector row = new Vector();

            row.add(app.getEmployee().getTabNum());
            row.add(app.getEmployee().getFio());
            row.add(app.getAppId());
            Course course = app.getCourse();
            row.add(course.getName());
            row.add(sdp.format(course.getDateStart()));
            row.add(course.getDuration());
            row.add(course.getTraningCenter());
            row.add(app.getStatus().getDescription());
            row.add(app.getDateChange());
            if (app.getStatus() == ApplicationStatus.GENERATED) {
                tabData.add(row);
            }
            else if (app.getStatus() != ApplicationStatus.GENERATED && app.getStatus() != ApplicationStatus.CLOSED) {
                row.add(app.getComment());
                tabDataApr.add(row);
            }
        }


        tbl_applications.setModel(new DefaultTableModel(tabData, tabHeader));
        tbl_approved.setModel(new DefaultTableModel(tabDataApr, tabHeader));

    }

    private void fillEmp(List<EmployeeCourse> employeeCourses) {
        Vector<String> tableHead = new Vector<>();
        tableHead.add("Таб номер");
        tableHead.add("ФИО");
        tableHead.add("Курс");
        tableHead.add("Начало");
        tableHead.add("Конец");
        tableHead.add("Продолжительность");

        Vector tableData = new Vector();
        Vector tableDataFut = new Vector();

        for (EmployeeCourse ec : employeeCourses) {
            if (employees.contains(ec.getEmployee())) {
                if (ec.getStatus() == CourseStatus.INPROGRESS) {
                    Vector row = new Vector();
                    row.add(ec.getEmployee().getTabNum());
                    row.add(ec.getEmployee().getFio());
                    row.add(ec.getCourse().getName());
                    row.add(ec.getCourse().getDateStart());
                    row.add(ec.getCourse().getDateEnd());
                    row.add(ec.getCourse().getDuration());
                    tableData.add(row);
                } else if (ec.getStatus() == CourseStatus.REGISTERED){
                    Vector row = new Vector();
                    row.add(ec.getEmployee().getTabNum());
                    row.add(ec.getEmployee().getFio());
                    row.add(ec.getCourse().getName());
                    row.add(ec.getCourse().getDateStart());
                    row.add(ec.getCourse().getDateEnd());
                    row.add(ec.getCourse().getDuration());
                    tableDataFut.add(row);
                }
            }
        }

        tbl_emp.setModel(new DefaultTableModel(tableData,tableHead));
        tbl_futureCourses.setModel(new DefaultTableModel(tableDataFut,tableHead));
    }


    private void updateForm() {
//        ApplicationDAO appDAO = new ApplicationDAO();

        while (true) {
            try {
                Thread.sleep(1000 * 60); // 1000 = 1 сек
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fillApplications(appDAO.getApplicationsByUsers(employees));
            fillEmp(ecDAO.getEmployeeCourses());
            tbl_applications.revalidate();
            tbl_emp.revalidate();
            tbl_approved.revalidate();
            tbl_futureCourses.revalidate();
        }



    }


}
