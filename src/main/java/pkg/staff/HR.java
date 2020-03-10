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

        Course course = application.getCourse();
        Employee employee = application.getEmployee();
        ApplicationDAO applicationDAO = new ApplicationDAO();
        application.setHr(this);

        if (!decision) {
            application.setStatus(ApplicationStatus.REJECT);
            applicationDAO.update(application);
        } else if (!checkCourseDate(course)) {
            application.setStatus(ApplicationStatus.BADDATE);
            applicationDAO.update(application);
            throw new AppExceptions(AppExceptionEnum.MAXCOUNT.name());
        } else if (!checkStudentCount(course)) {
            application.setStatus(ApplicationStatus.NOVACANT);
            applicationDAO.update(application);
            throw new AppExceptions(AppExceptionEnum.MAXCOUNT.name());
        } else if (!checkStudentSum(employee, course)) {
            application.setStatus(ApplicationStatus.EXCEEDSUM);
            applicationDAO.update(application);
            throw new AppExceptions(AppExceptionEnum.MAXCOUNT.name());
        } else {
            application.setStatus(ApplicationStatus.CLOSED);

            EmployeeCourse employeeCourse = new EmployeeCourse();
            employeeCourse.setCourse(application.getCourse());
            employeeCourse.setEmployee(application.getEmployee());
            employeeCourse.setStatus(CourseStatus.REGISTERED);

            applicationDAO.update(application);
            EmployeeCourseDAO ecDAO = new EmployeeCourseDAO();
            ecDAO.save(employeeCourse);
        }

    }

    private boolean checkStudentCount (Course course){
        return course.getMaxCount() >= course.getEmployeeCourses().size() + 1;
    }

    private boolean checkCourseDate (Course course){
        Date currentDate = new Date(System.currentTimeMillis());
        return course.getDateStart().toLocalDate().compareTo(currentDate.toLocalDate()) > 0;
    }

    private boolean checkStudentSum(Employee employee, Course course){
        float sum = employee.getApplicationSum((new Date(System.currentTimeMillis())).toLocalDate().getYear());
        if (sum + course.getPrice() > employee.getGrade().getMaxSum()) {
            return false;
        } else {
            return true;
        }
    }
}
