package pkg.application;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import pkg.course.Course;
import pkg.staff.DepHead;
import pkg.staff.Employee;
import pkg.staff.EmployeeCourse;
import pkg.staff.HR;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Application {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int appId;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "course_id")
    private Course course;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "employee_id")
    private Employee employee;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn (name = "head_id")
    private DepHead head;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "hr_id")
    private HR hr;

    private ApplicationStatus status;

    @Override
    public String toString() {
        return "Application{" +
                "appId=" + appId +
                ", course=" + course +
                ", employee=" + employee +
                ", head=" + head +
                ", hr=" + hr +
                ", status=" + status +
                ", dateAdd=" + dateAdd +
                ", dateChange=" + dateChange +
                ", employeeCourse=" + employeeCourse +
                ", comment='" + comment + '\'' +
                '}';
    }

    @CreationTimestamp
    private LocalDateTime dateAdd;

    @UpdateTimestamp
    private LocalDateTime dateChange;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "emp_course_id")
    private EmployeeCourse employeeCourse;


    private String comment;

    public void setDateAdd(LocalDateTime dateAdd) {
        this.dateAdd = dateAdd;
    }

    public void setDateChange(LocalDateTime dateChange) {
        this.dateChange = dateChange;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return appId == that.appId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(appId);
    }
//////

    public int getAppId (){return appId;}
    public LocalDateTime getDateAdd(){return dateAdd;}
    public LocalDateTime getDateChange(){return dateChange;}

    public Course getCourse(){return course;}
    public void setCourse(Course course){this.course = course;}

    public Employee getEmployee(){return employee;}
    public void setEmployee(Employee employee){this.employee = employee;}

    public DepHead getHead(){return head;}
    public void setHead(DepHead head){this.head = head;}

    public HR getHr(){return hr;}
    public void setHr(HR hr){this.hr = hr;}

    public ApplicationStatus getStatus(){return status;}
    public void setStatus(ApplicationStatus status){this.status = status;}

    public EmployeeCourse getEmployeeCourse(){return employeeCourse;}
    public void setEmployeeCourse(EmployeeCourse employeeCourse){this.employeeCourse = employeeCourse;}
}
