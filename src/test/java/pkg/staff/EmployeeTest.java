package pkg.staff;

import org.junit.Before;
import org.junit.Test;
import pkg.dao.StaffDAO;

import static junit.framework.TestCase.*;

public class EmployeeTest {
    private Employee employee;


    @Before
    public void setUp(){
        StaffDAO staffDAO = new StaffDAO();
        employee = (Employee) staffDAO.findById(3);


//        Set<Application> apps = new HashSet<>();
//        Course c1 = new Course();
//        c1.setDateEnd(new Date(System.currentTimeMillis()));
//        c1.setPrice(10000);
//
//        Application a1 = new Application();
//        a1.setCourse(c1);
//        a1.setEmployee(employee);
//
//        EmployeeCourse ec = new EmployeeCourse();
//        ec.setCourse(c1);
//        ec.setEmployee(employee);
//
//        a1.setEmployeeCourse(ec);
//
//        Course c2 = new Course();
//        c2.setDateEnd(c1.getDateEnd());
//        c2.setPrice(4000);
//
//        Application a2 = new Application();
//        a2.setCourse(c2);
//        a2.setEmployee(employee);
//
//        EmployeeCourse ec2 = new EmployeeCourse();
//        ec2.setEmployee(employee);
//        ec2.setCourse(c2);
//        a2.setEmployeeCourse(ec2);
//
//        apps.add(a1);
//        apps.add(a2);
//
//        employee.setApplications(apps);


    }

    @Test
    public void getApplicationSum() {
        float res = employee.getApplicationSum(2020);
        assertTrue(res == 35000);
    }
}