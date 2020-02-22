package pkg.staff;

import pkg.application.Application;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee extends Staff {
    // список пройденных курсов

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "head_tabNum")
    private DepHead head;

    @OneToMany (mappedBy = "employee")
    private Set<EmployeeCourse> employeeCourses;

    @OneToMany (mappedBy = "employee", fetch = FetchType.EAGER)
    private Set<Application> applications;

    /////
    public String print() {
        return getTabNum() + "\t" + getFio() + "\t" + "возраст: " + getAge() + "\t" + "грейд: " + getGrade() ;
    }

    /////
    public DepHead getHead() {return head;}
    public void setHead(DepHead head) {this.head = head;}

    public Set<EmployeeCourse> getEmployeeCourses(){return employeeCourses;}
    public void setEmployeeCourses(Set<EmployeeCourse> employeeCourses){this.employeeCourses = employeeCourses;}

    public Set<Application> getApplications(){return applications;}
    public void setApplications(Set<Application> applications){this.applications = applications;}
}
