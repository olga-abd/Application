import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @CreationTimestamp
    private LocalDateTime dateAdd;

    @UpdateTimestamp
    private LocalDateTime dateChange;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "emp_course_id")
    private EmployeeCourse employeeCourse;



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
