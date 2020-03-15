package pkg.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import pkg.application.Application;
import pkg.course.Course;
import pkg.dao.CourseDAO;
import pkg.dao.StaffDAO;
import pkg.exception.AppExceptions;
import pkg.staff.Employee;
import pkg.staff.Staff;

import static org.junit.Assert.*;

public class MainUtilsTest {

    private String login;
    private String psw;

    private Course course;
    private Employee employee;
    private int appId;


    @Before
    public void setUp() throws Exception {
        login = "petrov";
        psw = "qwerty";

        CourseDAO courseDAO = new CourseDAO();
        course = courseDAO.fingById(1);
        StaffDAO staffDAO = new StaffDAO();
        employee = (Employee) staffDAO.findById(3);
        appId = 0;
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getStaff() {
        try {
            Staff staff = MainUtils.getStaff(login,psw);
            assertTrue(staff.getTabNum() == 2);
        } catch (AppExceptions appExceptions) {
            appExceptions.printStackTrace();
        }

    }

    @Ignore
    @Test
    public void createApplication() {
        MainUtils.createApplication(course,employee);

        for (Application app : employee.getApplications()) {
            if (app.getCourse() == course) {
                if (appId < app.getAppId()) appId = app.getAppId();
            }
        }

        assertFalse(appId == 0);
    }
}