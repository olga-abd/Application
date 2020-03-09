package pkg.staff;

import pkg.application.Application;
import pkg.application.ApplicationDAO;
import pkg.application.ApplicationStatus;
import pkg.course.Course;
import pkg.course.CourseStatus;
import pkg.exception.AppExceptionEnum;
import pkg.exception.AppExceptions;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class HR extends Staff {
    @OneToMany (mappedBy = "hr")
    private Set<Application> applications;


    /////
    public String print() {
        return getTabNum() + "\t" + getFio() + "\t" + "возраст: " + getAge() + "\t" + "грейд: " + getGrade().getGradeId() ;
    }

    /////
    public Set<Application> getApplications(){return applications;}
    public void setApplications(Set<Application> applications){this.applications = applications;}


    public void approveApp (Application application, boolean decision) throws AppExceptions {
        if (checkStudentCount(application.getCourse()) && checkCourseDate(application.getCourse())) {
            application.setHr(this);
            application.setStatus(decision ? ApplicationStatus.CLOSED : ApplicationStatus.REJECT);

            EmployeeCourse employeeCourse = new EmployeeCourse();
            employeeCourse.setCourse(application.getCourse());
            employeeCourse.setEmployee(application.getEmployee());
            employeeCourse.setStatus(CourseStatus.REGISTERED);

            ApplicationDAO applicationDAO = new ApplicationDAO();
            applicationDAO.update(application);
            EmployeeCourseDAO ecDAO = new EmployeeCourseDAO();
            ecDAO.save(employeeCourse);
        } else {
            application.setHr(this);
            application.setStatus(checkStudentCount(application.getCourse()) ? ApplicationStatus.BADDATE : ApplicationStatus.NOVACANT);
            ApplicationDAO applicationDAO = new ApplicationDAO();
            applicationDAO.update(application);
            throw new AppExceptions(AppExceptionEnum.MAXCOUNT.name());
        }
    }

    private boolean checkStudentCount (Course course){
        return course.getMaxCount() >= course.getEmployeeCourses().size();
    }

    private boolean checkCourseDate (Course course){
        Date currentDate = new Date(System.currentTimeMillis());
        return course.getDateStart().toLocalDate().compareTo(currentDate.toLocalDate()) > 0;
    }

    private boolean checkStudentSum(Employee employee, Course course){
        return false;
    }
}
