package pkg.course;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pkg.application.Application;
import pkg.application.ApplicationDAO;
import pkg.utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    public List<Course> getAllCourses() {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        List<Course> courseList = session.createQuery("from pkg.course.Course").list();
        session.close();
        return courseList;
    }

    public List<Course> getUserCourses(int tabnum){
        ApplicationDAO applicationDAO = new ApplicationDAO();
        List<Application> applications = applicationDAO.getApplicationsByUser(tabnum);
        List<Course> courses = getAllCourses();

        List<Course> userCourses = new ArrayList<>();
        for (Course course : courses){
            int flag = 0;
            for (Application application : applications){
                if (course.equals(application.getCourse())) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) userCourses.add(course);
        }

        return  userCourses;
    }

    public pkg.course.Course fingById (int courseId){
        Session session = HibernateUtils.getSesstionFactory().openSession();
        pkg.course.Course course = session.get(Course.class, courseId);
        session.close();
        return course;
    }

    public void save(Course course) {
        Session session = HibernateUtils.getSesstionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(course);
        transaction.commit();
        session.close();
    }
}
