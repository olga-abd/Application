import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.Date;

public class MainUtils {
    public static Staff getStaff (String login, String password) throws AppExceptions {
        StaffDAO staffDAO = new StaffDAO();
        Staff staff = staffDAO.findByLogin(login);
        if (staff == null) throw new AppExceptions(AppExceptionEnum.NOSTAFF.name());
        if (staff.checkPassword(password)) return staff;
        throw new AppExceptions(AppExceptionEnum.INCPSW.name());
    }

    public static void createApplication(Course course, Employee employee){
        Application application = new Application();
        application.setCourse(course);
        application.setEmployee(employee);
        application.setStatus(ApplicationStatus.GENERATED);
        ApplicationDAO applicationDAO = new ApplicationDAO();
        applicationDAO.save(application);
    }

    public static void headApproveApp (DepHead dh, int appId, boolean decision) throws AppExceptions{
//        System.out.println ("utils: " + dh);
        ApplicationDAO appDAO = new ApplicationDAO();
        Application application = appDAO.getApplicationById(appId);
        if(application == null) throw new AppExceptions(AppExceptionEnum.NOAPP.name());
        dh.approveApp(application, decision);
        appDAO.update(application);
        System.out.println("Заявка " + appId + " обработана руководителем " + dh.getFio() + " с решением: " + (decision ? "согласовано" : "отказано"));
    }

    public static void hrApproveApp (HR hr, int appId, boolean decision) throws AppExceptions {
        ApplicationDAO appDAO = new ApplicationDAO();
        Application application = appDAO.getApplicationById(appId);
        if(application == null) throw  new AppExceptions(AppExceptionEnum.NOAPP.name());
        hr.approveApp(application, decision);
    }

    public static void createNewCourse (String name, String center, Date starDate, Date endDate, String duration, float price, int maxCount) {
        Course course = new Course();
        course.setName(name);
        course.setTraningCenter(center);
        course.setDateStart(starDate);
        course.setDateEnd(endDate);
        course.setDuration(duration);
        course.setPrice(price);
        course.setMaxCount(maxCount);

        CourseDAO courseDAO = new CourseDAO();
        courseDAO.save(course);
    }

}