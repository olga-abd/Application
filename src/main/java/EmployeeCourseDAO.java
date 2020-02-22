import org.hibernate.Session;
import org.hibernate.Transaction;

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
}
