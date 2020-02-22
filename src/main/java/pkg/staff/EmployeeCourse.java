package pkg.staff;

import pkg.application.Application;
import pkg.course.Course;
import pkg.course.CourseStatus;

import javax.persistence.*;

@Entity
public class EmployeeCourse {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "employee_id")
    private Employee employee;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "course_id")
    private Course course;

    private CourseStatus status;

    @OneToOne (mappedBy = "employeeCourse")
    private Application application;

    //////
    public int getId(){return id;}

    public Employee getEmployee(){return employee;}
    public void setEmployee(Employee employee){this.employee = employee;}

    public Course getCourse(){return course;}
    public void setCourse(Course course){this.course = course;}

    public CourseStatus getStatus(){return status;}
    public void setStatus(CourseStatus status){this.status = status;}
}
