import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    public List<Application> getApplicationsByUser(int tn){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<Application> applications = session.createQuery("from Application where employee_id = " + tn).list();
        session.close();
        return applications;
    }

    public List<Application> getApplicationsByUsers(List<Employee> employees){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<Application> app0 = new ArrayList<>();
        for(Employee employee : employees) {
            List<Application> applications = session.createQuery("from Application where employee_id = " + employee.getTabNum()).list();
            app0.addAll(applications);
        }
        session.close();
        return app0;
    }

    public List<Application> getAllApplications(){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<Application> applications = session.createQuery("from Application").list();
        session.close();
        return applications;
    }

    public Application getApplicationById (int appId) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        //Application application = (Application) session.createQuery("from Application where appId = " + appId).getSingleResult();
        Application application = session.get(Application.class, appId);
        System.out.println("--- " + appId);
        System.out.println(application);
        session.close();
        return application;
    }

    public void save (Application application){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(application);
        transaction.commit();
        session.close();
    }

    public void update(Application application){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(application);
        transaction.commit();
        session.close();
    }


}
