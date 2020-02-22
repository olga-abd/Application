import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StaffDAO {

    public void save (Staff employee) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(employee);
        transaction.commit();
        session.close();
    }

    public void update (Staff staff) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(staff);
        transaction.commit();
        session.close();
    }

    public Staff findById (int tabNum) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Staff employee = session.get(Staff.class, tabNum);
        session.close();
        return employee;
    }

    public Staff findByLogin (String login) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Staff employee = (Staff) session.createQuery("from Staff where login = '" + login + "'").uniqueResult();
        session.close();
        return employee;
    }

    public void testConnection () {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        session.close();
    }

    public List<Staff> getAllStaff () {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<Staff> list = session.createQuery("from Staff").list();
        session.close();
        return list;
    }

    public List<Employee> getSubordinatesById(int tn) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<Employee>  employees = session.createQuery("from Employee where head_tabNum = " + tn).list();
        session.close();
        return employees;
    }

}
