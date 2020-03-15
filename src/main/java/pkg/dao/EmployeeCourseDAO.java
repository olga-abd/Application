package pkg.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pkg.staff.EmployeeCourse;
import pkg.utils.HibernateUtils;

import java.util.List;

public class EmployeeCourseDAO {

    public void update (EmployeeCourse employeeCourse) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(employeeCourse);
        transaction.commit();
        session.close();
    }

    public void save(EmployeeCourse employeeCourse) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employeeCourse);
        transaction.commit();
        session.close();
    }

    public List<EmployeeCourse> getEmployeeCourses() {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<EmployeeCourse> ec = session.createQuery("from pkg.staff.EmployeeCourse").list();
        session.close();
        return ec;
    }
}
